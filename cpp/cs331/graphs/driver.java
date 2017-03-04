package edu.cpp.cs331.graphs;
import java.util.HashMap;
public class driver{
	public static void main(String[] args)throws Graph.InconsistentEdgeException, Graph.DuplicateEdgeException,  Graph.VertexAlreadyExistsException{
		Graph g = new Graph();
		Graph graph = new Graph();
		graph = getGraph1();

		try{
			graph = g.genRandomGraph(10,1f);
		}
		catch(Graph.VertexAlreadyExistsException e){

		}
		catch(Graph.DuplicateEdgeException e){

		}
		// DijkstrasSP dijkstra = new DijkstrasSP();
		// System.out.println("Graph:\n"+graph);

		// System.out.println("STP: \n"+dijkstra.genShortestPath(graph, graph.getvList().get(3), graph.getvList().get(1)));
		// System.out.println("Neighbor:\n"+dijkstra.getNeibors());

		// FloydsSP floyd = new FloydsSP();
		// floyd.genShortestPath(graph, graph.getvList().get(3), graph.getvList().get(2));

		PrimsMST prims = new PrimsMST();

		System.out.println("Graph:\n"+graph);
		//System.out.println("MST Graph:\n"+prims.genMST(graph));
		testKruskal(graph);
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
	public static Graph getGraph2()throws Graph.DuplicateEdgeException, Graph.InconsistentEdgeException, Graph.VertexAlreadyExistsException{
		Graph graph1 = new Graph();
		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);

		graph1.addVertex(v0);
		graph1.addVertex(v1);
		graph1.addVertex(v2);
		graph1.addVertex(v3);
		graph1.addVertex(v4);
		graph1.addVertex(v5);

		graph1.addEdge(new Edge(v0, v1, 4));
		graph1.addEdge(new Edge(v1, v0, 4));
		graph1.addEdge(new Edge(v1, v5, 5));
		graph1.addEdge(new Edge(v5, v1, 5));
		graph1.addEdge(new Edge(v0, v5, 2));
		graph1.addEdge(new Edge(v5, v0, 2));
		graph1.addEdge(new Edge(v1, v2, 6));
		graph1.addEdge(new Edge(v2, v1, 6));
		graph1.addEdge(new Edge(v2, v5, 1));
		graph1.addEdge(new Edge(v5, v2, 1));
		graph1.addEdge(new Edge(v2, v3, 3));
		graph1.addEdge(new Edge(v3, v2, 3));
		graph1.addEdge(new Edge(v3, v4, 2));
		graph1.addEdge(new Edge(v4, v3, 2));
		graph1.addEdge(new Edge(v4, v5, 4));
		graph1.addEdge(new Edge(v5, v4, 4));
		return graph1;
	}
	public static void testKruskal(Graph g){
		KruskalsMST kruskal = new KruskalsMST();
		System.out.println(kruskal.genMST(g));
	}
}
