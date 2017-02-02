package pf.main;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jwpra on 2/1/2017.
 */
public class Node {

    private int x;


    private int y;
    private boolean obstacle = false;
    private ArrayList<Node> neighbors = new ArrayList<Node>();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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

}
