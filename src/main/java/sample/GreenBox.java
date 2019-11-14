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
	Label gbLabel ;
	//Label towerLabel ; 
	final int v ; 
	final int h;  
	Tower towerInBox = null ; 
	String id = null;
	Circle shootingRange = null ; 
	
	GreenBox(Label label, int v, int h){
		this.gbLabel = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
	boolean destroyTower() {
		if(towerInBox != null) {
			gbLabel.setGraphic(null);
			gbLabel.setId(null);
			//towerLabel = null ; 
			towerInBox = null ; 
			MyController.towers.remove(towerInBox) ; 
			shootingRange = null ; 
			return true ; 	
		}
		return false ;
	}
	
	Label copyOfLabel() {
		Label copy = new Label () ; 
		copy.setGraphic(ImageFunction.setImageView(id)) ; 
		copy.setId(id);
		copy.setLayoutX(h * MyController.GRID_WIDTH);
        copy.setLayoutY(v * MyController.GRID_HEIGHT);
		return copy  ;
	}
	
	Tower buildTower(String id) {
		if(towerInBox == null) {
			this.id = id ; 
			//towerLabel = new Label() ; 
			ImageView iv = ImageFunction.setImageView(id) ; 
			
	        gbLabel.setGraphic(iv);
	        gbLabel.setId(id) ; 
//	        Tooltip.install(tower.getLabel(), new Tooltip(util.getObjectTooltip(tower.getLabel())) ) ; 
			
			
	        
	        double x = MyController.GRID_WIDTH * ((double)h ) ; 
	        double y = MyController.GRID_HEIGHT * ((double) v + 0.5 ) ; 
	        
//	        towerLabel.setLayoutX(x);
//			towerLabel.setLayoutY(y);
//			towerLabel.setMinWidth(1);
//			towerLabel.setMaxWidth(1);
//			towerLabel.setMinHeight(1);
//			towerLabel.setMaxHeight(1);
//			towerLabel.setGraphic(iv);
//			towerLabel.setId(id);
	        
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
	        
	        //install tooltip after add towerBoxes
	        MyController.towers.add(towerInBox ) ; 
	        Tooltip.install(gbLabel, new Tooltip(util.getTowerTooltipString(towerInBox.getInfo())) ) ;
	        
	        return towerInBox; 
	        
	        
		}

        return null ; 
	}

	private void setupShootingRange(Tower tower, Class<? extends Tower> towerClass) {
		// TODO Auto-generated method stub
		
		if(towerClass == Tower.class || towerClass == IceTower.class) {
			shootingRange = new Circle () ; 
			shootingRange.setCenterX(MyController.GRID_WIDTH * ((double)h + 0.5));
			shootingRange.setCenterY(MyController.GRID_WIDTH * ((double)v + 0.5));
			shootingRange.setRadius(tower.getInfo().shooting_range);
			shootingRange.setStyle("-fx-fill: rgba(0,0,0,0.3); ");
		}
		
		if(towerClass == Catapult.class) {
			
		}
		
		if(towerClass == LaserTower.class) {
			
		}
		
		
	}
	
}
