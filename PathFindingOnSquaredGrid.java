import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author H.B.R.A.K.R.V.K.Bandara || UOW ID : w1582949 || IIT No : 2015072
 *
 */

public class PathFindingOnSquaredGrid {

	static int choice;

	// given an N-by-N matrix of open cells, return an N-by-N matrix
	// of cells reachable from the top
	public static boolean[][] flow(boolean[][] open) {
		int N = open.length;

		boolean[][] full = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			flow(open, full, 0, j);
		}

		return full;
	}

	// determine set of open/blocked cells using depth first search
	public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
		int N = open.length;
		// i and j values are x and y values of array full and open array
		// base cases
		if (i < 0 || i >= N) {
			return; // invalid row
		}
		if (j < 0 || j >= N) {
			return; // invalid column
		}
		if (!open[i][j]) { // check if the i,j cell of open array is not
							// true..if so its not an open cell return back
			return; // not an open cell
		}
		if (full[i][j]) {
			return; // already marked as open or which means has already been
					// marked open or true
		}

		full[i][j] = true; // marks as open

		flow(open, full, i + 1, j); // down
		flow(open, full, i, j + 1); // right
		flow(open, full, i, j - 1); // left
		flow(open, full, i - 1, j); // up
	}

	// does the system percolate?
	public static boolean percolates(boolean[][] open) {
		int N = open.length;

		boolean[][] full = flow(open);
		for (int j = 0; j < N; j++) {
			if (full[N - 1][j])
				return true;
		}

		return false;
	}

	// does the system percolate vertically in a direct way?
	public static boolean percolatesDirect(boolean[][] open) {
		int N = open.length;

		boolean[][] full = flow(open);
		int directPerc = 0;
		for (int j = 0; j < N; j++) {
			if (full[N - 1][j]) {
				// StdOut.println("Hello");
				directPerc = 1;
				int rowabove = N - 2;
				for (int i = rowabove; i >= 0; i--) {
					if (full[i][j]) {
						// StdOut.println("i: " + i + " j: " + j + " " +
						// full[i][j]);
						directPerc++;
					} else
						break;
				}
			}
		}

		// StdOut.println("Direct Percolation is: " + directPerc);
		if (directPerc == N)
			return true;
		else
			return false;
	}

	// draw the N-by-N boolean matrix to standard draw //this draws the graph
	// with random blocks as well
	public static void show(boolean[][] a, boolean which) {
		int N = a.length;
		StdDraw.setCanvasSize(900, 900);
		StdDraw.setXscale(-1, N);
		; // create x and y dimension and setting the pen colour
		StdDraw.setYscale(-1, N);
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (a[i][j] == which) // if its true or 1 or which means
										// unblocked squares no colour squares
										// created
					StdDraw.square(j, N - i - 1, .5); // x coordinate , y
														// coordinate and radius
														// of the square y
														// starts from 8
				else
					StdDraw.filledSquare(j, N - i - 1, .5); // blocked square
	}

	// draw the N-by-N boolean matrix to standard draw, including the points A
	// (x1, y1) and B (x2,y2) to be marked by a circle
	/*
	 * public static void show(boolean[][] a, boolean which, int x1, int y1, int
	 * x2, int y2) { int N = a.length; StdDraw.setXscale(-1, N);;
	 * StdDraw.setYscale(-1, N); StdDraw.setPenColor(StdDraw.GREEN); for (int i
	 * = 0; i < N; i++) for (int j = 0; j < N; j++) if (a[i][j] == which) if ((i
	 * == x1 && j == y1) ||(i == x2 && j == y2)) { StdDraw.circle(j, N-i-1, .5);
	 * } else StdDraw.square(j, N-i-1, .5); else StdDraw.filledSquare(j, N-i-1,
	 * .5); }
	 */
	// j = x axis and i = y axis
	// 1=true 0=false in the graph
	// return a random N-by-N boolean matrix, where each entry is
	// true with probability p
	public static boolean[][] random(int N, double p) {
		boolean[][] a = new boolean[N][N]; // 10*10 array of boolean created
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = StdRandom.bernoulli(p); // with a probability of 0.4
													// will fill the array with
													// trues
		return a;
	}

	// test client
	public static void main(String[] args) {
		// boolean[][] open = StdArrayIO.readBoolean2D();

		// The following will generate a 10x10 squared grid with relatively few
		// obstacles in it
		// The lower the second parameter, the more obstacles (black cells) are
		// generated
		boolean[][] randomlyGenMatrix = random(10, 0.8);

		StdArrayIO.print(randomlyGenMatrix);
		// This class provides methods for reading
		// in 1D and 2D arrays from standard input and printing out to
		// standard output.

		show(randomlyGenMatrix, true);
		// this will draw the graph along with the blocks

		System.out.println();
		System.out.println("The system percolates: " + percolates(randomlyGenMatrix));

		System.out.println();
		System.out.println("The system percolates directly: " + percolatesDirect(randomlyGenMatrix));
		System.out.println();

		// Reading the coordinates for points A and B on the input squared grid.

		// THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
		// Start the clock ticking in order to capture the time being spent on
		// inputting the coordinates
		// You should position this command accordingly in order to perform the
		// algorithmic analysis

		Scanner sc = new Scanner(System.in);

		boolean exit = false;
		while (!exit) {
			System.out.println("\n------------Welcome to Shortest Path Finder using Heuristics------------\n");
			System.out.println("\tPick one of the Heuristics to be used\n");
			System.out.println("\t1) Manhattan ");
			System.out.println("\t2) Euclidean ");
			System.out.println("\t3) Chebyshev ");
			System.out.println("\t4) Exit ");
			do { // validation of integer input
				System.out.print("Choice\t: ");
				while (!sc.hasNextInt()) {
					System.out.println("Please enter a valid number !");
					System.out.print("Choice\t:");
					sc.next();
				}
				choice = sc.nextInt();
			} while (choice <= 0 && choice != 4);
			if (choice == 4) {
				System.out.println("\n------------Exiting the System--------------\n\n");
				System.exit(0);

			}

			System.out.println("Enter Y coordinate for Point A > ");
			int PointAy = sc.nextInt();

			System.out.println("Enter X coordinate for Point A > ");
			int PointAx = sc.nextInt();

			System.out.println("Enter Y coordinate for Point B > ");
			int PointBy = sc.nextInt();

			System.out.println("Enter X coordinate for Point B > ");
			int PointBx = sc.nextInt();

			// THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
			// Stop the clock ticking in order to capture the time being spent
			// on inputting the coordinates
			// You should position this command accordingly in order to perform
			// the algorithmic analysis
			// StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

			System.out.println("Coordinates for A: [" + PointAy + "," + PointAx + "]");
			System.out.println("Coordinates for B: [" + PointBy + "," + PointBx + "]");

			AlgoDijkstra dist = new AlgoDijkstra();

			if ((choice == 1) || (choice == 2) || (choice == 3)) {
				AlgoDijkstra.totDist = 0;
				Stopwatch timerFlow = new Stopwatch();
				ArrayList<Node> FindPath = new AlgoDijkstra(choice).ShortestDist(randomlyGenMatrix, PointAy, PointAx,
						PointBy, PointBx);
				double time = timerFlow.elapsedTime();

				if (choice == 1) {
					System.out.println("\n------------Using Manhattan Distance-----------\n\n\t Elapsed Time: " + time
							+ "\n\tTotal Cost: " + AlgoDijkstra.totDist + "\n\tShortest Path cost: "
							+ AlgoDijkstra.shortDist);
				} else if (choice == 2) {
					System.out.println("\n------------Using Euclidean Distance-----------\n\n\t Elapsed Time: " + time
							+ "\n\tTotal Cost: " + AlgoDijkstra.totDist + "\n\tShortest Path cost: "
							+ AlgoDijkstra.shortDist);
				} else {
					System.out.println("\n------------Using Chebyshev Distance-----------\n\n\t Elapsed Time: " + time
							+ "\n\tTotal Cost: " + AlgoDijkstra.totDist + "\n\tShortest Path cost: "
							+ AlgoDijkstra.shortDist);
				}

			}

			Node a = new Node(PointAx, PointAy);

			Node b = new Node(PointBx, PointBy);

			AlgoDijkstra go = new AlgoDijkstra();

			System.out.println("\n\tEuclidean distance :=  " + go.euclidean(a, b));

			System.out.println("\tManhatten distance :=  " + go.manhatten(a, b));

			System.out.println("\tChebyshev distance :=  " + go.chebyshev(a, b));

		}
	}
}
