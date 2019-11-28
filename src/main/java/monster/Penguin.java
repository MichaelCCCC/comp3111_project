package monster;

import javafx.scene.control.Label;
/**
 * This is a class for Penguin
 * @author Michael
 *
 */
public class Penguin extends Monster {
	/**
	 * The {@link DEFAULT_PENGUIN_HP} is the default HP of penguin
	 */
	public static final int DEFAULT_PENGUIN_HP = 10;
	/**
	 * The {@link DEFAULT_PENGUIN_SPEED} is the default speed of penguin
	 */
	public static final int DEFAULT_PENGUIN_SPEED = 20;
	/**
	 * The {@link DEDAULT_PENGUIN_HEAL} is the amount of HP for penguin to heal
	 */
	public static final int DEDAULT_PENGUIN_HEAL = 2;
	/**
	 * The {@link NAME} is the name of penguin
	 */
	public static final String NAME = "Penguin" ; 
	/**
	 * This is the constructor of penguin
	 * @param label the label to display penguin
	 * @param num_frame the number of frame in the game
	 */
	public Penguin(Label label, int num_frame) {
		super(NAME, DEFAULT_PENGUIN_HP,DEFAULT_PENGUIN_HP,label, num_frame);
	}
	@Override
	/**
	 * This is the function to move penguin with heal
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
		label.setLayoutX(x-10);
		label.setLayoutY(y-10);
		if(HP < DEFAULT_PENGUIN_HP) {
			heal(DEDAULT_PENGUIN_HEAL);			
		}
	}
	/**
	 * This is the function to heal penguin
	 * @param heal amount of HP to heal
	 */
	@Override
	public void heal(int heal) {
		this.HP += heal;
	}
}
