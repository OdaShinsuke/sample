package sample.swagger_modelname;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import sample.swagger_modelname.linkerror.LinkErrorModelResource;
import sample.swagger_modelname.normal.NormalModelResource;
import sample.swagger_modelname.problem.ProblemModelResource;

import com.wordnik.swagger.jaxrs.Reader;
import com.wordnik.swagger.models.Swagger;
import com.wordnik.swagger.util.Json;

public class GenerateSwaggerTest {
  /**
   * JSON の definitions に ProblemBean の定義が1個しかない。(otherpackage.ProblemBean しか無い)
   * @throws IOException
   */
  @Test
  public void problemModel() throws IOException {
    Reader reader = new Reader(new Swagger());
    reader.read(ProblemModelResource.class);
    Assert.assertEquals(Files.readAllLines(Paths.get("src", "test", "resources", "ProblemModel.json")).get(0), Json.mapper().writeValueAsString(reader.getSwagger()));
  }
  /**
   * JSON の dinitions の LinkErrorModelResult の properties otherBean のリンクが、LinkErrorBean となっており、otherpackage.LinkErrorBean ではない
   * @throws IOException
   */
  @Test
  public void linkErrorModel() throws IOException {
    Reader reader = new Reader(new Swagger());
    reader.read(LinkErrorModelResource.class);
    Assert.assertEquals(Files.readAllLines(Paths.get("src", "test", "resources", "LinkErrorModel.json")).get(0), Json.mapper().writeValueAsString(reader.getSwagger()));
  }
  /**
   * 正しい JSON が出力される
   * @throws IOException
   */
  @Test
  public void normalModel() throws IOException {
    Reader reader = new Reader(new Swagger());
    reader.read(NormalModelResource.class);
    Assert.assertEquals(Files.readAllLines(Paths.get("src", "test", "resources", "NormalModel.json")).get(0), Json.mapper().writeValueAsString(reader.getSwagger()));
  }
}
