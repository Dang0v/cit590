/**
 * Class OceanTest
 * 
 * 
 * @author Qiwen Luo
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
		
		
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		assertFalse(ocean.isOccupied(1, 4));
		
		assertTrue(ocean.isOccupied(0, 0));
		
		Battleship battleship = new Battleship();
		row = 6;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(6, 4));
		assertTrue(ocean.isOccupied(6, 3));
		assertTrue(ocean.isOccupied(6, 2));
		assertTrue(ocean.isOccupied(6, 1));
		assertFalse(ocean.isOccupied(6, 0));
		

		
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		assertTrue(destroyer.isSunk());
		
		Battleship battleship = new Battleship();
		row = 6;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(6, 4));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 3));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 2));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 1));
		assertTrue(battleship.isSunk());
		
		// hit these places again will be false
		assertFalse(ocean.shootAt(6, 1));
		assertFalse(ocean.shootAt(6, 2));
		assertFalse(ocean.shootAt(6, 3));
		assertFalse(ocean.shootAt(6, 4));
		

		
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		ocean.shootAt(0, 5);
		assertEquals(7, ocean.getShotsFired());
		ocean.shootAt(0, 5);
		assertEquals(8, ocean.getShotsFired());
		ocean.shootAt(0, 5);
		assertEquals(9, ocean.getShotsFired());
		ocean.shootAt(0, 5);
		assertEquals(10, ocean.getShotsFired());
		
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		Battleship battleship = new Battleship();
		row = 6;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(6, 4));
		assertEquals(2, ocean.getHitCount());
		// same location but not sunk
		assertTrue(ocean.shootAt(6, 4));
		assertEquals(3, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 3));
		assertEquals(4, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 2));
		assertEquals(5, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 1));
		assertEquals(6, ocean.getHitCount());
		
		// same location but sunk
		assertFalse(ocean.shootAt(6, 1));
		assertEquals(6, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(0, 5));
		// now sunk
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		Battleship battleship = new Battleship();
		row = 6;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(6, 4));
		assertEquals(3, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 4));
		assertEquals(4, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 3));
		assertEquals(5, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 2));
		assertEquals(6, ocean.getHitCount());
		assertTrue(ocean.shootAt(6, 1));
		assertEquals(7, ocean.getHitCount());
		// now sunk
		assertEquals(2, ocean.getShipsSunk());
		
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		assertEquals("empty", shipArray[6][4].getShipType());
		
		// add a battleship
		Battleship battleship = new Battleship();
		int row = 6;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		// now (6,4) should change
		assertEquals("battleship", shipArray[6][4].getShipType());
		
		// add a destroyer
		Destroyer destroyer = new Destroyer();
		row = 1;
		column = 5;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("destroyer", shipArray[0][5].getShipType());
		
	}

}
