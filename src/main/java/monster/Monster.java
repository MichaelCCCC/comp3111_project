package monster;


public class Monster {
	//constant
	static final int DEFAULT_X = -1;
	static final int DEFAULT_Y = 0;
	
	//private data member
	private int HP;
	private int speed;
	private int x;
	private int y;
	
	public Monster(int _HP, int _speed) {
		HP = _HP;
		speed = _speed;
		x = DEFAULT_X;
		y = DEFAULT_Y;
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
	public void damage(int damage) {
		HP -= damage;
	}
	public void slow_down(int offset) {
		speed -= offset;
	}

	
	
}

