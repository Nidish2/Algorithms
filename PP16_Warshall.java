package daa;

import java.util.Scanner;

public class PP16_Warshall {

	void warshall(int[][] reach, int n) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (reach[i][k] == 1 && reach[k][j] == 1) {
						reach[i][j] = 1;
					}
//					reach[i][j] = reach[i][j] != 0 || (reach[i][k] != 0 && reach[k][j] != 0) ? 1 : 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of vertices: ");
		int n = sc.nextInt();
		int[][] a = new int[n][n];

		System.out.println("Enter the adjacency matrix (0 for no edge, 1 for edge): ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
			}
		}

		new PP16_Warshall().warshall(a, n);

		System.out.println("The transitive closure of the given graph is: ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		sc.close();
	}
}
