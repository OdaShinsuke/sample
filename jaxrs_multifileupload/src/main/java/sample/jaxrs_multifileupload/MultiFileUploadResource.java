package sample.jaxrs_multifileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@RequestScoped
@Path("/multifileupload")
public class MultiFileUploadResource {
  @POST
  @Path("/multi")
  @Consumes("multipart/form-data")
  @Produces("application/json")
  public String upload(@MultipartForm FormData form) {
    System.out.println("MultiFileUploadResource#upload");
    writeFile(form.getData1(), "data1");
    writeFile(form.getData2(), "data2");
    writeFile(form.getData3(), "data3");
    return "uploadfiles success " + form.getText();
  }
  
  private void writeFile(byte[] content, String name) {
    if (content == null || content.length == 0) {
      System.out.println("MultiFileUploadResource#writeFile content is null:" + name);
    }
    try {
      File f = Paths.get("E:/hoge", name).toFile();
      if (!f.exists()) {
        f.createNewFile();
      }
      try (FileOutputStream fos = new FileOutputStream(f)) {
        fos.write(content);
        fos.flush();
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
