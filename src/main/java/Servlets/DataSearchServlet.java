package Servlets;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.models.media.MediaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.WeatherData;
import models.WeatherDetails;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import database.DataOperations;
@WebServlet("/history")
public class  DataSearchServlet extends HttpServlet {

    private DataOperations dataOperations = new DataOperations();

//    @GET
//    @Path("/greet")
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Greets the user", notes = "This method returns a greeting message.")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String postalcode = request.getParameter("postalcode");
        List<WeatherData> weatherDataList = null;

        if (username != null) {
            weatherDataList = dataOperations.getDetails(username,"username");
        } else{
            weatherDataList = dataOperations.getDetails(postalcode,"postalcode");
        }

        String searchParam= username!=null ? username : postalcode;
        request.setAttribute("weatherDataList", weatherDataList);
        request.setAttribute("searchParam",searchParam);
        request.getRequestDispatcher("listdata.jsp").forward(request, response);
    }
}
