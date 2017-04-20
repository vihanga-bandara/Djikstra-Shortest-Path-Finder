
 /**
 * @author H.B.R.A.K.R.V.K.Bandara || uow ID : w1582949 || IIT No : 2015072
 *
 */
public class Node {

	int xnode;
	int ynode;

	double distance = Double.POSITIVE_INFINITY;
	Node parent = null;
	boolean visited;

	public int getX() {
		return xnode;
	}

	public void setX(int xnode) {
		this.xnode = xnode;
	}

	public int getY() {
		return ynode;
	}

	public void setY(int ynode) {
		this.ynode = ynode;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	boolean blocked;

	public Node(int xnode, int ynode) {
		this.xnode = xnode;
		this.ynode = ynode;
	}
}