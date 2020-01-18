package genericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheetData 

{
	public static Map<String,Map <String,String>>setMapData(String path,String Sheetname) throws IOException	
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

	
}
	
	
	
	
	

