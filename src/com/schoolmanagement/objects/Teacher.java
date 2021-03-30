package com.schoolmanagement.objects;

import com.schoolmanagement.containers.School;

public class Teacher extends Member{
	
	private String assignedClass;
	private int assignedGrade;
	
	public Teacher() {
		generateId();
		setSchoolObjectType("Teacher");
		School.addSchoolObjectByType(this);
	}
	public Teacher(String id) {
		++teacherCounter;
		setId(id);
		setSchoolObjectType("Teacher");
		School.addSchoolObjectByType(this);
	}

	@Override
	public void generateId() {
		String id = "T"+(++teacherCounter);
		setId(id);
	}

	public String getAssignedClass() {
		return assignedClass;
	}

	public void setAssignedClass(String assignedClass) {
		Classroom classroom = School.getClassroom(assignedClass);
		if (classroom != null) {
			classroom.setAssignedTeacherId(this.getId());
			this.assignedClass = classroom.getId();
			this.assignedGrade = classroom.getGrade();
		}else {
			System.out.println("Cannot find Classroom associated with the ID");
		}
		
		
	}

}
