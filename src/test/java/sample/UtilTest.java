package sample;

import org.junit.Test;
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
		
	}
	
	@Test
	public void testTowerAttack() {
		
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
		MyController.monsters.add(new Fox(new Label(), 0));
		MyController.monsters.get(0).move((int)(MyController.zones.get(1).getLayoutX() + 100), 300);
		
		Assert.assertTrue(util.decideEndGame());
	}
	
	@Test
	public void testGetTowerTooltipString() {
		
	}
	
	
	@Test
	public void testLineToFirstMonsterAlive() {
		
	}
	
	@Test 
	public void testLineToMonsterShot() {
		
	}
	
	@Test 
	public void testCheckMonsterDead() {
		
	}
	
	@Test
	public void testRemoveDeadMonster() {
		AnchorPane paneArena = new AnchorPane()  ;
		Fox temp = new Fox(new Label(), 0)  ;
		temp.setStatus(Status.DEAD);
		MyController.monsters.clear();  
		MyController.monsters.add(temp); 
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
	
	
}
