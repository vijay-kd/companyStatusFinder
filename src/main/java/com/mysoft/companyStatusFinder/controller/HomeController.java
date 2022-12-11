package com.mysoft.companyStatusFinder.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mysoft.companyStatusFinder.dto.FileConfiguration;
import com.mysoft.companyStatusFinder.service.DataWriteBackService;
import com.mysoft.companyStatusFinder.utils.ErrorExtractor;
import com.mysoft.companyStatusFinder.validator.FileConfigurationValidator;

@RestController
@RequestMapping("/excel")
public class HomeController {

	@Autowired
	private FileConfigurationValidator configurationValidator;
	
	@Autowired
	private DataWriteBackService dataWriteBackService;
	
	@PostMapping
	public ResponseEntity<?> excelUpload(@RequestPart("dataFile") MultipartFile uploadedExcel,
			@RequestPart("config") FileConfiguration configuration, 
			BindingResult bRes) throws IOException {

		configurationValidator.validate(configuration, bRes);
		
		if(bRes.hasErrors()) {
			return ResponseEntity.badRequest().body(ErrorExtractor.getErrorJSON(bRes));
		}
		
		byte[] byteArr = dataWriteBackService.writeStatus(uploadedExcel, configuration);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + uploadedExcel.getOriginalFilename())
				.contentLength(byteArr.length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(byteArr);
	}
}
