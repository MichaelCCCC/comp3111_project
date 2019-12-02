package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 


/**
 * A class to help getting Image and set ImageView
 * @author Yomaru
 *
 */
public class ImageFunction {
	
	/**
	 * default constructor
	 * do nothing 
	 */
	public ImageFunction(){
		
	}

	static final Image BTimage = new Image("/basicTower.png") ;  
    static final Image ITimage = new Image("/iceTower.png") ; 
    static final Image Cimage = new Image("/catapult.png")  ;
    static final Image LTimage = new Image( "/laserTower.png") ; 
    static final Image FOXimage = new Image("/fox.png");
    static final Image PENGUINimage = new Image ("/penguin.png");
    static final Image UNICORNimage = new Image ("/unicorn.png");
    static final Image COLLISIONimage = new Image ("/collision.png");
    static final Image HOLEimage = new Image("/hole.png") ; 
    static final Image CASTLEimage =new Image ("/castle.png"); 
    static final int TOWER_IMAGE_WIDTH = 30;
    static final int MONSTER_WIDTH = 20;
    static final int BOX_WIDTH = 40 ; 
    
    /**
     * return the image given the id 
     * @param id
     * @return
     */
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
		case "Hole" : 
			return HOLEimage ; 
		case "Castle" :
			return CASTLEimage ;
		default : 
				return null;  
		}
	}
    
    
    
    /**
     * return an image view when there is only one object in a box
     */
    static ImageView setImageView(Image image) {
		ImageView iv = new ImageView(image) ; 
		if(image != null) {
			int width;
			if(image == FOXimage|| image == PENGUINimage|| image == UNICORNimage || image == COLLISIONimage) {
				width = MONSTER_WIDTH;
			}else if(image == CASTLEimage || image == HOLEimage)
			{
				width = BOX_WIDTH ; 
			}
			else 
				width = TOWER_IMAGE_WIDTH;
			iv.setFitWidth(width) ;
			iv.setTranslateX((BOX_WIDTH - TOWER_IMAGE_WIDTH)/2) ; 
    		iv.setPreserveRatio(true) ;
    		iv.setSmooth(true);
            iv.setCache(true);
		}
		return iv ; 
	}
	
    /**
     * return an image view when there is only one object in a box
     */
	static ImageView setImageView (String id) {
		return setImageView(idReturnImage(id)) ; 
	}
	
	/**
	 * given an image id and the location on grid, return a label to that location
	 * @param id id of the image
	 * @param gridX X coordinate on grid
	 * @param gridY Y coordinate of grid
	 * @return Label a label showing the image
	 */
	public static Label setImageToLabel(String id, double gridX, double gridY) {
		Label newLabel = new Label() ; 
		newLabel.setGraphic(ImageFunction.setImageView(id)) ; 
		newLabel.setId(id);
		if(id == "Hole" || id == "Castle")
		{
			newLabel.setLayoutX((gridX - 0.1) * MyController.GRID_WIDTH );
			newLabel.setLayoutY(gridY * MyController.GRID_HEIGHT );
			
		}
		else {
		newLabel.setLayoutX(gridX * MyController.GRID_WIDTH);
		newLabel.setLayoutY(gridY * MyController.GRID_HEIGHT);
		} 
		return newLabel ; 
	}
	
}
