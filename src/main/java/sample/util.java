package sample;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import monster.Fox;
import monster.Monster;
import monster.Monster.Direction;
import monster.Penguin;
import monster.Unicorn;
import tower.Tower;
import tower.TowerInformation;

class util {

	static boolean moveMonsters(List<Monster> monsters) {
		
		for(int i = 0 ; i < monsters.size() ; i++ ) {
			Monster monster = monsters.get(i);
			int x = monster.getX();
			int y = monster.getY();
			int speed = monster.getSpeed();
			changeDirection(monster);
			switch(monster.getDirection()) {
			case DOWN:
				y += speed;
				break;
			case UP:
				y -= speed;
				break;
			case RIGHT:
				x += speed;
				break;
			}
			monster.move(x, y);
		}
		return true;
			
	}
	static void changeDirection(Monster monster) {
		int x = monster.getX();
		int y = monster.getY();
		if(y == 20) {
			if(x > 20) {
				if(x%160 == 20) {
					monster.setDirection(Direction.DOWN);
				}
				if(x%160 == 100) {
					monster.setDirection(Direction.RIGHT);
				}
			}
		}
		if(y == 460) {
			if(x%160 == 20) {
				monster.setDirection(Direction.RIGHT);
			}
			if(x%160 == 100) {
				monster.setDirection(Direction.UP);
			}
		}
	}

	static void showAllObjects(List<Monster> monsters, List<Tower> towers ,AnchorPane paneArena) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < monsters.size() ; i++ )
			if(!paneArena.getChildren().contains(monsters.get(i).getLabel())) // if the arean haven't show it, show it
				paneArena.getChildren().add(monsters.get(i).getLabel()) ; 
		
		for(int i = 0 ; i < towers.size() ; i++ )
//			if(!paneArena.getChildren().contains(towers.get(i).getLabel()))
//				paneArena.getChildren().add(towers.get(i).getLabel()) ; 
			if(!paneArena.getChildren().contains(GreenBoxes.towerGetGreenBox(towers.get(i)).gbLabel))
				paneArena.getChildren().add(GreenBoxes.towerGetGreenBox(towers.get(i)).gbLabel) ; 
	}

	static void towersAttack(List<Monster> monsters, List<Tower> towers) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < towers.size() ; i++) {
			towers.get(i).attack() ; 
		}
		
	}

	static void generateMonsters(AnchorPane paneArena) {
		int i = (int)(Math.random()*((2)+1));
		String id = "";
		String type[] = {"Fox","Unicorn","Penguin"};
			
	     Label newLabel = new Label();
	     newLabel.setLayoutX(10);
	     newLabel.setLayoutY(10);
	     newLabel.setMinWidth(1);
	     newLabel.setMaxWidth(1);
	     newLabel.setMinHeight(1);
	     newLabel.setMaxHeight(1);
	      
	     ImageView iv = ImageFunction.setImageView(type[i]) ; 
	     newLabel.setGraphic(iv);
	     
	     paneArena.getChildren().addAll(newLabel);
	     
	     Monster monster = null;
	     switch(i) {
	     	case 0:
	     		monster = new Fox(newLabel);
	     		break;
	     	case 1:
	     		monster = new Unicorn(newLabel);
	     		break;
	     	case 2:
	     		monster = new Penguin(newLabel);
			
	     }
	    MyController.monsters.add(monster);
		//Tooltip.install(monster.getLabel(), new Tooltip(getObjectTooltip(monster.getLabel())) ) ; 
	    Tooltip.install(monster.getLabel(), new Tooltip(monster.getTooltip())) ; 
		
	}

	static boolean decideEndGame() {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	
	static String getTowerTooltipString(TowerInformation towerInformation ) {
		String result = "" ; 
		result += "attack power: " + towerInformation.attack_power + "\n" ; 
		result += "building cost: " + towerInformation.building_cost + "\n"  ;
		result += "upgrade cost: " + towerInformation.upgrade_cost +"\n" ; 
		result += "shooting range: " + towerInformation.shooting_range + "\n"  ; 
		result += "upgrade difference: " + towerInformation.upgrade_diff + "\n" ; 
		result += "tier: " + towerInformation.tier + "\n" ; 
		result += "note: " + towerInformation.comment + "\n" ; 
		return result ; 
	}
	
	static String getInitTooltip(Label label, TowerInformation[] towerInformation, Label[] sources) {
		int towerType = -1 ; 
		for(int i = 0 ; i < sources.length ; i++)
			if(label == sources[i] ) towerType = i ; 


		return getTowerTooltipString(towerInformation[towerType]) ; 
}
	
	static String getObjectTooltip(Label label) {
		String tooltip = null ; 
		for(int i = 0 ; i < MyController.monsters.size() ; i++  )
			if(MyController.monsters.get(i).getLabel() == label) 
				tooltip = MyController.monsters.get(i).getTooltip()  ;
				 

		
		for(int i = 0 ; i < MyController.towers.size() ; i ++ )
			if(GreenBoxes.towerGetGreenBox(MyController.towers.get(i)).gbLabel == label )
				tooltip = getTowerTooltipString(MyController.towers.get(i).getInfo() ); 
    	return tooltip ; 
    }
	
	
	
}
