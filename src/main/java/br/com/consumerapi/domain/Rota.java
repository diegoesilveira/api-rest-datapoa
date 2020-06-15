package br.com.consumerapi.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Rota {
	
	
	private String Latitude;
	
	private String Longitude;

	
	public Rota() {
	}

	public Rota(String latitude, String longitude) {
		
		Latitude = latitude;
		Longitude = longitude;
	}


	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	
	

}
