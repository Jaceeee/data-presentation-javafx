/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

import java.util.Arrays;

/**
 *
 * @author Juan Carlos
 */

public class GlobalContext {
    public static final GlobalContext gc = new GlobalContext();
    public static boolean numericChoice = false;
    public static boolean categoricalChoice = false;
    public static int n;
    public static byte inputType;
    public static String title;
    public static String[] array;
    public static int counter = 0;
    public static String errorMessage = "";
    public static Data[] data;
    public static boolean f1,f2;
    
    public GlobalContext getInstance(){
        return gc;
    }
    
    public static void initialize(){
        numericChoice = false;
        categoricalChoice = false;
        n = 0;
        inputType = 0;
        title = "";
        array = new String[0];
        counter = 0;
    }        
        
    
    public static void setCategorical() {
        categoricalChoice = (categoricalChoice) ? false : true;
    }
    
    public static void setNumeric() {
        numericChoice = (numericChoice) ? false : true;
    }
    
    static boolean choiceSelected() {
        if(!categoricalChoice && !numericChoice) {
            return false;
        } else {
            return true;
        }
    }        
    
//    removes duplicates
    public static String[] removeDuplicates() {
        String[] array2 = array;
        String[] array3;

        Arrays.sort(array2);       
        int current = 0;

        for(int i = 0; i<n; i++) {
            if(!array2[i].equals(array2[current])){
                array2[++current] = array2[i];
            }
        }

        array3 = new String[current+1];
        
        for(int i = 0; i < current+1; i++) {
            array3[i] = array2[i];
        }

        return array3;
    }
   
//   returns the float data percentages for the table (categorical data)
    public static Float[] percentageComp(){       
       String[] array2 = array;
       Arrays.sort(array2);       
       
       String[] array3 = removeDuplicates();
       Float[] a = new Float[array3.length];
       
       for(int i = 0, counter; i<array3.length; i++){
           counter = 0;
           for(int j = 0; j<array2.length; j++) {
               if(array2[j].equals(array3[i])) {
                   counter++;
               }
           }
           a[i] = new Float(((float)counter/(float)n));
       }
       
       return a;
   }
   
    public static void setData(){
        if(categoricalChoice){
            String[] valueLabels = removeDuplicates();
            Float[] percentages = percentageComp();
            data = new Data[valueLabels.length];
            
            System.out.println(valueLabels.length);
            for(int i = 0; i < data.length; i++) {
                data[i] = new Data(valueLabels[i],percentages[i]*100);
            }
        } else {
            
        }
    }
}