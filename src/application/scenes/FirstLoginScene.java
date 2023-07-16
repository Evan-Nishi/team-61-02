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
import utils.FileUtils;
import javafx.stage.Stage;


public class FirstLoginScene {
    private VBox rootBox = new VBox(10);

    public Scene getScreen(){
        return new Scene(rootBox, 400, 400);
    }

	public void addNode(Node node){
		rootBox.getChildren().add(node);
	}
    public FirstLoginScene(String pwFile, String qFile, String aFile, Stage primaryStage){
        this.rootBox.setAlignment(Pos.CENTER);
		this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label changePassLabel = new Label("Set Password");
		Label confirmPassLabel = new Label("Confirm Password");
		Button changeButton = new Button("Set Password");
		PasswordField changePwBox = new PasswordField(), confirmPwBox = new PasswordField();
		Label securityQLabel = new Label("Set Security Question");
		Label securityALabel = new Label("Set Answer");
		TextField securityQBox = new TextField(), securityABox = new TextField();
		changeButton.setOnAction(setEvent -> {
			if(changePwBox.getText().equals(confirmPwBox.getText())){								
				FileUtils.writeFile(pwFile, changePwBox.getText());
				FileUtils.writeFile(qFile, securityQBox.getText());
				FileUtils.writeFile(aFile, securityABox.getText());
				
			} else {
				System.out.println("Passwords don't match");
				}
		});
        //TODO: make more neat.  Maybe have queue of nodes to be added
        //and add them using an iter
        this.rootBox.getChildren().addAll(changePassLabel, changePwBox, confirmPassLabel, confirmPwBox, securityQLabel, securityQBox, securityALabel, securityABox, changeButton);
    }
}

