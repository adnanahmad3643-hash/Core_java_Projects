package com.HospitalManageMentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

	private Connection connectio;

	
	public Doctors(Connection connectio) {
		super();
		this.connectio = connectio;
		
	}
	
	
	void viewDoctors() throws SQLException
	{
		String query="select * from doctors";
		PreparedStatement statement = connectio.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		
		if(!set.isBeforeFirst())
		{
			System.out.println("YOUR TABLE IS EMPTY...");
		}
		else
		{
			System.out.println("Doctor :");
			/*
			 * System.out.println("..............+...........+..............");
			 * System.out.println("| DOctor id  | name       |Specialization|");
			 * System.out.println("..............+............+.......+.............");
			 * while(set.next()) { System.out.println("|%-12s"
			 * +set.getInt(0)+" "+set.getString(1)+" "+set.getString(2)); }
			 */
			System.out.println("--------------------------------------------------------------");
			System.out.printf("| %-12s | %-12s | %-15s |\n", 
			                  "Doctor ID", "Name", "Specialization");
			System.out.println("--------------------------------------------------------------");

			while (set.next()) {
			    System.out.printf("| %-12d | %-12s | %-15s |\n",
			                      set.getInt(1),      // doctor_id
			                      set.getString(2),   // name
			                      set.getString(3));  // specialization
			}
			System.out.println("--------------------------------------------------------------");

		}
	}
	
	public boolean getDoctorById(int id) throws SQLException
	{
		String query="select * from doctors where id=?";
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
