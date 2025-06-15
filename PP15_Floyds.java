package daa;

import java.util.Scanner;

public class PP15_Floyds {

    void floyd(int[][] a, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != 999 && a[k][j] != 999 && a[i][k] + a[k][j] < a[i][j]) {
                        a[i][j] = a[i][k] + a[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices: ");
        int n = sc.nextInt();
        int[][] a = new int[n][n];

        System.out.println("Enter the weighted matrix (enter 999 for no edge): ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
//				if (i != j && dist[i][j] == 999) {
//					dist[i][j] = Integer.MAX_VALUE;
//				}
            }
        }

        new PP15_Floyds().floyd(a, n);

        System.out.println("The Shortest path matrix is: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 999) {
                    System.out.print("INF ");
                } else {
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
