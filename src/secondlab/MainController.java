/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Juan Carlos
 */
public class MainController implements Initializable {    
    Stage main;
    Parent root;
    
//    first template
    @FXML private Button exit1;
    @FXML private Button proceed1;
    @FXML private RadioButton categorical;
    @FXML private RadioButton numeric;
    
//    second template
    @FXML private Button proceed2;
    @FXML private Button back1;
    @FXML private TextField titleField;
    @FXML private TextField nField;    
            
//    third template
    @FXML private Button proceed3;
    @FXML private Button enter;
    @FXML private Button back2;
    @FXML private TextField dataField;    
    @FXML private ListView inputDisplay;
    
//    Table template
    @FXML private TableView<Data> tableView;
    @FXML private TableColumn<Data, String> valueLabel;
    @FXML private TableColumn<Data, Float> percentage;
        
//    auxiliary variables
    boolean f1;
    ObservableList<String> itemList;
    ObservableList<Data> tableList;
    
    @FXML
    private void handleInputAction1(ActionEvent event) throws IOException {        
        if(GlobalContext.choiceSelected()) {
            main = (Stage) proceed1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("PrimaryInput.fxml"));
            Scene scene = new Scene(root);
            main.setScene(scene);
            main.show();
            f1 = false;
        }
    }
    
    @FXML
    private void handleInputAction2(ActionEvent event) throws IOException {        
        if(Validation.isNumeric(nField.getText()) && Validation.checkLimit(Integer.parseInt(nField.getText()))) {
            GlobalContext.n = Integer.parseInt(nField.getText());
            GlobalContext.title = titleField.getText();
            GlobalContext.array = new String[GlobalContext.n];
            GlobalContext.counter = 0;
            
            main = (Stage) proceed2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("SecondaryInput.fxml"));
            Scene scene = new Scene(root);
            main.setScene(scene);
            main.show();
            f1 = false;
        } else {
            System.out.println("Hello");  
        }
    }
    
    @FXML 
    private void handleInputAction3(ActionEvent event) throws IOException {        
        if(GlobalContext.counter == GlobalContext.n){
            main = (Stage) proceed3.getScene().getWindow();
            if(GlobalContext.categoricalChoice){
                FXMLLoader.load(getClass().getResource("SummaryTableOutput.fxml"));                
                tableList = FXCollections.observableArrayList();
                tableView.setItems(tableList);
                valueLabel.setCellValueFactory(new PropertyValueFactory<Data, String> ("Value Label"));
            } else {
                FXMLLoader.load(getClass().getResource("FrequencyDistributionTableOutput.fxml"));
            }
            
            Scene scene = new Scene(root);
            main.setScene(scene);
            main.show();
            f1 = false;
        }
    }
        
    @FXML
    private void handleEnterInputAction(ActionEvent event) throws IOException {
        String text = dataField.getText();
        f1 = false;
        
        if(GlobalContext.counter == 0) {
            Validation.setValidation(text);
            f1 = GlobalContext.inputType < 5;
        } else {
            f1 = Validation.validate(text);
        }
        
        if(f1){
            GlobalContext.array[GlobalContext.counter++] = text;     
            itemList = FXCollections.observableArrayList(GlobalContext.array);
            inputDisplay.setItems(itemList);
            
            if(GlobalContext.counter == GlobalContext.n){
                dataField.setEditable(false);
                enter.setDisable(true);
            }
        }
    }
    
    @FXML
    private void handleBackAction1(ActionEvent event) throws IOException {
        main = (Stage) proceed1.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainTemplate.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.initialize();
    }        
    
    @FXML
    private void handleBackAction2(ActionEvent event) throws IOException {
        main = (Stage) back2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("PrimaryInput.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.n = 0;
        GlobalContext.title = "";
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        f1 = false;
    }                
    
    @FXML
    public void numericSelected() {
        GlobalContext.setNumeric();
    }
    
    @FXML
    public void categoricalSelected() {
        GlobalContext.setCategorical();
    }        
}