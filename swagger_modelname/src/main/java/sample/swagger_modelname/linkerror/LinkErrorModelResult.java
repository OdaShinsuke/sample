package sample.swagger_modelname.linkerror;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class LinkErrorModelResult {
  @ApiModelProperty
  public LinkErrorBean bean;
  @ApiModelProperty
  public sample.swagger_modelname.otherpackage.LinkErrorBean otherBean;
}
