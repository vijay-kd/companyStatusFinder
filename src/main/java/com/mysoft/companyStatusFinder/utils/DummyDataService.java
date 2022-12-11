package com.mysoft.companyStatusFinder.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysoft.companyStatusFinder.dto.CompanyDetails;

public class DummyDataService {

	public static List<CompanyDetails> getDummyData() throws IOException {
		
		String data = JsonData.getData();
		
		JSONArray jsonArr = new JSONArray(data);
		
		List<CompanyDetails> companyDataList = new ArrayList<CompanyDetails>();
		
		for(int i = 0; i < jsonArr.length(); i++) {
			
			JSONObject companyData = ((JSONObject) jsonArr.get(i)).getJSONObject("data");
			
			JSONObject companyMasterData = companyData.getJSONObject("company_master_data");
			
			CompanyDetails companyDetails = CompanyDetails.builder()
			.cin((String) companyMasterData.get("cin "))
			.status((String) companyMasterData.get("company_status(for_efiling)"))
			.companyName((String) companyMasterData.get("company_name"))
			.registrationNumber((String) companyMasterData.get("registration_number"))
			.build();
			
			companyDataList.add(companyDetails);
		}
		
		return companyDataList;
	}
}
