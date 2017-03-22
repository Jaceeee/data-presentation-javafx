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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML private TableView<Data> summaryTableView;
    
//    Summary Table
    @FXML private TableColumn<Data, String> valueLabel;
    @FXML private TableColumn<Data, Float> percentage;
//    Frequency Distribution
    @FXML private TableColumn<Data, String> classLimits;
    @FXML private TableColumn<Data, String> trueClassLimits;
    @FXML private TableColumn<Data, Float> midpoints;
    @FXML private TableColumn<Data, Float> frequency;
    @FXML private TableColumn<Data, Float> frequencyPercentage;
    @FXML private TableColumn<Data, Float> cumulativeFrequency;
    @FXML private TableColumn<Data, Float> cumulativeFrequencyPercentage;
    
    @FXML private Button proceed4;
    @FXML private Button back3;
    
        
//    auxiliary variables    
    ObservableList<String> itemList;
    ObservableList<Data> tableList = FXCollections.observableArrayList();
    
    @FXML
    private void handleInputAction1(ActionEvent event) throws IOException {        
        if(GlobalContext.choiceSelected()) {
            main = (Stage) proceed1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("PrimaryInput.fxml"));
            Scene scene = new Scene(root);
            main.setScene(scene);
            main.show();
            GlobalContext.f1 = false;
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
            GlobalContext.f2 = true;
        } else {
            System.out.println("Hello");  
        }
    }
    
    @FXML 
    private void handleInputAction3(ActionEvent event) throws IOException {                                        
        main = (Stage) proceed3.getScene().getWindow();
        if(GlobalContext.categoricalChoice) {
            root = FXMLLoader.load(getClass().getResource("SummaryTableOutput.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("FrequencyDistributionTableOutput.fxml"));
        }
                
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();        
    }
    
    @FXML
    private void handleInputAction4(ActionEvent event) throws IOException {
//        main = (Stage) proceed2.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("SecondaryInput.fxml"));
//        Scene scene = new Scene(root);
//        main.setScene(scene);
//        main.show();
        GlobalContext.f2 = false;
    }
        
    @FXML
    private void handleEnterInputAction(ActionEvent event) throws IOException {
        String text = dataField.getText();
        GlobalContext.f1 = false;
        
        if(GlobalContext.counter == 0) {
            Validation.setValidation(text);
            GlobalContext.f1 = GlobalContext.inputType < 5;
        } else {
            GlobalContext.f1 = Validation.validate(text);
        }
        
        if(GlobalContext.f1) {
            GlobalContext.array[GlobalContext.counter++] = text;     
            itemList = FXCollections.observableArrayList(GlobalContext.array);
            inputDisplay.setItems(itemList);
            
            if(GlobalContext.counter == GlobalContext.n) {
                dataField.setEditable(false);
                enter.setDisable(true);
            }
        }
    }
    
    @FXML
    private void handleBackAction1(ActionEvent event) throws IOException {
        main = (Stage) back1.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainTemplate.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.initialize();
    }        
    
    @FXML
    private void handleBackAction2(ActionEvent event) throws IOException {
        GlobalContext.n = 0;
        GlobalContext.title = "";
        itemList = null;
        
        main = (Stage) back2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("PrimaryInput.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
    }
    
    private void handleBackAction3(ActionEvent event) throws IOException {
        GlobalContext.data = new Data[0];
        tableList = null;
        
        main = (Stage) proceed2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SecondaryInput.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GlobalContext.f1 = false;        
        if(GlobalContext.f2) {
            GlobalContext.setData();
            tableList = FXCollections.observableArrayList(GlobalContext.data);
            
            if(GlobalContext.categoricalChoice){                
                valueLabel.setCellValueFactory(new PropertyValueFactory<Data, String> ("ValueLabel"));
                percentage.setCellValueFactory(new PropertyValueFactory<Data, Float> ("Percentage"));

                summaryTableView.setItems(tableList);
                summaryTableView.setVisible(true);
                valueLabel.setVisible(true);
                valueLabel.setVisible(true);
            }
            else if(GlobalContext.numericChoice) {
                classLimits.setCellValueFactory(new PropertyValueFactory<Data, String> ("ClassLimits"));
                trueClassLimits.setCellValueFactory(new PropertyValueFactory<Data, String>("TrueClassLimits"));
                midpoints.setCellValueFactory(new PropertyValueFactory<Data, Float>("Midpoints"));
                frequency.setCellValueFactory(new PropertyValueFactory<Data, Float>("Frequency"));
                frequencyPercentage.setCellValueFactory(new PropertyValueFactory<Data, Float>("FrequencyPercentages"));
                cumulativeFrequency.setCellValueFactory(new PropertyValueFactory<Data, Float>("CumulativeFrequency"));
                cumulativeFrequencyPercentage.setCellValueFactory(new PropertyValueFactory<Data, Float>("CumulativeFrequencyPercentages"));
            }
        }
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