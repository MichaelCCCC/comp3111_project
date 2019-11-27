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

 

public class LaserTest extends ApplicationTest{
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
	public void testSetAttCost() {
		LaserTower t = new LaserTower(0,0);
		t.setAttackCost(10);
		assertEquals(t.getAttackCost(), 10);
	}
	@Test
	public void testTargetedMonster() {
		Tower t = new LaserTower(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.getTargetedMonster(), null);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(5, 5);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(1).move(10, 10);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(2).move(15, 15);
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
		assertEquals(t.getTargetedMonster().get(2), MyController.monsters.get(2));
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(3).move(-5, -5);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(4).move(-5, 5);

		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
		assertEquals(t.getTargetedMonster().get(2), MyController.monsters.get(2));
		
		
		
		MyController.monsters.clear();
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(0, 2);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(1).move(0, 5);
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));

		
		
		MyController.monsters.clear();
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(0, -2);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(1).move(0, -5);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(2).move(0, -5);
		MyController.monsters.get(2).setStatus(Status.DEAD);
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
	}
	
	@Test
	public void testShoot() {
		Tower t = new LaserTower(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.shoot(), null); // No Target
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(5, 5);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(1).move(10, 10);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(2).move(15, 15);
		
		int prevHP1 = MyController.monsters.get(0).getHP();
		int prevHP2 = MyController.monsters.get(1).getHP();
		int prevHP3 = MyController.monsters.get(2).getHP();
		
		t.shoot();
		
		assertEquals(t.getTargetedMonster().get(0), MyController.monsters.get(0));
		assertEquals(t.getTargetedMonster().get(1), MyController.monsters.get(1));
		assertEquals(t.getTargetedMonster().get(2), MyController.monsters.get(2));
		
		assertTrue(MyController.monsters.get(0).getHP() < prevHP1);
		assertTrue(MyController.monsters.get(1).getHP() < prevHP2);
		assertTrue(MyController.monsters.get(2).getHP() < prevHP3);
	}
}