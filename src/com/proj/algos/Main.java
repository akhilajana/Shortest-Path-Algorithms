package com.proj.algos;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static long start;
    static long stop;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner scr = new Scanner(System.in); 
		int select;
                System.out.println("Select your coice of operation \n 1. Shortest Path \n 2. Minimum Spanning Tree" );
                select= scr.nextInt();
                switch(select){			
                    case 1: 		
                        start= System.currentTimeMillis();
                        ShortestPathAlgorithm spa = new ShortestPathAlgorithm();
                        spa.readFile("Input3.txt");
                        stop= System.currentTimeMillis();
                        System.out.println("\n Time taken in Milli-seconds: "+ (stop-start));
                        break;
                    case 2:  
                        start= System.currentTimeMillis();
                        MinimumSpanningTree.performMST("Input3.txt");
                        stop= System.currentTimeMillis();
                        System.out.println("\n Time taken in Milli-seconds: "+ (stop-start));
			break;
			}
				
	}

	}


