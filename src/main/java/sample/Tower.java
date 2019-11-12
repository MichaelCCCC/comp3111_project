package sample;

abstract class Tower {
	//initial constant 
	
	static final int NUM_TOWER_TYPE =4 ; 
	static final int NUM_INIT_INFORMATION_LINE = 8 ; 
	static final int NUM_CURRENT_INFORMATION_LINE = 9 ; 
	static final String[] INIT_INFORMATION_LINE_ID = {"Name" , "Shooting Range" , "Attack Power", "Building Cost" , "Tier" , "Upgrade Cost" , "Upgrading details", "Note"} ; 
	static final String[] CURRENT_INFORMATION_LINE_ID = {"Name" , "Shooting Range" , "Attack Power", "Building Cost" , "Tier" , "Upgrade Cost", "Total Cost", "Upgrading details", "Note"}; 
	
	//current information of data
	String name ; 
	double shootingRange ; 
	int attackPower; 
	int buildingCost ; 
	int tier; 
	int upgradeCost ; 
	int totalCost ; 
	String upgradeDiff ; 
	String note ; 
	
	Tower() {
	}
	
	Tower(String name , double sr, int ap, int bc, int tier, int uc, String ud, String note) {
		//initialize variable fields
		this.name = name  ; 
		this.shootingRange = sr ; 
		this.attackPower = ap ; 
		this.buildingCost = bc ; 
		this.tier = tier; 
		this.upgradeCost = uc ; 
		this.totalCost = uc ; 
		this.upgradeDiff = ud ; 
		this.note = note ; 
	}
    
	String[] getTowerCurrentInformation() {
		String towerInformation[] = new String [NUM_CURRENT_INFORMATION_LINE] ; 
		//todo
		towerInformation[0] = name ; 
		towerInformation[1] = String.valueOf(shootingRange) ; 
		towerInformation[2] = String.valueOf(attackPower) ; 
		towerInformation[3] = String.valueOf(buildingCost) ; 
		towerInformation[4] = String.valueOf(tier) ; 
		towerInformation[5] = String.valueOf(upgradeCost) ; 
		towerInformation[6] = String.valueOf(totalCost) ; 
		towerInformation[7] = upgradeDiff ; 
		towerInformation[8] = note ; 
		return towerInformation ; 
	}
	
	static String[][] getTowerInitInformation() {
		String towerInformation[][] = new String [NUM_TOWER_TYPE][NUM_INIT_INFORMATION_LINE]  ;
		//todo 
		towerInformation[0][0] = BasicTower.initName ; 
		towerInformation[0][1] = BasicTower.initShootingRange.toString() ; 
		towerInformation[0][2] = BasicTower.initAttackPower.toString() ; 
		towerInformation[0][3] = BasicTower.initBuildingCost.toString() ; 
		towerInformation[0][4] = BasicTower.initTier.toString() ; 
		towerInformation[0][5] = BasicTower.initUpgradeCost.toString() ; 
		towerInformation[0][6] = BasicTower.initUpgradeDiff ; 
		towerInformation[0][7] = BasicTower.initNote ; 
		return towerInformation ; 
	}
}
