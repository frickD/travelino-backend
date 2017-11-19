package com.travelino.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	@JsonProperty("landmarkAnnotations")
	LandmarkAnnotations[] landmarkAnnotations;
	@JsonProperty("formatted_address")
	String formatted_address;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public LandmarkAnnotations[] getLandmarkAnnotations() {
		return landmarkAnnotations;
	}

	public void setLandmarkAnnotations(LandmarkAnnotations[] landmarkAnnotations) {
		this.landmarkAnnotations = landmarkAnnotations;
	} 
	
}
