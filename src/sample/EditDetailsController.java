package sample;


import Classes.Products;
import Functionalities.Functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import products_utilities.ProductsSerializer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditDetailsController {

  @FXML
  private ImageView imageOfProductView;

  @FXML
  private TextField inventoryTextField;

  @FXML
  private TextField idTextField;



  @FXML
  private TextField typeTextField;

  @FXML
  private TextField nameTextField;

  @FXML
  private TextField quantityTextField;

  @FXML
  private TextField priceTextField;

  @FXML
  private TextField shippedInventoryTextField;



  @FXML
  private DatePicker datePicker;

  @FXML
  private TextField dateTextField;

  @FXML
  private Button backButton;

  @FXML
  private Button updateButton;

  @FXML
  private TextField brandTextField;

  @FXML
  private Button changePhotoButton;
  
  private ArrayList<Products> productsArrayList = null;

  private int indexOfSelectedPerson;

  private String photoPath;

  @FXML
  void handleBackButtonClick(ActionEvent event) {

    Functions.currentInventoryWindow(backButton,this.getClass());

  }

  @FXML
  void handleUpdateButtonClick(ActionEvent event) {

    String name = this.nameTextField.getText();
    String id = this.idTextField.getText();
    String type = this.typeTextField.getText();
    int quantity = Integer.parseInt(this.quantityTextField.getText());
    LocalDate date = this.datePicker.getValue();
    int price = Integer.parseInt(this.priceTextField.getText());
    int inventory = Integer.parseInt(this.inventoryTextField.getText());
    int shippedInventory = Integer.parseInt(this.shippedInventoryTextField.getText());
    String brand = this.brandTextField.getText();
    String photoPath = this.photoPath;

    Products product = new Products(
      id,
      name,
      type,
      brand,
      date,
      photoPath,
      price,
      quantity,
      inventory,
      shippedInventory
    );


    productsArrayList.set(indexOfSelectedPerson,product);

    File fileToWrite = new File(ProductsSerializer.productDataBasePath);

    try (
      FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

    ) {
      objectOutputStream.writeObject(productsArrayList);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    resetUI();

    loadInventoryWindow();
  }

  void setFields(int index) {
    this.indexOfSelectedPerson=index;

    Products product = productsArrayList.get(index);

    this.nameTextField.setText(product.getName());
    this.idTextField.setText(product.getItemID());
    this.typeTextField.setText(product.getType());
    this.quantityTextField.setText(String.valueOf(product.getQuantity()));
    this.datePicker.setValue(product.getDate());
    this.priceTextField.setText(String.valueOf(product.getPrice()));
    this.inventoryTextField.setText(String.valueOf(product.getInventory()));
    this.shippedInventoryTextField.setText(String.valueOf(product.getShippedInventory()));
    this.brandTextField.setText(product.getBrand());
    this.photoPath = product.getPathToPhoto();
    Image image = new Image("file://"+product.getPathToPhoto());
    this.imageOfProductView.setImage(image);
  }


  public void resetUI(){

    this.nameTextField.setText("");
    this.idTextField.setText("");
    this.typeTextField.setText("");
    this.quantityTextField.setText("");
    this.datePicker.setValue(null);
    this.priceTextField.setText("");
    this.inventoryTextField.setText("");
    this.shippedInventoryTextField.setText("");
    this.brandTextField.setText("");
    this.imageOfProductView.setImage(null);

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

  }


  @FXML
  void handleChangePhotoButtonClick(ActionEvent event) {


    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.changePhotoButton.getScene().getWindow();

    File fileSelected = fileChooser.showOpenDialog(primaryStage);

    if (fileSelected != null) {
      String selectedFilePath = fileSelected.toURI().getPath();
      this.photoPath = selectedFilePath;
    }

    Image profileImage = new Image("file://" + this.photoPath);
    this.imageOfProductView.setImage(profileImage);


  }


  public void loadInventoryWindow(){

    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CurrentInventory.fxml"));
      Pane root = (Pane) fxmlLoader.load();

      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.updateButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Current Inventory");
      primaryStage.show();

    }catch (Exception e){
      System.out.println(e);
    }
  }
}

