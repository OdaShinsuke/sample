package sample;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

@RequestScoped
public class Dao {
  @Inject private DataSource ds;
  
  public String getName(String name) {
    return "Hello, " + name;
  }
}
