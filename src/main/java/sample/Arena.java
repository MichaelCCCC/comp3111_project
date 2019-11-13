package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;
public class Arena {

	public static List<Monster> monsterlist = new ArrayList<>();
	
	static void GenerateMonster(AnchorPane Arena,String id) {
		
		System.out.println("Generate Monster");
		Label newLabel = new Label();
		newLabel.setLayoutX(10);
		newLabel.setLayoutY(10);
		newLabel.setMinWidth(1);
		newLabel.setMaxWidth(1);
		newLabel.setMinHeight(1);
		newLabel.setMaxHeight(1);
	     
		newLabel.setGraphic(ImageFunction.setImageView(id));
		
		newLabel.setId(id);

		Arena.getChildren().addAll(newLabel);
		
		Monster monster = null ;  
		switch(id)
		{
		case "Fox" : 
			monster = new Fox(newLabel) ; 
				break  ; 
		case "Unicorn" :
			monster = new Unicorn(newLabel) ; 
				break ;
		case "Penguin" : 
			monster = new Penguin(newLabel) ; 
				break ; 
		}
		monsterlist.add(monster);
	}
	
	static void moveMonster() {
		for(int i = 0; i<monsterlist.size(); i++) {
			Monster monster = monsterlist.get(i);
			int x = monster.getX();
			int y = monster.getY();
			int speed = monster.getSpeed();
			changeDirection(monster);
			if(monster.getDirection() == Monster.Direction.RIGHT) {
				x += speed;
			}else if(monster.getDirection() == Monster.Direction.UP) {
				y -= speed;
			}else {
				y +=speed;
			}
			monsterlist.get(i).move(x, y);
		}
	}
	static void changeDirection(Monster monster){
		int x = monster.getX();
		int y = monster.getY();
		
		if(y == 460 ) {
			if(x%160 == 20) {
				monster.setDirection(Monster.Direction.RIGHT);
			}
			if(x%160 == 100) {
				monster.setDirection(Monster.Direction.UP);
			}
		}
		if(y == 40) {
			if(x != 20) {
				if(x%160 ==100) {
					monster.setDirection(Monster.Direction.RIGHT);
				}
				if(x%160 == 20) {
					monster.setDirection(Monster.Direction.DOWN);
				}
			}
		}
	}
	
}
