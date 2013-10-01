public class GuessMyNumber
{
	public static void main(String[] args)
	{
		System.out.println("A, please type your number in.");
		int a_number = IO.readInt();
		
		System.out.println("B, please type your number in.");
		int b_number = IO.readInt();
		
		System.out.println("How many guesses are available?");
		int guesses = IO.readInt();
		
		int i = 0;
		
		int a_last_guess = 0, b_last_guess = 0;
		while (i < guesses)
		{
			System.out.println("A, guess B's number."); // we ask for the guess
			int a_guess = IO.readInt(); // we store the guess into this variable
			
			if (a_guess == b_number) // if they got it, they win
			{
				System.out.println("You got B's number!");
				break; // kills the program
			}
			else
			{
				if (a_last_guess == 0) // if there was no last guess
				{
					if (a_guess > b_number)
					{
						System.out.println("Lower!");
					}
					else
					{
						System.out.println("Higher!");
					}
					
				}
				else
				{
					if (Math.abs(b_number-a_last_guess) < Math.abs(b_number-a_guess))
					{
						System.out.println("Colder!");
					}
					else if (Math.abs(b_number-a_last_guess) > Math.abs(b_number-a_guess))
					{
						System.out.println("Warmer");
					}
					else if (a_last_guess == a_guess)
					{
						System.out.println("Idiot! You typed in the same number!");
					}
				}
				
				a_last_guess = a_guess;
				
			} // endif else
			
			System.out.println("B, guess A's number.");
			int b_guess = IO.readInt();

			if (b_guess == a_number)
			{
				System.out.println("You got A's number!");
				break;
			}
			else
			{
				if (b_last_guess == 0)
				{
					if (b_guess > a_number)
					{
						System.out.println("Lower!");
					}
					else
					{
						System.out.println("Higher!");
					}
					
				}
				else
				{
					if (Math.abs(a_number-b_last_guess) < Math.abs(a_number-b_guess))
					{
						System.out.println("Colder!");
					}
					else if (Math.abs(a_number-b_last_guess) > Math.abs(a_number-b_guess))
					{
						System.out.println("Warmer");
					}
					else if (b_last_guess == b_guess)
					{
						System.out.println("Idiot! You typed in the same number!");
					}
				}
				
				b_last_guess = b_guess;
			}
			
			i++; // turn count
			
		} //end while
	}
}