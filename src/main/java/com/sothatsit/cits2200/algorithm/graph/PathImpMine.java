package com.sothatsit.cits2200.algorithm.graph;

import CITS2200.Graph;
import CITS2200.Path;
import com.sothatsit.cits2200.data.queue.RandomAccessMinHeap;

/**
 * Implementations of path-finding algorithms over a graph.
 *
 * @author Paddy Lamont
 */
public class PathImpMine implements Path {

    /**
     * Find the minimum spanning tree of {@param graph}, returning
     * the weight of the found tree, or -1 if no tree could be found.
     *
     * @param graph the graph to find the minimum spanning tree in
     *
     * @return the weight of the minimum spanning tree, or -1 if no minimum spanning tree can be found
     */
    @Override
    public int getMinSpanningTree(Graph graph) {
        if(graph.getNumberOfVertices() == 0)
            return -1;

        // Used to iterate the edges each vertex is connected to
        int[][] matrix = graph.getEdgeMatrix();

        // Intermediate variables to calculate the result
        boolean[] processed = new boolean[graph.getNumberOfVertices()];
        int[] weights = new int[graph.getNumberOfVertices()];
        int totalWeight = 0;

        // A heap to store the next vertices to be processed
        RandomAccessMinHeap heap = new RandomAccessMinHeap(graph.getNumberOfVertices());

        // Initialise the heap with all the vertices, and all weights to -1
        for(int vertex = 0; vertex < graph.getNumberOfVertices(); ++vertex) {
            heap.enqueue(vertex, Integer.MAX_VALUE);
            weights[vertex] = -1;
        }

        // Relax the 0th node for it to be processed
        heap.relax(0, 0);
        weights[0] = 0;

        // Loop until the minimum spanning tree is found
        while(!heap.isEmpty()) {
            int vertex = heap.dequeue();

            // We have hit a vertex that was not reached from vertex 0
            if(weights[vertex] == -1)
                return -1;

            // Mark that we have added this vertex to the minimum spanning tree
            processed[vertex] = true;
            totalWeight += weights[vertex];

            // Loop through all the edges from vertex
            int[] edges = matrix[vertex];
            for(int toVertex = 0; toVertex < edges.length; ++toVertex) {
                int weight = edges[toVertex];

                // Skip edges to self, edges that do not exist or edges to already processed vertices
                if(vertex == toVertex || weight == 0 || processed[toVertex])
                    continue;

                // Skip edges that don't reduce the weight
                if(weights[toVertex] != -1 && weight > weights[toVertex])
                    continue;

                // Relax the to vertex's weight
                weights[toVertex] = weight;
                heap.relax(toVertex, weight);
            }
        }

        return totalWeight;
    }

    /**
     * Perform Djikstra's algorithm to find the distances of the shortest paths to
     * each vertex in the graph {@param graph} from the root vertex {@param root}.
     *
     * @param graph the graph to find the shortest paths in
     * @param root  the vertex to get the shortest paths from
     *
     * @return an array of distances from the root vertex {@param root}
     */
    @Override
    public int[] getShortestPaths(Graph graph, int root) {
        // Used to iterate the edges each vertex is connected to
        int[][] matrix = graph.getEdgeMatrix();

        // Intermediate variables to calculate the result
        boolean[] processed = new boolean[graph.getNumberOfVertices()];
        int[] distances = new int[graph.getNumberOfVertices()];

        // A heap to store the next vertices to be processed
        RandomAccessMinHeap heap = new RandomAccessMinHeap(graph.getNumberOfVertices());

        // Initialise the heap with all the vertexes
        for(int vertex = 0; vertex < graph.getNumberOfVertices(); ++vertex) {
            heap.enqueue(vertex, Integer.MAX_VALUE);
            distances[vertex] = -1;
        }

        // Relax the root node for it to be processed
        heap.relax(root, 0);
        distances[root] = 0;

        // Loop until all shortest paths are found
        while(!heap.isEmpty()) {
            int vertex = heap.dequeue();
            int distance = distances[vertex];

            // Mark that we have found the shortest path to this vertex
            processed[vertex] = true;

            // Loop through all the edges from vertex
            int[] edges = matrix[vertex];
            for(int toVertex = 0; toVertex < edges.length; ++toVertex) {
                int edgeWeight = edges[toVertex];

                // Skip edges to self, edges that do not exist or edges to already processed vertices
                if(vertex == toVertex || edgeWeight == 0 || processed[toVertex])
                    continue;

                // The distance through the vertex we are processing to this vertex
                int weight = edgeWeight + distance;

                // Skip edges that don't reduce the distances
                if(distances[toVertex] >= 0 && weight > distances[toVertex])
                    continue;

                // Update the distance to the vertex and relax its weight in the heap
                distances[toVertex] = weight;
                heap.relax(toVertex, weight);
            }
        }

        return distances;
    }
}
