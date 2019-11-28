package sample;

import org.junit.Test;
import org.junit.Assert;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import monster.Monster;
import monster.Monster.Direction;

public class UtilTest extends ApplicationTest {
	
	private Scene s;
	
	@FXML
	private AnchorPane paneArena;
	

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
	public void testMonsterChangeDirection() {
		clickOn("#buttonNextFrame");
		Monster monster = MyController.monsters.get(0);
		Direction org_direction = monster.getDirection();
		while(org_direction == monster.getDirection()) {
			clickOn("#buttonNextFrame");
		}
		Assert.assertEquals(Direction.RIGHT, monster.getDirection());

		
	}
}
