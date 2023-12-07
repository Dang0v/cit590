/**
 * File reader
 * 
 * @author Qiwen Luo
 */
package files;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import roles.*;
import courses.Course;

public class FileInfoReader {
	/**
	 * Courses list
	 */
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	/**
	 * Professors List
	 */
	private ArrayList<Professor> professors = new ArrayList<Professor>();
	
	/**
	 * Students List
	 */
	private ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Admin List
	 */
	private ArrayList<Admin> admins = new ArrayList<Admin>();
	
	/**
	 * Initialization of file reader
	 * @param coursePath
	 * @param studentPath
	 * @param professorPath
	 * @param adminPath
	 * @param courses
	 * @param professors
	 * @param students
	 */
	public FileInfoReader(String coursePath, String studentPath, String professorPath, String adminPath, 
			ArrayList<Course> courses, ArrayList<Professor> professors, ArrayList<Student> students, ArrayList<Admin> admins){
		this.courses = courses;
		this.professors = professors;
		this.students = students;
		this.admins = admins;
		this.loadAdmin(adminPath);
		this.loadProfessorWithoutCourse(professorPath);
		this.loadCourse(coursePath);
		this.loadStudent(studentPath);
	}
	
	/**
	 * Load professor information without course
	 * @param professorPath
	 */
	private void loadProfessorWithoutCourse(String professorPath) {
		try {
			File f = new File(professorPath);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				} else {
					// processes student data 
					String [] array = line.trim().split(";");
					String name = array[0].trim();
					String ID = array[1].trim();
					String username = array[2].trim();
					String password = array[3].trim();
					Professor professor = new Professor(name, username, password, ID);
					this.professors.add(professor);
					// need to add course in load course.
				}
			}				
			fr.close();
			br.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load admin information from given path
	 * @param adminPath
	 */
	private void loadAdmin(String adminPath) {
		try {
			File f = new File(adminPath);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				} else {
					// processes student data 
					String [] array = line.trim().split(";");
					String ID = array[0].trim();
					String name = array[1].trim();
					String username = array[2].trim();
					String password = array[3].trim();
					Admin admin = new Admin(name, username, password, ID, this.courses, this.professors, this.students);
					this.admins.add(admin);
					// need to add course in load course.
				}
			}				
			fr.close();
			br.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load student information from given path, after course is loaded
	 * @param adminPath
	 */
	private void loadStudent(String studentPath) {
		try {
			File f = new File(studentPath);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				} else {
					// processes student data 
					String [] array = line.trim().split(";");
					String ID = array[0].trim();
					String name = array[1].trim();
					String username = array[2].trim();
					String password = array[3].trim();
					// create a new student
					Student student = new Student(name, username, password, ID);
					// the rest of strings are course and grades
					String [] grade_array = array[4].split(",");
					for (int i = 0; i < grade_array.length; i++) {
						String courseID = grade_array[i].trim().split(":")[0].trim().toUpperCase();
						String grade = grade_array[i].trim().split(":")[1].trim();
						// add a certain course class to student
						Course course = this.getCoursebyID(courseID);
						student.addCourse(course);
						student.addGradeToCourse(courseID, grade);
					}
					this.students.add(student);
				}
			}				
			fr.close();
			br.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a professor class by name
	 * @param name
	 * @return
	 */
	private Professor getProfessorbyName(String name) {
		for (Professor professor : this.professors) {
			if (professor.getName().equals(name))
				return professor;
		}
		return null;
	}
	
	/**
	 * Load course from file. Need professor loaded first.
	 * @param coursePath
	 */
	private void loadCourse(String coursePath) {
		try {
			File f = new File(coursePath);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				} else {
					String [] array = line.trim().split(";");
					String courseID = array[0].trim().toUpperCase();
					String coursename = array[1].trim();
					String professor_str = array[2].trim();
					String days = array[3].trim();
					String time_start = array[4].trim();
					String time_end = array[5].trim();
					int capacity = Integer.parseInt(array[6].trim());
					// find the professor by name
					Professor professor = this.getProfessorbyName(professor_str);
					Course course = new Course(courseID, coursename, professor, days, time_start, time_end, capacity);
					this.courses.add(course);
					// add course to professor class
					professor.addCourse(course);
				}
				
			}				
			fr.close();
			br.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	private Course getCoursebyID (String courseID) {
		for (Course course : this.courses) {
			if (course.getID().equals(courseID))
				return course;
		}
		return null;
	}
	
}