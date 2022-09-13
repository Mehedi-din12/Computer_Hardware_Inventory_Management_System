package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Classes.Admin;

import java.time.LocalDate;

public class AdminDetailViewController {
  
  @FXML
  private ImageView profileImageView;
  
  @FXML
  private TextField fullNameTextField;
  
  @FXML
  private TextField contactNoTextField;
  
  @FXML
  private TextField userNameTextField;
  
  @FXML
  private TextField passwordTextField;
  
  @FXML
  private TextField joiningDateTextField;
  
  @FXML
  private Button closeButton;
  
  private void resetUi() {
    this.fullNameTextField.setText("");
    this.userNameTextField.setText("");
    this.contactNoTextField.setText("");
    this.passwordTextField.setText("");
    this.joiningDateTextField.setText(String.valueOf(""));
    this.profileImageView.setImage(null);
  }
  
  @FXML
  void handleCloseButton(ActionEvent event) {
    this.resetUi();
    this.switchToAdminPortal();
    
  }
  
  private void switchToAdminPortal(){
    try {
      FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXML/AdminPortal.fxml"));
      Pane root=(Pane) fxmlLoader.load();
      
      Scene adminPortalScene=new Scene(root);
      Stage adminPortalStage=(Stage) this.closeButton.getScene().getWindow();
      adminPortalStage.setScene(adminPortalScene);
      adminPortalStage.setTitle("Admin Portal");
      adminPortalStage.show();
    }catch (Exception exception){
      System.out.println(exception.getMessage());
    }
  }
  
  public void transferAdminObject(Admin admin){
    String fullName=admin.getFullName();
    String contactNo=admin.getContactNo();
    String userName=admin.getUserName();
    String password=admin.getPassword();
    LocalDate localDate=admin.getJoiningDate();
    String photoPath="file://"+admin.getPathToAdminPhoto();
    
    this.fullNameTextField.setText(fullName);
    this.contactNoTextField.setText(contactNo);
    this.userNameTextField.setText(userName);
    this.passwordTextField.setText(password);
    this.joiningDateTextField.setText(String.valueOf(localDate));
    this.profileImageView.setImage(new Image(photoPath));
    
  }
  
}