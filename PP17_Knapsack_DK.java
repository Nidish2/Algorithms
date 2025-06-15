package daa;

import java.util.Scanner;

public class PP17_Knapsack_DK {
	int n, c, p[], w[], v[][];

	public PP17_Knapsack_DK(int n, int c, int[] p, int[] w) {
		this.n = n;
		this.c = c;
		this.p = p;
		this.w = w;
		this.v = new int[n + 1][c + 1];
	}

	void compute() {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= c; j++) {
				if (i == 0 || j == 0) {
					v[i][j] = 0;
				} else if (j >= w[i - 1]) {
					v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - w[i - 1]] + p[i - 1]);
				} else {
					v[i][j] = v[i - 1][j];
				}
			}
		}
	}

	void traceback() {
		System.out.println("The objects picked up into the knapsack are :");
		for (int i = n, j = c; i > 0; i--) {
			if (v[i][j] != v[i - 1][j]) {
				System.out.println("Item " + i + " (Profit: " + p[i - 1] + ", Weight: " + w[i - 1] + ")");
				j -= w[i - 1];
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of objects: ");
		int n = sc.nextInt();
		System.out.println("Enter the capacity of the knapsack: ");
		int c = sc.nextInt();

		int[] p = new int[n];
		int[] w = new int[n];

		System.out.println("Enter the profit for each of the " + n + " objects:");
		for (int i = 0; i < n; i++) {
			p[i] = sc.nextInt();
		}

		System.out.println("Enter the weight for each of the " + n + " objects:");
		for (int i = 0; i < n; i++) {
			w[i] = sc.nextInt();
		}

		PP17_Knapsack_DK dk = new PP17_Knapsack_DK(n, c, p, w);
		dk.compute();
		dk.traceback();
		sc.close();
	}
}
