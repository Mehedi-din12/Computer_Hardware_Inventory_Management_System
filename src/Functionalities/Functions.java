package Functionalities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Functions {
  
  public static void openOpeningWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/OpeningWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Lambda Computer Hardware Inventory Management System");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
  
  public static void openSecondWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/SecondWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Inventory Management System Home Page");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
  public static void currentInventoryWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/CurrentInventory.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Current Inventory");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
  
  public static void adminPortalWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/AdminPortal.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Admin Portal");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
  public static void openShippingWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/ShippingWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Outgoing Orders");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
  public static void openPurchaseWindow(Button button, Class theClass) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(theClass.getResource("FXML/PurchasingWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = theClass.getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) button.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Incoming Purchase");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
  
}
