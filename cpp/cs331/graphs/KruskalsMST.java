package edu.cpp.cs331.graphs;

import java.util.HashSet;
import java.util.LinkedList;
//import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class KruskalsMST implements MinimumSpanningTree{
  HashSet<Vertex>[] sets;
  ArrayList<Edge> sortedEdge;
  public Graph genMST(Graph g){
    Graph mst = new Graph();
    sets = new HashSet[g.getvList().size()];
    ArrayList<Edge> unSortEdges = new ArrayList<>(g.geteList());
    sortedEdge = new ArrayList(mergeSort(unSortEdges));
    //System.out.println(sortedEdge);
    ListIterator<Edge> sortedEdgeList = sortedEdge.listIterator();
    while(sortedEdgeList.hasNext()){
      Edge curEdge = sortedEdgeList.next();
      System.out.println(curEdge);
      //System.out.println("im here "+curEdge+ "set is "+sets[curEdge.getOne().getId()]+sets[curEdge.getTwo().getId()]);
      if(sets[curEdge.getOne().getId()]==null && sets[curEdge.getTwo().getId()]==null){
        HashSet<Vertex> curSet = new HashSet<>();
        curSet.add(curEdge.getOne());
        curSet.add(curEdge.getTwo());
        sets[curEdge.getOne().getId()]= curSet;
        sets[curEdge.getTwo().getId()]= curSet;
        addEdge(curEdge, mst);
        //System.out.println("first if");
      }
      else if(sets[curEdge.getOne().getId()]==null){
        sets[curEdge.getTwo().getId()].add(curEdge.getOne());
        sets[curEdge.getOne().getId()]=sets[curEdge.getTwo().getId()];
        addEdge(curEdge, mst);
        //System.out.println("second if");
      }
      else if(sets[curEdge.getTwo().getId()]==null){
        sets[curEdge.getOne().getId()].add(curEdge.getTwo());
        sets[curEdge.getTwo().getId()]=sets[curEdge.getOne().getId()];
        addEdge(curEdge, mst);
        // System.out.println("third if");
        // System.out.println(mst);
      }
      else if(!sets[curEdge.getOne().getId()].contains(curEdge.getTwo())){
        sets[curEdge.getOne().getId()].addAll(sets[curEdge.getTwo().getId()]);
        sets[curEdge.getTwo().getId()] = sets[curEdge.getOne().getId()];
        addEdge(curEdge, mst);
        //System.out.println("fourth if");
      }
      else{
        //System.out.println("continue if");
        continue;
      }
    }
    System.out.println(mst);
    return null;
  }
  public void addEdge(Edge e, Graph g){
    try{
      g.addVertex(e.getOne());
      //System.out.println(g.getvList());
    }
    catch(Graph.VertexAlreadyExistsException v){
      System.out.println("InconsistenEdgeExcption");
    }
    try{
      g.addVertex(e.getTwo());
    }
    catch(Graph.VertexAlreadyExistsException v){
      System.out.println("InconsistenEdgeExcption");
    }

    try{
      g.addEdge(e);
    }
    catch(Graph.InconsistentEdgeException i){
      System.out.println("InconsistenEdgeExcption");
    }
    catch(Graph.DuplicateEdgeException d){
      System.out.println("DuplicateEdgeException");
    }


  }
  public ArrayList<Edge> mergeSort(ArrayList<Edge> l){	//Input Space: O(n)
    if(l.isEmpty()||l.size()==1){	//Auxillary Space: O(1), Time: O(1)
      return l;					//Auxillary Space: O(1), Time: O(1)
    }
    ArrayList<Edge> a = new ArrayList<Edge>();	//Auxillary Space: O(1), Time: O(1)
    ArrayList<Edge> b = new ArrayList<Edge>();	//Auxillary Space: O(1), Time: O(1)
    int mid = l.size()/2;					//Auxillary Space: O(1), Time: O(1)
    ListIterator<Edge> iterator = l.listIterator();	//Auxillary Space: O(1), Time: O(1)
    for(int i=0; i<mid; i++){
      a.add(iterator.next());				//Auxillary Space: O(n/2), Time: O(n/2)
    }
    while(iterator.hasNext()){
      b.add(iterator.next());				//Auxillary Space: O(n/2), Time: O(n/2)
    }
    return merge(mergeSort(a),mergeSort(b));
  }
  public ArrayList<Edge> merge(ArrayList<Edge> a, ArrayList<Edge> b){	//Input Space: n/2 + n/2
    ArrayList<Edge> sorted = new ArrayList<Edge>();		//Auxillary Space: O(1), Time: O(1)
    ListIterator<Edge> iterator_a = a.listIterator();	//Auxillary Space: O(1), Time: O(1)
    ListIterator<Edge> iterator_b = b.listIterator();	//Auxillary Space: O(1), Time: O(1)
    while(iterator_a.hasNext() && iterator_b.hasNext()){	//Auxillary Space: O(1), Time: O(1)
      Edge ele_a = iterator_a.next();		//Auxillary Space: O(1), Time: O(1)
      Edge ele_b = iterator_b.next();		//Auxillary Space: O(1), Time: O(1)
      if(ele_a.compareTo(ele_b)<0){
        sorted.add(ele_a);
        iterator_a.remove();
        iterator_b.previous();
      }
      else{
        sorted.add(ele_b);
        iterator_b.remove();
        iterator_a.previous();
      }
    }
    while(iterator_a.hasNext()){
      sorted.add(iterator_a.next());
      iterator_a.remove();		//OutPut Space: O(n/2), Time: O(n/2)
    }
    while(iterator_b.hasNext()){
      sorted.add(iterator_b.next());		//OutPut Space: O(n/2), Time: O(n/2)
      iterator_b.remove();
    }
    return sorted;
  }
}
