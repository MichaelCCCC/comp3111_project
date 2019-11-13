package monster;

import javafx.scene.control.Label;

public class Unicorn extends Monster {
	public static final int DEFAULT_UNICORN_HP = 20;
	public static final int DEFAULT_UNICRON_SPEED = 5;
	
	public Unicorn(Label label) {
		super(DEFAULT_UNICORN_HP,DEFAULT_UNICRON_SPEED, label);
	}
}
