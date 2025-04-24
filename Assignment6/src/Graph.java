import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road>{
	private Set<Town> towns;
    private Set<Road> roads;
    private Map<Town, Set<Road>> adjacencyMap;
    
    // for Dijkstra's Algorithm
    private Map<Town, Integer> distances;
    private Map<Town, Town> previous;
	
    public Graph() {
        towns = new HashSet<>();
        roads = new HashSet<>();
        adjacencyMap = new HashMap<>();
    }
    
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) return null; // make sure both are in set

        for (Road road : adjacencyMap.get(sourceVertex)) {
			if ((road.getHead().equals(sourceVertex) && road.getTail().equals(destinationVertex)) || (road.getHead().equals(destinationVertex) && road.getTail().equals(sourceVertex))) return road;
        } // i could've used .contains but im too lazy to fix it now...
        return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (!containsVertex(sourceVertex)) addVertex(sourceVertex);
        if (!containsVertex(destinationVertex)) addVertex(destinationVertex);

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        if (roads.contains(road)) return null;

        roads.add(road);
        adjacencyMap.get(sourceVertex).add(road);
        adjacencyMap.get(destinationVertex).add(road);
        return road;
	}

	@Override
	public boolean addVertex(Town v) {
		if (v == null || towns.contains(v)) return false;
		
        towns.add(v);
        adjacencyMap.put(v, new HashSet<>());
        return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		return getEdge(sourceVertex, destinationVertex) != null;
	}

	@Override
	public boolean containsVertex(Town v) {
		return towns.contains(v);
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		return adjacencyMap.get(vertex) == null ? Collections.emptySet() : adjacencyMap.get(vertex);
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
        adjacencyMap.get(sourceVertex).remove(road);
        adjacencyMap.get(destinationVertex).remove(road);
        return road;
	}

	@Override
	public boolean removeVertex(Town v) {
		if (!towns.remove(v)) return false;
        Set<Road> connectedRoads = new HashSet<>(adjacencyMap.get(v));
        for (Road road : connectedRoads) {
            Town other = road.getTail().equals(v) ? road.getHead() : road.getTail();
            adjacencyMap.get(other).remove(road);
            roads.remove(road);
        }
        adjacencyMap.remove(v);
        return true;
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
	}
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);

        ArrayList<String> path = new ArrayList<>();
        Town step = destinationVertex;

        if (!previous.containsKey(step)) return path;

        while (step != null && previous.get(step) != null) {
            Town prev = previous.get(step);
            Road road = getEdge(prev, step);
            if (road != null) {
                path.add(0, prev.getName() + " via " + road.getName() + " to " + step.getName() + " " + road.getWeight() + " mi");
            }
            step = prev;
        }

        return path;
	}

    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        distances = new HashMap<>();
        previous = new HashMap<>();
        PriorityQueue<Town> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Town town : towns) {
            distances.put(town, Integer.MAX_VALUE);
            previous.put(town, null);
        }
        distances.put(sourceVertex, 0);
        pq.add(sourceVertex);

        while (!pq.isEmpty()) {
            Town current = pq.poll();

            for (Road road : edgesOf(current)) {
                Town neighbor = road.getHead().equals(current) ? road.getTail() : road.getHead();
                int alt = distances.get(current) + road.getWeight();

                if (alt < distances.get(neighbor)) {
                    distances.put(neighbor, alt);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

}
