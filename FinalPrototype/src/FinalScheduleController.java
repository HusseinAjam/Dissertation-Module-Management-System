import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FinalScheduleController {

    @FXML
    private ComboBox degreeCombo;

    @FXML
    private ComboBox yearCombo;

    @FXML
    private DatePicker dayOneSelector;

    @FXML
    private Button FirstNextButton;

    @FXML
    private ComboBox startingCombo;

    @FXML
    private ComboBox endingCombo;

    @FXML
    private AnchorPane NoDMAnchor;

    @FXML
    private ListView NoDMList;

    @FXML
    private Button SecondNextButton;

    @FXML
    private AnchorPane NoRoomAnchor;

    @FXML
    private ListView NoRoomsList;

    @FXML
    private Button ThirdNextButton;

    @FXML
    private Button ScheduleButton;

    private ObservableList<String> YearOL = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025",
			"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035");
    private ObservableList<String> degreeOL = FXCollections.observableArrayList("Undergraduate", "Postgraduate");
    private ObservableList<String> timeOL = FXCollections.observableArrayList("09:00", "09:30","10:00", "10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00");
    private ObservableList<String> staffOL = FXCollections.observableArrayList();
    private ObservableList<String> RoomsOL = FXCollections.observableArrayList();
    private ObservableList<String> studentsOL = FXCollections.observableArrayList();
 
    
    private Rooms [] room;
    private Staff [] staff;
    private Students [] students;
    private String filename;
    private int activeRooms = 0;
    
	public void initialize() throws  IOException, ClassNotFoundException
    {
		
		degreeCombo.setItems(degreeOL);
		degreeCombo.setValue("Undergraduate");
		yearCombo.setItems(YearOL);
		yearCombo.setValue("2017");
		NoDMList.setItems(staffOL);
		NoDMList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		NoRoomsList.setItems(RoomsOL);
		NoRoomsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		startingCombo.setItems(timeOL);
		startingCombo.setValue("Select Time");
		endingCombo.setItems(timeOL);
		endingCombo.setValue("Select Time");
		activeRooms =0;
		 FileInputStream readStream = new FileInputStream("Storage/Rooms.dat");
	        ObjectInputStream readingObjects = new ObjectInputStream(readStream);
	  	      
	   	      // Create a  array
	  	       room = new Rooms[20];
	  	      
		      // Read the serialized objects from the file.
		      for (int i = 0; i < 20; i++)
		      {
		    	  room[i] = (Rooms) readingObjects.readObject();
		      }
		      readingObjects.close();
		      
		      for (int i = 0; i < 20; i++)
		      {    	 
		       if(room[i].getRoomState())
		       {
		    	activeRooms++;
		    	RoomsOL.add(room[i].getRoomName());
	 	       }
		      }
		      
		      FileInputStream readStream1 = new FileInputStream("Storage/Staff.dat");
		       ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);

		       ToolsForHelp tool = new ToolsForHelp();
		       int staffArraySize = tool.FilesManagerGetVlaue("StaffCounter");
		         
		        // Create a  array
		         staff = new Staff[staffArraySize];
		        // Read the serialized objects from the file.
		        for (int i = 0; i < staffArraySize; i++)
		        {
		        	staff[i] = (Staff) readingObjects1.readObject();
		        }
		        readingObjects1.close();
		     	
		     	for (int i = 0; i < staffArraySize; i++)
				{
 		 		   staffOL.add(staff[i].getId());
				}
	     	

			      
    }
    @FXML
    void FirstNextAction(ActionEvent event) throws IOException, ClassNotFoundException {
  	    filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
  	    FileInputStream readStream2 = new FileInputStream(filename);
        ObjectInputStream readingObjects2 = new ObjectInputStream(readStream2);
        ToolsForHelp tool = new ToolsForHelp();
	     tool = new ToolsForHelp();  	     
	      int studentNo = tool.FilesManagerGetVlaue(filename);
 	      // Create a  array
	      students = new Students[studentNo];
	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  students[i] = (Students) readingObjects2.readObject();
	      }
	      readingObjects2.close();	
	      
	      for (int i = 0; i < studentNo; i++)
	      {
	    	  studentsOL.add(students[i].getId());
	      }
	      
    	boolean cheker = true;
    	if(startingCombo.getValue().equals("Select Time"))
    	{
  	 	  JOptionPane.showMessageDialog (null, "Please select Starting time", "Missing Field", JOptionPane.ERROR_MESSAGE);
  	 	  cheker = false;
    	}
    	if(endingCombo.getValue().equals("Select Time"))
    	{
  	 	  JOptionPane.showMessageDialog (null, "Please select Finishing time", "Missing Field", JOptionPane.ERROR_MESSAGE);
  	 	  cheker = false;
    	}

    	if(cheker)
    	NoDMAnchor.setVisible(true);
    }

    @FXML
    void NoDmListAction(MouseEvent event) {
    	
    }

    @FXML
    void NoRoomsAction(MouseEvent event) {

    }

    private HashMap<String, String[][]> container = new HashMap<String, String[][]>();
    @FXML
    void ScheduleButtonAction(ActionEvent event) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException 
    {

     int start = startingCombo.getSelectionModel().getSelectedIndex();
     int end =  endingCombo.getSelectionModel().getSelectedIndex();
     ObservableList excludedRooms =  NoRoomsList.getSelectionModel().getSelectedItems();
      
     if(end > start)
     {
	       int daysCounter = 1;
	       while (true)
	       {
	    	 container.put("day" + daysCounter, new String[activeRooms - excludeRooms.size()][end - start]);
	    	    //Initilize the array
	    		for(int i = 0 ; i <  activeRooms - excludedRooms.size(); i++)
	    	   	{
	    	   	   for (int j = 0 ; j < end - start ; j++)
	    	   	   {
	    	   		 container.get("day" + daysCounter)[i][j] = "0-0";
	    	   	   }
	    	   	}
	    	 
		   	for(int i = 0 ; i <  students.length; i++)
		   	{
		   		
		   	   String staf = "not";
		   	   for (int m = 0 ; m < schema.length ; m++)
		   	   {
		   		   if(students[i].getSuperVisor().equals(schema[m][0]+"") && studentsOL.contains(students[i].getId()+""))
		   		   {
		   			staf = schema[m][0]+"";
		   		    break;
		   		   }
		   	   }
		   	   
		   	   if(!staf.equals("not"))
		   	   {
			   	   if(addSV (staf , students[i].getId() , activeRooms - excludedRooms.size() , end - start ,container.get("day" + daysCounter) ))
		   		   {
			   		studentsOL.remove(studentsOL.indexOf(students[i].getId()+""));
			   		   //Assign double marker
				    	for(int x = 0 ; x <  staff.length; x++)
				    	{
 				    	   int z =Integer.parseInt(schema[x][3]);
				    	   if(z > 0)
				    		  if(addDM (schema[x][0] , activeRooms - excludedRooms.size(), end - start , container.get("day" + daysCounter)))
				    		  {
				    			  schema[x][3] = z - 1 +"";
				    			  break;
				    		  }   
				    	}
			    	}
			    	
	 	   		  }
		   	   }	
 
		   	// Print test
		   	System.out.println("Day "+ daysCounter);
			for(int i = 0 ; i <  activeRooms - excludeRooms.size(); i++)
		   	{
		   	   for (int j = 0 ; j < end - start ; j++)
		   	   {
		   		System.out.print(container.get("day" + daysCounter)[i][j] + "  ");
		   	   }
		   	System.out.println();
		   	}
			
			writeToExcel ex = new writeToExcel(container.get("day" + daysCounter), daysCounter , RoomsOL, excludedRooms, start, end, timeOL, dayOneSelector.getValue()+"");
			
	     	for(int i = 0 ; i <  staff.length; i++)
	    	{
	    		for(int j = 0 ; j < 4 ; j++)
	    		{
	    			System.out.print(schema[i][j] + " ");
	    		}
	    		System.out.println();
	    	}
			
		   	if(studentsOL.size() == 0)
		   		break;
		   	daysCounter++;
	     }	       
     }  
     else
     {
  	 	 JOptionPane.showMessageDialog (null, "Event cannot end before start! \nOr start and end at the same time", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }
    

    private ObservableList excludedDoubleMarker;
    private ObservableList excludeRooms;
    @FXML
    void SecondNextAction(ActionEvent event) {
    	NoRoomAnchor.setVisible(true);
    }

    @FXML
    void ThirdNextAction(ActionEvent event) {
    	 excludedDoubleMarker=  NoDMList.getSelectionModel().getSelectedItems();
    	 excludeRooms = NoRoomsList.getSelectionModel().getSelectedItems();
    	if(prepareSchema ())
    	ScheduleButton.setVisible(true);
    	else
    	{
  	 	 JOptionPane.showMessageDialog (null, "Sorry, we cannot let you schedule! \nSome Students do not have Super visors \n Please go to Manage Data and add super visors for every student", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public String [][]schema;

    public boolean prepareSchema ()
    {
    	schema = new String [staff.length][4];
    	// Initilaize the array
    	for(int i = 0 ; i <  staff.length; i++)
    	{
    		for(int j = 0 ; j < 4 ; j++)
    		{
    			schema[i][j]= "0";
    		}
    	}
    	
    	// Count and add Staff name with their SV students number each
    	for(int i = 0 ; i <  staff.length; i++)
    	{
    			schema[i][0] = staff[i].getId();
    			schema[i][1] = staff[i].getJobType();
    			int SVcounting = 0;
    			
    			for(int m = 0 ; m < students.length; m++)
    			{
    				
     	    		try {
     	    			if(students[m].getSuperVisor().equals(schema[i][0]));
     	 	    		} catch(NullPointerException e) 
     	 	    		{
     	 	 	 	 	    return false;
     	 	    		}  

     				if(students[m].getSuperVisor().equals(schema[i][0]))
    				  SVcounting++;	
    			}
    			schema[i][2] = SVcounting+"";
    	}
    	
    	// Count how many staff with full time or part time job 
    	// Full time will have double duties than part time
    	
    	int studentNo = students.length;
		System.out.println(studentNo);
			// Add the details to the schema
    	int counter = 0;
    	while (studentNo > 0)
    	{
    		if(schema[counter][1].equals("Part Time") && !excludedDoubleMarker.contains(schema[counter][0]+""))
    		{
    			if(counter % 1 == 0)
    			{
    			int a = Integer.parseInt(schema[counter][3]);
    			a = a + 1;
    			schema[counter][3] = a + "";
    			studentNo --;
    			}
    			counter ++;
    			 if (counter == schema.length)
     	        	counter = 0;
    		}
    		else if (schema[counter][1].equals("Full Time") && !excludedDoubleMarker.contains(schema[counter][0]+""))
    		{
    			int a = Integer.parseInt(schema[counter][3]);
    			a = a + 1;
    			schema[counter][3] = a + "";
    			studentNo --;
    			counter ++;
    	        if (counter == schema.length)
    	        	counter = 0;
    		}	
    		else
    		{
    			counter ++;
   			    if (counter == schema.length)
    	        	counter = 0;	
    		}
    	}
    	return true;
    }
    
	public boolean addSV (String SV ,String Student ,  int roomNumber, int fullBlocksSize , String [][] accociatedSchema)
	{
		boolean forReturn = false;
		mainLoop:
		for(int i = 0 ; i < roomNumber ; i++)
		{
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				if(accociatedSchema[i][j].equals("0-0") && 2 <= fullBlocksSize - j)
				{
					if(accociatedSchema[i][j + 1].equals("0-0"))
					{
						boolean temp = true;
						for(int m = 0 ; m < roomNumber ; m++)
						{
							String string = accociatedSchema[m][j+1];
							String[] part = string.split("-");
							String temp0 =  part[0];
							String temp1 =  part[1];

							if(SV.equals(temp0) || SV.equals(temp1))   
								temp = false;
						}
 						if(temp)
						{
						 int tempi = i;
						 int tempj = j;
							for(int m = 0 ; m < roomNumber ; m++)
							{
								for(int n = j+1 ; n < j+2 ; n++)
								{
									if(j < tempj)
									{
										tempi = m;
										tempj = j;
									}
								}
							}

							accociatedSchema[tempi][tempj] = Student+"-1";
							accociatedSchema[tempi][tempj + 1] = SV +"-0";
							forReturn = true;
							break mainLoop;
						}
					}
				}
			}
		}
		return forReturn;
	}
	
	public boolean addDM (String DM , int roomNumber, int fullBlocksSize , String [][] accociatedSchema)
	{
		boolean forReturn = false;
		mainLoop:
 		for(int i = 0 ; i < roomNumber ; i++)
		{
			for (int j = 0 ; j < fullBlocksSize ; j++)
			{
				String string = accociatedSchema[i][j];
				String[] part = string.split("-");
				String temp0 =  part[0];
				String temp1 =  part[1];
				
				if(!accociatedSchema[i][j].equals("0-0") && temp1.equals("0") && !DM.equals(temp0) && !DM.equals(temp1) && !temp1.equals("1"))
				{
					boolean checker = true;
					for(int m = 0 ; m < roomNumber ; m++)					// Check for time clash
					{
						String string2 = accociatedSchema[i][j];
						String[] part2 = string2.split("-");
						String temp20 =  part2[0];
						String temp21 =  part2[1];
						
						if(DM.equals(temp20) || DM.equals(temp21))
						{
							checker = false;
							break;
						}
					}
					if(checker)
					{
						String st = accociatedSchema[i][j];
						String[] p = st.split("-");
						String t0 =  p[0];						
						accociatedSchema[i][j] = t0 + "-" + DM;
						forReturn = true;
						break mainLoop;	
					}	
				}
			}
		}
		return forReturn;
	}
}