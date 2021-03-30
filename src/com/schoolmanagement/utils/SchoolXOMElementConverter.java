package com.schoolmanagement.utils;

import com.schoolmanagement.objects.Classroom;
import com.schoolmanagement.objects.SchoolObject;
import com.schoolmanagement.objects.Student;
import com.schoolmanagement.objects.Teacher;

import nu.xom.Element;

public class SchoolXOMElementConverter extends XOMElementConverter<SchoolObject>{

	@Override
	public void convert(SchoolObject schoolObject) {
		if (schoolObject.getSchoolObjectType().equalsIgnoreCase("Student")) {
			Student student =(Student) schoolObject;
			Element studentElement = new Element("Student");
			studentElement.appendChild(getStringElement("ID", student.getId()));
			studentElement.appendChild(getStringElement("Name", student.getFullName()));
			studentElement.appendChild(getStringElement("Age", String.valueOf(student.getAge())));
			studentElement.appendChild(getStringElement("AssignedClassroom", student.getAssignedClass()));
			elements1.add(studentElement);
		}else if(schoolObject.getSchoolObjectType().equalsIgnoreCase("Classroom")) {
			Classroom classroom = (Classroom) schoolObject;
			Element classroomElement = new Element("Classrooms");
			classroomElement.appendChild(getStringElement("ID", classroom.getId()));
			classroomElement.appendChild(getStringElement("Grade", String.valueOf(classroom.getGrade())));
			Element studentList = new Element("StudentList");
			for (int i = 0; i < classroom.studentIdList.size(); i++) {
				studentList.appendChild(getStringElement("ID", classroom.studentIdList.get(i)));
			}
			classroomElement.appendChild(studentList);
			elements2.add(classroomElement);
		}else if(schoolObject.getSchoolObjectType().equalsIgnoreCase("Teacher")) {
			Teacher teacher = (Teacher) schoolObject;
			Element teacherElement = new Element("Teachers");
			teacherElement.appendChild(getStringElement("ID", teacher.getId()));
			teacherElement.appendChild(getStringElement("Name", teacher.getFullName()));
			teacherElement.appendChild(getStringElement("AssignedClassroom", teacher.getAssignedClass()));
			elements3.add(teacherElement);
		}
		
	}
	
	private Element getStringElement(String elementName, String value) {
		Element element = new Element(elementName);
		element.appendChild(value); 
		return element;
	}

	@Override
	public Element getXML() {
		Element root = new Element("School");
		Element studentsElement = new Element("Students");
		Element classroomsElement = new Element("Classrooms");
		Element teachersElement = new Element("Teachers");
		for(Element element : elements1) {
			studentsElement.appendChild(element);
		}
		for(Element element : elements2) {
			classroomsElement.appendChild(element);
		}
		for(Element element : elements3) {
			teachersElement.appendChild(element);
		}
		root.appendChild(studentsElement);
		root.appendChild(classroomsElement);
		root.appendChild(teachersElement);
		return root;
	}


}
