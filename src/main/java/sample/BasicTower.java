package sample;

class BasicTower extends Tower{
	
	/*
	 * initial information of basic tower
	 * the meaning of initial is that when the tower is constructor, these data are shown. However, the data can change as the turn proceed. 
	 */
	
	static String INIT_NAME = "Basic Tower"; 
	static Double INIT_SHOOTING_RANGE = 3.0 ; 
	static Integer INIT_ATTACK_POWER = 1  ; 
	static Integer INIT_BUILDING_COST =1  ; 
	static Integer INIT_TIER = 1 ; 
	static Integer INIT_UPGRADE_COST = 3 ; 
	static String INIT_UPGRADE_DIFF ="upgrading diff"; 
	static String INIT_NOTE  = "note" ; 
	
	
	BasicTower(){
		super(INIT_NAME, INIT_SHOOTING_RANGE, INIT_ATTACK_POWER, INIT_BUILDING_COST, INIT_TIER,  INIT_UPGRADE_COST, INIT_UPGRADE_DIFF , INIT_NOTE) ; 
		
	}

	BasicTower(String name, double sr, int ap, int bc, int tier , int uc, String ud, String note) {
		super(name, sr, ap, bc,tier,  uc, ud, note);
		// TODO Auto-generated constructor stub
		
	}

	
	
	
}
