package com.travelino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.travelino.google.LatLng;
import com.travelino.google.Response;

public class GoogleMapsConnector {
		private Response[] name;
		
		public GoogleMapsConnector(Double lat, Double lng) {
			try {
				conntectToGoogleMaps(lat, lng);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private void conntectToGoogleMaps(Double lat, Double lng) throws IOException, JSONException{
			URL url = null;
			try {
				url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat +"," + lng +"&key=AIzaSyA6WnDk67hslxD0aEMMJ9tjDJgKSpDdIRk");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    connection.setConnectTimeout(10000);//5 secs
		    connection.setReadTimeout(10000);//5 secs

		    connection.setRequestMethod("GET");
		    connection.setDoOutput(true);
		    int res = connection.getResponseCode();

		    System.out.println(res);


		    InputStream is = connection.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    String line = null;
		    String jsonResponse = "";
		    while((line = br.readLine() ) != null) {
		    	jsonResponse = jsonResponse + line;
		        System.out.println(line);
		    }
		  
		    connection.disconnect();
		    
		    
		    JSONObject jsonObject = new JSONObject(jsonResponse);  
			
			ObjectMapper m = new ObjectMapper();
			m.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			if(jsonObject.optJSONArray("results") != null){
				this.name = m.readValue(jsonObject.getJSONArray("results").toString(), Response[].class);
				m = new ObjectMapper();
				m.configure(SerializationFeature.INDENT_OUTPUT, true);
				m.setSerializationInclusion(Include.NON_NULL);
				m.setSerializationInclusion(Include.NON_EMPTY);
			}
		}
		
		public String getLocation(){
			return this.name[0].getFormatted_address();
		}
		
}
