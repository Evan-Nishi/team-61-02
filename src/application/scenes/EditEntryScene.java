package application.scenes;

import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.DBConnecter;
import data.JournalEntry;

/**
 * EditEntry
 * Scenecontroller for edit entry screen
 * 
 * @version 0.5
 */
public class EditEntryScene implements ScreenI {
    private VBox rootBox = new VBox(10);

    /**
     * @return Scene Returns a scene of the rootBox
     */
    public Scene getScene() {
        return new Scene(rootBox, 500, 500);
    }

    public EditEntryScene(JournalEntry initialEntry) {
        this.rootBox.setAlignment(Pos.TOP_CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label newEntryLabel = new Label("Edit Entry");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(logoutEvent -> {
            try {
                LoginScene loginScene = new LoginScene();
                loginScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Label titleLabel = new Label("Title");

        TextField titleField = new TextField(initialEntry.getTitle());
        titleField.setPromptText("Family Trip to Canada");



        Label bodyLabel = new Label("Body*");
        TextArea bodyField = new TextArea(initialEntry.getContext());
        bodyField.setPromptText("Today we landed in Vancouver International airport and went to Tim Horton's");
        
        
        Label dateLabel = new Label("Date*");
        DatePicker datePicker = new DatePicker();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/u");
        //LocalDate.parse(initialEntry.getDate(), formatter)
        datePicker.setValue(LocalDate.parse(initialEntry.getDate(), formatter));

        //DateTimeBox
        HBox dateTimeBox = new HBox(10);
        VBox timeBox = new VBox(10);
        VBox dateBox = new VBox(10);
        HBox timeFieldBox = new HBox(10);
        Label timeLabel = new Label("Time*");

        String[] timeArr = initialEntry.getTime().split(":");

        
        int hour = Integer.parseInt(timeArr[0]);
        boolean isPm = false;

        if(hour == 24){
            hour = 12;
        } else if (hour > 12){
            isPm = true;
            hour -= 12;
        } else if (hour == 12){
            isPm = true;
        }
        String minute = String.format("%02d", Integer.parseInt(timeArr[1]));

        
        String defaultVal = (String.valueOf(hour) + ":" + minute);

        TextField timeField = new TextField(defaultVal);
        ComboBox amPm = new ComboBox<String>();
        amPm.getItems().addAll("pm","am");
        amPm.setValue( isPm ? "pm": "am");



        timeField.focusedProperty().addListener((arg0, oldVal, newVal) -> {
            if(!newVal){
                if(!timeField.getText().matches("(0?[1-9]|1[0-2]):[0-5][0-9]")){
                    timeField.setText(defaultVal);
                }
            }
        });

        timeFieldBox.getChildren().addAll(timeField, amPm);
        timeBox.getChildren().addAll(timeLabel, timeFieldBox);
        dateBox.getChildren().addAll(dateLabel, datePicker);
        dateTimeBox.getChildren().addAll(dateBox, timeBox);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            try {
                String formattedDate = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/" + datePicker.getValue().getYear();
                System.out.println(formattedDate);
                JournalEntry newEntry = new JournalEntry(
                    titleField.getText(),
                    bodyField.getText(),
                    formattedDate,
                    timeField.getText(),
                    initialEntry.getID()
                );
                System.out.println(newEntry.getDate());
                DBConnecter.getInstance().editEntry(initialEntry.getID(), newEntry);
                DashboardScene dashboardScene = new DashboardScene();
                dashboardScene.setStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.rootBox.getChildren().addAll(newEntryLabel,titleLabel, titleField, dateTimeBox, bodyLabel, bodyField, saveButton,  logoutButton);
    }
}