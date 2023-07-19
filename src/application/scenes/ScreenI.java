package application.scenes;

import application.StageController;
import javafx.scene.Scene;

public interface ScreenI{
	static final String PASSWORD_FILE = "password.txt";
	static final String SECURITY_QUESTION_FILE = "question.txt";
	static final String SECURITY_ANSWER_FILE = "answer.txt";

    static StageController sController = StageController.getInstance();
    
    public Scene getScene();

    public default void setStage(){
        sController.showScene(getScene());
    }
}
