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
import monster.Fox;
import monster.Monster;
import monster.Monster.Status;
import sample.MyController;

 

public class IceTest extends ApplicationTest{
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
		Tower t = new IceTower(0,0);
		Label lbl = new Label("L");
		
		MyController.monsters.clear();
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(8, 15);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(0));
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(1).move(5, 12);
		assertEquals(MyController.monsters.get(1).getX(), 5);
		assertEquals(MyController.monsters.get(1).getY(), 12);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(1));
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(2).move(7, 24);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(1));
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(3).move(13, 0);
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(3));
		
		MyController.monsters.get(2).setStatus(Status.DEAD);
		assertEquals(MyController.monsters.get(2).getStatus(), Status.DEAD);
		
		assertEquals(t.findClosestEnemy(), MyController.monsters.get(3));
	}
	
	@Test
	public void testTargetedMonster() {
		Tower t = new IceTower(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.getTargetedMonster(), null);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(8, 15);
		t.setShootingRange(100);
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		
		t.setShootingRange(10);
		assertEquals(t.getTargetedMonster(),null);
	}
	
	@Test
	public void testShoot() {
		Tower t = new IceTower(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.shoot(), null);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(8, 15);
		int prevSpd = MyController.monsters.get(0).getSpeed();
		t.setShootingRange(100);
		t.shoot();
		assertTrue(t.shoot().get(0).getSpeed() < prevSpd);
	}
}