package pf.main;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by jwpra on 2/1/2017.
 */
public class Pathfinder {

    public ArrayList<Node> aStar(Grid grid) {
        ArrayList<Node> resultPath = new ArrayList<Node>();
        Node current;

        Set<Node> closedSet = new HashSet<Node>();
        Set<Node> openSet = new HashSet<Node>();
        openSet.add(grid.start);

        Map<Node, Node> cameFrom = new HashMap<Node, Node>();

        // The cost for each node of getting from the start
        // node to the goal (initialize all nodes with infinity)
        Map<Node, Integer> gScore = new HashMap<Node, Integer>();
        Map<Node, Integer> fScore = new HashMap<Node, Integer>();
        for (int c = 0; c < grid.getColums(); c++) {
            for (int r = 0; r < grid.getRows(); r++) {
                gScore.put(grid.getNodes()[c][r], Integer.MAX_VALUE);
                fScore.put(grid.getNodes()[c][r], Integer.MAX_VALUE);
            }
        }
        gScore.put(grid.start, 0);
        fScore.put(grid.start, heuristic_cost(grid.start, grid.goal)) ;

        while (openSet.size() > 0) {
            current = getLowestValue(openSet, fScore);

            if (current.equals(grid.goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            // For each neighbor...
        }
    }

    public Node getLowestValue(Set<Node> set, Map<Node, Integer> map) {
        Node resultNode = null;
        int lowest = Integer.MAX_VALUE;

        Iterator<Node> it = set.iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (resultNode == null || map.get(n) < lowest) {
                resultNode = n;
                lowest = map.get(n);
            }
        }
        return resultNode;
    }
}
