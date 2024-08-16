# 🌦️ WeatherApp

Welcome to the **WeatherApp** repository! This Java-based web application integrates with the OpenWeatherMap API to provide weather data based on user-submitted postal codes. The application is built using Java Servlets, JSP, and MySQL for data persistence.

Historical wather data requested by user so far based on username and postalcode
- Get  /TcgTestApplication/history?username=tejad245
- Get  /TcgTestApplication/history?postalcode=55590

## 📂 Project Structure

The project is organized as follows:


## 📦 Packages and Classes

### `weather` Package
- **WeatherApiImpl.java**: Handles the integration with the OpenWeatherMap API using HTTP GET requests and persist data retrieved to database

### `model` Package
Contains model classes for mapping the JSON response from the weather API to Java objects using Jackson's `ObjectMapper`.

### `database` Package
- **DatabaseConnection.java**: Manages the MySQL JDBC connection.
- **DataOperations.java**: Provides methods to execute SQL queries and retrieve or store data.

### `servlets` Package
Contains servlets that handle the interaction between the JSP pages and the backend logic.

- **SubmitServlet.java**: Processes the form submission validastes and add data to database , fetch weather data based on postlcode from `index.jsp`.
- **SearchServlet.java**: Handles search requests and fetches the weather history.

## 🖥️ JSP Files

### `index.jsp`
A user form to submit a username and postal code. The input is validated, and if successful, the data is stored in the database, and the weather API is called.

### `weather.jsp`
Displays the weather data retrieved based on the postal code. It also contains two forms to fetch the weather history by either username or postal code.

### `listdata.jsp`
Displays the weather history based on the username or postal code entered by the user in the `weather.jsp` file.

## 🗃️ Database Structure

### Schema: `weatherrequest_history`

#### Tables:

1. **`user_postaldetails`**
   - **id** (Auto-generated primary key)
   - **username**
   - **postalcode**
   - **Purpose**: Stores user details upon form submission from `index.jsp`.

2. **`weather_history`**
   - **weatherId** (Auto-generated primary key)
   - **timestamp**
   - **latitude**
   - **longitude**
   - **description**
   - **temperature**
   - **humidity**
   - **wind_speed**
   - **user_postal_id** (Foreign key referencing `id` in `user_postaldetails`)
   - **Purpose**: Stores weather data corresponding to the user's postal code.

### Example SQL Query:
To retrieve weather history based on a postal code:
sql
- SELECT w.timestamp, w.latitude, w.longitude, w.description, w.temperature, w.humidity, w.wind_speed
FROM weather_history w
JOIN user_postaldetails c ON w.user_postal_id = c.id
WHERE c.postalcode = '99950';

## Deplying and running:
Create a WAR (Web ARchive) file manually. This can be done using command-line tools or file compression utilities.
 - jar -cvf TcgTestApplication.war *

Copy the WAR file (TcgTestApplication.war) to the webapps directory of your Tomcat installation.
Tomcat will automatically deploy the application by extracting the WAR file into a directory with the same name as the WAR file (minus the .war extension).
Start Tomcat using the startup script (startup.sh or startup.bat).
go to http://localhost:8080/TcgTestApplication to access application
