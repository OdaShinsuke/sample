package sqldatabase_doma_retry.retrydao;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MssqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SqlConfig implements Config {
  private final LocalTransactionDataSource datasource;
  private final TransactionManager manager;

  private static SQLServerDataSource DEFAULT_DATASOURCE;
  static {
    SQLServerDataSource tmp = new SQLServerDataSource();
    tmp.setUser("user");
    tmp.setPassword("pass");
    tmp.setURL("jdbc:sqlserver://<your server>.database.windows.net:1433;database=<your database>;ssl=require");
    tmp.setLoginTimeout(30);
    DEFAULT_DATASOURCE = tmp;
  }

  public SqlConfig() {
    this(DEFAULT_DATASOURCE);
  }

  public SqlConfig(SQLServerDataSource ds) {
    datasource = new LocalTransactionDataSource(ds);
    manager = new LocalTransactionManager(
        datasource.getLocalTransaction(getJdbcLogger()));
  }

  @Override
  public DataSource getDataSource() {
    return datasource;
  }

  @Override
  public TransactionManager getTransactionManager() {
    return manager;
  }

  @Override
  public Dialect getDialect() {
    return new MssqlDialect();
  }
}
