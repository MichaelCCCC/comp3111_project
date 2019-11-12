package sample;
import java.util.* ;

import javafx.scene.control.Label;
import tower.Tower;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class GreenBoxes {
	
    
	static List<GreenBox> gbs = new ArrayList<>(); 
	
	static Integer targetGetIndex(Object target) {
		for(int i = 0 ;  i < gbs.size() ; i++)
			if(gbs.get(i).label.equals((Label)target))
				return i ; 
		return null ; 
	}
	
	static boolean targetHasTower(Object target) {
    	if(gbs.get(targetGetIndex(target)).tower != null)
    		return true  ; 
    	return false ; 
    }
	
	static int targetV(Object target) {
		return gbs.get(targetGetIndex(target)).v ; 
	}
	
	static int targetH(Object target) {
		return gbs.get(targetGetIndex(target)).h ;
	}
	
	
	static boolean targetDestroyTower(Object target) {
		if(targetHasTower(target) != true ) {
			Alert alert = new Alert ( AlertType.ERROR,"It has no Tower, no tower can be destroyed" ) ; 
			alert.showAndWait()  ; 
			return false ; 
		} 
		gbs.get(targetGetIndex(target)).destroyTower() ; 
		return true ;
	}
	
	
	static boolean targetBuildTower(Object target, String id) {
		if(targetHasTower(target) != false ) {
			Alert alert = new Alert ( AlertType.ERROR, "It has Tower, new tower cannot be built") ; 
			alert.showAndWait()  ; 
			return false ; 
		} 
		gbs.get(targetGetIndex(target)).buildTower(id);
		return true;  
		
	}
	
	static boolean targetUpgradeTower(Object target) { 
		if(targetHasTower(target) != true ) {
			Alert alert = new Alert ( AlertType.ERROR, "no target for upgrade") ; 
			alert.showAndWait()  ; 
			return false; 
		}
		gbs.get(targetGetIndex(target)).tower.upgrade() ; 
		return true ; 
		 
	}
	
	static Tower targetGetTower(Object target) {
		if(targetHasTower(target) == false ) {
			Alert alert = new Alert ( AlertType.ERROR, "target has no tower") ; 
			alert.showAndWait()  ; 
			return null;
		}
		return gbs.get(targetGetIndex(target)).tower ; 
	}
	
}
