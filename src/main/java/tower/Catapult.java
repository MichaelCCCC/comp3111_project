package tower;

import sample.Arena;
import monster.Monster;

public class Catapult extends Tower{
	static TowerInformation CatapultInit = new TowerInformation(10, 100, 100, 15, 10, 0, "This is Laser Tower");
	
	public Catapult(int x, int y) {		
		super(x,y);
		setAttributes(CatapultInit.attack_power, CatapultInit.building_cost, CatapultInit.upgrade_cost, CatapultInit.shooting_range, CatapultInit.upgrade_diff, CatapultInit.tier, CatapultInit.comment);
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
