package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

	private String timestamp;
	private double latitude;
	private double longititude;
	private String description;
	private int temperature;
	private int humidity;
	private double wind_speed;
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
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
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
	public WeatherData(String timestamp, double latitude, double longititude, String description, int temperature,
			int humidity, double wind_speed) {
		super();
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longititude = longititude;
		this.description = description;
		this.temperature = temperature;
		this.humidity = humidity;
		this.wind_speed = wind_speed;
	}
	public WeatherData() {
		
	}
}
