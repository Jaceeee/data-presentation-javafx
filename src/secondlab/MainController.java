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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    @FXML private Label errorMessage1;
    
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
    @FXML private Label summaryTableLabel;
    @FXML private Label frequencyDistributionTableLabel;
    @FXML private TableView<secondlab.Data> summaryTableView;
    @FXML private TableView<secondlab.Data> frequencyDistributionTableView;
    
//    Summary Table
    @FXML private TableColumn<secondlab.Data, String> valueLabel;
    @FXML private TableColumn<secondlab.Data, Float> percentage;
    
//    Frequency Distribution    
    @FXML private TableColumn<secondlab.Data, String> classLimits;
    @FXML private TableColumn<secondlab.Data, String> trueClassLimits;
    @FXML private TableColumn<secondlab.Data, Float> midpoints;
    @FXML private TableColumn<secondlab.Data, Integer> frequency;
    @FXML private TableColumn<secondlab.Data, Float> frequencyPercentage;
    @FXML private TableColumn<secondlab.Data, Integer> cumulativeFrequency;
    @FXML private TableColumn<secondlab.Data, Float> cumulativeFrequencyPercentage;
        
    @FXML private Button proceed4a;
    @FXML private Button proceed4b;
    @FXML private Button back3a;
    @FXML private Button back3b;
    
    //    Pie Chart
    @FXML private PieChart pieChart;
    @FXML private Label pieChartLabel;
    @FXML private Button proceed5a;
    @FXML private Button backToMainMenu;
        
     // Histogram
    private XYChart.Series series1;
    @FXML private BarChart histogram;
    @FXML private Button proceed5b;
    @FXML private Label histChartLabel;

//    auxiliary variables    
    ObservableList<String> itemList;
    ObservableList<secondlab.Data> tableList = FXCollections.observableArrayList();
    
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
            GlobalContext.categoricalArray = new String[GlobalContext.n];
            GlobalContext.counter = 0;
            
            main = (Stage) proceed2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("SecondaryInput.fxml"));
            Scene scene = new Scene(root);
            main.setScene(scene);
            main.show();            
        } else {
            errorMessage1.setText("Wrong input detected");
        }
    }
    
    @FXML 
    private void handleInputAction3(ActionEvent event) throws IOException {        
        GlobalContext.f2 = true;
        main = (Stage) proceed3.getScene().getWindow();
        if(GlobalContext.categoricalChoice) {            
            root = FXMLLoader.load(getClass().getResource("SummaryTableOutput.fxml"));            
        } else {
            root = FXMLLoader.load(getClass().getResource("FrequencyDistributionTableOutput.fxml"));            
        }                
        
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.f2 = false;        
    }
    
    @FXML   
    private void handleInputAction4(ActionEvent event) throws IOException {
        GlobalContext.f3 = true;
        if(GlobalContext.categoricalChoice){
            main = (Stage) proceed4a.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
        } else {
            main = (Stage) proceed4b.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("HistogramChartOutput.fxml"));
        }
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();        
        GlobalContext.f3 = false;
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
            GlobalContext.categoricalArray[GlobalContext.counter++] = text;     
            itemList = FXCollections.observableArrayList(GlobalContext.categoricalArray);
            inputDisplay.setItems(itemList);
            
            if(GlobalContext.counter == GlobalContext.n) {
                dataField.setEditable(false);
                enter.setDisable(true);
            }
            dataField.setText("");
        }
    }
    
    @FXML
    public void enterListener(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            enter.fire();            
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
        GlobalContext.f2 = false;
        itemList = null;
        
        main = (Stage) back2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("PrimaryInput.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
    }
    
    @FXML
    private void handleBackAction3(ActionEvent event) throws IOException {
        GlobalContext.categoricalData = new secondlab.Data[0];        
        tableList = null;
        
        if(event.getSource() == backToMainMenu) {
            main = (Stage) backToMainMenu.getScene().getWindow();
        } else {
            main = (Stage) back3b.getScene().getWindow();        
        }
        
        root = FXMLLoader.load(getClass().getResource("MainTemplate.fxml"));
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.initialize();
    }
    
    @FXML
    private void handleBackAction4(ActionEvent event) throws IOException {
        GlobalContext.f2 = true;        
        
        if(GlobalContext.categoricalChoice){
            main = (Stage) proceed5a.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("SummaryTableOutput.fxml"));
        } else {
            main = (Stage) proceed5b.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FrequencyDistributionTableOutput.fxml"));
        }
        Scene scene = new Scene(root);
        main.setScene(scene);
        main.show();
        GlobalContext.f2 = false;
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        if(GlobalContext.f2) {
            GlobalContext.setData();            
            
            if(GlobalContext.categoricalChoice){                
                tableList = FXCollections.observableArrayList(GlobalContext.categoricalData);
                valueLabel.setCellValueFactory(new PropertyValueFactory<secondlab.Data, String> ("ValueLabel"));
                percentage.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Float> ("Percentage"));

                summaryTableView.setItems(tableList);
                summaryTableView.setVisible(true);                
                summaryTableLabel.setText(GlobalContext.title);            
            }
            else if(GlobalContext.numericChoice) {
                tableList = FXCollections.observableArrayList(GlobalContext.numericData);
                classLimits.setCellValueFactory(new PropertyValueFactory<secondlab.Data, String> ("ClassLimits"));
                trueClassLimits.setCellValueFactory(new PropertyValueFactory<secondlab.Data, String>("TrueClassLimits"));
                midpoints.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Float>("Midpoints"));
                frequency.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Integer>("Frequency"));
                frequencyPercentage.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Float>("FrequencyPercentage"));
                cumulativeFrequency.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Integer>("CumulativeFrequency"));
                cumulativeFrequencyPercentage.setCellValueFactory(new PropertyValueFactory<secondlab.Data, Float>("CumulativeFrequencyPercentage"));
                
                frequencyDistributionTableView.setItems(tableList);
                frequencyDistributionTableView.setVisible(true);
                frequencyDistributionTableLabel.setText(GlobalContext.title);
            }
        }        
        if(GlobalContext.f3){
            if(GlobalContext.categoricalChoice){
                ObservableList<PieChart.Data> tempList = FXCollections.observableArrayList();
                for(secondlab.Data d : GlobalContext.categoricalData) {
                    tempList.add(new PieChart.Data(d.getValueLabel(), d.getPercentage()));
                }            
                pieChart.setData(tempList);
                pieChartLabel.setText(GlobalContext.title);
            }
            else{                
                
                histogram.setCategoryGap(0);
                histogram.setBarGap(0);
                series1 = new XYChart.Series();
                series1.setName(GlobalContext.title);
                ObservableList<XYChart.Data> tempList = FXCollections.observableArrayList();                
                for(secondlab.Data d : GlobalContext.numericData) {
                   tempList.add(new XYChart.Data(Float.toString(d.getMidpoints()), d.getFrequency()));                    
                }            
                series1.setData(tempList);                
            
                histogram.getData().addAll(series1);
                histChartLabel.setText(GlobalContext.title); 
            }
        }         
    }
    
    @FXML
    public void numericSelected() {
        if(GlobalContext.categoricalChoice)
            GlobalContext.setCategorical();
        GlobalContext.setNumeric();
    }
    
    @FXML
    public void categoricalSelected() {
        if(GlobalContext.numericChoice)
            GlobalContext.setNumeric();
        GlobalContext.setCategorical();
    }
}