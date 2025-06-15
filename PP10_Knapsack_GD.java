package daa;

import java.util.Scanner;

public class PP10_Knapsack_GD {
    private int n;
    private double c;
    private double[] p;
    private double[] w;

    public PP10_Knapsack_GD(int n, double c, double[] p, double[] w) {
        this.n = n;
        this.c = c;
        this.p = p;
        this.w = w;
    }

    void compute() {
        double[] x = new double[n];
        double rc = c;

        // Compute the knapsack solution
        for (int i = 0; i < n; i++) {
            if (w[i] <= rc) {
                x[i] = 1;
                rc -= w[i];
            } else {
                x[i] = rc / w[i];
                break;
            }
        }

        double netProfit = 0.0;
        for (int i = 0; i < n; i++) {
            netProfit += x[i] * p[i];
        }

        System.out.println("Net Profit: " + netProfit);
        System.out.print("The objects picked up into knapsack are: ");
        for (double xi : x) {
            System.out.print(xi + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of objects: ");
        int n = sc.nextInt();
        double[] p = new double[n];
        double[] w = new double[n];

        System.out.print("Enter the capacity of Knapsack: ");
        double c = sc.nextDouble();
        System.out.println("Enter profit for each " + n + " objects:");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextDouble();
        }
        System.out.println("Enter weight for each " + n + " objects:");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextDouble();
        }

        PP10_Knapsack_GD k = new PP10_Knapsack_GD(n, c, p, w);
        k.compute();
        sc.close();
    }
}
