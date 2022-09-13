package Classes;

import java.io.Serializable;

public abstract class Inventory implements Serializable {

  private int inventory;
  private int shippedInventory;
  private String shipmentAddress;

  public Inventory(){

  }

  public Inventory(int inventory,int shippedInventory){

    setInventory(inventory);
    setShippedInventory(shippedInventory);
  }


  public int getInventory() {
    return inventory;
  }

  public String getShipmentAddress() {
    return shipmentAddress;
  }

  public int getShippedInventory() {
    return shippedInventory;
  }

  public void setInventory(int inventory) {
    this.inventory = inventory;
  }

  public void setShipmentAddress(String shipmentAddress) {
    this.shipmentAddress = shipmentAddress;
  }

  public void setShippedInventory(int shippedInventory) {
    this.shippedInventory = shippedInventory;
  }

}
