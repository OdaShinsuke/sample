package sample.swagger_modelname.normal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class NormalModelResult {
  @ApiModelProperty
  public NormalBean bean;
  @ApiModelProperty
  public sample.swagger_modelname.otherpackage.NormalBean otherBean;
}
