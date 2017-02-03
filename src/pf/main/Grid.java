package pf.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.floor;

/**
 * @author Justin Praas
 */
public class Grid extends JPanel {

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
		private int nodeWidth = (int) floor(width / columns);
		private int nodeHeight = (int) floor(height / rows);
		private boolean obstacle = false;
		private Set<Node> neighbors = new HashSet<>();

		public Node(int c, int r) {
			this.c = c;
			this.r = r;
			this.x = c * nodeWidth;
			this.y = r * nodeHeight;
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

		public Set<Node> getNeighbors() {
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

		public void setAllNeighbors(Node node) {
			int nodeC = node.getC();
			int nodeR = node.getR();
			node.setNeighbor(nodes[nodeC][nodeR - 1]);
			node.setNeighbor(nodes[nodeC][nodeR + 1]);
			node.setNeighbor(nodes[nodeC - 1][nodeR]);
			node.setNeighbor(nodes[nodeC + 1][nodeR]);
			node.setNeighbor(nodes[nodeC - 1][nodeR - 1]);
			node.setNeighbor(nodes[nodeC + 1][nodeR - 1]);
			node.setNeighbor(nodes[nodeC - 1][nodeR + 1]);
			node.setNeighbor(nodes[nodeC + 1][nodeR + 1]);
		}
	}

	public Grid(int height, int width, int columns, int rows, double obstacleRate) {

		this.height = height;
		this.width = width;

		this.columns = columns;
		this.rows = rows;

		nodes = new Node[this.columns][this.rows];

		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				nodes[c][r] = new Node(c, r);
				double random = Math.random();
				if (random < obstacleRate) {
					nodes[c][r].setObstacle(true);
				}
			}
		}

		start = nodes[0][0];
		goal = nodes[columns - 1][rows - 1];

		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				assignNeighbours(c, r);
			}
		}
	}

	private void assignNeighbours(int i, int j) {

		// Find and assign all neighbors
		for (int c = Math.max(0, i - 1); c <= Math.min(i + 1, columns - 1); c++) {
			for (int r = Math.max(0, j - 1); r <= Math.min(j + 1, rows - 1); r++) {
				if (c != i || r != j) {
					nodes[i][j].setNeighbor(nodes[c][r]);
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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Pathfinder pathFinder = new Pathfinder();
		ArrayList<Node> path = pathFinder.aStar(this);

		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				Node node = nodes[c][r];
				if (node.obstacle) {
					g2.setColor(Color.BLACK);
				} else {
					g2.setColor(Color.LIGHT_GRAY);
				}
				g2.fillOval(node.getX() + node.nodeWidth/4, node.getY() + node.nodeHeight/4, node.nodeWidth/2,
						node.nodeHeight/2);
			}
		}
		if (path != null) {
			Node temp = path.get(0);
			g2.setColor(Color.getHSBColor(4,3, 2));
			float[] dash = {(float) 0.3, (float) 0.4};
			for (Node node : path) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(temp.getX() + temp.nodeWidth/2, temp.getY() + temp.nodeHeight/2, node.getX() + node.nodeWidth/2, node.getY() + node.nodeHeight/2);
				temp = node;
			}
		}

	}
}
