package tower;
import java.lang.Math; 
import java.lang.String;
import java.util.List;
import monster.Monster;
import monster.Monster.Status;
import sample.MyController;

import java.util.ArrayList;

/**
 * Basic Tower
 */
public class Tower {
	/**
	 * {@link NAME} is the default name for basic tower
	 */
	public static final String NAME = "Basic Tower"; 
	/**
	 * The tower's name
	 */
	public String name = NAME; 
	/**
	 * {@link x} is the x coordinate of the tower
	 */
	protected int x;
	/**
	 * {@link y} is the y coordinate of the tower
	 */
	protected int y;
	/**
	 * Attack power of the tower
	 */
	protected int attack_power;
	/**
	 * Cost of building the tower
	 */
	protected int building_cost;
	/**
	 * Cost of upgrading the tower
	 */
	protected int upgrade_cost;
	/**
	 * Shooting range of the tower
	 */
	protected int shooting_range;
	/**
	 * The increase attack power of the tower after upgrade
	 */
	protected int upgrade_diff;
	/**
	 * Current tier of the tower
	 */
	protected int tier;
	/**
	 * The description of the tower
	 */
	protected String comment;
	/**
	 * The status of the tower DEAD or ALIVE
	 */
	protected boolean is_destroyed = false;
	/**
	 * The default value for tower information
	 */
	public static TowerInformation BasicTowerInit = new TowerInformation(NAME, 10, 100, 100, 100, 10, 1, "This is Basic Tower");
	
	/**
	 * Constructor for Tower class
	 * @param name	{@link name}, Tower's name
	 * @param x {@link x}, Tower's x coordinate
	 * @param y {@link y}, Tower's y coordinate
	 */
	public Tower(String name, int x , int y) {
		this(x, y) ; 
		this.name = name ;

	}
	
	/**
	 * Constructor for Tower class
	 * @param x {@link x}, Tower's x coordinate
	 * @param y {@link y}, Tower's y coordinate
	 */
	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
		setAttributes(BasicTowerInit.attack_power,BasicTowerInit.building_cost,BasicTowerInit.upgrade_cost,BasicTowerInit.shooting_range, BasicTowerInit.upgrade_diff,BasicTowerInit.tier,BasicTowerInit.comment) ; 
	}
	
	
	//	Getter Functions
	
	/**
	 * Get X coordinate of the Tower
	 * @return x	{@link x}, Tower's X coordinate
	 * 
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get Y coordinate of the Tower
	 * @return y	{@link y}, Tower's Y coordinate
	 * 
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Check if tower is destroyed
	 * @return is_destroyed	{@link is_destroyed}, Tower's destroyed status
	 */
	boolean isDestroyed() {
		return is_destroyed;
	}
	
	/**
	 * Get all information of a tower
	 * @return TowerInformation	Tower's information
	 */
	public TowerInformation getInfo() {
		return new TowerInformation(name,attack_power, building_cost, upgrade_cost, shooting_range, upgrade_diff, tier, comment);
	}
	
	/**
	 * Set attack power
	 * @param attack_power	new attack power	
	 */
	void setAttackPower(int attack_power) {
		this.attack_power = attack_power;
	}
	
	/**
	 * Set building cost
	 * @param building_cost	new building cost	
	 */
	void setBuildingCost(int building_cost) {
		this.building_cost = building_cost;
	}
	
	/**
	 * Set upgrade cost
	 * @param upgrade_cost	new upgrade cost	
	 */
	void setUpgradeCost(int upgrade_cost) {
		this.upgrade_cost = upgrade_cost;
	}
	
	/**
	 * Set increase in attack power after an upgrade
	 * @param upgrade_diff	increase in attack power after an upgrade	
	 */
	void setUpgradeDiff(int upgrade_diff) {
		this.upgrade_diff = upgrade_diff;
	}
	
	/**
	 * Set tier of the tower
	 * @param tier	new tier	
	 */
	void setTier(int tier) {
		this.tier = tier;
	}
	
	/**
	 * Set description for the tower
	 * @param tier	new description	
	 */
	void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * Set shooting range of the tower
	 * @param shooting_range	new shooting range
	 */
	void setShootingRange(int shooting_range) {
		this.shooting_range = shooting_range;
	}
	
	/**
	 * Set all information of a tower
	 * @param attack_power	New attack power
	 * @param building_cost	New building cost
	 * @param upgrade_cost	New upgrade cost
	 * @param shooting_range	New shooting range
	 * @param upgrade_diff	New difference in attack power after upgrade
	 * @param tier	New tier
	 * @param comment	New comment
	 */
	void setAttributes(int attack_power, int building_cost, int upgrade_cost, int shooting_range, int upgrade_diff, int tier, String comment) {
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
		this.upgrade_diff = upgrade_diff;
		this.tier = tier;
		this.comment = comment;
	}
	
	/**
	 * Find distance between this tower and the specified monster
	 * @param enemy	The monster to find the distance from
	 * @return distance The distance between this tower and the specified monster
	 */
	protected double distance(Monster enemy) {
		return Math.sqrt(Math.pow(enemy.getX() - x, 2) + Math.pow(enemy.getY() - y, 2));
	}
	
	/**
	 * Find distance between two monsters
	 * @param enemy1	The first enemy to find the distance from
	 * @param enemy2	The second enemy to find the distance from
	 * @return distance The distance between the two monsters
	 */
	protected double distance(Monster enemy1, Monster enemy2) {
		return Math.sqrt(Math.pow(enemy1.getX() - enemy2.getX(), 2) + Math.pow(enemy1.getY() - enemy2.getY(), 2));
	}
	
	/**
	 * Find an monster closest to this tower
	 * @return closestEnemy	The closest monster to this tower
	 */
	public Monster findClosestEnemy() {
		Monster closestEnemy = null;
		double closestEnemyDistance = Double.MAX_VALUE;
		for(int i=0; i<MyController.monsters.size(); ++i) {
			double dist = distance(MyController.monsters.get(i));
			if(MyController.monsters.get(i).getStatus() == Status.ALIVE) {				
				if(dist < closestEnemyDistance) {
					closestEnemyDistance = distance(MyController.monsters.get(i));
					closestEnemy = MyController.monsters.get(i);
				}
				else if(dist == closestEnemyDistance) {
					if(MyController.monsters.get(i).getX() > closestEnemy.getX()) {
						closestEnemyDistance = distance(MyController.monsters.get(i));
						closestEnemy = MyController.monsters.get(i);
					}
				}
			}
		}
		return closestEnemy;
	}
	
	/**
	 * Find monsters to be targeted
	 * @return targetedMonster	The list of the targeted monsters
	 */
	public List<Monster> getTargetedMonster() {
		List<Monster> targetedMonster = new ArrayList<Monster>();
		Monster closestEnemy = findClosestEnemy();
		if (closestEnemy == null )
			return null ; 
		if(distance(closestEnemy) <= shooting_range) {			
			targetedMonster.add(closestEnemy);
		}
		if(targetedMonster.size() == 0 )
			targetedMonster = null ; 
		return targetedMonster;
	}
	
	/**
	 * Shoot targeted monsters
	 * @return targetedMonster	The list of the monsters shot
	 */
	public List<Monster> shoot() {
		List<Monster> targetedMonster = getTargetedMonster();
		if(targetedMonster == null) return targetedMonster;
		for(int i=0; i<targetedMonster.size(); ++i) {
			targetedMonster.get(i).damage(attack_power) ; 
		}
		return targetedMonster; 
	}
	
	/**
	 * Upgrade the tower
	 */
	public void upgrade() {
		attack_power += upgrade_diff;
		tier++;
	}
	
	/**
	 * Destroy the tower
	 */
	public void destroy() {
		is_destroyed = true;
	}

}