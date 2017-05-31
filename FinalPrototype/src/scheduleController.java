import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class scheduleController {
	 @FXML
	    private ComboBox degreeCombo;

	    @FXML
	    private ComboBox yearCombo;

	    @FXML
	    private ComboBox eventDurationCombo;

	    @FXML
	    private ComboBox preparationCombo;

	    @FXML
	    private CheckBox dayTwoActivate;

	    @FXML
	    private CheckBox dayThreeActivate;

	    @FXML
	    private CheckBox dayFourActivate;

	    @FXML
	    private CheckBox dayFiveActivate;

	    @FXML
	    private Button scheduleButton;

	    @FXML
	    private ComboBox startOneCombo;

	    @FXML
	    private ComboBox endOneCombo;

	    @FXML
	    private ListView excludeOneList;

	    @FXML
	    private ComboBox startTwoCombo;

	    @FXML
	    private ComboBox  endTwoCombo;

	    @FXML
	    private ListView  excludeTwoList;

	    @FXML
	    private ComboBox  startThreeCombo;

	    @FXML
	    private ComboBox  endThreeCombo;

	    @FXML
	    private ListView  excludeThreeList;

	    @FXML
	    private ComboBox  startFourCombo;

	    @FXML
	    private ComboBox  endFourCombo;

	    @FXML
	    private ListView  excludeFourList;

	    @FXML
	    private ComboBox  startFiveCombo;

	    @FXML
	    private ComboBox  endFiveCombo;

	    @FXML
	    private ListView  excludeFiveList;

	    @FXML
	    private DatePicker dayTwoSelector;

	    @FXML
	    private DatePicker dayThreeSelector;

	    @FXML
	    private DatePicker dayFourSelector;

	    @FXML
	    private DatePicker dayFiveSelector;

	    @FXML
	    private DatePicker dayOneSelector;

	    @FXML
	    private ComboBox  breakCombo;

	    @FXML
	    private Button FirstNextButton;

	    @FXML
	    private Pane paneOne;
	    
	    @FXML
	    private AnchorPane paneTwo;

	    @FXML
	    private ListView  NoDMList;

	    @FXML
	    private ListView  NoRoomsList;

	    @FXML
	    private Button nextButton;

	    @FXML
	    private ListView NoStudentsList;
	    
	    ObservableList<String> YearOL = FXCollections.observableArrayList("2017","2018","2019","2020","2021","2022","2023","2024","2025",
				"2026","2027","2028","2029","2030","2031","2032","2033","2034","2035");
	    ObservableList<String> degreeOL = FXCollections.observableArrayList("Undergraduate", "Postgraduate");
	    ObservableList<String> eventDurationOL = FXCollections.observableArrayList("30 Minute", "1 Hour", "1 + 30 Minute", "2 Hours","2 + 30 Minute", "3 Hours", "3 + 30 Minute", "4 Hours");
	    ObservableList<String> eventPreparationOL = FXCollections.observableArrayList("30 Minute", "1 Hour", "1 + 30 Minute", "2 Hours","2 + 30 Minute", "3 Hours", "3 + 30 Minute", "4 Hours");
	    ObservableList<String> timeOL = FXCollections.observableArrayList("09:00", "09:30","10:00", "10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00");
	    ObservableList<Integer> studentCounterOL = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9);

	    ObservableList<String> staffOL = FXCollections.observableArrayList();
	    ObservableList<String> RoomsOL = FXCollections.observableArrayList();
	    ObservableList<String> StrudentsOL = FXCollections.observableArrayList();
	    
	    private Rooms [] room;
	    private Staff [] allStaff;
	    private Students [] students;
		public void initialize() throws  IOException, ClassNotFoundException
	    {
			
			degreeCombo.setItems(degreeOL);
			degreeCombo.setValue("Undergraduate");
			yearCombo.setItems(YearOL);
			yearCombo.setValue("2017");
			eventDurationCombo.setItems(eventDurationOL);
			eventDurationCombo.setValue("30 Minute");
			preparationCombo.setItems(eventPreparationOL);
			preparationCombo.setValue("30 Minute");
			NoDMList.setItems(staffOL);
			NoDMList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			NoRoomsList.setItems(RoomsOL);
			NoRoomsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			breakCombo.setItems(studentCounterOL);
			breakCombo.setValue(1);
			
			startOneCombo.setItems(timeOL);
			startTwoCombo.setItems(timeOL);
			startThreeCombo.setItems(timeOL);
			startFourCombo.setItems(timeOL);
			startFiveCombo.setItems(timeOL);
			endOneCombo.setItems(timeOL);
			endTwoCombo.setItems(timeOL);
			endThreeCombo.setItems(timeOL);
			endFourCombo.setItems(timeOL);
			endFiveCombo.setItems(timeOL);
			
			startOneCombo.setValue("09:00");
			endOneCombo.setValue("09:00");
	    	startTwoCombo.setValue("09:00");
	    	endTwoCombo.setValue("09:00");
	    	startThreeCombo.setValue("09:00");
	    	endThreeCombo.setValue("09:00");
	    	startFourCombo.setValue("09:00");
	    	endFourCombo.setValue("09:00");
	    	startFiveCombo.setValue("09:00");
	    	endFiveCombo.setValue("09:00");

			
			/*
			excludeTwoList.setItems(staffOL);
			excludeTwoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			excludeThreeList.setItems(staffOL);
			excludeThreeList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			excludeFourList.setItems(staffOL);
			excludeFourList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	
			excludeFiveList.setItems(staffOL);
			excludeFiveList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			*/
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
		    	RoomsOL.add(room[i].getRoomName() + " - " + room[i].getRoomInfo());
	 	       }
		      }
		      
		      //////////////////
		       
	       FileInputStream readStream1 = new FileInputStream("Storage/Staff.dat");
	       ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);

	       ToolsForHelp tool = new ToolsForHelp();
	       int staffArraySize = tool.FilesManagerGetVlaue("StaffCounter");
	         
	        // Create a  array
	         allStaff = new Staff[staffArraySize];
	        // Read the serialized objects from the file.
	        for (int i = 0; i < staffArraySize; i++)
	        {
	        	allStaff[i] = (Staff) readingObjects1.readObject();
	        }
	        readingObjects1.close();
	     	
	     	for (int i = 0; i < staffArraySize; i++)
			{
	     		System.out.println("Unique  " + allStaff[i].getId() + "  " + allStaff[i].getSpecialFinder());
	 		staffOL.add(allStaff[i].getId());
			}
	    }

		String filename;
	    @FXML
	    void FirstNextAction(ActionEvent event) throws IOException, ClassNotFoundException {
	    	paneOne.setVisible(true);
	    	
	    	// Read Students
	    	
	  	   filename = "Storage/"+degreeCombo.getValue()+""+yearCombo.getValue()+".dat";
	  	  FileInputStream readStream1 = new FileInputStream(filename);
	        ObjectInputStream readingObjects1 = new ObjectInputStream(readStream1);
		      ToolsForHelp tool = new ToolsForHelp();  	     
		      int studentNo = tool.FilesManagerGetVlaue(filename);
	 	      // Create a  array
		      students = new Students[studentNo];
		      
		      // Read the serialized objects from the file.
		      for (int i = 0; i < studentNo; i++)
		      {
		    	  students[i] = (Students) readingObjects1.readObject();
		      }
		      readingObjects1.close();
	    
		      for (int i = 0; i < studentNo; i++)
		      {    	 
		    	 StrudentsOL.add(students[i].getId() + " - " + students[i].getFirstName() + " " + students[i].getLastName());
		      }
				NoStudentsList.setItems(StrudentsOL);
				NoStudentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	      
	    }

	    @FXML
	    void NoDMListAction(MouseEvent event) {

	    }

	    @FXML
	    void NoRoomsListAction(MouseEvent event) {

	    }

	    @FXML
	    void NoStudentsListAction(MouseEvent event) {
	    	
	    }

	    @FXML
	    void dayFiveActivateAction(ActionEvent event) {
	     	startFiveCombo.setDisable(false);
	    	endFiveCombo.setDisable(false);
	    	excludeFiveList.setDisable(false);
	    	dayFiveSelector.setDisable(false);
	    	dayFiveSelector.setValue(dayFourSelector.getValue().plusDays(1));
	    	startFiveCombo.setValue("09:00");
	    	endFiveCombo.setValue("17:00");
			excludeFiveList.setItems(staffOL);
			excludeFiveList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    @FXML
	    void dayFourActivateAction(ActionEvent event) {
	    	dayFiveActivate.setDisable(false);
	    	startFourCombo.setDisable(false);
	    	endFourCombo.setDisable(false);
	    	excludeFourList.setDisable(false);
	    	dayFourSelector.setDisable(false);
	    	dayFourSelector.setValue(dayThreeSelector.getValue().plusDays(1));
	    	startFourCombo.setValue("09:00");
	    	endFourCombo.setValue("17:00");
			excludeFourList.setItems(staffOL);
			excludeFourList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    @FXML
	    void dayThreeActivateAction(ActionEvent event) {
	    	dayFourActivate.setDisable(false);
	    	startThreeCombo.setDisable(false);
	    	endThreeCombo.setDisable(false);
	    	excludeThreeList.setDisable(false);
	    	dayThreeSelector.setDisable(false);
	    	dayThreeSelector.setValue(dayTwoSelector.getValue().plusDays(1));
	    	startThreeCombo.setValue("09:00");
	    	endThreeCombo.setValue("17:00");
			excludeThreeList.setItems(staffOL);
			excludeThreeList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    @FXML
	    void dayTwoActivateAction(ActionEvent event) {
	    	dayThreeActivate.setDisable(false);
	    	startTwoCombo.setDisable(false);
	    	endTwoCombo.setDisable(false);
	    	excludeTwoList.setDisable(false);
	    	dayTwoSelector.setDisable(false);
	    	dayTwoSelector.setValue(dayOneSelector.getValue().plusDays(1));
	    	startTwoCombo.setValue("09:00");
	    	endTwoCombo.setValue("17:00");
			excludeTwoList.setItems(staffOL);
			excludeTwoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    @FXML
	    void nextAction(ActionEvent event) {
	    	
	    	paneTwo.setVisible(true);

	    	// Activate first day
	    	startOneCombo.setValue("09:00");
	    	endOneCombo.setValue("17:00");
			excludeOneList.setItems(staffOL);
			excludeOneList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    }

	    ObservableList excludedRooms;
	    ObservableList excludedDM;
	    ObservableList excludedStudents;
	    int roomsNo;
	    
     	int start1 ;
     	int start2;
	 	int start3 ;
	 	int start4 ;
	 	int start5 ;
	    int end1 ;
	 	int end2 ;
	 	int end3 ;
	 	int end4 ;
	 	int end5 ;
 		
	    @FXML
	    void scheduleAction(ActionEvent event) throws FileNotFoundException, IOException 
	    {
	    	// Find excluded Rooms
	     	 excludedRooms =  NoRoomsList.getSelectionModel().getSelectedItems();
	     	
	    	// Find excluded DM
	     	 excludedDM =  NoDMList.getSelectionModel().getSelectedItems();
	     	//System.out.println(excludedDM.contains("1/2 Hour"));
	    	
	     	// Find excluded Students
	     	 excludedStudents =  NoStudentsList.getSelectionModel().getSelectedItems();
	     	
	     	// Find excluded staff from day 1 to day 5
	     	ObservableList excludedStaffDay1=  excludeOneList.getSelectionModel().getSelectedItems();
	     	ObservableList excludedStaffDay2=  excludeTwoList.getSelectionModel().getSelectedItems();
	     	ObservableList excludedStaffDay3=  excludeThreeList.getSelectionModel().getSelectedItems();
	     	ObservableList excludedStaffDay4=  excludeFourList.getSelectionModel().getSelectedItems();
	     	ObservableList excludedStaffDay5=  excludeFiveList.getSelectionModel().getSelectedItems();
	     	
	     	// Find number of ready to use room
	     	roomsNo = room.length - excludedRooms.size();
	     	
	     	// Find slot size 
	     	int slotSize = eventDurationCombo.getSelectionModel().getSelectedIndex();
	     	if(eventDurationCombo.getValue().equals("30 Minute"))
	     		slotSize = 0;
	     	// Find preparation Size 
	     	int prepare = preparationCombo.getSelectionModel().getSelectedIndex();
	     	if(preparationCombo.getValue().equals("30 Minute"))
	     		prepare = 0;
	  
	     	// Find timings for every day
	     	 start1 = startOneCombo.getSelectionModel().getSelectedIndex();
	     		if(startOneCombo.getValue().equals("09:00")) start1 = 0;
	     	 start2 = startTwoCombo.getSelectionModel().getSelectedIndex();
	 			if(startTwoCombo.getValue().equals("09:00")) start2 = 0;
	     	 start3 = startThreeCombo.getSelectionModel().getSelectedIndex();
	     		if(startThreeCombo.getValue().equals("09:00")) start3 = 0;
	     	 start4 = startFourCombo.getSelectionModel().getSelectedIndex();
	     		if(startFourCombo.getValue().equals("09:00")) start4 = 0;
	     	 start5 = startFiveCombo.getSelectionModel().getSelectedIndex();
	     		if(startFiveCombo.getValue().equals("09:00")) start5 = 0;

	         end1 = endOneCombo.getSelectionModel().getSelectedIndex();
	        	if(endOneCombo.getValue().equals("17:00")) end1 = 17;
	     	 end2 = endTwoCombo.getSelectionModel().getSelectedIndex();
	     		if(endTwoCombo.getValue().equals("17:00")) end2 = 17;
	     	 end3 = endThreeCombo.getSelectionModel().getSelectedIndex();
	     		if(endThreeCombo.getValue().equals("17:00")) end3 = 17;
	     	 end4 = endFourCombo.getSelectionModel().getSelectedIndex();
	     		if(endFourCombo.getValue().equals("17:00")) end4 = 17;
	     	 end5 = endFiveCombo.getSelectionModel().getSelectedIndex();
	     		if(endFiveCombo.getValue().equals("17:00")) end5 = 17;
	     		
	     		int breaking = Integer.parseInt(breakCombo.getValue()+"");	
	     		
	     /////////////////////////////////////////////////////////////////////
	    	// Actual Staff Array
	    	String identifiers[]  = new String [allStaff.length];
	     	for (int i = 0; i < allStaff.length; i++)
			{
	     		identifiers[i] = allStaff[i].getSpecialFinder()+"";
			}
	     	claculateRounds();
	    	
	    	// Day one
	    	SchedulingProcess test = new SchedulingProcess(roomsNo, slotSize + 1, prepare + 1 ,  end1 -  start1, identifiers);
	    	// Add Super Visors
	    	for (int i = 0 ; i < allStaff.length ; i++)
	    	{
	    		int frequent1 = Integer.parseInt(associatedStaff[i][1]); 
	    		test.addSV(identifiers[i], frequent1, breaking);
	    	   	for (int j = 0 ; j < allStaff.length ; j++)
	        	{
	        		int frequent2 = Integer.parseInt(associatedStaff[i][2]); 
	        		test.addDM(identifiers[j], frequent2, breaking);
	        	}
	    	}
	    	
	    	
	    	String [][] a;
	    	a = test.getList();
	    	// day 2
	    	SchedulingProcess test2 = new SchedulingProcess(roomsNo,  slotSize + 1, prepare + 1 , end2 - start2 ,identifiers);
	    	// Super Visors
	    	for (int i = 0 ; i < a.length ; i++)
	    	{
	    		int frequent1 = Integer.parseInt(associatedStaff[i][1]);
	    		int tracker = -1;
	    		for (int n = 0 ; n < a.length ; n++)
		    	{
	    		  String temping = a[n][0]+""; 
	    		  temping = temping.charAt(0)+"";
 	    		  String temping2 = associatedStaff[i][3]+"";
		    	  if(temping.equals(temping2))
		    	  {
		    		  tracker = n;
		    		  break;
		    	  }
		    	}
	    		if(tracker != -1)
	    		{
	    		int sv = Integer.parseInt(a[tracker][1]);
	    		if (frequent1 > sv && frequent1 != 0 )
	    		test2.addSV(identifiers[i],  frequent1 - sv, breaking);
	    		}
	        	// Double Markers
	        	for (int j = 0 ; j < a.length ; j++)
	        	{
		    		int tracker2 = -1;
		    		for (int n = 0 ; n < a.length ; n++)
			    	{
		    		  String temping = a[n][0]+"";
		    		  temping = temping.charAt(0)+"";
		    		  String temping2 = associatedStaff[i][3]+"";
			    	  if(temping.equals(temping2))
			    	  {
			    		  tracker2 = n;
			    		  break;
			    	  }
			    	}
		    		if(tracker2 != -1)
		    		{
	        		int frequent2 = Integer.parseInt(associatedStaff[i][2]); 
	        		int dm = Integer.parseInt(a[tracker2][2]);
	        		if (frequent2 > dm && frequent2 != 0)
	        		{
	        		test2.addDM(identifiers[j], frequent2 - dm, breaking);
	        		}
		    		}
	        		
	        	}
	    	}

	    	
	    	
	    	// Day 3
	    
	    	String [][] b;
	    	b = test2.getList();
	    	SchedulingProcess test3 = new SchedulingProcess(roomsNo, slotSize + 1, prepare + 1 , end3 - start3 ,identifiers);
	    	// Super Visors
	    	for (int i = 0 ; i < b.length ; i++)
	    	{
	    		int frequent1 = Integer.parseInt(associatedStaff[i][1]); 
	    		int tracker = -1;
	    		for (int n = 0 ; n < b.length ; n++)
		    	{
	    		  String temping = b[n][0]+"";
	    		  temping = temping.charAt(0)+"";
	    		  String temping2 = associatedStaff[i][3]+"";
		    	  if(temping.equals(temping2))
		    	  {
		    		  tracker = n;
		    		  break;
		    	  }
		    	}
	    		if(tracker != -1)
	    		{
	    		int sv = Integer.parseInt(b[i][1]);
	    		int exsv = Integer.parseInt(a[i][1]);
	    		if(frequent1 > (sv + exsv)  && frequent1 != 0)
	    		test3.addSV(identifiers[i],  frequent1 - (sv + exsv), breaking);
	    		}
	        	// Double Markers
	        	for (int j = 0 ; j < b.length ; j++)
	        	{
		    		int tracker2 = -1;
		    		for (int n = 0 ; n < b.length ; n++)
			    	{
		    		  String temping = b[n][0]+"";
		    		  temping = temping.charAt(0)+"";
		    		  String temping2 = associatedStaff[i][3]+"";
			    	  if(temping.equals(temping2))
			    	  {
			    		  tracker2 = n;
			    		  break;
			    	  }
			    	}
		    		if(tracker2 != -1)
		    		{
	        		int frequent2 = Integer.parseInt(associatedStaff[i][2]); 
	        		int dm = Integer.parseInt(b[j][2]);
	        		int exdm = Integer.parseInt(a[j][2]);
	        		if(frequent2 > (dm + exdm) && frequent2 != 0)
	        		test3.addDM(identifiers[j],  frequent2 - (dm + exdm), breaking);
		    		}
	        	}
	    	}
	    	
	    	
	    	
	    	
	    	// Day 4
	    
	    	String [][] c;
	    	c = test3.getList();
	    	SchedulingProcess test4 = new SchedulingProcess(roomsNo,  slotSize + 1, prepare + 1, end4 - start4 ,identifiers);
	    	// Super Visors
	    	for (int i = 0 ; i < c.length ; i++)
	    	{
	    		int frequent1 = Integer.parseInt(associatedStaff[i][1]); 
	    		int tracker = -1;
	    		for (int n = 0 ; n < c.length ; n++)
		    	{
	    		  String temping = c[n][0]+"";
	    		  temping = temping.charAt(0)+"";
	    		  String temping2 = associatedStaff[i][3]+"";
		    	  if(temping.equals(temping2))
		    	  {
		    		  tracker = n;
		    		  break;
		    	  }
		    	}
	    		if(tracker != -1)
	    		{
	    		int sv = Integer.parseInt(c[i][1]);
	    		int exsv = Integer.parseInt(b[i][1]);
	    		int exxsv = Integer.parseInt(a[i][1]);
	    		if(frequent1 > (sv + exsv + exxsv)  && frequent1 != 0)
	    		test4.addSV(identifiers[i],  frequent1 - (sv + exsv + exxsv), breaking);
	    		}
	        	// Double Markers
	        	for (int j = 0 ; j < c.length ; j++)
	        	{
		    		int tracker2 = -1;
		    		for (int n = 0 ; n < c.length ; n++)
			    	{
		    		  String temping = c[n][0]+"";
		    		  temping = temping.charAt(0)+"";
		    		  String temping2 = associatedStaff[i][3]+"";
			    	  if(temping.equals(temping2))
			    	  {
			    		  tracker2 = n;
			    		  break;
			    	  }
			    	}
		    		if(tracker2 != -1)
		    		{
	        		int frequent2 = Integer.parseInt(associatedStaff[i][2]); 
	        		int dm = Integer.parseInt(c[j][2]);
	        		int exdm = Integer.parseInt(b[j][2]);
	        		int exxdm = Integer.parseInt(a[j][2]);
	        		if(frequent2 > (dm + exdm + exxdm) && frequent2 != 0)
	        		test4.addDM(identifiers[j], frequent2 - (dm + exdm + exxdm), breaking);
		    		}
	        	}
	    	}
	    	
	    	
	    	// Day 5
	        
	    	String [][] d;
	    	d = test4.getList();
	    	SchedulingProcess test5 = new SchedulingProcess(roomsNo,  slotSize + 1, prepare + 1,  end5 - start5,identifiers);
	    	// Super Visors
	    	for (int i = 0 ; i < d.length ; i++)
	    	{
	    		int frequent1 = Integer.parseInt(associatedStaff[i][1]); 
	    		int tracker = -1;
	    		for (int n = 0 ; n < d.length ; n++)
		    	{
	    		  String temping = d[n][0]+"";
	    		  temping = temping.charAt(0)+"";
	    		  String temping2 = associatedStaff[i][3]+"";
		    	  if(temping.equals(temping2))
		    	  {
		    		  tracker = n;
		    		  break;
		    	  }
		    	}
	    		if(tracker != -1)
	    		{
	    		int sv = Integer.parseInt(d[i][1]);
	    		int exsv = Integer.parseInt(c[i][1]);
	    		int exxsv = Integer.parseInt(b[i][1]);
	    		int exxxsv = Integer.parseInt(a[i][1]);
	    		if(frequent1 > (sv + exsv + exxsv + exxxsv)  && frequent1 != 0)
	    		test5.addSV(identifiers[i],  frequent1 - (sv + exsv + exxsv + exxxsv), breaking);
	    		}
	        	// Double Markers
	        	for (int j = 0 ; j < d.length ; j++)
	        	{
		    		int tracker2 = -1;
		    		for (int n = 0 ; n < d.length ; n++)
			    	{
		    		  String temping = d[n][0]+"";
		    		  temping = temping.charAt(0)+"";
		    		  String temping2 = associatedStaff[i][3]+"";
			    	  if(temping.equals(temping2))
			    	  {
			    		  tracker2 = n;
			    		  break;
			    	  }
			    	}
		    		if(tracker2 != -1)
		    		{
	        		int frequent2 = Integer.parseInt(associatedStaff[i][2]); 
	        		int dm = Integer.parseInt(d[j][2]);
	        		int exdm = Integer.parseInt(c[j][2]);
	        		int exxdm = Integer.parseInt(b[j][2]);
	        		int exxxdm = Integer.parseInt(a[j][2]);
	        		if(frequent2 > (dm + exdm + exxdm + exxxdm) && frequent2 != 0)
	        		test5.addDM(identifiers[j], frequent2 - (dm + exdm + exxdm + exxxdm), breaking);
		    		}
	        	}
	    	}
	    	
	    	/*
	    	// Printing
	    	
	    	for(int i = 0 ; i < allStaff.length ; i++)
			{
				for(int j = 0 ; j < 4 ; j++)
			     System.out.print(associatedStaff[i][j] + "   ");
				System.out.println();
			}
	    	
	    	String schema[][] = test.finalSchema();
			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
					System.out.print(schema[i][j] + "  ");
				}
				System.out.println();
			}
	    	for(int i = 0 ; i < allStaff.length ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
			     System.out.print(a[i][j] + "   ");
				System.out.println();
			}
	    	
	    	/////////////////
	    	 schema = test2.finalSchema();
			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
					System.out.print(schema[i][j] + "  ");
				}
				System.out.println();
			}
	    	for(int i = 0 ; i < b.length ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
			     System.out.print(b[i][j] + "   ");
				System.out.println();
			}
	    	//////////////////////////
	    	
	    	 schema = test3.finalSchema();
			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
					System.out.print(schema[i][j] + "  ");
				}
				System.out.println();
			}
	    	for(int i = 0 ; i < c.length ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
			     System.out.print(c[i][j] + "   ");
				System.out.println();
			}
	    	////////////////////////////
	    	
	    	 schema = test4.finalSchema();
			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
					System.out.print(schema[i][j] + "  ");
				}
				System.out.println();
			}
	    	for(int i = 0 ; i < d.length ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
			     System.out.print(d[i][j] + "   ");
				System.out.println();
			}
			
			*/
	    	
	    	System.out.println(" ---  Allocating Supervisors and Double Markrs for the first day --- ");
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	
	    	test.print();
			String schema[][] = test.finalSchema();
		
			
	    	System.out.println(" ---  Add Students to reserved rooms, Super visors and their alocated Double markers --- ");
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	
	    	String [][] studentsSchema1 = new String [roomsNo][end1 - start1];
 			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
				String x = schema[i][j];
				 if (!x.equals("-"))	
				 {
				  x = x.charAt(1)+"";
				  if(x.equals("$"))
			      for (int m = 0; m < students.length; m++)
			      { 
			    	  String y = schema[i][j];
			    	  y = y.charAt(0) + "";
			    	  System.out.println(y);
			    	  String name = "";
				    	for (int l = 0; l < allStaff.length; l++)
				    	{
				    	  if(associatedStaff[l][3].equals(y))
				    		  name = associatedStaff[l][0];
				    	}
			    	  if(students[m].getSuperVisor().equals(name))
			    	  {
			    		 studentsSchema1 [i][j] = students[m].getId() + " ";
			    		 break;
			    	  }
			      }
				}
				 else
				 {
					 studentsSchema1 [i][j] = "-" ;
				 }
			  }
			}
 			
 			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
				  System.out.print(studentsSchema1[i][j] + "  ");
				}
				System.out.println();
			} 			
			
 		//	writeToExcel x = new writeToExcel(schema, studentsSchema1, room, allStaff , start1, end1);
		// Print the Second day 
 			
	    	System.out.println(" ---  Allocating Supervisors and Double Markrs for the second day --- ");
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
 			
	    	
	    	test2.print();
	    	schema = test2.finalSchema();
			
			
	    	System.out.println(" ---  Add Students to reserved rooms, Super visors and their alocated Double markers --- ");
	    	System.out.println();
	    	System.out.println();
	    	System.out.println();
	    	
	    	String [][] studentsSchema2 = new String [roomsNo][end2 - start2];
 			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
				String x = schema[i][j];
				 if (!x.equals("-"))	
				 {
				  x = x.charAt(1)+"";
				  if(x.equals("$"))
			      for (int m = 0; m < students.length; m++)
			      { 
			    	  String y = schema[i][j];
			    	  y = y.charAt(0) + "";
			    	  System.out.println(y);
			    	  String name = "";
				    	for (int l = 0; l < allStaff.length; l++)
				    	{
				    	  if(associatedStaff[l][3].equals(y))
				    		  name = associatedStaff[l][0];
				    	}
			    	  if(students[m].getSuperVisor().equals(name))
			    	  {
			    		 studentsSchema2 [i][j] = students[m].getId() + " ";
			    		 break;
			    	  }
			      }
				}
				 else
				 {
					 studentsSchema2 [i][j] = "-" ;
				 }
			  }
			}
 			
 			for(int i = 0 ; i < schema.length ; i++)
			{
				for (int j = 0 ; j < schema[0].length; j++)
				{
				  System.out.print(studentsSchema2[i][j] + "  ");
				}
				System.out.println();
			}
 		
			
	    }
	    
	    private String associatedStaff [][] ;
	    public void claculateRounds ()
	    {
	    	// Count each staff's students as SV
	    	associatedStaff = new String [allStaff.length][4];
	    	for (int i = 0; i < allStaff.length; i++)
	    	{
	    		associatedStaff[i][0] = allStaff[i].getId();
	    		int counter = 0 ;
	    	      for (int j = 0; j < students.length; j++)
	    	      {  
	    	    	 if( students[j].getSuperVisor().equals(allStaff[i].getId()))
	    	    		 counter++;
	    	      }
	    	      associatedStaff[i][1] = counter+"";	 
	    	      associatedStaff[i][2] = "0";
	    	}
	    	
	    	for (int i = 0; i < allStaff.length; i++)
	    	{
	    		associatedStaff[i][3] = allStaff[i].getSpecialFinder()+"";
	    	}
	    	
	    	// Count part time active staff
	    	int partTimeStaff = 0 ;
	    	for (int i = 0; i < allStaff.length; i++)
	    	{
	    		if(allStaff[i].getJobType().equals("Part Time") && !excludedDM.contains(allStaff[i].getId()))
	    			partTimeStaff++; 
	    	}
	    	// Count Full time Active staff
	    	int fullTimeStaff = 0 ;
	    	for (int i = 0; i < allStaff.length; i++)
	    	{
	    		if(allStaff[i].getJobType().equals("Full Time") && !excludedDM.contains(allStaff[i].getId()))
	    			fullTimeStaff++; 
	    	}
	    	int allStaffPositions = (int) (fullTimeStaff + Math.ceil(partTimeStaff/2));
	    	// Count included Student
	    	int activeStudents = students.length - excludedStudents.size();
	    	
	    	int avarageNo = activeStudents / allStaffPositions ;
	    	
	    	int i = 0;
	    	while (activeStudents > 0)
	    	{
	    		if (i == allStaff.length)
	    			i = 0;
	    		if(!excludedDM.contains(allStaff[i].getId()))
	    		{
	    			if(allStaff[i].getJobType().equals("Part Time"))
	    			{	
	    			  if(i % 2 == 0)	
	    			  {
	    			    int current = Integer.parseInt(associatedStaff[i][2] );
	    	            associatedStaff[i][2] = current + 1 +"";
	    	            activeStudents --;
	    			  }
	    			}
	    			else
	    			{
	    			    int current = Integer.parseInt(associatedStaff[i][2] );
	    	            associatedStaff[i][2] = current + 1 +"";
	    	            activeStudents --;
	    			}
	    		}
	    		i++;
	    	}	
	    }	
	}
