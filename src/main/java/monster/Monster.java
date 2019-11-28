package monster;

import javafx.scene.control.Label;
import sample.MyController;

/**
 * 
 * @author Michael
 * 
 * This is Monster class. It is used to store all the information of a monster
 *
 */

public class Monster {
	//constant
	/**
	 * The {@link DEFAULT_X} is the starting x coordinate of monster.
	 */
	static final int DEFAULT_X = 20;
	static final int DEFAULT_Y = 20;
	static public enum Direction {UP, DOWN, RIGHT};
	static public enum Status{ALIVE,DEAD};
	//private data member
	/**
	 * The {@link HP} is the health of monster.
	 * The {@link label} is the label of monster.
	 */
	protected int HP;
	/**
	 * The {@link speed} is the speed of monster.
	 */
	private int speed;
	/**
	 * The {@link x} is the x coordinate of monster.
	 */
	protected int x;
	/**
	 * The {@link y} is the x coordinate of monster.
	 */
	protected int y;
	/**
	 * The {@link label} is the label of monster.
	 */
	protected Label label;
	/**
	 * The {@link direction} is the direction of monster.
	 */
	private Direction direction;
	/**
	 * The {@link status} is the status of monster.
	 */
	private Status status;
	/**
	 * The {@link name} is the name of monster.
	 */
	public String name = null ; 
	
	/**
	 * The constructor of monster
	 * @param id 	the type of monster
	 * @param _HP	the health of monster
	 * @param _speed	the displacement in every move
	 * @param _label	the label to show the monster image on the arena
	 * @param num_frame		the number of num_frame to generate stronger monster over time
	 */
	public Monster(String id,int _HP, int _speed, Label _label, int num_frame) {
		name = id ; 
		HP = _HP + num_frame/5;
		speed = _speed;
		x = DEFAULT_X;
		y = DEFAULT_Y;
		label = _label;
		direction = Direction.DOWN;
		status = Status.ALIVE;
	}


	/**
	 * This function is used to get the HP of monster
	 * @return HP	the health of monster
	 */
	public int getHP() {
		return HP;
	}
	/**
	 * This function is used to get the speed of monster
	 * @return speed	the displacement in every move
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * This function is used to get the X coordinate of monster
	 * @return x	the X coordinate 
	 */
	public int getX() {
		return x;
	}
	/**
	 * This function is used to get the Y coordinate of monster
	 * @return	y	the Y coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * This function is used to get the label of monster
	 * @return	label 	the label of the monster	
	 */
	public Label getLabel() {
		return label;
	}
	/**
	 * This function is used to get the direction of the monster
	 * @return	direction  the direction of monster
	 */
	public Direction getDirection() {
		return direction;
	}
	/**
	 * This function is used to get the Status of monster
	 * @return	status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * This function is used to change the direction of monster
	 * @param _direction	the new direction of monster
	 */
	public void setDirection(Direction _direction) {
		direction = _direction;
	}
	/**
	 * This function is used to change the status of monster
	 * @param _status	the new status of monster
	 */
	public void setStatus(Status _status) {
		status = _status;
	}
	/**
	 * This function is used to decrease the HP of monster
	 * @param damage	the damage done to monster
	 */
	public void damage(int damage) {
		HP -= damage;
	}
	/**
	 * This function is used to decrease the speed of monster
	 * @param offset	the offset of speed
	 */
	public void slow_down(int offset) {
		speed -= offset;
	}
	/**
	 * This function is used to move the monster in the arena
	 * @param _x	the new x coordinate
	 * @param _y	the new y coordinate
	 */
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
		label.setLayoutX(x-10);
		label.setLayoutY(y-10);

	}
	/**
	 * This function is used to get the HP info of monster
	 * @return "HP: "+ HP
	 */
	public String getTooltip() {
		return "HP :" + HP;
	}
	/**
	 * This function is used to determine whether a monster has reached the end zone
	 * @return	boolean
	 */
	public boolean reachEndZone() {
		if(x >= MyController.zones.get(1).getLayoutX())
			return true ; 
		return false;
	}
	/**
	 * This function is used to increase the HP of monster
	 * @param heal	amount to heal
	 */
	public void heal(int heal) {
		
	}
}

