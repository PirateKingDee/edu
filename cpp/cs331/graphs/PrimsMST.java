package edu.cpp.cs331.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.ArrayList;
public class PrimsMST implements MinimumSpanningTree{
  LinkedList<Edge>[] neighborLists;
  PriorityQueue<Edge> frontier;
  int vertexSize;
  public Graph genMST(Graph g){
    vertexSize = g.getvList().size();
    Graph mst = new Graph();
    frontier = new PriorityQueue<>();
    //set up Neighbor
    setupNeighborLists(g);
    //put v0 in graph
    try{
      mst.addVertex(g.getvList().get(0));
    }
    catch(Graph.VertexAlreadyExistsException e){

    }
    //store all neighbor edges in an ArrayList
    //heapify the list
    updateFrontier(g.getvList().get(0));
    System.out.println(frontier);

    //while the list is not empty
    while(mst.getvList().size()!=vertexSize){
      //get the least weight edge(first one)
      Edge curEdge = frontier.poll();
        //if(e.v1 and e.v2 are in the graph)
        if(mst.getvList().contains(curEdge.getOne())&&mst.getvList().contains(curEdge.getTwo())){
          continue;
        }
          //pop the list and continue
        //else
        else{
          if(mst.getvList().contains(curEdge.getOne())){
            try{
              mst.addVertex(curEdge.getTwo());
              mst.addEdge(new Edge(curEdge.getOne(), curEdge.getTwo(), curEdge.getWeight()));
            }
            catch(Graph.VertexAlreadyExistsException e){

            }
            catch(Graph.InconsistentEdgeException e){

            }
            catch(Graph.DuplicateEdgeException e){

            }
            updateFrontier(curEdge.getTwo());
          }
          else if(mst.getvList().contains(curEdge.getTwo())){
            try{
              mst.addVertex(curEdge.getOne());
              mst.addEdge(new Edge(curEdge.getOne(), curEdge.getTwo(), curEdge.getWeight()));
            }
            catch(Graph.VertexAlreadyExistsException e){

            }
            catch(Graph.InconsistentEdgeException e){

            }
            catch(Graph.DuplicateEdgeException e){

            }
            updateFrontier(curEdge.getOne());
          }
        }
          //graph add both vertex
          //graph add edge
          //get neighbors of e.v2
          //add all its neighbor to list
          //pop the list
    }

    return mst;
  }

  public void setupNeighborLists(Graph g){
    neighborLists = new LinkedList[vertexSize];
    ListIterator<Edge> edges = g.geteList().listIterator();
    while(edges.hasNext()){
      Edge e = edges.next();
      System.out.println(e.getOne().getId());
      if(neighborLists[e.getOne().getId()]==null){
        LinkedList<Edge> edgeList = new LinkedList<>();
        edgeList.add(e);
        neighborLists[e.getOne().getId()]= edgeList;

      }
      else{
        neighborLists[e.getOne().getId()].add(e);
      }
    }
  }

  public void updateFrontier(Vertex v){
    for(Edge e : neighborLists[v.getId()]){
      frontier.add(e);
    }
  }
}
