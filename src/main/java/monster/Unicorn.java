package monster;

import javafx.scene.control.Label;
/**
 * This is a class for Unicorn
 * @author Michael
 *
 */
public class Unicorn extends Monster {
	/**
	 * The {@link DEFAULT_UNICORN_HP} is the default HP of unicorn
	 */
	public static final int DEFAULT_UNICORN_HP = 20;
	/**
	 * The {@link DEFAULT_UNICRON_SPEED} is the default speed of unicorn
	 */
	public static final int DEFAULT_UNICRON_SPEED = 20;
	/**
	 * The {@link NAME} is the name of unicorn
	 */
	public static final String NAME = "UNICORN" ; 
	
	/**
	 * This is the constructor of unicorn
	 * @param label the label to display unicorn
	 * @param num_frame the number of frame of the game
	 */
	public Unicorn(Label label, int num_frame) {
		super(NAME, DEFAULT_UNICORN_HP,DEFAULT_UNICRON_SPEED, label, num_frame);
	}
}
