package daa;

import java.util.Scanner;

public class PP12_Prims {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of vertices: ");
		int n = sc.nextInt();

		int[][] a = new int[n][n];
		int[] sol = new int[n];

		System.out.println("Enter the weighted graph:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
//				if (i != j && w[i][j] == 0) {
//					w[i][j] = 99; // Assuming 99 represents no edge
//				}
			}
		}

		System.out.println("Enter the source vertex: ");
		int s = sc.nextInt();
		sol[s] = 1;

		int sum = 0;
		// edges will be vertices-1 so from 1 to n-1
		for (int k = 1; k < n; k++) {
			int min = 99, u = -1, v = -1;
			for (int i = 0; i < n; i++) {
				if (sol[i] == 1) {
					for (int j = 0; j < n; j++) {
						if (sol[j] == 0 && a[i][j] < min) {
							min = a[i][j];
							u = i;
							v = j;
						}
					}
				}
			}
			if (u == -1 || v == -1) {
				break; // No more edges to process, break out of the loop
			}
			sol[v] = 1;
			sum += min;
			System.out.println(u + " -> " + v + " = " + min);
		}

		boolean MST = true;
		for (int i = 0; i < n; i++) {
			if (sol[i] == 0) {
				MST = false;
				break;
			}
		}

		if (MST) {
			System.out.println("The cost of minimum spanning tree is: " + sum);
		} else {
			System.out.println("No Spanning tree");
		}

		sc.close();
	}
}
