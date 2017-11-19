package com.travelino.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.travelino.Check24Connector;
import com.travelino.GoogleMapsConnector;
import com.travelino.GoogleVisionConnector;


@RestController
public class VisionControllerAPI {
	
	@RequestMapping(value= "/googlevision", method = RequestMethod.POST, produces="application/json")
	public String getResponse(@RequestBody Image64 base64) throws ParseException {
		GoogleVisionConnector vision = new GoogleVisionConnector(base64.getBase64());
		HashMap<String, Double> geolocation = new HashMap<>();
		geolocation.put("latitude", vision.getLocations().getLatitude());
		geolocation.put("longitude", vision.getLocations().getLongitude());
		GoogleMapsConnector maps = new GoogleMapsConnector(vision.getLocations().getLatitude(), vision.getLocations().getLongitude());
		Check24Connector check24 = new Check24Connector(vision.getLocations().getLatitude(), vision.getLocations().getLongitude());
		
		VisionAPIResponse response = new VisionAPIResponse(geolocation, maps.getLocation(), check24.getHotels());
		return new Gson().toJson(response);
	}
}
