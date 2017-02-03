package pf.main;

import javax.swing.*;
import java.awt.*;

/**
 * @author Justin Praas
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// Rules: Height and width should be a multiple of columns and rows
		int width = 800;
		int height = 800;
		JPanel panel = new Grid(width, height, 40, 40, .35);
		Dimension d = new Dimension();
		d.setSize(width, height);
		panel.setPreferredSize(d);
		panel.setBackground(Color.WHITE);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}
}
