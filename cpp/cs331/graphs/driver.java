package edu.cpp.cs331.graphs;
import java.util.HashMap;
public class driver{
	public static void main(String[] args){
		Graph g = new Graph();
		Graph graph = new Graph();
		try{
			graph = g.genRandomGraph(4,0.5f);
		}
		catch(Graph.VertexAlreadyExistsException e){

		}
		DijkstrasSP dijkstra = new DijkstrasSP();
		dijkstra.genShortestPath(graph, graph.getvList().get(1), graph.getvList().get(2));
		System.out.println(graph);
		System.out.println(dijkstra.getNeibors());
	}
}
