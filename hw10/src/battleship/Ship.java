/**
 * Class Ship
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

abstract class Ship {
	/**
	 * The row that contains the bow (front part of the ship)
	 */
	private int bowRow;
	
	/**
	 * The column that contains the bow (front part of the ship)
	 */
	private int bowColumn;

	/**
	 * The length of the ship
	 */
	private int length;
	
	/**
	 * A boolean that represents whether the ship is going to be placed horizontally or vertically
	 */
	private boolean horizontal;
	
	/**
	 * An array of booleans that indicate whether that part of the ship has been hit or not
	 */
	private boolean[] hit;
	
	/**
	 * This constructor sets the length property of the particular ship and initializes
	 * the hit array based on that length.
	 * @param length
	 */
	public Ship(int length) {
		// initialize
		this.length = length;
		// set hit boolean
		this.hit = new boolean[this.length];
		for (int i = 0; i < this.length; i++) {
			hit[i] = false;
		}
	}

	/**
	 * Returns the ship length
	 * @return integer of length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Returns the row corresponding to the position of the bow
	 * @return integer of row
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * Returns the bow column location
	 * @return integer of column
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * Returns the hit array
	 * @return boolean of hit status
	 */
	public boolean[] getHit() {
		return this.hit;
	}
 
	/**
	 * Returns whether the ship is horizontal or not
	 * @return boolean of horizontal status
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}	
	
	/**
	 * Sets the value of bowRow
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Sets the value of bowColumn
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Sets the value of the instance variable horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Returns the type of ship as a String.
	 * @return String of ship type
	 */
	public abstract String getShipType();
	
	/**
	 *  Based on the given row, column, and orientation, returns true if it is okay to put a
	 *  ship of this length with its bow in this location; false otherwise.
	 *  
	 *  The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
	 *  and it must not ”stick out” beyond the array.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		Ship [][] ships = ocean.getShipArray();
		// horizontal case
		if (horizontal) {
			// out of range
			// row bound
			if (row < 0 || row > 9) return false;
			// column bound
			if (column - this.length < 0 || column > 9) return false;
			
			// check if there is any ship touch or overlap
			// check row, the upper row and lower row should be empty
			for (int i = row - 1; i < row + 2; i++) {
				for (int j = column + 1; j > column - this.length - 1; j--) {
					// make sure the validation will not out of ship array
					if (i >= 0 && i < 10 && j >= 0 && j < 10) {
						if (!(ships[i][j].getShipType().equals("empty"))) {
							return false;
						}
					}
				}
			}
		// vertical case
		} else {
			// out of range
			// row bound
			if (row - this.length < 0 || row > 9) return false;
			// column bound
			if (column < 0 || column > 9) return false;
			
			// check if there is any ship touch or overlap
			// check row, the upper row and lower row should be empty
			for (int j = column - 1; j < column + 2; j++) {
				for (int i = row + 1; i > row - this.length - 1; i--) {
					// make sure the validation will not out of ship array
					if (i >= 0 && i < 10 && j >= 0 && j < 10) {
						if (!(ships[i][j].getShipType().equals("empty"))) {
							return false;
						}
					}
				}
			}
		}
		
		
		return true;
	}
	
	/**
	 * “Puts” the ship in the ocean.
	 * horizontal ships face East (bow at right end) and vertical ships face South (bow at bottom end).
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		// horizontal case
		if (horizontal) {
			for (int j = column; j > column - this.length; j--) {
				ocean.getShipArray()[row][j] = this;
			}
		// vertical case
		} else {
			for (int i = row; i > row - this.length; i--) {
				ocean.getShipArray()[i][column] = this;
			}
		}
		
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship has not been 
	 * sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates the
	 * bow) and return true; otherwise return false.
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		// horizontal case
		if (this.horizontal) {
			// check row
			if (row == this.bowRow) {
				// check column
				if (column <= this.bowColumn && column > this.bowColumn - this.length) {
					// set hit array
					hit[this.bowColumn - column] = true;
					return true;
				}	
			}
		// vertical case
		} else {
			// check column
			if (column == this.bowColumn) {
				// check row
				if (row <= this.bowRow && row > this.bowRow - this.length) {
					// set hit array
					hit[this.bowRow - row] = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return
	 */
	boolean isSunk() {
		for (int i = 0; i < this.length; i++) {
			if (hit[i] == false) return false;
		}
		return true;
	}
	
	/**
	 * Returns a single-character String to use in the Ocean’s print method.
	 *  ”s” if the ship has been sunk.
	 *  ”x” if it has not been sunk.
	 */
	@Override
	public String toString() {
		if(this.isSunk()) return "s";
		else return "x";
	}
	
}