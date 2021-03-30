package com.schoolmanagement.utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.schoolmanagement.containers.School;
import com.schoolmanagement.exception.ClassroomExistsException;
import com.schoolmanagement.objects.Classroom;
import com.schoolmanagement.objects.Student;
import com.schoolmanagement.objects.Teacher;

public class Menu {
	
	public static void start() {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\n");
		boolean running = true;
		
		while (running) {
			System.out.println("Menu:");
			System.out.println("1. Student Operations");
			System.out.println("2. Teacher Operations");
			System.out.println("3. Classroom Operations");
			System.out.println("0. EXIT");
			int menuInput = input.nextInt();
			if (menuInput == 0) {
				running = false;
			}else if (menuInput == 1) {
				while(studentOperations()) {
					
				}
				continue;
			}else if(menuInput == 2){
				while(teacherOperations()) {
					
				}
				continue;
			}else if(menuInput == 3) {
				try {
					while(classroomOperations()) {
						
					}
				} catch (ClassroomExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			else {
				System.out.println("Invalid Command");
			}
		}
	}// END Start
	
	
	static boolean studentOperations() {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\n");
		boolean running = true;
		while(running){
			System.out.println("Student Operations:");
			System.out.println("1. Register New Student");
			System.out.println("2. List All Students");
			System.out.println("3. Update Student Name");
			System.out.println("4. Search Student");
			System.out.println("0. BACK");
			int studentInput = input.nextInt();
			if (studentInput == 0) {
				running = false;
			}else if (studentInput == 1) {//REGISTER NEW STUDENT
				Student student = new Student();
				
				System.out.println("Name of the new Student: ");
				String studentName = input.next();
				int studentAge;
				do {
					System.out.println("Age of the Student(Between 7 and 11): ");
					studentAge = input.nextInt();
				}while (studentAge < 7 || studentAge > 11);

				student.setFullName(studentName);
				
				student.setAge(studentAge);
				School.persist();
			}else if(studentInput == 2) {//LIST ALL STUDENTS
				School.listStudents();
			}else if (studentInput == 3) {//UPDATE STUDENT NAME
				System.out.println("Please enter student ID");
				String id = input.next();
				System.out.println("Please enter the new name");
				String newName = input.next();
				School.updateStudentName(id, newName);
				School.persist();
			}else if(studentInput == 4) {
				System.out.println("Please enter Id or the Name");
				String keyword = input.next();
				School.searchStudent(keyword);
			}
			else {
				System.out.println("Invalid Command");
			}
		}

		
		return running;
	}// END Student Operations
	static boolean teacherOperations() {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\n");
		boolean running = true;
		while (running) {
			System.out.println("Teacher Operations:");
			System.out.println("1. Register New Teacher");
			System.out.println("2. Assign Teacher to a Classroom");
			System.out.println("0. BACK");
			int teacherInput = input.nextInt();
			if (teacherInput == 0) {
				running = false;
			}else if(teacherInput == 1) {
				Teacher teacher = new Teacher();
				System.out.println("Name of the new Teacher: ");
				String teacherName = input.next();
				teacher.setFullName(teacherName);
				School.persist();
			}else if(teacherInput == 2) {
				School.listTeachers();
				System.out.println("Please enter the ID of the Teacher");
				String teacherId = input.next();
				School.listClassrooms();
				System.out.println("Please enter the ID of the Classroom from above");
				String classroomId = input.next();
				
				Teacher teacher = School.getTeacher(teacherId);
				if (teacher != null) {
					teacher.setAssignedClass(classroomId);
				}else {
					System.out.println("Cannot find Teacher associated with the ID");
				}
				School.persist();
			}
			else {
				System.out.println("Invalid Command");
			}
		}
		return running;
	}//END Teacher Operations
	
	static boolean classroomOperations() throws ClassroomExistsException {
		Scanner input = new Scanner(System.in);
		input.useDelimiter("\\n");
		boolean running = true;
		System.out.println("Classroom Operations:");
		System.out.println("1. Create New Classroom");
		System.out.println("2. Get Student List of A Classroom");
		System.out.println("0. BACK");
		int classroomInput = input.nextInt();
		if (classroomInput == 0) {
			running = false;
		}else if(classroomInput == 1) {
			System.out.println("Please enter the grade of the classroom: ");
			int gradeInput = input.nextInt();
			if (School.classrooms.get(gradeInput) == null) {
				Classroom classroom = new Classroom(gradeInput);
			}else {
				throw new ClassroomExistsException("A classroom for this grade " + gradeInput + " already created");
			}
		}else if (classroomInput == 2) {
			School.listClassrooms();
			System.out.println("Please Enter the ID of the Classroom");
			Scanner input2 = new Scanner(System.in);
			String classroomSelect = input2.nextLine();
			Classroom classroom = School.getClassroom(classroomSelect);
			if (classroom == null) {
				System.out.println("Classroom Not Found!");
			}else {
				System.out.println("here");
				ArrayList<String> studentList = classroom.studentIdList;
				School.listStudents(studentList);
			}
		}else {
			System.out.println("Invalid Command");
		}
		return running;
	}//END Classroom Operations
	
	
}
