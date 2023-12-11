package expenses;

/**
 * Represents a single expense for a particular month.
 * @author lbrandon
 * @author Qiwen Luo
 */
public class Expense {

	/**
	 * Number of month for expense.
	 */
	private int month;
	
	/**
	 * Amount of expense.
	 */
	private double amount;
	
	/**
	 * Creates Expense with given month number and amount.
	 * @param month for expense
	 * @param amount for expense
	 */
	public Expense(int month, double amount) {
		this.month = month;
		this.amount = amount;
	}
	
	/**
	 * Get month of expense.
	 * @return month
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Get amount of expense.
	 * @return amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Returns the month number and amount for expense.
	 */
	@Override 
	public String toString() {
		return this.month + " : " + this.amount;
	}
	
	/**
	 * Compares two Expense objects for equality, based on the months and amounts.
	 * If the month and amount of one Expense object is equal to 
	 * the month and amount of the other Expense object, 
	 * the two Expense objects are equal.
	 */
	@Override 
	public boolean equals(Object o) {
		
		// Cast given Object o to Expense object
		// Compare month and amount of this Expense to other casted Expense
		
		// check if given object is an Expense
		if (o instanceof Expense) {
			Expense expensetoCompare = (Expense) o;
			// compare
			if (this.month == expensetoCompare.getMonth() && Double.compare(this.amount, expensetoCompare.getAmount()) == 0)
				return true;
		}

		return false;
	}
}