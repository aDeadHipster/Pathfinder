package pf.main;

/**
 * @author Justin Praas
 */
public class Main {

	public static void main(String[] args) {

		Grid grid = new Grid(300, 300, 2, 2);
		Pathfinder pathFinder = new Pathfinder();
		System.out.print(pathFinder.aStar(grid));

	}
}
