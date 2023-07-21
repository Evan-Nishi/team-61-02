package application.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class DashboardScene implements ScreenI{
    private VBox rootBox = new VBox(10);

    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public DashboardScene(){
        this.rootBox.setAlignment(Pos.CENTER);
	    this.rootBox.setPadding(new Insets(25, 25, 25, 25));
		Label welcomeLabel = new Label("Main App stuff");
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(logoutEvent -> {
			try {
				LoginScene loginScene = new LoginScene();
				loginScene.setStage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Button newEntryButton = new Button("New Entry");
        newEntryButton.setOnAction(newEvent -> {
            try {
                NewEntryScene newEntryScene = new NewEntryScene();
                newEntryScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

		this.rootBox.getChildren().addAll(welcomeLabel,  newEntryButton, logoutButton);
    }

}
