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
import javafx.scene.shape.Circle;

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

    static final int ARENA_WIDTH = 480;
    static final int ARENA_HEIGHT = 480;
    static final int GRID_WIDTH = 40;
    static final int GRID_HEIGHT = 40;
    static final int MAX_H_NUM_GRID = 12;
    static final int MAX_V_NUM_GRID = 12;
    
    static Integer money = 10 ; 
    
    String towerInitInformation[][] = tower.TowerInformation.getTowerInitInformation()  ; // get tower information
    //get tower information
    

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    static GreenBoxes greenboxes = new GreenBoxes () ;   //store all the green box
    private int x = -1, y = 0; //where is my monster
    
    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
    	/** 
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
    	 */
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
            x = 0; // x is generated 
            return;
        }
        if (y == MAX_V_NUM_GRID - 1)
            return;
        grids[y++][x].setText(""); //remove text on original labal
        grids[y][x].setText("M"); //add text on original label 
        
        //monster move
        
        //tower attack 
        
        //generate monster
        
        //detected where monster cross the final line
        //if yes, notify the play the game is over
    }
    
    int getLabelMoney() {
    	return Integer.parseInt(labelMoney.getText()) ; 
    }
   
    void setLabelMoney(Integer i) {
    	money = i ; 
    	labelMoney.setText(money.toString());
    }
    
    private String getInitTooltip(Label label) {
		String result = "";
		int towerType = -1 ; 
		if(label.equals(labelBasicTower)) towerType = 0 ; 
		if(label.equals(labelIceTower)) towerType = 1 ; 
		if(label.equals(labelCatapult)) towerType=2 ;
		if(label.equals(labelLaserTower)) towerType = 3 ; 
		
		String initInformationLineId[] = tower.TowerInformation.getInitInformationLineId() ; 
		
		if(initInformationLineId.length != towerInitInformation[towerType].length) {
			Alert alert = new Alert(AlertType.ERROR , "initInformationLineId.length != towerInitInformation[towerType].length"); 
			alert.showAndWait() ; 
		}
		if(towerInitInformation[towerType][0] != null )
			result += towerInitInformation[towerType][0] ; 
		for(int i = 1 ; i < towerInitInformation[towerType].length ; i++ ) 
			if(towerInitInformation[towerType][i] != null )
				result += ("\n" + initInformationLineId[i] + ": " +towerInitInformation[towerType][i])   ;
		
		return result ; 
}
    
    private String getCurrentTooltip(Label label) {
    	String result = "" ; 
    	return result; 
    }
    
    private void setDragAndDrop2() { 
    		Label sources[] = { labelBasicTower, labelIceTower, labelCatapult, labelLaserTower} ; 
    		
        
        for(int i = 0 ; i < sources.length ; i++ ) {
        		Tooltip.install(sources[i], new Tooltip(getInitTooltip(sources[i])));
        		sources[i].setOnDragDetected(new DragEventHandler(sources[i]));//once this is on, it cannot be off
        }
         
    }
    
    private void setMouseAction(int v , int h ) {
    		Label target = grids[v][h] ; 
    		Tooltip.install(target, new Tooltip(getCurrentTooltip(target)));
    		
    		target.setOnMouseEntered( new EventHandler <MouseEvent>() {
            	@Override
            	public void handle (MouseEvent event) {
            		System.out.println("mouse entered"); 
            		if(GreenBoxes.targetHasTower(target))
            		{
            			target.setStyle("-fx-border-color: rgba(255,0,0,0.3) ; -fx-border-width: 3");
            			
            			//show range and show tool tip 
            			
            			util.showTowerRange(target) ; 
            			
            			
            	        
            			
            		}
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
                				int upgradeCost = tower.TowerInformation.getUpgradeCost(target.getId()) ; 
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
            		moneyDeducted = tower.TowerInformation.getBuildingCost("Basic Tower") ; 
            		break ; 
            	case "Ice Tower" : 
            		moneyDeducted = tower.TowerInformation.getBuildingCost("Ice Tower") ; //change
            		break ; 
            	case "Catapult" : 
            		moneyDeducted = tower.TowerInformation.getBuildingCost("Catapult")  ; 
            		break ; 
            	case "Laser Tower" : 
            		moneyDeducted = tower.TowerInformation.getBuildingCost("Laser Tower")  ;
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
