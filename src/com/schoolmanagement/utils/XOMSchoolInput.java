package com.schoolmanagement.utils;

import java.io.File;
import java.io.IOException;

import com.schoolmanagement.objects.Student;
import com.schoolmanagement.objects.Teacher;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class XOMSchoolInput {
	
	public static void readXml() {
		try {
			Document document = new Builder().build(new File("schoolManagementSystem.xml"));
			Element rootElement = document.getRootElement();
			for(Element element : rootElement.getChildElements()) {
				if (element.getLocalName().equalsIgnoreCase("students")) {
					for (Element studentElement : element.getChildElements()) {
						parseStudentElement(studentElement);
					}
				}else if(element.getLocalName().equalsIgnoreCase("teachers")) {
					for (Element teacherElement : element.getChildElements()) {
						parseTeacherElement(teacherElement);
					}
				}
			}
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No School data has been found to initialize!");
		}
	}
	
	public static void parseStudentElement(Element studentElement) {
		String id = null;
		String name = null;
		int age = 0;
		for(Element element : studentElement.getChildElements()) {
			if (element.getLocalName().equalsIgnoreCase("name")) {
				name = element.getValue();
			}else if(element.getLocalName().equalsIgnoreCase("age")) {
				age = Integer.parseInt(element.getValue());
			}else if (element.getLocalName().equalsIgnoreCase("id")) {
				id = element.getValue();
			}
		}
		Student student = new Student(id);
		student.setFullName(name);
		student.setAge(age);
	}
	public static void parseTeacherElement(Element teacherElement) {
		String id = null;
		String name = null;
		String assignedClassroom = null;
		int age = 0;
		for(Element element : teacherElement.getChildElements()) {
			if (element.getLocalName().equalsIgnoreCase("ID")) {
				id = element.getValue();
			}else if(element.getLocalName().equalsIgnoreCase("Name")) {
				name = element.getValue();
			}else if (element.getLocalName().equalsIgnoreCase("AssignedClassroom")) {
				assignedClassroom = element.getValue();
			}
		}
		Teacher teacher = new Teacher(id);
		teacher.setFullName(name);
		teacher.setAssignedClass(assignedClassroom);
	}
}
