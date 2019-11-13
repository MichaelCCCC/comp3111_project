package tower;

public class Catapult extends Tower{
	static TowerInformation CatapultInit = new TowerInformation(10, 100, 100, 15, 10, 0, "This is Laser Tower");
	
	public Catapult(int x, int y) {		
		super(x,y);
		setAttributes(CatapultInit.attack_power, CatapultInit.building_cost, CatapultInit.upgrade_cost, CatapultInit.shooting_range, CatapultInit.upgrade_diff, CatapultInit.tier, CatapultInit.comment);
	}

	void shoot() {
//		for(int i=0; i<enemyList.size(); ++i) {
//			Monster currentEnemy = enemyList.get(i);
//			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
//				currentEnemy.damage(attack_power);
//			}
//		}	
	}
}
