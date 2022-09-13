package sample;

import AdminUtilities.AdminSerializer;
import AdminUtilities.OpeningMenuValidation;
import Classes.OpeningWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Classes.Admin;

import java.io.*;
import java.util.ArrayList;

public class OpeningMenuController {
  
  @FXML
  private TextField userNameTextField;
  
  @FXML
  private PasswordField passwordField;
  
  @FXML
  private Button logInButton;
  
  @FXML
  private Button exitButton;
  
  @FXML
  private CheckBox showPasswordCheckBox;
  
  @FXML
  private Button registerButton;
  
  private ArrayList<Admin> adminArrayListLogInPortal=null;
  
  @FXML
  private GridPane firstWindowGridPane;
  
  Stage stage;
  private boolean bool;
  
  @FXML
  void handleExitButton(ActionEvent event) {
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Exit portal");
    alert.setContentText("Do you want to exit? ");

    if(alert.showAndWait().get()==ButtonType.OK){
      stage=(Stage) firstWindowGridPane.getScene().getWindow();
      System.out.println("Exited successfully");
      stage.close();
    }
  }
  
  @FXML
  void handleLogInButton(ActionEvent event) throws Exception {
    bool=true;
    if (adminArrayListLogInPortal.size() > 0) {
      bool = true;
      for (int i = 0; i < adminArrayListLogInPortal.size(); i++) {
        if (this.adminArrayListLogInPortal.get(i).getUserName().equals(this.userNameTextField.getText()) &&
          this.adminArrayListLogInPortal.get(i).getPassword().equals(this.passwordField.getText())) {
          this.resetUi();
          bool = false;
        
        
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/SecondWindow.fxml"));
          Pane root = (Pane) fxmlLoader.load();
          Scene scene = new Scene(root);
          Stage primaryStage = (Stage) this.logInButton.getScene().getWindow();
          primaryStage.setScene(scene);
          primaryStage.setTitle("Inventory Management System Home Page");
        
          primaryStage.sizeToScene();
          primaryStage.show();
        }
      }
    } if(bool) {
      try {
        
          String username = this.userNameTextField.getText();
          String password = this.passwordField.getText();
          boolean bool = OpeningMenuValidation.isValidOpenMenu(username, password);
          OpeningWindow openingWindow = new OpeningWindow(username, password);
        
    
    
      } catch (Exception exception) {
        Stage primary = (Stage) this.logInButton.getScene().getWindow();
        ViewUtilities.showErrorMessageDialouge(exception.getMessage(), primary);
      }
  
    }
  }
  
  
  
  private void resetUi(){
    this.userNameTextField.setText("");
    this.passwordField.setText("");
  }
  
  @FXML
  void handleShowPasswordCheckBox(ActionEvent event) {
    if(this.showPasswordCheckBox.isSelected()){
      this.passwordField.setPromptText(this.passwordField.getText());
      this.passwordField.setText("");
      this.passwordField.setDisable(true);
    }else{
      this.passwordField.setText(this.passwordField.getPromptText());
      this.passwordField.setPromptText("");
      this.passwordField.setDisable(false);
    }
  }
  
  @FXML
  void handleRegisterButton(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CreateAdmin.fxml"));
      Pane root = (Pane) fxmlLoader.load();
    
      Scene registerButtonScene = new Scene(root);
      Stage registerButtonStage= (Stage) this.registerButton.getScene().getWindow();//---
      registerButtonStage.setScene(registerButtonScene);
      registerButtonStage.setTitle("Create Account Portal");
      registerButtonStage.show();
    
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }
  
  public void initialize(){
    this.adminArrayListLogInPortal=new ArrayList<>();
    File fileToRead = new File(AdminSerializer.adminDataBasePath);
    if (fileToRead.exists()) {
      this.adminArrayListLogInPortal= AdminSerializer.deserialization(AdminSerializer.adminDataBasePath);
      if(this.adminArrayListLogInPortal==null){
        this.adminArrayListLogInPortal=new ArrayList<>();
      }
    }else{
      try(FileOutputStream fileOutputStream = new FileOutputStream(fileToRead);
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
        System.out.println("");
      }catch (Exception exception){
        System.out.println("File not created For Admin DataBase");
      }
    }
  }
}
