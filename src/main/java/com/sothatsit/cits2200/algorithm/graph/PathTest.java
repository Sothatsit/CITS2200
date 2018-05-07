package com.sothatsit.cits2200.algorithm.graph;

import CITS2200.*;

public class PathTest{

    public static void main(String[] args) {
        run(new PathImp(), new String[] {
                "F",
                "/Users/159503/Desktop/Workspace/CITS2200/src/main/resources/error.txt",
                "0"
        });
    }

//
//
//  PathTest.java
// This class is used to test and impolementation of the interface Path,
//
//  Created by tim on 14/03/06.
//  All rights reserved.
//

    /**
     *This runs the Prim algorithm algorithms. The algorithm can either be
     *run over a random graph, or a graph specified from a file.
     *To specify a graph from a file use
     *usage: java PathTest F [filename] [startVertex]
     *where filename is the name of the file and startVertex
     *is the index of the vertex on which to start the .
     *To use a random graph, use
     * java PathTest R [numberOfVertices] [density] [maxweight]
     *where number of vertices is an int specifying
     *the number of vertices, density is a double between
     *0 and 1 specifying the the density of the graph and
     * maxweight is an integer representing the maximum possible edge weight. The random
     *graphs will always start at vertex 0.
     **/
    public static void run(Path alg, String[] args){
        Graph g;
        if(args.length == 3 && args[0].equals("F")){
            try{
                g =	Graph.readFile(args[1], true, false);
                output(alg, g,  Integer.parseInt(args[2]));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(args.length == 4 && args[0].equals("R")){
            g = Graph.randomWeightedGraph(Integer.parseInt(args[1]), false, Double.parseDouble(args[2]), Integer.parseInt(args[3]));
            output(alg, g, 0);
        }
        else usage();
    }

    /** Prints results of methods**/
    private static void output(Path alg, Graph g, int v){
        int w = alg.getMinSpanningTree(g);
        System.out.println("Input Graph (adjacency matrix)");
        System.out.println(g.toString());
        System.out.println("total weight: " + w);

        int[] d = alg.getShortestPaths(g,v);
        System.out.println("Shortest paths from "+ v);
        if(d!= null){
            for (int aD : d) System.out.print(aD + "\t");
            System.out.println("");
        }

    }


    public static void usage(){
        System.out.println("usage: java PathTest F [filename] [startVertex]");
        System.out.println("\t or");
        System.out.println("java PathTest R [numberOfVertices] [densisty] [maxWeight]");
        System.out.println("where:\t filename is the name of the file");
        System.out.println("\t\t\t starVertex is the index of the starting vertex (int)");
        System.out.println("\t\t\t numberOfVertices is the number of vertices to be in the random graph (int)");
        System.out.println("\t\t\t density (between 0 and 1) is the density of the random graph (double)");
        System.out.println("\t\t\t maxWeight is the maximum edge weight for the random graph (int)");
    }


}