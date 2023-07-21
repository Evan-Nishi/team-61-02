package application.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class NewEntryScene implements ScreenI {
    private VBox rootBox = new VBox(10);

    public Scene getScene() {
        return new Scene(rootBox, 500, 500);
    }

    public NewEntryScene() {
        this.rootBox.setAlignment(Pos.TOP_CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label newEntryLabel = new Label("New Entry");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(logoutEvent -> {
            try {
                LoginScene loginScene = new LoginScene();
                loginScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.rootBox.getChildren().addAll(newEntryLabel, logoutButton);

        Label titleLabel = new Label("Title");
        TextField titleField = new TextField();
        titleField.setPromptText("Family Trip to Canada");

        Label dateLabel = new Label("Date*");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        Label bodyLabel = new Label("Body*");
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Today we landed in Vancouver International airport and went to Tim Horton's");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            try {
                DashboardScene dashboardScene = new DashboardScene();
                dashboardScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.rootBox.getChildren().addAll(titleLabel, titleField, dateLabel, datePicker, bodyLabel, bodyField, saveButton);
    }
}
