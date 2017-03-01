package edu.cpp.cs331.graphs;

import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.LinkedList;
public class FloydsSP implements ShortestPath{
	int [][] distanceMatrix;
	LinkedList<Edge> [][]pathMatrix;
	int vertexSize;


	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal){
		vertexSize = G.getvList().size();
		initializeMatrix(G);
		printDistance();
		printPath();
		for(int k = 0; k<vertexSize; k++){
			for(int i = 0; i<vertexSize; i++){
				for(int j = 0; j<vertexSize; j++){
					if(distanceMatrix[i][k]+distanceMatrix[k][j]< distanceMatrix[i][j]){
						distanceMatrix[i][j] = distanceMatrix[i][k]+distanceMatrix[k][j];
						LinkedList<Edge> shortPath = new LinkedList<Edge>(pathMatrix[i][k]);
						shortPath.addAll(pathMatrix[k][j]);
						pathMatrix[i][j] = shortPath;
					}
				}
			}
		}
		printDistance();
		printPath();
		return null;
	}

	public void initializeMatrix(Graph G){
		distanceMatrix = new int[G.getvList().size()][G.getvList().size()];
		pathMatrix = new LinkedList[G.getvList().size()][G.getvList().size()];
		for(int i=0; i<distanceMatrix.length; i++){
			for(int j=0; j<distanceMatrix.length; j++){
				if(i==j){
					distanceMatrix[i][j] = 0;
				}
				else distanceMatrix[i][j] = Integer.MAX_VALUE;
				LinkedList<Edge> pathList = new LinkedList<Edge>();
				pathMatrix[i][j] = pathList;
			}
		}
		ListIterator<Edge> edges = G.geteList().listIterator();
		while(edges.hasNext()){
			Edge e = edges.next();
			distanceMatrix[e.getOne().getId()][e.getTwo().getId()] = e.getWeight();
			pathMatrix[e.getOne().getId()][e.getTwo().getId()].add(e);
		}

	}

	public void printDistance(){
		for(int i = 0; i < distanceMatrix.length; i++){
			for (int j = 0; j < distanceMatrix.length; j ++){
				if(distanceMatrix[i][j]==Integer.MAX_VALUE){
					System.out.print("        INF        ");
				}
				else{
					System.out.print("   "+distanceMatrix[i][j]+"   ");
				}
			}
			System.out.println();
		}
	}
	public void printPath(){
		System.out.println(Arrays.deepToString(pathMatrix));
	}
}
