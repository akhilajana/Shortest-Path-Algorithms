package com.proj.algos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MinimumSpanningTree {

	int numOfNodes, numOfEdges;
	Edge[] edges;

	MinimumSpanningTree(int v, int e) {
		numOfNodes = v;
		numOfEdges = e;
		edges = new Edge[numOfEdges];
		for (int i = 0; i < e; ++i)
			edges[i] = new Edge();
	}

	public static void performMST(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner scr = new Scanner(file);
		int V = scr.nextInt();
		int E = scr.nextInt();
		MinimumSpanningTree tree = new MinimumSpanningTree(V, E);
		scr.next().charAt(0);
		for (int i = 0; i < E; i++) {
			int source = (int) scr.next().charAt(0) - 65;
			int dest = (int) scr.next().charAt(0) - 65;
			int weight = scr.nextInt();
			tree.edges[i].src = source;
			tree.edges[i].dest = dest;
			tree.edges[i].weight = weight;
		}
		tree.Kruskal();
	}

	class Edge implements Comparable<Edge> {
		int src, dest, weight;

		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	}

	class Tree {
		int parent, rank;
	}

	int find(Tree subsets[], int i) {
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);
		return subsets[i].parent;
	}

	void combine(Tree subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	void Kruskal() {
		Edge result[] = new Edge[numOfNodes];
		int e = 0;
		int i = 0;
		for (i = 0; i < numOfNodes; ++i)
			result[i] = new Edge();
		Arrays.sort(edges);
		Tree subsets[] = new Tree[numOfNodes];
		for (i = 0; i < numOfNodes; ++i)
			subsets[i] = new Tree();
		for (int v = 0; v < numOfNodes; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}
		i = 0;
		while (e < numOfNodes - 1) {
			Edge next_edge = new Edge();
			next_edge = edges[i++];
			int x = find(subsets, next_edge.src);
			int y = find(subsets, next_edge.dest);
			if (x != y) {
				result[e++] = next_edge;
				combine(subsets, x, y);
			}
		}
		System.out.print("Number of nodes: " + numOfNodes + "\n" + "Number of edges: " + numOfEdges + "\n");
		System.out.println("\nThe Minimum Spanning Tree contains following: \n");
		for (i = 0; i < e; ++i)
			System.out.println("\t" + (char) (result[i].src + 65) + " --> " + (char) (result[i].dest + 65)
					+ " \t (weight)" + result[i].weight);
	}

}
