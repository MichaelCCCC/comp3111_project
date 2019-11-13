package tower;
import java.lang.Math; 
import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import monster.Monster;

import java.util.ArrayList;

public class Tower {
	protected int x;
	protected int y;
	protected int attack_power;
	protected int building_cost;
	protected int upgrade_cost;
	protected double shooting_range;
	protected boolean is_destroyed = false;
	
	//	Constructor with default parameters
	Tower(int x, int y) {
		this.x = x;
		this.y = y;
		attack_power = 10;
		building_cost = 10;
		upgrade_cost = 10;
		shooting_range = 10;
	}
	
	//	Constructor with custom parameters
	Tower(int x, int y, int attack_power, int building_cost, int upgrade_cost, double shooting_range) {
		this.x = x;
		this.y = y;
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
	}
	
	public Tower() {
		// TODO Auto-generated constructor stub
	}

	//	Get Tower Coordinate
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	//	Get Tower Destroyed status
	boolean isDestroyed() {
		return is_destroyed;
	}
	
	//	Get Tower Upgrade Cost
	int getUpgradeCost() {
		return upgrade_cost;
	}
	
	//	Get Tower Information
	TowerInformation getInfo() {
		return new TowerInformation(attack_power, building_cost, upgrade_cost, shooting_range);
	}
	
	//	Update Tower Attack Power
	void setAttackPower(int attack_power) {
		this.attack_power = attack_power;
	}
	
	//	Update Tower Building Cost
	void setBuildingCost(int building_cost) {
		this.building_cost = building_cost;
	}
	
	//	Update Tower Upgrade Cost
	void setUpgradeCost(int upgrade_cost) {
		this.upgrade_cost = upgrade_cost;
	}
	
	//	Update Tower Shooting Range
	void setShootingRange(double shooting_range) {
		this.shooting_range = shooting_range;
	}
	
	void setAttributes(int attack_power, int building_cost, int upgrade_cost, double shooting_range) {
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
	}
	
	protected double distance(Monster enemy) {
		return Math.sqrt(Math.pow(enemy.getX() - x, 2) + Math.pow(enemy.getY() - y, 2));
	}
	
	protected Monster findClosestEnemy() {
//		Monster closestEnemy;
//		double closestEnemyDistance = Double.MAX_VALUE;
//		for(int i=0; i<enemyList.size(); ++i) {
//			if(distance(enemyList.get(i)) < closestEnemyDistance) {
//				closestEnemyDistance = distance(enemyList.get(i));
//				closestEnemy = enemyList.get(i);
//			}
//		}
		return null;
	}
	

	void shoot() {
//		Monster closestEnemy = findClosestEnemy();
//		if(distance(closestEnemy) <= shooting_range) {			
//			closestEnemy.damage(attack_power);
//		}
	}
	
	void upgrade() {
		attack_power += 10;
	}
	
	void destroy() {
		is_destroyed = true;
	}

	public Label getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	public void attack(List<Monster> monsters) {
		// TODO Auto-generated method stub
		
	}
}