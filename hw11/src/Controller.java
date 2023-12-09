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
	
	static Admin controller;
	
	public static void main(String[] args) {
		// create lists
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Professor> professors = new ArrayList<Professor>();
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Admin> admins = new ArrayList<Admin>();
		ArrayList<String> usernames = new ArrayList<String>();
		// create a new file reader
		
		controller = new Admin("", "", "", "", courses, professors, students, usernames);
		
		FileInfoReader filereader = new FileInfoReader(courses, professors, students, admins, usernames);
		filereader.setup(coursePath, studentPath, professorPath, adminPath);
		
		while(true) {
			// Welcome message
			loginPrint();
			int logintype = inputNumber(4);
			
			// login process
			User user = login(logintype, usernames, professors, students, admins);
			if (user == null) break;
			
			switch (logintype) {
			case 1:
				studentMenu((Student)user, courses, controller);
				break;
			case 2:
				professorMenu((Professor)user);
				break;
			case 3:
				adminMenu((Admin)user, courses, professors, students);
				break;
			}
						
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
		
		// discard scanner buffer
		scanner.nextLine();
		
		return input;
	}
	
	/**
	 * Get user string input with a given question;
	 * type 0 will be normal case, type 1 will be upper case, type 2 will be lower case
	 * @param question
	 * @return user input string; null is user enter q for quit
	 */
	private static String inputString(String question, int type) {
		while(true) {
			System.out.println(question);
			String userInput = scanner.nextLine();
			if (userInput == null) {
				System.out.println("You don't seem to have typed anything. Please try again.");
				continue;
			}
			// enter q as input
			if (userInput.equals("q")) return null;
			switch(type) {
			case 0:
				return userInput;
			case 1:
				return userInput.toUpperCase();
			case 2:
				return userInput.toLowerCase();
			}
		}
	}
	
	/**
	 * Time string input handling
	 * @param question
	 * @return
	 */
	private static String inputTime(String question) {
		while(true) {
			System.out.println(question);
			String userInput = scanner.nextLine();
			// enter q as input
			if (userInput.equals("q")) return null;
			String [] array;
			array = userInput.split(":");
			if (array.length != 2) {
				System.out.println("Invalid input, try again.");
				continue;
			}
			String hour = array[0].trim();
			String min = array[1].trim();
			try {
	            Integer.parseInt(hour);
	            Integer.parseInt(min);
	            return hour + ":" + min;
	        } catch (NumberFormatException e) {
	            // If parsing fails, the string is not an integer
	            System.out.println("Invalid input, try again.");
	        }
		}
	}
	
	private static String inputDays() {
		while(true) {
        	String userInput = inputString("Please enter the course date, or type 'q' to end.", 1);
            // enter q as input
 			if (userInput == null) return null;
            boolean isValid = true;
            for (char c : userInput.toCharArray()) {
                if ("MTWRF".indexOf(c) == -1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("Input contains invalid characters. Use only 'M', 'T', 'W', 'R', 'F'.");
            } else {
                return userInput;
            }
        }
	}
	
	/*
	 * Menu functions
	 */
	
	/**
	 * Login handling
	 * @param logintype
	 * @return
	 */
	private static User login(int logintype, ArrayList<String> usernames, ArrayList<Professor> professors, 
			ArrayList<Student> students, ArrayList<Admin> admins) {
		if (logintype == 4) return null;
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
	
	// Student related function
	
	/**
	 * Student main menu
	 * @return
	 */
	private static void studentMenu(Student student, ArrayList<Course> courses, Admin controller) {
		while(true) {
			welcomePrint(1, student);
			int actionNum = inputNumber(6);
			switch (actionNum) {
			// View all courses
			case 1:
				printAllCourses(courses);
				break;
			// Add course to your list
			case 2:
				studentAddCourse(student, courses, controller);
				break;
			// View selected Courses
			case 3:
				System.out.println("\nThe courses in your list:");
				System.out.println(student.toStringEnrolledCourse());
				break;
			// Drop a course
			case 4:
				studentDropCourse(student);
				break;
			// View grades
			case 5:
				System.out.println("\n Here are the courses you already take, with your grade in a letter form:");
				System.out.println(student.toStringGrades());
				break;
			// Return to previous menu
			case 6:
				return;
			}
		}
	}
	
	/**
	 * Student add course menu loop
	 * @param student
	 * @param courses
	 * @param controller
	 */
	private static void studentAddCourse(Student student, ArrayList<Course> courses, Admin controller) {
		while(true) {
			String courseID = inputString("Please select the course ID you want to add to your list, eg. \'CIT590\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (courseID == null) return;
			switch(controller.addStudentCourse(student.getUserID(), courseID)) {
			// add successfully
			case 1:
				System.out.println("Course add successfully.\n");
				break;
			// course does not exist
			case -2:
				System.out.println("Course does not exist, try agian\n");
				break;
			// time conflict
			case -3:
				Course course_conflict = controller.getStudentConflictCourse(student, courseID);
				System.out.println("The course you selected has time conflict with " + course_conflict.getID() 
						+ " " + course_conflict.getName() +".\n");
				break;
			// has already enrolled
			case -4:
				System.out.println("The course you selected is already in your list.\n");
				break;
			}
		}
	}
	
	/**
	 * Student drop course menu loop
	 * @param student
	 */
	private static void studentDropCourse(Student student) {
		while(true) {
			System.out.println("\nThe courses in your list:");
			System.out.println(student.toStringEnrolledCourse());
			String courseID = inputString("Please select the ID of the course you want to drop, eg. \'CIT590\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (courseID == null) return;
			if (student.dropCourse(courseID)) {
				System.out.println("Course drop successfully.");
			} else {
				System.out.println("The course isn't in your schedule.");
			}		
		}
	}
	
	// Professor related functions
	
	/**
	 * Professor main menu
	 * @param professor
	 */
	private static void professorMenu(Professor professor) {
		while(true) {
			welcomePrint(2, professor);
			int actionNum = inputNumber(3);
			switch (actionNum) {
			// View given courses
			case 1:
				System.out.println("\nThe courses list:");
				System.out.println(professor.toStringCourse());
				break;
			// View student list of the given course
			case 2:
				professorViewStudent(professor);
				break;
			// Return to previous menu
			case 3:
				return;
			}
		}
	}
	
	/**
	 * Professor view student of a certain course menu loop
	 * @param professor
	 */
	private static void professorViewStudent(Professor professor) {
		while(true) {
			System.out.println("\nThe courses list:");
			System.out.println(professor.toStringCourse());
			String courseID = inputString("\nPlease enter a course ID, eg. \'CIT590\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (courseID == null) return;
			String studentString = professor.toStringStudent(courseID);
			if (studentString == null) {
				System.out.println("No such course in your list.");
			} else {
				System.out.println("Student in your course " + courseID + " " + professor.getCourseNamebyCourseID(courseID) + ":");
				System.out.println(professor.toStringStudent(courseID));
			}
		}
	}
	
	// Admin related functions
	
	private static void adminMenu(Admin admin, ArrayList<Course> courses, ArrayList<Professor> professers, ArrayList<Student> students) {
		while(true) {
			welcomePrint(3, admin);
			int actionNum = inputNumber(8);
			switch (actionNum) {
			// View all courses
			case 1:
				printAllCourses(courses);
				break;
			// Add new courses
			case 2:
				adminAddCourse(admin);
				break;
			// Delete courses
			case 3:
				adminDeleteCourse(admin);
				break;
			// Add new professor
			case 4:
				adminAddProfessor(admin);
				break;
			// Delete professor
			case 5:
				adminDeleteProfessor(admin);
				break;
			// Add new student
			case 6:
				adminAddStudent(admin);
				break;
			// Delete new student
			case 7:
				adminDeleteStudent(admin);
				break;
			// back to previous menu
			case 8:
				return;
			}
		}
	}
	
	/**
	 * Admin add a course menu loop
	 * @param admin
	 * @param professor
	 * @param course
	 */
	private static void adminAddCourse(Admin admin) {
		String courseID;	// need to check
		String coursename;
		String professorID;	// need to check time conflict and existence
		String days;
		String time_start;	// need validation
		String time_end;
		int capacity;
		while(true) {
			// check existence of course
			while(true) {
				courseID = inputString("Please entre the course ID, or type 'q' to end.", 1);
				// return to previous menu
				if (courseID == null) return;
				if (admin.getCoursebyID(courseID) == null) break;
				System.out.println("Course has already existed.");
			}
			
			coursename = inputString("Please entre the course name, or type 'q' to end.", 0);
			// return to previous menu
			if (coursename == null) return;
			
			time_start = inputTime("Please entre the course start time, or type 'q' to end.");
			// return to previous menu
			if (time_start == null) return;
			
			time_end = inputTime("Please entre the course end time, or type 'q' to end.");
			// return to previous menu
			if (time_end == null) return;
			
			days = inputDays();
			// return to previous menu
			if (days == null) return;
			
			System.out.println("Please enter the course capacity. eg \'72\'");
			capacity = inputNumber(999);
			
			// check professor ID, professor time conflict
			while(true) {
				professorID = inputString("Please entre the professor's ID, or type 'q' to end.", 0);
				// return to previous menu
				if (professorID == null) return;
				// need a new professor
				if (admin.getProfessorbyID(professorID) == null) {
					System.out.println("The professor isn't in the system, please add this professor first.");
					adminAddProfessor(admin);
				// check if there is time conflict
				}
				int flag = admin.addCourse(courseID, coursename, professorID, days, time_start, time_end, capacity);
				// time conflict
				if (flag == -3) {
					Professor professor = admin.getProfessorbyID(professorID);
					Course course_time_check = new Course(courseID, coursename, professor, days, time_start, time_end, capacity);
					System.out.println("There is time conflict with course: " + professor.getConflictCourse(course_time_check));
				} else if(flag == 1) {
					System.out.println("Successfully add the course: " + admin.getCoursebyID(courseID).toString());
					return;
				}
			}
		}
	}
	
	private static void adminAddProfessor(Admin admin) {
		String name;
		String username;	// need to check
		String password;
		String userID;	// need to check
		while(true) {
			// check if user ID is existent
			while(true) {
				userID = inputString("Please entre professor's user ID, or type 'q' to end.", 0);
				// return to previous menu
				if (userID == null) return;
				if (admin.getProfessorbyID(userID) == null) break;
				System.out.println("Professor is already existed, change another Professor ID please.");
			}
			// check if username is used
			while(true) {
				username = inputString("Please entre professor's username, or type 'q' to end.", 0);
				// return to previous menu
				if (username == null) return;
				if (admin.checkUsername(username) == false) break;
				System.out.println("Username is already existed, change another username please.");
			}
			
			name = inputString("Please entre professor's name, or type 'q' to end.", 0);
			// return to previous menu
			if (name == null) return;
			
			password = inputString("Please entre professor's password, or type 'q' to end.", 0);
			// return to previous menu
			if (password == null) return;
			
			// add professor
			int flag = admin.addProfessor(name, username, password, userID);
			if (flag == 1){
				System.out.println("Successfully add the new professor: " + userID + " " + name);
				break;
			} else {
				System.out.println("Add failed, error type: " + flag);	
				break;
			}
		}
	}
	
	private static void adminAddStudent(Admin admin) {
		String name;
		String username;	// need to check
		String password;
		String userID;	// need to check
		// student basic information loop
		while(true) {
			// check if user ID is existent
			while(true) {
				userID = inputString("Please entre student's user ID, or type 'q' to end.", 0);
				// return to previous menu
				if (userID == null) return;
				if (admin.getStudentbyID(userID) == null) break;
				System.out.println("Student is already existed, change another Student ID please.");
			}
			// check if username is used
			while(true) {
				username = inputString("Please entre student's username, or type 'q' to end.", 0);
				// return to previous menu
				if (username == null) return;
				if (admin.checkUsername(username) == false) break;
				System.out.println("Username is already existed, change another username please.");
			}
			
			name = inputString("Please entre student's name, or type 'q' to end.", 0);
			// return to previous menu
			if (name == null) return;
			
			password = inputString("Please entre student's password, or type 'q' to end.", 0);
			// return to previous menu
			if (password == null) return;
			
			// add student
			int flag = admin.addStudent(name, username, password, userID);
			if (flag == 1){
				System.out.println("Successfully add the new student: " + userID + " " + name);
				break;
			} else {
				System.out.println("Add failed, error type: " + flag);	
				break;
			}
		}
		// student course loop
		String courseID;
		String grade;
		while (true) {
			courseID = inputString("Please entre the ID of course which this student is taken, one in a time\n"
					+ "or type 'q' to end.", 1);
			// return to previous menu
			if (courseID == null) return;
			int flag = admin.addStudentCourse(userID, courseID);
			while(flag < 0 && flag > -4) {
				switch(flag) {
				// course does not exist
				case -2:
					System.out.println("The course does not exist.");
					break;
				// student has time conflict
				case -3:
					System.out.println("Student has time conflict.");
					break;
				}
				// ask for a new course ID
				courseID = inputString("Please entre the ID of course which this student is taken, one in a time\n"
						+ "or type 'q' to end.", 1);
				// return to previous menu
				if (courseID == null) return;
				flag = admin.addStudentCourse(userID, courseID);
			}
			// student has already enrolled
			if (flag == -4)
				System.out.println("Student has already enrolled this class, you are now updating the grade.");
			grade = inputString("Please entre the grade, eg, 'A'", 1);
			admin.addStudentCourseGrade(userID, courseID, grade);
			System.out.println("Add course to student successfully: " + courseID + " : " + grade);
		}
	}
	
	/**
	 * Admin delete a course menu loop
	 * @param admin
	 */
	private static void adminDeleteCourse(Admin admin) {
		while(true) {
			String courseID = inputString("Please select the ID of the course you want to delete, eg. \'CIT590\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (courseID == null) return;
			if (admin.deletCourse(courseID)) {
				System.out.println("Course drop successfully.");
			} else {
				System.out.println("The course does not exist.");
			}		
		}
	}
	
	/**
	 * Admin delete a professor menu loop
	 * @param admin
	 */
	private static void adminDeleteProfessor(Admin admin) {
		while(true) {
			String professorID = inputString("Please select the ID of the professor you want to delete, eg. \'001\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (professorID == null) return;
			if (admin.deletProfessor(professorID)) {
				System.out.println("Professor delete successfully.");
			} else {
				System.out.println("The Professor does not exist.");
			}
		}
	}
	
	/**
	 * Admin delete a student menu loop
	 * @param admin
	 */
	private static void adminDeleteStudent(Admin admin) {
		while(true) {
			String studentID = inputString("Please select the ID of the student you want to delete, eg. \'001\'\n"
					+ "Or enter \'q\' to return to the previous menu.", 1);
			// return to previous menu
			if (studentID == null) return;
			if (admin.deletStudent(studentID)) {
				System.out.println("Student delete successfully.");
			} else {
				System.out.println("The student does not exist.");
			}
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
	
	/**
	 * Print all the courses
	 * @param courses
	 */
	private static void printAllCourses(ArrayList<Course> courses) {
		for(Course course : courses) {
			System.out.println(course.toString());
		}
	}
	
}