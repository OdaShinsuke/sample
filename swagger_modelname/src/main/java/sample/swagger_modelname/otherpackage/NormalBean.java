package sample.swagger_modelname.otherpackage;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.wordnik.swagger.annotations.ApiModel;

// ApiModel の value で被らない名前を付けると Model 定義が残る
// 但し、JsonRootName で新しく付けた名前を指定しないと、他のModelでこのクラスを参照している時にリンクが機能しない
@JsonRootName("otherpackage.NormalBean")
@ApiModel(description="otherpackage.NormalBean:両方定義が残る", value="otherpackage.NormalBean")
public class NormalBean {
  public String field2;
}
