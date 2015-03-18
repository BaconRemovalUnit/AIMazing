import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Maze extends JPanel{
	//first[] decides X, second[] decides Y
	boolean[][] maze;
	int X;
	int Y;
	int u;
	int Width;
	int Height;
	
	Maze(){	
	}

	Maze(int[][] grid, int u){
		this.u = u;
		this.X = grid.length;
		this.Y = grid[0].length;
		this.Width = X*u;
		this.Height = Y*u;
		maze = new boolean[grid[0].length][grid.length];
		for(int i=0; i< grid[0].length; i++){
			for(int j=0; j<grid.length; j++){
				if(grid[j][i]==0)
					maze[i][j] = false;
				else
					maze[i][j] = true;
			}
		}
	}
	
	Maze(boolean[][] grid, int u){
		this.u = u;
		this.X = grid.length;
		this.Y = grid[0].length;
		this.Width = X*u;
		this.Height = Y*u;
		maze = new boolean[grid.length][grid[0].length];
		for(int i=0; i< grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
					maze[i][j] = grid[i][j];
			}
		}
	}
	
	public void paintComponent (Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, X*u, Y*u);
		for(int i=0; i< maze.length; i++){
			for(int j=0; j<maze[0].length; j++){
					if(!maze[i][j]){
						g.setColor(Color.WHITE);
						g.fillRect(i*u, j*u, u, u);
					}
			}
		}
	}
	
	private boolean[][] twist(boolean[][] maze){
		return null;
	}
	
	
}
