// Daphne Lin

import java.util.Scanner;

import javax.swing.JOptionPane;

import static java.lang.Math.*;

public class DayBorn
{
    public static void main(String[] args)
    {
      
    	String promptString = "Please enter the date in this form: MM/DD/YYYY";
        String aString = JOptionPane.showInputDialog(promptString);
      
      // Declaring all VARS used in this class.
      
      double m, d, y, am, ay, c, ac, cy, mc;
        
      // Asking for user input, and scanning them into variables.
      
      m = aString.charAt(0)+aString.charAt(1);
      d = aString.charAt(3)+aString.charAt(4);
      y = aString.charAt(6)+aString.charAt(7)+aString.charAt(8)+aString.charAt(9);
      
      // Calculations.
      
      am = ((m+9)%12)+4;
      ay = y-(am/14);
      c = ay/100;
      cy = ay%100;
      mc = (am*26)/10;
      
      // Showing output.
                
      System.out.println("The day you were born was:");
      String bString = "The day you were born was: "+Math.round(((d+mc+cy+(cy/4)+(c/4)+(c*5)+6)%7));
      JOptionPane.showMessageDialog(null, bString);
     }
}