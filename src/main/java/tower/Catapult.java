package tower;

public class Catapult extends Tower{
	public static final String NAME = "Catapult" ;
	public int shortDistance = 50 ; 
	public int longDistance = 150 ; 
	static final int INIT_LONG_DISTANCE = 150 ; 
	
	public static TowerInformation CatapultInit = new TowerInformation(NAME,10, 100, 100, INIT_LONG_DISTANCE, 10, 1, "This is Laser Tower");
	
	public Catapult(int x, int y) {		
		super(x,y);
		setAttributes(CatapultInit.attack_power, CatapultInit.building_cost, CatapultInit.upgrade_cost, CatapultInit.shooting_range, CatapultInit.upgrade_diff, CatapultInit.tier, CatapultInit.comment);
	}


	public void shoot() {
//		for(int i=0; i<enemyList.size(); ++i) {
//			Monster currentEnemy = enemyList.get(i);
//			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
//				currentEnemy.damage(attack_power);
//			}
//		}	
	}

}
