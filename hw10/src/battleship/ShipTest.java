/**
 * Class ShipTest
 * 
 * 
 * @author Qiwen Luo
 */


package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		// test for cruiser
		Ship ship2 = new Cruiser();
		assertEquals(3, ship2.getLength());
		
		// test for destroyer
		Ship ship3 = new Destroyer();
		assertEquals(2, ship3.getLength());
		
		// test for submarine
		Ship ship4 = new Submarine();
		assertEquals(1, ship4.getLength());
		
		// test for Emptysea
		Ship ship5 = new Emptysea();
		assertEquals(1, ship5.getLength());
		

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		// test another type of ship
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// test another type of ship
		Ship destroyer = new Destroyer();
		row = 5;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
		// test another type of ship
		Ship submarine = new Submarine();
		row = 6;
		column = 9;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		// test another type of ship
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		// test another type of ship
		Ship destroyer = new Destroyer();
		row = 5;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		// test another type of ship
		Ship submarine = new Submarine();
		row = 6;
		column = 9;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits1 = new boolean[4];
		assertArrayEquals(hits1, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		// test for cruiser
		Ship ship2 = new Cruiser();
		boolean[] hits2 = new boolean[3];
		assertArrayEquals(hits2, ship2.getHit());
		assertFalse(ship2.getHit()[0]);
		assertFalse(ship2.getHit()[1]);
		
		// test for destroyer
		Ship ship3 = new Destroyer();
		boolean[] hits3 = new boolean[2];
		assertArrayEquals(hits3, ship3.getHit());
		assertFalse(ship3.getHit()[0]);
		assertFalse(ship3.getHit()[1]);
		
		// test for submarine
		Ship ship4 = new Submarine();
		boolean[] hits4 = new boolean[1];
		assertArrayEquals(hits4, ship4.getHit());
		assertFalse(ship4.getHit()[0]);

		
		
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// test for cruiser
		Ship ship2 = new Cruiser();
		assertEquals("cruiser", ship2.getShipType());
		
		// test for destroyer
		Ship ship3 = new Destroyer();
		assertEquals("destroyer", ship3.getShipType());
		
		// test for submarine
		Ship ship4 = new Submarine();
		assertEquals("submarine", ship4.getShipType());
		
		// test for Emptysea
		Ship ship5 = new Emptysea();
		assertEquals("empty", ship5.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// vertical test
		Ship battleship2 = new Battleship();
		row = 8;
		column = 4;
		horizontal = false;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship2.isHorizontal());	
		
		// another ship test
		Ship submarine1 = new Submarine();
		row = 7;
		column = 0;
		horizontal = true;
		submarine1.placeShipAt(row, column, horizontal, ocean);
		assertTrue(submarine1.isHorizontal());
		
		// vertical test
		Ship submarine2 = new Submarine();
		row = 9;
		column = 3;
		horizontal = false;
		submarine2.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine2.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		// test for cruiser
		Ship cruiser = new Cruiser();
		row = 2; 
		column = 4;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());

		// test for destroyer
		Ship destroyer = new Destroyer();
		row = 4; 
		column = 4;
		horizontal = true;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
		// test for negative integer, this should also succeed
		Ship submarine = new Submarine();
		row = -1; 
		column = -2;
		horizontal = false;
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());
		
		
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		// test for cruiser
		Ship cruiser = new Cruiser();
		row = 2; 
		column = 4;
		horizontal = true;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());

		// test for destroyer
		Ship destroyer = new Destroyer();
		row = 4; 
		column = 4;
		horizontal = true;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
		
		// test for negative integer, this should also succeed
		Ship submarine = new Submarine();
		row = -1; 
		column = -2;
		horizontal = false;
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());
		
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		// test for cruiser
		Ship cruiser = new Cruiser();
		row = 2; 
		column = 4;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());

		// test for destroyer
		Ship destroyer = new Destroyer();
		row = 4; 
		column = 4;
		horizontal = true;
		destroyer.setHorizontal(horizontal);
		assertTrue(destroyer.isHorizontal());
		
		// test for negative integer, this should also succeed
		Ship submarine = new Submarine();
		row = -1; 
		column = -2;
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// test when head is out of bound
		row = -1;
		column = -2;
		ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Head out of bound");
		
		// test when tail out of bound
		row = 0;
		column = 0;
		ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Tail out of bound");
		
		// test bigger than 9
		row = 10;
		column = 8;
		ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Head out of bound");
		
		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

		//test for another type of ship
		Cruiser cruiser1 = new Cruiser();
		row = 8;
		column = 0;
		horizontal = false;
		boolean ok3 = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "Should be ok to place here");
		
		Battleship battleship3 = new Battleship();
		row = 4;
		column = 4;
		horizontal = false;
		boolean ok4 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship vertically adjacent below.");
		
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		// test for cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[2][1].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[2][2]);
		
		// test for destroyer
		Ship destroyer = new Destroyer();
		row = 8;
		column = 8;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[6][8].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[7][8]);
	
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		// hit part of battleship and check
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		// another hit
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		// another hit
		assertTrue(battleship.shootAt(0, 6));
		boolean[] hitArray3 = {true, true, false, true};
		assertArrayEquals(hitArray3, battleship.getHit());
		
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		assertTrue(submarine.shootAt(row, column));
		assertTrue(submarine.isSunk());
		
		// test for a longer ship
		Ship battleship = new Battleship();
		row = 0;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// before hit
		assertFalse(battleship.isSunk());
		// 1st hit
		assertTrue(battleship.shootAt(0, 9));
		assertFalse(battleship.isSunk());
		// 2nd hit
		assertTrue(battleship.shootAt(0, 8));
		assertFalse(battleship.isSunk());
		// 3rd hit
		assertTrue(battleship.shootAt(0, 7));
		assertFalse(battleship.isSunk());
		// fully hit
		assertTrue(battleship.shootAt(0, 6));
		assertTrue(battleship.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(0, 9);
		assertEquals("x", battleship.toString());
		
		battleship.shootAt(0, 8);
		assertEquals("x", battleship.toString());
		
		battleship.shootAt(0, 6);
		assertEquals("x", battleship.toString());
		
		battleship.shootAt(0, 7);
		assertEquals("s", battleship.toString());
		
	}

}
