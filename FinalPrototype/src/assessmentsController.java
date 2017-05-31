import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class assessmentsController {

    @FXML
    private Button deleteSelectedButton;

    @FXML
    private Button saveOrUpdateButton;

    @FXML
    private ComboBox degreeCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private Button loadButton;

    @FXML
    private ComboBox proposalCombo;

    @FXML
    private ComboBox interimCombo;

    @FXML
    private ComboBox finalCombo;

    @FXML
    private Button calculateGrades;
    
    @FXML
    private Button exportButton;
    
    @FXML
    private Button importFromExcelButton;

    @FXML
	private SpreadsheetView spreadsheet;
    
    ObservableList<String> PersentageOL = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
																    		"19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34",
																    		"35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50",
																    		"51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66",
																    		"67","68","69","70","71","72","73","74","75","76","77","78","79","80","81",
																    		"82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100");

    
    ObservableList<String> YearOL = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025",
			"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035");
    
	ObservableList<String> degreeOL = FXCollections.observableArrayList("Undergraduate", "Postgraduate");
	private GridBase grid;
	private ObservableList<ObservableList<SpreadsheetCell>> rows;
	private ObservableList<SpreadsheetCell> list;
	
	public void initialize()
	{
		degreeCombo.setItems(degreeOL);
		degreeCombo.setValue("Undergraduate");
		yearCombo.setItems(YearOL);
		yearCombo.setValue("2017");
		proposalCombo.setItems(PersentageOL);
		proposalCombo.setValue("0");
		interimCombo.setItems(PersentageOL);
		interimCombo.setValue("0");
		finalCombo.setItems(PersentageOL);
		finalCombo.setValue("0");
	
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

    }
    
    @FXML
    void exportAction(ActionEvent event) {

    }

    private Students[] studentArray;
    private int studentNo;
    @FXML
    void loadAction(ActionEvent event) throws IOException, ClassNotFoundException {
   	     String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";

     	  FileInputStream readStream1 = new FileInputStream(filename);
          ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);
  	      ToolsForHelp tool = new ToolsForHelp();  	     
  	      studentNo = tool.FilesManagerGetVlaue(filename);
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
 	    		columns.add(studentArray[i].getFirstName()+" "+studentArray[i].getLastName());
 	    		columns.add(studentArray[i].getId());
 	    		columns.add(studentArray[i].getAssessments().getproposalSV()+"");
 	    		columns.add(studentArray[i].getAssessments().getpropsalDM()+"");
 	    		columns.add(studentArray[i].getAssessments().getproposalFG()+"");
 	    		columns.add(studentArray[i].getAssessments().getinterimSV()+"");
 	    		columns.add(studentArray[i].getAssessments().getinterimDM()+"");
 	    		columns.add(studentArray[i].getAssessments().getinterimFG()+"");
 	    		columns.add(studentArray[i].getAssessments().getfinalSV()+"");
 	    		columns.add(studentArray[i].getAssessments().getfinalDM()+"");
 	    		columns.add(studentArray[i].getAssessments().getfinalFG()+"");
 	    		main.add(columns);
	      }
	     
		Iterator<List<String>> rowsListIterator = main.iterator();
		Iterator<String> columnsListIterator = null;
	    int rowCount = main.size();
	    int columnCount = 12;

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
  	   spreadsheet.getGrid().getColumnHeaders().setAll("Full Name", "Student ID","Proposal SV","Proposal DM", "Proposal Aggreed","Interim SV",  "Interim DM", "Interim Aggreed", "Final SV",  "Final DM", "Final Aggreed", "Final Grades");
    }
    
    @FXML
    void saveOrUpdateAction(ActionEvent event) throws IOException {
  	     String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";

    	SpreadsheetCell cell = rows.get(0).get(0);
	    for (int row = 0; row < grid.getRowCount(); ++row) 
	    { 
	    	Assessments ob = new Assessments();
    		cell = rows.get(row).get(2);
    		if(!cell.getText().equals(" "))
    		ob.setproposalSV(cell.getText());
    		cell = rows.get(row).get(3);
    		if(!cell.getText().equals(" "))
    		ob.setpropsalDM(cell.getText());
    		cell = rows.get(row).get(4);
    		if(!cell.getText().equals(" "))
    		ob.setproposalFG(cell.getText());
    		cell = rows.get(row).get(5);
    		if(!cell.getText().equals(" "))
    		ob.setinterimSV(cell.getText());
    		cell = rows.get(row).get(6);
    		if(!cell.getText().equals(" "))
    		ob.setinterimDM(cell.getText());
    		cell = rows.get(row).get(7);
    		if(!cell.getText().equals(" "))
    		ob.setinterimFG(cell.getText());
    		cell = rows.get(row).get(8);
    		if(!cell.getText().equals(" "))
    		ob.setfinalSV(cell.getText());
    		cell = rows.get(row).get(9);
    		if(!cell.getText().equals(" "))
    		ob.setfinalDM(cell.getText());
    		cell = rows.get(row).get(10);
    		if(!cell.getText().equals(" "))
    		ob.setfinalFG(cell.getText());
    		
    		studentArray[row].setAssessments(ob);
	    }
    	  //Now Save the updated array back to the file
	     FileOutputStream saveStream = new FileOutputStream(filename);
	     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
	     for (int i = 0; i < studentArray.length ; i++)
	       {
	    	  savingObjects.writeObject(studentArray[i]);
	       }  
	      savingObjects.close();
    }
    
    @FXML
    void calculateGradesAction(ActionEvent event) {
    		    
    	int propsalPercentage = Integer.parseInt(proposalCombo.getValue()+"");
    	int interimPercentage = Integer.parseInt(interimCombo.getValue()+"");
    	int finalPercentage = Integer.parseInt(finalCombo.getValue()+"");

    	 for (int row = 0; row < grid.getRowCount(); ++row) {
    	{
        	double propsalTemp = 0;
        	double interimTemp = 0;
        	double finalTemp = 0;
        	double finalGrades = 0;
        	
	    	SpreadsheetCell cell = rows.get(row).get(4);
	    	String cellOne = cell.getText()+"";
	    	if(propsalPercentage !=0 && !cellOne.equals("") && !cellOne.equals(" "))
	    	{
	    	propsalTemp =  getActualValue (cell.getText()+"");
	    	System.out.println(propsalTemp + " propsalTemp");
	    	propsalTemp = propsalTemp * propsalPercentage / 100;
	    	}
	    	
	    	SpreadsheetCell cell1 = rows.get(row).get(7);
	    	String cellTwo = cell1.getText()+"";
	    	if(interimPercentage != 0 && !cellTwo.equals("") && !cellTwo.equals(" "))
	    	{
	    	interimTemp =  getActualValue (cell1.getText()+"");
	    	System.out.println(interimTemp + " interimTemp");
	    	interimTemp = interimTemp * interimPercentage / 100;
	    	}
	    	
	    	SpreadsheetCell cell2 = rows.get(row).get(10);
	    	String cellThree = cell2.getText()+"";
	    	if( finalPercentage != 0 && !cellThree.equals("") && !cellThree.equals(" "))
	    	{
	    	finalTemp = getActualValue (cell2.getText()+"");
	    	System.out.println(finalTemp + " finalTemp");
	    	finalTemp = finalPercentage * finalTemp /  100;   	
	    	}
	    	
	    	finalGrades = propsalTemp + interimTemp + finalTemp ;
	    	System.out.println(finalGrades + " Finalll");
	 	    grid.setCellValue(row, 11, getSymbole(finalGrades));
    	}
    }
    }
    
    public double getActualValue (String Symbole)
    {
    	double holder = 0;
    	String level = degreeCombo.getValue()+"";
    	if (level.equals("Undergraduate"))
    	{
    	if(Symbole.equals("A+"))
    		holder = 25;
    	if(Symbole.equals("A"))
    		holder = 23;
    	if(Symbole.equals("A-"))
    		holder = 21;
    	if(Symbole.equals("B+"))
    		holder = 20;
    	if(Symbole.equals("B"))
    		holder = 19;
    	if(Symbole.equals("B-"))
    		holder = 18;
    	if(Symbole.equals("C+"))
    		holder = 17;
    	if(Symbole.equals("C"))
    		holder = 16;
    	if(Symbole.equals("C-"))
    		holder = 15;
    	if(Symbole.equals("D+"))
    		holder = 14;
    	if(Symbole.equals("D"))
    		holder = 13;
    	if(Symbole.equals("D-"))
    		holder = 12;
    	if(Symbole.equals("F+"))
    		holder = 11;
    	if(Symbole.equals("F"))
    		holder = 8;
    	if(Symbole.equals("F-"))
    		holder = 4;
    	if(Symbole.equals("G"))
    		holder = 0;
    	}
    	else if (level.equals("Postgraduate"))
    	{
        	if(Symbole.equals("A+"))
        		holder = 25;
        	if(Symbole.equals("A"))
        		holder = 23;
        	if(Symbole.equals("A-"))
        		holder = 21;
        	if(Symbole.equals("B+"))
        		holder = 20;
        	if(Symbole.equals("B"))
        		holder = 19;
        	if(Symbole.equals("B-"))
        		holder = 18;
        	if(Symbole.equals("C+"))
        		holder = 17;
        	if(Symbole.equals("C"))
        		holder = 16;
        	if(Symbole.equals("C-"))
        		holder = 15;
        	if(Symbole.equals("F+"))
        		holder = 14;
        	if(Symbole.equals("F"))
        		holder = 11.5;
        	if(Symbole.equals("F-"))
        		holder = 6;
        	if(Symbole.equals("G"))
        		holder = 0;
    	}
 	  return holder;
    }
    
    public String getSymbole (double grade)
    {
    	String holder = "";
    	String level = degreeCombo.getValue()+"";
    	if (level.equals("Undergraduate"))
    	{
    	if(grade >= 25)
    		holder = "A+";
    	if(grade >= 23 && grade < 25)
    		holder = "A";
    	if(grade >= 21 && grade < 23)
    		holder = "A-";
    	if(grade >= 20 && grade < 21)
    		holder = "B+";
    	if(grade >= 19 && grade < 20)
    		holder = "B";
    	if(grade >= 18 && grade < 19)
    		holder = "B-";
    	if(grade >= 17 && grade < 18)
    		holder = "C+";
    	if(grade >= 16 && grade < 17)
    		holder = "C";
    	if(grade >= 15 && grade < 16)
    		holder = "C-";
    	if(grade >= 14 && grade < 15)
    		holder = "D+";
    	if(grade >= 13 && grade < 14)
    		holder = "D";
    	if(grade >= 12 && grade < 13)
    		holder = "D-";
    	if(grade >= 11 && grade < 12)
    		holder = "F+";
    	if(grade >= 8 && grade < 11)
    		holder = "F";
    	if(grade >= 4 && grade < 8)
    		holder = "F-";
    	if(grade >= 0 && grade < 4)
    		holder = "G";
    	}
    	else if (level.equals("Postgraduate"))
    	{
        	if(grade >= 25)
        		holder = "A+";
        	if(grade >= 23 && grade < 25)
        		holder = "A";
        	if(grade >= 21 && grade < 23)
        		holder = "A-";
        	if(grade >= 20 && grade < 21)
        		holder = "B+";
        	if(grade >= 19 && grade < 20)
        		holder = "B";
        	if(grade >= 18 && grade < 19)
        		holder = "B-";
        	if(grade >= 17 && grade < 18)
        		holder = "C+";
        	if(grade >= 16 && grade < 17)
        		holder = "C";
        	if(grade >= 15 && grade < 16)
        		holder = "C-";
        	if(grade >= 14 && grade < 15)
        		holder = "F+";
        	if(grade >= 11.5 && grade < 14)
        		holder = "F";
        	if(grade >= 6 && grade < 11.5)
        		holder = "F-";
        	if(grade >= 0 && grade < 4)
        		holder = "G";
    	}
	  return holder;
    }
    @FXML
    void importFromExcelAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Select And Copy");
            stage.setScene(new Scene(root1));  
            stage.show();
          }
        catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
