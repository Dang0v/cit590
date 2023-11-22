/**
 * Class Ship Extend Emptysea
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

public class Emptysea extends Ship{
	
	private static int emptysea_length = 1;
	private String shipType = "empty";
	
	/**
	 * Set the length of empty sea
	 */
	public Emptysea() {
		super(emptysea_length);
	}
	
	/**
	 * always returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 *  always returns false
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Returns the single-character “-“
	 */
	@Override
	public String toString() {
		return "-";
	}
	
	/**
	 * @return Strings of ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
	
	
}