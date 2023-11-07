package hw8;

import java.util.Random;
import java.util.Scanner;

public class GameControl {
	
	/**
	 * Computer player
	 */
	Computer computer;
	
	/**
	 * Human player
	 */
	Human human;
	
	/**
	 * A random number generator to be used for returning random dice result (1-6) for both computer and human player
	 */
	Random random = new Random();
	
	/**
	 * The main method just creates a GameControl object and calls its run method
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Welcome to Pig!");
		
		GameControl gc = new GameControl();
		while (true) {
			gc.run(sc);
			System.out.println("--------------------");
			System.out.println("Do you want to play again?");
			
			boolean check = gc.askYesNo(sc);
			if (!check) {
				System.out.println("Goodbye!");
				sc.close();
				break;
			}
		}
	}
	
	/**
     * Calls methods to perform each of the following actions:
     * - Ask user to input the name for human player, and print welcome with the name
     * - Create the players (one human and one computer)
     * - Control the play of the game
     * - Print the final results
	 * @param sc to use for getting user input
	 */
	public void run(Scanner sc) {
		// ask user to enter name
		System.out.println("\nPleas enter your name");
		// create players
		createPlayers(sc.nextLine());
		computer = new Computer();
		// print welcome message
		System.out.println("Welcome " + human.getName());
		
		/*
		 * game loop
		 * 
		 * Break when one of player has won.
		 * 
		 * Only check winning state after both player has finished rolling,
		 * because second player will get one more turn.
		 * 
		 * Computer players will always go first.
		 * 
		 */
		while(true) {
			// computer plays
			computer.setScore(computer.getScore() + computer.move(human, random));
			// print compute's score
			System.out.println("Computer's total score is :" + computer.getScore() + "\n");
			// human plays
			human.setScore(human.getScore() + human.move(computer, random, sc));
			// print human score
			System.out.println(human.getName() + "'s total score is :" + human.getScore() + "\n");
			// check winning
			if(this.checkWinningStatus()) break;
		}
		// Game over, print the result
		this.printResults();
	}
	
	/**
     * Creates one human player with the given humanName, and one computer player with a name.
     * @param humanName for human player
     */
	public void createPlayers(String humanName) {
		human = new Human(humanName);
	}
	
	/**
     * Checks if a winning status has been achieved
     * @return true if one player has won the game
     */
	public boolean checkWinningStatus() {
		// tie will not end the game
		if (human.getScore() == computer.getScore()) {
			return false;
		}
		// one of player reach or exceed 50, end the game
		if (human.getScore() >= 50 || computer.getScore() >= 50) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Prints the final scores of the human player and computer player
	 */
	public void printResults() {
		System.out.println("Human gets " + human.getScore());
		System.out.println("Computer gets " + computer.getScore());
	}
	
	/**
     * Determines who won the game, and prints the results
     */
	public void printWinner() {
		// There won't be a tie here
		if (human.getScore() > computer.getScore()) {
			System.out.println("Human wins!");
		} else {
			System.out.println("Computer wins!");
		}
	}
	
	/**
	 * If the user responds a string starting with "y" or "Y", the function returns True. 
	 * If the  user responds a string starting with "n" or "N", the function returns False. 
	 * Any other response will cause the question to be repeated until the user provides an acceptable response. 
	 * @param sc to use for getting user input
	 * @return true if user responds with "y" or "Y"
	 */
	public boolean askYesNo(Scanner sc) {
		while(true) {
			String inputString = sc.nextLine();
			if (inputString.charAt(0) == 'N' || inputString.charAt(0) == 'n' ) {
				return false;
			} else if (inputString.charAt(0) == 'Y' || inputString.charAt(0) == 'y' ) {
				return true;
			} else {
				System.out.println("Invalid input, please try again.");
			}
		}	
	}
	
}
