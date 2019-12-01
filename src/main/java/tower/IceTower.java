package tower;

import java.util.List;
import monster.Monster;

/**
 * Ice Tower
 */
public class IceTower extends Tower{	
	/**
	 * The default name for Ice Tower
	 */
	public static final String NAME = "Ice Tower" ;
	//	Important ! For IceTower the slow duration is kept in the attack_power variable !
	
	/**
	 * The default value for Ice Tower information
	 */
	public static TowerInformation IceTowerInit = new TowerInformation( NAME,10, 100, 100, 100, 10, 1, "This is Ice Tower");
	
	/**
	 * Catapult constructor
	 * @param x	X coordinate of the tower
	 * @param y	Y coordinate of the tower
	 */
	public IceTower(int x, int y) {
		super(NAME, x,y);
		setAttributes(IceTowerInit.attack_power, IceTowerInit.building_cost, IceTowerInit.upgrade_cost, IceTowerInit.shooting_range, IceTowerInit.upgrade_diff, IceTowerInit.tier, IceTowerInit.comment);
	}
	
	/**
	 * Shoot targeted monsters
	 * @return targetedMonster	The list of the monsters shot
	 */
	public List<Monster> shoot() {	
		List<Monster> targetedMonster = getTargetedMonster();
		if(targetedMonster == null) return targetedMonster;
		for(int i=0; i<targetedMonster.size(); ++i) {
			targetedMonster.get(i).slow_down(attack_power) ; 
		}
		return targetedMonster; 
	}
	
}