package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.Data;
import utilities.Serializer;

import java.util.ArrayList;

public class Controller {
  
  @FXML
  private TextField accountTextField;
  
  @FXML
  private TableView<Data> tableView;
  
  @FXML
  private TableColumn<Data, String> accountColumn;
  
  @FXML
  private TableColumn<Data, String> passwordColumn;
  
  @FXML
  private Button okButton;
  
  @FXML
  private Button deleteButton;
  
  
  @FXML
  void handleTableView(ActionEvent event) {
  
  }
  
  //Arraylist to store the person info
  private ArrayList<Data> dataList = null;
  
  //ObservableList for the list of persons stored
  private ObservableList<Data> observableDataList = null;
  
  
  public void initialize() {
    accountColumn.setCellValueFactory((new PropertyValueFactory<>("accountName")));
    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    
    this.dataList = Serializer.deserialized(Serializer.databasePath);
    if (this.dataList == null) {
      this.dataList = new ArrayList<>();
    }
    this.observableDataList = FXCollections.observableArrayList(dataList);
    this.tableView.setItems(observableDataList);
    
  }
  

  @FXML
  void handleOkButton(ActionEvent event) {

    String accountName = this.accountTextField.getText();
    String password = passwordGenerator();


    try {
      Data data = new Data(accountName, password);
      //System.out.println(data.getAccountName() + " : "+data.getPassword());
      this.dataList.add(data);
      this.observableDataList.add(data);
      boolean binaryFileSave = Serializer.serialize(Serializer.databasePath, this.dataList);

      if (!binaryFileSave) {
        throw new Exception("Couldn't save to the database");
      }
      this.accountTextField.setText(null);
    }
    catch (Exception e){
      e.printStackTrace();
  }
  
}
  private int indexSelected = -1;
  @FXML
  void hanleDeleteButton(ActionEvent event) throws Exception {
  
    this.indexSelected = this.tableView.getSelectionModel().getSelectedIndex();
  
    if (this.indexSelected != -1) {
       tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
       dataList.remove(indexSelected);
    }
    boolean binaryFileSave = Serializer.serialize(Serializer.databasePath, this.dataList);
  
    if (!binaryFileSave) {
      throw new Exception("Couldn't save to the database");
    }
    this.indexSelected = -1;
  }
  
  public static String passwordGenerator() {
    StringBuilder sb = new StringBuilder();
    
    String random = "abc$%^de345pqrFGHIJKstuvwxyzABC@#&DEL789MNOPQfghijklmnoRSTUVWXYZ1260!*,./?";
    
    String randomString = null;
    
    int rand = 8 + (int) (Math.random() * 8);
    
    for (int i = 0; i < rand; i++) {
      int index = (int) (Math.random() * (random.length()));
      
      char randomChar = random.charAt(index);
      sb.append(randomChar);
      randomString = sb.toString();
    }
    return randomString;
  }
  
}
