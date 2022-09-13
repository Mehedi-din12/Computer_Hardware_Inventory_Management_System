package AdminUtilities;

import OpenMenu_exceptions.OpeningWindowAdminPasswordException;
import OpenMenu_exceptions.OpeningWindowAdminUserNameException;

public class OpeningMenuValidation {
  private static  int VALID_NAME_LENGTH_AT_LEAST =3;
  private static int VALID_PASSWod =4;
  public static boolean isValidOpenMenu(String username,String passport) throws Exception{
    if(!isUserName(username)){
      throw new OpeningWindowAdminUserNameException("Invalid Username. You can register");
    }
    if(!isPassport(passport)){
      throw new OpeningWindowAdminPasswordException("Invalid Password. You can register");
    }if(isUserName(username)||isPassport(passport)) {
      throw new OpeningWindowAdminPasswordException("Invalid Password or Invalid Username. You can register");
      
    }
    return true;
  }
  public static boolean isUserName(String userName){
    if(userName.length()<3 ){
      return false;
    }
    return true;
    
  }
  public static boolean isPassport(String passport){
    if(passport.length()<4){
      return false;
    }else {
      return true;
    }
  }
  
}
