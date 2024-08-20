import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import Servlets.SubmitServlet;
import database.DataOperations;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.WeatherDetails;
import weather.WeatherApiImpl;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubmitServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private DataOperations dataOperations;

    @Mock
    private WeatherApiImpl weatherApiImpl;

    @InjectMocks
    private SubmitServlet submitServlet;

    @Before
    public void setUp() {
        when(request.getRequestDispatcher("weather.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoPost_ValidInput() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("vijay");
        when(request.getParameter("postalcode")).thenReturn("12345");
        when(dataOperations.addUser("vijay", "12345")).thenReturn(1);
        WeatherDetails weatherDetails = new WeatherDetails();
        when(weatherApiImpl.main("12345", 1)).thenReturn(weatherDetails);

        submitServlet.doPost(request, response);
        verify(dataOperations, times(1)).addUser("vijay", "12345");
        verify(weatherApiImpl, times(1)).main("12345", 1);
        verify(request, times(1)).setAttribute("weatherData", weatherDetails);
        verify(request, times(1)).setAttribute("username", "vijay");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_InvalidUsername() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("Jo");
        when(request.getParameter("postalcode")).thenReturn("12345");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_InvalidPostalCode_Length() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("vijay");
        when(request.getParameter("postalcode")).thenReturn("1234");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_InvalidPostalCode_NonDigit() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("vijay");
        when(request.getParameter("postalcode")).thenReturn("12a45");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_EmptyUsernameAndPostalCode() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("postalcode")).thenReturn("");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_ValidUsername_EmptyPostalCode() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("vijay");
        when(request.getParameter("postalcode")).thenReturn("");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_EmptyUsername_ValidPostalCode() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("postalcode")).thenReturn("12345");

        submitServlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?error=Invalid input, Please re-enter minimum 3 chars for name and 5 digits postalcode");
        verify(dataOperations, never()).addUser(anyString(), anyString());
        verify(weatherApiImpl, never()).main(anyString(), anyInt());
    }

    @Test
    public void testDoPost_ValidInput_WeatherApiFailure() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("vijay");
        when(request.getParameter("postalcode")).thenReturn("12345");
        when(dataOperations.addUser("vijay", "12345")).thenReturn(1);
        when(weatherApiImpl.main("12345", 1)).thenThrow(new IOException("Weather API fail"));

        submitServlet.doPost(request, response);

        // verification
    }
}
