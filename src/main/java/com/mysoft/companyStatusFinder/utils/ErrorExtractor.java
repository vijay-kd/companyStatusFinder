/**
 * 
 */
package com.mysoft.companyStatusFinder.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * @author v.kumar
 *
 */
public class ErrorExtractor {
	public static Map<String, String> getErrors(BindingResult bRes) {

		Map<String, String> errorMap = new HashMap<String, String>();

		for (ObjectError error : bRes.getAllErrors()) {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMap.put(fieldName, message);
		}

		return errorMap;
	}

	public static JSONObject getErrorJSON(BindingResult bRes) {
		
		JSONObject resposneJson = new JSONObject();
		JSONObject errorJson = new JSONObject();
		Map<String, String> errors = getErrors(bRes);

		for (Entry<String, String> entry : errors.entrySet()) {
			errorJson.put(entry.getKey(), entry.getValue());
		}

		resposneJson.put("errors", errorJson);
		return resposneJson;
	}
}
