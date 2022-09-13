package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtilities {

  public static void showErrorMessageDialouge(String error, Stage primaryStage)  {
   try {
     FXMLLoader fxmlLoader=new FXMLLoader(ViewUtilities.class.getResource("FXML/ErrorWindow.fxml"));
     Pane root=(Pane)fxmlLoader.load();
     ErrorWindowController errorWindowController=fxmlLoader.getController();
     errorWindowController.setShowErrorlabel(error);
     Scene errorViewScene=new Scene(root);
     Stage errorStage=new Stage();
     errorStage.setScene(errorViewScene);
     errorStage.initOwner(primaryStage);
     errorStage.initModality(Modality.APPLICATION_MODAL);
     errorStage.setTitle("Error");
     errorStage.showAndWait();
   }catch (Exception exception){
     exception.printStackTrace();
   }
   
  }
  
  
}
