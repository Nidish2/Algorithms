package daa;

import java.util.Scanner;

public class P0_Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the TOTAL MONEY: ");
        int c = sc.nextInt();
        int[] demo = {2000, 500, 100, 50, 20, 10, 5, 2, 1}; // Sorted in descending order

        System.out.println("*Change*");
        for (int d : demo) {
            int k = c / d;
            c %= d;
            if (k > 0) {
                System.out.println(d + " Rs - " + k);
            }
        }
        sc.close();
    }
}
