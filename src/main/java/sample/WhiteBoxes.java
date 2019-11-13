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
	
	static boolean GenerateMonster(String id) {
		wbs.get(0).generateMonster(id);
		return true;  
	}
	
	static boolean removeMonster(Monster monster) {
		return false ; 
	}
	
	static void moveMonsters() {
		
	}
}
