/**
 * Class Ship Extend Class Cruiser
 * 
 * 
 * @author Qiwen Luo
 */


package battleship;

public class Cruiser extends Ship {
	
	private static int cruiser_length = 3;
	private String shipType = "cruiser";
	
	/**
	 * Set the length of cruiser
	 */
	public Cruiser() {
		super(cruiser_length);
	}
	
	/**
	 * @return Strings of ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
	
}