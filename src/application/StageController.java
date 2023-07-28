package application;

import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * StageController
 * The StageController clas is used to allow SceneControllers to edit the common
 * stage and share it between scenes.  It can only be initiated once.
 * @version 0.5
 */
public class StageController {
    private static StageController sController = new StageController();
    private Stage stage;
    
    private StageController(){}

    /**
     * @return StageController returns single instance of class
     */
    public static StageController getInstance(){
        return sController;
    }
    
    /**
     * sets stage to parameter
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * sets main stage to passed scene and updates screen
     * @param scene
     */
    public void showScene(Scene scene){
        this.stage.setScene(scene);
        this.stage.show();
    }

    /**
     * @return Stage Returns currently active stage
     */
    public Stage getStage(){
        return this.stage;
    }

}
