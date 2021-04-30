package application;
/*
    Author: Elijah Moya
    Date: 4-21-2021
    controller class for Calculator scene
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class CalculateEnergyController {
    private String name;
    private int age;
    private double weight;
    private int height;
    private String activity;
    private char sex;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private ComboBox activityField;

    @FXML
    private TextField heightFeetField;

    @FXML
    private TextField heightInchField;

    @FXML
    private Button calcBut;

    @FXML
    private RadioButton fCheckbox;

    @FXML
    private RadioButton mCheckbox;

    @FXML
    private Button calendar;

    @FXML
    private Button addFoodBut;

    @FXML
    private Button homeBut;

    @FXML
    private Label caloriesOut;

    /* When calculate button pushed read data fields and output recommended calories */
    @FXML
    public void calculateButtonPushed(ActionEvent event) {
        if(!getVals())//reads in user entered values and if missing values end action
            return;

        HashMap<String, ArrayList<String>> personData = new HashMap<String, ArrayList<String>>();

        Person person = new Person(weight, height, age, sex, "true", activity );
        ArrayList<String> data = person.getData("");
        personData.put(name, data);
        int calories = Biometrics.calculateBMR(personData, name);

        Biometrics.saveUserData(calories);
        caloriesOut.setText("Your recommended daily calorie intake is " + calories + "Cal.");

        resetVal();
    }

    /* Checks that that all fields are valid */
    private boolean getVals() {
        if(!getName())
            return false;
        if(!getSex())
            return false;
        if(!getAge())
            return false;
        if(!getWeight())
            return false;
        if(!getHeight())
            return false;
        if(!getActivity())
            return false;

        return true;
    }

    private boolean getName() {
        name = nameField.getText().trim();
        if(name.equals("")) {
            caloriesOut.setText("Please Enter a name.");
            return false;
        }
        return true;
    }

    private boolean getSex() {
        //assures only one value is checked in the sex field
        if((mCheckbox.isSelected() && fCheckbox.isSelected()) || (!mCheckbox.isSelected() && !fCheckbox.isSelected())) {
            caloriesOut.setText("Please select only one of the check boxes.");
            return false;
        }
        else if(mCheckbox.isSelected())
            sex = 'm';
        else
            sex = 'f';

        return true;
    }

    private boolean getAge() {
        String userAge = ageField.getText().trim();
        if(userAge.equals("")) {
            caloriesOut.setText("Please Enter an age.");
            return false;
        }
        age = Integer.parseInt(userAge);
        return true;
    }

    private boolean getWeight() {
        String userWeight = weightField.getText().trim();
        if(userWeight.equals("")) {
            caloriesOut.setText("Please Enter a weight.");
            return false;
        }
        weight = Double.parseDouble(userWeight);
        return true;
    }

    private boolean getHeight() {
        String userHeightFeet = heightFeetField.getText().trim();
        int heightFeet = 0;
        if(!userHeightFeet.equals(""))
            heightFeet = Integer.parseInt(userHeightFeet);

        String userHeightInch = heightInchField.getText().trim();
        int heightInch = 0;
        if(!userHeightInch.equals(""))
            heightInch = Integer.parseInt(userHeightInch);

        if(userHeightFeet.equals("") && userHeightInch.equals("")) {//if both height fields are empty return error
            caloriesOut.setText("Please Enter values in the height fields.");
            return false;
        }
        height = heightInch + (heightFeet * 12);//converts height field to inches
        return true;
    }

    private boolean getActivity() {
        activity = (String)activityField.getSelectionModel().getSelectedItem();
        if(activity.equals("")) {
            caloriesOut.setText("Please enter a value in the activity field.");
            return false;
        }
        //if user did not enter defined values print error and return false
        else if(!(activity.equals("sedentary") || activity.equals("light") || activity.equals("moderate") || activity.equals("very") || activity.equals("extra"))) {
            caloriesOut.setText("Invalid value in activity field.");
            return false;
        }
        return true;
    }

    private void resetVal() {
        name = "";
        age = 0;
        weight = 0;
        height = 0;
        activity = "";
        sex =' ';

        nameField.clear();
        ageField.clear();
        heightFeetField.clear();
        heightInchField.clear();
        weightField.clear();
    }

    /* Radio actions allow only one button to be pressed at once */
    @FXML
    void femaleRadio(ActionEvent event) {
        mCheckbox.setSelected(false);
    }

    @FXML
    void maleRadio(ActionEvent event) {
        fCheckbox.setSelected(false);
    }

    /* returns to home page */
    @FXML
    void homeButtonPushed(ActionEvent event) {
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

}
