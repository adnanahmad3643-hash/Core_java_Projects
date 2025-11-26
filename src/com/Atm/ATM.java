
package com.Atm;

import java.util.Scanner;

public class ATM {
	private double balence;
	private int pin;
	int countWrongPin;

	ATM(double balence, int pin) {
		this.balence = balence;
		this.pin = pin;
	}

	boolean verifyPin(Scanner sc) {
		

		System.out.println("ENTER PIN : ");
		int newpin = sc.nextInt();

		if (this.pin == newpin) {
			countWrongPin = 0;
			return true;
		} 
		else {
			countWrongPin++;
			System.out.println("INCORRECT PIN! (" + countWrongPin + "/3)");
		}
		if (countWrongPin >= 3) {
			System.out.println("Your account is permanently blocked!!");
			System.out.println("Please try after 24 Hours..!!");
			return false;
		}
		return false;

	}

	void checkBalence(Scanner sc) {
		if (verifyPin(sc)) {
			System.out.println("YOUR CURRENT ACCOUNT BALENCE :" + balence);
		}

	}

	void deposit(Scanner sc) {
		if (verifyPin(sc)) {
			System.out.println("ENTER YOUR DEPOSIT AMOUNT : ");
			double amt = sc.nextDouble();
			this.balence = this.balence + amt;
			System.out.println("your deposit amount " + amt + " \n BALENCE : " + balence);

		}
	}

	void withdrwal(Scanner sc) {
		if (verifyPin(sc)) {
			System.out.println("ENTER YOUR WITHDRWAL AMOUNT : ");
			double amt = sc.nextDouble();
			sc.nextLine();
			if (amt >= balence) {
				System.out.println("INSUFFICIENT BALENCE \n CURRENT BALENCE :" + balence);
			} else {
				System.out.println("WITHDRWAL SUCCESFULL..." + amt);
				this.balence = this.balence - amt;
				System.out.println("YOUR CURRENT BALENCE = " + balence);
			}
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("CREATE A PIN..");
		int pin=sc.nextInt();
		sc.nextLine();
		System.out.println("ENTER AMOUNT...");
		double balence=sc.nextDouble();

	ATM atm = new ATM(balence, pin);
		int choice = 0;

		do {
			
			  if (atm.countWrongPin >= 3) {
//			  System.out.println("Your account is permanently blocked!");
			  return; }
			 
			System.out.println("1 : check balnce ");
			System.out.println("2 : wihdrwal ");
			System.out.println("3 : deposit ");
			System.out.println("0 : exit..");
			System.out.print("ENER YOUR CHOICE :");
			
			
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				atm.checkBalence(sc);
				break;
			case 2:
				atm.withdrwal(sc);
				break;
			case 3:
				atm.deposit(sc);
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("INCORRECT YOUR CHOICE...!!!! ");

			}
		} while (choice != 0);

	}

}
