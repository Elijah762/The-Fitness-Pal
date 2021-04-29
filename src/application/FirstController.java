package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstController {

    @FXML
    private Button biometrics;

    @FXML
    private Button addFoodBut;

    @FXML
    private Button calendar;

    @FXML
    void bioButtonPushed(ActionEvent event) {
        try {
            AnchorPane give = (AnchorPane) FXMLLoader.load(getClass().getResource("CalculateEnergy.fxml"));
            Scene giveScene = new Scene(give, 442, 213);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(giveScene);
            window.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addButtonPushed(ActionEvent event) {
            try {
                AnchorPane give = (AnchorPane) FXMLLoader.load(getClass().getResource("AddFoods.fxml"));
                Scene giveScene = new Scene(give, 442, 213);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(giveScene);
                window.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    void calendarButtonPushed(ActionEvent event) {
        try {
            AnchorPane give = (AnchorPane) FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            Scene giveScene = new Scene(give, 442, 213);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(giveScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
