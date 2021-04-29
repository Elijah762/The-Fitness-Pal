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

public class FirstController /*implements Initializable */{

   // @FXML
   // private Circle circleSalad;

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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

   /* public void initialize(URL url, ResourceBundle rb){
    	circleSalad.setStroke(Color.BLUEVIOLET);
    	Image im = new Image("/application/salad.jpg",false);
    	circleSalad.setFill(new ImagePattern(im));
    	circleSalad.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKBLUE));
    }
    */
    

}
