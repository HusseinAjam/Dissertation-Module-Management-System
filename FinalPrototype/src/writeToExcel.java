import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;

public class writeToExcel {
	
	public writeToExcel(String[][] schema, int dayNo ,  ObservableList<String> YesRoom, ObservableList NoRoom,   int start , int end, ObservableList times, String date) throws FileNotFoundException, IOException
	{
			final InputStream is ;
			File f = new File(date+".xlsx");
			if(f.exists() && !f.isDirectory()) { 
				 is = new FileInputStream(date+".xlsx");
			}
			else
			{
				is = new FileInputStream("JavaBooks.xlsx");
			}
		
		    Object[][] bookData = new Object[1000][100];
	        XSSFWorkbook workbook = new XSSFWorkbook(is);
	        XSSFSheet sheet = workbook.createSheet("Day Number "+ dayNo);	        
	       
	        String []room = new String [YesRoom.size()-NoRoom.size()];
	        int arrauCounter = 0;
	        for(int i = 0 ; i < YesRoom.size(); i++)
	        {
	        	if(!NoRoom.contains(YesRoom.get(i)))
	        	{
	        		room[arrauCounter] = YesRoom.get(i);
	        		arrauCounter++;
	        	}
	        }
	        
	        int rowCounter = 0;
			for(int i = 0 ; i <  schema.length; i++)
		   	{
				if(i == 0)
				{
 			        bookData[rowCounter][0] = "Room Name";
			        bookData[rowCounter][1] = "Time";
			        bookData[rowCounter][2] = "Student Id";
			        bookData[rowCounter][3] = "Super Visor";
			        bookData[rowCounter][4] = "Double Marker";
			        rowCounter++;
				}
		   	   for (int j = 0 ; j < end - start ; j++)
		   	   {
					String string = schema[i][j];
					String[] part = string.split("-");
					String temp0 =  part[0];
					String temp1 =  part[1];
					
					//Add Room 
					bookData[rowCounter][0] = room[i];
					// Add time
					bookData[rowCounter][1] = times.get(start+j);
					
					//Add Student id
					if(temp1.equals("1") && !temp0.equals("0"))
						bookData[rowCounter][2] = temp0 + "";
					
					//Add SV and DM
					else if(!temp1.equals("0") && !temp0.equals("0"))
					{
					bookData[rowCounter][3] = temp0 + "";
					bookData[rowCounter][4] = temp1 + "";
					rowCounter++;
					}
					
 		   		//bookData[rowCounter][j] = schema[i][j] + "";
		   	   }
		   	rowCounter++;
 		   	}
	    
	        int rowCount = 0;
	         
	        for (Object[] aBook : bookData) {
	            Row row = sheet.createRow(++rowCount);
	             
	            int columnCount = 0;
	             
	            for (Object field : aBook) {
	                Cell cell = row.createCell(++columnCount);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	             
	        }
	         
	         
	        try (FileOutputStream outputStream = new FileOutputStream(date+".xlsx")) {
	            workbook.write(outputStream);
	        }
	    }
}

