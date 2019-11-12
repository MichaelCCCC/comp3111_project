package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;

class util {

	public static void showTowerRange(Label target) {
		if(GreenBoxes.targetHasTower(target) != true) 
			return ; 
		// TODO Auto-generated method stub
		//if the tower is basic tower or ice tower 
		switch(target.getId()) {
		case "Basic Tower" : 
		case "Ice Tower" :
			Circle circle = new Circle () ; 
			circle.setCenterX(MyController.GRID_WIDTH * ((double)GreenBoxes.targetV(target) + 0.5) );
			circle.setCenterY(MyController.GRID_WIDTH * ((double)GreenBoxes.targetH(target) + 0.5));
			circle.setRadius(GreenBoxes.targetGetTower(target).getShootingRange());
			circle.setStyle("-fx-border-color: rgba(0,0,0,0.3); -fx-border-width: 3");
			break ; 
		case "Catapult" : 
			break ; 
		case "Laser Tower" : 
			break ; 
			default : 
				Alert alert = new Alert(AlertType.ERROR , "no such kind of tower") ; 
				alert.showAndWait() ; 
		}
		
	}
	
	
}
