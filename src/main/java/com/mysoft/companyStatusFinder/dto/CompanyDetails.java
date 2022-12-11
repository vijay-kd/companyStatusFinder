package com.mysoft.companyStatusFinder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDetails {

	private String cin;
	private String companyName;
	private String status;
	private String registrationNumber;
}
