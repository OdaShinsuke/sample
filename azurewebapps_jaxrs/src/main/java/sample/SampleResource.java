
package sample;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/sample")
@RequestScoped
public class SampleResource {
  @Inject Dao dao;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/greet")
  public SampleResult greet(@QueryParam("name") String name) {
    return new SampleResult(1, dao.getName(name));
  }
}
