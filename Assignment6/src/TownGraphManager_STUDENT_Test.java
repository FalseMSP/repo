


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = "City_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[2], 2, "Path_1");
		  graph.addRoad(town[1], town[3], 4, "Path_2");
		  graph.addRoad(town[1], town[5], 6, "Path_3");
		  graph.addRoad(town[3], town[7], 1, "Path_4");
		  graph.addRoad(town[3], town[8], 2, "Path_5");
		  graph.addRoad(town[4], town[8], 3, "Path_6");
		  graph.addRoad(town[6], town[9], 3, "Path_7");
		  graph.addRoad(town[9], town[10], 4, "Path_8");
		  graph.addRoad(town[8], town[10], 2, "Path_9");
		  graph.addRoad(town[5], town[10], 5, "Path_10");
		  graph.addRoad(town[10], town[11], 3, "Path_11");
		  graph.addRoad(town[2], town[11], 6, "Path_12");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Path_1", roads.get(0));
		assertEquals("Path_10", roads.get(1));
		assertEquals("Path_11", roads.get(2));
		assertEquals("Path_12", roads.get(3));
		graph.addRoad(town[4], town[11], 1,"Path_13");
		roads = graph.allRoads();
		assertEquals("Path_1", roads.get(0));
		assertEquals("Path_10", roads.get(1));
		assertEquals("Path_11", roads.get(2));
		assertEquals("Path_12", roads.get(3));
		assertEquals("Path_13", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Path_12", graph.getRoad(town[2], town[11]));
		assertEquals("Path_4", graph.getRoad(town[3], town[7]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("City_12"));
		graph.addTown("City_12");
		assertEquals(true, graph.containsTown("City_12"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("City_12"));
		graph.addTown("City_12");
		ArrayList<String> path = graph.getPath(town[1],"City_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("City_2"));
		assertEquals(false, graph.containsTown("City_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
		assertEquals(false, graph.containsRoadConnection(town[3], town[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Path_1", roads.get(0));
		assertEquals("Path_10", roads.get(1));
		assertEquals("Path_11", roads.get(2));
		assertEquals("Path_8", roads.get(10));
		assertEquals("Path_9", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
		graph.deleteRoadConnection(town[2], town[11], "Path_12");
		assertEquals(false, graph.containsRoadConnection(town[2], town[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("City_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("City_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("City_1", roads.get(0));
		assertEquals("City_10", roads.get(1));
		assertEquals("City_11", roads.get(2));
		assertEquals("City_2", roads.get(3));
		assertEquals("City_8", roads.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("City_1 via Path_1 to City_2 2 mi",path.get(0).trim());
		  assertEquals("City_2 via Path_12 to City_11 6 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[1],town[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("City_1 via Path_2 to City_3 4 mi",path.get(0).trim());
		  assertEquals("City_3 via Path_5 to City_8 2 mi",path.get(1).trim());
		  assertEquals("City_8 via Path_9 to City_10 2 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[1],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("City_1 via Path_2 to City_3 4 mi",path.get(0).trim());
		  assertEquals("City_3 via Path_5 to City_8 2 mi",path.get(1).trim());
		  assertEquals("City_8 via Path_9 to City_10 2 mi",path.get(2).trim());
		  assertEquals("City_10 via Path_8 to City_9 4 mi",path.get(3).trim());
		  assertEquals("City_9 via Path_7 to City_6 3 mi",path.get(4).trim());

	}

}
