package sample;

import AdminUtilities.AdminDataValidation;
import AdminUtilities.AdminSerializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.Admin;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateAdminController {
  
  @FXML
  private Button backButton;
  
  @FXML
  private TextField fullNameTextField;
  
  @FXML
  private TextField usernameTextField;
  
  @FXML
  private TextField contactNoTextField;
  
  @FXML
  private TextField passwordTextField;
  
  @FXML
  private DatePicker joiningDateDatePicker;
  
  @FXML
  private Button selectAPhotoButton;
  
  @FXML
  private ImageView adminImageView;
  
  @FXML
  private Button createButton;

  private int indexOfSelectedAdmin=-1;
  private int indexOfDeleteItem;
  private String picPath;
  private ArrayList<Admin> adminArrayList =null;
  private ObservableList<Admin> adminObservableList=null;
  
  @FXML
  void handleCreateButton(ActionEvent event) {
    String sampleFullName =this.fullNameTextField.getText();
    String sampleUserName =this.usernameTextField.getText();
    String sampleContactNo=this.contactNoTextField.getText();
    String samplePassword =this.passwordTextField.getText();
    LocalDate sampleJoiningDate = this.joiningDateDatePicker.getValue();
    String samplePicPath =this.picPath;
    
    try{
      boolean boolValue= AdminDataValidation.isAdminValidData(sampleFullName,
        sampleContactNo,sampleUserName,
        samplePassword,sampleJoiningDate,samplePicPath);
      Admin admin=new Admin(sampleFullName,
        sampleContactNo,sampleUserName,
        samplePassword,samplePicPath,sampleJoiningDate);
      if(searchForMatches(admin.getUserName(),admin.getContactNo())){
        this.adminArrayList.add(admin);
        boolean boolFile= AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
        if(!boolFile){
          this.adminArrayList.remove(this.adminArrayList.size()-1);
          throw new Exception("File could not save");
        }
        resetUi();
      }
    }catch (Exception exception){
      Stage primary=(Stage) this.createButton.getScene().getWindow();
      ViewUtilities.showErrorMessageDialouge(exception.getMessage(),primary);
    }
    
  }
  
  public boolean searchForMatches(String userName,String contactNo){
    
    for(Admin a: adminArrayList){
      String userNameCurrentAdmin = a.getUserName();
      if(userNameCurrentAdmin.equals(userName)){
        sameUserNameAlert();
        return false;
      }
    }
    
    for(Admin a: adminArrayList){
      String contactNoCurrentAdmin = a.getContactNo();
      if(contactNoCurrentAdmin.equals(contactNo)){
        sameContactNoAlert();
        return false;
      }
    }
    return true;
  }
  
  void sameUserNameAlert(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Admin with same user name already exists!");
    alert.show();
  }
  
  void sameContactNoAlert(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Admin with same contact no already exists!");
    alert.show();
  }
  
  
  
  
  private void resetUi() {
    this.fullNameTextField.setText("");
    this.usernameTextField.setText("");
    this.contactNoTextField.setText("");
    this.passwordTextField.setText("");
    this.joiningDateDatePicker.setValue(null);
    this.adminImageView.setImage(null);
  }
  
  
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
  void handleSelectAPhotoButton(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.selectAPhotoButton.getScene().getWindow();
    
    File fileSelected = fileChooser.showOpenDialog(primaryStage);
    
    if (fileSelected != null) {
      String selectedFilePath = fileSelected.toURI().getPath();
      this.picPath = selectedFilePath;
      Image profileImage = new Image("file://" + this.picPath);
      this.adminImageView.setImage(profileImage);
    }
  }
  
  public void initialize() {
    
    File fileToRead = new File(AdminSerializer.adminDataBasePath);
    if (fileToRead.exists()) {
      this.adminArrayList= AdminSerializer.deserialization(AdminSerializer.adminDataBasePath);
      if(this.adminArrayList==null){
        this.adminArrayList=new ArrayList<>();
      }
    }else{
      System.out.println("file not create yet");
    }
    
  }
  
}
