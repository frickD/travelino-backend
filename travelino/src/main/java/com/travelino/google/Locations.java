package com.travelino.google;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations {
	
	@JsonProperty("latLng")
	private LatLng[] latLng;

	public LatLng[] getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng[] latLng) {
		this.latLng = latLng;
	}

	
}
