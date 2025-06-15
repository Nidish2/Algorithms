package daa;

import java.util.Scanner;

public class PP19_TravellingSalesman {

    public static int tsp(int[][] a, int[] tour, int s, int n) {
        int[] minTour = new int[n + 1], curTour = new int[n + 1];
        int minCost = Integer.MAX_VALUE, cCost;

        if (s == n) {
            return a[tour[n]][tour[1]];
        }

        for (int i = s + 1; i <= n; i++) {
            System.arraycopy(tour, 1, curTour, 1, n);
            curTour[s + 1] = tour[i];
            curTour[i] = tour[s + 1];

            cCost = tsp(a, curTour, s + 1, n) + a[tour[s]][tour[i]];

            if (cCost < minCost) {
                minCost = cCost;
                System.arraycopy(curTour, 1, minTour, 1, n);
            }
        }

        System.arraycopy(minTour, 1, tour, 1, n);
        return minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        if (n < 2) {
            System.out.println("Path is not possible.");
            sc.close();
            return;
        }

        int[][] a = new int[n + 1][n + 1];
        int[] tour = new int[n + 1];
        System.out.println("Enter the cost matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = sc.nextInt();
            }
            tour[i] = i;// we can use different loop to initialize it but this reduces the lines of code
        }

        sc.close();

        int minCost = tsp(a, tour, 1, n);
        System.out.print("\nThe optimal Tour is: ");
        for (int i = 1; i <= n; i++) {
            System.out.print(tour[i] + " -> ");
        }
        System.out.println("1");
        System.out.println("\nMinimum Cost: " + minCost);
    }
}
