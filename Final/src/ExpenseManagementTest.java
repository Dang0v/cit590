import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expenses.Expense;
import expenses.Expenses;

class ExpenseManagementTest {

	//define instance of expenses class for testing
	Expenses expenses;
	
	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		this.expenses = new Expenses();
		
		//initialize individual expenses and add for testing
		Expense expense = new Expense(12, 2.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(10, 98.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(2, 44.00);
		this.expenses.addExpense(expense);
		
		expense = new Expense(12, 12.01);
		this.expenses.addExpense(expense);
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
	}

	@Test
	void testGetMonthlyExpensesIntListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct (10)
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses(10, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
        // Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months
		
		// an empty expense list
		List<Double> monthlyExpensesExpected2 = new ArrayList<Double>();
		List<Double> monthlyExpenses2 = ExpenseManagement.getMonthlyExpenses(5, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected2, monthlyExpenses2, "May should be an empty List.");
		
		// check Feb (2)
		monthlyExpensesExpected2.add(44.0);
		List<Double> monthlyExpenses3 = ExpenseManagement.getMonthlyExpenses(2, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected2, monthlyExpenses3, "The expected list of monthly expenses for Feb. do not match the actual list of monthly expenses for Feb.");
		
	}

	@Test
	void testGetMonthlyExpensesStringListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
        // Hint(s): Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months
		
		// an empty expense list
		List<Double> monthlyExpensesExpected2 = new ArrayList<Double>();
		List<Double> monthlyExpenses2 = ExpenseManagement.getMonthlyExpenses("may", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected2, monthlyExpenses2, "May should be an empty List.");
		
		// check Feb (2)
		monthlyExpensesExpected2.add(44.0);
		List<Double> monthlyExpenses3 = ExpenseManagement.getMonthlyExpenses("feb", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected2, monthlyExpenses3, "The expected list of monthly expenses for Feb. do not match the actual list of monthly expenses for Feb.");
}

	@Test
	void testGetTotalMonthlyExpenses() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//get actual total monthly expense amount for oct
		double totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm it is 98.34
		assertEquals(98.34, totalMonthlyExpenses, "The expected total of monthly expenses for Oct. does not match the actual total of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		// get total monthly expense amount for may
		totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("may", this.expenses.getMonthlyExpenses());
		
		// confirm it is 0
		assertEquals(0, totalMonthlyExpenses, "The total of may should be 0.");
		
		// add a new amount to Feb
		Expense expense = new Expense(2, 44.00);
		this.expenses.addExpense(expense);
		totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("feb", this.expenses.getMonthlyExpenses());
		
		// confirm it is 88
		assertEquals(88, totalMonthlyExpenses, "The total of may should be 0.");
		
	}

	@Test
	void testGetMostExpensiveMonth() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create expense object for expected most expensive month 10 (oct)
		Expense mostExpensiveMonthCompare = new Expense(10, 98.34);
		
		//get expense object for actual most expensive month
		Expense mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
        // Hint(s): Create additional expense object for a particular month and add to monthly expenses
		// Test if it's the most expensive month
		
		// add a new amount to Feb
		Expense expense = new Expense(2, 56.00);
		this.expenses.addExpense(expense);
		mostExpensiveMonthCompare = new Expense(2, 100);
		
		// update the most expensive month
		mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		
		
		// add a new amount to Feb
		expense = new Expense(5, 1000.0);
		this.expenses.addExpense(expense);
		mostExpensiveMonthCompare = new Expense(5, 1000);
		
		// update the most expensive month
		mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		
	}

}
