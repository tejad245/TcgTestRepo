package swagger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
//import io.swagger.v3.oas.model.OpenAPI;
import io.swagger.jaxrs.config.BeanConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class SwaggerConfig extends Application {

  
    public void init(ServletConfig config) throws ServletException {
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/YourProjectName/api");
//        beanConfig.setResourcePackage("");
       // beanConfig.setPrsettyPrint(true); 
        beanConfig.setScan(true);
    }
}
