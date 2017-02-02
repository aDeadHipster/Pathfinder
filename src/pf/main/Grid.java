package pf.main;

import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class Grid {

	private int width;
	private int height;
    private int columns;
    private int rows;

	public Node start;
    public Node goal;

    private Node[][] nodes;

	public class Node {

		private int c;
		private int r;
		private int x;
		private int y;
		private int nodeWidth = width/columns;
		private int nodeHeight = height/rows;
		private boolean obstacle = false;
		private ArrayList<Node> neighbors = new ArrayList<Node>();

		public Node(int c, int r) {
			this.c = c;
			this.r = r;
			this.x = c + c * nodeWidth;
			this.y = r + r * nodeHeight;
		}

		public boolean isObstacle() {
			return obstacle;
		}

		public void setObstacle(boolean value) {
			obstacle = value;
		}

		public void setNeighbor(Node n) {
			neighbors.add(n);
		}

		public ArrayList<Node> getNeighbors() {
			return neighbors;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getC() {
			return c;
		}

		public int getR() {
			return r;
		}
	}

    public Grid(int height, int width, int columns, int rows) {

	    this.height = height;
	    this.width = width;

	    this.columns = columns;
	    this.rows = rows;

	    nodes = new Node[this.columns][this.rows];

        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                nodes[c][r] = new Node(c, r);
            }
        }

        start = nodes[0][0];
        goal = nodes[columns - 1][rows - 1];

	    assignNeighbours();
    }

    private void assignNeighbours() {
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
	            // if the node is not on the left edge
                if (c != 0) {
		            nodes[c][r].setNeighbor(nodes[c - 1][r]);
                }

                // if the node is not on the right edge
                if (c != columns - 1) {
	                nodes[c][r].setNeighbor(nodes[c + 1][r]);
                }

                // if the node is not on the top edge
	            if (r != 0) {
		            nodes[c][r].setNeighbor(nodes[c][r - 1]);
	            }

	            // if the node is not on the bottom edge
	            if (r != rows - 1) {
		            nodes[c][r].setNeighbor(nodes[c][r + 1]);
	            }
            }
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Node[][] getNodes() {
        return nodes;
    }
}
