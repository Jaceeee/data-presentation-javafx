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
            array3 = array2;
        }

        return array3;
    }
   
//   returns the float data percentages for the table (categorical data)
    public static float[] percentageComp(){       
       String[] array2 = array;
       Arrays.sort(array2);       
       
       String[] array3 = removeDuplicates();
       float[] a = new float[array3.length];
       
       for(int i = 0, counter = 0; i<n; i++){
           for(int j = 0; j<n; j++){
               if(array2[j].equals(array3[i])){
                   counter++;
               }
           }
           a[i] = counter/array2.length;
       }
       
       return a;
   }
   
}