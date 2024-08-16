package weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import database.DataOperations;
import models.WeatherDetails;
import models.WeatherHistory;
public class WeatherApiImpl {

	DataOperations dataOperations=new DataOperations();
	 
		/**
		 * 
		 * @param postalcode
		 * @param id
		 * @return weatherdetails after calling the api
		 */
		public WeatherDetails main(String postalcode,int id){
			
			int code=0;
			String uri="https://api.openweathermap.org/data/2.5/weather?zip="+postalcode+"&appid=a6f6975b82a60746ccc866d3ca2ca369";
			
			//building http request
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = null;
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				code=500;
				System.out.println("The error thrown : "+ e.getMessage());
			} catch (InterruptedException e) {
				code=500;
				System.out.println("The error thrown : "+ e.getMessage());
			}
		
			WeatherHistory weatherHistory=null;
			ObjectMapper objectMapper = new ObjectMapper();	
			objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			try {
				if(response!=null) {
				//using objectmapper transform to object of type WeatherHistory
				weatherHistory = objectMapper.readValue(response.body(), WeatherHistory.class);
				}
			} catch (Exception e) {
				code=500;
				System.out.println("The error thrown : "+ e.getMessage());
			}
			//getting the current timestamp , to get current time
			String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
			WeatherDetails weatherDetails=null;
			if(weatherHistory!=null)
			{
				if(weatherHistory.getCod()==200)
				{
					weatherDetails=dataOperations.addWeatherDetails(timestamp,weatherHistory.getCoord().getLat(),weatherHistory.getCoord().getLon()
							,weatherHistory.getWeather().get(0).getDescription(),weatherHistory.getMain().getTemp(),weatherHistory.getMain().getHumidity()
							,weatherHistory.getWind().getSpeed(),id);
					weatherDetails.setCode(weatherHistory.getCod());
				}
				else {
					weatherDetails=new WeatherDetails();
					weatherDetails.setCode(weatherHistory.getCod());
					weatherDetails.setErrorMessage("Oops.. Could not get the weather data");
				}
			}
			else {
			weatherDetails=new WeatherDetails();
				weatherDetails.setCode(code);
				weatherDetails.setErrorMessage("Oops.. Could not get the weather data");
			}
			return weatherDetails;
		}
	
}
