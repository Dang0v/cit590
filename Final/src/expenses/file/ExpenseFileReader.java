package expenses.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * Manages the loading and parsing of expense files.
 * @author lbrandon
 * @author Qiwen Luo
 *
 */
public class ExpenseFileReader {

	/**
	 * Loads the given filename and adds each line to a list.
	 * Ignores lines with only whitespace.
	 * @param fileName to load
	 * @return list of lines from the file
	 */
	public static List<String> loadExpenses(String fileName) {
		
		// Hint: Load and read each line in the file
		// Strip each line of leading and trailing whitespace
		// If a line is made up entirely of whitespace, ignore it
		// Return a list of lines
		
		List<String> fileLines = new ArrayList<String>();
		
		try {
			File f = new File(fileName);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line = br.readLine();
				// check if end of file
				if(line == null) {
					break;
				} else {
					line = line.strip();
					// check if line is empty
					if (!line.isBlank())
						fileLines.add(line);
				}
			}				
			fr.close();
			br.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		return fileLines;
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses_sample.txt file and get list of expenses
		List<String> expensesListSample = ExpenseFileReader.loadExpenses("expenses_sample.txt");
		
		//print sample expenses
		System.out.println("SAMPLE EXPENSES\n");
		for (String line : expensesListSample) {
			System.out.println(line);
		}
		
		//blank line
		System.out.println();
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//print expenses 
		System.out.println("EXPENSES\n");
		for (String line : expensesList) {
			System.out.println(line);
		}
	}
}