package sqldatabase_doma_retry;

import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Table(name = "Test1")
public class Test1 {
  public Integer id;
  public String name;

  public Test1() {
    this(null, null);
  }

  public Test1(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
