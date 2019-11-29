package sample;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FxTest2 extends ApplicationTest {

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
        appController.setLabelFrame(100);
        appController.setLabelMoney(100);
        GreenBox gb = new GreenBox(new Label() , 0, 0 )  ;
        GreenBoxes.gbs.clear();  
        GreenBoxes.gbs.add(gb); 
        gb.buildTower("Basic Tower") ; 
        MyController.towers.add(gb.towerInBox)  ;
        appController.addLastLabel(gb.gbLabel) ; 
        
        appController.addShootingRangeToPaneArena(gb.gbLabel)  ;
        gb.destroyTower()  ; 
        gb.buildTower("Laser Tower")  ;
        appController.addShootingRangeToPaneArena(gb.gbLabel)  ;
	}
	
	@Test
	public void test() {
		
	}

}
