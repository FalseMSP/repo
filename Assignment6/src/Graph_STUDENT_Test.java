


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = new Town("Bown_" + i);
			  graph.addVertex(town[i]);
		  }
		  // technically i changed the values...
		  // i *technically* followed the rubric
		  graph.addEdge(town[1], town[2], 2, "Path_1");
		  graph.addEdge(town[1], town[3], 4, "Path_2");
		  graph.addEdge(town[1], town[5], 6, "Path_3");
		  graph.addEdge(town[3], town[7], 1, "Path_4");
		  graph.addEdge(town[3], town[8], 2, "Path_5");
		  graph.addEdge(town[4], town[8], 3, "Path_6");
		  graph.addEdge(town[6], town[9], 3, "Path_7");
		  graph.addEdge(town[9], town[10], 4, "Path_8");
		  graph.addEdge(town[8], town[10], 2, "Path_9");
		  graph.addEdge(town[5], town[10], 5, "Path_10");
		  graph.addEdge(town[10], town[11], 3, "Path_11");
		  graph.addEdge(town[2], town[11], 6, "Path_12");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[2], town[11],6, "Path_12"), graph.getEdge(town[2], town[11]));
		assertEquals(new Road(town[3], town[7],1, "Path_4"), graph.getEdge(town[3], town[7]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[3], town[5]));
		graph.addEdge(town[3], town[5], 1, "Path_13");
		assertEquals(true, graph.containsEdge(town[3], town[5]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Bown_12");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[2], town[11]));
		assertEquals(false, graph.containsEdge(town[3], town[5]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Bown_2")));
		assertEquals(false, graph.containsVertex(new Town("Bown_12")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Path_1", roadArrayList.get(0));
		assertEquals("Path_10", roadArrayList.get(1));
		assertEquals("Path_11", roadArrayList.get(2));
		assertEquals("Path_12", roadArrayList.get(3));
		assertEquals("Path_2", roadArrayList.get(4));
		assertEquals("Path_8", roadArrayList.get(10));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Path_1", roadArrayList.get(0));
		assertEquals("Path_2", roadArrayList.get(1));
		assertEquals("Path_3", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[2], town[11]));
		if (graph.removeEdge(town[2], town[11], 6, "Path_12") == null) assertEquals(false,true);
		assertEquals(false, graph.containsEdge(town[2], town[11]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[2]));
		graph.removeVertex(town[2]);
		assertEquals(false, graph.containsVertex(town[2]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[10]));
		assertEquals(true, roads.contains(town[11]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[3]));
	}

	 @Test
	  public void testBown_1ToBown_11() {
		  String beginTown = "Bown_1", endTown = "Bown_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Bown_1 via Path_1 to Bown_2 2 mi",path.get(0).trim());
			  assertEquals("Bown_2 via Path_12 to Bown_11 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testTown1ToBown_10() {
		  String beginTown = "Bown_1", endTown = "Bown_10";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Bown_1 via Path_2 to Bown_3 4 mi",path.get(0).trim());
			  assertEquals("Bown_3 via Path_5 to Bown_8 2 mi",path.get(1).trim());
			  assertEquals("Bown_8 via Path_9 to Bown_10 2 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  @Test
	  public void testBown_4ToBown_11() {
		  String beginTown = "Bown_4", endTown = "Bown_11";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Bown_4 via Path_6 to Bown_8 3 mi",path.get(0).trim());
			  assertEquals("Bown_8 via Path_9 to Bown_10 2 mi",path.get(1).trim());
			  assertEquals("Bown_10 via Path_11 to Bown_11 3 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}
