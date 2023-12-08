/**
 * Controller Class
 * Include main function of system.
 * 
 * @author Qiwen Luo
 */

import java.util.ArrayList;
import java.util.Scanner;

import roles.*;
import files.FileInfoReader;
import courses.Course;

public class Controller {
	
	// set file path
	static String coursePath = "courseInfo.txt"; 
	static String studentPath = "studentInfo.txt";
	static String professorPath = "profInfo.txt";
	static String adminPath = "adminInfo.txt";
	// create a new scanner
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// create lists
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Professor> professors = new ArrayList<Professor>();
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		ArrayList<String> usernames = new ArrayList<String>();
		// create a new file reader
		
		FileInfoReader filereader = new FileInfoReader(courses, professors, students, admins, usernames);
		filereader.setup(coursePath, studentPath, professorPath, adminPath);
		
		while(true) {
			// Welcome message
			loginPrint();
			int logintype = inputNumber(4);
			
			// login process
			User user = login(logintype, usernames, professors, students, admins);
			if (user == null) break;
			
			welcomePrint(logintype, user);
			
			
			
			
		}
		// quit message
		System.out.println("Thanks for using!");
	}
	
	/*
	 * Input handling functions
	 */
	
	/**
	 * Get user integer input with a given range
	 * @param range
	 * @return
	 */
	private static int inputNumber(int range) {
		int input;
		System.out.print("Please enter a number from 1 to " + range + ": \n");
		
		do {
            // check if it is an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, try again.");
                scanner.next();
            }
            
            input = scanner.nextInt();
            // check the range
            if (input < 1 || input > range) {
                System.out.println("Out of range, try again.");
            }
        } while (input < 1 || input > range);
		
		return input;
	}
	
	/**
	 * Login handling
	 * @param logintype
	 * @return
	 */
	private static User login(int logintype, ArrayList<String> usernames, ArrayList<Professor> professors, 
			ArrayList<Student> students, ArrayList<Admin> admins) {
		if (logintype == 4) return null;
		// discard scanner buffer
		scanner.nextLine();
		User user = null;
		// handling user input
		while(true) {
			// ask for username
			System.out.print("Please enter your username, or type 'q' to quit.\n");
			String username_in = scanner.nextLine();
			
			// quit the system
			if (username_in.equals("q")) return null;
			
			// check if username is correct
			if (usernames.contains(username_in)) {
				switch (logintype) {
				case 1:
					for (Student student : students) {
						if (student.getUserName().equals(username_in))
							user = student;
					}
					break;
				case 2:
					for (Professor professor : professors) {
						if (professor.getUserName().equals(username_in))
							user = professor;
					}
					break;
				case 3:
					for (Admin admin : admins) {
						if (admin.getUserName().equals(username_in))
							user = admin;
					}
					break;
				}
				if (user == null) {
					// not a correct login user group
					System.out.println("Cannot find certain username in your current user group!");
					continue;
				}
				// check password
				String password = new String();
				int count = 0;
				while(count < 3) {
					System.out.print("Please enter your password, or type 'q' to quit.\n");
					// quit the system
					password = scanner.nextLine();
					if (password.equals("q"))
						return null;
					// successfully login
					if (user.userLogin(password))
						return user;
					count++;
				}
				System.out.println("You have entered your password incorrectly 3 times, the system will exit automatically.");
				return null;
			}
			System.out.println("No such username found.");
		}
	}
	
	/*
	 * UI printing functions
	 */
	
	/**
	 * Login message print
	 */
	private static void loginPrint() {
		System.out.println("-----------------------------------------");
		System.out.println("       Student Management System         ");
		System.out.println("-----------------------------------------");
		System.out.println(" 1 -- login as a student   ");
		System.out.println(" 2 -- login as a professor ");
		System.out.println(" 3 -- login as an admin    ");
		System.out.println(" 4 -- quit the system      ");
		System.out.println("");
	}
	
	/**
	 * User menu print
	 * @param logintype
	 * @param user
	 */
	private static void welcomePrint(int logintype, User user) {
		System.out.println("-----------------------------------------");
		System.out.println(" Welcome, " + user.getName());
		System.out.println("-----------------------------------------");
		switch (logintype) {
		case 1:
			System.out.println(" 1 -- View all courses                   ");
			System.out.println(" 2 -- Add courses to your list           ");
			System.out.println(" 3 -- View enrolled courses              ");
			System.out.println(" 4 -- Drop courses in your list          ");
			System.out.println(" 5 -- View grades                        ");
			System.out.println(" 6 -- Return to previous menu            ");
			break;
		case 2:
			System.out.println(" 1 -- View given courses                 ");
			System.out.println(" 2 -- View student list of the given course");
			System.out.println(" 3 -- Return to the previous menu        ");
			break;
		case 3:
			System.out.println(" 1 -- View all courses                   ");
			System.out.println(" 2 -- Add new courses                    ");
			System.out.println(" 3 -- Delete courses                     ");
			System.out.println(" 4 -- Add new professor                  ");
			System.out.println(" 5 -- Delete professor                   ");
			System.out.println(" 6 -- Add new student                    ");
			System.out.println(" 7 -- Delete student                     ");
			System.out.println(" 8 -- Return to previous menu            ");
			break;
		}
	}
	
}