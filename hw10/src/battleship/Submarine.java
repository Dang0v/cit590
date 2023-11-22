/**
 * Class Ship Extend Class Submarine
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

public class Submarine extends Ship {
	
	private static int submarine_length = 1;
	private String shipType = "submarine";
	
	/**
	 * Set the length of submarine
	 */
	public Submarine() {
		super(submarine_length);
	}
	
	/**
	 * @return Strings of ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
	
}