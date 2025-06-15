package daa;

import java.util.Arrays;
import java.util.Scanner;

public class PP18_BellmanFord {
    class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private int V, E;
    private Edge[] edges;

    public PP18_BellmanFord(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[e];
    }

    // Add an edge to the graph
    void addEdge(int ind, int src, int dest, int weight) {
        edges[ind] = new Edge(src, dest, weight);
    }

    // Bellman-Ford algorithm
    void bellmanFord(int src) {
        int[] D = new int[V];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[src] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;
                if (D[u] != Integer.MAX_VALUE && D[u] + w < D[v]) {
                    D[v] = D[u] + w;
                }
            }
        }
        for (int j = 0; j < E; ++j) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int w = edges[j].weight;
            if (D[u] != Integer.MAX_VALUE && D[u] + w < D[v]) {
                System.out.println("Graph contains negative w cycle");
                return;
            }
        }
        printArr(D);
    }

    // Print the distance array
    void printArr(int[] dist) {
        System.out.println("Vertex distance from source:");
        for (int i = 0; i < V; ++i) {
            System.out.println(
                    "Vertex " + i + " \t Distance from Source: " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter the number of edges:");
        int E = sc.nextInt();

        PP18_BellmanFord G = new PP18_BellmanFord(V, E);

        System.out.println("Enter each edge (src dest weight):");
        for (int i = 0; i < E; ++i) {
            G.addEdge(i, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        System.out.println("Enter the source vertex:");
        G.bellmanFord(sc.nextInt());

        sc.close();
    }
}