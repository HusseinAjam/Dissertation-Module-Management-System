import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class mainController {

    @FXML
    private Pane subPane;

    @FXML
    private Button loadDataButton;
    
    @FXML
    private Button roomsButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button staffButton;

    @FXML
    private Button studentButton;
    
    @FXML
    private Button manageDataButton;
    
    @FXML
    private Button assessmentsButton;
    
    @FXML
    private Button helpButton;
    
    void initialize ()
    {
    
    }
    
    @FXML
    void assessmentsAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("assessmentsDocument.fxml")); 
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
     }

    @FXML
    void loadDataAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("loadingDocument.fxml")); 
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
     }

    @FXML
    void scheduleAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("FinalScheduleDocument.fxml")); 
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void staffAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("staffDocument.fxml")); 
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void studentAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("studentDocument.fxml")); 
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void manageDataAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("manageDocument.fxml"));
    	newLoadedPane.setId("general");
    	newLoadedPane.getStylesheets().addAll(this.getClass().getResource("styling.css").toExternalForm());
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
   }
    @FXML
    void roomsAction(ActionEvent event) throws IOException {
    	Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("roomsDocument.fxml")); 
    	subPane.getChildren().clear();
    	subPane.getChildren().add(newLoadedPane);
   }
    
    @FXML
    void helpAction(ActionEvent event)  {
    	if (Desktop.isDesktopSupported()) {
    	    try {
    	        File myFile = new File("user guide.pdf");
    	        Desktop.getDesktop().open(myFile);
    	    } catch (IOException ex) {
    	        // no application registered for PDFs
    	    }
    	}
   }
}
