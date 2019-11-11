package sample;

import javafx.scene.control.Label;

class GreenBox{ 
	Label label ; 
	final int v ; 
	final int h; 
	boolean haveTower = false ; 
	
	GreenBox(Label label, int v, int h){
		this.label = label; 
		this.v = v ; 
		this.h = h ; 
	}
	
}
