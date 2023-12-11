package expenses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpenseTest {

	@Test
	void testEquals() {

		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create individual expenses
		Expense expense1 = new Expense(12, 2.34);
		Expense expense2 = new Expense(12, 2.34);
		
		//compare for equality
		assertEquals(expense1, expense2, "The 2 expenses should be considered equal. They are for the same amount and the same month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
        // Create additional expense objects and compare
		
		// Different class check
		String str = "123";
		assertEquals(false, expense1.equals(str), "The 2 object should have the same type.");
		
		// Different month check
		Expense expense3 = new Expense(11, 2.34);
		assertEquals(false, expense1.equals(expense3), "The 2 expenses should not be considered equal. They are for the same amount but not the same month.");
		
		// Different amount check
		Expense expense4 = new Expense(12, 1.34);
		assertEquals(false, expense1.equals(expense4), "The 2 expenses should not be considered equal. They are for the same month but not the same amount.");
		
	}

}
