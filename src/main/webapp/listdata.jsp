<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>
<%@ page import="database.DataOperations" %>
<%@ page import="weather.WeatherApiImpl" %>
<%@ page import="models.*" %>
 <%
	List<WeatherData> weatherDataList = (List<WeatherData>) request.getAttribute("weatherDataList");
	String searchParam=(String) request.getAttribute("searchParam");
%>  
<!DOCTYPE html> 
<html> 
    <head> 
       <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFA500;
            color: #333;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #4682b4;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #4682b4;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .no-details {
            text-align: center;
            font-size: 1.2em;
            color: #777;
        }
    </style>
    </head> 
    <body> 
      <div class="container">
        <h1>Weather Details</h1>
        
     <%-- if weatherlist is not null or if list is not empty , printing the data --%>
        <% if (weatherDataList != null && weatherDataList.size()!=0) { %>
            <table>
                <tr>
                    <th>Timestamp</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    <th>Description</th>
                    <th>Temperature (°C)</th>
                    <th>Humidity (%)</th>
                    <th>Wind Speed (Km/h)</th>
                </tr>
                <% for (WeatherData detail : weatherDataList) { %>
                    <tr>
                        <td><%= detail.getTimestamp() %></td>
                        <td><%= detail.getLatitude() %></td>
                        <td><%= detail.getLongititude() %></td>
                        <td><%= detail.getDescription() %></td>
                        <td><%= detail.getTemperature() %></td>
                        <td><%= detail.getHumidity() %></td>
                        <td><%= detail.getWind_speed() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
    <%-- if weatherlist is null or if list is empty , printing the no data --%>
    
            <p class="no-details">No weather details found for user/postalcode : <%= searchParam %></p>
        <% } %>
    </div>
       
    </body> 
</html> 