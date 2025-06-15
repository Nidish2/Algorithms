package daa;

import java.util.*;

class Node {
    int pathCost, cost, workerId, jobId;
    boolean[] assigned;
    Node parent;

    Node(int workerId, int jobId, boolean[] assigned, Node parent, int pathCost) {
        this.workerId = workerId;
        this.jobId = jobId;
        this.assigned = assigned.clone();
        if (jobId != -1) {
            this.assigned[jobId] = true;
        }
        this.parent = parent;
        this.pathCost = pathCost;
    }
}

public class PP21_Assignment {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices (N): ");
        N = sc.nextInt();
        int[][] costMatrix = new int[N][N];

        System.out.println("Enter the Cost Matrix: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costMatrix[i][j] = sc.nextInt();
            }
        }

        sc.close();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        boolean[] assigned = new boolean[N];
        Node root = new Node(-1, -1, assigned, null, 0);
        root.cost = calculateCost(costMatrix, 0, root.assigned);

        pq.add(root);

        Node min = null;
        while (!pq.isEmpty()) {
            min = pq.poll();
            int i = min.workerId + 1;

            if (i == N)
                break;

            for (int j = 0; j < N; j++) {
                if (!min.assigned[j]) {
                    Node child = new Node(i, j, min.assigned, min, min.pathCost + costMatrix[i][j]);
                    child.cost = child.pathCost + calculateCost(costMatrix, i + 1, child.assigned);
                    pq.add(child);
                }
            }
        }

        printAssignments(min);
        System.out.println("\nTotal cost is " + min.pathCost);
    }

    static int calculateCost(int[][] costMatrix, int x, boolean[] assigned) {
        int cost = 0;
        for (int i = x; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!assigned[j] && costMatrix[i][j] < min) {
                    min = costMatrix[i][j];
                }
            }
            cost += min;
        }
        return cost;
    }

    static void printAssignments(Node min) {
        if (min.parent == null)
            return;
        printAssignments(min.parent);
        if (min.jobId != -1) {
            System.out.println("Assign worker " + (min.workerId + 1) + " to job " + (min.jobId + 1));
        }
    }
}
