package application.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import utils.FileUtils;

public class LoginScene implements ScreenI{
    private VBox Screen = new VBox(10);

    public VBox getScreen(){
        return this.Screen;
    }

    public LoginScene(String pFile, Stage primaryStage){
        this.Screen.setAlignment(Pos.CENTER);
        this.Screen.setPadding(new Insets(25, 25, 25, 25));
        Text appName = new Text("Skiver");
        Text appDescription = new Text("Secure Journaling Application");
        //loginButton here
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            String password = FileUtils.readFile(pFile);

        });

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");
        Button forgotPasswordButton = new Button("Forgot Password?");
        this.Screen.getChildren().addAll(appName, appDescription, pwBox, forgotPasswordButton);
    }
}