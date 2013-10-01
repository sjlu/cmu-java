//Here is a fancier application of our InvestmentAccount_v3
//class.  We will create two accounts and compare their long
//term performance.

//We use private 'helper' methods here.  The
//work of getting the input and using it to create
//classes, as well as that of printing a report of
//the results, is handled by these additional private
//methods.

import java.util.Scanner;

public class CompareAccounts
{
  
  public static void main(String[] args)
  {
    //Here is what a main method really
    //should look like.
    InvestmentAccount_v3 acct1, acct2;
    System.out.println("Enter data for first account:");
    acct1 = getAccountFromInput();
    System.out.println("Enter data for second account:");
    acct2 = getAccountFromInput();
    printReport(acct1,acct2);
  }
  
  //This prompts the user for account input and returns an
  //InvestmentAccount_v3 object initialized with the entered 
  //data.
  
  //This method is "static" because it is not associated with
  //an instance of the class CompareAccounts---CompareAccounts is
  //a non-instantiable class.
  
  //On the other hand, you might well have private helper methods
  //in an instantiable class, in which case they would not include
  //the modifier 'static'.
  private static InvestmentAccount_v3 getAccountFromInput()
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter beginning balance, annual interest (in percent),");
    System.out.println("and monthly deposit.");
    double bal = keyboard.nextDouble();
    double intr = keyboard.nextDouble();
    double md = keyboard.nextDouble();
    return new InvestmentAccount_v3(bal,intr,md);
  }
  
  private static void printReport(InvestmentAccount_v3 acct1, InvestmentAccount_v3 acct2)
  {
    System.out.println("years\t\taccount 1\t\taccount 2");
    for(int j=5;j<=20;j+=5)
    {
      System.out.print(j+"\t\t");
      System.out.print(Math.round(100*acct1.predictPerformance(j))/100.0+"\t\t");
      System.out.println(Math.round(100*acct2.predictPerformance(j))/100.0);
    }    
  }
}