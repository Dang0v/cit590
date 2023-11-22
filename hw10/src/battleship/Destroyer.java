/**
 * Class Ship Extend Class Destroyer
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

public class Destroyer extends Ship {
	
	private static int destroyer_length = 2;
	private String shipType = "destroyer";
	
	/**
	 * Set the length of destroyer
	 */
	public Destroyer() {
		super(destroyer_length);
	}
	
	/**
	 * @return Strings of ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
	
}