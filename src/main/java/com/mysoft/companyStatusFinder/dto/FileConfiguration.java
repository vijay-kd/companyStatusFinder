package com.mysoft.companyStatusFinder.dto;

import lombok.Data;

@Data
public class FileConfiguration {

	private String sheetName;
	private int startRowNumber;
	private int cinColumnNo;
	private int statusColumnNo;
}
