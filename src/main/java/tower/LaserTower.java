package tower;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import sample.MyController;

public class LaserTower extends Tower{
	public static final String NAME = "Laser Tower";
	int attack_cost;
	
	public static TowerInformation LaserTowerInit = new TowerInformation(NAME,5, 100, 100, 1000, 10, 1, "This is Laser Tower");
	
	//	Constructor with default parameters
	public LaserTower(int x, int y) {
		super(NAME,x,y);
		setAttributes(LaserTowerInit.attack_power, LaserTowerInit.building_cost, LaserTowerInit.upgrade_cost, LaserTowerInit.shooting_range, LaserTowerInit.upgrade_diff, LaserTowerInit.tier, LaserTowerInit.comment);
		attack_cost = 20;
	}
	

	//	Get Cost of Shooting with Laser Tower
	public int getAttackCost() {
		return attack_cost;
	}
	
	//	Update Cost of Shooting with Laser Tower
	void setAttackCost(int attack_cost) {
		this.attack_cost = attack_cost;
	}
	
//	public List<Monster> shoot() {
////		for(int i=0; i<enemyList.size(); ++i) {
////			Monster currentEnemy = enemyList.get(i);
////			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
////				currentEnemy.damage(attack_power);
////			}
////		}	
//		List<Monster> monsterShooted = new ArrayList<>( );
//		if(MyController.monsters.size() == 0)
//			return null ; 
//		monsterShooted.add(MyController.monsters.get(0)) ; 
//		return monsterShooted; 
//	}

}
