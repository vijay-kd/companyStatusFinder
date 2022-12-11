package com.mysoft.companyStatusFinder.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class JsonData {

	public static String getData() throws IOException {
		
		File file = new File("C:/Users/vijay/OneDrive/Desktop/json data.txt");
		
		byte[] data = FileUtils.readFileToByteArray(file);
		
		return new String(data);
	}
}
