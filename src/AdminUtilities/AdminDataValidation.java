package AdminUtilities;

import admin_exceptions.*;

import java.time.LocalDate;

public class AdminDataValidation {
  private static final int VALID_NAME_LENGTH_AT_LEAST =3;
  private static final int VALID_CONTACT_LENGTH =11;
  private static final int VALID_PASSWORD_LENGTH_AT_LEAST =4;
  private static final int VALID_USER_NAME_LENGTH_AT_LEAST =2;
  
  public static boolean isAdminValidData(String fullName,
                                         String contactNo,
                                         String userName,
                                         String password,
                                         LocalDate localDate,
                                         String photoPath) throws Exception{
    if(!fullNameValidator(fullName)){
      throw new AdminInvalidNameException("Invalid Full Name. "+
        "Name length must be at least "+
        VALID_NAME_LENGTH_AT_LEAST+
        " with letter characters including space.");
    }
    
    
    
    if(!userNameValidator(userName)){
      throw new AdminNullException("Invalid user name. "+
        "User Name length must be at least "+
        VALID_USER_NAME_LENGTH_AT_LEAST+
        " with letter or number characters");
    }
    if(!contactNoValidator(contactNo)){
      throw new AdminInvalidContactNoException("Invalid Contact No. "+
        "Contact no must be exactly "+
        VALID_CONTACT_LENGTH+
        " with number characters");
    }
    
    if(!passwordValidator(password)){
      throw new AdminInvalidPasswordException("Invalid password. "+
        "Password must be at least "+
        VALID_PASSWORD_LENGTH_AT_LEAST+
        " characters");
    }
    
    if(!localDateValidator(localDate)){
      throw new AdminInvalidDateException("Invalid Date. Please try again !!");
    }
    
    if(!pictureValidator(photoPath)){
      throw new AdminNullException("Photo path not found. Please try again !!");
    }
    return true;
    
  }
  
  public static boolean isSpace(char ch){
    if(ch==32){
      return true;
    }
    return false;
  }
  
  public static boolean isLetterValidator(String string){
    for(int i=0;i<string.length();i++){
      if(isSpace(string.charAt(i))){
        continue;
      }
      if(!Character.isLetter(string.charAt(i))){
        return false;
      }
    }
    return true;
  }
  
  public static boolean isDigitValidator(String string){
    for(int i=0;i<string.length();i++){
      if(!Character.isDigit(string.charAt(i))){
        return false;
      }
    }
    return true;
  }
  
  public static boolean isLetterOrDigit(String string){
    for(int i=0;i<string.length();i++){
      if(!( (isDigitValidator(String.valueOf(string.charAt(i)))) ||
        (isLetterValidator(String.valueOf(string.charAt(i)))) )){
        return false;
      }
    }
    return true;
  }
  
  
  
  public static boolean fullNameValidator(String fullName){
    if((fullName.length()>=VALID_NAME_LENGTH_AT_LEAST) && (isLetterValidator(fullName))){
      return true;
    }
    return false;
  }
  
  public static boolean userNameValidator(String userName){
    if( (userName.length()>=VALID_USER_NAME_LENGTH_AT_LEAST) &&
      (isLetterOrDigit(userName)) ){
      return true;
    }
    return false;
  }
  
  
  
  public static boolean passwordValidator(String password){
    if(password.length()>= VALID_PASSWORD_LENGTH_AT_LEAST){
      return true;
    }
    return false;
  }
  
  public static boolean contactNoValidator(String contactNo){
    if((contactNo.length()==VALID_CONTACT_LENGTH) && (isDigitValidator(contactNo))){
      return true;
    }
    return false;
  }
  
  public static boolean localDateValidator(LocalDate localDate){
    boolean boolValue=true;
    String string=String.valueOf(localDate);
    try{
      LocalDate.parse(string);
    }catch (Exception exception){
      boolValue=false;
    }
    return boolValue;
  }
  
  public static boolean pictureValidator(String photoPath){
    if(photoPath==null)
      return false;
    return true;
  }
}
