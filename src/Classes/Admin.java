package Classes;

import sample.ViewUtilities;

import java.io.Serializable;
import java.time.LocalDate;

public class Admin extends Person implements Serializable {
  private String userName,password,pathToAdminPhoto;
  private LocalDate joiningDate;
  
  public Admin(String fullName, String contactNo, String userName, String password, String pathToAdminPhoto, LocalDate joiningDate) {
    super(fullName, contactNo);
    this.userName = userName;
    this.password= password;
    this.pathToAdminPhoto = pathToAdminPhoto;
    this.joiningDate = joiningDate;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getPathToAdminPhoto() {
    return pathToAdminPhoto;
  }
  
  public void setPathToAdminPhoto(String pathToAdminPhoto) {
    this.pathToAdminPhoto = pathToAdminPhoto;
  }
  
  public LocalDate getJoiningDate() {
    return joiningDate;
  }
  
  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
