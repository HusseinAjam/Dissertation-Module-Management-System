import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class index  extends Application{

	 public void start(Stage stage) throws Exception
	   {  
	      // Load the login FXML file.
	      Parent parent = FXMLLoader.load(
	      getClass().getResource("mainDocument.fxml"));      
	      Scene scene = new Scene(parent); 
	      parent.setId("pane");
	      scene.getStylesheets().addAll(this.getClass().getResource("mainStyle.css").toExternalForm());
	      stage.setTitle("Dissertations Management Tool"); 
	      stage.setScene(scene);
	      stage.show(); 
	   }
	   public static void main(String[] args) throws IOException
	   {
		   launch(args);
	   }
	 } 
