package InventoryManagement;

import java.util.HashMap;
import java.util.Scanner;

class Operation {

	int id;
	String name;
	int quantity;

	public Operation(int id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	void display() {
		System.out.println("Operation [id=" + id + ", name=" + name + ", quantity=" + quantity + "]");
	}

}

public class CrudOperation {
	static HashMap<Integer, Operation> map = new HashMap<Integer, Operation>();
	static Scanner sc = new Scanner(System.in);

	
	public static void main(String[] args) {
		
		int choice=0;
		do
		{
			System.out.println("1 = INSERT \n 2 = UPDATE \n 3 = DELETE \n 4 = DISPLAY \n 0 = EXIT");
			System.out.println("ENTER CHOICE :");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				insert();
				break;
			case 2: 
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				displayAll();
				break;
			default :
				System.out.println("YOUR CHOICE IS INVALID ");
				
			
			
			}
		}while(choice!=0);
		
		
		
		

	}
	
	static void insert() {
		System.out.println("ENTER ID");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("ENTER NAME :");
		String name = sc.nextLine();
		System.out.println("ENTER QUANTITY");
		int quantity = sc.nextInt();
		map.put(id, new Operation(id, name, quantity));
		System.out.println("ITEM SUCCESFULLY ADDED");

	}

	static void delete() {
		System.out.println("ENTER ID :");
		int id = sc.nextInt();
		if (map.remove(id) != null)
			System.out.println("ITEM DELETED SUCCESFULLY ");
		else
			System.out.println("ITEM NOT FOUND");

	}

	static void update() {

		System.out.println("ENTER ID");
		int id = sc.nextInt();
		sc.nextLine();
		if (map.containsKey(id)) {
			System.out.println("ENTER NAME :");
			String name = sc.nextLine();
			System.out.println("ENTER QUANTITY");
			int quantity = sc.nextInt();
			map.put(id, new Operation(id, name, quantity));
			System.out.println("ITEM SUCCESFULLY UPDATED..");
		} else
			System.out.println("ID NOT FOUND..! ");
	}
	static void displayAll()
	{
		for(Operation o:map.values())
		{
			o.display();
		}
	}


}
