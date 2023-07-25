package application.scenes;

import data.FileUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

/**
 * FirstLoginScene
 * Scenecontroller for initial login screen
 * 
 * @version 0.5
 */
public class FirstLoginScene implements ScreenI{
    private VBox rootBox = new VBox(10);

    /**
     * @return Scene Returns a scene of the rootBox
     */
    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public FirstLoginScene(){
        this.rootBox.setAlignment(Pos.CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label changePassLabel = new Label("Set Password");
        Label confirmPassLabel = new Label("Confirm Password");
        Button changeButton = new Button("Set Password");
        PasswordField changePwBox = new PasswordField(), confirmPwBox = new PasswordField();
        Label securityQLabel = new Label("Set Security Question");
        Label securityALabel = new Label("Set Answer");
        TextField securityQBox = new TextField(), securityABox = new TextField();
        Label errTag = new Label();
        changeButton.setOnAction(setEvent -> {
            if(changePwBox.getText().equals(confirmPwBox.getText())){
                FileUtils.writeFile(PASSWORD_FILE, changePwBox.getText());
                FileUtils.writeFile(SECURITY_QUESTION_FILE, securityQBox.getText());
                FileUtils.writeFile(SECURITY_ANSWER_FILE, securityABox.getText());
                DashboardScene dashBoard = new DashboardScene();
                dashBoard.setStage();
            } else {
                errTag.setText("Passwords don't match");
            }
        });

        Button logoutButton = new Button("Back");
        logoutButton.setOnAction(logoutEvent -> {
            try {
                LoginScene loginScene = new LoginScene();
                loginScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //TODO: make more neat.  Maybe have queue of nodes to be added
        //and add them using an iter
        this.rootBox.getChildren().addAll(changePassLabel, changePwBox, confirmPassLabel, confirmPwBox, securityQLabel, securityQBox, securityALabel, securityABox, changeButton, logoutButton, errTag);
    }
}

