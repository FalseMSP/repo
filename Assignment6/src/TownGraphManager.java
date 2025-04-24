import java.util.ArrayList;
import java.util.Collections;

public class TownGraphManager implements TownGraphManagerInterface {
	Graph graph;
	public TownGraphManager() {
		graph = new Graph();
	}
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		if (containsTown(name)) return new Town(name);
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.vertexSet().contains(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return (graph.getEdge(new Town(town1), new Town(town2)) == null) ? false : true;
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> ans = new ArrayList<String>();
		for (Road i : graph.edgeSet()) {
			ans.add(i.toString());
		}
		Collections.sort(ans);
		return ans;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = new Town(town1);
	    Town destination = new Town(town2);
	    
	    Road r = graph.getEdge(source, destination);
	    if (r.getName() == road) {
	    	graph.removeEdge(source, destination, r.getWeight(), r.getName());
	    	return true;
	    }
	    return false; 
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> ans = new ArrayList<String>();
		for (Town i : graph.vertexSet()) {
			ans.add(i.toString());
		}
		Collections.sort(ans);
		return ans;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

}
