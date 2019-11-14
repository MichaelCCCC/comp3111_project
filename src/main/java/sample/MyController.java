package sample;

import javafx.scene.control.Alert; 
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import tower.Tower;
import tower.TowerInformation;
import javafx.scene.shape.Circle;
import monster.Monster;


/**
 * @author Yomaru
 *
 */

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
    private Label Fox;
    
    @FXML
    private Label Penguin;
    
    @FXML
    private Label Unicorn;
    

    @FXML
    private Label frameText;

    @FXML
    private Label labelFrame;

    @FXML
	//static
    private Label labelMoney; 

    static final int ARENA_WIDTH = 480;
    static final int ARENA_HEIGHT = 480;
    static final int GRID_WIDTH = 40;
    static final int GRID_HEIGHT = 40;
    static final int MAX_H_NUM_GRID = 12;
    static final int MAX_V_NUM_GRID = 12;
    
    /*
     * initial amount of money
     */
    static final int INIT_MONEY = 300 ; 
    
    /*
     * current money amount of player
     */
    static Integer money = INIT_MONEY ; 
    
    /*
     * current number of frame
     */
    private int num_frame = 0;
    private final int TEST_FRAME = 10 ;  // by default is 1 
    
    /*
     * a list of monsters
     */
	static List<Monster> monsters = new ArrayList<> ()  ;
	
	/*
	 * a list of towers
	 */
	static List<Tower> towers = new ArrayList<> ()  ;

	/*
	 * grid of arena
	 */
    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    
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
                
                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1)) {
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
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
                paneArena.getChildren().addAll(newLabel);// this code show new label on pane.
                
            }
        
        labelMoney.setText(Integer.valueOf(money).toString());
        labelFrame.setText(Integer.valueOf(num_frame).toString());
        
        for(int i = 0 ; i < GreenBoxes.gbs.size() ; i++ ) {
        		setDragAndDrop(GreenBoxes.gbs.get(i).v , GreenBoxes.gbs.get(i).h);
        		setMouseAction(GreenBoxes.gbs.get(i).v,GreenBoxes.gbs.get(i).h) ; 
        }
        		
        
        setDragAndDrop2() ; 
    }

    /**
     * 
     */
    @FXML
    private void nextFrame() {
        
    	for(int i =  0 ; i < TEST_FRAME ; i++ ) {
    		//monster move
            
            util.moveMonsters(monsters)  ;
            
            //tower attack 
            util.towersAttack(monsters, towers) ; 
            
            //generate monster
            if(num_frame % 5 == 0) {        	
            	util.generateMonsters(paneArena) ; 
            }
           
            //show all object 
            util.showAllObjects(monsters, towers, paneArena);
            
            //detected where monster cross the final line
            //if yes, notify the play the game is over
            if (util.decideEndGame()) {
            	Alert alert = new Alert(AlertType.INFORMATION, "The game is over" )  ;
            	alert.showAndWait() ; 
            	System.exit(0);
            }

        	num_frame ++ ;
    	}
    	setLabelFrame(num_frame) ; 
        
    }
    
    @FXML
    private void play() {
    	
    }
     
    /**
     * @param i
     */
    void setLabelMoney(Integer i) {
    	money = i ; 
    	labelMoney.setText(money.toString());
    }
	
    /**
     * @param i
     */
    void setLabelFrame(Integer i) {
    	num_frame = i ; 
    	labelFrame.setText(Integer.valueOf(num_frame).toString());
    }
	
	
    /**
     * @param label
     * @param sources
     * @return
     */
    private String getInitTooltip(Label label, Label[] sources) {
    	TowerInformation towerInformation[] = {tower.Tower.BasicTowerInit, tower.IceTower.IceTowerInit, tower.Catapult.BasicTowerInit, tower.LaserTower.LaserTowerInit} ;
    	return util.sourceLabelGetTooltip(label, towerInformation, sources); 
    }
    
    /**
     * 
     */
    private void setDragAndDrop2() { 
    	Label sources[] = { labelBasicTower, labelIceTower, labelCatapult, labelLaserTower} ; 
    		
        
        for(int i = 0 ; i < sources.length ; i++ ) {
        		Tooltip.install(sources[i], new Tooltip(getInitTooltip(sources[i],sources)));
        		Label e = sources[i] ; 
        		sources[i].setOnDragDetected(new EventHandler<MouseEvent>( ) {
        			private Label source = e;
        		    @Override
        		    public void handle (MouseEvent event) {
        		        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        		        ClipboardContent content = new ClipboardContent();
        		        content.putString(source.getText());
        		        db.setContent(content);

        		        event.consume();
        		    }
        		});//once this is on, it cannot be off
        }
    }
    
    
    
    /**
     * @param v
     * @param h
     */
    private void setMouseAction(int v , int h ) {
    	Label label = grids[v][h] ; 
    	setMouseAction(label) ; 
    }
    
    /*
     * set up mouse action for the temporary label
     */
    private void  setTempMouseAction(Label tempLabel, Circle shootingRange) {  	
    	EventHandler<? super MouseEvent> linehover =  new EventHandler<MouseEvent>() {
    		@Override 
    		public void handle(MouseEvent event ) {
    			if(event.getEventType() == MouseEvent.MOUSE_EXITED ){
    				
					//System.out.println("Exited: " + GreenBoxes.targetGetGreenBox(tempLabel).toString() + ": " + GreenBoxes.targetH(tempLabel) +  ", " + GreenBoxes.targetV(tempLabel));
		            System.out.println("exit temp label") ; 
    				/* mouse moved away, remove the graphical cues */
					
	                System.out.println("remove shooting range and temp label") ; 
	                paneArena.getChildren().remove(shootingRange) ; 
	                paneArena.getChildren().remove(tempLabel) ; 
	                event.consume();	
    	    	}
    		}
    	} ; 
    	tempLabel.setOnMouseExited(linehover);
    	
    }
    
    /*
     * record the last label that the cursor is at
     */
    protected Label lastLabel = null ;
    
    /*
     * applicable to only green boxes
     */
    private void setMouseAction(Label target ) {
    		
    		
    		EventHandler<? super MouseEvent> linehover =  new EventHandler<MouseEvent>(){			
    		    @Override
    		    public void handle(MouseEvent event) {
		        	if(event.getEventType() == MouseEvent.MOUSE_ENTERED  ){
		        		//System.out.println("mouse entered"); 
			        	if(target != lastLabel  ) {
			        		System.out.println("mouse entered: " + GreenBoxes.targetGetGreenBox(target).toString() + ": " + GreenBoxes.targetH(target) +  ", " + GreenBoxes.targetV(target));
	            			if(GreenBoxes.targetHasTower(target))
	                		{
	            				System.out.println("has tower") ; 
	            				
	                			//show shooting range of current tower  
	            				Circle shootingRange = addShootingRangeToPaneArena(target) ; 
	            				Label lastLabel = addLastLabel(target) ; 
	            				setTempMouseAction(lastLabel, shootingRange) ; 
	                			
	                		}
	                		event.consume();
			        	}
		        }
    		    }
    		};
			target.setOnMouseEntered(linehover);  
    		target.setOnMouseExited(linehover);
    		
            String choices [] = {"destroy" , "upgrade" } ; 
    		ChoiceDialog<String> cd = new ChoiceDialog<String>(choices[0],  choices) ;
            target.setOnMouseClicked(new EventHandler <MouseEvent>() {
            		public void handle (MouseEvent event) {
            			if(GreenBoxes.targetHasTower(target)) {
            				
            				cd.showAndWait();
            				
            				if((String)cd.getResult() == choices[0]) 
            					GreenBoxes.targetDestroyTower(target) ;
            				
                			if((String)cd.getResult() == choices[1] ) {
                				int upgradeCost = GreenBoxes.targetGetTower(target).getUpgradeCost() ; 
                				if (money >= upgradeCost) {
                					//tower upgrade
                					money -= upgradeCost ; 
                					setLabelMoney(money) ;
                					GreenBoxes.targetUpgradeTower(target) ; 
                					
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
     * @param target
     * @return
     */
    protected Label addLastLabel(Label target) {
    	Label tempLabel = GreenBoxes.targetGetGreenBox(target).copyOfLabel()  ; 
    	Tooltip.install(tempLabel, new Tooltip(util.getTowerTooltipString(GreenBoxes.targetGetTower(target).getInfo())));
    	tempLabel.setStyle("-fx-border-color: rgba(255,0,0,0.3) ; -fx-border-width: 3");
    	paneArena.getChildren().add(tempLabel) ; 
		return tempLabel ; 
    	
	}

	/**
	 * @param target
	 * @return
	 */
	protected Circle addShootingRangeToPaneArena(Label target) { 
		Circle shootingRange = GreenBoxes.targetGetGreenBox(target).shootingRange ; 
		paneArena.getChildren().add(shootingRange); 
		System.out.println("add shooting range") ; 
		return shootingRange ; 
	}

	/**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop(int v, int h) {
    		Label target = grids[v][h] ; 
   
        target.setText("");
        
        target.setOnDragDropped(new EventHandler<DragEvent>() {
        	@Override
            public void handle(DragEvent event) {

                System.out.println("xx");
                Dragboard db = event.getDragboard();
                boolean success = false;
                System.out.println(db.getString());
                  if (db.hasString() || db.hasImage()) { //db is the button being dragged
                    Integer moneyDeducted = 0 ; 
                    switch(db.getString())
                    {
                    	case "Basic Tower" : 
                    		moneyDeducted = tower.Tower.BasicTowerInit.building_cost; 
                    		//((Label)event.getGestureTarget()).setText(db.getString());
                    		break ; 
                    	case "Ice Tower" : 
                    		moneyDeducted = tower.IceTower.IceTowerInit.building_cost;  //change
                    		break ; 
                    	case "Catapult" : 
                    		moneyDeducted = tower.Catapult.CatapultInit.building_cost ; 
                    		break ; 
                    	case "Laser Tower" : 
                    		moneyDeducted = tower.LaserTower.LaserTowerInit.building_cost  ;
                    		break ; 
                    	default :
                    		assert false : "invalid tower" ; 
                    }
                    
                    if(MyController.money >= moneyDeducted && GreenBoxes.targetHasTower(event.getGestureTarget()) == false)
                    {
                    	
                    	GreenBoxes.targetBuildTower(event.getGestureTarget(), db.getString()) ;
                    	util.showAllObjects(monsters, towers, paneArena);
                		MyController.money -= moneyDeducted; 
                		setLabelMoney(money) ;
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
        });
        
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