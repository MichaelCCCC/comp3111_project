package sample;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import monster.Fox;
import monster.Monster;
import monster.Monster.Direction;
import monster.Monster.Status;
import monster.Penguin;
import monster.Unicorn;
import tower.Tower;
import tower.TowerInformation;

class util {

	static List<Shape> lastShootingShape = new ArrayList<>( ) ; 
	
	/**
	 * @param monsters
	 * @return
	 */
	
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
	/**
	 * @param monster
	 */
	private static void changeDirection(Monster monster) {
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
	
	private static void refreshLists(List<Monster> monsters, List <Tower> towers) {
		
		for(int i = 0 ; i < monsters.size() ; i++)
			if(monsters.get(i) == null || monsters.get(i).getLabel() == null)
				monsters.remove(i) ; 
		
		for(int i = 0 ; i < towers.size() ; i++) {
			if(towers.get(i) == null )
				towers.remove(i) ;
			if(GreenBoxes.towerGetGreenBox(towers.get(i)) == null)
				towers.remove(i) ; 
		} 
		
		//refresh tooltip of monters, but refresh tooltip of towers is done through tempLabel
		for(int i = 0 ; i < monsters.size(); i++ ) 
			Tooltip.install(monsters.get(i).getLabel(),new Tooltip(monsters.get(i).getTooltip()) );
	}

	/**
	 * @param monsters
	 * @param towers
	 * @param paneArena
	 */
	static void showAllObjects(List<Monster> monsters, List<Tower> towers ,AnchorPane paneArena) {
		refreshLists(monsters, towers)  ; 
		for(int i = 0 ; i < monsters.size() ; i++ )
			if(!paneArena.getChildren().contains(monsters.get(i).getLabel())) // if the arean haven't show it, show it
				paneArena.getChildren().add(monsters.get(i).getLabel()) ; 
		
		for(int i = 0 ; i < towers.size() ; i++ )
			if(!paneArena.getChildren().contains(GreenBoxes.towerGetGreenBox(towers.get(i)).gbLabel))
				paneArena.getChildren().add(GreenBoxes.towerGetGreenBox(towers.get(i)).gbLabel) ; 
	}

	/**
	 * @param monsters
	 * @param towers
	 */
	static void towersAttack(List<Monster> monsters, List<Tower> towers, AnchorPane paneArena) {
		if(monsters.size() == 0 || towers.size() == 0)
			return ; 
		
		
		for(int i = 0 ; i < towers.size() ; i++) {
			Tower tower = towers.get(i);
			List<Monster> monsterShooted  = null ; 
			
			monsterShooted = tower.shoot() ; 
			if(monsterShooted == null) //the tower hit nothing
				continue ; 
			
			//if the tower hit somethings
			for(int j = 0 ; j < monsterShooted.size() ; j++) {
				Monster monster = monsters.get(j) ; 
				GreenBox gb = GreenBoxes.towerGetGreenBox(tower) ; 
				System.out.println( tower.name + "@(" + gb.getTowerX() + "," + gb.getTowerY() + ") -> " + monster.name + "@(" + monster.getX() + "," + monster.getY()+")");
				lastShootingShape.add(lineToMonsterShooted(gb, false )) ; 
				//the number of line to monsters shooted should be the same as monster shooted
			} 
			
			
		
			for(int j = 0 ; j < lastShootingShape.size() ; j++)
			{
				lastShootingShape.get(i).setStyle("-fx-stroke: blue; -fx-stroke-dash-array: 3 5 3 5 3 5; -fx-stroke-width: 3;\n" + 
						"    -fx-stroke-dash-offset: 6;\n" + 
						"    -fx-stroke-line-cap: butt;");
				if(!paneArena.getChildren().contains(lastShootingShape.get(i)))
				paneArena.getChildren().add(lastShootingShape.get(i)) ; 
			}
		}
		
	}
	
	/**
	 * @param paneArena
	 */
	static void generateMonsters(AnchorPane paneArena) {
		int i = (int)(Math.random()*((2)+1));
		String type[] = {"Fox","Unicorn","Penguin"};
		double gridX = 1/4  ;
		double gridY = 1/4 ; 
		Label monsterLabel = ImageFunction.setImageToLabel(type[i], gridX, gridY) ; 
		
	     
	     paneArena.getChildren().addAll(monsterLabel);
	     
	     Monster monster = null;
	     switch(i) {
	     	case 0:
	     		monster = new Fox(monsterLabel);
	     		break;
	     	case 1:
	     		monster = new Unicorn(monsterLabel);
	     		break;
	     	case 2:
	     		monster = new Penguin(monsterLabel);
			
	     }
	    MyController.monsters.add(monster);
	    Tooltip.install(monster.getLabel(), new Tooltip(monster.getTooltip())) ; 
	    System.out.println("<type>" + type[i] + ":<" + monster.getHP() + "> generated" ) ; 
		
	}

	/**
	 * @return
	 */
	static boolean decideEndGame() {
		for(int i = 0 ; i < MyController.monsters.size() ; i++) {
			if(MyController.monsters.get(i).reachEndZone() == true)
				return true ; 
		}
		return false;
	}
	
	/**
	 * @param towerInformation
	 * @return
	 */
	static String getTowerTooltipString(TowerInformation towerInformation ) {
		String result = "" ; 
		result += towerInformation.name ; 
		result += "attack power: " + towerInformation.attack_power + "\n" ; 
		result += "building cost: " + towerInformation.building_cost + "\n"  ;
		result += "upgrade cost: " + towerInformation.upgrade_cost +"\n" ; 
		if(towerInformation.name != tower.LaserTower.NAME ) 
			result += "shooting range: " + towerInformation.shooting_range + "\n"  ; 
		result += "upgrade difference: " + towerInformation.upgrade_diff + "\n" ; 
		result += "tier: " + towerInformation.tier + "\n" ; 
		result += "note: " + towerInformation.comment + "\n" ; 
		return result ; 
	}
	
	/**
	 * @param towerInformation
	 * @param target: should be a label 
	 * @return tooltip
	 */
	static String getTowerTooltipString(TowerInformation towerInformation, Object target) {
		Class<? extends Tower> temp = GreenBoxes.targetGetTower(target).getClass() ; 
		String result = "" ; 
		result += towerInformation.name ; 
		result += "attack power: " + towerInformation.attack_power + "\n" ; 
		result += "building cost: " + towerInformation.building_cost + "\n"  ;
		result += "upgrade cost: " + towerInformation.upgrade_cost +"\n" ; 
		if( temp  == tower.LaserTower.class)
			result += "attack cost: " + ((tower.LaserTower)GreenBoxes.targetGetTower(target)).getAttackCost() + "\n" ; 
		if (temp == tower.Tower.class || temp == tower.IceTower.class)
			result += "shooting range: " + towerInformation.shooting_range + "\n"  ; 
		if  (temp == tower.Catapult.class)
			result += "shooting range: " + ((tower.Catapult)GreenBoxes.targetGetTower(target)).shortDistance + " to " + ((tower.Catapult)GreenBoxes.targetGetTower(target)).shortDistance + "\n"; 
		result += "upgrade difference: " + towerInformation.upgrade_diff + "\n" ; 
		result += "tier: " + towerInformation.tier + "\n" ; 
		result += "note: " + towerInformation.comment + "\n" ; 
		return result ; 
	}
	
	/**
	 * @param label
	 * @param towerInformation
	 * @param sources
	 * @return
	 */
	static String sourceLabelGetTooltip(Label label, TowerInformation[] towerInformation, Label[] sources) {
		int towerType = -1 ; 
		for(int i = 0 ; i < sources.length ; i++)
			if(label == sources[i] ) towerType = i ; 


		return getTowerTooltipString(towerInformation[towerType]) ; 
}
	
	static Shape lineToFirstMonster(GreenBox gb) {
		if(MyController.monsters.size() != 0 )
		{
			Monster monster = MyController.monsters.get(0) ; 
			return new Line(gb.getTowerX(), gb.getTowerY() , monster.getX(),monster.getY()) ; 
		}
		return null ; 
	}
	
	/**
	 * @param green box that contain laser tower
	 * @return a line to monster shooted
	 */
	static Shape lineToMonsterShooted(GreenBox gb, boolean infinite) {
		Monster monster  = gb.towerInBox.getMonstershooted() ; 
		
		if(monster == null )
			return null; 
		double x = gb.getTowerX() ; 
		double y = gb.getTowerY() ; 
		double xp = (monster.getX()) ; 
		double yp = (monster.getY() ); 
		//k is the multiple constant
		int k = (infinite == true)? 1000 : 1 ; 
		double xpp = k * (xp - x ) + x ; 
		double ypp = k* (yp - y ) + y ; 
		Line line = new Line(x , y ,xpp ,ypp) ; 
		
		line.setId(gb.id);
		return line;
		//return lineToFirstMonster(gb) ;
	}
	
	static void checkMonsterDead() {
		for(int i = 0; i<MyController.monsters.size(); i++) {
			Monster monster = MyController.monsters.get(i);
			if(monster.getStatus() == Status.ALIVE) {
				int HP = monster.getHP();
				if(HP <= 0) {
					ImageView iv = ImageFunction.setImageView("Collision"); 
					monster.getLabel().setGraphic(iv);
					monster.setStatus(Status.DEAD);
				}
			}
		}
	}
	/**
	 * @param paneArena
	 */
	static void removeDeadMonster(AnchorPane paneArena) {
		for(int 
				i = 0; i<MyController.monsters.size(); i++) {
			Monster monster = MyController.monsters.get(i);
			if(monster.getStatus() == Status.DEAD) {
				paneArena.getChildren().remove(monster.getLabel());
				MyController.monsters.remove(monster);
			}
		}
	}
	public static void removeLastShooting(AnchorPane paneArena) {
		for(int i = 0 ; i < lastShootingShape.size() ; i++)
			paneArena.getChildren().remove(lastShootingShape.get(i)) ; 
		lastShootingShape.clear(); ; 
	}
	
	
}
