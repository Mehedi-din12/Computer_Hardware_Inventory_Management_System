package sample;

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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import products_utilities.ProductValidator;
import products_utilities.ProductsSerializer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class AddProductController {

  @FXML
  private TextField productNameTextField;

  @FXML
  private TextField productIDTextField;

  @FXML
  private TextField productPriceTextField;

  @FXML
  private TextField productTypeTextField;

  @FXML
  private TextField productBrandTextField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button selectPhotoButton;

  @FXML
  private ImageView choseImageView;

  @FXML
  private TextField productQuantityTextField;

  @FXML
  private Button addProductButton;

  @FXML
  private Button backButton;


  @FXML
  private Button clearAllButton;


  @FXML
  private ComboBox<String> typeComboBox;
  
  private String profilePhotoPath;

  private ArrayList<Products> productsArrayList = null;
  
  @FXML
  void handleClearAllButtonClick(ActionEvent event) {

    try {
      this.productNameTextField.setText("");
      this.productIDTextField.setText("");
      this.typeComboBox.setValue(null);
      this.productQuantityTextField.setText("");
      this.datePicker.setValue(null);
      this.productPriceTextField.setText("");
      this.productBrandTextField.setText("");
      this.choseImageView.setImage(null);
    }catch (Exception e){
      System.out.println(e);
    }


  }


  @FXML
  void handleAddProductButtonClick(ActionEvent event) {

    try {

      String productName = this.productNameTextField.getText();
      String productID = this.productIDTextField.getText();
      String productQuantityString = this.productQuantityTextField.getText();
      String priceString = this.productPriceTextField.getText();
      String type = this.typeComboBox.getValue();
      System.out.println(type);
      String brand = this.productBrandTextField.getText();
      LocalDate date = this.datePicker.getValue();
      String photoPath = this.profilePhotoPath;

      ProductValidator.isValid(productID,
        productName,
        type,
        brand,
        date,photoPath,
        priceString,
        productQuantityString);

      int productQuantity = Integer.parseInt(productQuantityString);
      int price = Integer.parseInt(priceString);

      Products products = new Products(
        productID,
        productName,
        type,
        brand,
        date,
        photoPath,
        price,
        productQuantity
      );
      
      if(searchForMatches(products.getName(),products.getItemID())) {

        this.productsArrayList.add(products);
        resetInterface();
        
        File fileToWrite = new File(ProductsSerializer.productDataBasePath);

        try (
          FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        ) {
          objectOutputStream.writeObject(productsArrayList);

        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

      }

    } catch (Exception e){
      Stage primary=(Stage) this.addProductButton.getScene().getWindow();
      ViewUtilities.showErrorMessageDialouge(e.getMessage(),primary);

    }

  }
  
  public boolean searchForMatches(String productName,String productID){

    for(Products p: productsArrayList){
      String nameOfCurrentProduct = p.getName();
      if(nameOfCurrentProduct.equals(productName)){
        sameNameAlert();
        return false;
      }
    }

    for(Products p: productsArrayList){
      String idOfCurrentProduct = p.getItemID();
      if(idOfCurrentProduct.equals(productID)){
        sameIDAlert();
        return false;
      }
    }

    return true;


  }

  void sameNameAlert(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Product with same name already exists!");
    alert.show();
  }

  void sameIDAlert(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Product with same ID already exists!");
    alert.show();
  }

  @FXML
  void handleSelectPhotoButtonClick(ActionEvent event) {


    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.selectPhotoButton.getScene().getWindow();

    File fileSelected = fileChooser.showOpenDialog(primaryStage);

    if (fileSelected != null) {
      String selectedFilePath = fileSelected.toURI().getPath();
      this.profilePhotoPath = selectedFilePath;
    }

    Image profileImage = new Image("file://" + this.profilePhotoPath);
    this.choseImageView.setImage(profileImage);


  }

  public void initialize() {

    this.productsArrayList = new ArrayList<>();

    File fileToRead = new File(ProductsSerializer.productDataBasePath);
    System.out.println(fileToRead.exists());

    if (fileToRead.exists()) {

      try (
        FileInputStream fileInputStream = new FileInputStream(fileToRead);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ) {
        this.productsArrayList = (ArrayList<Products>) objectInputStream.readObject();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

    }


    System.out.println(productsArrayList);

    ArrayList<String> typeOptions = new ArrayList<>();
    typeOptions.add("Monitor");
    typeOptions.add("Processor");
    typeOptions.add("Ram");
    typeOptions.add("Keyboard");
    typeOptions.add("Mouse");
    typeOptions.add("Motherboard");
    typeOptions.add("CPU Cooler");
    typeOptions.add("Sound Card");
    typeOptions.add("Graphics Card");
    typeOptions.add("Hard Disk");
    typeOptions.add("SSD");
    typeOptions.add("UPS");

    ObservableList<String> typeObservableList = FXCollections.observableArrayList(typeOptions);

    this.typeComboBox.setItems(typeObservableList);
  }


  void resetInterface() {
    this.productNameTextField.setText("");
    this.productNameTextField.setText("");
    this.productIDTextField.setText("");
    this.productQuantityTextField.setText("");
    this.productPriceTextField.setText("");
    this.productBrandTextField.setText("");
    this.datePicker.setValue(null);
    this.profilePhotoPath = null;
    choseImageView.setImage(null);
  }


  @FXML
  void handleBackButtonClick(ActionEvent event) {

    Functions.currentInventoryWindow(backButton,this.getClass());


  }

}




