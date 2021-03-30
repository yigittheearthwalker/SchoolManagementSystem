package com.schoolmanagement.objects;

import com.schoolmanagement.containers.School;
import com.schoolmanagement.interfaces.ClassroomAssignmentImplementation;

public class Student extends Member implements ClassroomAssignmentImplementation{
	
	private int age;
	private int grade;
	private String assignedClass;
	
	public Student() {
		generateId();
		setSchoolObjectType("Student");
		School.addSchoolObjectByType(this);
	}
	
	public Student(String id) {
		++studentCounter;
		setId(id);
		setSchoolObjectType("Student");
		School.addSchoolObjectByType(this);
	}

	@Override
	public void generateId() {
		String id = "S"+(++studentCounter);
		setId(id);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age == 0) {
			this.grade = age - 6;
			assignToClassroom(this.grade);
		}
		this.age = age;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public void assignToClassroom(int grade) {
		 Classroom classroom = School.getClassroomForStudentAssignment(grade);
		 if (classroom == null) {
			classroom = new Classroom(grade);
		}
		 classroom.addStudentToClassroom(this.getId());
		 this.setAssignedClass(classroom.getId());		
	}

	public String getAssignedClass() {
		return assignedClass;
	}

	public void setAssignedClass(String assignedClass) {
		this.assignedClass = assignedClass;
	}




}
