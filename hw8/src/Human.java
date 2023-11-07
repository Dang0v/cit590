package hw8;

import java.util.Random;
import java.util.Scanner;

public class Human {
	
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The score of the current player
	 */
	private int score = 0;
	
	
	/**
	 * Constructs a human player with the given name
	 * @param name of human
	 */
	public Human(String name) {
		this.name = name;
	}

	/**
	 * Controls human player to roll the dice, and prints the related information for each roll.
	 * First of all, this method will automatically roll one time for the human player, 
	 * if the result is 6, the player will have no right to roll again and this method should return 0; 
	 * else, this roll will be added to the total score for this turn.
	 * User should answer Y or N (y or n) to determine whether they want to roll the dice again 
	 * or stop with the current score.
	 * 
	 * This method will also update the human's total score.
	 * -- You can either add the total of all the rolls to the human's total score, within this move method
	 * e.g. this.score += scoreOneRound;
	 *  
	 * -- or you can call the setScore method from outside of this class, after calling this move method 
	 * e.g. int scoreOneRound = human.move(computer, random, sc);
	 *      human.setScore(scoreOneRound + human.getScore());
	 * 
	 * @param computer player
	 * @param random number generator
	 * @param sc to get input from user
	 * @return the score this round (for example, 
	 * 1. the player rolls: 2, 6, then this method should return 0
	 * 2. the player rolls: 5, 5, 2, then this method should return 12
	 * )
	 */
	public int move(Computer computer, Random random, Scanner sc) {	
		// initialize parameter
		int scorethisround = 0;
		/*
		 * Rolling a dice loop
		 * 
		 * 1. When value is 6 return 0
		 * 2. else add to score of this round and then ask user for next roll.
		 */
		while(true) {
			// generate a random integer from 1 to 6
			int dice = random.nextInt(6) + 1;
			System.out.println(this.name + "'s roll: " + dice);
			// when the random number equals to 6, return 0
			if(dice == 6) {
				System.out.println(this.name + "'s score in this round: 0");
				return 0;
			// when the random number does not equal to 6, ask user
			} else {
				// first add new random number to score of this round
				scorethisround += dice;
				// ask user for next roll
				System.out.println("Do you want to roll again? (Y/N)");
				/*
				 * Handle user input
				 * 
				 * Check the first letter of input
				 * 1. 'n' or 'N' stop asking and return
				 * 2. 'y' or 'Y' break the handling loop to roll a dice
				 * 3. invalid input will continue ask for input
				 */
				while(true) {
					// input buffer for processing
					String inputString = sc.nextLine();
					// check the first char of input
					if (inputString.charAt(0) == 'N' || inputString.charAt(0) == 'n') {
						System.out.println(this.name + "'s score in this round: " + scorethisround);
						// return score of this round
						return scorethisround;
					} else if(inputString.charAt(0) == 'Y' || inputString.charAt(0) == 'y') {
						// break the loop, go to rolling a dice
						break;
					} else {
						// keep in loop, ask for user input
						System.out.println("Invalid input, try again.");
					}
				}
			}
		}
	}
	
	/**
	 * Get the name of human
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the score of human
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set the score of human
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
