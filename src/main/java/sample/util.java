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

/**
 * @author Yomaru
 *
 */
class util {

	/**
	 * a list of shape record the relationship between monsters targeted and towers in the last frame
	 */
	static List<Shape> lastShootingShape = new ArrayList<>( ) ; 
	
	/**
	 * a function to move all monsters
	 * @param monsters
	 * @return whether the monsters are successfully moved
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
	 * a function to change the direction of a monster 
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
	
	/**
	 * A function to refresh the list of monsters and towers to make sure there isn't any null in the list.
	 * Then refresh every tooptip of monsters because monsters can be shot in this frame.
	 * @param a list of monsters
	 * @param a list of towers
	 */
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
	 * A function to make sure every elements in the list of monsters and towers are shown, no matter what the status of the elements are. 
	 * @param a list of monsters
	 * @param a list of towers
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
	 * a function that allow each tower to attack monsters, record the monsters which are attacked, and then show the relationship between the monsters and towers in this frame.
	 * @param the list of monsters
	 * @param the list of towers
	 * @param paneArena
	 */
	static void towersAttack(List<Monster> monsters, List<Tower> towers, AnchorPane paneArena) {
		if(monsters.size() == 0 || towers.size() == 0)
			return ; 
		
		
		for(int i = 0 ; i < towers.size() ; i++) {
			Tower tower = towers.get(i);
			List<Monster> monsterShooted  = null ; 
			

			if (tower.getTargetedMonster() != null )
				monsterShooted = tower.shoot() ; 
			if(monsterShooted == null) //the tower hit nothing
				continue ; 
			
			//if the tower hit somethings
			for(int j = 0 ; j < monsterShooted.size() ; j++) {
				Monster monster = monsters.get(j) ; 
				GreenBox gb = GreenBoxes.towerGetGreenBox(tower) ; 
				System.out.println( tower.name + "@(" + gb.getTowerX() + "," + gb.getTowerY() + ") -> " + monster.name + "@(" + monster.getX() + "," + monster.getY()+")");
				if(lineToMonsterShot(gb, false ) != null)
					lastShootingShape.add(lineToMonsterShot(gb, false )) ; 
				//the number of line to monsters shooted should be the same as monster shooted
			} 
			
		}
		for(int i = 0 ; i < lastShootingShape.size() ; i++)
		{
			lastShootingShape.get(i).setStyle("-fx-stroke: blue; -fx-stroke-dash-array: 3 5 3 5 3 5; -fx-stroke-width: 3;\n" + 
					"    -fx-stroke-dash-offset: 6;\n" + 
					"    -fx-stroke-line-cap: butt;");
			if(!paneArena.getChildren().contains(lastShootingShape.get(i)))
			paneArena.getChildren().add(lastShootingShape.get(i)) ; 
		}
		
	}
	
	/**
	 * A function to generate a monster randomly, and then show it on Arena
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
	 * A function to determine whether the game is ended. 
	 * @return whether the game is end
	 */
	static boolean decideEndGame() {
		for(int i = 0 ; i < MyController.monsters.size() ; i++) {
			if(MyController.monsters.get(i).reachEndZone() == true)
				return true ; 
		}
		return false;
	}
	
	/**
	 * A function to get tower initial information and then return it as a readable string 
	 * @param towerInformation
	 * @return a string of tower information
	 */
	static String getTowerTooltipString(TowerInformation towerInformation ) {
		String result = "" ; 
		result += towerInformation.name + "\n" ; 
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
	 * A function to get the current information of the tower
	 * @param towerInformation
	 * @param target: should be a label 
	 * @return tooltip
	 */
	static String getTowerTooltipString(TowerInformation towerInformation, Object target) {
		Class<? extends Tower> temp = GreenBoxes.targetGetTower(target).getClass() ; 
		Tower tower  = GreenBoxes.targetGetTower(target)  ; 
		TowerInformation ti = tower.getInfo() ; 
		String result = "" ; 
		result += ti.name + "\n"; 
		result += "attack power: " + ti.attack_power + "\n" ; 
		result += "building cost: " +ti.building_cost + "\n"  ;
		result += "upgrade cost: " + ti.upgrade_cost +"\n" ; 
		if( temp  == tower.LaserTower.class)
			result += "attack cost: " + ((tower.LaserTower)tower).getAttackCost() + "\n" ; 
		if (temp == tower.Tower.class || temp == tower.IceTower.class)
			result += "shooting range: " + ti.shooting_range + "\n"  ; 
		if  (temp == tower.Catapult.class)
			result += "shooting range: " + ((tower.Catapult)tower).shortDistance + " to " + ((tower.Catapult)tower).shortDistance + "\n"; 
		result += "upgrade difference: " + ti.upgrade_diff + "\n" ; 
		result += "tier: " + ti.tier + "\n" ; 
		result += "note: " + ti.comment + "\n" ; 
		return result ; 
	}
	
	/**
	 * A function to get the tooltip string of the tower source label 
	 * @param label
	 * @param towerInformation
	 * @param sources
	 * @return tooptip 
	 */
	static String sourceLabelGetTooltip(Label label, TowerInformation[] towerInformation, Label[] sources) {
		int towerType = -1 ; 
		for(int i = 0 ; i < sources.length ; i++)
			if(label == sources[i] ) towerType = i ; 


		return getTowerTooltipString(towerInformation[towerType]) ; 
}
	
	/**
	 * A function to generate a line from the green box to first monster alive in the monster list. If there is no monster in the list, it will return null.
	 * @param greenbox
	 * @return a line to first monster alive or null
	 */
	static Shape lineToFirstMonsterAlive(GreenBox gb) {
		if(MyController.monsters.size() > 0  )
		{
			Monster monster = null ; 
			for(int i = 0 ; i < MyController.monsters.size() ; i++)
				if(MyController.monsters.get(i).getStatus() == Status.ALIVE ) 
				{
					monster = MyController.monsters.get(i); 
					break  ; 
				}
			return new Line(gb.getTowerX(), gb.getTowerY() , monster.getX(),monster.getY()) ; 
		}
		return null ; 
	}
	
	/**
	 * A monster to the closest enemy
	 * @param green box that contain laser tower
	 * @return a line to monster shooted
	 */
	static Shape lineToMonsterShot(GreenBox gb, boolean infinite) {
		Monster monster  = gb.towerInBox.findClosestEnemy()  ; //in fact, this should be monster targeted
		
		if(monster == null)
			return null; 
		if(monster.getStatus() == Status.DEAD)
			return null ; 
		
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
	
	/**
	 * A function to scan through the list of monster to see whether the monster is dead or not,
	 * then change the status of monster to DEAD if yes
	 */
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
	 * A function to remove dead monster from the arena
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
	
	
	/**
	 * A function to remove last shooting shape from arena and then clear the list of shooting shape as well
	 * @param paneArena
	 */
	public static void removeLastShooting(AnchorPane paneArena) {
		for(int i = 0 ; i < lastShootingShape.size() ; i++)
			paneArena.getChildren().remove(lastShootingShape.get(i)) ; 
		lastShootingShape.clear(); ; 
	}
	
	
}
