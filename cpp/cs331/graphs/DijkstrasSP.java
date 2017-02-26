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
	HashMap<Vertex, LinkedList<LinkedList<Integer>>> neighbor;
	Graph graph;
	int shortestDistance;
	public DijkstrasSP(){
		reachable = new ArrayList<Vertex>();
		distance = new ArrayList<Integer>();
		visited = new ArrayList<Vertex>();
		neighbor = new HashMap<Vertex, LinkedList<LinkedList<Integer>>>();
	}
	public List<Edge> genShortestPath(Graph G, Vertex source, Vertex goal){
		this.graph = G;
		setInitialDistance();
		setupNeighbor();
		distance.set(source.getId(), 0);
		Vertex visitingVertex = source;
		visited.add(visitingVertex);
		shortestDistance = 0;
		heapify(reachable, distance);
		while(visitingVertex != goal){
			// ListIterator<LinkedList<Integer>> neighborList = neighbor.get(visitingVertex).listIterator();
			// while(neighborList.hasNext()){
			// 	LinkedList<Integer> nextNeighbor = neighborList.next();
			// 	Integer cost = nextNeighbor.getLast();
			// 	Vertex neighborVertex = graph.getvList().get(nextNeighbor.getFirst());
			// 	int newCost = distance.get(visitingVertex.getId())+cost;
			// 	if(distance.get(neighborVertex.getId())!=Integer.MAX_VALUE){
			//
			// 		if(newCost < distance.get(neighborVertex.getId())){
			// 			distance.set(neighborVertex.getId(), newCost);
			// 		}
			// 	}
			// 	else{
			// 		distance.set(neighborVertex.getId(), newCost);
			//
			// 	}
			// }
			System.out.println(distance);
			System.out.println(reachable);
			break;
		}
		return null;
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
			Vertex v = e.getOne();
			Vertex v2 = e.getTwo();
			if(neighbor.containsKey(v)){
				LinkedList<Integer> cost = new LinkedList<>();
				cost.add(v2.getId());
				cost.add(e.getWeight());
				neighbor.get(v).add(cost);
			}
			else{
				LinkedList<Integer> cost = new LinkedList<>();
				LinkedList<LinkedList<Integer>> neighborList = new LinkedList<>();
				cost.add(v2.getId());
				cost.add(e.getWeight());
				neighborList.add(cost);
				neighbor.put(v, neighborList);
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
