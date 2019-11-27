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

 

public class TowerTest extends ApplicationTest{
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
	public void testContor() {
		Tower t = new Tower(3,4);
		assertEquals((int)t.getX(), 3);
		assertEquals((int)t.getY(), 4);
	}
	
	@Test
	public void testModifier() {
		Tower t = new Tower(3,4);
		t.setAttackPower(126);
		assertEquals(t.getInfo().attack_power, 126);
		t.setBuildingCost(1000);
		assertEquals(t.getInfo().building_cost, 1000);
		t.setComment("CMT");
		assertEquals(t.getInfo().comment, "CMT");
		t.setShootingRange(144);
		assertEquals(t.getInfo().shooting_range, 144);
		t.setTier(4);
		assertEquals(t.getInfo().tier, 4);
		t.setUpgradeCost(1500);
		assertEquals(t.getInfo().upgrade_cost, 1500);
		t.setUpgradeDiff(560);
		assertEquals(t.getInfo().upgrade_diff, 560);
		t.setAttributes(123, 456, 789, 101, 112, 1, "CMT2");
		assertEquals(t.getInfo().attack_power, 123);
		assertEquals(t.getInfo().building_cost, 456);
		assertEquals(t.getInfo().upgrade_cost, 789);
		assertEquals(t.getInfo().shooting_range, 101);
		assertEquals(t.getInfo().upgrade_diff, 112);
		assertEquals(t.getInfo().tier, 1);
		assertEquals(t.getInfo().comment, "CMT2");
	}
	
	@Test
	public void testUpgrade() {
		Tower t = new Tower(3,4);
		int oldTier = t.getInfo().tier;
		int oldAttPow = t.getInfo().attack_power;
		t.upgrade();
		assertEquals(t.getInfo().tier, oldTier+1);
		assertEquals(t.getInfo().attack_power, oldAttPow+t.getInfo().upgrade_diff);
	}
	
	@Test
	public void testDestroy() {
		Tower t = new Tower(3,4);
		assertEquals(t.isDestroyed(), false);
		t.destroy();
		assertEquals(t.isDestroyed(), true);
	}
	
	@Test
	public void testFindClosestEnemy() {
		Tower t = new Tower(0,0);
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
		Tower t = new Tower(0,0);
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
		Tower t = new Tower(0,0);
		Label lbl = new Label("");
		MyController.monsters.clear();
		
		assertEquals(t.shoot(), null);
		
		MyController.monsters.add(new Fox(lbl,0));
		MyController.monsters.get(0).move(8, 15);
		int prevHP = MyController.monsters.get(0).getHP();
		t.setShootingRange(100);
		t.shoot();
		assertTrue(t.shoot().get(0).getHP() < prevHP);
	}
}