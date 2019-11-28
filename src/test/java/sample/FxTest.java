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
		for(int i = 0 ; i < 10 ; i++ )
			clickOn("#buttonNextFrame");
//		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//		Assert.assertEquals(1,MyController.monsters.size());
//		int y = MyController.monsters.get(0).getY();
//		//Test monster move
//		clickOn("#buttonNextFrame");
//		Assert.assertNotEquals(y,MyController.monsters.get(0).getY());
		
	}
	
	@Test
	public void testSetLabelMoney() {
		MyController.money  = 100; 
	}
	
	
	
	
	
	
}