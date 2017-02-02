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

		JPanel panel = new Grid(300, 300, 50, 50, 0.3);
		Dimension d = new Dimension();
		d.setSize(300, 300);
		panel.setPreferredSize(d);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}
}
