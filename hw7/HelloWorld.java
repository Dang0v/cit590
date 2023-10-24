package hw7;
import java.util.Scanner; 

public class HelloWorld {
	public static void main(String[] args) {
		/*
		 * 1. Say Hello
		 * 
		 * Ask user's name.
		 * Print welcome message.
		 */
		
		System.out.println("Hello, World!");
		
		// Create a new scanner
		Scanner input = new Scanner(System.in);
		// Read user input
		System.out.println("Please enter your full name:");
		String fullname = input.nextLine();
		// Print the welcome message
		System.out.println("Hello, " + fullname + "!");
		System.out.println("\n");
		
		/*
		 * 2. Add Five Numbers
		 * 
		 * Ask for 5 number
		 * Print the sum with each input
		 */
		
		// initialize variables
		double sum = 0;
		// we need to handle the input as double or integer type
		int i = 0;
		double d = 0;
		// check if the input has double or integer type
		System.out.println("Please enter 5 numbers for sum: ");
		for (int n = 0; n < 5;) {
			// read the user input
			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				// check if input has type of integer
				i = input.nextInt();
				sum = sum + i;
				// go to next input
				++n;
				// print out sum
				System.out.println("sum: " + sum);
			} else if(input.hasNextDouble()) {
				// check if input has type of double
				d = input.nextDouble();
				sum = sum + d;
				// go to next input
				++n;
				// print out sum
				System.out.println("sum: " + sum);
			} else {
				// if input is invalid (not double or integer)
				// n will not increase, ask again
				System.out.println("Invalid input, enter again:");
			}
		}
		
		/*
		 * 3. Even or Odd
		 * 
		 * Ask user to enter an integer
		 * Check if the number is even or odd.
		 */
		System.out.println("Please enter an integer for odd or even check:");
		// loop for handling invalid input
		while (true) {
			// read the user input
			input = new Scanner(System.in);
			// check if input is an integer
			if (input.hasNextInt()) {
				i = input.nextInt();
				
				if (i % 2 == 0) {
					// check if even
					System.out.println(i + " is even");
				} else {
					// not even will be odd
					System.out.println(i + " is odd");
				}
				// break the loop
				break;
			} else {
				// handle invalid input
				System.out.println("Invalid input, enter again:");
			}
		}
		
		/*
		 * 4. Prime or Composite
		 * 
		 * Ask the user to enter a positive integer.
		 * Check if the number is prime or composite.
		 */
		System.out.println("Please enter an positive integer for prime or composite check:");
		// loop for handling invalid input
		while (true) {
			// read the input
			input = new Scanner(System.in);
			// input validation
			if (input.hasNextInt()) {
				i = input.nextInt();
				if (i > 0) {
					// valid input, check for prime or composite
					if (i == 1) {
						// if input is 1, print 1
						System.out.println(i);
					} else {
						// set a flag to true
						boolean primeflag = true;
						// check if prime
						for (int n = 2; n < i; ++n){
							if (i % n == 0) {
								// composite, set flag to false
								primeflag = false;
								// break the check loop
								break;
							}
						}
						// print the result
						if (primeflag) {
							System.out.println(i + " is prime.");
						} else {
							System.out.println(i + " is composite.");
						}
					}
					// get the result, break the loop
					break;
				} else {
					// not positive, invalid input
					System.out.println("Invalid input, enter again:");
				}
			} else {
				// not an integer, invalid input
				System.out.println("Invalid input, enter again:");
			}
		}
		
		/* 
		 * 5. Convert Seconds to Time
		 * Ask the user to enter some number of seconds
		 * Convert it to hours:minutes:seconds.
		 */
		
		System.out.println("Please enter an positive integer for converting:");
		// loop for handling invalid input
		while (true) {
			// read the input
			input = new Scanner(System.in);
			// input validation
			if (input.hasNextInt()) {
				i = input.nextInt();
				if (i >= 0) {
					// valid input	
					// convert
					int hours = i / 3600;
					int min = i % 3600 / 60;
					int sec = i % 3600 % 60;
					// print the result
					System.out.println(hours + " : " + min + " : " + sec);
					// break the input validation loop
					break;
				} else {
					// negative, invalid input
					System.out.println("Invalid input, enter again:");
				}
			} else {
				// not an integer, invalid input
				System.out.println("Invalid input, enter again:");
			}
		}
		
		// close the scanner
		input.close();
		
		/*
		 * 6. Say Goodbye
		 */
		
		System.out.println("Goodbye, " + fullname +"!");		
	}
}
