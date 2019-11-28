package sample;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
		AnchorPane paneArena = new AnchorPane()  ;
		Assert.assertNull(GreenBoxes.targetGetIndex(null) ); 
		GreenBoxes.gbs.clear(); 
		GreenBox gb = new GreenBox(new Label() , 0,0 )  ; 
		GreenBoxes.gbs.add(gb) ;
		gb.buildTower("Ice Tower") ; 
		MyController.towers.add(gb.towerInBox)  ;
		util.showAllObjects(MyController.monsters, MyController.towers, paneArena);
		Assert.assertNotNull(GreenBoxes.targetGetIndex(gb.gbLabel))  ;
		Assert.assertNotNull(GreenBoxes.targetGetGreenBox(gb.gbLabel));
		Assert.assertTrue(GreenBoxes.targetHasTower(gb.gbLabel));
		Assert.assertTrue(GreenBoxes.targetDestroyTower(gb.gbLabel)) ; 
		Assert.assertFalse(GreenBoxes.targetHasTower(gb.gbLabel))  ;
		GreenBoxes.targetV(gb.gbLabel) ; 
		GreenBoxes.targetH(gb.gbLabel);
		Assert.assertNotNull(GreenBoxes.targetBuildTower(gb.gbLabel, "Basic Tower"));
		Assert.assertTrue(GreenBoxes.targetUpgradeTower(gb.gbLabel)) ; 
		Assert.assertNotNull(GreenBoxes.targetGetTower(gb.gbLabel)) ; 
		
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
