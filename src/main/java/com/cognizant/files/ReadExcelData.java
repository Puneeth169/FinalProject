package com.cognizant.files;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static Object[][] readData(String path)throws Exception{
		File read=new File(path);
		XSSFWorkbook book=new XSSFWorkbook(read);
		XSSFSheet sheet=book.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cells = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data= new Object[rows-1][cells-1];
		for(int i=1;i<rows;i++) {
			XSSFRow row=sheet.getRow(i);
			for(int j=1;j<cells;j++) {
				data[i-1][j-1]=row.getCell(j).toString();
			}
		}
		book.close();
		return data;
	}
}
