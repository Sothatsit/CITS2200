package com.sothatsit.cits2200.data.graph;

import CITS2200.Graph;
import CITS2200.Search;
import com.sothatsit.cits2200.data.queue.QueueLinked;

public class SearchImp implements Search {

    private static final int WHITE = 0;
    private static final int GREY = 1;
    private static final int BLACK = 2;

    /**
     * Perform a breadth-first search from root vertex {@param root} of the directed, unweighted
     * graph {@param graph}, and produce an array containing a parent of each vertex.
     *
     * @param graph the graph we are searching
     * @param root  the root vertex
     *
     * @return A connected tree from root vertex {@param vertex} in the graph {@param graph}.
     */
    @Override
    public int[] getConnectedTree(Graph graph, int root) {
        int[][] edgeMatrix = graph.getEdgeMatrix();

        int[] colours = new int[graph.getNumberOfVertices()];
        int[] parents = new int[graph.getNumberOfVertices()];

        for(int index = 0; index < graph.getNumberOfVertices(); ++index) {
            colours[index] = WHITE;
            parents[index] = -1;
        }

        QueueLinked queue = new QueueLinked();

        colours[root] = GREY;
        queue.enqueue(root);

        while(!queue.isEmpty()) {
            int vertex = (Integer) queue.dequeue();

            int[] edges = edgeMatrix[vertex];
            for(int toVertex = 0; toVertex < edges.length; ++toVertex) {
                if(edges[toVertex] == 0)
                    continue;

                if(colours[toVertex] == WHITE) {
                    colours[toVertex] = GREY;
                    parents[toVertex] = vertex;
                    queue.enqueue(toVertex);
                }
            }

            colours[vertex] = BLACK;
        }

        return parents;
    }

    /**
     * Perform a breadth-first search from root vertex {@param root} of the directed, unweighted graph {@param graph},
     * and produce an array containing the distances of each vertex from the root vertex.
     *
     * @param graph the graph we are searching
     * @param root  the root vertex
     *
     * @return A connected tree from root vertex {@param vertex} in the graph {@param graph}.
     */
    @Override
    public int[] getDistances(Graph graph, int root) {
        int[][] edgeMatrix = graph.getEdgeMatrix();

        int[] colours = new int[graph.getNumberOfVertices()];
        int[] distances = new int[graph.getNumberOfVertices()];

        for(int index = 0; index < graph.getNumberOfVertices(); ++index) {
            colours[index] = WHITE;
            distances[index] = -1;
        }

        QueueLinked queue = new QueueLinked();

        colours[root] = GREY;
        distances[root] = 0;
        queue.enqueue(root);

        while(!queue.isEmpty()) {
            int vertex = (Integer) queue.dequeue();

            int[] edges = edgeMatrix[vertex];
            for(int toVertex = 0; toVertex < edges.length; ++toVertex) {
                if(edges[toVertex] == 0)
                    continue;

                if(colours[toVertex] == WHITE) {
                    colours[toVertex] = GREY;
                    distances[toVertex] = distances[vertex] + 1;
                    queue.enqueue(toVertex);
                }
            }

            colours[vertex] = BLACK;
        }

        return distances;
    }

    private int[] colours;
    private int[][] times;
    private int time;

    /**
     * Perform a depth-first search from root vertex {@param root} of the directed, unweighted graph {@param graph},
     * and produce an array of the start and end times of each vertex.
     *
     * @param graph the graph to be searched
     * @param root  the root vertex to search the graph from
     *
     * @return a 2-dimensional array, where each sub-array has two elements:
     *         the first is the start time and the second is the end time
     */
    @Override
    public int[][] getTimes(Graph graph, int root) {
        this.colours = new int[graph.getNumberOfVertices()];
        this.times = new int[graph.getNumberOfVertices()][2];
        this.time = 0;

        performGetTimesDFS(graph.getEdgeMatrix(), root);

        return times;
    }

    private void performGetTimesDFS(int[][] edgeMatrix, int vertex) {
        colours[vertex] = GREY;
        times[vertex][0] = time;
        time += 1;

        int[] edges = edgeMatrix[vertex];
        for(int toVertex = 0; toVertex < edges.length; ++toVertex) {
            if(edges[toVertex] == 0)
                continue;

            if(colours[toVertex] == WHITE) {
                performGetTimesDFS(edgeMatrix, toVertex);
            }
        }

        colours[vertex] = BLACK;
        times[vertex][1] = time;
        time += 1;
    }
}
