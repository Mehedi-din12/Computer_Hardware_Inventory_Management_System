package sample;

import Classes.Products;
import Functionalities.Functions;
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

public class ViewDetailsController {

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
  private TextField dateTextField;

  @FXML
  private Button backButton;


  public void setFields(Products product) {

    this.nameTextField.setText(product.getName());
    this.idTextField.setText(product.getItemID());
    this.typeTextField.setText(product.getType());
    this.quantityTextField.setText(String.valueOf(product.getQuantity()));
    this.dateTextField.setText(product.getDate().toString());
    this.priceTextField.setText(String.valueOf(product.getPrice()));
    this.inventoryTextField.setText(String.valueOf(product.getInventory()));
    this.shippedInventoryTextField.setText(String.valueOf(product.getShippedInventory()));




    Image imageOfProduct = new Image("file://"+product.getPathToPhoto());

    this.imageOfProductView.setImage(imageOfProduct);


  }



  @FXML
  void handleBackButtonClick(ActionEvent event) {

    Functions.currentInventoryWindow(backButton,this.getClass());


  }


}

