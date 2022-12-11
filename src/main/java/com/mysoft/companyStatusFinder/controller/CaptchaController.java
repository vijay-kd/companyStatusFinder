package com.mysoft.companyStatusFinder.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysoft.companyStatusFinder.utils.AutomateBrowser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/start")
@RequiredArgsConstructor
public class CaptchaController {
	
	private final AutomateBrowser automateUtil;

	@GetMapping
	public ResponseEntity<?> get() throws InterruptedException, IOException {
		
		automateUtil.start();
		
		return ResponseEntity.ok("");
	}
}
