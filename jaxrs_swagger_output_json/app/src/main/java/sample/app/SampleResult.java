package sample.app;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description="サンプル結果")
public class SampleResult {
  @ApiModelProperty(required = true, dataType = "string", notes="結果", example="Hello, hoge")
  private final String result;
  
  public String getResult() {
    return result;
  }

  public SampleResult(String result) {
    this.result = result;
  }
}
