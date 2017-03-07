package test.com.dubizzle.framework.commonutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static XSSFSheet excelWSheet;
	public static XSSFWorkbook excelWBook;
	public static XSSFCell cell;
	public static XSSFRow row;
	static String Path_TestData;

	// 

	/**
	 * This method is to set the File path and to open the Excel file
	 * 
	 * @param path The Excel workbook location
	 * @param sheetName The Excel worksheet name
	 * @throws Exception
	 */
	public static void setExcelFile(String path, String sheetName) throws Exception {
		try {
			/*Assigning excel path*/
			Path_TestData = path;

			/*Open the Excel file*/
			FileInputStream ExcelFile = new FileInputStream(path);

			/*Access the required test data sheet*/
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method is to write in the Excel cell, Row num and Col num are the parameters
	 * 
	 * @param rowNum The row number in excel worksheet start with 0 index
	 * @param colNum The column number in excel worksheet start with 0 index
	 * @param textValue The value that need to write on excel
	 * @throws Exception
	 */
	public static void setCellData(int rowNum, int colNum, String textValue) throws Exception {
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(textValue);
			} else {
				cell.setCellValue(textValue);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Path_TestData);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Read excel data from specified cell as per param
	 * 
	 * @param rowNum The row number in excel worksheet start with 0 index
	 * @param colNum The column number in excel worksheet start with 0 index
	 * @return String The text value from Excel
	 * @throws Exception return "No data in cell"
	 */
	public static String getCellData(int rowNum, int colNum) throws Exception {
		try {
			String CellData;
			// setExcelFile(Constant.Path_TestData+""+Constant.File_TestData,Constant.SheetnameLogin);
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			cell.setCellType(cell.CELL_TYPE_STRING);
			if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				int i = (int) cell.getNumericCellValue();
				CellData = String.valueOf(i);
			} else {
				CellData = cell.getStringCellValue();
			}
			return CellData;
		} catch (Exception e) {
			return "No data in cell";

		}
	}

	/**
	 * Read excel data from specified cell as per param
	 * 
	 * @param rowNum The row number in excel worksheet start with 0 index
	 * @param colNum The column number in excel worksheet start with 0 index
	 * @return String
	 * @throws Exception return "No data in cell"
	 */
	public static Date getDateCellValue(int rowNum, int colNum) throws Exception {
		cell = excelWSheet.getRow(rowNum).getCell(colNum);
		return cell.getDateCellValue();
	}

	/**
	 * @return The number of rows in Work sheet
	 */
	public static int getNumberOfRows() {
		return excelWSheet.getPhysicalNumberOfRows();
	}
}
