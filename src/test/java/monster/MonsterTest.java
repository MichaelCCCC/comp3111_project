package monster;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.FxTest;
import sample.ImageFunction;
import sample.MyController;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.sun.javafx.scene.traversal.Direction;

public class MonsterTest extends ApplicationTest {

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
	public void testMonsterFox() {
		Label monsterLabel = null;
		Monster fox = new Fox(monsterLabel,0);
//		Assert.assertEquals("Fox", fox.getClass().getSimpleName());
		Assert.assertNotNull(fox);
	}
	@Test
	public void testMonsterPenguin() {
		Label monsterLabel = null;
		Monster penguin = new Penguin(monsterLabel,0);
		Assert.assertNotNull(penguin);
	}
	@Test
	public void testMonsterUnicorn() {
		Label monsterLabel = null;
		Monster unicorn = new Unicorn(monsterLabel,0);
		Assert.assertNotNull(unicorn);
	}
	@Test
	public void testMonsterGetHP() {
		double gridX = 1/4  ;
		double gridY = 1/4 ; 
		Label monsterLabel = ImageFunction.setImageToLabel("Fox", gridX, gridY) ;
			
		Monster monster = new Fox(monsterLabel, 0);
		Assert.assertEquals(monster.getHP(), 5);
		Assert.assertEquals(monster.getSpeed(), 40);
		Assert.assertEquals(monster.getDirection(), Monster.Direction.DOWN);
	}
	@Test
	public void testMonsterGetSpeed() {
		Label monsterLabel = null;
		Monster fox = new Fox(monsterLabel,0);
		Assert.assertEquals(fox.getSpeed(), 40);
	}
	@Test
	public void testMonsterGetX() {
		Label monsterLabel = null;
		Monster fox = new Fox(monsterLabel,0);
		Assert.assertEquals(fox.getX(), 20);
	}
	@Test
	public void testMonsterGetY() {
		Label monsterLabel = null;
		Monster fox = new Fox(monsterLabel,0);
		Assert.assertEquals(fox.getY(), 20);
	}
	
	@Test
	public void testMonsterReachEndZone() {
		double gridX = 1/4  ;
		double gridY = 1/4 ; 
		Label monsterLabel = ImageFunction.setImageToLabel("Penguin", gridX, gridY) ;
			
		Monster monster = new Penguin(monsterLabel, 0);
		Assert.assertFalse(monster.reachEndZone());
		monster.move(470, 20);
		Assert.assertTrue(monster.reachEndZone());
	}
}
