package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.math.*; 

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
    
    /*
     * return an image view when there is only one object in a box
     */
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
	
    /*
     * return an image view when there is only one object in a box
     */
	static ImageView setImageView (String id) {
		return setImageView(idReturnImage(id)) ; 
	}
	
	/*
	 * return an image view when there is multiple objects in a box
	 */
	static ImageView setImageViewMultiple(int size, Image image, int Index) {
		if(size == 0 || image == null || Index <= 0 || Index >= size )
			return null ; 
		
		ImageView iv = new ImageView(image) ; 
		if(image != null) {
			int k = (int)Math.ceil(Math.pow(size, 0.5)); //k can never be 0
			iv.setFitWidth(IMAGE_WIDTH / k );
			double smallBoxWidth = (double)BOX_WIDTH / k ; 
			iv.setX(smallBoxWidth * (Index % k) );
			iv.setY(smallBoxWidth * (Index / k) );
			iv.setPreserveRatio(true) ;
    		iv.setSmooth(true);
            iv.setCache(true);
		}
		return iv ;
	}
	
	/*
	 * return an image view when there is multiple objects in a box
	 */
	static ImageView setImageViewMultiple(int size, String id , int Index) {
		return setImageViewMultiple(size, idReturnImage(id) , Index) ; 
	}
	
}
