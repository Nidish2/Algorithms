package daa;

import java.util.Scanner;

class student {
	String name;
	int age;
	String address;

	public student() {
		super();
		this.name = "Unknown";
		this.age = 0;
		this.address = "Not available";
	}

	public void setInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setInfo(String name, int age, String address) {
		setInfo(name, age);
		this.address = address;
	}

	public void printDetails(student s[]) {
		System.out.println("The details of Students are: ");
		for (student st : s) {
			if (st != null) {	//Null pointer exception
				System.out.println("Name = " + st.name + "\tAge = " + st.age + "\tAddress = " + st.address);
			}
		}
	}
}

public class P2_Students {
	public static void main(String[] args) {
		student s[] = new student[10];
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			System.out.println("Enter the details of Student " + (i + 1));
			System.out.print("Name = ");
			String name = sc.nextLine();
			System.out.print("Age = ");
			int age = sc.nextInt();
			sc.nextLine(); // Consume the newline character
			System.out.print("Address = ");
			String address = sc.nextLine();
			s[i] = new student();
			s[i].setInfo(name, age, address);
		}

		s[0].printDetails(s);
		sc.close();
	}
}