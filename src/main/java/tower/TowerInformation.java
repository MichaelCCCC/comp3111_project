package tower;

/**
 * A class that represents a tower's information
 */
public class TowerInformation {
	/**
	 * The tower's name
	 */
	public String name ; 
	/**
	 * Attack power of the tower
	 */
	public int attack_power;
	/**
	 * Cost of building the tower
	 */
	public int building_cost;
	/**
	 * Cost of upgrading the tower
	 */
	public int upgrade_cost;
	/**
	 * Shooting range of the tower
	 */
	public int shooting_range;
	/**
	 * The increase attack power of the tower after upgrade
	 */
	public int upgrade_diff;
	/**
	 * Current tier of the tower
	 */
	public int tier;
	/**
	 * The description of the tower
	 */
	public String comment;
	
	/**
	 * Constructor for TowerInformation class
	 * @param name			Name of the tower
	 * @param attack_power	New attack power
	 * @param building_cost	New building cost
	 * @param upgrade_cost	New upgrade cost
	 * @param shooting_range	New shooting range
	 * @param upgrade_diff	New difference in attack power after upgrade
	 * @param tier	New tier
	 * @param comment	New comment
	 */
	TowerInformation(String name , int attack_power, int building_cost, int upgrade_cost, int shooting_range, int upgrade_diff, int tier, String comment) {
		this.name = name ; 
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
		this.upgrade_diff = upgrade_diff;
		this.tier = tier;
		this.comment = comment;
	}

}