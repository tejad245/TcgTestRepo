Project-structure
WeatherApp/
├── src/
│   └── main/
│       └── java/
│           ├── weather/
│           │   └── WeatherApiImpl.java
│           ├── model/
│           │   ├── Weather.java
│           │   ├── WeatherResponse.java
│           │   └── ...
│           ├── database/
│           │   ├── DatabaseConnection.java
│           │   ├── DataOperations.java
│           │   └── ...
│           └── servlets/
│               ├── SubmitServlet.java
│               ├── SearchServlet.java
│               └── ...
├── webapp/
│   ├── index.jsp
│   ├── weather.jsp
│   ├── listdata.jsp
│   └── WEB-INF/
│       ├── lib/
│       │   └── (Required JAR files)
│       └── web.xml
└── pom.xml (If using Maven)

Packages and Classes
weather Package:

WeatherApiImpl.java: Handles the integration with the OpenWeatherMap API using HTTP GET requests.
model Package:

Contains model classes for mapping the JSON response from the weather API to Java objects using Jackson's ObjectMapper.
Example classes:
Weather.java
WeatherResponse.java
...
database Package:

DatabaseConnection.java: Manages the MySQL JDBC connection.
DataOperations.java: Provides methods to execute SQL queries and retrieve or store data.
servlets Package:

Contains servlets that handle the interaction between the JSP pages and the backend logic.
Example servlets:
SubmitServlet.java
SearchServlet.java
...
JSP Files
index.jsp:

A user form to submit a username and postal code. The input is validated, and if successful, the data is stored in the database and the weather API is called.
weather.jsp:

Displays the weather data retrieved based on the postal code. It also contains two forms to fetch the weather history by either username or postal code.
listdata.jsp:

Displays the weather history based on the username or postal code entered by the user in the weather.jsp file.
Database Structure
Schema: weatherrequest_history
Tables:
user_postaldetails:

Columns:
id (Auto-generated primary key)
username
postalcode
Purpose: Stores user details upon form submission from index.jsp.
weather_history:

Columns:
weatherId (Auto-generated primary key)
timestamp
latitude
longitude
description
temperature
humidity
wind_speed
user_postal_id (Foreign key referencing id in user_postaldetails)
Purpose: Stores weather data corresponding to the user's postal code.
SQL Query Example:
To retrieve weather history based on a postal code:

SELECT w.timestamp, w.latitude, w.longitude, w.description, w.temperature, w.humidity, w.wind_speed
FROM weather_history w
JOIN user_postaldetails c ON w.user_postal_id = c.id
WHERE c.postalcode = '99950';

