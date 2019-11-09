import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FindRoute {

	private static ArrayList<Node> nodes = new ArrayList<Node>();

	public static void main(String arg []) {
		if(arg.length == 3) {
			readFile(arg[0]);

			try{
				printRoute(getNode(arg[1]), getNode(arg[2]));
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}			
		}
		else {
			System.out.println("Arguments are not correct!");
		}

	}

	// Method for finding the route from all nodes to a node
	private static void findRoute(Node node) {
		ArrayList<Node> queue = new ArrayList<Node>();

		Node temp = node;
		temp.setLength(0);
		queue.add(temp);

		while(queue.size() > 0){
			temp = queue.remove(0);
			temp.setVisited();

			for(Edge edge : temp.getNeighbours()) {
				Node n = edge.getNode(); 
				if(!n.equals(node)) {
					n.onTheWay(temp, temp.getLength()+edge.getDistance());
				}

				if(!n.isVisited()){
					queue.add(n);
				}
			}
		}
	}

	// Returns the route from a node to destination
	private static String printRouteRecur(Node from) {
		return (from.getPrevNode() == null 
				? from.getName() 
				: from.getName()+" -> "+printRouteRecur(from.getPrevNode()));
	}

	// Prints the route from a node to destination including distance
	private static void printRoute(Node from, Node to) {
		findRoute(to);
		System.out.println(printRouteRecur(from)+": "+from.getLength()+" minutes.");
	}

	// Connect two nodes. It is bidirectional
	private static void connectNodes(Node from, Node to, int length) {
		from.addNeighbour(new Edge(to, length));
		to.addNeighbour(new Edge(from, length));
	}

	// Find and return a node
	private static Node getNode(String name) throws Exception{
		for(Node n : nodes) {
			if(n.getName().equals(name)) return n;
		}
		throw new Exception("The station "+name+" does not exist!");
	}

	// Find and return node or if the node does not exist it will create and return a new node
	private static Node getOrAddNode(String name) {
		for(Node n : nodes) {
			if(n.getName().equals(name)) return n;
		}
		Node newNode = new Node(name);
		nodes.add(newNode);
		return newNode;
	}

	// Read file and parse it
	private static void readFile(String fileName) {
		try{
			FileInputStream fileStream = new FileInputStream(fileName);
			BufferedReader buff = new BufferedReader(new InputStreamReader(fileStream));

			String line;

			while ((line = buff.readLine()) != null) {
				String[] fromToDist = line.split(",", 3);
				
				Node from = getOrAddNode(fromToDist[0]);
				Node to = getOrAddNode(fromToDist[1]);

				connectNodes(from, to, Integer.parseInt(fromToDist[2]));
			}

			fileStream.close();
		}catch (Exception e){
			System.err.println("Error at reading file: " + e.getMessage());
		}
	}
}