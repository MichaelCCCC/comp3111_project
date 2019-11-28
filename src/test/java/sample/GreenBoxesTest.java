package sample;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tower.Catapult;
import tower.LaserTower;
import tower.Tower;

public class GreenBoxesTest extends ApplicationTest {

	
	private Scene s;
	
	GreenBox generateGB() {
		Label newLabel = new Label() ; 
		GreenBox gb = new GreenBox(newLabel, 0, 0); 
		return gb ; 
	}

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
	public void testGreenBoxes() {
		GreenBoxes gbs = new GreenBoxes()  ; 
		Assert.assertNotNull(gbs);
	}
	
	@Test
	public void testTargetGetIndex() {
		//GreenBoxes gbs = new GreenBoxes() ; 
		Assert.assertNull(GreenBoxes.targetGetIndex(null) ); 
	}
	
	@Test
	public void testTargetGetGreenBox() {
		
	}
	
	@Test
	public void testTargetHasTower() {
		
	}
	
	@Test
	public void testTargetVH() {
		
		
	}
	
	@Test
	public void testTargetUpgradeTower() {
		
	}
	
	@Test
	public void testGreenBoxBuildTower() {
//		GreenBox gb = generateGB() ; 
//		GreenBoxes.gbs.add(gb)  ;
//		Assert.assertNotNull(gb.buildTower("Basic Tower"));
//		Assert.assertNotNull(gb.towerInBox)  ; 
//		Assert.assertNotNull(gb.id)  ;
//		gb.destroyTower() ; 
//		Assert.assertNotNull(gb.buildTower("Ice Tower")) ; 
//		Assert.assertNotNull(gb.towerInBox)  ; 
//		Assert.assertNotNull(gb.id)  ;
//		gb.destroyTower() ; 
//		Assert.assertNotNull(gb.buildTower("Catapult")) ; 
//		Assert.assertNotNull(gb.towerInBox)  ; 
//		Assert.assertNotNull(gb.id)  ;
//		gb.destroyTower() ;
//		Assert.assertNotNull(gb.buildTower("Laser Tower")) ; 
//		Assert.assertNotNull(gb.towerInBox)  ; 
//		Assert.assertNotNull(gb.id)  ;
//		Assert.assertNull(gb.buildTower("Basic Tower"));
//		gb.destroyTower()  ;
//		Assert.assertNull(gb.buildTower("ABC"));
		
	}
	
	@Test
	public void testTowerGetGreenBox() {
		GreenBox gb = generateGB() ; 
		gb.buildTower("Ice Tower") ;
		GreenBoxes.gbs.add(gb)  ; 
		if(GreenBoxes.towerGetGreenBox(gb.towerInBox) != gb)
			fail("testTowerGetGreenBox failed") ;
		Tower newTower = new Tower(0,0) ; 
		Catapult newC = new Catapult(0,0) ; 
		LaserTower newLT = new LaserTower(0,0) ; 
		
		if(GreenBoxes.towerGetGreenBox(newTower) != null)
			fail("testTowerGetGreenBox failed") ;
		if(GreenBoxes.towerGetGreenBox(newC) != null)
			fail("testTowerGetGreenBox failed") ;
		if(GreenBoxes.towerGetGreenBox(newLT) != null)
			fail("testTowerGetGreenBox failed") ;
		Assert.assertNotNull(gb.copyOfLabel()) ; 
		Assert.assertTrue(gb.destroyTower())  ;
		Assert.assertFalse(gb.destroyTower());	
	}

}
