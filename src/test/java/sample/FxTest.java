/**
 * 
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 * 
 */
package sample;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import monster.Monster;
import monster.Penguin;
import monster.Monster.Direction;
import javafx.fxml.FXMLLoader;
 

public class FxTest extends ApplicationTest {

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
        appController.createArena();   		
	}

	
	@Test
	public void testNextFrameButton() {
				
		clickOn("#buttonNextFrame");
		Assert.assertEquals(1,MyController.monsters.size());
		int y = MyController.monsters.get(0).getY();
		//Test monster move
		clickOn("#buttonNextFrame");
		Assert.assertNotEquals(y,MyController.monsters.get(0).getY());
		//Test Check direction
		Monster monster =  MyController.monsters.get(0)  ;
		Direction org_direction = monster.getDirection();
		while(org_direction == monster.getDirection()) {
			clickOn("#buttonNextFrame");
		}
		Assert.assertEquals(Direction.RIGHT, monster.getDirection());
		MyController.monsters.get(0).setDirection(Direction.LEFT);
		clickOn("#buttonNextFrame");

		
	}
	
	
	
	
}