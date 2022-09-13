package sample;

import AdminUtilities.AdminDataValidation;
import AdminUtilities.AdminSerializer;
import Classes.Products;
import Functionalities.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.Admin;
import products_utilities.ProductsSerializer;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminPortalController {
  
  @FXML
  private ListView<Admin> listView;
  
  @FXML
  private Button viewDetailsButton;
  
  @FXML
  private Button editButton;
  
  @FXML
  private Button deleteButton;
  
  @FXML
  private TextField fullNameTextField;
  
  @FXML
  private TextField userNameTextField;
  
  @FXML
  private TextField contactNoTextField;
  
  @FXML
  private TextField passwordTextField;
  
  @FXML
  private DatePicker joininigDatePicker;
  
  @FXML
  private Button selectProfilePhotoButton;
  
  @FXML
  private ImageView adminImageView;
  
  @FXML
  private Button addButton;
  
  @FXML
  private Button backButton;
  
  @FXML
  private ComboBox<String> deleteComboBox;
  
  @FXML
  private Button clearForm;
  @FXML
  private ComboBox<String> navigationComboBox;
  
  @FXML
  private Button goButton;
  
  private String photoPath;
  private ArrayList<Admin> adminArrayList=null;
  private ObservableList<Admin> adminObservableList=null;
  private int indexOfSelectedAdmin=-1;
  private int indexOfDeleteItem;
  private final String singleDelete="Delete One Admin";
  private final String allDelete="Delete All";
  
  @FXML
  void handleGoButtonClick(ActionEvent event) {
    if (this.navigationComboBox.getValue() != null) {
      switch (this.navigationComboBox.getValue()) {
      
        case "Opening Window" -> Functions.openOpeningWindow(this.goButton, this.getClass());
        case "Second Window" -> Functions.openSecondWindow(this.goButton, this.getClass());
        case "Current Inventory Window" -> Functions.currentInventoryWindow(this.goButton, this.getClass());
       // case "Admin Portal" -> Functions.adminPortalWindow(this.goButton, this.getClass());
        case "Shipping Window" -> Functions.openShippingWindow(this.goButton, this.getClass());
        case "Purchase Window"->Functions.openPurchaseWindow(this.goButton,this.getClass());
      }
    }
  }
  
  @FXML
  void handleAddButton(ActionEvent event) {
    this.indexOfSelectedAdmin=this.listView.getSelectionModel().getSelectedIndex();
    if(this.indexOfSelectedAdmin!=-1){
      String sampleFullName =this.fullNameTextField.getText();
      String sampleUserName =this.adminArrayList.get(indexOfSelectedAdmin).getUserName();
      String sampleContactNo=this.adminArrayList.get(indexOfSelectedAdmin).getContactNo();
      String samplePassword =this.passwordTextField.getText();
      LocalDate sampleJoiningDate = this.joininigDatePicker.getValue();
      String samplePicPath =this.photoPath;
      
      try {
        boolean boolValue= AdminDataValidation.isAdminValidData(sampleFullName,
          sampleContactNo,sampleUserName,
          samplePassword,sampleJoiningDate,samplePicPath);
        
        Admin admin=new Admin(sampleFullName,
          sampleContactNo,sampleUserName,
          samplePassword,samplePicPath,sampleJoiningDate);
  
        this.adminArrayList.set(this.indexOfSelectedAdmin,admin);
        boolean boolFile=AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
        if(!boolFile){
          throw new Exception("File could not save");
        }
        this.adminObservableList.set(this.indexOfSelectedAdmin,admin);
        this.listView.refresh();
        this.resetUi();
        
      }catch (Exception exception){
        Stage primaryStage=(Stage) this.addButton.getScene().getWindow();
        ViewUtilities.showErrorMessageDialouge(exception.getMessage(),primaryStage);
      }
    }else{
      String sampleFullName =this.fullNameTextField.getText();
      String sampleUserName =this.userNameTextField.getText();
      String sampleContactNo=this.contactNoTextField.getText();
      String samplePassword =this.passwordTextField.getText();
      LocalDate sampleJoiningDate = this.joininigDatePicker.getValue();
      String samplePicPath =this.photoPath;
      
      try {
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
          this.adminObservableList= FXCollections.observableList(adminArrayList);
          this.listView.setItems(adminObservableList);
          resetUi();
        }
      }catch (Exception exception){
        Stage primaryStage=(Stage) this.addButton.getScene().getWindow();
        ViewUtilities.showErrorMessageDialouge(exception.getMessage(),primaryStage);
      }
    }
//    String sampleFullName =this.fullNameTextField.getText();
//    String sampleUserName =this.userNameTextField.getText();
//    String sampleContactNo=this.contactNoTextField.getText();
//    String samplePassword =this.passwordTextField.getText();
//    LocalDate sampleJoiningDate = this.joininigDatePicker.getValue();
//    String samplePicPath =this.photoPath;
//
//    try{
//      boolean boolValue= AdminDataValidation.isAdminValidData(sampleFullName,
//        sampleContactNo,sampleUserName,
//        samplePassword,sampleJoiningDate,samplePicPath);
//      Admin admin=new Admin(sampleFullName,
//        sampleContactNo,sampleUserName,
//        samplePassword,samplePicPath,sampleJoiningDate);
//      if(this.indexOfSelectedAdmin!=-1){
//        this.adminArrayList.set(this.indexOfSelectedAdmin,admin);
//        boolean boolFile=AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
//        if(!boolFile){
//          throw new Exception("File could not save");
//        }
//        this.adminObservableList.set(this.indexOfSelectedAdmin,admin);
//        this.listView.refresh();
//        //*********************************************************
////        this.adminArrayList.set(this.indexOfSelectedAdmin,admin);
////        this.adminObservableList.set(this.indexOfSelectedAdmin,admin);
////        this.listView.refresh();
//
//      }else{
//        if(searchForMatches(admin.getUserName(),admin.getContactNo())){
//          this.adminArrayList.add(admin);
//          boolean boolFile= AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
//          if(!boolFile){
//            this.adminArrayList.remove(this.adminArrayList.size()-1);
//            throw new Exception("File could not save");
//          }
//          this.adminObservableList= FXCollections.observableList(adminArrayList);
//          this.listView.setItems(adminObservableList);
//          resetUi();
//        }
//      }
//    }catch (Exception exception){
//      Stage primaryStage=(Stage) this.addButton.getScene().getWindow();
//      ViewUtilities.showErrorMessageDialouge(exception.getMessage(),primaryStage);
//    }
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
    this.userNameTextField.setText("");
    this.contactNoTextField.setText("");
    this.passwordTextField.setText("");
    this.joininigDatePicker.setValue(null);
    this.adminImageView.setImage(null);
  }
  
  @FXML
  void handleBackButton(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXML/SecondWindow.fxml"));
      Pane root=(Pane) fxmlLoader.load();
      
      Scene backButtonScene = new Scene(root);
      Stage backButtonStage= (Stage) this.backButton.getScene().getWindow();
      backButtonStage.setScene(backButtonScene);
      backButtonStage.setTitle("Inventory Management System Home Page");
      backButtonStage.show();
      
    } catch (Exception exception) {
      System.err.println(exception.getMessage());
    }
  }
  
  @FXML
  void handleDeleteButton(ActionEvent event) {
    if(this.deleteComboBox.getValue()==null){
      Alert alert=new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Select option from delete button");
      alert.showAndWait();
    }else if(this.deleteComboBox.getValue().equals(this.allDelete)){
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("All clear");
      alert.showAndWait();
      this.adminArrayList.clear();
      
      try {
        boolean boolFile= AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
        if(!boolFile){
          throw new Exception("File could not save");
        }
        resetUi();//rather use clear button
      }catch (Exception exception){
        System.out.println(exception.getMessage());
      }
      
      ArrayList<Products> productsArrayList=new ArrayList<>();
      boolean boolInventory= ProductsSerializer.serialization(ProductsSerializer.productDataBasePath,productsArrayList);
      boolean boolShip= ProductsSerializer.serialization(ProductsSerializer.shippedProductDataBasePath,productsArrayList);
      boolean boolPurchase= ProductsSerializer.serialization(ProductsSerializer.purchasedProductDataBasePath,productsArrayList);
      this.loadOpeningWindow();
      
    }else{
      System.out.println("one clear");
      this.indexOfSelectedAdmin=this.listView.getSelectionModel().getSelectedIndex();
      if(this.indexOfSelectedAdmin!=-1){
        this.adminArrayList.remove(this.indexOfSelectedAdmin);
        try {
          boolean boolFile= AdminSerializer.serialization(AdminSerializer.adminDataBasePath,adminArrayList);
          if(!boolFile){
            throw new Exception("File could not save");
          }
          resetUi();
        }catch (Exception exception){
          System.out.println(exception.getMessage());
        }
        this.indexOfSelectedAdmin=-1;
        int adminSize=adminArrayList.size();
        if(!(adminSize>0)){
          
          ArrayList<Products> productsArrayList=new ArrayList<>();
          boolean boolInventory= ProductsSerializer.serialization(ProductsSerializer.productDataBasePath,productsArrayList);
          boolean boolShip= ProductsSerializer.serialization(ProductsSerializer.shippedProductDataBasePath,productsArrayList);
          boolean boolPurchase= ProductsSerializer.serialization(ProductsSerializer.purchasedProductDataBasePath,productsArrayList);
          this.loadOpeningWindow();
        }
        this.initialize();
      }else{
        System.out.println("No person selected");
      }
      
    }
  }
  
  @FXML
  void handleEditButton(ActionEvent event) {
    this.indexOfSelectedAdmin=this.listView.getSelectionModel().getSelectedIndex();
    if(this.indexOfSelectedAdmin!=-1){
      Admin savedAdmin=this.listView.getItems().get(this.indexOfSelectedAdmin);
      
      String fullName=savedAdmin.getFullName();
      String contactNo=savedAdmin.getContactNo();
      String userName=savedAdmin.getUserName();
      String password=savedAdmin.getPassword();
      LocalDate localDate=savedAdmin.getJoiningDate();
      this.photoPath=savedAdmin.getPathToAdminPhoto();
      
      this.updateAdmin(fullName,contactNo,userName,password,localDate);
      
    }
  }
  
  private void updateAdmin(String fullName,String contactNo, String userName,String password,LocalDate localDate){
    this.fullNameTextField.setText(fullName);
    this.contactNoTextField.setText(contactNo);
    this.userNameTextField.setText(userName);
    this.passwordTextField.setText(password);
    this.joininigDatePicker.setValue(localDate);
    this.updateProfileViewNode();
  }
  
  @FXML
  void handleListViewMouseClicked(MouseEvent event) {
  
  }
  
  private void updateProfileViewNode(){
    Image profileImage = new Image("file://" + this.photoPath);
    this.adminImageView.setImage(profileImage);
  }
  
  @FXML
  void handleSelectProfilePhotoButton(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.selectProfilePhotoButton.getScene().getWindow();
    
    File fileSelected = fileChooser.showOpenDialog(primaryStage);
    
    if (fileSelected != null) {
      String selectedFilePath = fileSelected.toURI().getPath();
      this.photoPath = selectedFilePath;
      this.updateProfileViewNode();
    }
  }
  
  @FXML
  void handleViewDetailsButton(ActionEvent event) {
    this.indexOfSelectedAdmin=this.listView.getSelectionModel().getSelectedIndex();
    
    if(this.indexOfSelectedAdmin!=-1){
      Admin admin=this.listView.getItems().get(this.indexOfSelectedAdmin);
      try {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXML/AdminDetailView.fxml"));
        Pane root=(Pane) fxmlLoader.load();
        
        AdminDetailViewController adminDetailViewController=fxmlLoader.getController();
        adminDetailViewController.transferAdminObject(admin);
        
        Scene detailViewScene=new Scene(root);
        Stage detailViewStage=(Stage) this.viewDetailsButton.getScene().getWindow();
        detailViewStage.setScene(detailViewScene);
        detailViewStage.setTitle("Admin detail view for: "+admin.getFullName());
        detailViewStage.show();
      }catch (Exception exception){
        System.out.println(exception.getMessage());
      }
    }
  }
  
  public void initialize(){
    this.adminArrayList= AdminSerializer.deserialization(AdminSerializer.adminDataBasePath);
    if(this.adminArrayList==null){
      this.adminArrayList=new ArrayList<>();
    }
    this.adminObservableList= FXCollections.observableList(adminArrayList);
    this.listView.setItems(adminObservableList);
    
    deleteOptionMethod();
    initializeNavigationComboBox();
    
  }
  
  private void deleteOptionMethod(){
    ArrayList<String> deleteOptionArraylist= new ArrayList<>();
    deleteOptionArraylist.add(this.singleDelete);
    deleteOptionArraylist.add(this.allDelete);
    
    ObservableList<String> deleteOptionObservableList=FXCollections.observableArrayList(deleteOptionArraylist);
    this.deleteComboBox.setItems(deleteOptionObservableList);
  }
  
  @FXML
  void handleClearForm(ActionEvent event) {
    this.resetUi();
  }
  
  public void loadOpeningWindow(){
    try {
      FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("FXML/OpeningWindow.fxml"));
      Pane root=(Pane) fxmlLoader.load();
      
      Scene backButtonScene = new Scene(root);
      Stage backButtonStage= (Stage) this.backButton.getScene().getWindow();
      backButtonStage.setScene(backButtonScene);
      backButtonStage.setTitle("1st Window");
      backButtonStage.show();
      
    } catch (Exception exception) {
      System.err.println(exception.getMessage());
    }
  }
  void initializeNavigationComboBox() {
    
    
    ArrayList<String> allWindows = new ArrayList<>();
    allWindows.add("Opening Window");
    allWindows.add("Second Window");
    allWindows.add("Current Inventory Window");
    allWindows.add("Shipping Window");
    allWindows.add("Purchase Window");
    
    ObservableList<String> allWindowsList = FXCollections.observableArrayList(allWindows);
    
    this.navigationComboBox.setItems(allWindowsList);
  }
  
}
