//package sample;
//
//
//import Functionalities.Functions;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//public class SecondWindowController {
//
//  @FXML
//  private RadioButton currentInventoryRadioButton;
//
//  @FXML
//  private RadioButton shippingRadioButton;
//
//  @FXML
//  private RadioButton adminPanelRadioButton;
//
//
//
//  @FXML
//  private Button selectButton;
//
//
//  private ToggleGroup choiceToggle = new ToggleGroup();
//
//  @FXML
//  private Button backButton;
//
//  @FXML
//  void handleBackButton(ActionEvent event) {
//
//    Functions.openOpeningWindow(backButton,this.getClass());
//
//  }
//
//
//  @FXML
//  void handleSelectButtonClick(ActionEvent event) {
//    try{
//      int choice = (int) this.choiceToggle.getSelectedToggle().getUserData();
//      chosenMethod(choice);
//    }catch (Exception exception){
//      System.out.println(exception.getMessage());
//    }
//
//
//
//
//  }
//
//
//  public void initialize(){
//
//    this.currentInventoryRadioButton.setToggleGroup(this.choiceToggle);
//    this.currentInventoryRadioButton.setUserData(1);
//    this.shippingRadioButton.setToggleGroup(this.choiceToggle);
//    this.shippingRadioButton.setUserData(2);
//    this.adminPanelRadioButton.setToggleGroup(this.choiceToggle);
//    this.adminPanelRadioButton.setUserData(3);
//
//
//
//  }
//
//
//  public void chosenMethod(int choice){
//    switch (choice){
//      case 1 :
//        loadCurrentInventoryWindow();
//        break;
//
//      case 2:
//        loadShippingWindow();
//        break;
//
//      case 3:
//        loadAdminPanelWindow();
//        break;
//
//    }
//  }
//
//  public void loadAdminPanelWindow(){
//    try {
//      //Parent root = FXMLLoader.load(getClass().getResource("SecondWindow.fxml"));
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AdminPortal.fxml"));
//      Pane root = (Pane) fxmlLoader.load();
//
//      Scene registerButtonScene = new Scene(root);
//
//      Stage registerButtonStage= (Stage) this.selectButton.getScene().getWindow();
//      registerButtonStage.setScene(registerButtonScene);
//      registerButtonStage.setTitle("2nd portal");
//      registerButtonStage.show();
//
//    } catch (Exception exception) {
//      System.out.println(exception);
//    }
//  }
//
//
//  public void loadCurrentInventoryWindow(){
//
//    try{
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CurrentInventory.fxml"));
//      Pane root = (Pane) fxmlLoader.load();
//
//      Scene optionsScene = new Scene(root);
//      String css = this.getClass().getResource("application.css").toExternalForm();
//      optionsScene.getStylesheets().add(css);
//      Stage primaryStage = (Stage) this.selectButton.getScene().getWindow();
//      primaryStage.setScene(optionsScene);
//      primaryStage.setTitle("Current Inventory");
//      primaryStage.show();
//
//    }catch (Exception e){
//      System.out.println(e);
//    }
//
//
//
//  }
//
//
//  public void loadShippingWindow(){
//
//    try{
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShippingWindow.fxml"));
//      Pane root = (Pane) fxmlLoader.load();
//
//      Scene optionsScene = new Scene(root);
//      String css = this.getClass().getResource("application.css").toExternalForm();
//      optionsScene.getStylesheets().add(css);
//      Stage primaryStage = (Stage) this.selectButton.getScene().getWindow();
//      primaryStage.setScene(optionsScene);
//      primaryStage.setTitle("Incoming Purchase");
//      primaryStage.show();
//
//    }catch (Exception e){
//      System.out.println(e);
//    }
//
//  }
//
//
//}
//
//


package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SecondWindowController {
  
  @FXML
  private RadioButton currentInventoryRadioButton;
  
  @FXML
  private RadioButton shippingRadioButton;
  
  @FXML
  private RadioButton adminPanelRadioButton;
  
  @FXML
  private RadioButton purchasingRadioButton;
  
  @FXML
  private Button selectButton;
  
  @FXML
  private Button backButton;
  
  
  private ToggleGroup choiceToggle = new ToggleGroup();
  
  
  @FXML
  void handleBackButton(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXML/OpeningWindow.fxml"));
      Pane root=(Pane) fxmlLoader.load();
      
      Scene backButtonScene = new Scene(root);
      Stage backButtonStage= (Stage) this.backButton.getScene().getWindow();
      backButtonStage.setScene(backButtonScene);
      backButtonStage.setTitle("Lambda Computer Hardware Inventory Management System");
      backButtonStage.show();
      
    } catch (Exception exception) {
      System.err.println(exception.getMessage());
    }
  }
  
  
  @FXML
  void handleSelectButtonClick(ActionEvent event) {
    try{
      int choice = (int) this.choiceToggle.getSelectedToggle().getUserData();
      chosenMethod(choice);
    }catch (Exception exception){
      System.out.println(exception.getMessage());
    }
  }
  
  
  public void initialize(){
    
    this.currentInventoryRadioButton.setToggleGroup(this.choiceToggle);
    this.currentInventoryRadioButton.setUserData(1);
    this.shippingRadioButton.setToggleGroup(this.choiceToggle);
    this.shippingRadioButton.setUserData(2);
    this.purchasingRadioButton.setToggleGroup(this.choiceToggle);
    this.purchasingRadioButton.setUserData(3);
    this.adminPanelRadioButton.setToggleGroup(this.choiceToggle);
    this.adminPanelRadioButton.setUserData(4);
  }
  
  
  public void chosenMethod(int choice){
    switch (choice){
      case 1 :
        loadCurrentInventoryWindow();
        break;
      
      case 2:
        loadShippingWindow();
        break;
      
      case 3:
        loadPurchasingWindow();
        break;
      
      case 4:
        loadAdminPanelWindow();
        break;
      
    }
  }
  
  public void loadAdminPanelWindow(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AdminPortal.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene registerButtonScene = new Scene(root);
      
      Stage registerButtonStage= (Stage) this.selectButton.getScene().getWindow();
      registerButtonStage.setScene(registerButtonScene);
      registerButtonStage.setTitle("Admin Portal");
      registerButtonStage.show();
      
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }
  
  
  public void loadCurrentInventoryWindow(){
    
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CurrentInventory.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.selectButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Current Inventory");
      primaryStage.show();
      
    }catch (Exception e){
      System.out.println(e);
    }
  }
  
  public void loadPurchasingWindow(){
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/PurchasingWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.selectButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Incoming Purchase");
      primaryStage.show();
      
    }catch (Exception e){
      System.out.println(e);
    }
    
  }
  
  public void loadShippingWindow(){
    
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShippingWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.selectButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Outgoing Orders");
      primaryStage.show();
      
    }catch (Exception e){
      System.out.println(e);
    }
    
  }
  
}


