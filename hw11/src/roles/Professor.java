/**
 *  Professor Class
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;

public class Professor extends User {
	/**
	 * Courses of lecturer
	 * This will be an ArrayList of Course
	 */
	private ArrayList<Course> teachingcourses = new ArrayList<Course>();
	
	/**
	 * Initialization of lecturers
	 */
	public Professor(String name, String username, String password, String ID) {
		super(name, username, password, ID);
	}
	
	/**
	 * Return string expression of professor
	 * @return
	 */
	public String toString() {
		return "Name: " + this.getName() + "\nUser ID: " + this.getUserID() + "\nCourse: " + teachingcourses + "\n";
	}
	
	/**
	 * Return string expression of courses
	 * @return
	 */
	public String toStringCourse() {
		String coursesString = new String();
		if (this.teachingcourses == null) return null;
		for (Course course : this.teachingcourses) {
			coursesString = coursesString + course.toString() + "/n";
		}
		return coursesString;
	}
	
	/**
	 * Return string expression of all the student of a certain course
	 * @param CourseID
	 * @return
	 */
	public String toStringStudent(String CourseID) {
		String studentString = new String();
		if (this.getStudent(CourseID) == null) return null;
		for (Student student : getStudent(CourseID)) {
			studentString = studentString + student.getUserID() + " " + student.getName() + "\n";
		}
		return studentString;
	}
	
	/**
	 * Get teaching courses
	 * @return Array list of courses
	 */
	public ArrayList<Course> getCourses() {
		return this.teachingcourses;
	}
	
	/**
	 * Get a certain course name
	 * @param courseID
	 * @return
	 */
	public String getCourseNamebyCourseID(String courseID) {
		Course course = this.getCoursebyID(courseID);
		if (course == null) return null;
		return course.getName();
	}
	
	/**
	 * add a new course to professor
	 * @param course
	 * @return 1 if add success, -1 if course is already exist, -3 if there is time conflict
	 */
	public int addCourse(Course course) {
		// Existence validation
		if (this.getCoursebyID(course.getID()) != null) return -1;
		// time validation
		// check for all courses
		for (Course coursetoCompare : teachingcourses) {
            // same day validation
            if (this.sameDayValidation(course, coursetoCompare) == true) {
            	// start time validation
            	if (this.checkTimeConflict(course, coursetoCompare) == true)
            		return -3;
            }            
        }
		// add course to teaching course		
		this.teachingcourses.add(course);
		return 1;
	}
	
	public boolean deletCourse(String courseID) {
		Course course = this.getCoursebyID(courseID);
		return this.teachingcourses.remove(course);
	}
	
	/**
	 * Get a enrolled course by course ID
	 * @param courseID
	 * @return Course class of course
	 */
	private Course getCoursebyID(String courseID) {
		for (Course course : this.teachingcourses) {
			if (course.getID().equals(courseID)) {
				return course;
			}
        }
        return null; // Course with the given ID not found
    }
	
	/**
	 * Get enrolled students in course
	 * @param courseID
	 * @return
	 */
	public ArrayList<Student> getStudent(String CourseID) {
		Course course = this.getCoursebyID(CourseID);
		if (course == null) return null;
		return course.getEnrolledStudent();
	}
	
	public Course getConflictCourse(Course course) {
		for (Course coursetoCompare : this.teachingcourses) {
            // same day validation
            if (this.sameDayValidation(course, coursetoCompare) == true) {
            	// start time validation
            	if (this.checkTimeConflict(course, coursetoCompare) == true)
            		return coursetoCompare;
            }
		}
		return null;
	}
	
}