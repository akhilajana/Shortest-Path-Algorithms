package com.proj.algos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShortestPathAlgorithm {
	int adj[][];
	int sourceVertex, numOfNodes, numOfEdges;
	int[] weight, path;
	boolean directed = false;

	public void readFile(String filename) throws IOException {
		FileReader input = null;
		try {
			input = new FileReader(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (input != null) {
			String line;
			BufferedReader br = new BufferedReader(input);
			if ((line = br.readLine()) != null) {
				String[] arr = line.split(" ");

				// Number of nodes is the first char passed in the input file
				numOfNodes = Integer.parseInt(arr[0]);

				// Number of edges is the second char passed in the input file
				numOfEdges = Integer.parseInt(arr[1]);

				// The third char states if it is Directed graph or not
				if (arr[2].equals("D")) {
					directed = true;
					System.out.println("The input graph is Directed Graph");
					// System.exit(0);
				}

				adj = new int[numOfNodes][numOfNodes];
				for (int i = 0; i < numOfEdges; i++) {
					line = br.readLine();
					arr = line.split(" ");
					adj[((int) arr[0].charAt(0) - 65)][((int) arr[1].charAt(0) - 65)] = Integer.parseInt(arr[2]);
					adj[((int) arr[1].charAt(0) - 65)][((int) arr[0].charAt(0) - 65)] = Integer.parseInt(arr[2]);
				}
				if ((line = br.readLine()) != null) {
					sourceVertex = ((int) line.charAt(0) - 65);
				} else {
					// as the source node is given optionally
					sourceVertex = 0;
				}
			}
		}
		Dijkstra();
	}

	public void Dijkstra() {
		weight = new int[numOfNodes];
		path = new int[numOfNodes];
		boolean[] visited = new boolean[numOfNodes];
		for (int i = 0; i < numOfNodes; i++) {
			weight[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		weight[sourceVertex] = 0;
		path[sourceVertex] = -1;

		for (int i = 0; i < numOfNodes; i++) {
			int next = -1;
			int minCost = Integer.MAX_VALUE;
			for (int v = 0; v < numOfNodes; v++) {
				if (visited[v] == false && weight[v] < minCost) {
					next = v;
					minCost = weight[v];
				}
			}
			visited[next] = true;
			for (int v = 0; v < numOfNodes; v++) {
				int edgeCost = adj[next][v];
				if (edgeCost > 0 && ((minCost + edgeCost) < weight[v])) {
					path[v] = next;
					weight[v] = minCost + edgeCost;
				}
			}
		}
		printOutput();
	}

	public void printOutput() {
		System.out.print("Number of nodes: " + numOfNodes + "\n" + "Number of edges: " + numOfEdges + "\n"
				+ "Source Node: " + (char) (sourceVertex + 65) + "\n");
		System.out.print("Nodes\t\tCost\t\tPath");
		for (int i = 0; i < numOfNodes; i++) {
			if (i != sourceVertex) {
				System.out.print("\n" + (char) (sourceVertex + 65) + " to ");
				System.out.print((char) (i + 65) + " \t\t ");
				System.out.print(weight[i] + "\t\t");
				int v = i;
				String str = "";
				while (path[v] != -1) {
					str = (char) (v + 65) + " " + str;
					v = path[v];
				}
				System.out.print(str);
			}
		}
	}
}
