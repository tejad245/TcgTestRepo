<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="models.WeatherDetails" %>
<%
    // Retrieve the data from the request scope
  WeatherDetails weatherDetails = (WeatherDetails) request.getAttribute("weatherData");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color:  #FFA500;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            display: flex;
        }
        
        .divout {
            max-width: 900px;
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #4682b4;
            margin-bottom: 5px;
        }

        .weather {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            gap: 15px;
        }

        .weatherdata {
            background-color: #f0f8ff;
            border: 1px solid #b3d9ff;
            border-radius: 8px;
            padding: 15px;
            flex: 1;
            min-width: 120px;
            max-width: 200px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .weatherdata p {
            margin: 0;
            font-size: 1.1em;
            color: black;
            align:center;
        }

        .no-info {
            font-size: 1.2em;
            color: #ff4d4d;
        }

        form {
            margin-top: 5px;
        }

        label {
            font-size: 1.1em;
            color: #333;
        }

        input[type="text"],
        input[type="submit"] {
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1em;
        }

        input[type="submit"] {
            background-color: #4682b4;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #315f7b;
        }
        .form-container {
        
        .formdiv {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            gap: 20px;
        }

        .formdata {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
        }

        label {
            font-size: 1.1em;
            margin-bottom: 5px;
            display: block;
            color: #45474B;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #4682b4;
            color: white;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #315f7b;
        }
    </style>
</head>

<body>
    <div class="divout">
        <h1>Weather Details</h1>
        <!-- displaying the weather data -->
        <div class="weather">
            <% if (weatherDetails.getCode()==200) { %>
                <div class="weatherdata">
                    <p>Timestamp: <%= weatherDetails.getTimestamp() %></p>
                </div>
                <div class="weatherdata">
                    <p>Latitude: <%= weatherDetails.getLatitude() %></p>
                </div>
                <div class="weatherdata">
                    <p>Longitude: <%= weatherDetails.getLongititude() %></p>
                </div>
                <div class="weatherdata">
                    <p>Sky: <%= weatherDetails.getDescription() %></p>
                </div>
                <div class="weatherdata">
                    <p>Temperature: <%= weatherDetails.getTemperature() %> °C</p>
                </div>
                <div class="weatherdata">
                    <p>Humidity: <%= weatherDetails.getHumidity() %> %</p>
                </div>
                <div class="weatherdata">
                    <p>Wind Speed: <%= weatherDetails.getWind_speed() %> Km/h</p>
                </div>
            <% } else { %>
                <div class="no-info">
                    <h2>Oops.., No weather information available.</h2>
                </div>
            <% } %>
        </div>
    </div>

    <!-- forms to get the username and postalcode -->
    <div class="formdiv">
        <div class="formdata">
            <form action="history" method="get">
                <label for="username"><h3>Username:</h3></label>
                 <h6>Enter valid username to fetch all records for user</h6>
                <input type="text" id="username" name="username" required>
                <input type="submit" value="Submit">
            </form>
        </div>

        <div class="formdata">
            <form action="history" method="get">
                <label for="postalcode"><h3>Postal Code:</h3></label>
                 <h6>Enter valid postalcode to fetch all records</h6>
                <input type="text" id="postalcode" name="postalcode" maxlength=5 required >
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</body>
</html>
</body>
</html>


