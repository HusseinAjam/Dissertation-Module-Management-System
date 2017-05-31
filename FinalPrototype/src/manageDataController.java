import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.lang.ArrayUtils;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class manageDataController {

    @FXML
    private Button deleteSelectedButton;

    @FXML
    private Button checkAndUpdateButton;

    @FXML
    private ComboBox degreeCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private Button loadButton;
    
	@FXML
	private SpreadsheetView spreadsheet;
    
    ObservableList<String> YearOL = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025",
			"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035");
    
	ObservableList<String> degreeOL = FXCollections.observableArrayList("Undergraduate", "Postgraduate");
	private GridBase grid;
	private ObservableList<ObservableList<SpreadsheetCell>> rows;
	private ObservableList<SpreadsheetCell> list;
	private Staff[] staff;
	public void initialize() throws IOException, ClassNotFoundException
	{
		degreeCombo.setItems(degreeOL);
		degreeCombo.setValue("Undergraduate");
		yearCombo.setItems(YearOL);
		yearCombo.setValue("2017");
		
		////////////////////////////////////
		
   	    FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);

         ToolsForHelp tool = new ToolsForHelp();
          int staffArraySize = tool.FilesManagerGetVlaue("StaffCounter");
         
        // Create a  array
        staff = new Staff[staffArraySize];
        // Read the serialized objects from the file.
        for (int i = 0; i < staffArraySize; i++)
        {
        	staff[i] = (Staff) readingObjects.readObject();
        }
        readingObjects.close();
        ////////////////////////////////
	
		int columnCount = 10;
		int rowCount = 26;
		
		grid = new GridBase(rowCount, columnCount);
		rows = FXCollections.observableArrayList();
		for (int row = 0; row < grid.getRowCount(); ++row)
		{
			list = FXCollections.observableArrayList();
			for (int column = 0; column < grid.getColumnCount(); ++column) 
				{
				list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1," "));
				}
			rows.add(list);
		}
		grid.setRows(rows);
		spreadsheet.setGrid(grid);
 	}

    @FXML
    void deleteSelectedAction(ActionEvent event) {
    	 spreadsheet.deleteSelectedCells();
    }

    private Students[] studentArray;
    private boolean checker = false;
    @FXML
    void importAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	  String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
    	  FileInputStream readStream1 = new FileInputStream(filename);
          ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);
  	      ToolsForHelp tool = new ToolsForHelp();  	     
  	      int studentNo = tool.FilesManagerGetVlaue(filename);
   	      // Create a  array
  	       studentArray = new Students[studentNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  studentArray[i] = (Students) readingObjects1.readObject();
	      }
	      readingObjects1.close();
	      
		  ArrayList<List<String>> main = new ArrayList<>();
	      ArrayList<String> columns = new ArrayList<String>();

	      for (int i = 0; i < studentNo; i++)
	      {
 	    		columns = new ArrayList<String>();
 	    		if(!studentArray[i].getFirstName().equals(null))
 	    		columns.add(studentArray[i].getFirstName());
 	    		else
 	 	    	columns.add(" ");
 	    		
 	    		if(!studentArray[i].getLastName().equals(null))
 	    		columns.add(studentArray[i].getLastName());
 	    		else
 	 	 	    	columns.add(" ");
 	    		if(!studentArray[i].getId().equals(null))
 	    		columns.add(studentArray[i].getId());
 	    		else
 	 	 	    	columns.add(" ");
 	    		if(!studentArray[i].getTopic().equals(null))
 	    		columns.add(studentArray[i].getTopic());
 	    		else
 	 	 	    	columns.add(" ");
 	  
 	    		try {
 	 	    	  columns.add(studentArray[i].getSuperVisor()); 
 	    		} catch(NullPointerException e) 
 	    		{
 	 	 	 	    	columns.add(" ");
 	    		}  
 	    		
 	    		if(!studentArray[i].getCourse().equals(null))
 	    		columns.add(studentArray[i].getCourse());
 	    		else
 	 	 	    	columns.add(" ");
 	    		if(!studentArray[i].getPathway().equals(null))
 	    		columns.add(studentArray[i].getPathway());
 	    		else
 	 	 	    	columns.add(" ");
 	    		if(!studentArray[i].getAdditionalNotes().equals(null))
 	    		columns.add(studentArray[i].getAdditionalNotes());
 	    		else
 	 	 	    	columns.add(" ");
 	    		main.add(columns);
	      }
	     
		Iterator<List<String>> rowsListIterator = main.iterator();
		Iterator<String> columnsListIterator = null;
	    int rowCount = main.size();
	    int columnCount = 20;

		 grid = new GridBase(rowCount, columnCount);
	     rows = FXCollections.observableArrayList();
	    for (int row = 0; row < grid.getRowCount(); ++row) {
	    	if(rowsListIterator.hasNext())
	    	columnsListIterator = rowsListIterator.next().iterator();
	      
	        list = FXCollections.observableArrayList();
		        for (int column = 0; column < grid.getColumnCount(); ++column) 
	        {
	        	if(columnsListIterator.hasNext())
 	            list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1,columnsListIterator.next()));
	        	else
 	            list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1," "));	
	        }
	        rows.add(list);
		    }
	    grid.setRows(rows);
 	    try {
			spreadsheet.setGrid(grid);
		} catch (Exception e) {
 			e.printStackTrace();
		}
  	   spreadsheet.getGrid().getColumnHeaders().setAll("First Name", "LastName", "Student ID","Topic","Super Visor", "Course","Pathway",  "Addotional Notes");
    }

    @FXML
    void checkAndUpdateAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	
		String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat"; 
    	SpreadsheetCell cell = rows.get(0).get(0);
    	ArrayList<Students> studentsList = new ArrayList<Students>();
    	String id="";
    	String firstName = "";
    	String lastName = "";
    	String topic= "";
    	String SV = "";
    	String course = "";
    	String pathway = "";
    	String notes = "";
    	String year = yearCombo.getValue()+"";
    	String level = degreeCombo.getValue()+"";
    	boolean errorTriger = false;  	
	    for (int row = 0; row < grid.getRowCount(); ++row) 
	    {
	    	Assessments asObj = new Assessments();
	    	Students student;
	    	cell = rows.get(row).get(2);
	    	if(cell.getText().length() > 1)
 	    	if(!cell.getText().equals(" "))
	    	{
	    		cell = rows.get(row).get(0);
	    		firstName = cell.getText();
	    		cell = rows.get(row).get(1);
	    		lastName = cell.getText();
	    		cell = rows.get(row).get(2);
	    		id = cell.getText();
	    		cell = rows.get(row).get(3);
	    		topic = cell.getText();
	    		cell = rows.get(row).get(4);
	    		SV = cell.getText();
	    		cell = rows.get(row).get(5);
	    		course = cell.getText();
	    		cell = rows.get(row).get(6);
	    		pathway = cell.getText();
	    		cell = rows.get(row).get(7);
	    		notes = cell.getText();
	    		if((notes+pathway+course+SV+topic+id+firstName+lastName).length() > 1)
	    		{
	    		student = new Students(id,firstName,lastName, null," "," "," "," ",pathway,course,notes,topic, year, level, asObj);
	    		if(!SV.equals(" "))
	    		{
    	         for (int i = 0; i < staff.length; i++)
    	         {
    	        	 if (staff[i].getId().equals(SV))
    	        	 {
    	        		 student.setSuperVisor(staff[i]);
    	        		 break;
    	        	 }
    	        	 errorTriger = true;
    	         }
	    		}
	    		studentsList.add(student);
	    		}
	    		
	        	 id="";
	        	 firstName = "";
	        	 lastName = "";
	        	 topic= "";
	        	 SV = "";
	        	 course = "";
	        	 pathway = "";
	        	 notes = "";
	        	 year = "";
	        	 level ="";
	    	}
		 }
	    
	    FileInputStream readStream1 = new FileInputStream(filename);
        ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);
  	     
         ToolsForHelp tool = new ToolsForHelp();
  	     int studentNo = tool.FilesManagerGetVlaue(filename);
   	      // Create a  array
  	      Students[] studentArray = new Students[studentNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  studentArray[i] = (Students) readingObjects1.readObject();
	      }
	      readingObjects1.close();
	      
	      Students tempArray[]=studentsList.toArray(new Students[studentsList.size()]);
	       		  
	      //Now Save the updated array back to the file
		     FileOutputStream saveStream = new FileOutputStream(filename);
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < tempArray.length ; i++)
		       {
		    	  savingObjects.writeObject(tempArray[i]);
	 	       }  
		     tool.FilesManagerSetNewValue(filename, studentsList.size());
 	 	     savingObjects.close();  
	 	  JOptionPane.showMessageDialog (null, "Records Updated successfully", "Title", JOptionPane.INFORMATION_MESSAGE);
	 	 // if(errorTriger)
	 	  //JOptionPane.showMessageDialog (null, "Some students do not have Super visor \nOr unknown supervisor", "Title", JOptionPane.WARNING_MESSAGE);

    }
}
