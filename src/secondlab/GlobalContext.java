/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondlab;

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
    
    static boolean choiceSelected( ){
        if(!categoricalChoice && !numericChoice) {
            return false;
        } else {
            return true;
        }
    }        
}
