package monster;

import javafx.scene.control.Label;

public class Fox extends Monster {
	public static final int DEFAULT_FOX_HP = 5;
	public static final int DEFAULT_FOX_SPEED = 20;
	public Fox(Label label) {
		super(DEFAULT_FOX_HP,DEFAULT_FOX_SPEED, label);
	}
}
