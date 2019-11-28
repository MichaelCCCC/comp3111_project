package sample;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageFunctionTest extends ApplicationTest{

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
	public void testImageFunction() {
		Assert.assertNotNull(new ImageFunction());
	}
	
	@Test
	public void testIdReturnImage() {
		Assert.assertNotNull(ImageFunction.idReturnImage("Unicorn")) ; 
		Assert.assertNotNull(ImageFunction.idReturnImage("Collision"));
		Assert.assertNull(ImageFunction.idReturnImage("ABC")) ; 
		Assert.assertNotNull(ImageFunction.idReturnImage("Fox"));
		Assert.assertNotNull(ImageFunction.idReturnImage("Penguin"));
		Assert.assertNotNull(ImageFunction.idReturnImage("Unicorn"));
		Assert.assertNotNull(ImageFunction.idReturnImage("Collision"));
		
	}

}
