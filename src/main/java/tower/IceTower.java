package tower;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import sample.MyController;

public class IceTower extends Tower{	
	
	public static final String NAME = "Ice Tower" ;
	//	Important ! For IceTower the slow duration is kept in the attack_power variable !
	
	public static TowerInformation IceTowerInit = new TowerInformation( NAME,10, 100, 100, 50, 10, 1, "This is Ice Tower");
	
	//	Constructor with default parameters
	public IceTower(int x, int y) {
		super(NAME, x,y);
		setAttributes(IceTowerInit.attack_power, IceTowerInit.building_cost, IceTowerInit.upgrade_cost, IceTowerInit.shooting_range, IceTowerInit.upgrade_diff, IceTowerInit.tier, IceTowerInit.comment);
	}
	

	public List<Monster> shoot() {
		Monster closestEnemy = findClosestEnemy();
		List<Monster> monsterShooted = new ArrayList<>( );
		if(distance(closestEnemy) <= shooting_range) {			
			closestEnemy.slow_down(attack_power);
			System.out.println("I (" + closestEnemy.getX() + "," + closestEnemy.getY() + ")");
			monsterShooted.add(closestEnemy) ; 
		}
		return monsterShooted; 
	}

}