package application.scenes;

import data.DBConnecter;
import data.JournalEntry;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class DashboardScene implements ScreenI{
    private VBox rootBox = new VBox(10);

    public Scene getScene(){
        return new Scene(rootBox, 400, 400);
    }

    public DashboardScene(){
        this.rootBox.setAlignment(Pos.CENTER);
	    this.rootBox.setPadding(new Insets(25, 25, 25, 25));
		Label welcomeLabel = new Label("Search Entries");

        TextField searchBar = new TextField();
        searchBar.setPromptText("search entries");

        VBox searchRes = new VBox();
        Label errTag = new Label();
        //have searchbar fetch on enter rather than on character update is better as it limits db calls
        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent k){
                errTag.setText("");
                if (k.getCode().equals(KeyCode.ENTER)) {
                    ArrayList<JournalEntry> entries = DBConnecter.getInstance().searchEntries(searchBar.getText());
                    searchRes.getChildren().clear();
                    for (int i = 0; i < entries.size(); i++){
                        searchRes.getChildren().add(entryNode(entries.get(i), searchRes));
                    }
                    if(entries.size() == 0){
                        errTag.setText("No results found");
                    }
                 }
        }});

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

		this.rootBox.getChildren().addAll(welcomeLabel, searchBar,  newEntryButton, logoutButton, searchRes, errTag);
    }

    /**
     * entryNode
     * used to loop through retrived entries and display to UI
     * @param JournalEntry entry: the entry being added
     * @param VBox searchRes: the parent VBox where results are displayed
     * @return
     */

    public HBox entryNode(JournalEntry entry, VBox searchRes){
        HBox root = new HBox(15);
        Label title = new Label(entry.getTitle());
        Label date = new Label(entry.getDate());
        Button editButton = new Button("Edit");
        editButton.setOnAction(newEvent -> {
            try{
                EditEntryScene editEntryScene = new EditEntryScene(entry);
                editEntryScene.setStage();
            } catch(Exception e){
                e.printStackTrace();
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(Event -> {
            try{
                DBConnecter.getInstance().deleteEntry(entry.getTitle());
                searchRes.getChildren().remove(root);
            } catch(Exception e){
                e.printStackTrace();
            }
        });
        root.getChildren().addAll(title, date, editButton, deleteButton);
        return root;
    }
}
