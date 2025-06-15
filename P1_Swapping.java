package daa;

public class P1_Swapping {

	void swapUsing3Var(int a, int b) {
		System.out.println("Before Swapping a = " + a + " and b = " + b);
		int t = a;
		a = b;
		b = t;
		System.out.println("After Swapping a = " + a + " and b = " + b + "\n");
	}

	void swapUsing2Var(int a, int b) {
		System.out.println("Before Swapping a = " + a + " and b = " + b);
		a += b;
		b = a - b;
		a -= b;
		System.out.println("After Swapping a = " + a + " and b = " + b + "\n");
	}

	void swapUsingXOR(int a, int b) {
		System.out.println("Before Swapping a = " + a + " and b = " + b);
		a ^= b;
		b = a ^ b;
		a ^= b;
		System.out.println("After Swapping a = " + a + " and b = " + b + "\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		P1_Swapping a = new P1_Swapping();
		a.swapUsing3Var(11, 33);
		a.swapUsing2Var(22, 44);
		a.swapUsingXOR(88, 99);
	}
}