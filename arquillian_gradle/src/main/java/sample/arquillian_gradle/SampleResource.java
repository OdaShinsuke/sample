package sample.arquillian_gradle;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;

@RequestScoped
@Path("/sample")
public class SampleResource {
  @GET
  public String get(@QueryParam("name") String name) {
    return "Hello, " + StringUtils.defaultString(name);
  }
}
