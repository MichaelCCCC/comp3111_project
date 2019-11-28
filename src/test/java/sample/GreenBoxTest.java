package sample;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tower.Tower;

public class GreenBoxTest extends ApplicationTest{

	
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
	
	GreenBox generateGB() {
		Label newLabel = new Label() ; 
		GreenBox gb = new GreenBox(newLabel, 0, 0); 
		return gb ; 
	}
	
	@Test
	public void testGreenBox() {
		GreenBox gb = generateGB() ; 
		Assert.assertNotNull(gb);
		}
	
	@Test
	public void testDestroyTower() {
		GreenBox gb = generateGB() ; 
		Assert.assertFalse(gb.destroyTower());
		gb.towerInBox = new Tower((int)gb.getTowerX(), (int)gb.getTowerY()) ; 
		Assert.assertTrue(gb.destroyTower()) ; 
	}
	
	@Test
	public void testBuildTowerAndGetTowerXY() {
		Label newLabel = new Label() ; 
		GreenBox gb = new GreenBox(newLabel, 0, 0)  ; 
		Assert.assertNotNull(gb.buildTower("Tower") ); 
		Assert.assertEquals( MyController.GRID_WIDTH * ((double)0 + 0.5), gb.getTowerX(), 1);
		Assert.assertEquals( MyController.GRID_HEIGHT * ((double)0 + 0.5), gb.getTowerY(), 1);
	}
	
	@Test
	public void testBuildTowerAndGetLabelCopy() {
		Label newLabel = new Label() ; 
		GreenBox gb = new GreenBox(newLabel, 0, 0)  ; 
		Assert.assertNotNull(gb.buildTower("Basic Tower"));
		Assert.assertNotNull(gb.towerInBox)  ; 
		Assert.assertNotNull(gb.id)  ;
		gb.destroyTower() ; 
		Assert.assertNotNull(gb.buildTower("Ice Tower")) ; 
		Assert.assertNotNull(gb.towerInBox)  ; 
		Assert.assertNotNull(gb.id)  ;
		gb.destroyTower() ; 
		Assert.assertNotNull(gb.buildTower("Catapult")) ; 
		Assert.assertNotNull(gb.towerInBox)  ; 
		Assert.assertNotNull(gb.id)  ;
		gb.destroyTower() ;
		Assert.assertNotNull(gb.buildTower("Laser Tower")) ; 
		Assert.assertNotNull(gb.towerInBox)  ; 
		Assert.assertNotNull(gb.id)  ;
		Assert.assertNull(gb.buildTower("Basic Tower"));
		gb.destroyTower()  ;
		Assert.assertNull("ABC");
	}
	
	@Test
	public void getShootingRangeShape() {
		 GreenBox gb = generateGB()  ;
		 gb.buildTower("Basic Tower"); 
		 Assert.assertNotNull(gb.getShootingRangeShape(gb.towerInBox, gb.towerInBox.getClass()) );
		 gb.destroyTower()  ; 
		 gb.buildTower("Ice Tower") ; 
		 Assert.assertNotNull(gb.getShootingRangeShape(gb.towerInBox, gb.towerInBox.getClass()));
		 gb.destroyTower() ; 
		 gb.buildTower("Catapult") ;  
		 Assert.assertNotNull(gb.getShootingRangeShape(gb.towerInBox, gb.towerInBox.getClass()));
		 gb.destroyTower() ; 
		 Assert.assertNull(gb.getShootingRangeShape(null, null)) ; 
		 
	}

}
