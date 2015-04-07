package sample.swagger_modelname.otherpackage;

import com.wordnik.swagger.annotations.ApiModel;

// @ApiModel.value で別名を指定しているので、モデルの定義は重複しないが、JsonRootName を指定していないので、LinkErrorModelResult の定義で問題が出る。
@ApiModel(description="otherpackage.LinkErrorBean:定義が残るが、プロパティのリンクで問題が出る", value="otherpackage.LinkErrorBean")
public class LinkErrorBean {
  public String field2;
}
