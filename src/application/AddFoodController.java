package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddFoodController{

	private InsertFoodExample foodObj = new InsertFoodExample();
	private Calendar totals = new Calendar();
	private HashMap<String, ArrayList<Integer>> hm;
	
    @FXML
    private Button AddDatabaseAdd;

    @FXML
    private TextField AddCustomCarb;

    @FXML
    private TextField AddCustomFat;

    @FXML
    private TextField AddCustomCalories;

    @FXML
    private TextField AddCustomName;

    @FXML
    private Button ButtonHome;

    @FXML
    private TextField AddCustomProtein;

    @FXML
    private TextField AddDatabaseQty;

    @FXML
    private ListView<String> AddDatabaseList;

    @FXML
    private ChoiceBox<?> AddDatabaseDest;

    @FXML
    private Button AddCustomAdd;
    
    @FXML
    void replaceSceneContentHome(ActionEvent event) {
		try {
			AnchorPane give = (AnchorPane) FXMLLoader.load(getClass().getResource("First.fxml"));
			Scene giveScene = new Scene(give, 500, 500);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(giveScene);
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // Should be called when the page is loaded
    public void init(){
    	foodObj.initializePresetData();
    	refreshList();
    }
    
    // Refresh listview
    public void refreshList(){
    	hm = foodObj.getFoodData();
    	for(Map.Entry<String, ArrayList<Integer>> mapElement : hm.entrySet()){
			String key = (String)mapElement.getKey();
			AddDatabaseList.getItems().add(key);
		}	
    }
    
    // Get the calories, c, p, f data * quantity of selected food and return
    // Should probably be called when the AddDatabaseAdd button (FXID) is pressed
    // NOTE: Currently does nothing with the "Add To: Breakfast/Lunch/Dinner etc." field
    @FXML
    public void addFoodToDate(ActionEvent event){
    	Alert a = new Alert(AlertType.ERROR);
    	
    	// validate quantity exists
    	if(AddDatabaseQty.getText().isEmpty()){
    		a.setTitle("Error");
    		a.setContentText("Quantity is required");
			a.show();
    		return;
    	}
    	
    	// convert String to int for quantity
    	int qty = Integer.parseInt(AddDatabaseQty.getText());
    	
    	// validate quantity is positive
    	if(qty < 0 || qty > 50){
    		a.setTitle("Error");
    		a.setContentText("Quantity must be between 0 and 50");
			a.show();
			return;		
    	}
    	
    	// get the food name (String) selected in the list view
    	String selectedFood = AddDatabaseList.getSelectionModel().getSelectedItem();
    	
    	// validate something was actually selected
    	if(selectedFood.isEmpty()){
    		a.setTitle("Error");
    		a.setContentText("Must select a food from the list");
			a.show();
			return;		
    	}
    	
    	// call Calendar's dateFood() method qty times
    	for(int i = 0; i < qty; i++){
    		try {
				totals.dateFood(selectedFood);
			} catch (IOException e) {
				System.out.println("Error calling dateFood() from Calendar class");
				e.printStackTrace();
			}
    	}
    }
    
	@FXML
	public void addCustomFood(ActionEvent event) throws IOException{
		
		String name = AddCustomName.getText();
		int calories = 0, carb = -1, protein = -1, fat = -1;
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Error");
		
		// Validate input on name and itemName -- must not be null
		if(name.isEmpty()){
			a.setTitle("Food Name Error");
			a.setContentText("Name field must not be empty.");
			a.show();
			return;
		}
			
		// Get itemQuantity. Validate input is a positive integer
		try{
			calories = Integer.parseInt(AddCustomCalories.getText());
		}
		catch(NumberFormatException e){
			calories = 0;
			a.setContentText("Calories must be an integer");
			a.show();
			return;
		}
		
		if(calories < 0){
			a.setContentText("Calories must be a positive number.");
			a.show();
			return;
		}
		
		if(!AddCustomCarb.getText().isEmpty()){
			try{
				carb = Integer.parseInt(AddCustomCarb.getText());
			}
			catch(NumberFormatException e){
				carb = -1;
				a.setContentText("Carbohydrates must be an integer");
				a.show();
				return;
			}
		}
		
		if(!AddCustomProtein.getText().isEmpty()){
			try{
				protein = Integer.parseInt(AddCustomProtein.getText());
			}
			catch(NumberFormatException e){
				protein = -1;
				a.setContentText("Protein must be an integer");
				a.show();
				return;
			}
		}
		
		if(!AddCustomFat.getText().isEmpty()){
			try{
				fat = Integer.parseInt(AddCustomFat.getText());
			}
			catch(NumberFormatException e){
				fat = -1;
				a.setContentText("Fat must be an integer");
				a.show();
				return;
			}
		}
		
		if(carb < 0){
			carb = -1;
		}
		if(fat < 0){
			fat = -1;
		}
		if(protein < 0){
			protein = -1;
		}
		
		// Prepopulate properties file if it doesn't exist. Then load.
		foodObj.initializePresetData();
		foodObj.loadFoodData();
		
		// Add the food
		foodObj.addFood(name, calories, carb, protein, fat);
		
		// Test print
		// foodObj.printContents();
		
		// Show confirmation
		Alert confirm = new Alert(AlertType.CONFIRMATION, "Added " + name + " to the food list.", ButtonType.OK);
		confirm.show();
		
		// Save the food to the properties file
		foodObj.saveFoodData();
		
		// Clear the fields
		AddCustomName.clear();
		AddCustomCalories.clear();
		AddCustomCarb.clear();
		AddCustomProtein.clear();
		AddCustomFat.clear();
		
		// Refresh listview
		refreshList();
	}

}
