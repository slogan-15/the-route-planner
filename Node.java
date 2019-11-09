import java.util.ArrayList;

public class Node {
	private String name; // Read-only
	private ArrayList<Edge> neighbours = new ArrayList<Edge>();
	private boolean visited;
	private Node prevNode;
	private int length = Integer.MAX_VALUE;
	

	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setVisited() {
		visited = true;
	}

	public boolean isVisited() {
		return visited;
	}

	public int getLength () {
		return (length == Integer.MAX_VALUE ? 0 : length);
	}

	public Node getPrevNode () {
		return prevNode;
	}

	public void setLength(int len) {
		length = len;
	}

	public void onTheWay(Node from, int len) {
		if(length > len) {
			length = len;
			prevNode = from;
		}
	}

	private boolean neighbourExists(Edge edge) {
		for(Edge e : neighbours) {	
			if(e.getNode().equals(edge.getNode())) return true;
		}
		return false;
	}

	public ArrayList<Edge> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Edge edge) {
		if(!neighbourExists(edge)) neighbours.add(edge);
	}

	@Override
	public boolean equals(Object node) {
		Node n = (Node)node;
		return n.getName() == name;
	}

	@Override
	public String toString() {
		return name+" prevNode: "+ (prevNode != null ? prevNode.getName() : "null")+"; length: "+length;
	}
}