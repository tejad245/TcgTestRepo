package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.WeatherDetails;
import database.DataOperations;
import weather.WeatherApiImpl;

import java.io.IOException;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {

    private DataOperations dataOperations = new DataOperations();
    private WeatherApiImpl weatherApiImpl = new WeatherApiImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String postalcode = request.getParameter("postalcode");

        if (username.length() <= 2 || postalcode.length() != 5 || !postalcode.matches("\\d{5}")) {
            response.sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
            return;
        }

        int userId = dataOperations.addUser(username, postalcode);

        WeatherDetails weatherData = weatherApiImpl.main(postalcode, userId);
        System.out.println(weatherData.getCode());
        request.setAttribute("weatherData", weatherData);
        request.setAttribute("username", username);
        request.getRequestDispatcher("weather.jsp").forward(request, response);
    }
}

