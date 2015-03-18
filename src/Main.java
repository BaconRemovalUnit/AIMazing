import java.awt.Color;

import javax.swing.JFrame;



public class Main {
	public static void main(String[] args)
	{
		int x = 300;
		int y = 300;
		int unit = 10;
		int[][] input = {
				{1,0,0,1,1},
				{1,0,0,0,1},
				{1,1,1,1,1}};
		
		Maze maze = new Maze(input, unit);
		
		JFrame frame = new JFrame("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		frame.getContentPane().add(maze);
		frame.setBackground(Color.BLACK);
		frame.setSize(x,y);
		frame.setVisible(true);
	}
}
