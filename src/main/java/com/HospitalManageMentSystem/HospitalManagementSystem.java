package com.HospitalManageMentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
	static Scanner sc=new Scanner(System.in);

	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String password="adnan5050";
	private static final String userName="root"; 
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, userName, password);
		Patient p1 = new Patient(con, sc);
		Doctors d1 = new Doctors(con);
		while(true)
		{
			System.out.println("HOSPITAL MANAGEMENT SYSTEM.");
			System.out.println("1.ADD PATIENT");
			System.out.println("2. VIEW PATIENT");
			System.out.println("3. VIEW DOCTORS");
			System.out.println("4. BOOK APOINTMENT");
			System.out.println("5. EXIT");
			System.out.println("ENTER YOUR CHOICE");
			int choice=sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				p1.addPatient();
				System.out.println();
				break;
			case 2:
				p1.viewPatient();
				System.out.println();
				break;
			case 3:
				d1.viewDoctors();
				System.out.println();
				break;
			case 4:
				bookAPoitment(p1, d1, con, sc);
				System.out.println();
				break;
			case 5:
				return;
			default :
			System.out.println("INVALID YOUR CHOICE");
			}
		}
		
	}
	public static void bookAPoitment( Patient p,Doctors d1,Connection con,Scanner sc) throws SQLException
	{
		System.out.print("ENTER PATIENT ID :");
		int p_id=sc.nextInt();
		sc.nextLine();
		System.out.print("ENTER DOCTOR ID :");
		int d_id=sc.nextInt();
		sc.nextLine();
		System.out.print("ENTER APOINTMENT DATE (YYYY-MM-DD) ");
		String apointmentdate=sc.next();
		if(p.getPatientById(p_id) && d1.getDoctorById(d_id))
		{
			if(checkDoctorAvailblity(d_id,apointmentdate,con))
			{
				String apointmentQuery="insert into  appointments(patient_id,doctor_id,appointment_date) values(?,?,?) ";
				PreparedStatement statement = con.prepareStatement(apointmentQuery);
				statement.setInt(1, p_id);
				statement.setInt(2, d_id);
				statement.setString(3, apointmentdate);
				int i = statement.executeUpdate();
				if(i>0)
				{
					System.out.println("APOINTMENT BOOKED..");
				}
				else
				{
					System.out.println("FAILED TO BOOK APOINTMENT.");
				}
			}
			else
			{
				System.out.println("DOCTOR NOT AVILBEL ON THIS DATE..");
			}
		}else
		{
			System.out.println("Either ptient or Doctor does't Exist..");
		}
	}
	public static boolean checkDoctorAvailblity(int d_id, String apointmentdate,Connection con) throws SQLException
	{
		String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date=?";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, d_id);
		st.setString(2, apointmentdate);
		ResultSet set = st.executeQuery();
		if(set.next())
		{
			 int count = set.getInt(1);
			 if(count==0)
			 {
				 System.out.println("");
				 return true;
			 }
			 else
				 return false;
		}
		return false;
		
	}
}
