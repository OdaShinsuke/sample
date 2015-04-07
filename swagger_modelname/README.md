Swagger で 別パッケージ/同名のクラスをモデルとして使う場合のサンプル

Swagger で同名のクラス(別パッケージ)をモデルとして扱うと、標準では上手く扱えない。(片方の定義が消える)　　
クラス名が重複する場合は、@ApiModel.value で別名を付けると重複しない。  

但し別名を付けた場合、そのクラスを他のクラス(Model)から参照していると正しくリンクが設定されない。
なので、@JsonRootName.value にも @ApiModel.value と同名を付ける必要がある。
  
@ApiModelProperty の設定ではどうにもならなそう。。


```java
package sample.swagger_modelname.normal;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(description="NormalBean:両方定義が残る")
public class NormalBean {
  public String field1;
}
```
```java
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
```

```java
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
```