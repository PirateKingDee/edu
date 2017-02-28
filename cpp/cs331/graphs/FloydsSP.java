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
		ListIterator<Edge> edges = G.geteList().listIterator();
		for(int i = 0; i<pathMatrix.length; i ++){
			for(int j = 0; j<pathMatrix.length; j++){
				if(edges.hasNext()){
					Edge e = edges.next();
					if(i == j){
						pathMatrix[i][j] = 0;
						edges.previous();
					}
					else if(e.getOne().getId()==i && e.getTwo().getId()==j){
						pathMatrix[i][j] = e.getWeight();
					}
					else{
						pathMatrix[i][j] = Integer.MAX_VALUE;
						edges.previous();
					}
				}
				else{
					pathMatrix[i][j] = Integer.MAX_VALUE;
				}	
			}
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