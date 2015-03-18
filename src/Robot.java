import javax.swing.JPanel;

public class Robot extends JPanel{

	boolean Activated = true;
	int X;
	int Y;
	String ID = this.toString();
	Maze maze;

	
	Robot(){
		this.X = 0;
		this.Y = 0;
	}
	
	Robot(int X, int Y, Maze maze){
		this.X = X;
		this.Y = Y;
		this.maze = maze;
	}
	
    public void start() {
    	Activated = true;
    }

	public void update() throws InterruptedException {
		if(!this.maze.hasRobot)
		this.maze.placeBot(X,Y);
		if(Activated)
		logic();
		else
		System.out.println("Goal!");
	}
	
	public boolean canMove(String direction){
		String dir = direction.toUpperCase();
		boolean ans;
		switch(dir){
		case "UP": 		ans = this.maze.blockEmpty(X, Y-1);  break;
		case "DOWN": 	ans = this.maze.blockEmpty(X, Y+1);  break;
		case "RIGHT":  	ans = this.maze.blockEmpty(X+1, Y);  break;
		case "LEFT":  	ans = this.maze.blockEmpty(X-1, Y);  break;
		default: throw new IllegalArgumentException();
		}
		return ans;
		}

	public void move(String direction){
		String dir = direction.toUpperCase();
		switch(dir){
		case "UP": 		this.maze.moveBot(0, -1);  break;
		case "DOWN": 	this.maze.moveBot(0, 1);  break;
		case "RIGHT":  	this.maze.moveBot(1, 0);  break;
		case "LEFT":  	this.maze.moveBot(-1, 0);  break;
		default: throw new IllegalArgumentException();
		}
	}
	
	private void logic(){
		String[] dir = new String[]{"up","down","left","right"};
		this.move( dir[(int)(Math.random()*4)]);
		
	}
}
