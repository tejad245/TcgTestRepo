package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {
	
	    private double lon;
	    private double lat;
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public Coord(double lon, double lat) {
			super();
			this.lon = lon;
			this.lat = lat;
		}
	public Coord() {
		
	}
}
