package sample;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BackingBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String fileNm;
  public String getFileNm() {
    return fileNm;
  }
  public void setFileNm(String fileNm) {
    this.fileNm = fileNm;
  }


  public void submit() {}
}
