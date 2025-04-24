public class Town implements Comparable<Town> {
	String name;
	
	public Town(String string) {
		name = string;
	}

	@Override
	public int compareTo(Town o) {
		if (this.name.equals(o.getName()))
			return 0;
		return -1;
	}

	protected String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		return name.equals(((Town) o).getName());
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