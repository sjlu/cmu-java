// Daphne Lin
// CO: SLUZORZ

import java.util.Scanner;

public class Rumplestiltskin
{
  public static void main(String[] args)
  { 
    String nameString;
    do
    {
      System.out.println ("What's my name?");
      Scanner keyboard = new Scanner(System.in);
      nameString = keyboard.nextLine();
      if (nameString.toLowerCase().equals("rumplestiltskin")) // String to string comparisons require a function.
        break; // This kills the "do" loop once the guy has got the name right.
      else
    	System.out.println ("You = wrong. That ain't my name.");
    } while(true); // continue looping until the "break" command has been recieved.
    
    System.out.println ("That's my name. T_T"); // Once he gets it right, the loop has finally ended and we therefore can print this message.
      
  }
}