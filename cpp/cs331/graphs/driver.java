package edu.cpp.cs331.graphs;
import java.util.HashMap;
public class driver{
	public static void main(String[] args)throws Graph.InconsistentEdgeException, Graph.DuplicateEdgeException,  Graph.VertexAlreadyExistsException{
		//Graph g = new Graph();
		Graph graph = new Graph();
		graph = getGraph1();

		// try{
		// 	graph = g.genRandomGraph(10,1f);
		// }
		// catch(Graph.VertexAlreadyExistsException e){
		//
		// }
		// catch(Graph.DuplicateEdgeException e){
		//
		// }
		// DijkstrasSP dijkstra = new DijkstrasSP();
		// System.out.println("Graph:\n"+graph);

		// System.out.println("STP: \n"+dijkstra.genShortestPath(graph, graph.getvList().get(3), graph.getvList().get(1)));
		// System.out.println("Neighbor:\n"+dijkstra.getNeibors());

		FloydsSP floyd = new FloydsSP();
		floyd.genShortestPath(graph, graph.getvList().get(3), graph.getvList().get(2));
		System.out.println("Graph:\n"+graph);
	}
	public static Graph getGraph1()throws Graph.DuplicateEdgeException, Graph.InconsistentEdgeException, Graph.VertexAlreadyExistsException{
		Graph graph1 = new Graph();
		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		graph1.addVertex(v0);
		graph1.addVertex(v1);
		graph1.addVertex(v2);
		graph1.addVertex(v3);



		graph1.addEdge(new Edge(v0, v2, 3));
		graph1.addEdge(new Edge(v0, v3, 0));
		graph1.addEdge(new Edge(v1, v0, -2));
		graph1.addEdge(new Edge(v1, v3, 1));
		graph1.addEdge(new Edge(v2, v3, 5));
		graph1.addEdge(new Edge(v3, v1, 4));
		return graph1;
	}
}
