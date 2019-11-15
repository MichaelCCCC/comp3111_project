package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import tower.Catapult;
import tower.IceTower;
import tower.LaserTower;
import tower.Tower;

class GreenBox{ 
	Label gbLabel ;
	final int v ; 
	final int h;  
	Tower towerInBox = null ; 
	String id = null;
	Shape shootingRange = null ; 
	
	/**
	 * @param label
	 * @param v
	 * @param h
	 */
	GreenBox(Label label, int v, int h){
		this.gbLabel = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
	/**
	 * @return
	 */
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
	
	/**
	 * @return
	 */
	Label copyOfLabel() {
		Label copy = new Label () ; 
		copy.setGraphic(ImageFunction.setImageView(id)) ; 
		copy.setId(id);
		copy.setLayoutX(h * MyController.GRID_WIDTH);
        copy.setLayoutY(v * MyController.GRID_HEIGHT);
        copy.setMinWidth(MyController.GRID_WIDTH);
        copy.setMaxWidth(MyController.GRID_WIDTH);
        copy.setMinHeight(MyController.GRID_HEIGHT);
        copy.setMaxHeight(MyController.GRID_HEIGHT);
		return copy  ;
	}
	
	double getTowerX () {
		return MyController.GRID_WIDTH * ((double)h + 0.5) ; 
	}
	
	double getTowerY () {
		return MyController.GRID_WIDTH * ((double)v + 0.5); 
	}
	
	/**
	 * @param id
	 * @return
	 */
	Tower buildTower(String id) {
		if(towerInBox == null) {
			this.id = id ; 
			ImageView iv = ImageFunction.setImageView(id) ; 
	        gbLabel.setGraphic(iv);
	        gbLabel.setId(id) ; 
	        switch(id)
			{
			case "Basic Tower" : 
				towerInBox = new Tower((int)getTowerX(),(int)getTowerY()) ; 
				break  ; 
			case "Ice Tower" :
				towerInBox = new IceTower((int)getTowerX(),(int)getTowerY()) ; 
					break ;
			case "Catapult" : 
				towerInBox = new Catapult((int)getTowerX(),(int)getTowerY()) ; 
					break ; 
			case "Laser Tower":
				towerInBox = new LaserTower((int)getTowerX(),(int)getTowerY()) ;
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

	/**
	 * @param tower
	 * @param towerClass
	 */
	void setupShootingRange(Tower tower, Class<? extends Tower> towerClass) {
		if(towerClass == Tower.class || towerClass == IceTower.class) {
			Circle circle = new Circle () ; 
			circle.setCenterX(getTowerX());
			circle.setCenterY(getTowerY());
			circle.setRadius(tower.getInfo().shooting_range);
			circle.setStyle("-fx-fill: rgba(0,0,0,0.3); ");
			shootingRange = circle ; 
			
			shootingRange.setId(id) ; 
		}
		
		if(towerClass == Catapult.class) {
			Circle outside = new Circle(getTowerX(),getTowerY() , ((Catapult)tower).longDistance) ; 
			
			Circle inside = new Circle(getTowerX(),getTowerY(),((Catapult)tower).shortDistance) ; 
			
			
			shootingRange = Shape.subtract(outside, inside); 
			shootingRange.setStyle("-fx-fill : rgba(0,0,0,0.3) ;"); 
			shootingRange.setId(id);
		}
		
		if(towerClass == LaserTower.class) { 
			if(((tower.LaserTower)towerInBox).getMonstershooted() == null )
				return ; 
			
			shootingRange = util.lineToMonsterShooted(this, true ) ; 
			shootingRange.setStyle("-fx-stroke: red;");
		}
		
		
	}
	
}

//add a comment
