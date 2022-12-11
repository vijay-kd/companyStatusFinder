package com.mysoft.companyStatusFinder.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mysoft.companyStatusFinder.dto.CompanyDetails;
import com.mysoft.companyStatusFinder.utils.KeyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class MCARepository {
	
	@Value("${mca.url}")
	private String mcaUrl;
	
	@Value("${mca.api.key}")
	private String mcaApiKey;

	@Value("${mca.url.companyDetails}")
	private String mcaCompanyDetailURL;
	
	@Value("${mca.api.secret}")
	private String mcaApiSecret;
	
	private final RestTemplate restTemplate;

	public List<CompanyDetails> getCompanyDetails(List<String> cinList) {
		
		String authenticationToken = this.authenticate();
		
		List<CompanyDetails> companyDataList = new ArrayList<CompanyDetails>();
		
		for(String cin : cinList) {
			
			if(null == cin || cin.length() <= 0) continue;
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("x-api-key", mcaApiKey);
			headers.set("Authorization", authenticationToken);
			headers.set("x-api-version", "1.0");
			
			log.info("Sending request for " + cin + ".....");
			
			Map<String, String> uriParam = new HashMap<>();
			uriParam.put("cin", cin);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(mcaCompanyDetailURL)
	                .queryParam("consent","Y")
	                        .queryParam("reason","For Testing").build();
			
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					builder.toUriString(), HttpMethod.GET, requestEntity, String.class,uriParam);
			
			log.info("Request completed");
			
			String responseBody = response.getBody();
			
			companyDataList.add(KeyMapper.mapCompanyDetails(responseBody));
		}
		
		return companyDataList;
	}
	
	public String authenticate() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", mcaApiKey);
		headers.set("x-api-secret", mcaApiSecret);
		headers.set("x-api-version", "1.0");
		
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				mcaUrl + "/authenticate", HttpMethod.POST, requestEntity, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		
		return json.getString("access_token");
	}
}
