/**
 * 
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 * 
 */
package monster;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.ImageFunction;
import sample.MyController;
import javafx.fxml.FXMLLoader;
 

public class PenguinTest extends ApplicationTest {

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
	public void testPenguinMove() {
		double gridX = 1/4  ;
		double gridY = 1/4 ; 
		Label monsterLabel = ImageFunction.setImageToLabel("Penguin", gridX, gridY) ;
			
		Penguin monster = new Penguin(monsterLabel, 0);
		monster.damage(2);
		monster.move(20, 40);
		Assert.assertEquals(monster.getHP(),10);
	}
	
	@Test
	public void testPenguinHeal() {
		double gridX = 1/4  ;
		double gridY = 1/4 ; 
		Label monsterLabel = ImageFunction.setImageToLabel("Penguin", gridX, gridY) ;
			
		Penguin monster = new Penguin(monsterLabel, 0);
		monster.heal(2);
		Assert.assertEquals(monster.getHP(),12);
	}


}