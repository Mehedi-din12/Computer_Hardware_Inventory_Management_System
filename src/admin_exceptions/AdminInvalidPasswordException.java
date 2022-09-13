package admin_exceptions;

public class AdminInvalidPasswordException extends Exception{
  public AdminInvalidPasswordException() {
    super();
  }
  
  public AdminInvalidPasswordException(String message) {
    super(message);
  }
}
