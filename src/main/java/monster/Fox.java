package monster;

import javafx.scene.control.Label;

public class Fox extends Monster {
	public static final int DEFAULT_FOX_HP = 5;
	public static final int DEFAULT_FOX_SPEED = 40;
	public static final String NAME = "Fox" ; 
	public Fox(Label label, int num_frame) {
		super(NAME,DEFAULT_FOX_HP,DEFAULT_FOX_SPEED, label, num_frame);
	}
}
