package sample;

import Classes.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import products_utilities.ProductsSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ShippingInfoController{

  @FXML
  private TableView<Products> productTableView;

  @FXML
  private TableColumn<Products, String> name;

  @FXML
  private TableColumn<Products, String> itemID;

  @FXML
  private TableColumn<Products, String> brand;

  @FXML
  private TableColumn<Products, String> type;

  @FXML
  private TableColumn<Products, Integer> shippedInventory;

  @FXML
  private TableColumn<Products, Integer> price;

  @FXML
  private TableColumn<Products, String> shipmentAddress;

  @FXML
  private Button backButton;

  private ArrayList<Products> productsArrayList = null;


  private ObservableList<Products> observableList = null;


  @FXML
  void handleBackButtonClick(ActionEvent event) {

    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ShippingWindow.fxml"));
      Pane root = (Pane) fxmlLoader.load();

      Scene optionsScene = new Scene(root);
      String css = this.getClass().getResource("application.css").toExternalForm();
      optionsScene.getStylesheets().add(css);
      Stage primaryStage = (Stage) this.backButton.getScene().getWindow();
      primaryStage.setScene(optionsScene);
      primaryStage.setTitle("Outgoing Orders");
      primaryStage.show();

    }catch (Exception e){
      System.out.println(e);
    }

  }

  public void initialize(){

    this.productsArrayList = new ArrayList<>();

    File fileToRead = new File(ProductsSerializer.shippedProductDataBasePath);

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
      brand.setCellValueFactory(new PropertyValueFactory<Products, String>("brand"));
      type.setCellValueFactory(new PropertyValueFactory<Products, String>("type"));
      shippedInventory.setCellValueFactory(new PropertyValueFactory<Products, Integer>("shippedInventory"));
      price.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
      shipmentAddress.setCellValueFactory(new PropertyValueFactory<Products,String>("shipmentAddress"));
      this.productTableView.setItems(observableList);

    }
  }

}

