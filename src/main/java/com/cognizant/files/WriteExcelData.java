package com.cognizant.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelData {
	public static void writeData(List<String> outputData,int noOfTestCase) {
		try {
			File file=new File("output/OutputData.xlsx");
			if(noOfTestCase==1 && file.exists()) {
				file.delete();
			}
			boolean exists = file.exists();
			XSSFWorkbook book=new XSSFWorkbook();
			XSSFSheet sheet = book.createSheet("OutputData");
			if(exists) {
				FileInputStream fis=new FileInputStream(file);
				XSSFWorkbook readbook=new XSSFWorkbook(fis);
				XSSFSheet xssfSheet = readbook.getSheetAt(0);
				int rows=xssfSheet.getPhysicalNumberOfRows();
				int cells=xssfSheet.getRow(0).getPhysicalNumberOfCells();
				Object[][] arr=new Object[rows][cells];
				for(int i=0;i<rows;i++) {
					XSSFRow row = xssfSheet.getRow(i);
					for(int j=0;j<cells;j++) {
						arr[i][j]=row.getCell(j).toString();
					}
				}
				readbook.close();
				for(int i=0;i<arr.length;i++) {
					XSSFRow row = sheet.createRow(i);
					for(int j=0;j<arr[i].length;j++) {
						row.createCell(j).setCellValue(arr[i][j].toString());
					}
				}
			}
			else {
				XSSFRow row = sheet.createRow(0);
				row.createCell(0).setCellValue("Course 1");
				row.createCell(1).setCellValue("Rating");
				row.createCell(2).setCellValue("Duration");
				row.createCell(3).setCellValue("Course 2");
				row.createCell(4).setCellValue("Rating");
				row.createCell(5).setCellValue("Duration");
				row.createCell(6).setCellValue("Languages");
				row.createCell(7).setCellValue("Levels");
				row.createCell(8).setCellValue("Error Message");
			}
			int rows = sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.createRow(rows);
			for(int i=0;i<outputData.size();i++) {
				row.createCell(i).setCellValue(outputData.get(i));
			}
			FileOutputStream fos=new FileOutputStream(file);
			book.write(fos);
			book.close();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	public static void main(String[] args) {
		WriteExcelData.writeData(null, 1);
	}
}