/**
 *  Admin Class
 *  
 *  Maintain courses, professor, students list.
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;

// TODO username check
public class Admin extends User {
	/**
	 * Courses list
	 */
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	/**
	 * Professors List
	 */
	private ArrayList<Professor> professors = new ArrayList<Professor>();
	
	/**
	 * Students List
	 */
	private ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Username List
	 */
	private ArrayList<String> usernames = new ArrayList<String>();
	
	/**
	 * Initialization of Admin
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 */
	public Admin(String name, String username, String password, String userID, ArrayList<Course> courses, 
			ArrayList<Professor> professors, ArrayList<Student> students, ArrayList<String> usernames) {
		super(name, username, password, userID);
		this.courses = courses;
		this.professors = professors;
		this.students = students;
		this.usernames = usernames;
	}
	
	/**
	 * Add a new professor
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 * @return 1 is add success, -1 if existed, -5 if username existed
	 */
	public int addProfessor(String name, String username, String password, String userID) {
		// check if professor is existed
		if (this.getProfessorbyID(userID) == null) {
			if (usernames.contains(username))
				return -5;
			professors.add(new Professor(name, username, password, userID));
			usernames.add(username);
			return 1;
		}
		return -1;
	}
	
	/**
	 * Remove a professor by professor ID
	 * @param professorID
	 * @return true if removed
	 */
	public boolean deletProfessor(String professorID) {
		// TODO delete the course
		Professor professor = this.getProfessorbyID(professorID);
		if(this.professors.remove(professor)) {
			usernames.remove(professor.getUserName());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add a new student
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 * @return 1 is add success, -1 if existed, -5 if username existed
	 */
	public int addStudent(String name, String username, String password, String userID) {
		// check if student is existed
		if (this.getStudentbyID(userID) == null) {
			if (usernames.contains(username))
				return -5;
			usernames.add(username);
			students.add(new Student(name, username, password, userID));
			// add to username list
			return 1;
		}
		return -1;
	}
	
	/**
	 * Add a course to student
	 * @param userID
	 * @param courseID
	 * @return 1 if success, -1 if student does not exist, -2 if course does not exist, -3 if student has time conflict, -4 if student has already enrolled
	 */
	public int addStudentCourse(String userID, String courseID) {
		// check if student is existed
		Student student = this.getStudentbyID(userID);
		if (student == null)
			return -1;
		// if course does not exist
		Course course = this.getCoursebyID(courseID);
		if (course == null)
			return -2;
		int flag = student.addCourse(course);
		// time conflict with student
		if (flag == -3)
			return -3;
		// already enrolled
		else if(flag == -1)
			return -4;
		return 1;
	}
	
	/**
	 * Get student's the conflict course
	 * @param student
	 * @param courseID
	 * @return
	 */
	public Course getStudentConflictCourse(Student student, String courseID) {
		Course course = this.getCoursebyID(courseID);
		return student.getConflictCourse(course, student.getCoursesWithGrade());
	}
	
	/**
	 * Add grade to a course that student is enrolled
	 * @param userID
	 * @param courseID
	 * @param Grade
	 * @return 1 if success, -1 if student does not exist, -2 if course does not exist, -4 if student does not enroll this course
	 */
	public int addStudentCourseGrade(String userID, String courseID, String grade) {
		// check if student is existed
		Student student = this.getStudentbyID(userID);
		if (student == null)
			return -1;
		// if course does not exist
		Course course = this.getCoursebyID(courseID);
		if (course == null)
			return -2;
		// if student does not enroll this class
		if (student.getCoursesWithGrade().containsKey(course) == false)
			return -4;
		// every thing is good add grade
		student.addGradeToCourse(courseID, grade);
		return 1;
	}
	
	/**
	 * Remove a student by student ID
	 * @param studentID
	 * @return true if removed
	 */
	public boolean deletStudent(String studentID) {
		Student student = this.getStudentbyID(studentID);
		// if student exist
		if (this.students.remove(student)) {
			// remove student from username list
			usernames.remove(student.getUserName());
			// remove student from all enrolled course
			for (HashMap.Entry<Course, String> entry : student.getCoursesWithGrade().entrySet()) {
				Course course = entry.getKey();
				course.deleteEnrooledStudent(student);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add a new course
	 * @param courseID
	 * @param coursename
	 * @param professorID
	 * @param days
	 * @param time_start
	 * @param time_end
	 * @param capacity
	 * @return 1 if success, -1 if course is existed, -2 if professor is not existed, -3 if there is time conflict with professor
	 */
	public int addCourse(String courseID, String coursename, String professorID, String days, String time_start, String time_end, int capacity) {
		// check if course is existed
		if (this.getCoursebyID(courseID) != null) {
			return -1;
		}
		// get a professor
		Professor professor = this.getProfessorbyID(professorID);
		// professor does not exist, add a new professor
		if (professor == null)
			return -2;
		
		// create a new Course
		Course course = new Course(courseID, coursename, professor, days, time_start, time_end, capacity);
		// check if there is time conflict with professor
		if (professor.addCourse(course) == 1) {
			// add course to professor should only be 1 or -2, since the course existence is already checked
			this.courses.add(course);
			return 1;
		} else {
			// time conflict with professor
			return -3;
		}
	}
	
	/**
	 * Remove a course by course ID
	 * @param courseID
	 * @return true if removed
	 */
	public boolean deletCourse(String courseID) {
		Course course = this.getCoursebyID(courseID);
		// remove course from course list
		if (this.courses.remove(course)) {
			// remove course from professor class
			course.getProfessor().deletCourse(courseID);
			// remove all student enrolled
			for (Student student : course.getEnrolledStudent()) {
				student.dropCourse(courseID);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if username is existent
	 * @param username
	 * @return true if is already used, false if available
	 */
	public boolean checkUsername(String username) {
		if (usernames.contains(username)) return true;
		return false;
	}
		
	/**
	 * Get Course by courseID
	 * @param courseID
	 * @return Course class of course
	 */
	public Course getCoursebyID(String courseID) {
		for (Course course : this.courses) {
			if (course.getID().equals(courseID)) {
				return course;
			}
		}
		return null;
	}
	
	/**
	 * Get Professor by professorID
	 * @param professorID
	 * @return Professor class of professor
	 */
	public Professor getProfessorbyID(String professorID) {
		for (Professor professor : this.professors) {
			if (professor.getUserID().equals(professorID)) {
				return professor;
			}
		}
		return null;
	}
	
	/**
	 * Get Student by studentID
	 * @param studentID
	 * @return Student class of student
	 */
	public Student getStudentbyID(String studentID) {
		for (Student student : this.students) {
			if (student.getUserID().equals(studentID)) {
				return student;
			}
		}
		return null;
	}
}