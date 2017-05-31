import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

import com.sun.glass.ui.Size;

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

public class StaffController {

    @FXML
    private ListView StaffList;

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
    private ComboBox SpecializationCombo;

    @FXML
    private ListView SupervisedStudentsList;
    
    @FXML
    private ComboBox JobTypeCombo;
    
    ObservableList<String> GenderOL = FXCollections.observableArrayList("Male","Female");
    ObservableList<String> JobOL = FXCollections.observableArrayList("Full Time","Part Time");
    ObservableList<String> SpecializationOL = FXCollections.observableArrayList("Software Engineering","Network Engineering", "Games  Developement", "Web Technology & Security", "Graphics & Visualisation", "General Computing");
    ObservableList<String> SupervisedStudentOL = FXCollections.observableArrayList();
    ObservableList<String> StaffOL = FXCollections.observableArrayList();
    
    private String targetStudentId = " ";
    
    public void initialize() throws IOException, ClassNotFoundException 
    {
    	SpecializationCombo.setItems(SpecializationOL);
     	GenderCombo.setItems(GenderOL);
     	SpecializationCombo.setValue("General Computing");
     	GenderCombo.setValue("Male");
     	SupervisedStudentsList.setItems(SupervisedStudentOL);
     	StaffList.setItems(StaffOL); 	
     	
    	JobTypeCombo.setItems(JobOL);
    	JobTypeCombo.setValue("Full Time");
     	// Load all staff to the list of staff
        // Read staff list
        FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);

         ToolsForHelp tool = new ToolsForHelp();
          int staffArraySize = tool.FilesManagerGetVlaue("StaffCounter");
         
        // Create a  array
        Staff [] staff = new Staff[staffArraySize];
        // Read the serialized objects from the file.
        for (int i = 0; i < staffArraySize; i++)
        {
        	staff[i] = (Staff) readingObjects.readObject();
        }
        readingObjects.close();
     	
     	for (int i = 0; i < staffArraySize; i++)
			{
      		StaffOL.add(staff[i].getId()+" "+staff[i].getFirstName()+" "+staff[i].getLastName());
			}
    }

    @FXML
    void DeleteAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	if(!targetStudentId.equals(" "))
    	{
             int targetPosition = 0;
             FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
             ObjectInputStream readingObjects = new ObjectInputStream(readStream);

              ToolsForHelp tool = new ToolsForHelp();
               int staffArraySize = tool.FilesManagerGetVlaue("StaffCounter");
              
             // Create a  array
             Staff [] staff = new Staff[staffArraySize];
             Staff [] staffReplacement = new Staff[staffArraySize-1];

             // Read the serialized objects from the file.
             for (int i = 0; i < staffArraySize; i++)
             {
             	staff[i] = (Staff) readingObjects.readObject();
             }
             readingObjects.close();
             // Find the targetStaff
             
             // Copy the staff array into another array except the target staff
             int externalCounter = 0;
             for (int i = 0; i < staffArraySize; i++)
             {

            	 if(!staff[i].getId().equals(targetStudentId))
            	 {
               		staffReplacement[externalCounter] = staff[i];
               		externalCounter++;
            	 }
             }
             
       	      FileOutputStream saveStream = new FileOutputStream("Storage/Staff.dat");
       	      ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
       	      // Write the serialized objects to the file.
       	      for (int i = 0; i < staffArraySize - 1; i++)
       	      {
       	    	  savingObjects.writeObject(staffReplacement[i]);
       	      }
       	      savingObjects.close();
       	      tool.FilesManagerDecrease("StaffCounter");
    	}
    }

    @FXML
    void RegisterAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	
    	if(ValidateInputs()) // if Inputs are valid!
    	{
    	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int satffNo = tool.FilesManagerGetVlaue("StaffCounter");
  	      satffNo = satffNo + 1; // Create space to the new staff
  	      // Create a  array
  	      Staff[] staffArray = new Staff[satffNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < satffNo - 1; i++)
	      {
	    	  staffArray[i] = (Staff) readingObjects.readObject();
	      }
	      readingObjects.close();
	      char x = rundomFinderGenerator(staffArray, satffNo);
	
 		  // load the new staff into the array
	      staffArray[satffNo - 1] = new Staff(IdText.getText(), FirstNameText.getText(), LastNAmeText.getText(), BirthdayDate.getValue(),
	    		  							AddressText.getText(), 	GenderCombo.getValue()+"", ContactNumberText.getText(), ContactEmail.getText(), SpecializationCombo.getValue()+"",JobTypeCombo.getValue()+"",x);
 
	      //Now Save the updated array back to the file
	     FileOutputStream saveStream = new FileOutputStream("Storage/Staff.dat");
	     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
	     for (int i = 0; i < satffNo; i++)
	       {
	    	  savingObjects.writeObject(staffArray[i]);
	       }  
 	      savingObjects.close();
 	      tool.FilesManagerIncrease("StaffCounter");
    	}
    }
    
	
	public char rundomFinderGenerator( Staff[] array , int index)
	{
	    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
	    final int N = alphabet.length();
	    Random r = new Random();
	    char sample = alphabet.charAt(r.nextInt(N));
	      for (int i = 0; i < index - 1; i++)
	      {
	    	  if(array[i].getSpecialFinder() == sample)
	    		  rundomFinderGenerator(array ,index);
	      }
	      return sample;
	}

    @FXML
    void StaffListAction(MouseEvent event) throws IOException, ClassNotFoundException {
       	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int satffNo = tool.FilesManagerGetVlaue("StaffCounter");
  	      satffNo = satffNo ;
  	      // Create a  array
  	      Staff[] staffArray = new Staff[satffNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < satffNo; i++)
	      {
	    	  staffArray[i] = (Staff) readingObjects.readObject();
	      }
	      readingObjects.close();

	       targetStudentId = StaffList.getSelectionModel().getSelectedItem()+"";
	       String[] temp=targetStudentId.split("\\s");
	       targetStudentId = temp[0];
 	      //Check the selected staff details
	       int StaffIndex = -1;
		     for (int i = 0; i < satffNo; i++)
		       {
		    	 if(staffArray[i].getId().equals(targetStudentId))
		    	 {
		    		 StaffIndex = i;
		    		 break;
		    	 }
		       }  
		     IdText.setText(staffArray[StaffIndex].getId());
		     FirstNameText.setText(staffArray[StaffIndex].getFirstName());
		     LastNAmeText.setText(staffArray[StaffIndex].getLastName());
		     BirthdayDate.setValue(staffArray[StaffIndex].getBirthday());
		     AddressText.setText(staffArray[StaffIndex].getAddress());
		     GenderCombo.setValue(staffArray[StaffIndex].getGender());
		     ContactNumberText.setText(staffArray[StaffIndex].getContactNumber());
		     ContactEmail.setText(staffArray[StaffIndex].getContactEmail());
		     SpecializationCombo.setValue(staffArray[StaffIndex].getSpecialization());
		     
	    	// Read the supervised Students
		     SupervisedStudentOL.clear();
		     int year = Calendar.getInstance().get(Calendar.YEAR);
		     String filename = "Storage/Undergraduate"+year+".dat";
	    	 FileInputStream readStream1 = new FileInputStream(filename);
	         ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);
	   	      
	   	     int studentNo = tool.FilesManagerGetVlaue(filename);
	    	      // Create a  array
	   	      Students[] studentArray = new Students[studentNo];
	   	      
	 	      // Read the serialized objects from the file.
	 	      for (int i = 0; i < studentNo; i++)
	 	      {
	 	    	  studentArray[i] = (Students) readingObjects1.readObject();
	 	      }
	 	      readingObjects1.close(); 
	 	      
	 	     SupervisedStudentOL.add("             Undergradute Students");
	 	     SupervisedStudentOL.add("");
	 	      for (int i = 0; i < studentNo; i++)
	 	      {
	 	    	  if(studentArray[i].getSuperVisor().equals(targetStudentId))
	 	    		 SupervisedStudentOL.add(studentArray[i].getId() + " "+ studentArray[i].getFirstName() + " " + studentArray[i].getLastName());
	 	      }
	 	     SupervisedStudentOL.add("");
	 	     SupervisedStudentOL.add("             Postgraduate Students");
	 	     
		     String filename2 = "Storage/Postgraduate"+year+".dat";
	    	 FileInputStream readStream2 = new FileInputStream(filename2);
	         ObjectInputStream readingObjects2 = new ObjectInputStream(readStream2);
	   	      
	   	     int studentNo2 = tool.FilesManagerGetVlaue(filename2);
	    	      // Create a  array
	   	      Students[] studentArray2 = new Students[studentNo2];
	   	      
	 	      // Read the serialized objects from the file.
	 	      for (int i = 0; i < studentNo2; i++)
	 	      {
	 	    	  studentArray2[i] = (Students) readingObjects2.readObject();
	 	      }
	 	      readingObjects2.close(); 
	 	      
	 	      for (int i = 0; i < studentNo2; i++)
	 	      {
	 	    	  if(studentArray2[i].getSuperVisor().equals(targetStudentId))
	 	    		 SupervisedStudentOL.add(studentArray2[i].getId() + " "+ studentArray2[i].getFirstName() + " " + studentArray2[i].getLastName());
	 	      }
    }

    @FXML
    void UpdateAction(ActionEvent event) throws IOException, ClassNotFoundException {
    	if(ValidateInputs()) // if Inputs are valid!
    	{
    	// Read and Store all Staff objects to use them in the class functions
        FileInputStream readStream = new FileInputStream("Storage/Staff.dat");
        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
  	      
  	     ToolsForHelp tool = new ToolsForHelp();
  	     int satffNo = tool.FilesManagerGetVlaue("StaffCounter");
  	      satffNo = satffNo ;
  	      // Create a  array
  	      Staff[] staffArray = new Staff[satffNo];
  	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < satffNo; i++)
	      {
	    	  staffArray[i] = (Staff) readingObjects.readObject();
	      }
	      readingObjects.close();

	      //Check the selected staff details
	       int StaffIndex = -1;
		     for (int i = 0; i < satffNo; i++)
		       {
		    	 if(staffArray[i].getId().equals(targetStudentId))
		    	 {
		    		 StaffIndex = i;
		    		 break;
		    	 }
		       }  
	      
	      // Update staff details
		     staffArray[StaffIndex].setId(IdText.getText());
		     staffArray[StaffIndex].setFirstname(FirstNameText.getText());
		     staffArray[StaffIndex].setLastname(LastNAmeText.getText());
		     staffArray[StaffIndex].setbirthday(BirthdayDate.getValue());
		     staffArray[StaffIndex].setaddress(AddressText.getText());
		     staffArray[StaffIndex].setgender(GenderCombo.getValue()+"");
		     staffArray[StaffIndex].setcontactNumber(ContactNumberText.getText());
		     staffArray[StaffIndex].setcontactEmail(ContactEmail.getText());
		     staffArray[StaffIndex].setSpecialization(SpecializationCombo.getValue()+"");
	 
	      
	        //Now Save the updated array back to the file
		     FileOutputStream saveStream = new FileOutputStream("Storage/Staff.dat");
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < satffNo; i++)
		       {
		    	  savingObjects.writeObject(staffArray[i]);
		       }  
	 	      savingObjects.close();
     	}
    }
    
    public boolean ValidateInputs()
   	{
   		
       	Validator validator = new Validator();
       	boolean checker = true;
       	if(!validator.validateName(IdText.getText()))
       	{
   		   JOptionPane.showMessageDialog(null, "Invalid Id", "Error", JOptionPane.ERROR_MESSAGE);
   		   checker = false;
       	}
       	if(!validator.validateEmailAddress(ContactEmail.getText()))
       	{
   		   JOptionPane.showMessageDialog(null, "Invalid Email Address", "Error", JOptionPane.ERROR_MESSAGE);
   		   checker = false;
       	}
       	return checker;
   	}
}
