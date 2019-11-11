package sample;
import java.util.* ;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  

class GreenBoxes {
	
	static final Image BTimage = new Image("/basicTower.png") ;  
    static final Image ITimage = new Image("/iceTower.png") ; 
    static final Image Cimage = new Image("/catapult.png")  ;
    static final Image LTimage = new Image( "/laserTower.png") ; 
    
	static List<GreenBox> gbs = new ArrayList<>(); 
	
	static boolean targetHasTower(Object target) {
    	for(int i = 0 ; i < gbs.size() ; i++ ) {
    		if(gbs.get(i).label.equals((Label)target))
    			if(gbs.get(i).haveTower == true)
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
	
	Image idReturnImage(String id) {
		switch(id)
		{
			case "Basic Tower" : 
				return BTimage; 
		case "Ice Tower" :
				return ITimage ;
		case "Catapult" : 
				return Cimage; 
		case "Laser Tower":
				return LTimage ;
		default : 
				assert false : "no such tower." ;  
		}
		return null ; 
	}
	
	boolean targetBuildTower(Object target, String id) {
		assert targetHasTower(target) == false : "It has Tower, new tower cannot be built" ; 
		for (int i = 0 ; i < gbs.size()  ; i++)
			if(gbs.get(i).label.equals((Label)target))
			{
				ImageView iv = new ImageView(idReturnImage(id)) ; 
                iv.setFitWidth(40) ; 
        		iv.setPreserveRatio(true) ;
        		iv.setSmooth(true);
                iv.setCache(true);
				gbs.get(i).label.setGraphic(iv);
				gbs.get(i).label.setId(id) ; 
				gbs.get(i).haveTower = true;  
				return true;  
			}
		return false; 
		
	}
	
}
