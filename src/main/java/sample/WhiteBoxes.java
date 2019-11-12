package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;

public class WhiteBoxes {
	static List<WhiteBox> wbs = new ArrayList<>();
	static List<Monster> monsterList = new ArrayList<>(); 
	
	public boolean generateMonster(String id) {
		Monster monster = null ; 
		ImageView iv = ImageFunction.setImageView(id);
		
		label.setGraphic(iv);
		label.setId(id);
		switch(id)
		{
		case "Fox" : 
			monster = new Fox() ; 
				break  ; 
		case "Unicorn" :
			monster = new Unicorn() ; 
				break ;
		case "Penguin" : 
			monster = new Penguin() ; 
				break ; 
		}
		monsterList.add(monster);
		
		return true;
	}
	
	
	static boolean targetGenerateMonster(String id) {

		wbs.get(0).generateMonster(id);
		return true;  
	}
	
	static boolean removeMonster(Monster monster) {
		for(int i = 0 ; i < )
	}
	
	static void moveMonsters() {
		
	}
}
