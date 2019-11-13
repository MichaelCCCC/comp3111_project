package tower;

import monster.Monster;
import sample.Arena;

public class LaserTower extends Tower{
	int attack_cost;
	
	static TowerInformation LaserTowerInit = new TowerInformation(10, 100, 100, 15, 10, 0, "This is Laser Tower");
	
	//	Constructor with default parameters
	LaserTower(int x, int y) {
		super(x,y);
		setAttributes(LaserTowerInit.attack_power, LaserTowerInit.building_cost, LaserTowerInit.upgrade_cost, LaserTowerInit.shooting_range, LaserTowerInit.upgrade_diff, LaserTowerInit.tier, LaserTowerInit.comment);
		attack_cost = 20;
	}

	//	Get Cost of Shooting with Laser Tower
	int getAttackCost() {
		return attack_cost;
	}
	
	//	Update Cost of Shooting with Laser Tower
	void setAttackCost(int attack_cost) {
		this.attack_cost = attack_cost;
	}
	
	void attack() {
		for(int i=0; i<Arena.monsterlist.size(); ++i) {
			Monster currentEnemy = Arena.monsterlist.get(i);
			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
				currentEnemy.damage(attack_power);
			}
		}	
	}
}
