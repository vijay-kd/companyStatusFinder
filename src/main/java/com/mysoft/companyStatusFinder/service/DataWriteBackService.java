package com.mysoft.companyStatusFinder.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysoft.companyStatusFinder.dto.CompanyDetails;
import com.mysoft.companyStatusFinder.dto.FileConfiguration;
import com.mysoft.companyStatusFinder.repository.MCARepository;
import com.mysoft.companyStatusFinder.utils.DummyDataService;
import com.mysoft.companyStatusFinder.utils.excelUtils.ExcelReader;
import com.mysoft.companyStatusFinder.utils.excelUtils.ExcelWriter;

@Service
public class DataWriteBackService {
	
	@Autowired
	private MCARepository mcaRepo;

	public byte[] writeStatus(MultipartFile uploadedExcel, FileConfiguration configuration) throws IOException {
		
		ExcelReader reader = new ExcelReader(uploadedExcel);

		List<String> cinList = reader.readColumn(
										configuration.getCinColumnNo(), 
										configuration.getStartRowNumber(),
										configuration.getSheetName());

		ExcelWriter excelWriter = new ExcelWriter(reader.getWorkbook());

		//List<CompanyDetails> companyDataList = mcaRepo.getCompanyDetails(cinList);
		
		List<CompanyDetails> companyDataList = DummyDataService.getDummyData();

		int rowNo = configuration.getStartRowNumber();

		for (CompanyDetails companyDetails : companyDataList) {
			excelWriter.writeValue(rowNo++, 
					configuration.getStatusColumnNo(), 
					configuration.getSheetName(), 
					companyDetails.getStatus());
		}
		
		return excelWriter.getByteArr();
	}
}
