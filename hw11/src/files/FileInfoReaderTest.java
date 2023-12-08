/**
 * All function in FileInfoReader class test.
 * And protected level function in user class test.
 * 
 * @author Qiwen Luo
 */

package files;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.Course;
import roles.*;

class FileInfoReaderTest {
	Scanner scanner = new Scanner(System.in);
	
	FileInfoReader filereader;

	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Professor> professors = new ArrayList<Professor>();
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<Admin> admins = new ArrayList<Admin>();
	ArrayList<String> usernames = new ArrayList<String>();
	
	String coursePath = "courseInfo.txt"; 
	String studentPath = "studentInfo.txt";
	String professorPath = "profInfo.txt";
	String adminPath = "adminInfo.txt";
	
	@BeforeEach
	void setUp() throws Exception {
		this.filereader = new FileInfoReader(courses, professors, students, admins, usernames);
	}
	
	@Test
	void testSetUp() {
		this.filereader.setup(coursePath, studentPath, professorPath, adminPath);
		assertEquals(32, this.professors.size());
		assertEquals(50, this.courses.size());
		assertEquals(2, this.students.size());
		assertEquals(3, this.admins.size());
		assertEquals(37, this.usernames.size());
		
		assertEquals("Mathematical Foundations of Computer Science", this.professors.get(0).getCourses().get(0).getName());
		Course course = courses.get(0);
		System.out.println(course.toString());
		// check if course has correct student
		for (Course course_temp : courses) {
			if(course_temp.getID().equals("CIS191")) {
				course = course_temp;
				break;
			}
		}
		assertEquals("001", course.getEnrolledStudent().get(0).getUserID());
		System.out.println(course.toString());
	}
	
	@Test
	void testLogin() {
		this.filereader.setup(coursePath, studentPath, professorPath, adminPath);
		
		String username = "Greenberg";
		String password = "password590";
		// professor login
		for (User professor : professors) {
			if (professor.getUserName().equals(username)) {
				System.out.println("Find!\n");
				if (professor.userLogin(password))
					System.out.println("Login success!\n");
			}
		}
	}
	
	
}