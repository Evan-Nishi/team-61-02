package application;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;


import application.scenes.FirstLoginScene;

public class Main extends Application {

	private static final String PASSWORD_FILE = "password.txt";
	private static final String SECURITY_QUESTION_FILE = "question.txt";
	private static final String SECURITY_ANSWER_FILE = "answer.txt";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Skiver");

		VBox loginVBox = new VBox(10);
		loginVBox.setAlignment(Pos.CENTER);
		loginVBox.setPadding(new Insets(25, 25, 25, 25));

		Text appName = new Text("Skiver");
		Text appDescription = new Text("Secure Journaling Application");

		PasswordField pwBox = new PasswordField();
		pwBox.setPromptText("Password");
		
		Button loginButton = new Button("Login");
		loginButton.setOnAction(event -> {
			try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
				String password = reader.readLine();
				if(pwBox.getText().equals(password) && pwBox.getText().equals("p")) {
					FirstLoginScene firstLoginScene = new FirstLoginScene(PASSWORD_FILE, SECURITY_QUESTION_FILE, SECURITY_ANSWER_FILE, primaryStage);
					//TODO: using start() to navigate back to home should be changed
					//implement a scenecontroller
					Button logoutButton = new Button("Logout");
					logoutButton.setOnAction(logoutEvent -> {
						try {
							start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					firstLoginScene.addNode(logoutButton);
					primaryStage.setScene(firstLoginScene.getScreen());
					primaryStage.show();
				}
				else if (pwBox.getText().equals(password)) {
					//TODO: abstract this into another class
					VBox mainAppVBox = new VBox(10);
					mainAppVBox.setAlignment(Pos.CENTER);
					mainAppVBox.setPadding(new Insets(25, 25, 25, 25));

					Label welcomeLabel = new Label("Main App stuff");
					mainAppVBox.getChildren().add(welcomeLabel);
					Button logoutButton = new Button("Logout");
					logoutButton.setOnAction(logoutEvent -> {
						try {
							start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					mainAppVBox.getChildren().add(logoutButton);
					Scene mainAppScene = new Scene(mainAppVBox, 400, 400);
					primaryStage.setScene(mainAppScene);
					primaryStage.show();
				}
				else {
					System.out.println("Password doesn't match!");
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		});

		Button forgotPasswordButton = new Button("Forgot Password?");

		loginVBox.getChildren().addAll(appName, appDescription, pwBox, loginButton, forgotPasswordButton);

		Scene loginScene = new Scene(loginVBox, 400, 400);
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}