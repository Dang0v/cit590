/**
 * All function in Admin class test.
 * 
 * @author Qiwen LUo
 */

package roles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;

class AdminTest {
	
	Admin admin;
	
	Scanner scanner = new Scanner(System.in);
	
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Professor> professors = new ArrayList<Professor>();
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<String> usernames = new ArrayList<String>();
	
	@BeforeEach
	void setUp() throws Exception {
		admin = new Admin("ad", "adusername", "adpassword", "adminid", courses, professors, students, usernames);
	}
	
	@Test
	void testAdminGetters() {
		// test getters
		assertEquals("ad", this.admin.getName());
		assertEquals("adusername", this.admin.getUserName());
		assertEquals("adminid", this.admin.getUserID());
		// password should be a private function, only public for test here
		// assertEquals("adpassword", this.admin.getPassword());
	}
	
	@Test
	// This test should be done first.
	void testAddProfessor() {
		assertEquals(1, this.admin.addProfessor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1"));
		assertEquals(1, this.professors.size());
		
		// test for the same professorID
		assertEquals(-1, this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID1"));
		assertEquals(1, this.professors.size());
		// test for the same username
		assertEquals(-5, this.admin.addProfessor("Professor2", "ProfessorUsername1", "ProfessorPassword2", "ProfessorID2"));
		assertEquals(1, this.professors.size());
		
		// test another professor
		assertEquals(1, this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID2"));
		assertEquals(2, this.professors.size());
	}
	
	@Test
	void testDeletProfessor() {
		this.admin.addProfessor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1");
		this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID2");
		
		// Delete a non-existent professor
		assertEquals(false, this.admin.deletProfessor("ProfessorID3"));
		assertEquals(2, this.professors.size());
		
		// Delete an existent professor
		assertEquals(true, this.admin.deletProfessor("ProfessorID2"));
		assertEquals(1, this.professors.size());
	}
	
	@Test
	void testAddStudent() {
		assertEquals(1, this.admin.addStudent("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1"));
		assertEquals(1, this.students.size());
		
		// test for the same studentID
		assertEquals(-1, this.admin.addStudent("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID1"));
		assertEquals(1, this.students.size());
		// test for the same username
		assertEquals(-5, this.admin.addStudent("Studentname2", "StudentUsername1", "StudentPassword2", "StudentID2"));
		assertEquals(1, this.students.size());
		
		// test another student
		assertEquals(1, this.admin.addStudent("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2"));
		assertEquals(2, this.students.size());
	}
	
	@Test
	// this is a test for addStudentCourse and addStudentCoursGrade
	void testAddStduentCourse() {
		// setup
		// add professor first
		this.admin.addProfessor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1");
		this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID2");
		assertEquals(2, this.professors.size());
		Professor professor1 = professors.get(0);
		Professor professor2 = professors.get(1);
		// add 2 student
		this.admin.addStudent("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		this.admin.addStudent("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2");
		assertEquals(2, this.students.size());
		Student student1 = students.get(0);
		Student student2 = students.get(1);
		assertEquals(0, student1.getCoursesWithGrade().size());
		assertEquals(0, student2.getCoursesWithGrade().size());
		// add courses
		this.admin.addCourse("courseID1", "coursename1", "ProfessorID1" , "MW", "12:00", "13:15", 10);
		this.admin.addCourse("courseID2", "coursename2", "ProfessorID2" , "MW", "12:00", "13:15", 10);
		this.admin.addCourse("courseID3", "coursename3", "ProfessorID1" , "TR", "12:00", "13:15", 10);
		assertEquals(3, this.courses.size());
		Course course1 = this.courses.get(0);
		Course course2 = this.courses.get(1);
		Course course3 = this.courses.get(2);
		
		// add a grade to a non-exsitent student
		assertEquals(-1, this.admin.addStudentCourseGrade("StudentID3", "courseID4", "A"));
		// add a grade to a non-exsitent course
		assertEquals(-2, this.admin.addStudentCourseGrade("StudentID1", "courseID4", "A"));
		// add a grade to a non-enrolled course
		assertEquals(-4, this.admin.addStudentCourseGrade("StudentID1", "courseID1", "A"));
		
		// add a course to a student
		assertEquals(1, this.admin.addStudentCourse("StudentID1", "courseID1"));
		// add a grade to an enrolled course
		assertEquals(1, this.admin.addStudentCourseGrade("StudentID1", "courseID1", "A"));
		// add a course to a non-existent student
		assertEquals(-1, this.admin.addStudentCourse("StudentID4", "courseID2"));
		// add a non-existent course
		assertEquals(-2, this.admin.addStudentCourse("StudentID1", "courseID4"));
		// add a course with time conflict
		assertEquals(-3, this.admin.addStudentCourse("StudentID1", "courseID2"));
		
		
	}
	
	@Test
	void testDeletStudent() {
		// add 2 students
		this.admin.addStudent("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		this.admin.addStudent("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2");
		
		// Delete a non-existent student
		assertEquals(false, this.admin.deletStudent("StudentID3"));
		assertEquals(2, this.students.size());
		
		// Delete an existent student
		assertEquals(true, this.admin.deletStudent("StudentID2"));
		assertEquals(1, this.students.size());
	}
	
	@Test
	void testAddCourse() {
		// before add course, there should be professors
		this.admin.addProfessor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1");
		this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID2");
		assertEquals(2, this.professors.size());
		Professor professor1 = professors.get(0);
		Professor professor2 = professors.get(1);
		
		// add a normal course
		assertEquals(1, this.admin.addCourse("courseID1", "coursename1", "ProfessorID1" , "MW", "12:00", "13:15", 10));
		assertEquals(1, this.courses.size());
		// check if this course is add to professor
		assertEquals(1, professor1.getCourses().size());
		assertEquals(professor1, courses.get(0).getProfessor());
		
		// test add a course with same course ID
		assertEquals(-1, this.admin.addCourse("courseID1", "coursename2", "ProfessorID1" , "MW", "12:00", "13:15", 10));
		assertEquals(1, this.courses.size());
		
		// test add a course with a non-existent professor
		assertEquals(-2, this.admin.addCourse("courseID2", "coursename2", "ProfessorID3" , "MW", "12:00", "13:15", 10));
		assertEquals(1, this.courses.size());
		
		// test add a course with professor time conflict
		assertEquals(-3, this.admin.addCourse("courseID2", "coursename2", "ProfessorID1" , "MW", "12:00", "13:15", 10));
		assertEquals(1, this.courses.size());
		
		// test add a correct course
		assertEquals(1, this.admin.addCourse("courseID2", "coursename2", "ProfessorID2" , "MW", "12:00", "13:15", 10));
		assertEquals(2, this.courses.size());
		assertEquals(1, professor2.getCourses().size());
		
		assertEquals(1, this.admin.addCourse("courseID3", "coursename3", "ProfessorID1" , "TR", "12:00", "13:15", 10));
		assertEquals(3, this.courses.size());
		assertEquals(2, professor1.getCourses().size());		
	}
	
	@Test
	void testDeleteCourse() {
		// setup
		// add professor first
		this.admin.addProfessor("Professor1", "ProfessorUsername1", "ProfessorPassword1", "ProfessorID1");
		this.admin.addProfessor("Professor2", "ProfessorUsername2", "ProfessorPassword2", "ProfessorID2");
		assertEquals(2, this.professors.size());
		Professor professor1 = professors.get(0);
		Professor professor2 = professors.get(1);
		// add student
		this.admin.addStudent("Studentname1", "StudentUsername1", "StudentPassword1", "StudentID1");
		this.admin.addStudent("Studentname2", "StudentUsername2", "StudentPassword2", "StudentID2");
		assertEquals(2, this.students.size());
		Student student1 = students.get(0);
		Student student2 = students.get(1);
		// add courses
		this.admin.addCourse("courseID1", "coursename1", "ProfessorID1" , "MW", "12:00", "13:15", 10);
		this.admin.addCourse("courseID2", "coursename2", "ProfessorID2" , "MW", "12:00", "13:15", 10);
		this.admin.addCourse("courseID3", "coursename3", "ProfessorID1" , "TR", "12:00", "13:15", 10);
		assertEquals(3, this.courses.size());
		Course course3 = this.courses.get(2);
		// student enrollment
		student1.addCourse(course3);
		student2.addCourse(course3);
		assertEquals(1, student1.getCoursesWithGrade().size());
		assertEquals(1, student2.getCoursesWithGrade().size());
		
		// delete a non-existent course
		assertEquals(false, this.admin.deletCourse("courseID4"));
		assertEquals(3, this.courses.size());
		assertEquals(1, student1.getCoursesWithGrade().size());
		assertEquals(1, student2.getCoursesWithGrade().size());
		
		// delete an existent course
		assertEquals(true, this.admin.deletCourse("courseID3"));
		assertEquals(2, this.courses.size());
		// check professor and students
		assertEquals(1, professor1.getCourses().size());
		assertEquals(1, professor2.getCourses().size());
		assertEquals(0, student1.getCoursesWithGrade().size());
		assertEquals(0, student2.getCoursesWithGrade().size());
		
	}
	
	
	
	
}