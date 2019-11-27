package monster;

import javafx.scene.control.Label;

public class Unicorn extends Monster {
	public static final int DEFAULT_UNICORN_HP = 20;
	public static final int DEFAULT_UNICRON_SPEED = 20;
	public static String NAME = "UNICORN" ; 
	
	public Unicorn(Label label, int num_frame) {
		super(NAME, DEFAULT_UNICORN_HP,DEFAULT_UNICRON_SPEED, label, num_frame);
	}
}
