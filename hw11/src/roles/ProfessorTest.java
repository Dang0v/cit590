/**
 * All function in Professor class tests.
 * 
 * 
 * @author Qiwen Luo
 */

package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;

class ProfessorTest {
	
	Course course;
	Student student;
	Professor professor;
	Scanner scanner = new Scanner(System.in);
	
	@BeforeEach
	void setUp() throws Exception {
		student = new Student("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		professor = new Professor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID");
		course = new Course("courseID", "coursename", professor , "MW", "12:00", "13:15", 10);
		this.student.addCourse(course);
	}
	
	@Test
	void testProfessorGetters() {
		// test getters
		assertEquals("Professor1", this.professor.getName());
		assertEquals("ProfessorUsername1", this.professor.getUserName());
		assertEquals("ProfessorID", this.professor.getUserID());
		// password should be a private function, only public for test here
		// assertEquals("StudentPassword1", this.student.getPassword());
	}
	
	@Test
	void testAddCourse() {
		// add a normal course
		assertEquals(1, this.professor.addCourse(course));
		assertEquals(1, this.professor.getCourses().size());
		
		// other invalid add course is test in same day validation and start time validation
		
//		System.out.print(this.student.toString());
	}
	
	@Test
	void testGetStudent() {
		assertEquals(1, this.professor.addCourse(course));
		
		assertEquals(1, this.professor.getStudent(this.course.getID()).size());
	}
	
	
}