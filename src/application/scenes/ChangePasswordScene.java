package application.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import data.FileUtils;


/**
 * ChangePasswordScene
 * Scenecontroller for change password screen
 * 
 * @version 0.5
 */
public class ChangePasswordScene implements ScreenI{
    private VBox rootBox = new VBox(10);

    /**
     * @return Scene Returns a scene of the rootBox
     */
    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public ChangePasswordScene() {
        this.rootBox.setAlignment(Pos.CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));

        Label currentPasswordLabel = new Label("Current Password");
        PasswordField currentPasswordField = new PasswordField();

        Label newPasswordLabel = new Label("New Password");
        PasswordField newPasswordField = new PasswordField();

        Label confirmNewPasswordLabel = new Label("Confirm New Password");
        PasswordField confirmNewPasswordField = new PasswordField();

        Label newSecurityQuestionLabel = new Label("New Security Question");
        TextField newSecurityQuestionField = new TextField();

        Label newSecurityAnswerLabel = new Label("Answer");
        TextField newSecurityAnswerField = new TextField();
        Label errTag = new Label();
        Button saveChangesButton = new Button("Save Changes");
        saveChangesButton.setOnAction(event -> {
            String currentPassword = FileUtils.readFile(PASSWORD_FILE);
            if (currentPasswordField.getText().equals(currentPassword) &&
                    newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
                FileUtils.writeFile(PASSWORD_FILE, newPasswordField.getText());
                FileUtils.writeFile(SECURITY_QUESTION_FILE, newSecurityQuestionField.getText());
                FileUtils.writeFile(SECURITY_ANSWER_FILE, newSecurityAnswerField.getText());

                DashboardScene dashboard = new DashboardScene();
                dashboard.setStage();
            } else if (!currentPassword.equals(currentPasswordField.getText())) {
                errTag.setText("Incorrect password");
            } else {
                errTag.setText("Passwords don't match");
            }
        });

        this.rootBox.getChildren().addAll(currentPasswordLabel, currentPasswordField, newPasswordLabel, newPasswordField,
                confirmNewPasswordLabel, confirmNewPasswordField, newSecurityQuestionLabel,
                newSecurityQuestionField, newSecurityAnswerLabel, newSecurityAnswerField,
                saveChangesButton, errTag);
    }
}
