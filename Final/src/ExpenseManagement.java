

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import expenses.Expense;
import expenses.Expenses;
import expenses.file.ExpenseFileParser;
import expenses.file.ExpenseFileReader;

/**
 * Provides static methods for extracting information from monthly expenses.
 * @author lbrandon
 */
public class ExpenseManagement {

	/**
	 * Mappings to match a month abbreviation with a month number.
	 * For example, the map should look like this:
	 *   {"jan"=1, "feb"=2, ...}
	 */
	private static Map<String, Integer> MONTHLY_MAPPINGS;
	
	static {
		
		//Initializes map of month abbreviations and creates mappings 
		ExpenseManagement.MONTHLY_MAPPINGS = new HashMap<String, Integer>();
		
		//populate map with keys (month abbreviations) and values (month numbers)
		ExpenseManagement.MONTHLY_MAPPINGS.put("jan", 1);
		ExpenseManagement.MONTHLY_MAPPINGS.put("feb", 2);
		ExpenseManagement.MONTHLY_MAPPINGS.put("march", 3);
		ExpenseManagement.MONTHLY_MAPPINGS.put("april", 4);
		ExpenseManagement.MONTHLY_MAPPINGS.put("may", 5);
		ExpenseManagement.MONTHLY_MAPPINGS.put("june", 6);
		ExpenseManagement.MONTHLY_MAPPINGS.put("july", 7);
		ExpenseManagement.MONTHLY_MAPPINGS.put("aug", 8);
		ExpenseManagement.MONTHLY_MAPPINGS.put("sept", 9);
		ExpenseManagement.MONTHLY_MAPPINGS.put("oct", 10);
		ExpenseManagement.MONTHLY_MAPPINGS.put("nov", 11);
		ExpenseManagement.MONTHLY_MAPPINGS.put("dec", 12);
	}
	
	/**
	 * Static method that gets mappings to match a month abbreviation with a month number.
	 * @return map of month names and month numbers
	 */
	public static Map<String, Integer> getMonthlyMappings() {
		return ExpenseManagement.MONTHLY_MAPPINGS;
	}
	
	/**
	 * Static method that gets list of expense amounts for given month number.
	 * @param month to look up
	 * @param list of monthly expenses to search
	 * @return list of expense amounts for given month
	 */
	public static List<Double> getMonthlyExpenses(int month, List<Expense> monthlyExpenses) {
		
		// Iterate over list of monthly expenses and find the expense amounts for given month
		List<Double> expensesOfMonth = new ArrayList<Double>();
		
		for(Expense expense : monthlyExpenses) {
			if (expense.getMonth() == month)
				expensesOfMonth.add(expense.getAmount());
		}
		
		return expensesOfMonth;
	}
	
	/**
	 * Static method that gets list of expense amounts for given month abbreviation.
	 * Maps given month name to month number.
	 * @param month to look up
	 * @param list of monthly expenses to search
	 * @return list of expense amounts for given month
	 */
	public static List<Double> getMonthlyExpenses(String month, List<Expense> monthlyExpenses) {
		
		// Get number associated with given month
		// Iterate over list of monthly expenses and find the expense amounts for given numeric month
        
        // to lower case
        month = month.toLowerCase();
        // get the integer type of month
        int month_int = getMonthlyMappings().get(month);
        // find the list
        return getMonthlyExpenses(month_int, monthlyExpenses);
	}

	/**
	 * Static method that gets total expense amount for given month abbreviation.
	 * @param month to look up
	 * @param list of monthly expenses to search
	 * @return total expense amount for given month
	 */
	public static double getTotalMonthlyExpenses(String month, List<Expense> monthlyExpenses) {

		// Get expense amounts for given month
		// Calculate sum of all expense amounts
		List<Double> expenseList = getMonthlyExpenses(month, monthlyExpenses);
		Double total = 0.0;
		// check if there is any expense
		if (expenseList == null) return 0.0;
		for (Double amount : expenseList) {
			total += amount;
		}
		return total;
	}
	
	/**
	 * Static method that identifies the month with the highest expense amount.
	 * First, gets the total expense amount for each month, then gets the greatest one.
	 * @param list of monthly expenses to search
	 * @return Expense object with information for most expensive month
	 */
	public static Expense getMostExpensiveMonth(List<Expense> monthlyExpenses) {

		// Iterate over each month and get the total expenses for each
		// Identify the month with the greatest expense amount
		
		// create a list of double
		List<Double> doubleList = new ArrayList<Double>();
		
		// add all total expense to the list
		for (int i = 1; i < 13; i++) {
			String month = new String();
			// get the String of month
			for (Map.Entry<String, Integer> entry : getMonthlyMappings().entrySet()) {
				if (entry.getValue() == i) {
					month = entry.getKey();
					break;
				}
			}
			doubleList.add(getTotalMonthlyExpenses(month, monthlyExpenses));
		}
		
		// search for the max value
		double max = Double.MIN_VALUE;
		int max_index = -1;
		
		for (int i = 0; i < 12; i++) {
			if (doubleList.get(i) > max) {
				max = doubleList.get(i);
				max_index = i;
			}
		}
		
		// index is from 0 to 11, we add one to become month
		max_index++;
		
		return new Expense(max_index, max);
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
		
		//get list of expense amounts for 10 (oct)
		List<Double> octMonthlyExpenses = ExpenseManagement.getMonthlyExpenses(10, 
				expenses.getMonthlyExpenses());
		System.out.println("Oct. Expenses: " + octMonthlyExpenses);
		
		System.out.println();
		
		//get list of expense amounts for jan
		List<Double> janMonthlyExpenses = ExpenseManagement.getMonthlyExpenses("jan", 
				expenses.getMonthlyExpenses());
		System.out.println("Jan. Expenses: " + janMonthlyExpenses);
		
		//get total expense amount for jan
		double totalJanMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("jan",
				expenses.getMonthlyExpenses());
		System.out.println("Total: " + totalJanMonthlyExpenses);
				
		System.out.println();
		
		//get list of expense amounts for 2 (feb)
		List<Double> febMonthlyExpenses = ExpenseManagement.getMonthlyExpenses(2,
				expenses.getMonthlyExpenses());
		System.out.println("Feb. Expenses: " + febMonthlyExpenses);
		
		//get total expense amount for feb
		double totalFebMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("feb",
				expenses.getMonthlyExpenses());
		System.out.println("Total: " + totalFebMonthlyExpenses);
		
		System.out.println();
		
		//get highest expense
		Expense mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(expenses.getMonthlyExpenses());
		System.out.println("Most Expensive Month: " + mostExpensiveMonth);
	}
}
