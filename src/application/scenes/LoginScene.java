package application.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
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
        String password = FileUtils.readFile(PASSWORD_FILE);

        loginButton.setOnAction(event -> {
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
            if(password.equals("p")){
                errTag.setText("Security question not set");
            } else {
                ForgotPasswordScene forgotPasswordScene = new ForgotPasswordScene();
                forgotPasswordScene.setStage();
            }
        });

        Button changePasswordButton = new Button("Change Password");
        changePasswordButton.setOnAction(event -> {
            if(password.equals("p")){
                errTag.setText("Password not set");
            } else {
                ChangePasswordScene changePasswordScene = new ChangePasswordScene();
                changePasswordScene.setStage();
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(forgotPasswordButton, changePasswordButton);

        this.rootBox.getChildren().addAll(appName, appDescription, pwBox,loginButton, buttonBox, errTag);
    }
}