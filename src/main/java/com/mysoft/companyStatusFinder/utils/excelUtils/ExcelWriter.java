package com.mysoft.companyStatusFinder.utils.excelUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelWriter {
	
	private XSSFWorkbook workbook;
	
	public ExcelWriter(MultipartFile multipartFile) throws IOException {
		
		FileInputStream file = (FileInputStream) multipartFile.getInputStream();

		this.workbook = new XSSFWorkbook(file);
	}
	
	public ExcelWriter() {
		
		this.workbook = new XSSFWorkbook();
	}
	
	public ExcelWriter(XSSFWorkbook workbook) {
		
		this.workbook = workbook;
	}
	
	public void writeValue(int rowNo, int colNo, int sheetNo, String value) {
		XSSFSheet sheet = this.workbook.getSheetAt(sheetNo);
		writeValue(rowNo, colNo, sheet, value);
	}
	
	public void writeValue(int rowNo, int colNo, String sheetName, String value) {
		XSSFSheet sheet = this.workbook.getSheet(sheetName);
		writeValue(rowNo, colNo, sheet, value);
	}
	
	public void writeValue(int rowNo, int colNo, XSSFSheet sheet, String value) {
		
		Row row = sheet.getRow(rowNo);
		
		Cell cell = row.getCell(colNo);
		
		cell.setCellValue(value);
	}
	
	public byte[] getByteArr() throws IOException {
		
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
		    this.workbook.write(bos);
		    
		    byte[] bytes = bos.toByteArray();
		    
		    return bytes;
		} 
	}
	
}
