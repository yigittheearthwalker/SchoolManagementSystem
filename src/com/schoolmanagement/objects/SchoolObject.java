package com.schoolmanagement.objects;

import com.schoolmanagement.interfaces.Identifiable;

public abstract class SchoolObject implements Identifiable{
	public static int studentCounter = 0;
	public static int classroomCounter = 0;
	public static int teacherCounter = 0;
	private String id;
	private String schoolObjectType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolObjectType() {
		return schoolObjectType;
	}

	public void setSchoolObjectType(String schoolObjectType) {
		this.schoolObjectType = schoolObjectType;
	}
}
