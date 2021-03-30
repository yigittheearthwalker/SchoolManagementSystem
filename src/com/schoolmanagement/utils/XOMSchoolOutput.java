package com.schoolmanagement.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.schoolmanagement.interfaces.ApplicationOutput;
import com.schoolmanagement.objects.Classroom;
import com.schoolmanagement.objects.Student;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class XOMSchoolOutput implements ApplicationOutput{
	
	public XOMSchoolOutput(XOMElementConverter<?> converter) {
		this.converter = converter;
	}
	
	private XOMElementConverter<?> converter;

	@Override
	public void writeToXml(OutputStream outputStream) {
		Document document = new Document(converter.<Element>getXML());
		Serializer serializer = new Serializer(outputStream);
		serializer.setIndent(5);
		try {
			serializer.write(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	
	
}
