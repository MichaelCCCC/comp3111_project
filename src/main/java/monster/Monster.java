package monster;

import javafx.scene.control.Label;
import sample.MyController;



public class Monster {
	//constant
	static final int DEFAULT_X = 20;
	static final int DEFAULT_Y = 20;
	static public enum Direction {UP, DOWN, RIGHT};
	static public enum Status{ALIVE,DEAD};
	//private data member
	protected int HP;
	private int speed;
	protected int x;
	protected int y;
	protected Label label;
	private Direction direction;
	private Status status;
	public String name = null ; 
	
	public Monster(String id,int _HP, int _speed, Label _label) {
		name = id ; 
		HP = _HP;
		speed = _speed;
		x = DEFAULT_X;
		y = DEFAULT_Y;
		label = _label;
		direction = Direction.DOWN;
		status = Status.ALIVE;
	}



	public int getHP() {
		return HP;
	}
	public int getSpeed() {
		return speed;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Label getLabel() {
		return label;
	}
	public Direction getDirection() {
		return direction;
	}
	public Status getStatus() {
		return status;
	}
	public void setDirection(Direction _direction) {
		direction = _direction;
	}
	public void setStatus(Status _status) {
		status = _status;
	}
	public void damage(int damage) {
		HP -= damage;
	}
	public void slow_down(int offset) {
		speed -= offset;
	}

	public void move(int _x, int _y) {
		x = _x;
		y = _y;
		label.setLayoutX(x-10);
		label.setLayoutY(y-10);

	}

	public String getTooltip() {
		return "HP :" + HP;
	}

	public boolean reachEndZone() {
		if(x >= MyController.zones.get(1).getLayoutX())
			return true ; 
		return false;
	}
	public void heal(int heal) {
		
	}
}

