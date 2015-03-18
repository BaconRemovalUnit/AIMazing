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

	public void update() throws InterruptedException, RobotBlockedException {
		if(!this.maze.hasRobot)
		this.maze.placeBot(X,Y);
		logic();
	}
	
	public boolean canMoveLeft(){
	 return maze.blockEmpty(X-1,Y);
	}
	
	public boolean canMoveRight(){
		 return maze.blockEmpty(X+1,Y);
		}
	
	public boolean canMoveUp(){
		 return maze.blockEmpty(X,Y-1);
		}
	
	public boolean canMoveDown(){
		 return maze.blockEmpty(X,Y+1);
		}

	public void move(String direction) throws RobotBlockedException{
		String dir = direction.toUpperCase();
		switch(dir){
		case "UP": 		this.maze.moveBot(0, -1);  break;
		case "DOWN": 	this.maze.moveBot(0, 1);  break;
		case "RIGHT":  	this.maze.moveBot(1, 0);  break;
		case "LEFT":  	this.maze.moveBot(-1, 0);  break;
		default: throw new IllegalArgumentException();
		}
	}


	private void logic() throws RobotBlockedException {
		this.move("RIGHT");
		
	}
}
