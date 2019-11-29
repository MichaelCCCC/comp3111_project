package sample;

import org.junit.Test;

import java.util.List;

import org.junit.Assert;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import monster.Fox;
import monster.Monster;
import monster.Monster.Direction;
import monster.Monster.Status;
import tower.Tower;

public class UtilTest extends ApplicationTest {
	
	private Scene s;


	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        MyController appController = (MyController)loader.getController();
        appController.createArena(); 
	}
	

//	@Test
//	public void testMonsterChangeDirection() {
//		for(int i = 0 ; i < 5 ; i++)
//			clickOn("#buttonNextFrame");
//		Monster monster =  MyController.monsters.get(0)  ;
//		Direction org_direction = monster.getDirection();
//		while(org_direction == monster.getDirection()) {
//			clickOn("#buttonNextFrame");
//		}
//		Assert.assertEquals(Direction.RIGHT, monster.getDirection());
//
//	}
	
	@Test
	public void testUtil() {
		util temp = new util ()  ; 
		Assert.assertNotNull(temp);
	}
	
	@Test
	public void testMoveMonster() {
		MyController.monsters.clear();
		Assert.assertTrue(util.moveMonsters(MyController.monsters)) ;
		for(int i = 0 ; i < 10 ; i++ ) {
			MyController.monsters.add(new Fox(new Label() , 0 )) ; 
		}
		Assert.assertEquals(10, MyController.monsters.size())  ;
		for(int i = 0 ; i < 10 ; i++)
			Assert.assertTrue(util.moveMonsters(MyController.monsters)) ;
		MyController.monsters.get(0).setDirection(null);  ; 
		//Assert.assertTrue(util.moveMonsters(MyController.monsters)) ;
		
	}
	
	@Test 
	public void testChangeDirection() {
		MyController.monsters.clear()  ;
		Fox temp = new Fox(new Label(), 0 )  ;
		MyController.monsters.add(temp)  ;
		temp.move(180, 20);
		Assert.assertTrue(util.moveMonsters(MyController.monsters)) ; 		
		temp.move(100, 20);  
		Assert.assertTrue(util.moveMonsters(MyController.monsters)) ;
		temp.move(180, 460);
		Assert.assertTrue(util.moveMonsters(MyController.monsters)) ; 
		temp.move(100, 460);
		Assert.assertTrue(util.moveMonsters(MyController.monsters)) ; 
		
	}
	
	@Test
	public void testShowAllObject() {
		AnchorPane paneArena = new AnchorPane() ; 
		MyController.monsters.clear()  ;
		Fox temp = new Fox(new Label(), 0 )  ;
		MyController.monsters.add(temp)  ;
		MyController.towers.clear();  
		GreenBoxes.gbs.clear(); 
		GreenBoxes.gbs.add(new GreenBox(new Label(), 0 ,0 )); 
		GreenBoxes.gbs.get(0).buildTower("Ice Tower") ; 
		MyController.towers.add(GreenBoxes.gbs.get(0).towerInBox) ; 
		if(MyController.monsters.get(0).getLabel() == null)
			Assert.fail("")  ;
		
		MyController.monsters.add(null) ;
		//Fox temp2 = new Fox(null , 0 )  ;
		
		//MyController.towers.add(null) ; 
		util.showAllObjects(MyController.monsters, MyController.towers, paneArena);
		Tower tempT = new Tower(0,0 ) ; 
		MyController.towers.add(tempT) ; 
		util.showAllObjects(MyController.monsters, MyController.towers, paneArena);
		
		tempT = null  ;
		if(!MyController.towers.contains(tempT)) 
			tempT = new Tower(0,0 )  ; 
		util.showAllObjects(MyController.monsters, MyController.towers, paneArena);
  	}
	
	@Test
	public void testTowerAttack() {
		MyController.monsters.clear();  
		MyController.towers.clear();  
		List<Monster> monsters = MyController.monsters; 
		List<Tower> towers = MyController.towers;
		AnchorPane paneArena = new AnchorPane ()  ;
		util.towersAttack(monsters, towers, paneArena);
		GreenBoxes.gbs.clear() ; 
		for(int i = 0 ; i < 4 ; i++ )
			GreenBoxes.gbs.add(new GreenBox(new Label(), 0 ,0 )); 
		GreenBoxes.gbs.get(0).buildTower("Basic Tower") ; 
		GreenBoxes.gbs.get(1).buildTower("Ice Tower") ; 
		GreenBoxes.gbs.get(2).buildTower("Catapult") ; 
		GreenBoxes.gbs.get(3).buildTower("Laser Tower") ; 
		for(int i =  0 ; i < 4 ; i++ ) 
				towers.add(GreenBoxes.gbs.get(0).towerInBox)  ;
		
		util.towersAttack(monsters, towers, paneArena);
		for(int i = 0 ; i < 10 ; i++)
			util.generateMonsters(paneArena, 0);
		for(int i = 0 ; i < 10 ; i++) {
			util.moveMonsters(monsters)  ;
			util.towersAttack(monsters, towers, paneArena);
		}
		
		
		
	}
	
	@Test
	public void testGenerateMonsters() {
		AnchorPane paneArena = new AnchorPane()  ;
		for(int i = 0 ; i < 10 ; i++)
			util.generateMonsters(paneArena, i);
	}
	
	@Test 
	public void testEndGame() { 
		MyController.monsters.clear();  
		Assert.assertFalse(util.decideEndGame());
		MyController.monsters.add(new Fox(new Label(), 0));
		MyController.monsters.get(0).move((int)(MyController.zones.get(1).getLayoutX() + 100), 300);
		
		Assert.assertTrue(util.decideEndGame());
	}
	
	@Test
	public void testGetTowerTooltipString() {
		GreenBox gb = new GreenBox(new Label() , 0 ,  0)  ;
		GreenBoxes.gbs.clear();  
		GreenBoxes.gbs.add(gb); 
		gb.buildTower("Basic Tower") ; 
		Assert.assertNotNull(util.getTowerTooltipString(gb.towerInBox.getInfo(), gb.gbLabel));
		gb.destroyTower() ; 
		gb.buildTower("Ice Tower") ; 
		Assert.assertNotNull(util.getTowerTooltipString(gb.towerInBox.getInfo(), gb.gbLabel));
		gb.destroyTower() ; 
		gb.buildTower("Catapult") ;
		Assert.assertNotNull(util.getTowerTooltipString(gb.towerInBox.getInfo(), gb.gbLabel));
		gb.destroyTower() ; 
		gb.buildTower("Laser Tower") ; 
		Assert.assertNotNull(util.getTowerTooltipString(gb.towerInBox.getInfo(), gb.gbLabel));
	}
	
	
	@Test
	public void testLineToFirstMonsterAlive() {
		MyController.monsters.clear();  
		GreenBox gb = new GreenBox(new Label() , 0 , 0) ; 
		Fox temp = new Fox( new Label() ,  0 )  ;
		MyController.monsters.add(temp ); 
		Assert.assertNotNull(util.lineToFirstMonsterAlive(gb)) ; 
		temp.setStatus(Status.DEAD);
		Assert.assertNull(util.lineToFirstMonsterAlive(gb));
		MyController.monsters.remove(temp )  ;
		Assert.assertNull(util.lineToFirstMonsterAlive(gb));
	}
	
	@Test 
	public void testLineToMonsterShot() {
		GreenBox gb = new GreenBox(new Label() , 0 , 0) ; 
		Fox temp = new Fox( new Label() ,  0 )  ;
		Assert.assertNull(util.lineToMonsterShot(gb, null, true)) ;
		Assert.assertNull(util.lineToMonsterShot(gb, null, false)) ; 
		Assert.assertNotNull(util.lineToMonsterShot(gb, temp, true)); 
		Assert.assertNotNull(util.lineToMonsterShot(gb, temp, false)) ; 
		temp.setStatus(Status.DEAD);
		Assert.assertNull(util.lineToMonsterShot(gb, temp, true)) ;
		Assert.assertNull(util.lineToMonsterShot(gb, temp, false));
		
	}
	
	@Test 
	public void testCheckMonsterDead() {
		MyController.monsters.clear()  ; 
		Fox temp = new Fox(new Label() , 0);
		MyController.monsters.add(temp ); 
		util.checkMonsterDead();
		temp.damage(100);
		util.checkMonsterDead(); 
		util.checkMonsterDead();
	}
	
	@Test
	public void testRemoveDeadMonster() {
		AnchorPane paneArena = new AnchorPane()  ;
		Fox temp = new Fox(new Label(), 0)  ;
		MyController.monsters.clear();  
		MyController.monsters.add(temp); 
		util.removeDeadMonster(paneArena);
		temp.setStatus(Status.DEAD);
		paneArena.getChildren().add(temp.getLabel())  ;
		util.removeDeadMonster(paneArena);
	}
	
	@Test
	public void testRemoveLastingShootingShape() {
		util.lastShootingShape.clear()  ;
		util.lastShootingShape.add(new Circle())  ;
		AnchorPane paneArena = new AnchorPane()  ; 
		paneArena.getChildren().addAll(util.lastShootingShape) ; 
		util.removeLastShooting(paneArena);
	}
	
	@Test
	public void testMain() {
		Main temp = new Main() ; 
		Assert.assertNotNull(temp)  ;
		//Main.main(null);
	}
	

}
