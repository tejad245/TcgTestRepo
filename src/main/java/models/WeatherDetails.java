package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {
	
	private String timestamp;
	private double latitude;
	private double longititude;
	private String description;
	private double temperature;
	private int humidity;
	private double wind_speed;
	private int id;
	private int code;
	private String errorMessage;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongititude() {
		return longititude;
	}
	public void setLongititude(double longititude) {
		this.longititude = longititude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public double getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public WeatherDetails(String timestamp, double latitude, double longititude, String description, double temperature,
			int humidity, double wind_speed, int id, int code, String errorMessage) {
		super();
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longititude = longititude;
		this.description = description;
		this.temperature = temperature;
		this.humidity = humidity;
		this.wind_speed = wind_speed;
		this.id = id;
		this.code = code;
		this.errorMessage = errorMessage;
	}
	public WeatherDetails() {
		
	}
}
