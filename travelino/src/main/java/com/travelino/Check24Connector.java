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

public class Check24Connector {
		private Hotels[] hotels;
		
		public Check24Connector(Double lat, Double lng) {
			try {
				conntectToCheck24(lat, lng);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private void conntectToCheck24(Double lat, Double lng) throws IOException, JSONException{
			boolean processing = true;
		JSONObject jsonObject = null;
			String line = null;
		    String jsonResponse = "";
		    BufferedReader br = null;
		    InputStream is;
		    int res;
			while(processing){
				URL url = null;
				try {
					url = new URL("https://api.hotel.check24.de/hackatum/hotels/searches.json?latitude="+ lat +"&longitude="+lng+"&radius=10&arrival_date=2017-11-20&departure_date=2017-11-24&room_configuration=%5BA%5D");
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
			     res = connection.getResponseCode();

			    System.out.println(res);

			    is = connection.getInputStream();
			    br = new BufferedReader(new InputStreamReader(is));
	
			     line = null;
			     jsonResponse = "";
			    while((line = br.readLine() ) != null) {
			    	jsonResponse = jsonResponse + line;
			        System.out.println(line);
			    }
			  
			    connection.disconnect();
			    jsonObject = new JSONObject(jsonResponse);  
			    String status = jsonObject.getJSONObject("search").getJSONObject("status_detailed").getString("state");
			    
			    if(status.matches("finished")){
			    	processing = false;
			    }
			}
			
			
			
			String searchID = jsonObject.getJSONObject("search").getInt("id") + "";
			
			URL urlSearch = null;
			try {
				urlSearch = new URL("https://api.hotel.check24.de/hackatum/hotels/searches/"+ searchID + "/results.json");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) urlSearch.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    connection.setConnectTimeout(10000);//5 secs
		    connection.setReadTimeout(10000);//5 secs

		    connection.setRequestMethod("GET");
		    connection.setDoOutput(true);
		    res = connection.getResponseCode();

		    System.out.println(res);


		    is = connection.getInputStream();
		    br = new BufferedReader(new InputStreamReader(is));
		    line = null;
		    jsonResponse = "";
		    while((line = br.readLine() ) != null) {
		    	jsonResponse = jsonResponse + line;
		        System.out.println(line);
		    }
		    
		    JSONArray results = new JSONObject(jsonResponse).getJSONObject("search").getJSONArray("results");
		    connection.disconnect();
		    
		    
		    ObjectMapper m = new ObjectMapper();
			m.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			if(results != null){
				this.hotels = m.readValue(results.toString(), Hotels[].class);
				m = new ObjectMapper();
				m.configure(SerializationFeature.INDENT_OUTPUT, true);
				m.setSerializationInclusion(Include.NON_NULL);
				m.setSerializationInclusion(Include.NON_EMPTY);
			}
			
			
		}
		
		public Hotels[] getHotels(){
			return this.hotels;
		}
		
}
