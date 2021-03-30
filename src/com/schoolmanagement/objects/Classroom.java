package com.schoolmanagement.objects;

import java.util.ArrayList;

import com.schoolmanagement.containers.School;

public class Classroom extends SchoolObject{
	
	static ArrayList<String> givenIds = new ArrayList<>();
	private int grade;
	public ArrayList<String> studentIdList = new ArrayList<>();
	private String assignedTeacherId = null;
	
	public Classroom(int grade) {
		++classroomCounter;
		this.grade = grade;
		setSchoolObjectType("Classroom");
		generateId();
		School.addSchoolObjectByType(this);
	}
	public void addStudentToClassroom(String studentId) {
		studentIdList.add(studentId);
	}

	@Override
	public void generateId() {
		StringBuilder id = null;
		int letter = 65;
		do {
			id = new StringBuilder();
			id.append(this.grade);
			id.append((char)letter++);
		} while (givenIds.contains(new String(id)));
		givenIds.add(new String(id));
		setId(new String(id));
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getAssignedTeacherId() {
		return assignedTeacherId;
	}
	public void setAssignedTeacherId(String assignedTeacherId) {
		this.assignedTeacherId = assignedTeacherId;
	}

}
