// ForgotPasswordScene
package application.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;

import data.FileUtils;

public class ForgotPasswordScene implements ScreenI {
    private VBox rootBox = new VBox(10);

    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public void addNode(Node node){
        rootBox.getChildren().add(node);
    }

    public ForgotPasswordScene(){
        this.rootBox.setAlignment(Pos.CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label securityQLabel = new Label("Security Question: " + FileUtils.readFile(SECURITY_QUESTION_FILE));
        TextField answerBox = new TextField();
        Label newPasswordLabel = new Label("Enter New Password");
        PasswordField newPasswordBox = new PasswordField();
        Label confirmPassLabel = new Label("Confirm New Password");
        PasswordField confirmPwBox = new PasswordField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            if (answerBox.getText().equals(FileUtils.readFile(SECURITY_ANSWER_FILE)) &&
                    newPasswordBox.getText().equals(confirmPwBox.getText())) {
                FileUtils.writeFile(PASSWORD_FILE, newPasswordBox.getText());

                LoginScene loginScene = new LoginScene();
                loginScene.setStage();
            } else {
                System.out.println("Answer to security question is incorrect or password don't match");
            }
        });

        this.rootBox.getChildren().addAll(securityQLabel, answerBox, newPasswordLabel, newPasswordBox, confirmPassLabel, confirmPwBox, saveButton);
    }
}

