package sample.swagger_modelname.normal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/normal")
@Api(value="/normal", description="モデル定義が消えないAPI")
public class NormalModelResource {
  @GET
  @ApiOperation(value="サンプル", httpMethod="get", nickname="get", response=NormalModelResult.class)
  public NormalModelResult get() {
    return new NormalModelResult();
  }
}
