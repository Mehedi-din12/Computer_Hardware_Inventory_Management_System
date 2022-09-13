package ProductExceptions;

public class ProductInvalidUserNameException extends Exception{

  public ProductInvalidUserNameException () {
    super();
  }

  public ProductInvalidUserNameException (String message) {
    super(message);
  }
}
