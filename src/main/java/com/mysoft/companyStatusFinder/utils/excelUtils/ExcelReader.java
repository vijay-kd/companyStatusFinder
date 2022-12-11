package com.mysoft.companyStatusFinder.utils.excelUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ExcelReader {
	
	private XSSFWorkbook workbook;

	public ExcelReader(MultipartFile multipartFile) throws IOException {
		
		FileInputStream file = (FileInputStream) multipartFile.getInputStream();

		this.workbook = new XSSFWorkbook(file);
	}
	
	public List<String> readColumn(int columnNo, int sheetNo) {
		
		XSSFSheet sheet = workbook.getSheetAt(sheetNo);
		
		return readColumn(columnNo, 0, sheet);
	}
	
	public List<String> readColumn(int columnNo, String sheetName) {
		
		XSSFSheet sheet = workbook.getSheet(sheetName);

		return readColumn(columnNo, 0, sheet);
	}
	
	public List<String> readColumn(int columnNo, int startRow, String sheetName) {
		
		XSSFSheet sheet = workbook.getSheet(sheetName);

		return readColumn(columnNo, startRow, sheet);
	}
	
	
	public List<String> readColumn(int colNo, int startRowNo, XSSFSheet sheet) {
		
		List<String> columnValues = new ArrayList<String>();
		
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			
			if(row.getRowNum() >= startRowNo) {
				
				Cell cell = row.getCell(colNo);
				
				columnValues.add(getCellValue(cell));
			}
		}
		
		return columnValues;
	}
	
	public String readCellValue(int rowNo, int columnNo, XSSFSheet sheet) {
		
		Row row = sheet.getRow(rowNo);
		
		Cell cell = row.getCell(columnNo);
		
		return getCellValue(cell);
	}
	
	private String getCellValue(Cell cell) {
		
		if(null == cell) {
			log.error("Null Cell captured");
			return null;
		}
		
		switch (cell.getCellType()) {

		case NUMERIC:
			return cell.getNumericCellValue() + "";

		case STRING:
			return cell.getStringCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue() + "";
		default:
			return "";
		}
	}
	
	public XSSFWorkbook getWorkbook() {
		return this.workbook;
	}
	
	public byte[] getByteArr() throws IOException {
		
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			
		    this.workbook.write(bos);
		    
		    byte[] bytes = bos.toByteArray();
		    
		    return bytes;
		} 
	}

}
