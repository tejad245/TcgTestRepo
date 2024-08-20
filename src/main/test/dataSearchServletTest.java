import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import Servlets.DataSearchServlet;
import database.DataOperations;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.WeatherData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class dataSearchServletTest {

	 	@Mock
	 	private HttpServletRequest request;

	    @Mock
	    private HttpServletResponse response;

	    @Mock
	    private RequestDispatcher requestDispatcher;

	    @Mock
	    private DataOperations dataOperations;

	    @InjectMocks
	    private DataSearchServlet dataSearchServlet;

	    private List<WeatherData> weatherDataList;

	    @Before
	    public void setUp() {
	        weatherDataList = new ArrayList<>();
	        weatherDataList.add(new WeatherData()); 

	        when(request.getRequestDispatcher("listdata.jsp")).thenReturn(requestDispatcher);
	    }

	    @Test
	    public void testDoGet_UsernameProvided() throws ServletException, IOException {
	        
	        when(request.getParameter("username")).thenReturn("testUser");
	        when(request.getParameter("postalcode")).thenReturn(null);
	        when(dataOperations.getDetails("testUser", "username")).thenReturn(weatherDataList);
	        
	        dataSearchServlet.doGet(request, response);
	        verify(dataOperations, times(1)).getDetails("testUser", "username");
	        verify(request, times(1)).setAttribute("weatherDataList", weatherDataList);
	        verify(request, times(1)).setAttribute("searchParam", "testUser");
	        verify(requestDispatcher).forward(request, response);
	    }
	    
	    @Test
	    public void testDoGet_PostalCodeProvided() throws ServletException, IOException {
	    
	        when(request.getParameter("username")).thenReturn(null);
	        when(request.getParameter("postalcode")).thenReturn("12345");
	        when(dataOperations.getDetails("12345", "postalcode")).thenReturn(weatherDataList);
	        
	        dataSearchServlet.doGet(request, response);	        
	        verify(dataOperations, times(1)).getDetails("12345", "postalcode");
	        verify(request, times(1)).setAttribute("weatherDataList", weatherDataList);
	        verify(request, times(1)).setAttribute("searchParam", "12345");
	        verify(requestDispatcher).forward(request, response);
	    }

	    @Test
	    public void testDoGet_BothParametersNull() throws ServletException, IOException {
	        
	        when(request.getParameter("username")).thenReturn(null);
	        when(request.getParameter("postalcode")).thenReturn(null);

	        dataSearchServlet.doGet(request, response);
	        
	        verify(dataOperations, never()).getDetails(anyString(), anyString());
	        verify(request, times(1)).setAttribute("weatherDataList", null);
	        verify(request, times(1)).setAttribute("searchParam", null);
	        verify(requestDispatcher).forward(request, response);
	    }
	    
	    @Test
	    public void testDoGet_EmptyWeatherDataList() throws ServletException, IOException {
	  
	        when(request.getParameter("username")).thenReturn("testUser");
	        when(dataOperations.getDetails("testUser", "username")).thenReturn(new ArrayList<>());
	        dataSearchServlet.doGet(request, response);
	        verify(dataOperations, times(1)).getDetails("testUser", "username");
	        verify(request, times(1)).setAttribute("weatherDataList", new ArrayList<>());
	        verify(request, times(1)).setAttribute("searchParam", "testUser");
	        verify(requestDispatcher).forward(request, response);
	    }
	    
	    @Test(expected = ServletException.class)
	    public void testDoGet_DataOperationsThrowsException() throws ServletException, IOException {
	        when(request.getParameter("username")).thenReturn("testUser");
	        when(dataOperations.getDetails("testUser", "username")).thenThrow(new ServletException());
	        dataSearchServlet.doGet(request, response);
	    }

	    @Test
	    public void testDoGet_ForwardToCorrectJSP() throws ServletException, IOException {
	        when(request.getParameter("username")).thenReturn("testUser");
	        when(dataOperations.getDetails("testUser", "username")).thenReturn(weatherDataList);
	        dataSearchServlet.doGet(request, response);

	        verify(request).getRequestDispatcher("listdata.jsp");
	        verify(requestDispatcher).forward(request, response);
	    }
}
 

