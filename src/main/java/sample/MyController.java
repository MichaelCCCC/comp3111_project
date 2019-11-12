
package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

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
    
    static final int COST_BASIC_TOWER = 1 ; //<- should come from tower class
    static final int COST_ICE_TOWER = 2 ; 
    static final int COST_CATAPULT = 2 ; 
    static final int COST_LASER_TOWER = 3 ; 
    static final int UPGRADE_COST = 3 ; 
    
    static final Image BTimage = new Image("/basicTower.png") ;  
    static final Image ITimage = new Image("/iceTower.png") ; 
    static final Image Cimage = new Image("/catapult.png")  ;
    static final Image LTimage = new Image( "/laserTower.png") ; 
    
    static Integer money = 10 ; 
    
    String towerInformation[][] = new String[4][8]  ; // get tower information
    //get tower information

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
        
        for(int i = 0 ; i < GreenBoxes.gbs.size() ; i++ ) {
        		setDragAndDrop(GreenBoxes.gbs.get(i).v , GreenBoxes.gbs.get(i).h);
        		setMouseAction(GreenBoxes.gbs.get(i).v,GreenBoxes.gbs.get(i).h) ; 
        }
        		
        
        setDragAndDrop2() ; 
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
    
    private String getTooltip(Label label) {
    		String result = "";
    		if(label.equals(labelBasicTower))
    			result =  towerInformation[0][0] + "\nshooting range: " + 
    		towerInformation[0][1] + "\nattack power: " + towerInformation[0][2] + 
    		"\nbuilding cost: " + towerInformation[0][3] + "\ntier: " + 
    		towerInformation[0][4] + "\nupgrade cost: " + towerInformation[0][5] + 
    		"\nupgrade diff: " + towerInformation[0][6] + "\nnote: " + towerInformation[0][7] ;
    		if(label.equals(labelIceTower))
    			result =  towerInformation[1][0] + "\nshooting range: " + 
    		towerInformation[1][1] + "\nattack power: " + towerInformation[1][2] + 
    		"\nbuilding cost: " + towerInformation[1][3] + "\ntier: " + 
    		towerInformation[1][4] + "\nupgrade cost: " + towerInformation[1][5] + 
    		"\nupgrade diff: " + towerInformation[1][6] + "\nnote: " + towerInformation[1][7] ;
    		if(label.equals(labelCatapult))
    			result =  towerInformation[2][0] + "\nshooting range: " + 
    		towerInformation[2][1] + "\nattack power: " + towerInformation[2][2] + 
    		"\nbuilding cost: " + towerInformation[2][3] + "\ntier: " + 
    		towerInformation[2][4] + "\nupgrade cost: " + towerInformation[2][5] + 
    		"\nupgrade diff: " + towerInformation[2][6] + "\nnote: " + towerInformation[2][7] ;
    		if(label.equals(labelLaserTower))
    			result =  towerInformation[3][0] + "\nshooting range: " + 
    		towerInformation[3][1] + "\nattack power: " + towerInformation[3][2] + 
    		"\nbuilding cost: " + towerInformation[3][3] + "\ntier: " + 
    		towerInformation[3][4] + "\nupgrade cost: " + towerInformation[3][5] + 
    		"\nupgrade diff: " + towerInformation[3][6] + "\nnote: " + towerInformation[3][7] ;
    			return result ; 
    }
    
    private void setDragAndDrop2() { 
    		Label sources[] = { labelBasicTower, labelIceTower, labelCatapult, labelLaserTower} ; 
    		
        
        for(int i = 0 ; i < sources.length ; i++ ) {
        		Tooltip.install(sources[i], new Tooltip(getTooltip(sources[i])));
        		sources[i].setOnDragDetected(new DragEventHandler(sources[i]));//once this is on, it cannot be off
        }
         
    }
    
    private void setMouseAction(int v , int h ) {
    		Label target = grids[v][h] ; 
    		
    		target.setOnMouseEntered( new EventHandler <MouseEvent>() {
            	@Override
            	public void handle (MouseEvent event) {
            		System.out.println("mouse entered"); 
            		if(GreenBoxes.targetHasTower(target))
            			target.setStyle("-fx-border-color: rgba(255,0,0,0.3) ; -fx-border-width: 3");
            		event.consume();
            	}
            });
            
            target.setOnMouseExited((event) -> {
                /* mouse moved away, remove the graphical cues */
                target.setStyle("-fx-border-color: black;");
                System.out.println("Exit");
                setLabelMoney(money) ; //change the labelmoney after buying
                event.consume();
            });
            
            
            String choices [] = {"destroy" , "upgrade" } ; 
    		ChoiceDialog<String> cd = new ChoiceDialog<String>(choices[0],  choices) ;
            target.setOnMouseClicked(new EventHandler <MouseEvent>() {
            		public void handle (MouseEvent event) {
            			if(GreenBoxes.targetHasTower(target)) {
            				//target.setGraphic(null);
            				cd.showAndWait();
            				
            				 
            				if((String)cd.getResult() == choices[0]) 
            					GreenBoxes.targetDestroyTower(target) ; 
            					//target.setGraphic(null);
                			
                			
                			if((String)cd.getResult() == choices[1] ) {
                				if (money >= UPGRADE_COST) {
                					//tower upgrade
                					money -= UPGRADE_COST ; 
                					setLabelMoney(money) ;
                					System.out.print(target.getId() + " is being upgraded");
                					Alert alert = new Alert (AlertType.INFORMATION, target.getId() + " is being upgraded") ; 
            	            			alert.showAndWait() ;
                				}
                				else 
                				{
                					System.out.println("not enough resource to upgrade " + target.getId() + "tower") ; 
                					Alert alert = new Alert (AlertType.WARNING, "Don't have enough money") ; 
                	            		alert.showAndWait() ;
                					
                				}
                					
                			}
            			}
            			event.consume() ; 
            			
            			
            		} 
            }) ; 
    }
    
    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop(int v, int h) {
    		Label target = grids[v][h] ; 
   
        target.setText("");
        
        
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
            	GreenBoxes.targetBuildTower(event.getGestureTarget(), db.getString()) ;
                //((Label)event.getGestureTarget()).setText(moneyDeducted.toString());
        		MyController.money -= moneyDeducted;  
        		
                
                success = true;
            }
            else
            {
            	//alert
            	Alert alert = new Alert (AlertType.WARNING, "Don't have enough money") ; 
            	alert.showAndWait() ; 
            	success = false ; 
            }
            
        }
        event.setDropCompleted(success);
        event.consume();
        
    }

}

