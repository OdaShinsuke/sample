package sqldatabase_doma_retry.retryconnection;

import org.junit.Assert;
import org.junit.Test;

import sqldatabase_doma_retry.Test1;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Test1DaoTest {
  private SQLServerDataSource failDataSource() {
    SQLServerDataSource tmp = new SQLServerDataSource();
    tmp.setUser("user");
    tmp.setPassword("bad password");
    tmp.setURL("jdbc:sqlserver://<your server>.database.windows.net:1433;database=<your database>;ssl=require");
    tmp.setLoginTimeout(30);
    return tmp;
  }

  @Test
  public void insertRetry() {
    SqlConfig config = new SqlConfig(failDataSource());
    Test1Dao dao = new Test1DaoImpl(config);
    try {
      config.getTransactionManager().required(() -> {
        dao.insert(new Test1(1, "aa"));
      });
      Assert.fail("例外が出るはず");
    } catch (Throwable __) {
      // 例外の中身までは調べない
    }

    Assert.assertEquals(4, config.getRetryCount());
  }

  @Test
  public void insert() {
    SqlConfig config = new SqlConfig();
    Test1Dao dao = new Test1DaoImpl(config);
    config.getTransactionManager().required(() -> {
      dao.insert(new Test1(1, "aa"));
    });
    Assert.assertEquals(0, config.getRetryCount());
  }
}
