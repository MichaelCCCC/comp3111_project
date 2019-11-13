package monster;

import javafx.scene.control.Label;



public class Monster {
	//constant
	static final int DEFAULT_X = 20;
	static final int DEFAULT_Y = 20;
	static public enum Direction {UP, DOWN, RIGHT};
	
	//private data member
	private int HP;
	private int speed;
	private int x;
	private int y;
	private Label label;
	private Direction direction;
	
	public Monster(int _HP, int _speed, Label _label) {
		HP = _HP;
		speed = _speed;
		x = DEFAULT_X;
		y = DEFAULT_Y;
		label = _label;
		direction = Direction.DOWN;
	}

	public Monster() {
		// TODO Auto-generated constructor stub
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
	public void setDirection(Direction _direction) {
		direction = _direction;
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

	public void move() {
		// TODO Auto-generated method stub
		
	}

	
	
}

