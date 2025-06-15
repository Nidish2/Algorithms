package daa;

class P6_basic {
	int factorial(int n) {
		if (n == 1)
			return 1;
		return n * factorial(n - 1);
	}

	void fibanacci(int a, int b, int n) {
		if (n <= -1) // it will print last number as well
			return;
		System.out.print(a + "\t");
		fibanacci(b, a + b, n - 1);
	}
}

class B_student {
	String name;
	int usn;

	public B_student(String name, int usn) {
		super();
		this.name = name;
		this.usn = usn;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	int getUsn() {
		return usn;
	}

	void setUsn(int usn) {
		this.usn = usn;
	}
}

public class P4_Basic {
	int sumNNo(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++)
			sum += i;
		return sum;
	}

	public static void main(String[] args) {
//		P4_Basic b = new P4_Basic();
//		System.out.println(b.sumNNo(95));
//		B_student B = new B_student("Nidish", 95);
//		System.out.println(B.getName() + " - " + B.getUsn());
//		B.setName("DAA");
//		B.setUsn(4);
//		System.out.println(B.getName() + " - " + B.getUsn());

		P6_basic p = new P6_basic();
		System.out.println(p.factorial(5));
		p.fibanacci(0, 1, 10);
	}
}
