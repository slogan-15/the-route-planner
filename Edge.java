public class Edge {
	private Node to; // Read-only
	private int distance; // Read-only

	public Edge(Node to, int distance) {
		this.to = to;
		this.distance = distance;
	}

	public Node getNode() {
		return to;
	}

	public int getDistance() {
		return distance;
	}
}