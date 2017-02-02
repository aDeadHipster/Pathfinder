package pf.main;

import java.util.*;
import pf.main.Grid.Node;

/**
 * @author Justin Praas
 */
public class Pathfinder {

    public ArrayList<Node> aStar(Grid grid) {
        Node current;

        Set<Node> closedSet = new HashSet<>();
        Set<Node> openSet = new HashSet<>();
        openSet.add(grid.start);

        Map<Node, Node> cameFrom = new HashMap<>();

        // The cost for each node of getting from the start
        // node to the goal (initialize all nodes with infinity)
        Map<Node, Double> gScore = new HashMap<>();
        Map<Node, Double> fScore = new HashMap<>();
        for (int c = 0; c < grid.getColumns(); c++) {
            for (int r = 0; r < grid.getRows(); r++) {
                gScore.put(grid.getNodes()[c][r], (double)Integer.MAX_VALUE);
                fScore.put(grid.getNodes()[c][r], (double)Integer.MAX_VALUE);
            }
        }
        gScore.put(grid.start, 0.0);
        fScore.put(grid.start, heuristicCost(grid.start, grid.goal)) ;

        while (openSet.size() > 0) {
            current = getLowestValue(openSet, fScore);

            if (current.equals(grid.goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : current.getNeighbors()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double temp_gScore = gScore.get(current) + 1;

                if (!closedSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (temp_gScore >= gScore.get(neighbor)) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                gScore.put(neighbor, temp_gScore);
                fScore.put(neighbor, gScore.get(neighbor) + heuristicCost(neighbor, grid.goal));

            }
        }
        return null;
    }

	public double heuristicCost(Node from, Node to) {
		return Math.sqrt(
				Math.pow(Math.abs(from.getC() - to.getC()), 2) +
	            Math.pow(Math.abs(from.getR() - to.getR()), 2));
    }

    public Node getLowestValue(Set<Node> set, Map<Node, Double> map) {
        Node resultNode = null;
        double lowest = (double) Integer.MAX_VALUE;

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

    public ArrayList<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
	    ArrayList<Node> totalPath = new ArrayList<>();
	    totalPath.add(current);

	    while (cameFrom.containsKey(current)) {
		    current = cameFrom.get(current);
		    totalPath.add(current);
	    }

	    return totalPath;
    }
}
