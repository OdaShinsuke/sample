package sample.swagger_modelname.problem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProblemModelResult {
  @ApiModelProperty
  public ProblemBean bean;
  @ApiModelProperty
  public sample.swagger_modelname.otherpackage.ProblemBean otherBean;
}
