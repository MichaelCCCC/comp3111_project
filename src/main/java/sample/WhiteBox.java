package sample;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import java.util.* ; 

import monster.Unicorn;


public class WhiteBox {
	final int v ; 
	final int h;  
	List<Object> objectsInBox = new ArrayList<> ( ) ; 
	List<Label> labelsInBox = new ArrayList<> () ; 
	

	WhiteBox(int v, int h) {
		this.v = v;
		this.h = h;
	}
	
	/*
	 * for a monster, find if it is in the box. If yes, return the index, else return null.
	 */
	Integer getIndexMonstersInBox(Monster monster) {
		for(int i = 0 ; i < objectsInBox.size() ; i++ ) {
			if(objectsInBox.get(i) == monster)
				return i ; 
		}
		return null; //there is no such monster in the box
	}

	/*
	 * when monster move away from this box or the monster is killed
	 */
	void removeMonster(Monster monster) {
		if(monster != null) {
			if(objectsInBox.get(getIndexMonstersInBox(monster)) != null) {
				labelsInBox.remove(getIndexMonstersInBox(monster)) ; 
				objectsInBox.remove(getIndexMonstersInBox(monster)) ; 
			}
		}
	}
	
	/*
	 * return the first monster that enter this box
	 */
	Monster getFirstMonster() {
		if(!objectsInBox.isEmpty())
			for(int i = 0 ; i < objectsInBox.size() ; i++ ) 
				if(objectsInBox.get(i).getClass() == Monster.class)
					return (Monster)objectsInBox.get(i) ; 
		return null ; 
					
	}
	
	/*
	 * new monster is generated in this box
	 */
	void generateMonster(String id) {
		Monster monster = null ; 
		Label label = new Label() ; 
		
		label.setGraphic(ImageFunction.setImageView(id));
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
		objectsInBox.add(monster);
		labelsInBox.add(label) ; 
	}
	
	/*
	 * old monster move into this box
	 */
	void addMonster(Monster monster, Label label) {
		objectsInBox.add(monster) ; 
		labelsInBox.add(label) ; 
	}
	
	/*
	 * return the x coordinate of the object in this box  
	 */
	double getX() {
		return MyController.GRID_WIDTH * ((double)v + 0.5) ;
	}
	
	/*
	 * return the y coordinate of the object in this box
	 */
	double getY() {
		return MyController.GRID_HEIGHT * ((double) v+0.5) ; 
	}
	
	/*
	 * move monster in this box
	 */
	boolean moveMonster() {
		for(int i = 0 ; i < objectsInBox.size() ; i++)
			if(objectsInBox.get(i).getClass() == Monster.class) {
				Monster monster = (Monster)objectsInBox.get(i) ; 
				Label label = labelsInBox.get(i) ; 
				if (monster.moveTo() != this ) {
					//get next white box 
					
					//add monster to next whitebox 
					monster.moveTo().addMonster(monster, label);
					
					//remove monster from this whitebox 
					removeMonster(monster) ; 
					
					//return true; 
					return true ; 
				}
					
			}	 
		return false ;
	}
}
