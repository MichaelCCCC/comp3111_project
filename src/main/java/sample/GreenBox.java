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
	 * @param the label of the box
	 * @param the v of the grid array
	 * @param the h of the grid array
	 */
	GreenBox(Label label, int v, int h){
		this.gbLabel = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
	/**
	 * remove the tower in this green box 
	 * @return  a boolean of whether removal is successful
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
	 * make a copy of the green box label 
	 * @return a label which is a copy of green box label
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
	        
	        if (setupShootingRange(towerInBox , towerInBox.getClass()) != null)
	        		shootingRange = setupShootingRange(towerInBox , towerInBox.getClass()); 
	        
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
	Shape setupShootingRange(Tower tower, Class<? extends Tower> towerClass) {
		Shape shootingRange = null ; 
		if(towerClass == Tower.class || towerClass == IceTower.class) {
			Circle circle = new Circle () ; 
			circle.setCenterX(getTowerX());
			circle.setCenterY(getTowerY());
			circle.setRadius(tower.getInfo().shooting_range);
			circle.setStyle("-fx-fill: rgba(0,0,0,0.3); ");
			shootingRange = circle ; 
			
			shootingRange.setId(id) ; 
		}
		
		if(towerClass == tower.Catapult.class) {
			Circle outside = new Circle(getTowerX(),getTowerY() , ((Catapult)tower).longDistance) ; 
			
			Circle inside = new Circle(getTowerX(),getTowerY(),((Catapult)tower).shortDistance) ; 
			
			
			shootingRange = Shape.subtract(outside, inside); 
			shootingRange.setStyle("-fx-fill : rgba(0,0,0,0.3) ;"); 
			shootingRange.setId(id);
		}
		
		if(towerClass == tower.LaserTower.class) { 
			if(((tower.LaserTower)towerInBox).findClosestEnemy() == null )
				return null ; 
			
			//shootingRange = util.lineToFirstMonsterAlive(this) ; 
			shootingRange = util.lineToMonsterShooted(this, true);
			shootingRange.setStyle("-fx-stroke: red;");
		}
		return shootingRange; 
		
	}
	
}

//add a comment
