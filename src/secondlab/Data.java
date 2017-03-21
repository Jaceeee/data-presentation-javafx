/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Juan Carlos
 */
public class Data {
    private final SimpleStringProperty valueLabel;
    private final SimpleFloatProperty percentage;
    
    public Data(String valueLabel, Float percentage){
        super();
        this.valueLabel = new SimpleStringProperty(valueLabel);
        this.percentage = new SimpleFloatProperty(percentage);
    }
    
    public String getValueLabel(){
        return valueLabel.get();
    }
    
    public float getPercentage(){
        return percentage.get();
    }        
}
