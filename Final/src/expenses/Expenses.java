package expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import expenses.file.ExpenseFileParser;
import expenses.file.ExpenseFileReader;

/**
 * Creates and stores monthly expenses.
 * @author lbrandon
 */
public class Expenses {

	/**
	 * List of monthly expenses.
	 */	
	private List<Expense> monthlyExpenses;
	
	/**
	 * Initializes list of monthly expenses.
	 */
	public Expenses() {
		
		//initialize list of expenses
		this.monthlyExpenses = new ArrayList<Expense>();
	}
	
	/**
	 * Gets list of monthly expenses.
	 * @return list of expenses
	 */
	public List<Expense> getMonthlyExpenses() {
		
		return this.monthlyExpenses;
	}
	
	/**
	 * Converts each expense in the given list to an Expense object,
	 * and adds it to the internal list of monthly expenses.
	 * @param expenses to add
	 */
	public void addExpenses(List<Map<Integer, Double>> expenses) {
		// get all map in list
		for (Map<Integer, Double> expenseMap : expenses) {
			// get all pair in map (only 1 pair exactly)
			for(Map.Entry<Integer, Double> entry : expenseMap.entrySet()){
				// create a new Expense class
				Expense expense = new Expense(entry.getKey(), entry.getValue());
				// add to the Expense list
				this.addExpense(expense);
			}
		}
	}
	
	/**
	 * Adds given Expense object to the internal list of monthly expense objects.
	 * @param expense to add
	 */
	public void addExpense(Expense expense) {
		this.monthlyExpenses.add(expense);	
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//parse list of expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpenses = ExpenseFileParser.parseExpenses(expensesList);
				
		//print list of expense maps for debugging purposes
		System.out.println(monthlyExpenses);
		
		//create instance of expenses class
		Expenses expenses = new Expenses();
				
		//add list of maps to internal list of expense objects
		expenses.addExpenses(monthlyExpenses);	
		
	}
}
