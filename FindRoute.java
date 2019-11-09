import java.util.ArrayList;

public class FindRoute {
	public static void main(String arg []) {
		Node mjomna = new Node("Mjømna");
		Node furenes = new Node("Furenes");
		Node eivindik = new Node("Eivindik");
		Node rutledal = new Node("Rutledal");
		Node oppedal = new Node("Oppedal");
		Node daloy = new Node("Daløy");
		Node slovag = new Node("Sløvåg");
		Node nara = new Node("Nåra");
		Node ynnesdal = new Node("Ynnesdal");
		Node leirvik = new Node("Leirvik");




		connectNodes(mjomna, furenes, 5);
		connectNodes(furenes, eivindik, 7);
		connectNodes(eivindik, oppedal, 12);
		connectNodes(eivindik, rutledal, 15);
		connectNodes(rutledal, oppedal, 10);
		connectNodes(rutledal, daloy, 17);
		connectNodes(rutledal, slovag, 10);
		connectNodes(slovag, daloy, 9);
		connectNodes(daloy, nara, 11);
		connectNodes(rutledal, ynnesdal, 20);
		connectNodes(ynnesdal, leirvik, 16);
		connectNodes(leirvik, nara, 30);





		printRoute(oppedal, nara); // fra til

	}

	public static void findRoute(Node node) {
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

	private static String printRouteRecur(Node from) {
		return (from.getPrevNode() == null 
				? from.getName() 
				: from.getName()+" -> "+printRouteRecur(from.getPrevNode()));
	}

	public static void printRoute(Node from, Node to) {
		findRoute(to);
		System.out.println(printRouteRecur(from)+": "+from.getLength()+" minutes.");
	}

	public static void connectNodes(Node from, Node to, int length) {
		from.addNeighbour(new Edge(to, length));
		to.addNeighbour(new Edge(from, length));

	}
}