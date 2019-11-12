package sample;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;

public class WhiteBox {
	Label label ; 
	final int v ; 
	final int h;  
	Monster monster ;
	
	public WhiteBox(Label _label, int _v, int _h) {
		label = _label;
		v = _v;
		h = _h;
		monster = null;
	}
	public Monster getMonster() {
		return monster;
	}
	
	public boolean generateMonster(String id) {
		if(monster == null) {
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
			WhiteBoxes.monsterList.add(monster);
		}
		return true;
	}
	public void removeMonster() {
		ImageView iv = ImageFunction.setImageView("");
		
		label.setGraphic(iv);
		label.setId("");
		
		monster = null;
	}
}
