package sqldatabase_doma_retry.retryconnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class RetryConnectionDataSource implements DataSource {
  private final DataSource datasource;
  public static final int DEFAULT_MAX = 3;
  public static final int DEFAULT_DELAY = 10;
  private final int max;
  private final int delay;
  private int count;

  public int getRetryCount() {
    return count;
  }

  public RetryConnectionDataSource(DataSource dataSource) {
    this(dataSource, DEFAULT_MAX, DEFAULT_DELAY);
  }

  public RetryConnectionDataSource(DataSource datasource, int max, int delay) {
    this.datasource = datasource;
    this.max = max;
    this.delay = delay;
  }

  /**
   * @return
   * @throws SQLException
   * @see javax.sql.CommonDataSource#getLogWriter()
   */
  public PrintWriter getLogWriter() throws SQLException {
    return datasource.getLogWriter();
  }

  /**
   * @param iface
   * @return
   * @throws SQLException
   * @see java.sql.Wrapper#unwrap(java.lang.Class)
   */
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return datasource.unwrap(iface);
  }

  /**
   * @param out
   * @throws SQLException
   * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
   */
  public void setLogWriter(PrintWriter out) throws SQLException {
    datasource.setLogWriter(out);
  }

  /**
   * @param iface
   * @return
   * @throws SQLException
   * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
   */
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return datasource.isWrapperFor(iface);
  }

  /**
   * @return
   * @throws SQLException
   * @see javax.sql.DataSource#getConnection()
   */
  public Connection getConnection() throws SQLException {
    count = 0;
    while (true) {
      try {
        System.out.println(count + "回目-");
        return datasource.getConnection();
      } catch (SQLException e) {
        count++;
        if (count > max) {
          System.out.println("リトライ回数超えた-");
          throw e;
        }
        try {
          TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  /**
   * @param seconds
   * @throws SQLException
   * @see javax.sql.CommonDataSource#setLoginTimeout(int)
   */
  public void setLoginTimeout(int seconds) throws SQLException {
    datasource.setLoginTimeout(seconds);
  }

  /**
   * @param username
   * @param password
   * @return
   * @throws SQLException
   * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
   */
  public Connection getConnection(String username, String password)
      throws SQLException {
    return datasource.getConnection(username, password);
  }

  /**
   * @return
   * @throws SQLException
   * @see javax.sql.CommonDataSource#getLoginTimeout()
   */
  public int getLoginTimeout() throws SQLException {
    return datasource.getLoginTimeout();
  }

  /**
   * @return
   * @throws SQLFeatureNotSupportedException
   * @see javax.sql.CommonDataSource#getParentLogger()
   */
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return datasource.getParentLogger();
  }
}
