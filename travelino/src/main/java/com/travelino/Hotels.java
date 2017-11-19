package com.travelino;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotels {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("city")
	private String city;
	@JsonProperty("street")
	private String street;
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;
	@JsonProperty("star_rating")
	private int star_rating;
	@JsonProperty("rating_average")
	private double rating_average;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("price")
	private String price;
	@JsonProperty("image_url")
	private String image_url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getStar_rating() {
		return star_rating;
	}
	public void setStar_rating(int star_rating) {
		this.star_rating = star_rating;
	}
	public double getRating_average() {
		return rating_average;
	}
	public void setRating_average(double rating_average) {
		this.rating_average = rating_average;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) throws UnsupportedEncodingException {
		this.image_url = URLDecoder.decode(image_url, "UTF-8");
	}

}
