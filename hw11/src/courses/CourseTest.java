/**
 * All Course in Student class test.
 * 
 * @author Qiwen LUo
 */

package courses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roles.*;

class CourseTest {
	
	Student student1, student2;
	Course course1;
	Professor professor1;
	Scanner scanner = new Scanner(System.in);
		
	@BeforeEach
	void setUp() throws Exception {
		student1 = new Student("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		student2 = new Student("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2");
		professor1 = new Professor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1");
		course1 = new Course("courseID1", "coursename1", professor1, "MW", "12:00", "13:15", 10);
		student1.addCourse(course1);
	}
	
	@Test
	void testGetters() {
		assertEquals("courseID1", this.course1.getID());
		assertEquals("coursename1", this.course1.getName());
		assertEquals(professor1, this.course1.getProfessor());
		assertEquals("MW", this.course1.getDays());
		assertEquals("12:00", this.course1.getStartTime());
		assertEquals("13:15", this.course1.getEndTime());
		assertEquals(10, this.course1.getCapacity());
//		System.out.println(this.course1.toString());
	}
	
	@Test
	void testTimeConverter() {
		assertEquals(720, this.course1.getStartTimeInt());
		assertEquals(795, this.course1.getEndTimeInt());
	}
	
	@Test
	void testModifyStudent() {
		this.course1.addEnrolledStudent(student2);
		assertEquals(2, this.course1.getEnrolledStudent().size());
		this.course1.deleteEnrooledStudent(student1);
		assertEquals(1, this.course1.getEnrolledStudent().size());
	}
	
	
}