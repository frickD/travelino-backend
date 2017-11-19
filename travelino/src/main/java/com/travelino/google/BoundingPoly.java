package com.travelino.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundingPoly {
	@JsonProperty("vertices")
	Vertices[] vertices;

	public Vertices[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertices[] vertices) {
		this.vertices = vertices;
	}
}
