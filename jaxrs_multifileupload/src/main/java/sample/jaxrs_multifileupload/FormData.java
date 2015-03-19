package sample.jaxrs_multifileupload;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FormData {
  private String text;
  @FormParam("text")
  @PartType("text/plain")
  public void setText(String text) {
    this.text = text;
  }
  public String getText() {
    return this.text;
  }
  
  private byte[] data1;
  @FormParam("uploadedFile1")
  @PartType("application/octet-stream")
  public void setData1(byte[] data1) {
    this.data1 = data1;
  }
  public byte[] getData1() {
    return this.data1;
  }
  
  private byte[] data2;
  @FormParam("uploadedFile2")
  @PartType("application/octet-stream")
  public void setData2(byte[] data2) {
    this.data2 = data2;
  }
  public byte[] getData2() {
    return this.data2;
  }
  
  private byte[] data3;
  @FormParam("uploadedFile3")
  @PartType("application/octet-stream")
  public void setData3(byte[] data3) {
    this.data3 = data3;
  }
  public byte[] getData3() {
    return this.data3;
  }
}
