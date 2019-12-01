package tower;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import monster.Monster.Status;
import sample.MyController;

/**
 * Catapult
 */
public class Catapult extends Tower{
	/**
	 * The default name for catapult
	 */
	public static final String NAME = "Catapult" ;
	
	/**
	 * The close limit of shooting range for catapult
	 */
	public int shortDistance = 50 ; 
	
	/**
	 * The far limit of shooting range for catapult
	 */
	public int longDistance = 150 ; 
	
	/**
	 * Cooldown state, 1 for cooling down, 0 for not
	 */
	public int cooldown = 0;
	
	/**
	 * The default far limit of shooting range for catapult
	 */
	static final int INIT_LONG_DISTANCE = 150 ; 
	
	/**
	 * The default far description of shooting range for catapult
	 */
	static final String NOTE = "All monsters placed at the\n radius of 25px of where\n the stone drop receive\n damage." ; 
	
	/**
	 * The default value for Catapult information
	 */
	public static TowerInformation CatapultInit = new TowerInformation(NAME, 10, 100, 100, INIT_LONG_DISTANCE, 10, 1, NOTE);
	
	/**
	 * Catapult constructor
	 * @param x	X coordinate of the tower
	 * @param y	Y coordinate of the tower
	 */
	public Catapult(int x, int y) {		
		super(NAME ,x,y);
		setAttributes(CatapultInit.attack_power, CatapultInit.building_cost, CatapultInit.upgrade_cost, CatapultInit.shooting_range, CatapultInit.upgrade_diff, CatapultInit.tier, CatapultInit.comment);
	}
	
	/**
	 * Check if Catapult is cooling down
	 * @return cooldown The current Catapult cool down state
	 */
	public int getCooldown() {
		return cooldown;
	}
	
	/**
	 * Find an monster closest to this tower
	 * @return closestEnemy	The closest monster to this tower
	 */
	public Monster findClosestEnemy() {
		Monster closestEnemy = null;
		double closestEnemyDistance = Double.MAX_VALUE;
		for(int i=0; i<MyController.monsters.size(); ++i) {
			if(MyController.monsters.get(i).getStatus() == Status.ALIVE) {				
				double dist = distance(MyController.monsters.get(i));
				if(dist < closestEnemyDistance && dist > shortDistance && dist < longDistance) {
					closestEnemyDistance = dist;
					closestEnemy = MyController.monsters.get(i);
				}
				else if(dist == closestEnemyDistance) {
					if(MyController.monsters.get(i).getX() > closestEnemy.getX()) {
						closestEnemy = MyController.monsters.get(i);
					}
				}
			}
		}
		cooldown = 0;
		return closestEnemy;
	}
	
	/**
	 * Find monsters to be targeted
	 * @return targetedMonster	The list of the targeted monsters
	 */
	public List<Monster> getTargetedMonster() {
		List<Monster> targetedMonster = new ArrayList<Monster>();
		Monster closestEnemy = findClosestEnemy();
		if(closestEnemy == null) return null;
		for(int i=0; i<MyController.monsters.size(); ++i) {			
			if(MyController.monsters.get(i).getStatus() == Status.ALIVE && distance(closestEnemy,MyController.monsters.get(i)) <= 25 ) {			
				targetedMonster.add(MyController.monsters.get(i));
			}
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
		if(cooldown == 0) {			
			List<Monster> targetedMonster = getTargetedMonster();
			if(targetedMonster == null) return targetedMonster;
			for(int i=0; i<targetedMonster.size(); ++i) {
				targetedMonster.get(i).damage(attack_power) ;
			}
			cooldown = 1;
			return targetedMonster; 
		}
		else {
			cooldown = 0;
			return null;
		}
	}

}
