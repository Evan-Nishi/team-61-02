package application.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScene {
    private VBox rootBox = new VBox(10);

    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public DashboardScene(Stage primaryStage){
        this.rootBox.setAlignment(Pos.CENTER);
	    this.rootBox.setPadding(new Insets(25, 25, 25, 25));
		Label welcomeLabel = new Label("Main App stuff");
		this.rootBox.getChildren().add(welcomeLabel);
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(logoutEvent -> {
			try {
				//start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
    }

}
