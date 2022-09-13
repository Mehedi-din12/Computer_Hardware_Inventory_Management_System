package admin_exceptions;

public class AdminNullException extends Exception{
  public AdminNullException() {
    super();
  }
  
  public AdminNullException(String message) {
    super(message);
  }
}
