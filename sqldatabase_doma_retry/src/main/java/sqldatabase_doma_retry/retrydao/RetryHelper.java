package sqldatabase_doma_retry.retrydao;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.seasar.doma.jdbc.JdbcException;
import org.seasar.doma.message.Message;

public class RetryHelper {
  private static final int DEFAULT_MAX = 3;
  private static final int DEFAULT_DELAY = 10;
  // SQL Server デッドロックのエラーコード
  private static final int ERROR_CODE_DEADLOCK = 1205;

  public static <T> T retry(Supplier<T> supplier) {
    return retry(supplier, DEFAULT_MAX, DEFAULT_DELAY);
  }

  public static <T> T retry(Supplier<T> supplier, int maxCount, int delay) {
    int count = 0;
    JdbcException lastEx = null;
    while (count <= maxCount) {
      try {
        System.out.println(count + "回目ー");
        return supplier.get();
      } catch (JdbcException ex) {
        if (isRetryable(ex)) {
          try {
            TimeUnit.SECONDS.sleep(delay);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          count++;
          lastEx = ex;
          continue;
        }

        throw ex;
      }
    }

    throw new RuntimeException(String.valueOf(count), lastEx);
  }

  private static boolean isRetryable(JdbcException ex) {
    if (ex.getMessageResource() != null
        && Message.DOMA2015.getCode().equals(ex.getMessageResource().getCode())) {
      return true;
    }

    if (ex.getCause() instanceof SQLException
        && ((SQLException) ex.getCause()).getErrorCode() == ERROR_CODE_DEADLOCK) {
      return true;
    }
    return false;
  }
}
