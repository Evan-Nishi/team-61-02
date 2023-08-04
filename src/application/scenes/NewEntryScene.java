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
import java.time.LocalTime;

import data.DBConnecter;

/**
 * NewEntryScene
 * Scenecontroller for new entry screen
 * 
 * @version 0.5
 */
public class NewEntryScene implements ScreenI {
    private VBox rootBox = new VBox(10);

    /**
     * @return Scene Returns a scene of the rootBox
     */
    public Scene getScene() {
        return new Scene(rootBox, 500, 500);
    }

    public NewEntryScene() {
        this.rootBox.setAlignment(Pos.TOP_CENTER);
        this.rootBox.setPadding(new Insets(25, 25, 25, 25));
        Label newEntryLabel = new Label("New Entry");

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

        TextField titleField = new TextField();
        titleField.setPromptText("Family Trip to Canada");



        Label bodyLabel = new Label("Body*");
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Today we landed in Vancouver International airport and went to Tim Horton's");
        
        Label dateLabel = new Label("Date*");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        //DateTimeBox
        HBox dateTimeBox = new HBox(10);
        VBox timeBox = new VBox(10);
        VBox dateBox = new VBox(10);
        HBox timeFieldBox = new HBox(10);
        Label timeLabel = new Label("Time*");
        int hour = (LocalTime.now().getHour());
        boolean isPm = false;

        //this is for convience of user to have current time set to local time with the combobox
        //having the correct meridian selected
        if(hour == 24){
            hour = 12;
        } else if (hour > 12){
            isPm = true;
            hour -= 12;
        } else if (hour == 12){
            isPm = true;
        }

        String minute = String.format("%02d", LocalTime.now().getMinute());
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
        Label errTag = new Label();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            try {
                String title = titleField.getText();
                String body = bodyField.getText();
                String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/" + datePicker.getValue().getYear();
                String time;
                if(body.length() == 0){
                    errTag.setText("context required");
                } else {
                    String[] parsedTime = timeField.getText().split(":");
                    int h = Integer.valueOf(parsedTime[0]);
                    int m = Integer.valueOf(parsedTime[1]);
                    if(amPm.getValue().toString().equals("pm") && h != 12){
                        h += 12;
                        time = String.valueOf(h) + ":" + String.valueOf(m);
                    } else {
                        time = timeField.getText();
                    }
                
                    System.out.println(title + " entry saved");
                    DBConnecter.getInstance().insertEntry(title, body, date, time);
                    DashboardScene dashboardScene = new DashboardScene();
                    dashboardScene.setStage();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.rootBox.getChildren().addAll(newEntryLabel,titleLabel, titleField, dateTimeBox, bodyLabel, bodyField, errTag, saveButton,  logoutButton);
    }
}