package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@RequestScoped
@Named
public class ImageBackingBean {
  public StreamedContent getImage() throws IOException {
    String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
    
    String fileNm = (param == null || param.length() == 0) ? "default.png" : param;
    
    Path path = Paths.get("D:\\home\\image", fileNm);
    return new DefaultStreamedContent(Files.newInputStream(path, StandardOpenOption.READ));
  }
}
