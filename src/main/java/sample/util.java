package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;
import monster.Monster;

class util {

	
	static void showTowerRange(Label target) {
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
//			circle.setRadius(GreenBoxes.targetGetTower(target).getShootingRange());
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

	static void moveMonsters() {
		// TODO Auto-generated method stub
		
	}

	public static void towersAttack() {
		// TODO Auto-generated method stub
		
	}

	public static void generateMonsters() {
		// TODO Auto-generated method stub
		Monster monster = new Monster () ; 
		Tooltip.install(monster.getLabel(), new Tooltip(getObjectTooltip(monster.getLabel())) ) ; 
		
	}

	public static boolean decideEndGame() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static String getObjectTooltip(Label label) {
    	String result = "" ; 
    	return result; 
    }
	
	
	
}
