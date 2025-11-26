package com.bankManageMent;

import java.util.ArrayList;
import java.util.Scanner;

class Account {
    int accNo;
    String name;
    double balance;

    Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amt) {
        balance += amt;
    }

    void withdraw(double amt) {
        if (amt <= balance)
            balance -= amt;
        else
            System.out.println("Insufficient balance!");
    }

    void display() {
        System.out.println("Account No: " + accNo + ", Name: " + name + ", Balance: " + balance);
    }
}

public class BankManagement {
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Display\n5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                displayAccounts();
                break;
            }
        } while (choice != 5);
    }

    static void createAccount() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Balance: ");
        double bal = sc.nextDouble();
        accounts.add(new Account(accNo, name, bal));
        System.out.println("Account created successfully!");
    }

    static Account findAccount(int accNo) {
        for (Account a : accounts) {
            if (a.accNo == accNo)
                return a;
        }
        return null;
    }

    static void deposit() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account a = findAccount(accNo);
        if (a != null) {
            System.out.print("Enter amount to deposit: ");
            double amt = sc.nextDouble();
            a.deposit(amt);
            System.out.println("Deposit successful!");
        } else
            System.out.println("Account not found!");
    }

    static void withdraw() {
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account a = findAccount(accNo);
        if (a != null) {
            System.out.print("Enter amount to withdraw: ");
            double amt = sc.nextDouble();
            a.withdraw(amt);
        } else
            System.out.println("Account not found!");
    }

    static void displayAccounts() {
        for (Account a : accounts)
            a.display();
    }
}