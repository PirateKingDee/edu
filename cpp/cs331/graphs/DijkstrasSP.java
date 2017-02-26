package edu.cpp.cs331.graphs;

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ListIterator;

public class DijkstrasSP implements ShortestPath{

	ArrayList<Vertex> reachable;
	ArrayList<Integer> distance;
	ArrayList<Vertex> visited;
	HashMap<Vertex, LinkedList<Edge>> neighbor;
	HashMap<Vertex, Edge> pathTo;
	Graph graph;
	public DijkstrasSP(){
		reachable = new ArrayList<Vertex>();
		distance = new ArrayList<Integer>();
		visited = new ArrayList<Vertex>();
		neighbor = new HashMap<Vertex, LinkedList<Edge>>();
		pathTo = new HashMap<Vertex, Edge>();
	}
	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal){
		this.graph = G;
		setInitialDistance();
		setupNeighbor();
		distance.set(source.getId(), 0);
		heapify(reachable, distance);
		Vertex visitingVertex = source;
		visited.add(visitingVertex);
		// distance.remove(0);
		// reachable.remove(0);
		// System.out.println(distance);
		// System.out.println(reachable);
		while(visitingVertex != goal){
			ListIterator<Edge> neighborList = neighbor.get(visitingVertex).listIterator();
			while(neighborList.hasNext()){
				Edge neighborEdge = neighborList.next();
				Integer cost = neighborEdge.getWeight();
				Vertex neighborVertex = neighborEdge.getTwo();
				if(visited.contains(neighborVertex)){
					continue;
				}
				int newCost = distance.get(reachable.indexOf(visitingVertex))+cost;
				if(distance.get(reachable.indexOf(neighborVertex))!=Integer.MAX_VALUE){

					if(newCost < distance.get(reachable.indexOf(neighborVertex))){
						distance.set(reachable.indexOf(neighborVertex), newCost);
						pathTo.replace(neighborVertex, neighborEdge);
					}
				}
				else{
					distance.set(reachable.indexOf(neighborVertex), newCost);
					pathTo.put(neighborVertex, neighborEdge);
				}
			}
			distance.remove(0);
			reachable.remove(0);
			heapify(reachable, distance);
			visitingVertex = reachable.get(0);
			visited.add(visitingVertex);
			// System.out.println(distance);
			// System.out.println(reachable);
			// break;
		}
		LinkedList<Edge> SPT = new LinkedList<Edge>();
		SPT.add(pathTo.get(goal));
		while(SPT.getFirst().getOne().equals(source)==false){
			SPT.addFirst(pathTo.get(SPT.getFirst().getOne()));
		}
		// System.out.println(distance);
		// System.out.println(reachable);
		// System.out.println(visited);
		// System.out.println(pathTo);
		return SPT;
	}
	public void setInitialDistance(){
		//System.out.println(graph.getvList().size());
		for(int i =0; i<graph.getvList().size(); i++){
			distance.add(Integer.MAX_VALUE);
		}
		for (Vertex v : graph.getvList()){
			reachable.add(v);
		}

	}
	public void setupNeighbor(){
		ListIterator<Edge> allVertices =graph.geteList().listIterator();
		while(allVertices.hasNext()){
			Edge e = allVertices.next();
			//Vertex v = e.getOne();
			//Vertex v2 = e.getTwo();
			if(neighbor.containsKey(e.getOne())){
				// LinkedList<Integer> cost = new LinkedList<>();
				// cost.add(v2.getId());
				// cost.add(e.getWeight());
				// neighbor.get(v).add(cost);
				neighbor.get(e.getOne()).add(e);
			}
			else{
				// LinkedList<Integer> cost = new LinkedList<>();
				// LinkedList<LinkedList<Integer>> neighborList = new LinkedList<>();
				// cost.add(v2.getId());
				// cost.add(e.getWeight());
				// neighborList.add(cost);
				// neighbor.put(v, neighborList);
				LinkedList<Edge> edgeList = new LinkedList<>();
				edgeList.add(e);
				neighbor.put(e.getOne(), edgeList);
			}
		}
	}


	public HashMap getNeibors(){
		return neighbor;
	}

	public void heapify(ArrayList<Vertex> v, ArrayList<Integer> d){
		int node = v.size()/2-1;
		for (int i=node; i>=0; i--){
			siftDown(v,d, i, v.size());
		}
	}
	public void siftDown(ArrayList<Vertex> vertex, ArrayList<Integer> distance, int node, int last){
		int c ;
		int d ;
		while(node<last/2){
			c = node*2+2;
			d = node*2+1;
			if(c>last-1){
				if(distance.get(d)<distance.get(node)){
					swap(vertex, distance, node, d);
					node = d;
				}
				else node = d;

			}

			else if(distance.get(d)<distance.get(node)||distance.get(c)<distance.get(node)){
				if(distance.get(d)<distance.get(c)){
					swap(vertex,distance,node,d);
					node = d;
				}
				else {
					swap(vertex, distance,node,c);
					node = c;
				}
			}
			else {
				break;
			}
		}
	}
	public void swap(ArrayList<Vertex> v, ArrayList<Integer> d, int a, int b){
		Vertex tempV = v.get(a);
		int tempD = d.get(a);
		v.set(a, v.get(b));
		d.set(a, d.get(b));
		v.set(b, tempV);
		d.set(b, tempD);
	}

}
