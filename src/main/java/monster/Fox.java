package monster;

import javafx.scene.control.Label;
/**
 * 
 *	This is class for Fox 
 * @author Michael
 */
public class Fox extends Monster {
	/**
	 * The {@link DEFAULT_FOX_HP} is the default HP for fox
	 */
	public static final int DEFAULT_FOX_HP = 5;
	/**
	 * The {@link DEFAULT_FOX_SPEED} is the default speed for fox
	 */
	public static final int DEFAULT_FOX_SPEED = 40;
	/**
	 * The {@link NAME} is the name of fox
	 */
	public static final String NAME = "Fox" ; 
	
	/**
	 * This is the constructor of fox 
	 * @param label the label to display fox
	 * @param num_frame the number of frame of the game
	 */
	public Fox(Label label, int num_frame) {
		super(NAME,DEFAULT_FOX_HP,DEFAULT_FOX_SPEED, label, num_frame);
	}
}
