package genericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtils

{
String filePath="C:\\Users\\samir mallick\\eclipse-workspace\\Seleniumautomation\\testData\\TestData.properties";
String excelPath="/Seleniumautomation/testData/TestData.xlsx";

/* To Read the data from property file */

public Properties getPropertiFileObject()throws Throwable
{
	
FileInputStream fis=new FileInputStream(filePath);
Properties pObj=new Properties();
pObj.load(fis);
return pObj;
}

/* To read the data from excel sheet */

public String getExcelData(String sheetName,int RowNum,int CellNum) throws Throwable, Exception, IOException
{
	FileInputStream fis=new FileInputStream(excelPath);
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(sheetName);
	String data=sh.getRow(RowNum).getCell(CellNum).getStringCellValue();
	wb.close();
	return data;
	
}

/*to write data in Excel sheet */
public void setExcelData(String sheetName,int RowNum,int CellNum,String data) throws Exception, InvalidFormatException, IOException
{
	FileInputStream fis=new FileInputStream(excelPath);
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(sheetName);
	sh.getRow(RowNum).getCell(CellNum).setCellValue(data);
	FileOutputStream fos=new FileOutputStream(excelPath);
	wb.write(fos);
	wb.close();

}








//********************************************************************************************
public static Map <String,Map <String,String>>setMapData(String path,String Sheetname) throws IOException
{
	FileInputStream fis=new FileInputStream(path);
	Workbook workbook=new XSSFWorkbook(fis);
	Sheet sheet=workbook.getSheetAt(0);
	int lastrow=sheet.getLastRowNum();
	Map<String,Map<String,String>>excelFileMap=new HashMap<String,Map<String,String>>();
	Map<String,String>dataMap=new HashMap<String,String>();
	//Looping the entire row
	for(int i=0;i<=lastrow;i++)
	{
		Row row= sheet.getRow(i);
	
   //1st cell as value
	Cell valuecell=row.getCell(1);
	
  // 0th cell as key
	
	Cell keycell=row.getCell(0);
	
	String value=valuecell.getStringCellValue().trim();
	String key=keycell.getStringCellValue().trim();
	
  //putting key & value in dataMap
	dataMap.put(key, value);
	
  // putting dataMap to ExcelFileMap
	excelFileMap.put(Sheetname,dataMap);
	}
 //returning excelfileMap
	return excelFileMap;
	
}


public String getMapData1(String key,String ExcelPath,String sheetName)
{
String value="";
try {
	Map<String,String>m=setMapData(ExcelPath,sheetName).get(sheetName);
	value=m.get(key);
	}
catch(IOException e)
{
	e.getMessage();
}
catch(NullPointerException e1)
{
	e1.printStackTrace();
	System.out.println("Null pointer exception caught");
}
return value;
}










}
