package sample;
import java.util.* ;

import javafx.scene.control.Label;

class GreenBoxes {
	
    
	static List<GreenBox> gbs = new ArrayList<>(); 
	
	static boolean targetHasTower(Object target) {
    	for(int i = 0 ; i < gbs.size() ; i++ ) {
    		if(gbs.get(i).label.equals((Label)target))
    			if(gbs.get(i).tower != null)
    				return true  ; 
    	
    	}
    	return false ; 
    }
	
	int targetV(Object target) {
		for(int i = 0 ; i < gbs.size() ; i++ )
			if(gbs.get(i).label.equals((Label)target))
				return gbs.get(i).v ; 
		return -1 ; 
	}
	
	int targetH(Object target) {
		for(int i = 0 ; i < gbs.size() ; i++)
			if(gbs.get(i).label.equals((Label)target))
				return gbs.get(i).h ; 
		return -1 ;
	}
	
	
	static boolean targetDestroyTower(Object target) {
		assert targetHasTower(target) == true : "It has no Tower, no tower can be destroyed"  ;
		for(int i = 0 ; i < gbs.size() ; i++) 
			if(gbs.get(i).label.equals((Label)target))
			{
				gbs.get(i).destroyTower() ; 
				return true ;
			}
		return false ; 
	}
	
	
	static boolean targetBuildTower(Object target, String id) {
		assert targetHasTower(target) == false : "It has Tower, new tower cannot be built" ; 
		for (int i = 0 ; i < gbs.size()  ; i++)
			if(gbs.get(i).label.equals((Label)target))
			{
				gbs.get(i).buildTower(id);
				return true;  
			}
		return false; 
		
	}
	
}
