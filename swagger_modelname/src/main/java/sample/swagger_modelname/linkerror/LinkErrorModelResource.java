package sample.swagger_modelname.linkerror;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/linkerror")
@Api(value="/linkerror", description="モデル定義が消えないけど、リンクがおかしいAPI")
public class LinkErrorModelResource {
  @GET
  @ApiOperation(value="サンプル", httpMethod="get", nickname="get", response=LinkErrorModelResult.class)
  public LinkErrorModelResult get() {
    return new LinkErrorModelResult();
  }
}
