package com.mysoft.companyStatusFinder.configuration;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(MissingServletRequestPartException.class)
	private ResponseEntity<?> missingServletRequestPartException(MissingServletRequestPartException ex) {
		
		JSONObject errorJson = new JSONObject();
		
		errorJson.put("error", ex.getMessage());
		
		return ResponseEntity.badRequest().body(errorJson.toString());
	}
}
