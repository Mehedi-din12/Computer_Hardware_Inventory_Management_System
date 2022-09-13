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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CurrentInventoryController {
  
  @FXML
  private Button addProductButton;
  
  @FXML
  private Button editDetailsButton;
  
  
  @FXML
  private Button viewDetailsButton;
  
  
  @FXML
  private Button backButton;
  
  @FXML
  private Button deleteButton;
  
  
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
  private ComboBox<String> deleteComboBox;
  
  @FXML
  private ComboBox<String> sortProductComboBox;
  
  @FXML
  private Button sortButton;
  
  @FXML
  private TextField deleteNumberField;
  
  
  @FXML
  private ComboBox<String> navigationComboBox;
  
  @FXML
  private Button goButton;
  
  
  private ObservableList<Products> observableList = null;
  
  private int indexOfProductSelectedFromTableView;
  
  private ArrayList<Products> productsArrayList = null;
  
  private String profilePhotoPath;
  
  private int indexOfDeleteItem;
  
  
  @FXML
  void handleGoButtonClick(ActionEvent event) {
    
    
    if (this.navigationComboBox.getValue() != null) {
      switch (this.navigationComboBox.getValue()) {
        
        case "Opening Window" -> Functions.openOpeningWindow(this.goButton, this.getClass());
        case "Second Window" -> Functions.openSecondWindow(this.goButton, this.getClass());
        case "Admin Portal" -> Functions.adminPortalWindow(this.goButton, this.getClass());
        case "Shipping Window" -> Functions.openShippingWindow(this.goButton, this.getClass());
        case "Purchase Window" -> Functions.openPurchaseWindow(this.goButton, this.getClass());
        
      }
      
    }
  }
  
  
  @FXML
  void handleSortButtonClick(ActionEvent event) {
    
    
    if (this.sortProductComboBox.getValue() != null) {
      
      String choice = this.sortProductComboBox.getValue();
      
      if (choice.equals("Sort By Price")) {
        
        Comparator<Products> productsComparator = new Comparator<Products>() {
          @Override
          public int compare(Products o1, Products o2) {
            if (o1.getPrice() > o2.getPrice()) {
              return 1;
            } else if (o1.getPrice() < o2.getPrice()) {
              return -1;
            }
            
            return 0;
          }
        };
        
        Collections.sort(this.productsArrayList, productsComparator);
        
        
        File fileToWrite = new File(ProductsSerializer.productDataBasePath);
        
        try (
          FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        ) {
          objectOutputStream.writeObject(productsArrayList);
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        
        initialize();
        
        
      } else if (choice.equals("Sort By Name")) {
        
        Comparator<Products> productsComparator = new Comparator<Products>() {
          @Override
          public int compare(Products o1, Products o2) {
            
            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
          }
        };
        
        Collections.sort(this.productsArrayList, productsComparator);
        
        
        File fileToWrite = new File(ProductsSerializer.productDataBasePath);
        
        try (
          FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        ) {
          objectOutputStream.writeObject(productsArrayList);
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        
        initialize();
      }
    }
  }
  
  
  @FXML
  void handleAddProductButtonClick(ActionEvent event) {
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AddProduct.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.addProductButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Add Inventory Product");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
    
  }
  
  @FXML
  void handleDeleteButtonClick(ActionEvent event) {
    
    if (this.deleteComboBox.getValue() == null) {
      
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Select item and option from delete button");
      alert.showAndWait();
    
      
    
    } else {
      
      
      this.indexOfDeleteItem = this.productTableView.getSelectionModel().getSelectedIndex();
      
      
      if (this.indexOfDeleteItem != -1) {
        String chosenOption = this.deleteComboBox.getValue();
        
        
        if (chosenOption.equals("Delete All")) {
          
          productsArrayList.remove(indexOfDeleteItem);
          
          File fileToWrite = new File(ProductsSerializer.productDataBasePath);
          
          try (
            FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
          
          ) {
            objectOutputStream.writeObject(productsArrayList);
            
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          
          initialize();
          
        }
        
        if (chosenOption.equals("Delete Single Product")) {
          
          System.out.println(indexOfDeleteItem);
          
          Products selectedProduct = productsArrayList.get(this.indexOfDeleteItem);
          
          selectedProduct.setInventory(selectedProduct.getInventory() - 1);
          productsArrayList.set(this.indexOfDeleteItem, selectedProduct);
          
          File fileToWrite = new File(ProductsSerializer.productDataBasePath);
          
          try (
            FileOutputStream fileOutputStream = new FileOutputStream(fileToWrite);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
          
          ) {
            objectOutputStream.writeObject(productsArrayList);
            
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          
          initialize();
          
          
        }
        
      }
      
    }
    
    
  }
  
  
  @FXML
  void handleviewDetailsButtonClick(ActionEvent event) {
    
    this.indexOfProductSelectedFromTableView = this.productTableView.getSelectionModel().getSelectedIndex();
    
    Products savedProduct = null;
    if (indexOfProductSelectedFromTableView != -1) {
      savedProduct = this.productTableView.getItems().get(this.indexOfProductSelectedFromTableView);
      
    }
    
    if (savedProduct != null) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ViewDetails.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        
        ViewDetailsController viewDetailsController = fxmlLoader.getController();
        viewDetailsController.setFields(savedProduct);
        
        Scene optionsScene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        optionsScene.getStylesheets().add(css);
        Stage primaryStage = (Stage) this.viewDetailsButton.getScene().getWindow();
        primaryStage.setScene(optionsScene);
        primaryStage.setTitle("Inventory Product Detail");
        primaryStage.show();
        
      } catch (Exception e) {
        System.out.println(e);
      }
      
    }
    
    
  }
  
  
  @FXML
  void handleEditDetailsButtonClick(ActionEvent event) {
    
    this.indexOfProductSelectedFromTableView = this.productTableView.getSelectionModel().getSelectedIndex();
    
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/EditDetails.fxml"));
      Pane root = (Pane) fxmlLoader.load();
      
      EditDetailsController editDetailsController = fxmlLoader.getController();
      editDetailsController.setFields(this.indexOfProductSelectedFromTableView);
      
      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.editDetailsButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Edit Inventory Product");
      primaryStage.show();
      
    } catch (Exception e) {
      System.out.println(e);
    }
    
    System.out.println(this.indexOfProductSelectedFromTableView);
    
    
  }
  
  
  public void initialize() {
    
    this.productsArrayList = new ArrayList<>();
    
    File fileToRead = new File(ProductsSerializer.productDataBasePath);
    System.out.println(fileToRead.exists());
    
    if (fileToRead.exists()) {
      this.productsArrayList = ProductsSerializer.deserialization(ProductsSerializer.productDataBasePath);
      if (this.productsArrayList == null) {
        this.productsArrayList = new ArrayList<>();
      }
      
      this.observableList = FXCollections.observableArrayList(productsArrayList);
      name.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
      itemID.setCellValueFactory(new PropertyValueFactory<Products, String>("ItemID"));
      inventory.setCellValueFactory(new PropertyValueFactory<Products, Integer>("inventory"));
      type.setCellValueFactory(new PropertyValueFactory<Products, String>("type"));
      shippedInventory.setCellValueFactory(new PropertyValueFactory<Products, Integer>("shippedInventory"));
      price.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
      this.productTableView.setItems(observableList);
      
    } else {
      try (FileOutputStream fileOutputStream = new FileOutputStream(fileToRead);
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      } catch (Exception exception) {
        System.out.println("File not created");
      }
    }
    
    
    ArrayList<String> typeOptions = new ArrayList<>();
    typeOptions.add("Delete Single Product");
    typeOptions.add("Delete All");
    
    ObservableList<String> deleteObservableList = FXCollections.observableArrayList(typeOptions);
    
    this.deleteComboBox.setItems(deleteObservableList);
    
    
    ArrayList<String> sortOptions = new ArrayList<>();
    sortOptions.add("Sort By Price");
    sortOptions.add("Sort By Name");
    
    ObservableList<String> sortObservableList = FXCollections.observableArrayList(sortOptions);
    
    this.sortProductComboBox.setItems(sortObservableList);
    
    
    initializeNavigationComboBox();
    
    
  }
  
  @FXML
  void handleBackButtonClick(ActionEvent event) {
    
    Functions.openSecondWindow(backButton, this.getClass());
    
  }
  
  
  void initializeNavigationComboBox() {
    
    
    ArrayList<String> allWindows = new ArrayList<>();
    allWindows.add("Opening Window");
    allWindows.add("Second Window");
    allWindows.add("Admin Portal");
    allWindows.add("Shipping Window");
    allWindows.add("Purchase Window");
    
    
    ObservableList<String> allWindowsList = FXCollections.observableArrayList(allWindows);
    
    this.navigationComboBox.setItems(allWindowsList);
    
  }
  
}

