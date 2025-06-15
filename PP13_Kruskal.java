package daa;

import java.util.Scanner;

public class PP13_Kruskal {

    int[] parent = new int[10];

    int find(int m) {
        while (parent[m] != 0) {
            m = parent[m];
        }
        return m;
    }

    void union(int i, int j) {
        parent[Math.min(i, j)] = Math.min(i, j);
    }

    void kruskal(int[][] a, int n) {
        int sum = 0, edges = 0;

        while (edges < n - 1) {
            int min = 99, u = -1, v = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] < min && i != j) {
                        min = a[i][j];
                        u = i;
                        v = j;
                    }
                }
            }

            int i = find(u);
            int j = find(v);

            if (i != j) {
                union(i, j);
                System.out.println("(" + u + "," + v + ") = " + a[u][v]);
                sum += a[u][v];
                edges++;
            }
            a[u][v] = a[v][u] = 99;
        }
        System.out.println("The cost of the minimum spanning tree is: " + sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices of the graph: ");
        int n = sc.nextInt();

        int[][] a = new int[n][n];

        System.out.println("Enter the weighted matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
//				if (i != j && a[i][j] == 0) {
//					a[i][j] = 99; // Assuming 99 represents no edge
//				}
            }
        }

        PP13_Kruskal k = new PP13_Kruskal();
        k.kruskal(a, n);

        sc.close();
    }
}
