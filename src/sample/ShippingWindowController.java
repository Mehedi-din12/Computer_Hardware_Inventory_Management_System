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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import products_utilities.ProductsSerializer;

import java.io.*;
import java.util.ArrayList;

public class ShippingWindowController {

  @FXML
  private TextField totalAmountTextField;

  @FXML
  private Button shipButton;

  @FXML
  private Button viewShippedProducts;

  @FXML
  private TableView<Products> productTableView;

  @FXML
  private TableColumn<Products, String> name;

  @FXML
  private TableColumn<Products, String> itemID;

  @FXML
  private TableColumn<Products, Integer> inventory;

  @FXML
  private TableColumn<Products, String> type;

  @FXML
  private TableColumn<Products, Integer> shippedInventory;

  @FXML
  private TableColumn<Products, Integer> price;

  @FXML
  private Button backButton;

  @FXML
  private TextField addressTextField;
  @FXML
  private ComboBox<String> navigationComboBox;
  
  @FXML
  private Button goButton;


  private ArrayList<Products> productsArrayList = null;


  private ObservableList<Products> observableList = null;

  private ArrayList<Products> shippedArrayList = null;


  private int indexOfShippingItem;
  
  @FXML
  void handleGoButtonClick(ActionEvent event) {
    if (this.navigationComboBox.getValue() != null) {
      switch (this.navigationComboBox.getValue()) {
      
        case "Opening Window" -> Functions.openOpeningWindow(this.goButton, this.getClass());
        case "Second Window" -> Functions.openSecondWindow(this.goButton, this.getClass());
        case "Current Inventory Window" -> Functions.currentInventoryWindow(this.goButton, this.getClass());
        case "Admin Portal" -> Functions.adminPortalWindow(this.goButton, this.getClass());
        case "Purchase Window" -> Functions.openPurchaseWindow(this.goButton, this.getClass());
      
      }
    }
  }
  @FXML
  void handleBackButtonClick(ActionEvent event) {

    Functions.openSecondWindow(backButton,this.getClass());

  }

  void invalidAmountAlert(){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setHeaderText("Invalid Amount");
    alert.show();
  }

  @FXML
  void handleShipButtonClick(ActionEvent event) {

    this.indexOfShippingItem = this.productTableView.getSelectionModel().getSelectedIndex();
    Products shippedProduct;
    Boolean isDigit = false;


    int amount;
    int currentInventory=-1;
    boolean amountAlertShown = true;
    try{
       amount = Integer.parseInt(this.totalAmountTextField.getText());
      currentInventory = productsArrayList.get(indexOfShippingItem).getInventory()-amount;
      isDigit=true;
    }catch (Exception e){
      invalidAmountAlert();
      amountAlertShown=false;
    }
    
    if (indexOfShippingItem != -1 && isDigit &&
      (currentInventory)>=0 ) {

      Products selectedProduct = productsArrayList.get(this.indexOfShippingItem);
      int amountToShip = Integer.parseInt(this.totalAmountTextField.getText());

      if (amountToShip > 0) {
        selectedProduct.setInventory(selectedProduct.getInventory() - amountToShip);
        selectedProduct.setShippedInventory(selectedProduct.getShippedInventory() + amountToShip);

        productsArrayList.set(indexOfShippingItem, selectedProduct);

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


      shippedProduct = new Products();

      shippedProduct.setName(selectedProduct.getName());
      shippedProduct.setItemID(selectedProduct.getItemID());
      shippedProduct.setBrand(selectedProduct.getBrand());
      shippedProduct.setShippedInventory(amountToShip);
      shippedProduct.setType(selectedProduct.getType());
      shippedProduct.setPrice(selectedProduct.getPrice());
      shippedProduct.setShipmentAddress(this.addressTextField.getText());

      System.out.println(shippedProduct);

      shippedArrayList.add(shippedProduct);

      File fileToWrite = new File(ProductsSerializer.shippedProductDataBasePath);

      try (
        FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      ) {
        objectOutputStream.writeObject(shippedArrayList);

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }


    }
    else {
      if(amountAlertShown) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Not enough products in inventory!");
        alert.show();
      }
    }


    initialize();


  }

  @FXML
  void handleViewShippedProducts(ActionEvent event) {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShippingInfo.fxml"));
      Pane root = (Pane) fxmlLoader.load();

      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.viewShippedProducts.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Shipping Details");
      primaryStage.show();

    } catch (Exception e) {
      System.out.println(e);
    }


  }

  public void initialize() {

    this.productsArrayList = new ArrayList<>();
    this.shippedArrayList = new ArrayList<>();


    File fileToRead = new File(ProductsSerializer.productDataBasePath);

    if (fileToRead.exists()) {

      try (
        FileInputStream fileInputStream = new FileInputStream(fileToRead);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ) {
        this.productsArrayList = (ArrayList<Products>) objectInputStream.readObject();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      this.observableList = FXCollections.observableArrayList(productsArrayList);
      name.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
      itemID.setCellValueFactory(new PropertyValueFactory<Products, String>("ItemID"));
      inventory.setCellValueFactory(new PropertyValueFactory<Products, Integer>("inventory"));
      type.setCellValueFactory(new PropertyValueFactory<Products, String>("type"));
      shippedInventory.setCellValueFactory(new PropertyValueFactory<Products, Integer>("shippedInventory"));
      price.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
      this.productTableView.setItems(observableList);

    }

    File fileToReadTwo = new File(ProductsSerializer.shippedProductDataBasePath);

    if (fileToReadTwo.exists()) {

      try (
        FileInputStream fileInputStream = new FileInputStream(fileToReadTwo);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ) {
        this.shippedArrayList = (ArrayList<Products>) objectInputStream.readObject();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }


    }
    initializeNavigationComboBox();

  }
  void initializeNavigationComboBox() {
    
    
    ArrayList<String> allWindows = new ArrayList<>();
    allWindows.add("Opening Window");
    allWindows.add("Second Window");
    allWindows.add("Current Inventory Window");
    allWindows.add("Admin Portal");
    allWindows.add("Purchase Window");
    
    
    ObservableList<String> allWindowsList = FXCollections.observableArrayList(allWindows);
    
    this.navigationComboBox.setItems(allWindowsList);
    
    
  }
  
  
  
}



