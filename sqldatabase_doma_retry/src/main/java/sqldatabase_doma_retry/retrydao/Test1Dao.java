package sqldatabase_doma_retry.retrydao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;

import sqldatabase_doma_retry.Test1;

@Dao(config = SqlConfig.class)
public interface Test1Dao {
  @Insert
  int insert(Test1 entity);
}
