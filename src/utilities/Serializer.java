package utilities;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Serializer {
  
  public static final String databasePath = "D:\\java files\\Password Manager\\src\\utilities\\data";
  
  public static boolean serialize(String filePath, ArrayList<Data> personArrayList) {
    File databaseFile = new File(filePath);
    
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;
    
    boolean success;
    
    try {
      fileOutputStream = new FileOutputStream(databaseFile);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(personArrayList);
      success = true;
    } catch (Exception e) {
      success = false;
    }
    
    return success;
  }
  
  public static ArrayList<Data> deserialized(String filePath) {
    File databaseFile = new File(filePath);
    FileInputStream fileInputStream;
    ObjectInputStream objectInputStream;
    
    ArrayList<Data> list = null;
    
    try {
      fileInputStream = new FileInputStream(databaseFile);
      objectInputStream = new ObjectInputStream(fileInputStream);
      list = (ArrayList<Data>) objectInputStream.readObject();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return list;
  }
  
}
