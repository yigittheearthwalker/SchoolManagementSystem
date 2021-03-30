package com.schoolmanagement.utils;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

public abstract class XOMElementConverter<T> {

	protected List<Element> elements1 = new ArrayList<Element>();
	protected List<Element> elements2 = new ArrayList<Element>();
	protected List<Element> elements3 = new ArrayList<Element>();
		
		public abstract void convert(T item);
		
		
		public abstract <E> E getXML();
}
