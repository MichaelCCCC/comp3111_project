package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ImageFunction {

	static final Image BTimage = new Image("/basicTower.png") ;  
    static final Image ITimage = new Image("/iceTower.png") ; 
    static final Image Cimage = new Image("/catapult.png")  ;
    static final Image LTimage = new Image( "/laserTower.png") ; 
    static final Image FOXimage = new Image("/fox.png");
    static final Image PENGUINimage = new Image ("/penguin.png");
    static final Image UNICORNimage = new Image ("/unicorn.png");
    static final Image COLLISIONimage = new Image ("/collision.png");
    static final int IMAGE_WIDTH = 30; 
    static final int BOX_WIDTH = 40 ; 
    
    static Image idReturnImage(String id) {
		switch(id)
		{
		case "Basic Tower" : 
				return BTimage; 
		case "Ice Tower" :
				return ITimage ;
		case "Catapult" : 
				return Cimage; 
		case "Laser Tower":
				return LTimage ;
		case "Fox":
				return FOXimage;
		case "Penguin":
				return PENGUINimage;
		case "Unicorn":
				return UNICORNimage;
		case "Collision":
				return COLLISIONimage;
		default : 
				return null;  
		}
	}
    
    static ImageView setImageView(Image image) {
		ImageView iv = new ImageView(image) ; 
		if(image != null) {
			iv.setFitWidth(IMAGE_WIDTH) ;
			iv.setTranslateX((BOX_WIDTH - IMAGE_WIDTH)/2) ; 
    		iv.setPreserveRatio(true) ;
    		iv.setSmooth(true);
            iv.setCache(true);
		}
		return iv ; 
	}
	
	static ImageView setImageView (String id) {
		ImageView iv = new ImageView((id == null) ? null: idReturnImage(id)) ; 
		if(id != null) {
			iv.setFitWidth(IMAGE_WIDTH) ;
			iv.setTranslateX((BOX_WIDTH - IMAGE_WIDTH)/2) ; 
    		iv.setPreserveRatio(true) ;
    		iv.setSmooth(true);
            iv.setCache(true);
		}
		return iv ; 
	}
	
}
