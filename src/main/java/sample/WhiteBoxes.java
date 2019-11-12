package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;

public class WhiteBoxes {
	static List<WhiteBox> wbs = new ArrayList<>();
	static List<Monster> monsterList = new ArrayList<>(); 
	
	static boolean targetGenerateMonster( String id) {
		wbs.get(0).generateMonster(id);
		return true;  
	}
	
	static void moveMonsters() {
		for(int i = 0; i<monsterList.size(); i++) {
			if(monsterList.get(i) != null) {
				System.out.print(i);
			}
		}
	}
}
