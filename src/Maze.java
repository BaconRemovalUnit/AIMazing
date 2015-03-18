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
	int DELAY = 200;
	int X;
	int Y;
	int u;
	int Width;
	int Height;
	int botX;
	int botY;
	int goalX;
	int goalY;
	
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
				else if(grid[j][i]==9)
					maze[i][j] = 9;
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
					else if(maze[i][j]==9){
						g.setColor(Color.GREEN);
						g.fillRect(i*u, j*u, u, u);
					}
					else if(maze[i][j]==2){
						g.setColor(Color.RED);
						g.fillRect(i*u, j*u, u, u);
					}
			}
		}
	}
	
	public void setFPS(int FPS){
		DELAY = 1000/FPS;
		timer.setDelay(DELAY);
	}
	
	public void begin(){
		this.bot.start();
	}


	public void placeBot(int x, int y) {
		if(maze[x][y]!=0)
			System.err.println("Can't place bot at "+x+" "+y+"!");
		else{
			maze[x][y] = 2;
			hasRobot = true;
		}	
	
	}

	public boolean blockEmpty(int x, int y) {
		if(x<0 || y<0 || x==X || y==Y)
		return false;
		else
		return (maze[x][y]==0||maze[x][y]==9);
	}

	public void moveBot(int dX, int dY){
		if(blockEmpty((botX+dX),(botY+dY))){
			if(maze[botX+dX][botY+dY]==9)
				this.bot.Activated = false;
			maze[botX][botY] = 0;
			maze[botX+dX][botY+dY] = 2;
			bot.X = botX+dX;
			bot.Y = botY+dY;
			this.botX = botX+dX;
			this.botY = botY+dY;
		}
		else
			System.err.println("Can't place bot at "+( botX+dX)+" "+(botY+dY)+"!");
	}
	
	private class BoardListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			try {
				bot.update();
			} catch (InterruptedException  e1) {
				e1.printStackTrace();
			}
			repaint();
		}
	}
}
