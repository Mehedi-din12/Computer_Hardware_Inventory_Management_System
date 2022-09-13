package admin_exceptions;

public class AdminInvalidDateException extends Exception{
  public AdminInvalidDateException() {
    super();
  }
  
  public AdminInvalidDateException(String message) {
    super(message);
  }
}
