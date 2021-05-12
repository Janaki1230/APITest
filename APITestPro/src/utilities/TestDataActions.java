package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class TestDataActions {
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFCell cell;
	public static int colNum;
	static ArrayList<String> al=new ArrayList<String>();

	public static ArrayList<String> dataToMap(String path,String sheetName,String name) throws IOException{
		String pathVal=System.getProperty("user.dir")+"\\"+path;
		FileInputStream excelPath=new FileInputStream(pathVal);
		wb=new XSSFWorkbook(excelPath);
	sh=wb.getSheet(sheetName);
	int lastcolval=sh.getRow(0).getLastCellNum();
	for(int i=0;i<lastcolval;i++)
	{
		if(sh.getRow(0).getCell(i).getStringCellValue()==name){
			colNum=i;
			break;
		}
	}	
	int rowCount=sh.getLastRowNum();
	System.out.println("names are :");
	for(int j=1;j<rowCount;j++)
	{		
		String personName=sh.getRow(j).getCell(colNum).getStringCellValue();
		System.out.println(personName);
		al.add(personName);
		
		
	}
	return al;
	
	
	}

}
