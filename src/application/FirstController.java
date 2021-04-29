package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
    void biometButtonPushed(ActionEvent event) {

    }

    @FXML
    void addButtonPushed(ActionEvent event) {

    }

    @FXML
    void calendarButtonPushed(ActionEvent event) {

    }

   /* public void initialize(URL url, ResourceBundle rb){
    	circleSalad.setStroke(Color.BLUEVIOLET);
    	Image im = new Image("/application/salad.jpg",false);
    	circleSalad.setFill(new ImagePattern(im));
    	circleSalad.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKBLUE));
    }
    */
    

}
