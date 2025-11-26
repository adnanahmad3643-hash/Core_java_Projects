package StudentManagement;

import java.util.ArrayList;
import java.util.Scanner;

class Student1 {

	int rollNo;
	String name;
	String course;

	public Student1(int rollNo, String name, String course) {

		this.rollNo = rollNo;
		this.name = name;
		this.course = course;
	}

	void display() {
		System.out.println("StudentDetail [rollNo=" + rollNo + ", name=" + name + ", course=" + course + "]");
	}

}

class StudentDetail {
	static ArrayList<Student1> list = new ArrayList<Student1>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;
		do {
			System.out.println("\n1. Add Student\n2. Display All\n0. Exit");
			System.out.println("ENTER YOUR CHOICE : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				sc.nextLine();
				System.out.println("ENTER YOUR ROLL NO..");
				int roll = sc.nextInt();
				sc.nextLine();
				System.out.println("ENTER YOUR NAME");
				String name = sc.nextLine();
				System.out.println("ENTER YOUR COURSE");
				String course = sc.nextLine();

				list.add(new Student1(roll, name, course));
				System.out.println("Student added successfully!");
				break;
			case 2:
				displayAll();
				break;

			}

		} while (choice != 0);

	}

	static void displayAll() {
		for (Student1 s : list)
			s.display();
	}
}
