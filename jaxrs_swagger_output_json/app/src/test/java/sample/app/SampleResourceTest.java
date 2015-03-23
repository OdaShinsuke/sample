package sample.app;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SampleResourceTest {

  public static String TEST_SERVER = System.getProperty("TEST_SERVER", "localhost:8080");

  private static String convApiUri(String apiPath) {
    String basePath = "http://" + TEST_SERVER + "/app/api";
    return basePath + apiPath;
  }

  @Test @RunAsClient
  public void get() {
    Response response = ClientBuilder.newBuilder()
      .build()
      .target(convApiUri("/sample?name=abc"))
      .request().get();

    Assert.assertEquals(200, response.getStatus());
    String actual = response.readEntity(String.class);
    Assert.assertEquals("{\"result\":\"Hello, abc\"}", actual);
  }

  @Deployment
  public static WebArchive createDeployment() {
    Path webinf = Paths.get("src", "main", "webapp", "WEB-INF");

    return ShrinkWrap.create(WebArchive.class, "app.war")
      .addClasses(SampleApplication.class, SampleResource.class, SampleResult.class)
      .addAsWebInfResource(webinf.resolve("web.xml").toFile())
      .addAsWebInfResource(webinf.resolve("beans.xml").toFile());
  }
}
