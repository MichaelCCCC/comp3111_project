package monster;

import javafx.scene.control.Label;

public class Penguin extends Monster {
	public static final int DEFAULT_PENGUIN_HP = 10;
	public static final int DEFAULT_PENGUIN_SPEED = 10;
	
	public Penguin(Label label) {
		super(DEFAULT_PENGUIN_HP,DEFAULT_PENGUIN_HP,label);
	}
	
}
