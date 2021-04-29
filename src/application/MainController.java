package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
	// Main.fxml elements

    @FXML
    private Button addFoodBut;

    @FXML
    private Button biometrics;

    @FXML
    private Button calendar;
    
    /* WINDOW CHANGE METHODS */
    
    // Change window to the BMR calculation screen
	@FXML
    private void replaceSceneContentOne(ActionEvent event){
		try {
			AnchorPane give = (AnchorPane) FXMLLoader.load(getClass().getResource("CalculateEnergy.fxml"));
			Scene giveScene = new Scene(give, 500, 500);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(giveScene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	// Change window to the addfood screen
	@FXML
    private void replaceSceneContentTwo(ActionEvent event){
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFoods.fxml"));
			AddFoodController newControl = new AddFoodController();
			loader.setController(newControl);
			AnchorPane root = (AnchorPane)loader.load();
			primaryStage.getScene().setRoot(root);
			newControl.init();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	// Change window to the Calendar screen
	@FXML
    private void replaceSceneContentThree(ActionEvent event){
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
			AddFoodController newControl = new AddFoodController();
			loader.setController(newControl);
			AnchorPane root = (AnchorPane)loader.load();
			primaryStage.getScene().setRoot(root);
			newControl.init();

		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
}