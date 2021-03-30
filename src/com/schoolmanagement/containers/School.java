package com.schoolmanagement.containers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.schoolmanagement.objects.Classroom;
import com.schoolmanagement.objects.SchoolObject;
import com.schoolmanagement.objects.Student;
import com.schoolmanagement.objects.Teacher;
import com.schoolmanagement.utils.SchoolXOMElementConverter;
import com.schoolmanagement.utils.XOMElementConverter;
import com.schoolmanagement.utils.XOMSchoolInput;
import com.schoolmanagement.utils.XOMSchoolOutput;


public class School{
	public static ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	public static ArrayList<Student> students = new ArrayList<Student>();
	public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	
	public static Classroom getClassroom(String id) {
		Classroom classroom = null;
		for(int i = 0; i < classrooms.size(); i++) {
			if(classrooms.get(i).getId().equalsIgnoreCase(id)) {
				classroom = classrooms.get(i);
			}
		}
		return classroom;
	}

	public static Student getStudent(String id) {
		Student student = null;
		for (int i = 0; i < students.size(); i++) {
			if(students.get(i).getId().equalsIgnoreCase(id)) {
				student = students.get(i);
			}
		}
		return student;
	}
	public static Teacher getTeacher(String id) {
		Teacher teacher = null;
			for (int i = 0; i < teachers.size(); i++) {
				if (teachers.get(i).getId().equalsIgnoreCase(id)) {
					teacher = teachers.get(i);
				}
			}
		return teacher;
	}
	public static Classroom getClassroomForStudentAssignment(int grade) {
		Classroom classroom = null;
		for (int i = 0; i < classrooms.size(); i++) {
			if (classrooms.get(i).getGrade() == grade) {
				if (classrooms.get(i).studentIdList.size() < 5) {
					classroom = classrooms.get(i);
				}else {
					continue;
				}
				
			}
		}
		return classroom;
	}
	public static void addSchoolObjectByType(SchoolObject schoolObject) {
		if (schoolObject.getSchoolObjectType().equalsIgnoreCase("Student")) {
			Student student = (Student) schoolObject;
			students.add(student);
		}else if(schoolObject.getSchoolObjectType().equalsIgnoreCase("Classroom")) {
			Classroom classroom = (Classroom) schoolObject;
			classrooms.add(classroom);
		}else if(schoolObject.getSchoolObjectType().equalsIgnoreCase("Teacher")) {
			Teacher teacher = (Teacher) schoolObject;
			teachers.add(teacher);
		}
	}
	public static void listStudents() {
		System.out.printf(Formats.STUDENT_HEADER_FORMAT, "ID", "Name", "Age", "Classroom");
		System.out.println("----------------------------------------");
		for(int i = 0; i < students.size(); i++) {
		    Student student = students.get(i);
			System.out.printf(Formats.STUDENT_LIST_FORMAT, student.getId(), student.getFullName(), student.getAge(), student.getAssignedClass());
		}
		System.out.println("----------------------------------------");
	}
	public static void listStudents(ArrayList<String> studentList) {
		System.out.printf(Formats.STUDENT_HEADER_FORMAT, "ID", "Name", "Age", "Classroom");
		System.out.println("----------------------------------------");
		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < studentList.size(); j++) {
				if (students.get(i).getId().equalsIgnoreCase(studentList.get(j))) {
					Student student = students.get(i);
					System.out.printf(Formats.STUDENT_LIST_FORMAT, student.getId(), student.getFullName(), student.getAge(), student.getAssignedClass());
				}
			}

		}
	}
	public static void listClassrooms() {
		System.out.printf(Formats.CLASSROOM_HEADER_FORMAT, "ID", "Grade");
		System.out.println("---------");
		for(int i = 0; i < classrooms.size(); i++) {
		    Classroom value = classrooms.get(i);
			System.out.printf(Formats.CLASSROOM_LIST_FORMAT, value.getId(), value.getGrade());
		}
	}
	public static void listTeachers() {
		System.out.printf(Formats.TEACHER_HEADER_FORMAT, "ID", "Teacher Name", "Current Class");
		System.out.println("-----------------------------------------");
		for (int i = 0; i < teachers.size(); i++) {
			Teacher t = teachers.get(i);
			System.out.printf(Formats.TEACHER_LIST_FORMAT, t.getId(), t.getFullName(), t.getAssignedClass() != null ? t.getAssignedClass() : "");

		}
	}
	public static void updateStudentName(String id, String newName) {
		boolean studentExist = false;
		for (int i = 0; i < students.size(); i++) {
			if (id.equalsIgnoreCase(students.get(i).getId())) {
				students.get(i).setFullName(newName);
				studentExist = true;
			}
		}
		if (!studentExist) {
			System.out.println("No student associated with the ID");
		}
	}
	public static void searchStudent(String keyword) {
		ArrayList<String> matchingStudents = new ArrayList<String>();
		for (int i = 0; i < students.size(); i++) {
		    Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher( students.get(i).getFullName());
		    Matcher matcher2 = pattern.matcher(students.get(i).getId());
		    while (matcher.find() || matcher2.find()) {
			    System.out.println(students.get(i).getFullName());
		        matchingStudents.add(students.get(i).getId());
		    }
		}
		listStudents(matchingStudents);
	}

	public static void persist() {
		XOMElementConverter<SchoolObject> converter = new SchoolXOMElementConverter();
		for(int i = 0; i < students.size(); i++) {
		    converter.convert(students.get(i));
		}
		for(int i = 0; i < classrooms.size(); i++) {
		    Classroom value = classrooms.get(i);
		    converter.convert(value);
		}
		for (int i = 0; i < teachers.size(); i++) {
			converter.convert(teachers.get(i));
		}
		
		XOMSchoolOutput applicationXMLOutput = new XOMSchoolOutput(converter);
		try {
			applicationXMLOutput.writeToXml(new BufferedOutputStream(new FileOutputStream(new File("schoolManagementSystem.xml"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void init() {
		XOMSchoolInput.readXml();
	}
}
