package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Class of the game
 */
public class Main extends Application {

	
    /**
     *The Start function to generate a game 
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
       // System.out.println(getClass().getResource("/sample.fxml")) ;  //it can find the file
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
        MyController appController = (MyController)loader.getController();
        appController.createArena();
    }


    /**
     * the main function of the game
     * @param args the argument 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
