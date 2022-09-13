package ProductExceptions;

public class ProductInvalidQuantityException extends Exception {

  public ProductInvalidQuantityException() {
  }

  public ProductInvalidQuantityException(String message) {
    super(message);
  }

}
