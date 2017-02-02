package pf.main;

import java.util.ArrayList;

/**
 * Created by jwpra on 2/1/2017.
 */
public class Node {

    private boolean obstacle = false;
    private ArrayList<Node> neighbors = new ArrayList<Node>();

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean value) {
        obstacle = value;
    }

    public void setNeighbor(Node n) {
        neighbors.add(n);
    }

}
