//Write a Java application that uses our InvestmentAccount_v3 class to do the following:

//Ask the user for a starting balance, an interest rate, and a monthly withdrawal amount, 
  //and print how many times you can take that amount from the account before the balance is exhausted.  
  //The answer should be an integer---so, for example, if the monthly withdrawal amount is $100, 
  //and after 17 withdrawals the balance is $97.63, then the answer is 17, not 18 or 17.9763.
//Here are restrictions on how you are to do this:

    //* You cannot, absolutely can not change the class definition of InvestmentAccount_v3.  
  //You have to use it exactly the way it is.  
  //You may have noticed that there is no method for making a withdrawal in this class, but I'm sure you can figure that one out!
    //* You should hand the work of the main method off to two sttic helper methods:  
  //First, you should steal the method getAccountFromInput() in the application CompareAccounts on the website.  
  //Just copy and paste it.  Second, you should have a method int withdrawalsPossible(InvestmentAccount_v3) 
  //that returns the answer, which just gets printed by the main method.

  // DL

import static java.lang.Math.*;
import java.util.Scanner;

public class ClientProgram1
{
  public static void main(String[] args)
  { 
    
    CompareAccounts myInput = new CompareAccounts ();
                         
     double bal = 100; // ask for this
     double intRate = 0.02; // ask for this
    InvestmentAccount_v3 myAccount = new InvestmentAccount_v3(bal,intRate,0); //because there are no depos
    
    double mWidthdrawl = 50; // ask for this later
    
    double initBalance = myAccount.getBalance();
    double initIntRate = myAccount.getInterestRate();
    
    double widthdrawlsPossible = 0;
    
    while (initBalance > 0) {
    	initBalance = (1+initIntRate)*initBalance - mWidthdrawl;
    	if (initBalance > 0) {
    		widthdrawlsPossible++;
    	}
    }
    
    System.out.printf("You can widthdrawl %d...",widthdrawlsPossible);

    }
  }
