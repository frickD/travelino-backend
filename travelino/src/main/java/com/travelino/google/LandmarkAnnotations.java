package com.travelino.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LandmarkAnnotations {
	
	
	@JsonProperty("mid")
	private String mid;
	@JsonProperty("description")
	private String description;
	@JsonProperty("score")
	private long score;
	@JsonProperty("locations")
	private Locations[] locations;
	
	public String getMid() {
		return mid;
	}
	public Locations[] getLocations() {
		return locations;
	}
	public void setLocations(Locations[] locations) {
		this.locations = locations;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public BoundingPoly getBoundingPoly() {
		return boundingPoly;
	}
	public void setBoundingPoly(BoundingPoly boundingPoly) {
		this.boundingPoly = boundingPoly;
	}
	@JsonProperty("boundingPoly")
	private BoundingPoly boundingPoly;
}
