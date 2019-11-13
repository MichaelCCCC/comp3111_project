package tower;

import monster.Monster;

public class IceTower extends Tower{	
	//	Important ! For IceTower the slow duration is kept in the attack_power variable !
	
	static TowerInformation IceTowerInit = new TowerInformation(10, 100, 100, 15, 10, 0, "This is Ice Tower");
	
	//	Constructor with default parameters
	IceTower(int x, int y) {
		super(x,y);
		setAttributes(IceTowerInit.attack_power, IceTowerInit.building_cost, IceTowerInit.upgrade_cost, IceTowerInit.shooting_range, IceTowerInit.upgrade_diff, IceTowerInit.tier, IceTowerInit.comment);
	}
	
	void shoot() {
		Monster closestEnemy = findClosestEnemy();
		if(distance(closestEnemy) <= shooting_range) {			
			closestEnemy.slow_down(attack_power);
		}
	}
}