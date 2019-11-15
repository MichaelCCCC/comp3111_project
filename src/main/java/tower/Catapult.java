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

	
	public List<Monster> shoot() {
		for(int i=0; i<MyController.monsters.size(); ++i) {
			Monster currentEnemy = MyController.monsters.get(i);
			if(currentEnemy.getY() <= y-3 || currentEnemy.getY() <= y+3) {
				currentEnemy.damage(attack_power);
				System.out.println("C (" + currentEnemy.getX() + "," + currentEnemy.getY() + ")");
			}
		}	
		List<Monster> monsterShooted = new ArrayList<>( );
		if(MyController.monsters.size() == 0 )
			return null;
		monsterShooted.add(MyController.monsters.get(0)) ; 
		return monsterShooted; 
	}

}
