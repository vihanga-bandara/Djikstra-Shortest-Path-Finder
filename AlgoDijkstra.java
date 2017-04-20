
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author H.B.R.A.K.R.V.K.Bandara || uow ID : w1582949 || IIT No : 2015072
 *
 */
public class AlgoDijkstra {

	Node pointA;

	Node pointB;

	static double totDist;
	static double shortDist;

	double vertHDist;

	double DiagDist;

	Node[][] NMatrixGrid;

	static int choice1;

	boolean endnode = false;

	public AlgoDijkstra() {
		// TODO Auto-generated constructor stub
	}

	public AlgoDijkstra(int choice) {
		choice1=choice;
		// Distance for Manhattan
		if (choice == 1) {

			vertHDist = 1.0;
			DiagDist = 2.0;

		}
		// Distance for Euclidean
		if (choice == 2) {

			vertHDist = 1.0;
			DiagDist = 1.4;

		}
		// Distance for Chebyshev
		if (choice == 3) {

			vertHDist = 1.0;
			DiagDist = 1.0;

		}

	}

	public void createNode(int arrSize, Node[][] NMatrixGrid, boolean[][] findPath) {
		for (int i = 0; i < arrSize; ++i) {
			for (int j = 0; j < arrSize; ++j) {
				NMatrixGrid[i][j] = new Node(i, j);
				if (findPath[i][j] == false) {
					NMatrixGrid[i][j].blocked = true;
				}
			}
		}
	}

	// passes visitednodeq , nextnode , current node and cost
	public void nodeComp(PriorityQueue<Node> q, Node t, Node currentNode, double diff_distance) {
		double dist = currentNode.distance + diff_distance;

		// not visted not blocked and more than futurenodedist ( initially
		// infinite )

		if (!t.visited && !t.blocked && t.distance > dist) {

			// make it parent node
			t.parent = currentNode;

			// add cost i.e 1.4 , 1
			totDist += diff_distance;
			shortDist = dist;
			// add current distance to futurenode
			t.distance = dist;
			// adds it to priorityqueue
			q.add(t);
			if (!(NMatrixGrid[pointB.xnode][pointB.ynode].distance == Double.POSITIVE_INFINITY)) {

				endnode = true;

			}
		}

	}

	public void showPossPath(ArrayList<Node> pathfind) {
		for (Node node : pathfind) {
			if (!((node.ynode == pointB.ynode && node.xnode == pointB.xnode)
					|| (node.ynode == pointA.ynode && node.xnode == pointA.xnode) || (node.blocked == true))) {
				StdDraw.setPenColor(Color.PINK);
				StdDraw.square(node.ynode, 10 - node.xnode - 1, .5);
				StdDraw.setPenColor(Color.YELLOW);
				StdDraw.filledSquare(node.ynode, 10 - node.xnode - 1, .5);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}

	public Double manhatten(Node p1, Node p2) {
		return Math.pow(Math.pow((double) p2.getX() - (double) p1.getX(), 1.0)
				+ Math.pow((double) p2.getY() - (double) p1.getY(), 1), 1.0);
	}

	public double euclidean(Node p1, Node p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

	public double chebyshev(Node p1, Node p2) {
		return Math.max(Math.pow(p2.getX() - p1.getX(), 1), Math.pow(p2.getY() - p1.getY(), 1));
	}

	public void drawShortestPath(ArrayList<Node> path, int choice, int arrSize) {
		for (Node node : path) {

			if (!(node.ynode == pointA.ynode && node.xnode == pointA.xnode)) {

				if (choice == 1) {
					StdDraw.setPenColor(Color.BLUE);
					StdDraw.filledSquare(node.ynode, arrSize - node.xnode - 1, .5);
				}
				if (choice == 2) {
					StdDraw.setPenColor(Color.ORANGE);
					StdDraw.filledSquare(node.ynode, arrSize - node.xnode - 1, .5);
				
				}
				if (choice == 3) {
					StdDraw.setPenColor(Color.MAGENTA);
					StdDraw.filledSquare(node.ynode, arrSize - node.xnode - 1, .5);
				}
				/*
				 * try { Thread.sleep(100); } catch (InterruptedException e) {
				 * e.printStackTrace(); }
				 */
			}
		}
		
	}
	
	

	/**
	 * @param findPath
	 * @param PointAy
	 * @param PointAx
	 * @param PointBy
	 * @param PointBx
	 * @return
	 */
	/**
	 * @param findPath
	 * @param PointAy
	 * @param PointAx
	 * @param PointBy
	 * @param PointBx
	 * @return
	 */
	ArrayList<Node> ShortestDist(boolean[][] findPath, int PointAy, int PointAx, int PointBy, int PointBx) {

		int arrSize = findPath.length;
		double diff_distance;

		// creates another array of node type which is used to store nodes that
		// we have checked
		NMatrixGrid = new Node[arrSize][arrSize];

		// Creating nodes and finding blocked cells in findPath and mapping
		// accordingly to our grid
		createNode(arrSize, NMatrixGrid, findPath);

		pointA = new Node(PointAy, PointAx);

		// make the start node to green square
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledSquare(PointAx, arrSize - PointAy - 1, .5);

		pointB = new Node(PointBx, PointBy);
		// make the end node to red square
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledSquare(PointBx, arrSize - PointBy - 1, .5);
		// setting pointA distance to 0.
		// All other nodes will have postive infinity distance at the beginning
		pointA.distance = 0;
		// comparator is an interface and it has been implemented by defining a
		// class
		// a comparator object to deal with Priority Queue
		// it will compare two objects
		Comparator<Node> adjCompare = (l, r) -> {

			if (l.distance > (r.distance)) {
				return 1;
			}
			return -1;
		};

		// Queue to store visiting nodes
		// these are just like normal queues but has an element which defines
		// the priority
		PriorityQueue<Node> visitedNodesQ = new PriorityQueue(arrSize, adjCompare);

		visitedNodesQ.add(pointA);

		while (visitedNodesQ.size() > 0) {
			// for showing possible path method
			ArrayList<Node> pathfind = new ArrayList<>();
			// removes the next lowest distance node from visited queue and adds
			// it to the current node
			Node currentNode = visitedNodesQ.remove();
			Node nodeFuture;

			// Check Upper row
			if (currentNode.xnode - 1 >= 0) {

				// Up Straight cell
				// get the next node from nmatrixgrid
				nodeFuture = NMatrixGrid[currentNode.xnode - 1][currentNode.ynode];
				// add it the pathfind array
				pathfind.add(nodeFuture);
				diff_distance = vertHDist;

				nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
				// check whether end node has been found
				if (endnode) {
					break;
				}

				// Upper Left cell
				if (currentNode.ynode - 1 > 0 && !(choice1==1)) {
					nodeFuture = NMatrixGrid[currentNode.xnode - 1][currentNode.ynode - 1];
					pathfind.add(nodeFuture);
					diff_distance = DiagDist;
					nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
					if (endnode) {
						break;
					}

				}

				// Upper Right cell
				if (currentNode.ynode + 1 < arrSize && !(choice1==1)) {
					nodeFuture = NMatrixGrid[currentNode.xnode - 1][currentNode.ynode + 1];
					pathfind.add(nodeFuture);
					diff_distance = DiagDist;
					nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
					if (endnode) {
						break;
					}
				}
			}

			// LSide cell
			if (currentNode.ynode - 1 > 0) {
				nodeFuture = NMatrixGrid[currentNode.xnode][currentNode.ynode - 1];
				pathfind.add(nodeFuture);
				diff_distance = vertHDist;
				nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
				if (endnode) {
					break;
				}
			}

			// RSide cell
			if (currentNode.ynode + 1 < arrSize) {
				nodeFuture = NMatrixGrid[currentNode.xnode][currentNode.ynode + 1];
				pathfind.add(nodeFuture);
				diff_distance = vertHDist;
				nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
				if (endnode) {
					break;
				}
			}
			// Down row
			if (currentNode.xnode + 1 < arrSize) {

				// Down Straight cell
				nodeFuture = NMatrixGrid[currentNode.xnode + 1][currentNode.ynode];
				pathfind.add(nodeFuture);
				diff_distance = vertHDist;
				nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
				if (endnode) {
					break;
				}

				// DLeft cell
				if (currentNode.ynode - 1 >= 0 && !(choice1==1)) {
					nodeFuture = NMatrixGrid[currentNode.xnode + 1][currentNode.ynode - 1];
					pathfind.add(nodeFuture);
					diff_distance = DiagDist;
					nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
					if (endnode) {
						break;
					}
				}

				// DRight Cell
				if (currentNode.ynode + 1 < arrSize && !(choice1==1)) {
					nodeFuture = NMatrixGrid[currentNode.xnode + 1][currentNode.ynode + 1];
					pathfind.add(nodeFuture);
					diff_distance = DiagDist;
					nodeComp(visitedNodesQ, nodeFuture, currentNode, diff_distance);
					if (endnode) {
						break;
					}
				}
			}

			currentNode.visited = true;

			// showPossPath(pathfind);

		}

		ArrayList<Node> path = new ArrayList<>();

		// Checking if a path exists
		if (!(NMatrixGrid[pointB.xnode][pointB.ynode].distance == Double.POSITIVE_INFINITY)) {
			// Trace back the path
			Node currentNode = NMatrixGrid[pointB.xnode][pointB.ynode];

			while (currentNode.parent != null) {
				path.add(currentNode.parent);
				currentNode = currentNode.parent;
			}
			drawShortestPath(path, PathFindingOnSquaredGrid.choice, arrSize);
		} else {
			System.out.println("No possible path");

		}

		return path;
	}

}