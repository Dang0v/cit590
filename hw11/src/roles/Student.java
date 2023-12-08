/**
 *  Student class
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.ArrayList;
import java.util.HashMap;
import courses.Course;

public class Student extends User {	
	/**
	 * Course with grades
	 * This will be HashMap of Course and List<String>
	 */
	private HashMap<Course, String> enrolledcourses = new HashMap<Course, String>();
		
	/**
	 * initialization of Student
	 */
	public Student(String name, String username, String password, String userID) {
		super(name, username, password, userID);
	}
	
	/**
	 * String output for class Student
	 */
	public String toString() {
		ArrayList<String> coursewithgrades = new ArrayList<String>();
		for (HashMap.Entry<Course, String> entry : enrolledcourses.entrySet()) {
            String coursename = entry.getKey().getName();
            String grade = entry.getValue();
            coursewithgrades.add(coursename + " : " + grade);
        }
		return "Name: " + this.getName() + "\nUser ID: " + this.getUserID() + "\nCourse: " + coursewithgrades + "\n";
	}
		
	/**
	 * Get enrolled courses with grades
	 * @return HashMap of courses
	 */
	public HashMap<Course, String> getCoursesWithGrade() {
		return this.enrolledcourses;
	}
	
	/**
	 * Get grade of a course by courseID
	 * @param courseID
	 * @return
	 */
	public String getCourseGrade(String courseID) {
		Course course = this.getCoursebyID(courseID);
		return enrolledcourses.get(course);
	}
	
	/**
	 * Enroll a new course, add course to student class, add student to course class at the same time
	 * @param courseID
	 * @return 1 if add success, -1 if course is already exist, -3 if there is time conflict with student
	 */
	public int addCourse(Course course) {
		// Existence validation
		if (this.getCoursebyID(course.getID()) != null) return -1;
		// time validation
		// check for all courses
		for (HashMap.Entry<Course, String> entry : enrolledcourses.entrySet()) {
			// check for days
            Course coursetoCompare = entry.getKey();
            // same day validation
            if (this.sameDayValidation(course, coursetoCompare) == true) {
            	// start time validation
            	if (this.checkTimeConflict(course, coursetoCompare) == true)
            		return -3;
            }            
        }
		this.enrolledcourses.put(course, new String());
		// add student to course class
		course.addEnrolledStudent(this);
		return 1;
	}
		
	/**
	 * Add a grade to a existed course by courseID
	 * @param courseID
	 * @param grade
	 * @return true if course exist, false if student does not enroll this course
	 */
	public boolean addGradeToCourse(String courseID, String grade) {
		Course course = this.getCoursebyID(courseID);
		if (course == null) return false;
		// course validation
		if(enrolledcourses.containsKey(course)) {
			enrolledcourses.put(course, grade);
			return true;
		// add grade failed
		} else {
			return false;
		}
	}
	
	/**
	 * Drop a course by course ID
	 * @param courseID
	 * @return true if course dropped
	 */
	public boolean dropCourse(String courseID) {
		Course course = this.getCoursebyID(courseID);
		if(enrolledcourses.containsKey(course)) {
			enrolledcourses.remove(course);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get a enrolled course by course ID
	 * @param courseID
	 * @return Course class of course
	 */
	private Course getCoursebyID(String courseID) {
		for (HashMap.Entry<Course, String> entry : enrolledcourses.entrySet()) {
            if (entry.getKey().getID() == courseID) {
                return entry.getKey();
            }
        }
		// TODO need a validation
        return null;
    }
	
	
	
}