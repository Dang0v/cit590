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
		
		Course course2 = new Course("courseID2", "coursename2", professor , "TR", "12:00", "13:15", 10);
		assertEquals(1, this.professor.addCourse(course2));
		assertEquals(2, this.professor.getCourses().size());
		
		Course course3 = new Course("courseID3", "coursename3", professor , "MWF", "11:00", "13:00", 10);
		Course course4 = new Course("courseID4", "coursename4", professor , "MF", "11:00", "13:00", 10);
		Course course5 = new Course("courseID5", "coursename5", professor , "TR", "8:00", "10:00", 10);
		
		// add an existent course
 		assertEquals(-1, this.professor.addCourse(course));
 		assertEquals(2, this.professor.getCourses().size());
 		// add a course with time conflict
 		assertEquals(-3, this.professor.addCourse(course3));
 		assertEquals(-3, this.professor.addCourse(course4));
 		assertEquals(2, this.professor.getCourses().size());
 		
 		// add a valid course
 		assertEquals(1, this.professor.addCourse(course5));
 		assertEquals(3, this.professor.getCourses().size());
		
//		System.out.print(this.student.toString());
	}
	
	@Test
	void testDeletCourse() {
		// add a normal course
		assertEquals(1, this.professor.addCourse(course));
		Course course2 = new Course("courseID2", "coursename2", professor , "TR", "12:00", "13:15", 10);
		assertEquals(1, this.professor.addCourse(course2));
		
		// delete a non-existent course
		assertEquals(false, this.professor.deletCourse("courseID3"));
		assertEquals(2, this.professor.getCourses().size());
		
		// delete an existent course
		assertEquals(true, this.professor.deletCourse("courseID"));
		assertEquals(1, this.professor.getCourses().size());
		
		assertEquals(true, this.professor.deletCourse("courseID2"));
		assertEquals(0, this.professor.getCourses().size());
		
		
	}
	
	@Test
	void testGetStudent() {
		assertEquals(1, this.professor.addCourse(course));
		
		assertEquals(1, this.professor.getStudent(this.course.getID()).size());
	}
	
	
}