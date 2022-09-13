package admin_exceptions;

public class AdminInvalidNameException extends Exception{
  public AdminInvalidNameException() {
    super();
  }
  
  public AdminInvalidNameException(String message) {
    super(message);
  }
}
