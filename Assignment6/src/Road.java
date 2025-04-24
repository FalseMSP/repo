import java.util.Objects;

public class Road implements Comparable<Road> {

	private Town head;
	private Town tail;
	private int weight;
	private String name;
	
	public Road(Town town, Town town2, int i, String string) {
		head = town;
		tail = town2;
		this.name = string;
		this.weight = i;
	}

	@Override
	public int compareTo(Road o) {
		return equals(o) ? 0 : -1;
	}

	protected Town getTail() {
		return tail;
	}

	protected Town getHead() {
		return head;
	}

	public boolean contains(Town destinationVertex) {
		if (destinationVertex == tail || destinationVertex == head) return true;
		return false;
	}

	protected int getWeight() {
		return weight;
	}

	protected String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) { //scuff af, there's gotta be a better way of doin this.
		Road other = (Road) o;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public int hashCode() {
	    return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
