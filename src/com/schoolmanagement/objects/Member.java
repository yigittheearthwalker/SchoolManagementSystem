package com.schoolmanagement.objects;

public abstract class Member extends SchoolObject{
	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
