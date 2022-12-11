package com.mysoft.companyStatusFinder.utils;

import org.json.JSONObject;

import com.mysoft.companyStatusFinder.dto.CompanyDetails;

public class KeyMapper {

	public static CompanyDetails mapCompanyDetails(String jsonString) {
		
		JSONObject json = new JSONObject(jsonString);
		
		JSONObject companyData = json.getJSONObject("data");
		
		JSONObject companyMasterData = companyData.getJSONObject("company_master_data");
		
		return CompanyDetails.builder()
				.cin((String) companyMasterData.get("cin "))
				.status((String) companyMasterData.get("company_status(for_efiling)"))
				.companyName((String) companyMasterData.get("company_name"))
				.registrationNumber((String) companyMasterData.get("registration_number"))
				.build();
	}
}
