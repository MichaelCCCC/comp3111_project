package tower;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import sample.MyController;

public class Catapult extends Tower{
	public static final String NAME = "Catapult" ;
	public int shortDistance = 50 ; 
	public int longDistance = 150 ; 
	static final int INIT_LONG_DISTANCE = 150 ; 
	static final String NOTE = "All monsters placed at the\n radius of 25px of where\n the stone drop receive\n damage." ; 
	
	public static TowerInformation CatapultInit = new TowerInformation(NAME, 10, 100, 100, INIT_LONG_DISTANCE, 10, 1, NOTE);
	
	public Catapult(int x, int y) {		
		super(NAME ,x,y);
		setAttributes(CatapultInit.attack_power, CatapultInit.building_cost, CatapultInit.upgrade_cost, CatapultInit.shooting_range, CatapultInit.upgrade_diff, CatapultInit.tier, CatapultInit.comment);
	}


	public Monster findClosestEnemy() {
		Monster closestEnemy = null;
		double closestEnemyDistance = Double.MAX_VALUE;
		for(int i=0; i<MyController.monsters.size(); ++i) {
			double dist = distance(MyController.monsters.get(i));
			if(dist < closestEnemyDistance && dist > shortDistance && dist < longDistance) {
				closestEnemyDistance = dist;
				closestEnemy = MyController.monsters.get(i);
			}
			else if(dist == closestEnemyDistance) {
				if(MyController.monsters.get(i).getX() > closestEnemy.getX()) {
					closestEnemy = MyController.monsters.get(i);
				}
				else if(MyController.monsters.get(i).getX() == closestEnemy.getX()) {
					if(MyController.monsters.get(i).getY() < closestEnemy.getY()) {
						closestEnemy = MyController.monsters.get(i);
					}
				}
			}
		}
		return closestEnemy;
	}
	
	public List<Monster> getTargetedMonster() {
		List<Monster> targetedMonster = new ArrayList<Monster>();
		Monster closestEnemy = findClosestEnemy();
		for(int i=0; i<MyController.monsters.size(); ++i) {			
			if(distance(closestEnemy,MyController.monsters.get(i)) <= 25) {			
				targetedMonster.add(MyController.monsters.get(i));
			}
		}
		return targetedMonster;
	}
	

	public List<Monster> shoot() {
		List<Monster> targetedMonster = getTargetedMonster();
		for(int i=0; i<targetedMonster.size(); ++i) {
			targetedMonster.get(i).damage(attack_power) ; 
		}
		return targetedMonster; 
	}

}
