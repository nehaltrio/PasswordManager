package utilities;

import java.io.Serializable;

public class Data implements Serializable {
  String accountName;
  String password;
  
  public Data(String accountName, String password) {
    this.accountName = accountName;
    this.password = password;
  }
  
  public String getAccountName() {
    return accountName;
  }
  
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "Data{" +
             "accountName='" + accountName + '\'' +
             ", password='" + password + '\'' +
             '}';
  }
}
