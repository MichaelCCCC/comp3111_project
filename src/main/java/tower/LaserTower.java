package tower;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import monster.Monster.Status;
import sample.MyController;

public class LaserTower extends Tower{
	public static final String NAME = "Laser Tower";
	public int attack_cost;
	
	public static TowerInformation LaserTowerInit = new TowerInformation(NAME,4, 300, 100, 1000, 10, 1, "This is Laser Tower");
	
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
	public void setAttackCost(int attack_cost) {
		this.attack_cost = attack_cost;
	}
	
	private double len(Monster m) {
		return Math.sqrt(Math.pow(m.getX(), 2) + Math.pow(m.getY(), 2));
	}
	
	private int pointToLine(Monster line, Monster point) {
		double cosine = (line.getX()*point.getX() + line.getY()*point.getY()) / (len(line) + len(point));
		return (int) (distance(point) * Math.sin(Math.acos(cosine)));
	}
	
	public List<Monster> getTargetedMonster() {
		List<Monster> targetedMonster = new ArrayList<Monster>();
		Monster closestEnemy = findClosestEnemy();
		if(closestEnemy == null) return null;		
		int x_diff = closestEnemy.getX() - x;
		int y_diff = closestEnemy.getY() - y;

		for(int i=0; i<MyController.monsters.size(); ++i) {	
			Monster current = MyController.monsters.get(i);
			int x_diff2 = current.getX() - x;
			int y_diff2 = current.getY() - y;
			
			if(MyController.monsters.get(i).getStatus() == Status.ALIVE) {					
				if(x_diff == 0 || x_diff2 == 0) {
					if((x_diff == 0 && x_diff2 == 0) && ((y_diff < 0 && y_diff2 < 0) || (y_diff > 0 && y_diff2 > 0))) {
						if(pointToLine(closestEnemy,current) <= 3) {
							targetedMonster.add(current);
						}
					}
				}
				else if(y_diff2/x_diff2 == y_diff/x_diff) {
					if((x_diff < 0 && x_diff2 <0) || (x_diff > 0 && x_diff2 >0)) {
						if((y_diff < 0 && y_diff2 < 0) || (y_diff > 0 && y_diff2 > 0) || (y_diff == 0 && y_diff2 == 0)) {
							if(pointToLine(closestEnemy,current) <= 3) {
								targetedMonster.add(current);
							}
						}
					}	
				}
			}
		}
		
		
		if(targetedMonster.size() == 0 )
			targetedMonster = null ; 
		
		return targetedMonster;
	}
	

	public List<Monster> shoot() {
		List<Monster> targetedMonster = getTargetedMonster();
		if(targetedMonster == null) return targetedMonster;
		for(int i=0; i<targetedMonster.size(); ++i) {
			targetedMonster.get(i).damage(attack_power) ; 
		}
		MyController.money -= attack_cost;
		return targetedMonster; 
	}

}
