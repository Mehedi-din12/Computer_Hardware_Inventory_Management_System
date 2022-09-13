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

public class PurchasingWindowController {
  
  @FXML
  private TextField totalAmountTextField;
  
  @FXML
  private Button purchaseButton;
  
  @FXML
  private Button viewPurchasedProducts;
  
  @FXML
  private TableView<Products> productTableView;
  
  @FXML
  private TableColumn<Products, String> name;
  
  @FXML
  private TableColumn<Products, String> itemID;
  
  @FXML
  private TableColumn<Products, Integer> inventory;
  
  @FXML
  private TableColumn<Products, Integer> purchasedInventory;
  
  @FXML
  private TableColumn<Products, String> type;
  
  @FXML
  private TableColumn<Products, Integer> price;
  
  @FXML
  private Button backButton;
  
  @FXML
  private TextField agentTextField;
  @FXML
  private ComboBox<String> navigationComboBox;
  
  @FXML
  private Button goButton;
  
  private ArrayList<Products> productsArrayList = null;
  private ObservableList<Products> observableList = null;
  private ArrayList<Products> purchasedArrayList = null;
  private int indexOfPurchasingItem;
  
  
  @FXML
  void handleGoButtonClick(ActionEvent event) {
    if (this.navigationComboBox.getValue() != null) {
      switch (this.navigationComboBox.getValue()) {
      
        case "Opening Window" -> Functions.openOpeningWindow(this.goButton, this.getClass());
        case "Second Window" -> Functions.openSecondWindow(this.goButton, this.getClass());
        case "Current Inventory Window" -> Functions.currentInventoryWindow(this.goButton, this.getClass());
        case "Admin Portal" -> Functions.adminPortalWindow(this.goButton, this.getClass());
        case "Shipping Window" -> Functions.openShippingWindow(this.goButton, this.getClass());
        
      }
    
    }
  
  }
  
  
  @FXML
  void handleBackButtonClick(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/SecondWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.backButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Inventory Management System Home Page");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  void invalidAmountAlert(){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setHeaderText("Invalid Amount");
    alert.show();
  }
  
  @FXML
  void handlePurchaseButtonClick(ActionEvent event) {
    
    this.indexOfPurchasingItem = this.productTableView.getSelectionModel().getSelectedIndex();
    Products purchaseProducts;
    
    
    if (indexOfPurchasingItem != -1) {
      
      Products selectedProduct = productsArrayList.get(this.indexOfPurchasingItem);
      
      
      try{
        int amountToShip = Integer.parseInt(this.totalAmountTextField.getText());
  
        if (amountToShip > 0) {
          selectedProduct.setInventory(selectedProduct.getInventory() + amountToShip);
    
          productsArrayList.set(indexOfPurchasingItem, selectedProduct);
    
          File fileToWrite = new File(ProductsSerializer.productDataBasePath);
    
          try (
            FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    
          ) {
            objectOutputStream.writeObject(productsArrayList);
      
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
    
          purchaseProducts = new Products();
    
          purchaseProducts.setName(selectedProduct.getName());
          purchaseProducts.setItemID(selectedProduct.getItemID());
          purchaseProducts.setBrand(selectedProduct.getBrand());
          purchaseProducts.setShippedInventory(amountToShip);
          purchaseProducts.setType(selectedProduct.getType());
          purchaseProducts.setPrice(selectedProduct.getPrice());
          purchaseProducts.setShipmentAddress(this.agentTextField.getText());
    
          purchasedArrayList.add(purchaseProducts);
    
          File fileToWrite2 = new File(ProductsSerializer.purchasedProductDataBasePath);
    
          try (
            FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite2);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    
          ) {
            objectOutputStream.writeObject(purchasedArrayList);
      
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        
      }catch (Exception exception){
        invalidAmountAlert();
      }
      
      
//      int amountToShip = Integer.parseInt(this.totalAmountTextField.getText());
//
//      if (amountToShip > 0) {
//        selectedProduct.setInventory(selectedProduct.getInventory() + amountToShip);
//
//        productsArrayList.set(indexOfPurchasingItem, selectedProduct);
//
//        File fileToWrite = new File(ProductsSerializer.productDataBasePath);
//
//        try (
//          FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
//          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//        ) {
//          objectOutputStream.writeObject(productsArrayList);
//
//        } catch (Exception e) {
//          System.out.println(e.getMessage());
//        }
//
//        purchaseProducts = new Products();
//
//        purchaseProducts.setName(selectedProduct.getName());
//        purchaseProducts.setItemID(selectedProduct.getItemID());
//        purchaseProducts.setBrand(selectedProduct.getBrand());
//        purchaseProducts.setShippedInventory(amountToShip);
//        purchaseProducts.setType(selectedProduct.getType());
//        purchaseProducts.setPrice(selectedProduct.getPrice());
//        purchaseProducts.setShipmentAddress(this.agentTextField.getText());
//
//        purchasedArrayList.add(purchaseProducts);
//
//        File fileToWrite2 = new File(ProductsSerializer.purchasedProductDataBasePath);
//
//        try (
//          FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite2);
//          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//        ) {
//          objectOutputStream.writeObject(purchasedArrayList);
//
//        } catch (Exception e) {
//          System.out.println(e.getMessage());
//        }
//      }
      
      
      }
      
    initialize();
  }
  
  @FXML
  void handleViewPurchasedProducts(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/PurchasingInfo.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.viewPurchasedProducts.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Purchasing Details");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  public void initialize() {
    this.productsArrayList = new ArrayList<>();
    this.purchasedArrayList = new ArrayList<>();
    
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
      price.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
      this.productTableView.setItems(observableList);
      
    }
    
    File fileToReadTwo = new File(ProductsSerializer.purchasedProductDataBasePath);
    
    if (fileToReadTwo.exists()) {
      
      try (
        FileInputStream fileInputStream = new FileInputStream(fileToReadTwo);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ) {
        this.purchasedArrayList = (ArrayList<Products>) objectInputStream.readObject();
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
    allWindows.add("Shipping Window");
    
    ObservableList<String> allWindowsList = FXCollections.observableArrayList(allWindows);
    
    this.navigationComboBox.setItems(allWindowsList);
    
  }
  
}
