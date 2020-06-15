package data_store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;*/

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataFile 
{
	public  List<String> userName;
	public  List<String> password;
	
  @Test
  public void FileRead() 
  {
	  	File dataFile = new File("D:\\Course Project Data\\HMS Login Details.xlsx");
		try 
		{
			FileInputStream fis = new FileInputStream(dataFile);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
		/*	DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sh.iterator();
			while (rowIterator.hasNext()) 
				{
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) 
						{
						Cell cell = cellIterator.next();
						// String cellValue=dataFormatter.formatCellValue(cell).toString();;
						dataFormatter.formatCellValue(cell).toString();
						// System.out.print(cellValue+ "\t");
						}
					// System.out.println(" ");
				}*/
        
        userName=new ArrayList<String>();
        userName.add(sh.getRow(1).getCell(0).getStringCellValue());
        userName.add(sh.getRow(2).getCell(0).getStringCellValue());
        userName.add(sh.getRow(3).getCell(0).getStringCellValue());
        
        password=new ArrayList<String>();
        password.add(sh.getRow(1).getCell(1).getStringCellValue());
        password.add(sh.getRow(2).getCell(1).getStringCellValue());
        password.add(sh.getRow(3).getCell(1).toString());
        
		wb.close();
		fis.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found exception");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("IO exception occurred");
			e.printStackTrace();
		}
  	}
}