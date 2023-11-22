/**
 * Class BattleshipGame
 * Include main process of game
 * 
 * @author Qiwen Luo
 */

package battleship;

import java.util.Scanner;

public class BattleshipGame {
	
	public static void main(String[] args) {
		
		// add a scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		// initialize a new ocean
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		// welcome message 
		System.out.println("Welcome to battleship game!");
		
		// starts the game
		while(!ocean.isGameOver()) {
			System.out.println();
			ocean.print();
			ocean.printWithShips();
			
			// player shooting
			// get the input
			int[] position = getInput(scanner);
			int row = position[0];
			int column = position[1];
			System.out.println("You are shooting at: " + row + ", " + column);
			
			// apply shooting
			if (ocean.shootAt(row, column)) {
				System.out.println("Hit!");
				if(ocean.getShipArray()[row][column].isSunk()) {
					System.out.println("You just sank a ship - (" + ocean.getShipArray()[row][column].getShipType() + ")");
				}
			}
			// print game message
			System.out.println("Number of shots: " + ocean.getShotsFired());
			System.out.println("Count of hits : " + ocean.getHitCount());
			System.out.println("Sunk ships : " + ocean.getShipsSunk());			
		}
		System.out.println();
		System.out.println("Game Over!");
		System.out.println("You have shot for " + ocean.getShotsFired() + " times to win the game!");
		
		scanner.close();
		
	}
	
	/**
	 * Input handling function. Keeping getting user input until valid.
	 * @param scanner
	 * @return An integer array with row and column
	 */
	static int[] getInput(Scanner scanner) {
		int[] position = new int[2];
		// input validation
		boolean validinput = false;
		while (!validinput) {
			System.out.println("Please enter  row, column:");
			String input = scanner.nextLine();
			// format the input
			String[] input_split = input.split(",");
			String[] input_formatted = new String[input_split.length];
			for (int i = 0; i < input_split.length; i++) {
				input_formatted[i] = input_split[i].trim();
			}
			// check number of input variables
			if (input_split.length != 2) {
				System.out.println("Invalid input, try again");
			// correct number of input variables
			} else {
				// check if integer
				try {
					position[0] = Integer.parseInt(input_formatted[0]);
					position[1] = Integer.parseInt(input_formatted[1]);
					// input is in the range, will jump out of the input loop
					if (position[0] > -1 && position[0] < 10 && position[1] > -1 && position[1] < 10)
						validinput = true;
					else
						System.out.println("Invalid input, try again");
				} catch (NumberFormatException e) {
					// invalid input, will not jump out of the input loop
					System.out.println("Invalid input, try again");
				}
			}
		}
		return position;
	}
}