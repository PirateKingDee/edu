package edu.cpp.cs331.graphs;

public class driver{
	public static void main(String[] args){
		Graph g = new Graph();
		
		try{
			System.out.println(g.genRandomGraph(4, 0.2f));
		}
		catch(Graph.VertexAlreadyExistsException e){
			
		}
	}
}
