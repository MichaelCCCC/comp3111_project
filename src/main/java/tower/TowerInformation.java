package tower;

public class TowerInformation {
	//public String name ; 
	public int attack_power;
	public int building_cost;
	public int upgrade_cost;
	public double shooting_range;
	public int upgrade_diff;
	public int tier;
	public String comment;
	
	TowerInformation( int attack_power, int building_cost, int upgrade_cost, double shooting_range, int upgrade_diff, int tier, String comment) {
		//this.name = name ; 
		this.attack_power = attack_power;
		this.building_cost = building_cost;
		this.upgrade_cost = upgrade_cost;
		this.shooting_range = shooting_range;
		this.upgrade_diff = upgrade_diff;
		this.tier = tier;
		this.comment = comment;
	}

}