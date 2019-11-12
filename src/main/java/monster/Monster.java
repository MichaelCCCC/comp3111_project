package monster;

public class Monster {
	private int HP;
	private int speed;
	private int x;
	private int y;
	
	public static final int DEFAULT_X = 10;
	public static final int DEFAULT_Y = 10;
	
	public Monster(int _HP, int _speed) {
		HP = _HP;
		speed = _speed;
		x = DEFAULT_X;
		y = DEFAULT_Y;
	}
	public int getHP() {
		return HP;
	}
	
}
