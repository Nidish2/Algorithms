package daa;

import java.util.*;

class Job {
	char id; // Job ID
	int deadline; // Job deadline
	int profit; // Job profit
}

public class PP11_JobSequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of jobs: ");
		int n = sc.nextInt();

		Job[] jobs = new Job[n];
		for (int i = 0; i < n; i++) {
			jobs[i] = new Job();
			System.out.println("Enter job ID, profit, and deadline (e.g., a 20 2): ");
			jobs[i].id = sc.next().charAt(0);
			jobs[i].profit = sc.nextInt();
			jobs[i].deadline = sc.nextInt();
		}
		sc.close();

		Arrays.sort(jobs, (a, b) -> b.profit - a.profit); // Sort jobs by profit in descending order

		int[] result = new int[n];
		boolean[] slot = new boolean[n];
		int totalProfit = 0;

		for (int i = 0; i < n; i++) {
			for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
				if (!slot[j]) {
					result[j] = i;
					slot[j] = true;
					totalProfit += jobs[i].profit;
					break;
				}
			}
		}

		System.out.print("Maximum profit sequence of jobs: ");
		for (int i = 0; i < n; i++) {
			if (slot[i]) System.out.print(jobs[result[i]].id + " ");
		}
		System.out.println("\nProfit from the sequence of jobs: " + totalProfit);
	}
}
