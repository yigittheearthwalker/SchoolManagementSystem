package com.schoolmanagement.main;


import com.schoolmanagement.containers.School;
import com.schoolmanagement.utils.Menu;

public class Driver {
	public static void main(String[] args) {
		System.out.println("***Welcome to the School Management System***");
		System.out.println("---------------------------------------------\n\n");
		
		School.init();
		Menu.start();
	}
}
