package DIS08;

import java.util.Arrays;

public class MyBellmanFord {
    private static Edge[] edge;
    private static MyBellmanFord graph;
    private static int[] dist;
    private final int V;
    private final int E;

    public static void main(String[] args) {
        //TODO
        //angiv antal knuder og kanter
        //lave en ny instans af MyBellmanFord graph
        //fyld array’et edge med kanterne

        int v = 5; //samlet antal knuder
        int e = 7; //samlet antal edges
        MyBellmanFord myBellmanFord = new MyBellmanFord(v, e*2);

        edge[0] = new Edge(0, 1, 1);
        edge[1] = new Edge(1, 0, 1);

        edge[2] = new Edge(0, 2, 2);
        edge[3] = new Edge(2, 0, 2);

        edge[4] = new Edge(1, 2, 4);
        edge[5] = new Edge(2, 1, 4);

        edge[6] = new Edge(1, 3, 2);
        edge[7] = new Edge(3, 1, 2);

        edge[8] = new Edge(2, 3, 5);
        edge[9] = new Edge(3, 2, 5);

        edge[10] = new Edge(1, 4, 3);
        edge[11] = new Edge(4, 1, 3);

        edge[12] = new Edge(3, 4, 10);
        edge[13] = new Edge(4, 3, 10);

        bellmanFordAlgo(myBellmanFord, 4);
    }
    public MyBellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
    }

    public static void bellmanFordAlgo(MyBellmanFord graph, int source) {
        int V = graph.V, E = graph.E;
        int[] dist = new int[V];
        // Step 1: Initialize distances from source to all other vertices as INFINITE
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        // Step 2: Relax all edges |V| - 1 times.
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edge[j].source;
                int v = edge[j].destination;
                int weight = edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
        // Print distances from source to all vertices
        printDistances(dist, V);
    }
    // Print distances from source to all vertices
    public static void printDistances(int[] dist, int V) {
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }
}