
package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import java.util.* ; 

public class MyController {
    @FXML
    private Button buttonNextFrame;

    @FXML
    private Button buttonSimulate;

    @FXML
    private Button buttonPlay;

    @FXML
    private AnchorPane paneArena;

    @FXML
    private Label labelBasicTower;

    @FXML
    private Label labelIceTower;

    @FXML
    private Label labelCatapult;

    @FXML
    private Label labelLaserTower;
    
    @FXML
	//static
    private Label labelMoney; //<- label cannot be static

    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 480;
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;
    
    static final int COST_BASIC_TOWER = 1 ;
    static final int COST_ICE_TOWER = 2 ; 
    static final int COST_CATAPULT = 2 ; 
    static final int COST_LASER_TOWER = 3 ; 
    
    static final Image BTimage = new Image("/basicTower.png") ;  
    static final Image ITimage = new Image("/iceTower.png") ; 
    static final Image Cimage = new Image("/catapult.png")  ;
    static final Image LTimage = new Image( "/laserTower.png") ; 
    
    static Integer money = 10 ; 

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    static GreenBoxes greenboxes = new GreenBoxes () ;   //store all the green box
    private int x = -1, y = 0; //where is my monster
    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        System.out.println("Play button clicked");
        Label newLabel = new Label();
        newLabel.setLayoutX(GRID_WIDTH / 4 );
        newLabel.setLayoutY(GRID_WIDTH / 4);
        newLabel.setMinWidth(GRID_WIDTH / 2);
        newLabel.setMaxWidth(GRID_WIDTH / 2);
        newLabel.setMinHeight(GRID_WIDTH / 2);
        newLabel.setMaxHeight(GRID_WIDTH / 2);
        newLabel.setStyle("-fx-border-color: black;");
        newLabel.setText("*");
        newLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN,
       CornerRadii.EMPTY, Insets.EMPTY)));
        paneArena.getChildren().addAll(newLabel);
        
    }

    /**
     * A function that create the Arena
     */
    @FXML
    public void createArena() {
        if (grids[0][0] != null)
            return; //created already
        
        for (int i = 0; i < MAX_V_NUM_GRID; i++) //the top left corner is (0,0)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                Label newLabel = new Label();
                
                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                else 
                {
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    GreenBoxes.gbs.add(new GreenBox(newLabel,i,j))  ; 
                }
                newLabel.setLayoutX(j * GRID_WIDTH);
                newLabel.setLayoutY(i * GRID_HEIGHT);
                newLabel.setMinWidth(GRID_WIDTH);
                newLabel.setMaxWidth(GRID_WIDTH);
                newLabel.setMinHeight(GRID_HEIGHT);
                newLabel.setMaxHeight(GRID_HEIGHT);
                newLabel.setStyle("-fx-border-color: black;");
                grids[i][j] = newLabel;
                paneArena.getChildren().addAll(newLabel);
            }
        
        labelMoney.setText(money.toString());
        
        for(int i = 0 ; i < GreenBoxes.gbs.size() ; i++ )
        		setDragAndDrop(GreenBoxes.gbs.get(i).v , GreenBoxes.gbs.get(i).h);
    }

    @FXML
    private void nextFrame() {
        if (x == -1) {
            grids[0][0].setText("M");
            x = 0;
            return;
        }
        if (y == MAX_V_NUM_GRID - 1)
            return;
        grids[y++][x].setText("");
        grids[y][x].setText("M");
    }
    
    int getLabelMoney() {
    	return Integer.parseInt(labelMoney.getText()) ; 
    }
   
    void setLabelMoney(Integer i) {
    	money = i ; 
    	labelMoney.setText(money.toString());
    }
    
    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop(int v, int h) {
    		Label target = grids[v][h] ; 
   
        target.setText("");
        
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        Label source3 = labelCatapult; 
        Label source4 = labelLaserTower ; 
         
        	source1.setOnDragDetected(new DragEventHandler(source1)); //once this is on, it cannot be off
         
        	source2.setOnDragDetected(new DragEventHandler(source2));
        
        	source3.setOnDragDetected(new DragEventHandler(source3));
       
        	source4.setOnDragDetected(new DragEventHandler(source4));
        //target.setOnDragDetected(new DragEventHandler(target)) ; 

        target.setOnDragDropped(new DragDroppedEventHandler());
        
        

        //well, you can also write anonymous class or even lambda
        //Anonymous class
        
        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                	
                	if(GreenBoxes.targetHasTower(target) == false)
                    /* allow for both copying and moving, whatever user chooses */
                	event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
        
        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    target.setStyle("-fx-border-color: blue;");
                    
                }

                event.consume();
            }
        });
        
        //lambda
        target.setOnDragExited((event) -> {
            /* mouse moved away, remove the graphical cues */
            target.setStyle("-fx-border-color: black;");
            System.out.println("Exit");
            setLabelMoney(money) ; //change the labelmoney after buying
            event.consume();
    });
        
        
    }
}

class DragEventHandler implements EventHandler<MouseEvent> {
    private Label source;
    public DragEventHandler(Label e) {
        source = e;
    }
    @Override
    public void handle (MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);

        event.consume();
    }
}

class DragDroppedEventHandler implements EventHandler<DragEvent> { //always on 
	
	@Override
    public void handle(DragEvent event) {
        System.out.println("xx");
        Dragboard db = event.getDragboard();
        boolean success = false;
        System.out.println(db.getString());
        
        
        if (db.hasString() || db.hasImage()) { //db is the button being dragged
            
        	
        	//((Label)event.getGestureTarget()).setText(db.getString()); //set the target to tower
            
        	
        	//set image 
            Integer moneyDeducted = 0 ; 
            switch(db.getString())
            {
            	case "Basic Tower" : 
            		moneyDeducted = MyController.COST_BASIC_TOWER ; 
            		break ; 
            	case "Ice Tower" : 
            		moneyDeducted = MyController.COST_ICE_TOWER ; 
            		break ; 
            	case "Catapult" : 
            		moneyDeducted = MyController.COST_CATAPULT  ; 
            		break ; 
            	case "Laser Tower" : 
            		moneyDeducted = MyController.COST_LASER_TOWER  ;
            		break ; 
            	default :
            		assert false : "invalid tower" ; 
            }
            if(MyController.money >= moneyDeducted && GreenBoxes.targetHasTower(event.getGestureTarget()) == false)
            {
            	MyController.greenboxes.targetBuildTower(event.getGestureTarget(), db.getString()) ;
                //((Label)event.getGestureTarget()).setText(moneyDeducted.toString());
        		MyController.money -= moneyDeducted;  
        		
                
                success = true;
            }
            else
            {
            	//alert
            	success = false ; 
            }
            
        }
        event.setDropCompleted(success);
        event.consume();
        
    }

}

