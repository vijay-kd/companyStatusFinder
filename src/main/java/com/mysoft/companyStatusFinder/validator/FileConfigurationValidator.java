package com.mysoft.companyStatusFinder.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mysoft.companyStatusFinder.dto.FileConfiguration;

@Component
public class FileConfigurationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FileConfiguration.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sheetName","FileConfiguration.sheetName.empty", "Sheet Name can't be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startRowNumber","FileConfiguration.startRowNumber.empty", "Start Row No can't be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cinColumnNo","FileConfiguration.cinColumnNo.empty", "CIN Column No can't be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statusColumnNo","FileConfiguration.statusColumnNo.empty", "Status Column No can't be blank");
		
	}
}
