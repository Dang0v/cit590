/**
 *  Admin Class
 *  
 *  Maintain courses, professor, students list.
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.ArrayList;
import courses.Course;

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
	 * Initialization of Admin
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 */
	public Admin(String name, String username, String password, String userID, ArrayList<Course> courses, ArrayList<Professor> professors, ArrayList<Student> students) {
		super(name, username, password, userID);
		this.courses = courses;
		this.professors = professors;
		this.students = students;
	}
	
	/**
	 * Add a new course
	 * @param coursename
	 * @param courseID
	 * @param professorID
	 * @param days
	 * @param time_start
	 * @param time_end
	 * @param capacity
	 * @return true if success
	 */
	public boolean addCourse(String coursename, String courseID, String professorID, String days, String time_start, String time_end, int capacity) {
		//// check if course is existed
		if (this.getCoursebyID(courseID) == null) {
			return false;
		}
		// get a professor
		Professor professor = this.getProfessorbyID(professorID);
		// TODO professor does not exist, add a new professor
		// TODO check professor conflict
		if (professor == null) {
			// TODO add a new professor
			// this should be in controller
		}
		for (Course course : courses) {
			// course existed
			if(course.getID().equals(courseID))
				return false;
		}
		courses.add(new Course(coursename, courseID, professor, days, time_start, time_end, capacity));
		return true;
	}
	
	/**
	 * Add a new professor
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 * @return true is add success, false if existed
	 */
	public boolean addProfessor(String name, String username, String password, String userID) {
		// check if professor is existed
		if (this.getProfessorbyID(userID) == null) {
			professors.add(new Professor(name, username, password, userID));
			return true;
		}
		return false;
	}
	
	/**
	 * Add a new student
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 * @return true is add success, false if existed
	 */
	public boolean addStudent(String name, String username, String password, String userID) {
		// check if student is existed
		if (this.getStudentbyID(userID) == null) {
			students.add(new Student(name, username, password, userID));
			return true;
		}
		return false;
	}
	
	/**
	 * Remove a course by course ID
	 * @param courseID
	 * @return true if removed
	 */
	public boolean deletCourse(String courseID) {
		Course course = this.getCoursebyID(courseID);
		return this.courses.remove(course);
	}
	
	/**
	 * Remove a professor by professor ID
	 * @param professorID
	 * @return true if removed
	 */
	public boolean deletProfessor(String professorID) {
		Professor professor = this.getProfessorbyID(professorID);
		return this.professors.remove(professor);
	}
	
	/**
	 * Remove a student by student ID
	 * @param studentID
	 * @return true if removed
	 */
	public boolean deletStudent(String studentID) {
		Student student = this.getStudentbyID(studentID);
		return this.students.remove(student);
	}
	
	/**
	 * Get Course by courseID
	 * @param courseID
	 * @return Course class of course
	 */
	private Course getCoursebyID(String courseID) {
		for (Course course : this.courses) {
			if (course.getID().equals(courseID)) {
				return course;
			}
		}
		// TODO need a validation
		return null;
	}
	
	/**
	 * Get Professor by professorID
	 * @param professorID
	 * @return Professor class of professor
	 */
	private Professor getProfessorbyID(String professorID) {
		for (Professor professor : this.professors) {
			if (professor.getUserID().equals(professorID)) {
				return professor;
			}
		}
		// TODO need a validation
		return null;
	}
	
	/**
	 * Get Student by studentID
	 * @param studentID
	 * @return Student class of student
	 */
	private Student getStudentbyID(String studentID) {
		for (Student student : this.students) {
			if (student.getUserID().equals(studentID)) {
				return student;
			}
		}
		// TODO need a validation
		return null;
	}
}