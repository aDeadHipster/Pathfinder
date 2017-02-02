package pf.main;

/**
 * Created by jwpra on 2/1/2017.
 */
public class Grid {

    private int height;
    private int width;

    private int colums;
    private int rows;

    public Node start;
    public Node goal;

    private Node[][] nodes = new Node[colums][rows];

    public Grid(int height, int width, int colums, int rows) {

        for (int c = 0; c < colums; c++) {
            for (int r = 0; r < rows; r++) {
                nodes[c][r] = new Node();
            }
        }

        start = nodes[0][0];
        goal = nodes[colums - 1][rows - 1];
    }

    public void assignNeighbours() {
        for (int c = 0; c < colums; c++) {
            for (int r = 0; r < rows; r++) {
                if (c == 0)
                nodes[c][r].setNeighbor();
            }
        }
    }

    public int getColums() {
        return colums;
    }

    public int getRows() {
        return rows;
    }

    public Node[][] getNodes() {
        return nodes;
    }
}
