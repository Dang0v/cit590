/**
 * Class for courses
 * 
 * @author Qiwen Luo
 */

package courses;

import java.util.ArrayList;
import roles.Student;
import roles.Professor;

public class Course {
	
	/**
	 * course name
	 */
	private String coursename;
	
	/**
	 * course ID
	 */
	private String courseID;
	
	/**
	 * course professor
	 */
	private Professor professor;
	
	/**
	 * course days
	 */
	private String days;
	
	/**
	 * course start time
	 */
	private String time_start;
	
	/**
	 * course end time
	 */
	private String time_end;
	
	/**
	 * course capacity
	 */
	private int capacity;
	
	/**
	 * enrolled student
	 */
	private ArrayList<Student> student_enrolled = new ArrayList<Student>();
	
	/**
	 * initialization of course
	 * @param coursename
	 * @param courseID
	 * @param lecturer
	 * @param days
	 * @param time_start
	 * @param time_end
	 * @param capacity
	 */
	public Course(String courseID, String coursename, Professor professor, String days, String time_start, String time_end, int capacity) {
		this.coursename = coursename;
		this.courseID = courseID;
		this.professor = professor;
		this.days = days;
		this.time_start = time_start;
		this.time_end = time_end;
		this.capacity = capacity;
	}
	
	public String getName() {
		return this.coursename;
	}
	
	public String getID() {
		return this.courseID;
	}
	
	public Professor getLecturer() {
		return this.professor;
	}
	
	public String getDays() {
		return this.days;
	}
	
	public String getStartTime() {
		return this.time_start;
	}
	
	public int getStartTimeInt() {
		return this.timeconverter(time_start);
	}
	
	public String getEndTime() {
		return this.time_end;
	}
	
	public int getEndTimeInt() {
		return this.timeconverter(time_end);
	}
	
	/**
	 * Get capacity
	 * @return integer of capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Get enrolled student array list
	 * @return
	 */
	public ArrayList<Student> getEnrolledStudent() {
		return this.student_enrolled;
	}
	
	/**
	 * Add a new enrolled student
	 * @param Student class of student
	 */
	public void addEnrolledStudent(Student student) {
		this.student_enrolled.add(student);
	}
	
	/**
	 * Convert string type time to an integer, the input should be in "xx:xx" format
	 * @param time_str
	 * @return integer type express of time, 0 if input is invalid
	 */
	private int timeconverter(String time_str) {
		String buffer [] = time_str.split(":");
		if (buffer.length != 2) return 0;
		int time_int = Integer.parseInt(buffer[0]) * 60 + Integer.parseInt(buffer[1]);
		return time_int;
	}
	
}