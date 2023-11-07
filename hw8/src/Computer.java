package hw8;

import java.util.Random;

public class Computer {
	
	/**
	 * The score of the computer
	 */
	private int score = 0;
	
	/**
	 * Controls computer player to roll the dice, and prints the related information for each roll
	 * First of all, this method will automatically roll one time for the computer player, 
	 * if the result is 6, the computer will have no right to roll and this method should return 0; 
	 * else, this roll will be added to the total score for this turn. 
	 * The computer should play intelligently to determine whether they want to roll the dice again 
	 * or stop with the current score.  It depends on you how to design the strategy for the computer.
	 * 
	 * This method will also update the computer's total score.
	 * -- You can either add the total of all the rolls to the computer's total score, within this move method
	 * e.g. this.score += scoreOneRound;
	 *  
	 * -- or you can call the setScore method from outside of this class, after calling this move method
	 * e.g. int scoreOneRound = computer.move(human, random);
	 *      computer.setScore(scoreOneRound + computer.getScore());
	 * 
	 * @param human player
	 * @param random number generator
	 * @return the score this round (for example, 
	 * 1. the computer rolls: 2, 6, then this method should return 0;
	 * 2. the computer rolls: 5, 5, 2, then this method should return 12;
	 * )
	 */
	public int move(Human human, Random random) {
		// initialize parameter
		int scorethisround = 0;
		/*
		 * Rolling a dice loop
		 * 
		 * 1. When value is 6 return 0
		 * 2. else add to score of this round and decide to roll again or not.
		 */
		while(true) {
			// generate a random integer from 1 to 6
			int dice = random.nextInt(6) + 1;
			System.out.println("Computer's roll: " + dice);
			// when the random number equals to 6, return 0
			if(dice == 6) {
				System.out.println("Computer's score in this round: 0");
				return 0;
			// when the random number does not equal to 6, computer make decision
			} else {
				// first add new random number to score of this round
				scorethisround += dice;
				int tempscore = scorethisround + this.score;
				/*
				 * Computer play intelligently
				 * 
				 * If former score is less than 50 and temporary score is larger than 50, stop rolling.
				 * If former score is bigger than 50 which means 2 player are tied above 50, keep rolling at 50%.
				 * 
				 * If the computer's score is less than human, computer will more possibly to roll again
				 * Else computer will less possibly to roll again
				 */
				// first reach 50, stop rolling.
				if(this.score < 50 && tempscore >= 50) {
					System.out.println("Computer's score in this round: " + scorethisround + "\n");
					return scorethisround;
				}
				if(this.score > 50) {
					if (random.nextInt(2) > 0) {
						System.out.println("Computer's score in this round: " + scorethisround);
						return scorethisround;
					}
				}
				// computer takes the lead
				if(tempscore > human.getScore()) {
					// computer will have 25% possibility to roll again
					// if score in this round larger than 5 then stop
					if (scorethisround >= 5 || random.nextInt(4) + 1 <= 3) {
						System.out.println("Computer's score in this round: " + scorethisround);
						return scorethisround;
					}
				// computer falls behind
				} else {
					// computer has 75% possibility to roll again
					// if score in this round larger than 50 then stop
					if (scorethisround >= 12 || random.nextInt(4) + 1 > 3) {
						System.out.println("Computer's score in this round: " + scorethisround);
						return scorethisround;
					}
				}
			}
		}
	}
	
	
	/**
	 * Get the score of computer
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Set the score of computer
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
