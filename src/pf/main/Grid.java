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

		assignNeighbours();
	}

	private void assignNeighbours() {
		for (int c = 1; c < columns - 1; c++) {
			for (int r = 1; r < rows - 1; r++) {
				nodes[c][r].setAllNeighbors(nodes[c][r]);
			}
		}

		for (int i = 1; i < columns - 1; i++) {

			// top row
			nodes[i][0].setNeighbor(nodes[i][1]);
			nodes[i][0].setNeighbor(nodes[i - 1][0]);
			nodes[i][0].setNeighbor(nodes[i + 1][0]);
			nodes[i][0].setNeighbor(nodes[i - 1][1]);
			nodes[i][0].setNeighbor(nodes[i + 1][1]);

			// bottom row
			nodes[i][rows - 1].setNeighbor(nodes[i][rows - 2]);
			nodes[i][rows - 1].setNeighbor(nodes[i - 1][rows - 1]);
			nodes[i][rows - 1].setNeighbor(nodes[i + 1][rows - 1]);
			nodes[i][rows - 1].setNeighbor(nodes[i - 1][rows - 2]);
			nodes[i][rows - 1].setNeighbor(nodes[i + 1][rows - 2]);

			// left column
			nodes[0][i].setNeighbor(nodes[1][i]);
			nodes[0][i].setNeighbor(nodes[0][i - 1]);
			nodes[0][i].setNeighbor(nodes[0][i + 1]);
			nodes[0][i].setNeighbor(nodes[1][i - 1]);
			nodes[0][i].setNeighbor(nodes[1][i + 1]);

			// right column
			nodes[columns - 1][i].setNeighbor(nodes[columns - 2][i]);
			nodes[columns - 1][i].setNeighbor(nodes[columns - 1][i - 1]);
			nodes[columns - 1][i].setNeighbor(nodes[columns - 1][i + 1]);
			nodes[columns - 1][i].setNeighbor(nodes[columns - 2][i - 1]);
			nodes[columns - 1][i].setNeighbor(nodes[columns - 2][i + 1]);
		}

		nodes[0][0].setNeighbor(nodes[1][1]);
		nodes[0][0].setNeighbor(nodes[0][1]);
		nodes[0][0].setNeighbor(nodes[1][0]);

		nodes[columns - 1][0].setNeighbor(nodes[columns - 2][1]);
		nodes[columns - 1][0].setNeighbor(nodes[columns - 2][0]);
		nodes[columns - 1][0].setNeighbor(nodes[columns - 1][1]);

		nodes[columns - 1][rows - 1].setNeighbor(nodes[columns - 2][rows - 1]);
		nodes[columns - 1][rows - 1].setNeighbor(nodes[columns - 1][rows - 2]);
		nodes[columns - 1][rows - 1].setNeighbor(nodes[columns - 2][rows - 2]);

		nodes[0][rows - 1].setNeighbor(nodes[1][rows - 1]);
		nodes[0][rows - 1].setNeighbor(nodes[1][rows - 2]);
		nodes[0][rows - 1].setNeighbor(nodes[0][rows - 2]);

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

		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		Pathfinder pathFinder = new Pathfinder();
		ArrayList<Node> path = pathFinder.aStar(this);


		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				Node node = nodes[c][r];
				if (node.obstacle) {
					g2.setColor(Color.BLACK);
				} else {
					g2.setColor(Color.WHITE);
				}
				g2.fillRect(node.getX(), node.getY(), node.nodeWidth, node.nodeHeight);
			}
		}
		if (path != null) {
			g2.setColor(Color.RED);
			for (Node node : path) {
				g2.fillRect(node.getX(), node.getY(), node.nodeWidth,
						node.nodeHeight);
			}
		}

	}
}
