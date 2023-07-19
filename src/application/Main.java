package application;

import javafx.application.Application;
import javafx.stage.Stage;


import application.scenes.LoginScene;


public class Main extends Application {
	public Stage pStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageController stageController = StageController.getInstance();
		stageController.setStage(primaryStage);
		LoginScene login = new LoginScene();
		login.setStage();
	}

	public static void main(String[] args) {
		launch(args);
	}
}