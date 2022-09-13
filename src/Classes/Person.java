package Classes;

import java.io.Serializable;
import java.util.Formatter;

public abstract class Person implements Serializable {
  private String fullName,contactNo;
  
  public Person(String fullName, String contactNo) {
    this.fullName = fullName;
    this.contactNo = contactNo;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
  public String getContactNo() {
    return contactNo;
  }
  
  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }
  
  @Override
  public String toString() {
    return this.getFullName();
  }
}
