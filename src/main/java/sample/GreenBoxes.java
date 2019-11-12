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
    static final int IMAGE_WIDTH = 30; 
    static final int BOX_WIDTH = 40 ; 
    
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
	
	static Image idReturnImage(String id) {
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
				return null;  
		}
	}
	
	static boolean targetDestroyTower(Object target) {
		assert targetHasTower(target) == true : "It has no Tower, no tower can be destroyed"  ;
		for(int i = 0 ; i < gbs.size() ; i++) 
			if(gbs.get(i).label.equals((Label)target))
			{
				
				
				gbs.get(i).haveTower = false ; 
				((Label)target).setGraphic(null);
				//((Label)target).setText("destroyed") ; 
				((Label)target).setId("");
				return true ;
			}
		return false ; 
	}
	
	static boolean targetBuildTower(Object target, String id) {
		assert targetHasTower(target) == false : "It has Tower, new tower cannot be built" ; 
		for (int i = 0 ; i < gbs.size()  ; i++)
			if(gbs.get(i).label.equals((Label)target))
			{
				ImageView iv = new ImageView((id == null) ? null: idReturnImage(id)) ; 
				if(id != null) {
					iv.setFitWidth(IMAGE_WIDTH) ;
					iv.setTranslateX((BOX_WIDTH - IMAGE_WIDTH)/2) ; 
	        		iv.setPreserveRatio(true) ;
	        		iv.setSmooth(true);
	                iv.setCache(true);
				}
                ((Label)target).setGraphic(iv);
                ((Label)target).setId(id) ; 
                
                gbs.get(i).haveTower = (id != null) ? true : false;  
                	
				return true;  
			}
		return false; 
		
	}
	
}
