import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	   private int rowNumber = 0;
	    public ArrayList<List<String>> reading(String filePathAndName) throws IOException
	   {
	    String [] temp = filePathAndName.split("\\.");
	   	 ArrayList<List<String>> rowws = new ArrayList<>();

     if(temp[1].equals("xlsx"))
     {
    	 XSSFRow XRow;
	      FileInputStream fis = new FileInputStream(
	      new File(filePathAndName));
	      XSSFWorkbook xworkbook = new XSSFWorkbook(fis);
	      XSSFSheet spreadsheet = xworkbook.getSheetAt(0);
	      Iterator < Row > rowIterator = spreadsheet.iterator();	 	 
	 		 	 
	      while (rowIterator.hasNext()) 
	      { 
	    	 ArrayList<String> columns = new ArrayList<String>();
	    	 XRow = (XSSFRow) rowIterator.next();
	         Iterator < Cell > cellIterator = XRow.cellIterator();
	         DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	          
	         while ( cellIterator.hasNext()) 
	         {
	           Cell cell = cellIterator.next();
	           columns.add( formatter.formatCellValue(cell));
	         }  
	         if (!columns.isEmpty())
	         {
	         rowws.add(columns);
	         rowNumber++;
	         }
 	      }
	      fis.close();
	   }
     else if (temp[1].equals("xls"))
	   {
    	  HSSFRow HRow;
	      FileInputStream fis = new FileInputStream(
	      new File(filePathAndName));
	      HSSFWorkbook hworkbook = new HSSFWorkbook(fis);
	      HSSFSheet spreadsheet = hworkbook.getSheetAt(0);
	      Iterator < Row > rowIterator = spreadsheet.iterator();	 	 
	 		 	 
	      while (rowIterator.hasNext()) 
	      { 
	    	 ArrayList<String> columns = new ArrayList<String>();
	    	 HRow = (HSSFRow) rowIterator.next();
	         Iterator < Cell > cellIterator = HRow.cellIterator();
	         DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	          
	         while ( cellIterator.hasNext()) 
	         {
	           Cell cell = cellIterator.next();
	           columns.add( formatter.formatCellValue(cell));
	         }  
	         if (!columns.isEmpty())
	         {
	         rowws.add(columns);
	         rowNumber++;
	         }
	      }
	      fis.close(); 
	   }
     return rowws;
	  } 
	    public int getRowNumber ()
	    {
	    	return rowNumber;
	    }
	}