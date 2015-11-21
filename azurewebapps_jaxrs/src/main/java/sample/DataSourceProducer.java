package sample;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@ApplicationScoped
public class DataSourceProducer {
  private DataSource datasource;
  
  @PostConstruct
  void postConstruct() {
    SQLServerDataSource ds = new SQLServerDataSource();
    ds.setURL(System.getenv("SQLAZURECONNSTR_DefaultConnection"));
    datasource = ds;
  }
  @Produces
  public DataSource getDataSource() {
    return datasource;
  }
}
