package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import tower.Catapult;
import tower.IceTower;
import tower.LaserTower;
import tower.Tower;

class GreenBox{ 
	Label label ; 
	final int v ; 
	final int h;  
	Tower towerInBox = null ; 
	Circle shootingRange = null ; 
	
	GreenBox(Label label, int v, int h){
		this.label = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
	boolean destroyTower() {
		if(towerInBox != null) {
			label.setGraphic(null);
			label.setId(null);
			towerInBox = null ; 
			MyController.towers.remove(towerInBox) ; 
			shootingRange = null ; 
			return true ; 	
		}
		return false ;
	}
	
	Tower buildTower(String id) {
		if(towerInBox == null) {
			ImageView iv = ImageFunction.setImageView(id) ; 
			
//	        tower.getLabel().setGraphic(iv);
//	        tower.getLabel().setId(id) ; 
//	        Tooltip.install(tower.getLabel(), new Tooltip(util.getObjectTooltip(tower.getLabel())) ) ; 
			
			label.setGraphic(iv);
			label.setId(id);
			Tooltip.install(label, new Tooltip(util.getObjectTooltip(label)) ) ;
	        
	        double x = MyController.GRID_WIDTH + ((double)v + 0.5 ) ; 
	        double y = MyController.GRID_HEIGHT + ((double) h + 0.5 ) ; 
	        
	        switch(id)
			{
			case "Basic Tower" : 
				towerInBox = new Tower((int)x,(int)y) ; 
				break  ; 
			case "Ice Tower" :
				towerInBox = new IceTower((int)x,(int)y) ; 
					break ;
			case "Catapult" : 
				towerInBox = new Catapult((int)x,(int)y) ; 
					break ; 
			case "Laser Tower":
				towerInBox = new LaserTower((int)x,(int)y) ;
					break ; 
			default : 
			}
	        
	        setupShootingRange(towerInBox , towerInBox.getClass()) ; 
	         
	        if(towerInBox == null )
	        {
	        	Alert alert = new Alert(AlertType.ERROR, "tower is not successfully built") ;  
	        	alert.showAndWait();
	        	return null; 
	        }
	        MyController.towers.add(towerInBox ) ; 
	        return towerInBox; 
	        
	        
		}

        return null ; 
	}

	private void setupShootingRange(Tower tower, Class<? extends Tower> towerClass) {
		// TODO Auto-generated method stub
		
		if(towerClass == Tower.class || towerClass == IceTower.class) {
			Circle circle = new Circle () ; 
			circle.setCenterX(MyController.GRID_WIDTH * ((double)v + 0.5));
			circle.setCenterY(MyController.GRID_WIDTH * ((double)h + 0.5));
			circle.setRadius(tower.getInfo().shooting_range);
			circle.setStyle("-fx-border-color: rgba(0,0,0,0.3); -fx-border-width: 3");
		}
		
		if(towerClass == Catapult.class) {
			
		}
		
		if(towerClass == LaserTower.class) {
			
		}
		
		
	}
	
}
