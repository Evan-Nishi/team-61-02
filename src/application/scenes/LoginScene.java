package application.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


import data.FileUtils;

/**
 * LoginScene
 * Scenecontroller for login screen
 * 
 * @version 0.5
 */
public class LoginScene implements ScreenI{
    private VBox rootBox = new VBox(10);

    /**
     * @return Scene Returns a scene of the rootBox
     */
    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public LoginScene(){
        this.rootBox.setAlignment(Pos.CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Text appName = new Text("Skiver");
        Text appDescription = new Text("Secure Journaling Application");
        //loginButton here
        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label errTag = new Label();
        loginButton.setOnAction(event -> {
            String password = FileUtils.readFile(PASSWORD_FILE);

            if(pwBox.getText().equals(password) && pwBox.getText().equals("p")){
                FirstLoginScene firstLoginScene = new FirstLoginScene();
                firstLoginScene.setStage();
            } else if (pwBox.getText().equals(password)){
                DashboardScene dashboard = new DashboardScene();
                dashboard.setStage();
            } else {
                errTag.setText("Incorrect password");
            }
        });

        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setOnAction(event -> {
            ForgotPasswordScene forgotPasswordScene = new ForgotPasswordScene();
            forgotPasswordScene.setStage();
        });

        this.rootBox.getChildren().addAll(appName, appDescription, pwBox,loginButton, forgotPasswordButton, errTag);
    }
}