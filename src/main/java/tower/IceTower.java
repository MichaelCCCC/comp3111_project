package tower;

import monster.Monster;

public class IceTower extends Tower{
	int slow_duration;
	//	Constructor with default parameters
	IceTower(int x, int y) {
		super(x,y);
		setAttributes(10,10,10,10);
		slow_duration = 5;
	}
	
	//	Constructor with custom parameters
	IceTower(int x, int y, int attack_power, int building_cost, int upgrade_cost, double shooting_range, int slow_duration) {
		super(x,y,attack_power,building_cost,upgrade_cost,shooting_range);
		this.slow_duration = slow_duration;
	}
	
	//	Get Slow Duration of Ice Tower
	int getSlowDuration() {
		return slow_duration;
	}
	
	//	Update Slow Duration of Ice Tower
	void setSlowDuration(int slow_duration) {
		this.slow_duration = slow_duration;
	}
	
	//	Update Ice Tower Attributes
	void setAttributes(int attack_power, int building_cost, int upgrade_cost, double shooting_range, int slow_duration) {
		setAttributes(attack_power, building_cost, upgrade_cost, shooting_range);
		this.slow_duration = slow_duration;
	}
	
	void shoot() {
		Monster closestEnemy = findClosestEnemy();
		if(distance(closestEnemy) <= shooting_range) {			
			closestEnemy.slow_down(slow_duration);
		}
	}
	
	void upgrade() {
		slow_duration += 5;
	}
}