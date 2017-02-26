package edu.cpp.cs331.graphs;
import java.util.HashMap;
public class driver{
	public static void main(String[] args){
		Graph g = new Graph();
		Graph graph = new Graph();
		try{
			graph = g.genRandomGraph(10,0.3f);
		}
		catch(Graph.VertexAlreadyExistsException e){

		}
		DijkstrasSP dijkstra = new DijkstrasSP();
		System.out.println("Graph:\n"+graph);

		System.out.println("STP: \n"+dijkstra.genShortestPath(graph, graph.getvList().get(1), graph.getvList().get(6)));
		System.out.println("Neighbor:\n"+dijkstra.getNeibors());

	}
}
