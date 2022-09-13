package products_utilities;

import Classes.Products;

import java.io.*;
import java.util.ArrayList;

public class ProductsSerializer {
  public static final String productDataBasePath="./Products.bin";
  public static final String shippedProductDataBasePath="./ShippedProducts.bin";
  public static final String purchasedProductDataBasePath="./PurchasedProducts.bin";
  
  public static boolean serialization(String filePath, ArrayList<Products> productsArrayList){
    File fileToWrite=new File(filePath);
    boolean success;
    try(FileOutputStream fileOutputStream=new FileOutputStream(fileToWrite);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)){
      objectOutputStream.writeObject(productsArrayList);
      success=true;
    }catch (Exception exception){
      success=false;
    }
    return success;
  }
  
  public static ArrayList<Products> deserialization(String filePath){
    ArrayList<Products> productsArrayList=null;
    File fileToRead=new File(filePath);
    try(FileInputStream fileInputStream=new FileInputStream(fileToRead);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)){
      productsArrayList=(ArrayList<Products>)objectInputStream.readObject();
    }catch (Exception exception){
      System.err.println(exception.getMessage());
    }
    return productsArrayList;
  }
}
