package sample;

class Tower {
	
	final String Name ; 
	double shootingRange ; 
	int attackPower; 
	int BuildingCost ; 
	int tier = 1; 
	int upgradeCost ; 
	String upgradeDiff ; 
	String note ; 
	
	Tower() {
		Name = "unknown" ; 
	}
	
	Tower(String name , double sr, int ap, int bc, int tier, int uc, String ud, String note) {
		this.Name = name ; 
		this.shootingRange = sr; 
		this .attackPower  = ap; 
		this.BuildingCost = bc; 
		this.tier = tier; 
		this.upgradeDiff= ud ; 
		this.note = note ; 
	}
    
}
