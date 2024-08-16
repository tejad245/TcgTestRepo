package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.WeatherData;
import models.WeatherDetails;

import java.io.IOException;
import java.util.List;

import database.DataOperations;

@WebServlet("/history")
public class  DataSearchServlet extends HttpServlet {

    private DataOperations dataOperations = new DataOperations();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
