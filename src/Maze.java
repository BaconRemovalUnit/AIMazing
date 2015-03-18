import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Maze extends JPanel{
	//first[] decides X, second[] decides Y
	boolean hasRobot;
	int[][] maze;
	int DELAY = 1000;
	int X;
	int Y;
	int u;
	int Width;
	int Height;
	int botX;
	int botY;
	
	private Timer timer;
	Robot bot = new Robot();
	
	
	Maze(){	
	}

	Maze(int[][] grid, int u){
		
		this.u = u;
		this.X = grid.length;
		this.Y = grid[0].length;
		this.Width = X*u;
		this.Height = Y*u;
		maze = new int[grid[0].length][grid.length];
		for(int i=0; i< grid[0].length; i++){
			for(int j=0; j<grid.length; j++){
				if(grid[j][i]==0)
					maze[i][j] = 0;
				else
					maze[i][j] = 1;
			}
		}
		
		bot.maze = this;
		botX = bot.X;
		botY = bot.Y;
		timer = new Timer(DELAY, new BoardListener());
		timer.start();
	}
	
	Maze(boolean[][] grid, int u){
		this.u = u;
		this.X = grid.length;
		this.Y = grid[0].length;
		this.Width = X*u;
		this.Height = Y*u;
		maze = new int[grid.length][grid[0].length];
		for(int i=0; i< grid.length; i++){
			for(int j=0; j<grid[0].length; j++){
				if(grid[i][j])
					maze[i][j] = 1;
				else
					maze[i][j] = 0;
			}
		}
		bot.maze = this;
		botX = bot.X;
		botY = bot.Y;
		timer = new Timer(DELAY, new BoardListener());
		timer.start();
	}
	
	public void paintComponent (Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, X*u, Y*u);
		for(int i=0; i< maze.length; i++){
			for(int j=0; j<maze[0].length; j++){
					if(maze[i][j]==0){
						g.setColor(Color.WHITE);
						g.fillRect(i*u, j*u, u, u);
					}
					else if(maze[i][j]==2){
						g.setColor(Color.GREEN);
						g.fillRect(i*u, j*u, u, u);
					}
			}
		}
	}
	
	public void begin(){
		this.bot.start();
	}


	public void placeBot(int x, int y) throws RobotBlockedException{
		if(maze[x][y]!=0)
			throw new RobotBlockedException();
		else
		{
			maze[x][y] = 2;
			hasRobot = true;
		}	
	
	}

	public boolean blockEmpty(int x, int y) {
		if(x<0 || y<0)
		return false;
		else
		return (maze[x][y]==0);
	}

	public void moveBot(int dX, int dY) throws RobotBlockedException{
		if(blockEmpty((botX+dX),(botY+dY))){
			maze[botX][botY] = 0;
			maze[botX+dX][botY+dY] = 2;
			this.botX = botX+dX;
			this.botY = botY+dY;
		}
		else
			throw new RobotBlockedException();
	}
	
	private class BoardListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			try {
				bot.update();
			} catch (InterruptedException | RobotBlockedException e1) {
				e1.printStackTrace();
			}
			repaint();
		}
	}
}
