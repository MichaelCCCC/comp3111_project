package tower;

import monster.Monster;

public class LaserTower extends Tower{
	int attack_cost;
	
	//	Constructor with default parameters
	LaserTower(int x, int y) {
		super(x,y);
		setAttributes(10,10,10,Double.MAX_VALUE);
		attack_cost = 20;
	}
	
	//	Constructor with custom parameters
	LaserTower(int x, int y, int attack_power, int building_cost, int upgrade_cost, int attack_cost) {
		super(x,y,attack_power,building_cost,upgrade_cost,Double.MAX_VALUE);
		this.attack_cost = attack_cost;
	}
	
	//	Get Cost of Shooting with Laser Tower
	int getAttackCost() {
		return attack_cost;
	}
	
	//	Update Cost of Shooting with Laser Tower
	void setAttackCost(int attack_cost) {
		this.attack_cost = attack_cost;
	}
	
	//	Update Laser Tower Attributes
	void setAttributes(int attack_power, int building_cost, int upgrade_cost, double shooting_range, int attack_cost) {
		setAttributes(attack_power, building_cost, upgrade_cost, shooting_range);
		this.attack_cost = attack_cost;
	}
	
	void shoot() {
		for(int i=0; i<enemyList.size(); ++i) {
			Monster currentEnemy = enemyList.get(i);
			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
				currentEnemy.damage(attack_power);
			}
		}
	}
	
	void upgrade() {
		attack_power += 10;
	}
}
