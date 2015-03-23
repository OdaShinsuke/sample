package sample.app;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RequestScoped
@Path("/sample")
@Api(value="/sample", description="サンプルAPI")
public class SampleResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value="サンプルget", notes="hello, world", httpMethod="get", produces="appication/json", nickname="get", response=SampleResult.class)
  public SampleResult get(@QueryParam("name") String name) {
    return new SampleResult("Hello, " + name);
  }
}
