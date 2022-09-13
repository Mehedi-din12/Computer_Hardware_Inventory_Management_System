package AdminUtilities;

import Classes.Admin;

import java.io.*;
import java.util.ArrayList;

public class AdminSerializer {
  public static final String adminDataBasePath ="./AdminList.bin";
  
  public static boolean serialization(String filePath, ArrayList<Admin> adminArrayList){
    File fileToWrite=new File(filePath);
    boolean success;
    try(FileOutputStream fileOutputStream=new FileOutputStream(fileToWrite);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream)){
      objectOutputStream.writeObject(adminArrayList);
      success=true;
    }catch (Exception exception){
      success=false;
    }
    return success;
  }
  
  public static ArrayList<Admin> deserialization(String filePath){
    ArrayList<Admin> adminArrayList=null;
    File fileToRead=new File(filePath);
    try(FileInputStream fileInputStream=new FileInputStream(fileToRead);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream)){
      adminArrayList=(ArrayList<Admin>)objectInputStream.readObject();
    }catch (Exception exception){
      System.err.println(exception.getMessage());
    }
    return adminArrayList;
  }
}