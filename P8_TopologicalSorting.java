package daa;

import java.util.*;

public class P8_TopologicalSorting {
    public static List<Integer> topologicalSort(List<List<Integer>> adj, int v) {
        int[] inDegree = new int[v];
        adj.forEach(list -> list.forEach(vertex -> inDegree[vertex]++));
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);
            adj.get(node).forEach(adjacent -> {
                inDegree[adjacent]--;
                if (inDegree[adjacent] == 0)
                    q.add(adjacent);
            });
        }
        if (result.size() != v) {
            System.out.println("Graph contains cycle");
            return Collections.emptyList();
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4; // Number of vertices
        List<List<Integer>> edges = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(0, 2),
                Arrays.asList(3, 2));

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(edge.get(1));
        }

        System.out.println("Topological Sorting of the graph\n");
        long start = System.nanoTime();
        List<Integer> result = topologicalSort(adj, n);
        long end = System.nanoTime();
        if (!result.isEmpty()) {
            result.forEach(vertex -> System.out.print(vertex + "\t"));
        }
        System.out.println("\nThe time taken to Topological Sort " + n + " items is " + (end - start) + " ns\n");
    }
}
