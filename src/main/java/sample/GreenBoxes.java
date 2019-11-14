package sample;
import java.util.* ;

import javafx.scene.control.Label;
import tower.Tower;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Yomaru
 *
 */
class GreenBoxes {
	
    /*
     * a list of green boxes
     */
	static List<GreenBox> gbs = new ArrayList<>(); 
	
	/**
	 * @param target
	 * @return
	 */
	static Integer targetGetIndex(Object target) {
		for(int i = 0 ;  i < gbs.size() ; i++)
			if(gbs.get(i).gbLabel == ((Label)target))
				return i ; 
		return null ; 
	}
	
	/**
	 * @param target
	 * @return
	 */
	static GreenBox targetGetGreenBox(Object  target) {
		return gbs.get(targetGetIndex(target)) ; 
	}
	
	/**
	 * @param target
	 * @return
	 */
	static boolean targetHasTower(Object target) {
    	if(gbs.get(targetGetIndex(target)).towerInBox != null)
    		return true  ; 
    	return false ; 
    }
	
	
	/**
	 * @param target
	 * @return
	 */
	static int targetV(Object target) {
		return gbs.get(targetGetIndex(target)).v ; 
	}
	
	/**
	 * @param target
	 * @return
	 */
	static int targetH(Object target) {
		return gbs.get(targetGetIndex(target)).h ;
	}
	
	/**
	 * @param target
	 * @return
	 */
	static boolean targetDestroyTower(Object target) {
		targetGetGreenBox(target).destroyTower() ; 
		return true ;
	}
	
	
	/**
	 * @param target
	 * @param id
	 * @return
	 */
	static Tower targetBuildTower(Object target, String id) {
		if(targetHasTower(target) != false ) {
			Alert alert = new Alert ( AlertType.ERROR, "It has Tower, new tower cannot be built") ; 
			alert.showAndWait()  ; 
			return null ; 
		} 
		Tower tower  = targetGetGreenBox(target).buildTower(id) ; 
		return tower;
	}
 
	/**
	 * @param target
	 * @return
	 */
	static boolean targetUpgradeTower(Object target) { 
		targetGetGreenBox(target).towerInBox.upgrade() ; 
		return true ; 
		 
	}
	
	/**
	 * @param target
	 * @return
	 */
	static Tower targetGetTower(Object target) {
		return targetGetGreenBox(target).towerInBox ; 
	}
	
	/**
	 * @param tower
	 * @return
	 */
	static GreenBox towerGetGreenBox(Tower tower) {
		for(int i = 0 ; i < gbs.size(); i++)
			if(tower == gbs.get(i).towerInBox)
				return gbs.get(i) ;
		return null; 
	}
}
