package tower;

public class TowerInformation {
	public int attack_power;
	public int building_cost;
	public int upgrade_cost;
	public double shooting_range;
	
	TowerInformation(int attack_power, int building_cost, int upgrade_cost, double shooting_range) {
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
	}


	public static int getUpgradeCost(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Integer getBuildingCost(String id) {
		// TODO Auto-generated method stub
		return 3;
	}


	public static String getTowerBuilingTooltip() {
		// TODO Auto-generated method stub
		return null;
	}
}