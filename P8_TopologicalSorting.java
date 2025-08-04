package daa;

import java.util.*;

public class P8_TopologicalSorting {

	// Topo Sort using Kahn's Algorithm (BFS + In-Degree)
	public static List<Integer> topoSortKahns(List<List<Integer>> adj, int v) {
		int[] inDegree = new int[v];
		for (List<Integer> list : adj) {
			for (int vertex : list) {
				inDegree[vertex]++;
			}
		}

		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < v; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!q.isEmpty()) {
			int node = q.poll();
			result.add(node);
			for (int neighbor : adj.get(node)) {
				inDegree[neighbor]--;
				if (inDegree[neighbor] == 0) {
					q.offer(neighbor);
				}
			}
		}

		if (result.size() != v) {
			System.out.println("⚠️ Graph contains a cycle (Kahn’s)");
			return Collections.emptyList();
		}
		return result;
	}

	// Topo Sort using DFS (Reverse Postorder with Cycle Detection)
	public static List<Integer> topoSortDFS(List<List<Integer>> adj, int v) {
		boolean[] visited = new boolean[v];
		boolean[] recStack = new boolean[v];
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				if (dfsTopo(i, adj, visited, recStack, stack)) {
					System.out.println("⚠️ Graph contains a cycle (DFS)");
					return Collections.emptyList();
				}
			}
		}

		List<Integer> result = new ArrayList<>();
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private static boolean dfsTopo(int node, List<List<Integer>> adj, boolean[] visited, boolean[] recStack,
			Deque<Integer> stack) {
		visited[node] = true;
		recStack[node] = true;

		for (int neighbor : adj.get(node)) {
			if (!visited[neighbor]) {
				if (dfsTopo(neighbor, adj, visited, recStack, stack)) {
					return true; // Cycle detected
				}
			} else if (recStack[neighbor]) {
				return true; // Cycle detected
			}
		}

		recStack[node] = false;
		stack.push(node);
		return false;
	}

	public static void main(String[] args) {
		// Test your previous DAG (0→1, 0→2, 1→3, 2→3)
		System.out.println("=== Testing Previous DAG ===");
		int n = 4;
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
		}

		// Print adjacency list
		System.out.println("Adjacency List:");
		for (int i = 0; i < n; i++) {
			System.out.println(i + " => " + adj.get(i));
		}

		// Topological Sort with Runtime Averaging
		int iterations = 1000;
		long totalKahns = 0, totalDFS = 0;
		List<Integer> kahnResult = null, dfsResult = null;

		for (int i = 0; i < iterations; i++) {
			long start = System.nanoTime();
			kahnResult = topoSortKahns(adj, n);
			totalKahns += System.nanoTime() - start;
		}
		for (int i = 0; i < iterations; i++) {
			long start = System.nanoTime();
			dfsResult = topoSortDFS(adj, n);
			totalDFS += System.nanoTime() - start;
		}

		System.out.println("\n📘 Topological Sort using Kahn’s Algorithm:");
		System.out.println(kahnResult);
		System.out.println("⏱️ Average Time (Kahn's): " + (totalKahns / iterations) + " ns");
		System.out.println("\n📘 Topological Sort using DFS:");
		System.out.println(dfsResult);
		System.out.println("⏱️ Average Time (DFS): " + (totalDFS / iterations) + " ns");

		// Test original graph (0→1, 1→2, 0→2, 3→2)
		System.out.println("\n=== Testing Original Graph ===");
		n = 4;
		adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		edges = new int[][] { { 0, 1 }, { 1, 2 }, { 0, 2 }, { 3, 2 } };
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
		}

		System.out.println("Adjacency List:");
		for (int i = 0; i < n; i++) {
			System.out.println(i + " => " + adj.get(i));
		}
		System.out.println("\n📘 Topological Sort using Kahn’s Algorithm:");
		kahnResult = topoSortKahns(adj, n);
		System.out.println(kahnResult);
		System.out.println("\n📘 Topological Sort using DFS:");
		dfsResult = topoSortDFS(adj, n);
		System.out.println(dfsResult);

		// Test graph with cycle
		System.out.println("\n=== Testing Graph with Cycle ===");
		n = 4;
		adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		edges = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 1 } }; // Cycle: 1→3→1
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
		}

		System.out.println("Adjacency List:");
		for (int i = 0; i < n; i++) {
			System.out.println(i + " => " + adj.get(i));
		}
		System.out.println("\n📘 Topological Sort using Kahn’s Algorithm:");
		kahnResult = topoSortKahns(adj, n);
		System.out.println(kahnResult);
		System.out.println("\n📘 Topological Sort using DFS:");
		dfsResult = topoSortDFS(adj, n);
		System.out.println(dfsResult);

		// Test larger graph for scalability
		System.out.println("\n=== Testing Larger Graph ===");
		n = 1000;
		adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < n - 1; i++) {
			adj.get(i).add(i + 1);
		}
		long start = System.nanoTime();
		topoSortKahns(adj, n);
		long kahnsTime = System.nanoTime() - start;
		start = System.nanoTime();
		topoSortDFS(adj, n);
		long dfsTime = System.nanoTime() - start;
		System.out.println("📘 Topological Sort Kahn's (Large Graph, V=1000): " + kahnsTime + " ns");
		System.out.println("📘 Topological Sort DFS (Large Graph, V=1000): " + dfsTime + " ns");
	}
}