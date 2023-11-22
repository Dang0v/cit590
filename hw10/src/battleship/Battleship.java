/**
 * Class Ship Extend Class Battleship
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

public class Battleship extends Ship {
	
	private static int battleship_length = 4;
	private String shipType = "battleship";
	
	/**
	 * Set the length of battleship
	 */
	public Battleship() {
		super(battleship_length);
	}
	
	/**
	 * @return Strings of ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
	
}