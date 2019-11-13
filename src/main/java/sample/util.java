package sample;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;
import monster.Monster;
import tower.Tower;

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

	static boolean moveMonsters(List<Monster> monsters) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < monsters.size() ; i++ )
			monsters.get(i).move() ;  
		return false ; 
	}

	static void showAllObjects(List<Monster> monsters, List<Tower> towers ,AnchorPane paneArena) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < monsters.size() ; i++ )
			if(!paneArena.getChildren().contains(monsters.get(i).getLabel())) // if the arean haven't show it, show it
				paneArena.getChildren().add(monsters.get(i).getLabel()) ; 
		
		for(int i = 0 ; i < towers.size() ; i++ )
			if(!paneArena.getChildren().contains(towers.get(i).getLabel()))
				paneArena.getChildren().add(towers.get(i).getLabel()) ; 
		
	}

	static void towersAttack(List<Monster> monsters, List<Tower> towers) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < towers.size() ; i++) {
			towers.get(i).attack(monsters) ; 
		}
		
	}

	static void generateMonsters(AnchorPane paneArena) {
		// TODO Auto-generated method stub 
		Monster monster = new Monster() ; 
		MyController.monsters.add(monster) ; 
		Tooltip.install(monster.getLabel(), new Tooltip(getObjectTooltip(monster.getLabel())) ) ; 
		paneArena.getChildren().addAll(monster.getLabel()); //show the monster
	}

	static boolean decideEndGame() {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	
	static String getObjectTooltip(Label label) {
		String tooltip = null ; 
		for(int i = 0 ; i < MyController.monsters.size() ; i++  )
			if(MyController.monsters.get(i).getLabel() == label)
				tooltip = MyController.monsters.get(i).getTooltip()  ;
		
		for(int i = 0 ; i < MyController.towers.size() ; i ++ )
			if(MyController.towers.get(i).getLabel() == label )
				tooltip = MyController.towers.get(i).getTooltip() ; 
    	return tooltip ; 
    }
	
	
	
}
