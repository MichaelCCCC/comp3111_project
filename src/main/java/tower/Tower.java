package tower;
import java.lang.Math; 
import java.lang.String;
import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import monster.Monster;

import java.util.ArrayList;

public class Tower {
	public static final String NAME = "Basic Tower" ;
	protected int x;
	protected int y;
	protected int attack_power;
	protected int building_cost;
	protected int upgrade_cost;
	protected double shooting_range;
	protected int upgrade_diff;
	protected int tier;
	protected String comment;
	protected boolean is_destroyed = false;
	
	public static TowerInformation BasicTowerInit = new TowerInformation(NAME, 10, 100, 100, 100, 10, 1, "This is Basic Tower");
	
	//	Constructor with default parameters
	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
		attack_power = BasicTowerInit.attack_power;
		building_cost = BasicTowerInit.building_cost;
		upgrade_cost = BasicTowerInit.upgrade_cost;
		shooting_range = BasicTowerInit.shooting_range;
		upgrade_diff = BasicTowerInit.upgrade_diff;
		tier = BasicTowerInit.tier;
		comment = BasicTowerInit.comment;
	}
	//	Getter Functions
	
	//	Get Tower X-Coordinate
	int getX() {
		return x;
	}
	
	//	Get Tower Y-Coordinate
	int getY() {
		return y;
	}
	
	//	Get Tower Destroyed status
	boolean isDestroyed() {
		return is_destroyed;
	}
	
	//	Get Tower Information
	public TowerInformation getInfo() {
		return new TowerInformation(NAME,attack_power, building_cost, upgrade_cost, shooting_range, upgrade_diff, tier, comment);
	}
	
	//	Set Tower Attack Power
	void setAttackPower(int attack_power) {
		this.attack_power = attack_power;
	}
	
	//	Set Tower Building Cost
	void setBuildingCost(int building_cost) {
		this.building_cost = building_cost;
	}
	
	//	Set Tower Upgrade Cost
	void setUpgradeCost(int upgrade_cost) {
		this.upgrade_cost = upgrade_cost;
	}
	
	//	Set Tower Upgrade Diff
	void setUpgradeDiff(int upgrade_diff) {
		this.upgrade_diff = upgrade_diff;
	}
	
	//	Set Tower Tier
	void setTier(int tier) {
		this.tier = tier;
	}
	
	//	Set Tower Comment
	void setComment(String comment) {
		this.comment = comment;
	}
	
	//	Set Tower Shooting Range
	void setShootingRange(double shooting_range) {
		this.shooting_range = shooting_range;
	}
	
	void setAttributes(int attack_power, int building_cost, int upgrade_cost, double shooting_range, int upgrade_diff, int tier, String comment) {
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
		this.upgrade_diff = upgrade_diff;
		this.tier = tier;
		this.comment = comment;
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
	
	public void upgrade() {
		attack_power += upgrade_diff;
	}
	
	public void destroy() {
		is_destroyed = true;
	}


	public void attack() {
		// TODO Auto-generated method stub
		
	}

	public int getUpgradeCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}