/**
 * Class Ocean
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

import java.util.Random;

public class Ocean{
	
	// Used to quickly determine which ship is in any given location
	private Ship[][]ships = new Ship[10][10];
	// The total number of shots fired by the user
	private int shotsFired;
	// The number of times a shot hit a ship.
	private int hitCount;
	// The number of ships sunk
	private int shipsSunk;
	
	// Position fired
	private boolean[][] fired = new boolean[10][10];

	/**
	 * Creates an empty ocean.
	 * Initialize all parameters.
	 */
	public Ocean() {
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		// fill the ship array with empty sea and fired position
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new Emptysea();
				ships[i][j].placeShipAt(i, j, true, this);
				fired[i][j] = false;
			}
		}
		
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 * Place larger ships before smaller ones
	 */
	void placeAllShipsRandomly() {
		Random rand = new Random();
		
		final int number_battleship = 1;
		final int number_cruiser = 2;
		final int number_destroyer = 3;
		final int number_submarine = 4;
		
		
		// one battleship
		for(int i = 0; i < number_battleship;) {
			Battleship battleship = new Battleship();
			int row_random = rand.nextInt(10);
			int column_random = rand.nextInt(10);
			boolean horizontal_random = rand.nextBoolean();
			if (battleship.okToPlaceShipAt(row_random, column_random, horizontal_random, this)) {
				battleship.placeShipAt(row_random, column_random, horizontal_random, this);
				i++;
			}
		}
		
		// two cruises
		for(int i = 0; i < number_cruiser;) {
			Cruiser cruiser = new Cruiser();
			int row_random = rand.nextInt(10);
			int column_random = rand.nextInt(10);
			boolean horizontal_random = rand.nextBoolean();
			if (cruiser.okToPlaceShipAt(row_random, column_random, horizontal_random, this)) {
				cruiser.placeShipAt(row_random, column_random, horizontal_random, this);
				i++;
			}
		}
		
		// three destroyers
		for(int i = 0; i < number_destroyer;) {
			Destroyer destroyer = new Destroyer();
			int row_random = rand.nextInt(10);
			int column_random = rand.nextInt(10);
			boolean horizontal_random = rand.nextBoolean();
			if (destroyer.okToPlaceShipAt(row_random, column_random, horizontal_random, this)) {
				destroyer.placeShipAt(row_random, column_random, horizontal_random, this);
				i++;
			}
		}
		
		// four submarines
		for(int i = 0; i < number_submarine;) {
			Submarine submarine = new Submarine();
			int row_random = rand.nextInt(10);
			int column_random = rand.nextInt(10);
			boolean horizontal_random = rand.nextBoolean();
			if (submarine.okToPlaceShipAt(row_random, column_random, horizontal_random, this)) {
				submarine.placeShipAt(row_random, column_random, horizontal_random, this);
				i++;
			}
		}
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return boolean of occupied status
	 */
	boolean isOccupied(int row, int column) {
		// no need to check the boundary of array
		if (ships[row][column].getShipType().equals("empty"))
			return false;
		else
			return true;
	}
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat.
	 * Updates the number of shots that have been fired, and the number of hits.
	 * @param row
	 * @param column
	 * @return boolean of hit status
	 */
	boolean shootAt(int row, int column) {
		// add one to shots fired
		this.shotsFired++;
		// change fired status
		fired[row][column] = true;
		
		// check if there is a ship
		if (this.isOccupied(row, column)) {
			// check if this ship is sunk
			if (this.ships[row][column].isSunk()) {
				// shoot is invalid
				return false;
			// if this ship is still afloat
			} else {
				// shoot at this point
				if (this.getShipArray()[row][column].shootAt(row, column)) {
					this.hitCount++;
				}
				// check sunk again, update count number
				if (this.ships[row][column].isSunk())
					this.shipsSunk++;
				// shoot is valid
				return true;
			}
		// there is no ship, sunk or not.
		} else {
			return false;
		}	
	}
	
	/**
	 * Returns the number of shots fired (in the game)
	 * @return integer of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}

	int getHitCount() {
		return this.hitCount;
	}
	
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	boolean isGameOver() {
		if (this.shipsSunk == 10)
			return true;
		else
			return false;
	}

	public Ship[][] getShipArray() {
		return this.ships;
	}
	
	/**
	 * print the ocean
	 * ‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
	 * ‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing there.
	 * ‘s’: Use ‘s’ to indicate a location containing a sunken ship. 
	 * '.’: Use ‘.’ (a period) to indicate a location that you have never fired upon.
	 * 
	 */
	public void print() {
		// print the first row
		for(int i = 0; i < 11; i++) {
			if (i == 0) System.out.print(' ');
			else System.out.print(i - 1);
		}
		System.out.println();
		
		// print the ship and first column
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			for (int j = 0; j < 10; j++) {
				// if this position is fired, print toString
				if (fired[i][j])
					System.out.print(this.ships[i][j].toString());
				// if not fired, print '.'
				else
					System.out.print('.');
			}
			System.out.println();
		}
		
	}
	
	/**
	 * USED FOR DEBUGGING PURPOSES ONLY.
	 * ‘b’: Use ‘b’ to indicate a Battleship.
	 * ‘c’: Use ‘c’ to indicate a Cruiser.
	 * ‘d’: Use ‘d’ to indicate a Destroyer.
	 * ‘s’: Use ‘s’ to indicate a Submarine.
	 */
	void printWithShips() {
		// print the first row
		for(int i = 0; i < 11; i++) {
			if (i == 0) System.out.print(' ');
			else System.out.print(i - 1);
		}
		System.out.println();
		
		// print the ship and first column
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			for (int j = 0; j < 10; j++) {
				switch(this.ships[i][j].getShipType()) {
				case "battleship":
					System.out.print('b');
					break;
				case "cruiser":
					System.out.print('c');
					break;
				case "destroyer":
					System.out.print('d');
					break;
				case "submarine":
					System.out.print('s');
					break;
				case "empty":
					System.out.print(' ');
					break;
				default:
					// invalid input do nothing
					break;
				}
			}
			System.out.println();
		}
		
	}
	
	
	
	

}