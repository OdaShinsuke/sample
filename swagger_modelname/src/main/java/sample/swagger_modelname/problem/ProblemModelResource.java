package sample.swagger_modelname.problem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/problem")
@Api(value="/problem", description="モデル定義が消えるAPI")
public class ProblemModelResource {
  @GET
  @ApiOperation(value="サンプル", httpMethod="get", nickname="get", response=ProblemModelResult.class)
  public ProblemModelResult get() {
    return new ProblemModelResult();
  }
}
