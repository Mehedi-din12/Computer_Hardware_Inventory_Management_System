package ProductExceptions;

public class ProductInvalidPriceException extends Exception {
  public ProductInvalidPriceException() {
  }

  public ProductInvalidPriceException(String message) {
    super(message);
  }
}
