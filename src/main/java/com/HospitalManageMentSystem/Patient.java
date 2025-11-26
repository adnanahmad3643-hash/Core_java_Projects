package com.HospitalManageMentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection connectio;
	private Scanner scanner;
	
	public Patient(Connection connectio, Scanner scanner) {
		super();
		this.connectio = connectio;
		this.scanner = scanner;
	}
	
	void addPatient() throws SQLException
	{
		System.out.print("ENTER PATIENT NAME :");
		String name=scanner.nextLine();
		System.out.print("ENTER PATIENT AGE :");
		int age=scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter patient Gender");
		String gender=scanner.nextLine();
		
		String query="insert into patient (name,age,gender) values (?,?,?)";
		PreparedStatement ps=connectio.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, gender);
		int i = ps.executeUpdate();
		if(i>0)System.out.println("PATIENT ADDEDD SUCCESFULLY");
		else System.out.println("FAILED ENTRY...");
	}
	
	void viewPatient() throws SQLException
	{
		String query="select * from patient";
		PreparedStatement statement = connectio.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		
		if(!set.isBeforeFirst())
		{
			System.out.println("YOUR TABLE IS EMPTY...");
		}
		else
		{
			/*
			 * System.out.println("..............+............+.......+.............");
			 * System.out.println("| patient id  | name       | age   | Gender      |");
			 * System.out.println("..............+............+.......+.............");
			 * while(set.next()) { System.out.println("|%-12s"
			 * +set.getInt(1)+" "+set.getString(2)+" "+set.getInt(3)+" "+set.getString(4));
			 * }
			 */
			System.out.println("--------------------------------------------------");
			System.out.printf("| %-12s | %-10s | %-5s | %-10s |\n", 
			                  "patient id", "name", "age", "gender");
			System.out.println("--------------------------------------------------");

			while(set.next()) {
			    System.out.printf("| %-12d | %-10s | %-5d | %-10s |\n",
			                      set.getInt(1),
			                      set.getString(2),
			                      set.getInt(3),
			                      set.getString(4));
			}
			System.out.println("--------------------------------------------------");

		}
	}
	
	public boolean getPatientById(int id) throws SQLException
	{
		String query="select * from patient where id=?";
		PreparedStatement statement = connectio.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		if(set.next())
//			System.out.println("|%-12s" +set.getInt(0)+" "+set.getString(1)+" "+set.getInt(3)+" "+set.getString(4));
			return true;
		else
		return false;
		
	}
	

	
	
}
