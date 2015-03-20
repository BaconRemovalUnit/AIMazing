import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	int goalX;
	int goalY;
	int pathLength = 20;
	
	private Timer timer;
	ArrayList<int[]> path = new ArrayList<int[]>();
	Robot bot = new Robot();
	
	
	Maze(){	
	}

	Maze(int[][] grid, int u){
		
		this.u = u;
		this.Y = grid.length;
		this.X = grid[0].length;
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
		//draw path
		for(int[] cord: path){
			if(!(cord[0]==bot.X&&cord[1]==bot.Y)){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(cord[0]*u, cord[1]*u, u, u);
			}
		}
	}
	
	public void setFPS(double FPS){
		DELAY = (int) (1000.0/FPS);
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
			this.bot.X = x;
			this.bot.Y = y;
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
		if(blockEmpty((bot.X+dX),(bot.Y+dY))){
			if(maze[bot.X+dX][bot.Y+dY]==9)
				this.bot.Activated = false;
			
			maze[bot.X][bot.Y] = 0;
			maze[bot.X+dX][bot.Y+dY] = 2;
			bot.X = bot.X+dX;
			bot.Y = bot.Y+dY;
			path.add(new int[]{bot.X,bot.Y});
		}
		else
			System.err.println("Can't move bot to "+( bot.X+dX)+" "+(bot.Y+dY)+"!");
	}
	
	private class BoardListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {	
			try {
				bot.update();
				if(path.size()>pathLength){
					 path.remove(0);
				}
			} catch (InterruptedException  e1) {
				e1.printStackTrace();
			}
			repaint();
		}
	}
}
