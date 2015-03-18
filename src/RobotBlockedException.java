/*
 *filename: RobotBlockedException.java
 *Author:SuizhuShengqi
 *Date 2015Äê3ÔÂ17ÈÕ
 *Class:CSC172
 *Lab Session:MW	18:15-19:30
 */

public class RobotBlockedException extends Exception {
	public Throwable nestedThrowable = null;
	public RobotBlockedException() {
	super("Robot is Blocked!");
	}
}
