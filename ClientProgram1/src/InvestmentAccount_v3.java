//Here is our first illustration of
//how to write instantiable classes.

//In the third version, we add an alternate
//constructor method, and a method to project
//the future peformance of the fund.

public class InvestmentAccount_v3
{
  
  
  private double balance;
  private double interestRate;
  private double monthlyDeposit;
  
  //We supply two constructors:  One, with
  //no arguments, sets the fields to the default
  //amount.  The other sets the fields to specified
  //amounts.
  
  public InvestmentAccount_v3()
  {
    balance = 1000;
    interestRate=4.75;
    monthlyDeposit = 100;
  }
  
  public InvestmentAccount_v3(double bal, double intr, double md)
  {
    monthlyDeposit = md;
    balance = bal;
    interestRate = intr;
  }
  
  //This method returns the current balance.
  
  public double getBalance()
  {
    return balance;
  }
  
  public double getMonthlyDeposit()
  {
    return monthlyDeposit;
  }
  
  public double getInterestRate()
  {
    return interestRate;
  }
  
  //This method applies the monthly deposit
  public void makeDeposit()
  {
    balance = (1+interestRate/1200)*balance + monthlyDeposit;
  }
  
  public void makeDeposit(double x)
  {
    balance = (1+interestRate/1200)*balance + x;
     //notice that we can use the same variable
    //name x in several different methods.
    //The effect is the same as if we had used
    //different names.
  }
  
  public void setBalance(double x)
  {
    balance = x;
   
  }
  
  public void setInterestRate(double x)
  {
    interestRate = x;
  }
  
  public void setMonthlyDeposit(double x)
  {
    monthlyDeposit = x;
  }
  
  public void setAccountData(double bal, double intr, double md)
  {
    monthlyDeposit = md;
    balance = bal;
    interestRate = intr;
  }
  
  //What will the balance be in y years, assuming
  //the same level of monthly deposit and no changes
  //to the interest rate?
  
  //Rather than writing a loop to simulate each deposit,
  //we use a well-known formula for this.
  
  public double predictPerformance(int y)
  {
    int numMonths = 12*y;
    double monthlyRate = interestRate/1200;
    double multiplier = Math.pow(1+monthlyRate,numMonths);
    return balance*multiplier + monthlyDeposit*(multiplier-1)/monthlyRate;
  }
}
  