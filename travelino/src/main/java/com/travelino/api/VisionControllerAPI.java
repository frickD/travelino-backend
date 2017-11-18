package com.travelino.api;

import java.text.ParseException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VisionControllerAPI {
	
	@RequestMapping("/googlevision")
	public Image64 getResponse(Image64 base64) throws ParseException {
		
		return base64;
	}
}
