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

public class GoogleVisionConnector {
		private String base64;
		private String response;
		private Response[] landmarks;
		
		public GoogleVisionConnector(String base64) {
			this.base64 = base64;
			try {
				conntectToGoogle();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private void conntectToGoogle() throws IOException, JSONException{
			URL url = null;
			try {
				url = new URL("https://vision.googleapis.com/v1/images:annotate?key=AIzaSyCFxzBIwYa9uxqNdskWP7-9Ye_QFLIPoVM");
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

		    connection.setRequestMethod("POST");
		    connection.setDoOutput(true);
		    connection.setRequestProperty("Content-Type", "application/json");

		    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());  
		    out.write("{\"requests\":[{\"image\":{\"content\":\""+ this.base64 + "\"},\"features\":[{\"maxResults\":10,\"type\":\"LANDMARK_DETECTION\"}]}]}");
		    out.flush();
		    out.close();

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
			if(jsonObject.optJSONArray("responses") != null){
				this.landmarks = m.readValue(jsonObject.getJSONArray("responses").toString(), Response[].class);
				m = new ObjectMapper();
				m.configure(SerializationFeature.INDENT_OUTPUT, true);
				m.setSerializationInclusion(Include.NON_NULL);
				m.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
				m.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
				m.setSerializationInclusion(Include.NON_EMPTY);
			}
//			JSONObject land = (JSONObject) jsonObject.getJSONArray("responses").get(0);
//			JSONObject locations = (JSONObject) land.getJSONArray("landmarkAnnotations").get(0);
//			JSONObject latLng = (JSONObject) locations.getJSONArray("locations").get(0);
//			Long lng = latLng.get("longitude").toString();
		}
		
		public LatLng getLocations(){
			return landmarks[0].getLandmarkAnnotations()[0].getLocations()[0].getLatLng()[0];
		}
		
}
