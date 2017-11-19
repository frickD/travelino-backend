package com.travelino.api;

import java.util.HashMap;

import com.travelino.Hotels;

public class VisionAPIResponse {
		private HashMap<String, Double> geolocation;
		private String locationName;
		private Hotels[] hotels;
		
		public VisionAPIResponse(HashMap<String, Double> geolocation, String locationName, Hotels[] hotels) {
			this.geolocation = geolocation;
			this.locationName = locationName;
			this.hotels = hotels;
		}
}
