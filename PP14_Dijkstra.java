package daa;

import java.util.Scanner;

public class PP14_Dijkstra {
	private int[] D;
	private int[][] a;

	public void calc(int n, int s) {
		boolean[] visited = new boolean[n];
		D = new int[n];

		// Initialize distances and visited array
		for (int i = 0; i < n; i++) {
			D[i] = a[s][i];
			visited[i] = false;
		}

		visited[s] = true;

		for (int k = 1; k < n; k++) {
			int minDist = Integer.MAX_VALUE;
			int minPos = -1;

			// Find the vertex with the minimum distance from the source
			for (int i = 0; i < n; i++) {
				if (!visited[i] && D[i] < minDist) {
					minDist = D[i];
					minPos = i;
				}
			}

			if (minPos == -1)
				break; // All reachable vertices are visited

			visited[minPos] = true;

			// Update the distances of the adjacent vertices
			for (int i = 0; i < n; i++) {
				if (!visited[i] && a[minPos][i] != 999 && D[minPos] + a[minPos][i] < D[i]) {
					D[i] = D[minPos] + a[minPos][i];
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Number of Nodes:");
		int n = sc.nextInt();
		PP14_Dijkstra d = new PP14_Dijkstra();
		d.a = new int[n][n];

		System.out.println("Enter the Cost Matrix Weights:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				d.a[i][j] = sc.nextInt();
				if (i != j && d.a[i][j] == 0) {
					d.a[i][j] = 999; // Assuming 999 represents no edge
				}
			}
		}

		System.out.println("Enter the source vertex:");
		int s = sc.nextInt();
		d.calc(n, s);

		sc.close();

		System.out.println("Source: " + s);
		for (int i = 0; i < n; i++) {
			System.out.println("Destination: " + i + " MinCost: " + d.D[i]);
		}
	}
}
