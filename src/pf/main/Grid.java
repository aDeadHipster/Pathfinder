package pf.main;

/**
 * @author Justin Praas
 */
public class Grid {

    private int height;
    private int width;

    private int columns;
    private int rows;

    public Node start;
    public Node goal;

    private Node[][] nodes;

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
