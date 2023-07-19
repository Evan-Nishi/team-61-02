package application;

import javafx.stage.Stage;
import javafx.scene.Scene;

public class StageController {
    private static StageController sController= new StageController();
    private Stage stage;
    private StageController(){}
    public static StageController getInstance(){
        return sController;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void showScene(Scene scene){
        this.stage.setScene(scene);
        this.stage.show();
    }

    public Stage getStage(){
        return this.stage;
    }

}
