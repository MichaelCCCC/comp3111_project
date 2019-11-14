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
			if(gbs.get(i).gbLabel == ((Label)target))
				return i ; 
		return null ; 
	}
	
	static GreenBox targetGetGreenBox(Object  target) {
		return gbs.get(targetGetIndex(target)) ; 
	}
	
	static boolean targetHasTower(Object target) {
    	if(gbs.get(targetGetIndex(target)).towerInBox != null)
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
		targetGetGreenBox(target).destroyTower() ; 
		return true ;
	}
	
	
	static Tower targetBuildTower(Object target, String id) {
		if(targetHasTower(target) != false ) {
			Alert alert = new Alert ( AlertType.ERROR, "It has Tower, new tower cannot be built") ; 
			alert.showAndWait()  ; 
			return null ; 
		} 
		Tower tower  = targetGetGreenBox(target).buildTower(id) ; 
		return tower;
	}
 
	static boolean targetUpgradeTower(Object target) { 
		targetGetGreenBox(target).towerInBox.upgrade() ; 
		return true ; 
		 
	}
	
	static Tower targetGetTower(Object target) {
		return targetGetGreenBox(target).towerInBox ; 
	}
	
	static GreenBox towerGetGreenBox(Tower tower) {
		for(int i = 0 ; i < gbs.size(); i++)
			if(tower == gbs.get(i).towerInBox)
				return gbs.get(i) ;
		return null; 
	}
}
