package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import tower.Catapult;
import tower.IceTower;
import tower.LaserTower;
import tower.Tower;

class GreenBox{ 
	Label label ; 
	final int v ; 
	final int h;  
	Tower tower ; 
	
	GreenBox(Label label, int v, int h){
		this.label = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
	boolean destroyTower() {
		if(tower != null) {
			label.setGraphic(null);
			label.setId(null);
			tower = null ; 
			return true ; 	
		}
		return false ;
	}
	
	Tower buildTower(String id) {
		if(tower == null) {
			ImageView iv = ImageFunction.setImageView(id) ; 
			
	        tower.getLabel().setGraphic(iv);
	        tower.getLabel().setId(id) ; 
	        Tooltip.install(tower.getLabel(), new Tooltip(util.getObjectTooltip(tower.getLabel())) ) ; 
	        
	        double x = MyController.GRID_WIDTH + ((double)v + 0.5 ) ; 
	        double y = MyController.GRID_HEIGHT + ((double) h + 0.5 ) ; 
	        
	        switch(id)
			{
			case "Basic Tower" : 
				tower = new Tower((int)x,(int)y) ; 
				break  ; 
			case "Ice Tower" :
				tower = new IceTower((int)x,(int)y) ; 
					break ;
			case "Catapult" : 
				tower = new Catapult((int)x,(int)y) ; 
					break ; 
			case "Laser Tower":
				tower = new LaserTower((int)x,(int)y) ;
					break ; 
			default : 
				Alert alert = new Alert(AlertType.ERROR, "tower is not successfully built") ;  
	        	alert.showAndWait();
	        	return null;  
			}
	         
	        if(tower == null )
	        {
	        	Alert alert = new Alert(AlertType.ERROR, "tower is not successfully built") ;  
	        	alert.showAndWait();
	        	return null; 
	        }
	        return tower; 
	        
	        
		}
		
        
        return null ; 
	}
	
}
