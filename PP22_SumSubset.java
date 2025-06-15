package daa;

import java.util.Scanner;

public class PP22_SumSubset {
    static int c = 0;

    // cs=Current Sum, rs= Remaining Sum, x=Selected, d=target
    static void findSubsets(int cs, int ind, int rs, int[] x, int[] w, int n, int d) {
        if (cs == d) {
            c++;
            System.out.println("Solution " + c + " is ");
            for (int i = 0; i < ind; i++) {
                if (x[i] == 1) {
                    System.out.print(w[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        if (ind >= n) {
            return;
        }

        if (cs + w[ind] <= d) {
            x[ind] = 1;
            findSubsets(cs + w[ind], ind + 1, rs - w[ind], x, w, n, d);
        }

        if (cs + rs - w[ind] >= d) {
            x[ind] = 0;
            findSubsets(cs, ind + 1, rs - w[ind], x, w, n, d);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] w = new int[n];
        int sum = 0;
        System.out.println("Enter the elements in increasing order: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            sum += w[i];
        }

        System.out.print("Enter the value of d: ");
        int d = sc.nextInt();
        sc.close();

        System.out.println("SUM = " + sum);

        if (sum < d || w[0] > d) {
            System.out.println("Subset is not possible!");
            return;
        }

        int[] x = new int[n];
        findSubsets(0, 0, sum, x, w, n, d);

        if (c == 0) {
            System.out.println("Subset is not possible!");
        }
    }
}