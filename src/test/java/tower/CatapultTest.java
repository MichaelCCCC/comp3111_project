package tower;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import monster.Monster;
import monster.Monster.Status;
import sample.MyController;

 

public class CatapultTest extends ApplicationTest{
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
	
	@Test
	public void testFindClosestEnemy() {
		Tower t = new Catapult(0,0);
		Label lbl = new Label("L");
		
		MyController.monsters.clear();
		MyController.monsters.add(new Monster("m1",10, 2, lbl));
		MyController.monsters.get(0).move(17, 144);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(0));
		
		MyController.monsters.add(new Monster("m2", 10, 2, lbl));
		MyController.monsters.get(1).move(60, 91);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(1));
		
		MyController.monsters.add(new Monster("m3", 10, 2, lbl));
		MyController.monsters.get(2).move(88, 105);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(1));
		
		MyController.monsters.add(new Monster("m4", 10, 2, lbl));
		MyController.monsters.get(3).move(109, 0);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(3));
		
		MyController.monsters.add(new Monster("m5", 10, 2, lbl));
		MyController.monsters.get(4).move(23, 264);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(3));
		
		MyController.monsters.get(2).setStatus(Status.DEAD);
		assertEquals(MyController.monsters.get(2).getStatus(), Status.DEAD);
		
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(3));
	}
	
	@Test
	public void testTargetedMonster() {
		Tower t = new Catapult(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.getTargetedMonster(), null);
		
		MyController.monsters.add(new Monster("m1",10, 2, lbl));
		MyController.monsters.get(0).move(60, 91);
		
		MyController.monsters.add(new Monster("m2",10, 2, lbl));
		MyController.monsters.get(1).move(63, 95);
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
		
		MyController.monsters.add(new Monster("m3",10, 2, lbl));
		MyController.monsters.get(2).move(90, 100);
		
		MyController.monsters.add(new Monster("m4",10, 2, lbl));
		MyController.monsters.get(3).move(90, 100);
		MyController.monsters.get(3).setStatus(Status.DEAD);
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
	}
//	
	@Test
	public void testShoot() {
		Catapult t = new Catapult(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.shoot(), null); // No Target
		
		MyController.monsters.add(new Monster("m1",10, 2, lbl));
		MyController.monsters.get(0).move(100, 0);
		int prevHP = MyController.monsters.get(0).getHP();
		t.setShootingRange(100);
		assertEquals(t.shoot().get(0), MyController.monsters.get(0));
		assertTrue(MyController.monsters.get(0).getHP() < prevHP);

		assertEquals(t.getCooldown(), 1);
		assertEquals(t.shoot(), null); // Cooldown
		
		
	}
}