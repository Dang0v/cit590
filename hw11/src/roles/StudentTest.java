/**
 * All function in Student class test.
 * And protected level function in user class test.
 * 
 * @author Qiwen LUo
 */

package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;

class StudentTest {
	
	Student student;
	Course course;
	Professor professor;
	Scanner scanner = new Scanner(System.in);
	
	@BeforeEach
	void setUp() throws Exception {
		student = new Student("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		professor = new Professor("Professor", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID");
		course = new Course("courseID", "coursename", professor , "MW", "12:00", "13:15", 10);
	}
	
	@Test
	void testStudentGetters() {
		// test getters
		assertEquals("Studentname1", this.student.getName());
		assertEquals("StudentUsername1", this.student.getUserName());
		assertEquals("StudentID1", this.student.getUserID());
		// password should be a private function, only public for test here
		// assertEquals("StudentPassword1", this.student.getPassword());
	}
	
	@Test
	// test add course function and add course with grade function
	void testAddCourse() {
		// add a normal course
		assertEquals(1, this.student.addCourse(course));
		this.student.addGradeToCourse("courseID", "A");
		assertEquals(true, this.student.getCoursesWithGrade().containsKey(course));
		assertEquals(1, this.student.getCoursesWithGrade().size());
		
		// Test for enrolled student size
		assertEquals(1, this.course.getEnrolledStudent().size());
		// add another student
		Student student2 = new Student("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2");
		assertEquals(1, student2.addCourse(course));
		assertEquals(2, this.course.getEnrolledStudent().size());
		
		// other invalid add course is test in same day validation and start time validation
		
//		System.out.print(this.student.toString());
	}
	
	@Test
	// this should be a protected function in User class
	void testSameDayValidation() {
		// check course with same days
		Course course2 = new Course("courseID2", "coursename2", professor , "MW", "12:00", "13:15", 10);
		assertEquals(true, this.student.sameDayValidation(course, course2));
		Course course3 = new Course("courseID3", "coursename3", professor , "MWF", "11:00", "13:00", 10);
		assertEquals(true, this.student.sameDayValidation(course, course3));
		
		// check course with mutual day
		Course course4 = new Course("courseID4", "coursename4", professor , "MF", "11:00", "13:00", 10);
		assertEquals(true, this.student.sameDayValidation(course, course4));
		
		// check course with different days
		Course course5 = new Course("courseID5", "coursename5", professor , "TR", "8:00", "10:00", 10);
		assertEquals(false, this.student.sameDayValidation(course, course5));
		
		// check if course is added this should be correct after function addCourse logic is complete
 		assertEquals(1, this.student.addCourse(course));
 		assertEquals(-2, this.student.addCourse(course2));
 		assertEquals(-2, this.student.addCourse(course3));
 		assertEquals(-2, this.student.addCourse(course4));
 		assertEquals(1, this.student.addCourse(course5));
 		assertEquals(2, this.student.getCoursesWithGrade().size());
//		System.out.print(this.student.toString());
	}
	
	@Test
	// this should be a protected function in User class
	void testStartTimeValidation() {
		// check course with same time
		Course course2 = new Course("courseID2", "coursename2", professor , "MW", "12:00", "13:15", 10);
		assertEquals(true, this.student.checkTimeConflict(course, course2));
		
		// check course with overlapped time
		Course course3 = new Course("courseID3", "coursename3", professor , "MW", "11:00", "13:00", 10);
		Course course4 = new Course("courseID4", "coursename4", professor , "MW", "12:15", "13:30", 10);
		assertEquals(true, this.student.checkTimeConflict(course, course3));
		assertEquals(true, this.student.checkTimeConflict(course, course4));
		
		// check course not in the same period
		Course course5 = new Course("courseID5", "coursename5", professor , "MW", "8:00", "10:00", 10);
		assertEquals(false, this.student.checkTimeConflict(course, course5));
		
		
		// check if course is added this should be correct after function addCourse logic is complete
 		assertEquals(1, this.student.addCourse(course));
 		assertEquals(-2, this.student.addCourse(course2));
 		assertEquals(-2, this.student.addCourse(course3));
 		assertEquals(-2, this.student.addCourse(course4));
 		assertEquals(1, this.student.addCourse(course5));
 		assertEquals(2, this.student.getCoursesWithGrade().size());
//		System.out.print(this.student.toString());
	}
	
}