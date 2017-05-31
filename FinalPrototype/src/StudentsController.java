import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StudentsController {

    @FXML
    private ListView searchResultsList;

    @FXML
    private TextField FirstNameText;

    @FXML
    private TextField LastNAmeText;

    @FXML
    private DatePicker BirthdayDate;

    @FXML
    private TextArea AddressText;

    @FXML
    private ComboBox GenderCombo;

    @FXML
    private TextField IdText;

    @FXML
    private TextField ContactNumberText;

    @FXML
    private TextField ContactEmail;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private ComboBox pathwayCombo;

    @FXML
    private ComboBox degreeCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private ComboBox searchCriteriaCombo;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox courseCombo;

    @FXML
    private TextArea topicText;

    @FXML
    private ComboBox superVisorCombo;

    @FXML
    private TextArea additionalNotesText;
    
    ObservableList<String> YearOL = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025",
 			"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035");
     
 	ObservableList<String> degreeOL = FXCollections.observableArrayList("Undergraduate", "PostGraduate");
 	ObservableList<String> searchCritereaOL = FXCollections.observableArrayList("By FirstName", "By LastName", "By Id");
    ObservableList<String> StudentOL = FXCollections.observableArrayList();
    ObservableList<String> PathwayOL = FXCollections.observableArrayList("Software Engineering","Network Engineering", "Games  Developement", "Web Technology & Security", "Graphics & Visualisation", "General Computing");
    ObservableList<String> CourseOL = FXCollections.observableArrayList("BSc","HNd", "BEng");
    ObservableList<String> SupervisorsOL = FXCollections.observableArrayList();
    ObservableList<String> GenderOL = FXCollections.observableArrayList("Male","Female");
    Staff [] staff;

 	
 	public void initialize() throws IOException, ClassNotFoundException
 	{
 		degreeCombo.setItems(degreeOL);
 		degreeCombo.setValue("Undergraduate");
 		yearCombo.setItems(YearOL);
 		yearCombo.setValue("2017");
 		searchCriteriaCombo.setItems(searchCritereaOL);
 		searchCriteriaCombo.setValue("By Id");
 		searchResultsList.setItems(StudentOL);
 		pathwayCombo.setItems(PathwayOL);
 		courseCombo.setItems(CourseOL);
 		superVisorCombo.setItems(SupervisorsOL);
 		GenderCombo.setItems(GenderOL);
 		
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
     	
     	for (int i = 0; i < staffArraySize; i++)
		{
     	  SupervisorsOL.add(staff[i].getId());
		}
 	}
 	
 	private String targetStudentId = " ";
 	

    @FXML
    void DeleteAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	  String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";

    	if(!targetStudentId.equals(" "))
    	{
             int targetPosition = 0;
             FileInputStream readStream = new FileInputStream(filename);
             ObjectInputStream readingObjects = new ObjectInputStream(readStream);

              ToolsForHelp tool = new ToolsForHelp();
               int ArraySize = tool.FilesManagerGetVlaue(filename);
              
             // Create a  array
             Students [] student = new Students[ArraySize];
             Students [] studentReplacement = new Students[ArraySize-1];

             // Read the serialized objects from the file.
             for (int i = 0; i < ArraySize; i++)
             {
            	 student[i] = (Students) readingObjects.readObject();
             }
             readingObjects.close();
             // Find the targetStaff
             
             // Copy the staff array into another array except the target staff
             int externalCounter = 0;
             for (int i = 0; i < ArraySize; i++)
             {
            	 if(!student[i].getId().equals(targetStudentId) && !student[i].getDegree().equals(degreeCombo.getValue()+"")
            			 && !student[i].getYear().equals(yearCombo.getValue()+""))
            	 {
            		 studentReplacement[externalCounter] = student[i];
               		externalCounter++;
            	 }
             }
             
       	      FileOutputStream saveStream = new FileOutputStream(filename);
       	      ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
       	      // Write the serialized objects to the file.
       	      for (int i = 0; i < ArraySize - 1; i++)
       	      {
       	    	  savingObjects.writeObject(studentReplacement[i]);
       	      }
       	      savingObjects.close();
       	      tool.FilesManagerDecrease(filename);
    	}
    }

    @FXML
    void RegisterAction(ActionEvent event) throws ClassNotFoundException, IOException {
    	if(ValidateInputs()) // if Inputs are valid!
    	{
      	  String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";

    	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream(filename);
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int studentNo = tool.FilesManagerGetVlaue(filename);
  	     studentNo = studentNo + 1; // Create space to the new staff
  	      // Create a  array
  	      Students[] studentArray = new Students[studentNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo - 1; i++)
	      {
	    	  studentArray[i] = (Students) readingObjects.readObject();
	      }
	      readingObjects.close();
	         
		   Assessments ob = new Assessments();
 		  // load the new student into the array
	      studentArray[studentNo - 1] = new Students(IdText.getText(), FirstNameText.getText(), LastNAmeText.getText(), BirthdayDate.getValue(),
	    		  							AddressText.getText(), 	GenderCombo.getValue()+"", ContactNumberText.getText(), ContactEmail.getText(), pathwayCombo.getValue()+"",courseCombo.getValue()+"", additionalNotesText.getText(),topicText.getText(), yearCombo.getValue()+"", degreeCombo.getValue()+"" ,ob );
 
	      //Now Save the updated array back to the file
		     FileOutputStream saveStream = new FileOutputStream(filename);
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < studentNo; i++)
		       {
		    	  savingObjects.writeObject(studentArray[i]);
		       }  
	 	      savingObjects.close();
	 	      tool.FilesManagerIncrease(filename);
	    	}
   
    }

    @FXML
    void UpdateAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	if(ValidateInputs()) // if Inputs are valid!
    	{
         String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
    	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream(filename);
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int studentNo = tool.FilesManagerGetVlaue(filename);
   	      // Create a  array
  	      Students[] studentArray = new Students[studentNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  studentArray[i] = (Students) readingObjects.readObject();
	      }
	      readingObjects.close();

	      //Check the selected student details
	       int studentIndex = -1;
		     for (int i = 0; i < studentNo; i++)
		       {
		    	 if(studentArray[i].getId().equals(targetStudentId) && studentArray[i].getYear().equals(yearCombo.getValue()+"")
		    			 && studentArray[i].getDegree().equals(degreeCombo.getValue()+""))
		    	 {
		    		 studentIndex = i;
		    		 break;
		    	 }
		       }  
	      
	      // Update staff details
		     studentArray[studentIndex].setId(IdText.getText());
		     studentArray[studentIndex].setFirstname(FirstNameText.getText());
		     studentArray[studentIndex].setLastname(LastNAmeText.getText());
		     studentArray[studentIndex].setbirthday(BirthdayDate.getValue());
		     studentArray[studentIndex].setaddress(AddressText.getText());
		     studentArray[studentIndex].setgender(GenderCombo.getValue()+"");
		     studentArray[studentIndex].setcontactNumber(ContactNumberText.getText());
		     studentArray[studentIndex].setcontactEmail(ContactEmail.getText());
		     studentArray[studentIndex].setPathway(pathwayCombo.getValue()+"");
		     studentArray[studentIndex].setCourse(courseCombo.getValue()+"");
		     studentArray[studentIndex].setTopic(topicText.getText());
		     
	     	for (int i = 0; i < staff.length; i++)
			{
	     		if(staff[i].getId().equals(superVisorCombo.getValue()+""))
	     		{
	     		studentArray[studentIndex].setSuperVisor(staff[i]);
	     		break;
	     		}
			}
		     	
		     studentArray[studentIndex].setAdditionalNotes(additionalNotesText.getText());
		     
	        //Now Save the updated array back to the file
		     FileOutputStream saveStream = new FileOutputStream(filename);
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < studentNo; i++)
		       {
		    	  savingObjects.writeObject(studentArray[i]);
		       }  
	 	      savingObjects.close();
     	}
    }

    @FXML
    void searchAction(ActionEvent event) throws ClassNotFoundException, IOException {
    	 StudentOL.clear();
    	 String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
    	 FileInputStream readStream = new FileInputStream(filename);
         ObjectInputStream readingObjects = new ObjectInputStream(readStream);
   	      
   	     ToolsForHelp tool = new ToolsForHelp();
   	     int studentNo = tool.FilesManagerGetVlaue(filename);
    	      // Create a  array
   	      Students[] studentArray = new Students[studentNo];
   	      
 	      // Read the serialized objects from the file.
 	      for (int i = 0; i < studentNo; i++)
 	      {
 	    	  studentArray[i] = (Students) readingObjects.readObject();
 	      }
 	      readingObjects.close();

 	      
    	if(searchCriteriaCombo.getValue().equals("By Id"))
    	{
	      for (int i = 0; i < studentNo; i++)
 	      {
 	    	  if(studentArray[i].getId().equals(searchText.getText()))
 	       		StudentOL.add(studentArray[i].getId()+" "+studentArray[i].getFirstName()+" "+studentArray[i].getLastName());
 	      }
    		
    	}
    	if(searchCriteriaCombo.getValue().equals("By FirstName"))
    	{
  	      for (int i = 0; i < studentNo; i++)
 	      {
 	    	  if(studentArray[i].getFirstName().equals(searchText.getText()))
 	 	       		StudentOL.add(studentArray[i].getId()+" "+studentArray[i].getFirstName()+" "+studentArray[i].getLastName());
 	      }
    	}
    	if(searchCriteriaCombo.getValue().equals("By LastName"))
    	{
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  if(studentArray[i].getLastName().equals(searchText.getText()))
	 	       		StudentOL.add(studentArray[i].getId()+" "+studentArray[i].getFirstName()+" "+studentArray[i].getLastName());
	      }
    	}
    }

    @FXML
    void searchResultsAction(MouseEvent event) throws IOException, ClassNotFoundException {
  	  String filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
    	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream(filename);
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int studentNo = tool.FilesManagerGetVlaue(filename);
  	      // Create a  array
  	     Students[] studentArray = new Students[studentNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  studentArray[i] = (Students) readingObjects.readObject();
	      }
	      readingObjects.close();

	       targetStudentId = searchResultsList.getSelectionModel().getSelectedItem()+"";
	       String[] temp=targetStudentId.split("\\s");
	       targetStudentId = temp[0];
 	      //Check the selected staff details
	       int StudnetIndex = -1;
		     for (int i = 0; i < studentNo; i++)
		       {
		    	 if(studentArray[i].getId().equals(targetStudentId))
		    	 {
		    		 StudnetIndex = i;
		    		 break;
		    	 }
		       }  
		     IdText.setText(studentArray[StudnetIndex].getId());
		     FirstNameText.setText(studentArray[StudnetIndex].getFirstName());
		     LastNAmeText.setText(studentArray[StudnetIndex].getLastName());
		     BirthdayDate.setValue(studentArray[StudnetIndex].getBirthday());
		     AddressText.setText(studentArray[StudnetIndex].getAddress());
		     GenderCombo.setValue(studentArray[StudnetIndex].getGender());
		     ContactNumberText.setText(studentArray[StudnetIndex].getContactNumber());
		     ContactEmail.setText(studentArray[StudnetIndex].getContactEmail());
		     pathwayCombo.setValue(studentArray[StudnetIndex].getPathway());
		     courseCombo.setValue(studentArray[StudnetIndex].getCourse());
		     topicText.setText(studentArray[StudnetIndex].getTopic());
		     superVisorCombo.setValue(studentArray[StudnetIndex].getSuperVisor());
		     additionalNotesText.setText(studentArray[StudnetIndex].getAdditionalNotes());	
		     
		     
		     
		  	  searchResultsList.getSelectionModel().clearSelection();

    }
 
    public boolean ValidateInputs()
   	{
   		
       	Validator validator = new Validator();
       	boolean checker = true;
       	if(!validator.validateNumber(IdText.getText()))
       	{
   		   JOptionPane.showMessageDialog(null, "Invalid Id Number", "Error", JOptionPane.ERROR_MESSAGE);
   		   checker = false;
       	}
       	return checker;
   	}
}
