package edu.cpp.cs331.graphs;

import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
public class FloydsSP implements ShortestPath{
	int [][] pathMatrix;

	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal){
		initializeMatrix(G);
		print();
		return null;
	}

	public void initializeMatrix(Graph G){
		pathMatrix = new int[G.getvList().size()][G.getvList().size()];
		for(int i=0; i<pathMatrix.length; i++){
			for(int j=0; j<pathMatrix.length; j++){
				if(i==j){
					pathMatrix[i][j] = 0;
				}
				else pathMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
		ListIterator<Edge> edges = G.geteList().listIterator();
		while(edges.hasNext()){
			Edge e = edges.next();
			pathMatrix[e.getOne().getId()][e.getTwo().getId()] = e.getWeight();
		}

	}

	public void print(){
		for(int i = 0; i < pathMatrix.length; i++){
			for (int j = 0; j < pathMatrix.length; j ++){
				if(pathMatrix[i][j]==Integer.MAX_VALUE){
					System.out.print("        INF        ");
				}
				else{
					System.out.print("   "+pathMatrix[i][j]+"   ");
				}
			}
			System.out.println();
		}
	}
}
