package daa;

import java.util.Scanner;

public class PP23_Hamiltonian {
    static int[] x; // Path array

    private static void nextVertex(int[][] G, int n, int k) {
        int j;
        while (true) {
            x[k] = (x[k] + 1) % (n + 1);
            if (x[k] == 0)
                return;
            if (G[x[k - 1]][x[k]] != 0) {
                for (j = 1; j <= k; j++) {
                    if (x[j] == x[k])
                        break;
                }
                if (j == k) {
                    if (k < n || (k == n && G[x[n]][x[1]] != 0))
                        return;
                }
            }
        }
    }

    public static void H_Cycle(int[][] G, int n, int k) {
        int i;
        while (true) {
            nextVertex(G, n, k);
            if (x[k] == 0)
                return;
            if (k == n) {
                System.out.println("\nHamiltonian Cycle found:");
                for (i = 1; i <= n; i++) {
                    System.out.print(x[i] + " ");
                }
                System.out.println(x[1]); // Print the starting vertex to complete the cycle
            } else
                H_Cycle(G, n, k + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices of the graph:");
        int n = sc.nextInt();

        int[][] G = new int[n + 1][n + 1]; // Adjusting to handle vertex indexing from 1
        x = new int[n + 1]; // Adjusting size based on input vertices

        System.out.println("Enter the adjacency matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                G[i][j] = sc.nextInt();
            }
            x[i] = 0;
        }

        x[1] = 1; // Start with the first vertex

        System.out.println("\nHamiltonian Cycles are:");
        H_Cycle(G, n, 2);

        sc.close();
    }
}
