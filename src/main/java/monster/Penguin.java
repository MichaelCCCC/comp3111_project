package monster;

import javafx.scene.control.Label;

public class Penguin extends Monster {
	public static final int DEFAULT_PENGUIN_HP = 10;
	public static final int DEFAULT_PENGUIN_SPEED = 20;
	public static final int DEDAULT_PENGUIN_HEAL = 2;
	public static final String NAME = "Penguin" ; 
	public Penguin(Label label, int num_frame) {
		super(NAME, DEFAULT_PENGUIN_HP,DEFAULT_PENGUIN_HP,label, num_frame);
	}
	@Override
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
		label.setLayoutX(x-10);
		label.setLayoutY(y-10);
		if(HP < DEFAULT_PENGUIN_HP) {
			heal(DEDAULT_PENGUIN_HEAL);			
		}
	}
	@Override
	public void heal(int heal) {
		this.HP += heal;
	}
}
