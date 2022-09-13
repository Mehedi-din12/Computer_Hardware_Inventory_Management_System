package Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Products extends Inventory implements Serializable,Cloneable,Comparable<Products> {
  private String itemID;
  private String name;
  private String type;
  private String brand;
  private int quantity;
  private LocalDate date;
  private String pathToPhoto;
  private int price;



  public Products() {

  }

  public Products(String itemID, String name, String type, String brand,
                  LocalDate date, String pathToPhoto,int price,int quantity) {
    this.itemID = itemID;
    this.name = name;
    this.type = type;
    this.brand = brand;
    this.date = date;
    this.pathToPhoto = pathToPhoto;
    this.price = price;
    this.quantity = quantity;
    setInventory(this.quantity);
  }

  public Products(String itemID, String name, String type, String brand,
                  LocalDate date, String pathToPhoto,int price,int quantity,int inventory,int shippedInventory) {
    super(inventory,shippedInventory);
    this.itemID = itemID;
    this.name = name;
    this.type = type;
    this.brand = brand;
    this.date = date;
    this.pathToPhoto = pathToPhoto;
    this.price = price;
    this.quantity = quantity;
  }

  public String getItemID() {
    return itemID;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getBrand() {
    return brand;
  }

  public LocalDate getDate() {
    return date;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getPrice() {
    return price;
  }

  public String getPathToPhoto() {
    return pathToPhoto;
  }

  public void setItemID(String itemID) {
    this.itemID = itemID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }


  public void setType(String type) {
    this.type = type;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setPathToPhoto(String pathToPhoto) {
    this.pathToPhoto = pathToPhoto;
  }


  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return "Products{" +
      "itemID='" + itemID + '\'' +
      ", name='" + name + '\'' +
      ", type='" + type + '\'' +
      ", brand='" + brand + '\'' +
      ", quantity=" + quantity +
      ", date=" + date +
      ", pathToPhoto='" + pathToPhoto + '\'' +
      ", price=" + price +
      '}';
  }

  @Override
  public int compareTo(Products o) {
    return this.getName().toUpperCase().compareTo(o.getName().toUpperCase());

  }
}
