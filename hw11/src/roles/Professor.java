/**
 *  Professor Class
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.ArrayList;

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
	 */
	public String toString() {
		return "Name: " + this.getName() + "\nUser ID: " + this.getUserID() + "\nCourse: " + teachingcourses + "\n";
	}
	
	/**
	 * Get teaching courses
	 * @return Array list of courses
	 */
	public ArrayList<Course> getCourses() {
		return this.teachingcourses;
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
		return course.getEnrolledStudent();
	}
	
}