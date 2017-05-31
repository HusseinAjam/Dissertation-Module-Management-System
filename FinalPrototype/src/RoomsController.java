import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
public class RoomsController {
	
    @FXML
    private TextField roomNameText;

    @FXML
    private TextArea detailsText;
    
    @FXML
    private ListView roomsList;

    @FXML
    private Button addRoom;

    @FXML
    private Button deleteRoom;

    @FXML
    private Button updateRoom;
	
    @FXML
    private Button saveCanvasButton;

    @FXML
    private Pane subPane;

    @FXML
    private Group root;
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private ArrayList<StackPane> stack = new ArrayList<StackPane>();
    ObservableList<String> RoomOL = FXCollections.observableArrayList();

    private Rooms [] room;
    
    public void initialize() throws IOException, ClassNotFoundException{
    	
     	roomsList.setItems(RoomOL); 	

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
	    	 stack.add(new StackPane());
	    	 stack.get(i).setTranslateX(room[i].getRoomLocationX());
	      	 stack.get(i).setTranslateY(room[i].getRoomLocationY());
	      	 
	       if(room[i].getRoomState())
	       {
	    	RoomOL.add(room[i].getRoomName());
	      	String text = room[i].getRoomName()+"\n"+room[i].getRoomInfo();
	     	createRoomImage(stack.get(i), text);
	       }
	      }
        }
    
    
    public void createRoomImage(StackPane container, String texting)
    {
        Circle object;
        Text text = new Text();
        text.setFont(new Font(12));
        text.setText(texting);
	    ImageView imageView = new ImageView();
	    Image image2 = new Image(RoomsController.class.getResourceAsStream("room.png"));
	    imageView.setImage(image2);
	    
	   // container = new StackPane();
        object = new Circle(50.0f, Color.RED);
        object.setCursor(Cursor.HAND);
        container.getChildren().addAll(object , imageView, text); 
        container.setOnMousePressed(circleOnMousePressedEventHandler);
        container.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        root.getChildren().add(container); 
    }
    EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
            new EventHandler<MouseEvent>() {
     
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((StackPane)(t.getSource())).getTranslateX();
                orgTranslateY = ((StackPane)(t.getSource())).getTranslateY();
            }
        };
         
        EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
            new EventHandler<MouseEvent>() {
     
            @Override
            public void handle(MouseEvent t) {
                double offsetX = t.getSceneX() - orgSceneX;   	
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                if(newTranslateX < -133)
                	newTranslateX = -133;
                if(newTranslateX > 590)
                	newTranslateX = 590;
                double newTranslateY = orgTranslateY + offsetY;
                if(newTranslateY > 360)
                	newTranslateY = 360;
                if(newTranslateY < -150)
                	newTranslateY = -150;
                ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
                ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
            }
        };
        
        @FXML
        void saveCanvasAction(ActionEvent event) throws IOException, ClassNotFoundException {
        	System.out.println(stack.get(1).getTranslateX() + " : "+ stack.get(1).getTranslateY());
        	  
    	      for (int i = 0; i < 20; i++)
    	      {
    	    	  if(stack.get(i).getTranslateX() != 0 && stack.get(i).getTranslateY() != 0)
    	    	  {
    	    	  room[i].setRoomLocationX(stack.get(i).getTranslateX());
    	    	  room[i].setRoomLocationY(stack.get(i).getTranslateY());
    	    	  }
    	      }
    	      
 		     FileOutputStream saveStream = new FileOutputStream("Storage/Rooms.dat");
 		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
 		     for (int i = 0; i < 20; i++)
 		       {
 		    	  savingObjects.writeObject(room[i]);
 		       }  
 	 	     savingObjects.close();
    	      
        	/*
        	Rooms [] r = new Rooms[20];
        	for(int i = 0 ; i < 20 ; i++)
        	{
        		r[i]= new Rooms(i, "Unassigned", false, 0,0, "No Info");
        	}
        	
		     FileOutputStream saveStream = new FileOutputStream("Storage/Rooms.dat");
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < 20; i++)
		       {
		    	  savingObjects.writeObject(r[i]);
		       }  
	 	      savingObjects.close();
	 	      */
        }
        
        private String targetRoomId;
        @FXML
        void listAction(MouseEvent event)  throws IOException, ClassNotFoundException {
    	      targetRoomId = roomsList.getSelectionModel().getSelectedItem()+"";
     	      //Check the selected room details
    	       int Index = -1;
    		     for (int i = 0; i < 20; i++)
    		       {
    		    	 if(room[i].getRoomName().equals(targetRoomId) && room[i].getRoomState())
    		    	 {
    		    		 Index = i;
    		    		 break;
    		    	 }
    		       }
    		     
    		     if(Index > -1)
    		     {
	    		    roomNameText.setText(room[Index].getRoomName());
	    		    detailsText.setText(room[Index].getRoomInfo());
    		     }
        }
        
        @FXML
        void addAction(ActionEvent event) throws IOException {
        	
		     for (int i = 0; i < 20; i++)
		       {
		    	 if(!room[i].getRoomState())
		    	 {
		    		 room[i].setRoomInfo(detailsText.getText());
		    		 room[i].setRoomName(roomNameText.getText());
		    		 room[i].setRoomState(true);
		    		 break;
		    	 }
		       }  
		     
		     FileOutputStream saveStream = new FileOutputStream("Storage/Rooms.dat");
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < 20; i++)
		       {
		    	  savingObjects.writeObject(room[i]);
		       }  
	 	     savingObjects.close();
        }

        @FXML
        void deleteAction(ActionEvent event) throws IOException {
		     for (int i = 0; i < 20; i++)
		       {
		    	 if(room[i].getRoomName().equals(targetRoomId))
		    	 {
		    		 room[i].setRoomState(false);
		    	 }
		       }  
		     
		     FileOutputStream saveStream = new FileOutputStream("Storage/Rooms.dat");
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < 20; i++)
		       {
		    	  savingObjects.writeObject(room[i]);
		       }  
	 	     savingObjects.close();
        }

        @FXML
        void updateAction(ActionEvent event) throws IOException {
		     for (int i = 0; i < 20; i++)
		       {
		    	 if(room[i].getRoomName().equals(targetRoomId))
		    	 {
		    		 room[i].setRoomInfo(detailsText.getText());
		    		 room[i].setRoomName(roomNameText.getText());
		    		 break;
 		    	 }
		       }  
		     
		     FileOutputStream saveStream = new FileOutputStream("Storage/Rooms.dat");
		     ObjectOutputStream savingObjects =  new ObjectOutputStream(saveStream);
		     for (int i = 0; i < 20; i++)
		       {
		    	  savingObjects.writeObject(room[i]);
		       }  
	 	     savingObjects.close();
        }
}
