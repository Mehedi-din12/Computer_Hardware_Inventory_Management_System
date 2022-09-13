package products_utilities;

import ProductExceptions.*;

import java.time.LocalDate;

public class ProductValidator {


  public static boolean isValid(
    String productID,
    String productName,
    String type,
    String brand,
    LocalDate date,
    String photoPath,
   String price,
    String productQuantity

  ) throws Exception {

    if (!(productIDValid(productID))) {

      throw new ProductInvalidIDException("Invalid Product ID! ID can't be less than 3");

    }


    if (!(productNameValid(productName))) {

      throw new ProductInvalidUserNameException("Invalid Product Name. Product Name can't be less than 3");

    }

    if (!(productTypeValid(type))) {

      throw new ProductInvalidTypeException("Invalid Product Type. Type should be chosen");

    }

    if (!(brandValid(brand))) {

      throw new ProductInvalidBrandException("Invalid Brand Name. Brand name can't be less than 3");

    }


    if (!(dateValid(date))) {

      throw new ProductInvalidUserNameException("Invalid Date! Date should be chosen");

    }

    if (!(priceValid(price))) {

      throw new ProductInvalidPriceException("Invalid Price. Price can't be less than 0");

    }


    if (!(quantityValid(productQuantity))) {

      throw new ProductInvalidQuantityException("Invalid Quantity. Quantity can't be less than 0");

    }

    if (!(photoPathValid(photoPath))) {

      throw new InvalidPhotoPath("Invalid Photo Path");

    }


    return true;

  }



  public static boolean productIDValid(String productID) {
    if (productID.length() > 2) {
      return true;
    }
    return false;

  }



  public static boolean productNameValid(String productName) {
    if (productName.length() > 2) {
      return true;
    }
    return false;

  }

  public static boolean productTypeValid(String type) {
    if (type!=null) {
      return true;
    }
    return false;

  }

  public static boolean brandValid(String brand) {
    if (brand.length() > 2) {
      return true;
    }
    return false;

  }

  public static boolean dateValid(LocalDate date) {
    if (date != null) {
      return true;
    }
    return false;

  }

  public static boolean photoPathValid(String photoPath) {
    if (!(photoPath==null)) {
      return true;
    }
    return false;

  }

  public static boolean priceValid(String priceString) throws Exception {

    try {
      int price = Integer.parseInt(priceString);
      if(price>=0){
        return true;
      }
    }catch (Exception e){
      throw new ProductInvalidPriceException("Invalid Price! Price can't be less than 0");
    }


    return false;


  }

  public static boolean quantityValid(String quantityString) throws Exception {
    try {
      int quantity = Integer.parseInt(quantityString);
      if (quantity >= 0) {
        return true;
      }

    }catch (Exception e){
      throw new ProductInvalidQuantityException("Invalid Quantity! Quantity can't be less than 0");
    }
    return false;

  }

}


