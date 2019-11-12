package tower;

public class BasicTower extends Tower{
	
	/*
	 * initial information of basic tower
	 * the meaning of initial is that when the tower is constructor, these data are shown. However, the data can change as the turn proceed. 
	 */
	
	static String initName = "Basic Tower"; 
	static Double initShootingRange = 3.0 ; 
	static Integer initAttackPower = 1  ; 
	static Integer initBuildingCost =1  ; 
	static Integer initTier = 1 ; 
	static Integer initUpgradeCost = 3 ; 
	static String initUpgradeDiff ="upgrading diff"; 
	static String initNote  = "note" ; 
	
	
	
	public BasicTower(){
		super(initName,initShootingRange, initAttackPower,initBuildingCost,initTier,initUpgradeCost,initUpgradeDiff,initNote) ; 
		
	}

	BasicTower(String name, double sr, int ap, int bc, int tier , int uc, String ud, String note) {
		super(name, sr, ap, bc,tier,  uc, ud, note);
		// TODO Auto-generated constructor stub
		
	}

	//different methods with respect to type of tower
	
	
}
