package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalendarController{

    @FXML
    private Text CalendarWeeklyNumber;

    @FXML
    private Button ButtonHome;
    
    @FXML
    private ListView<String> monBox;

    @FXML
    private ListView<String> tueBox;

    @FXML
    private Text weekString;

    @FXML
    private ListView<String> wedBox;

    @FXML
    private ListView<String> thuBox;

    @FXML
    private ListView<String> friBox;

    @FXML
    private ListView<String> satBox;

    @FXML
    private ListView<String> sunBox;

    @FXML
    
    public void initialize() {
        fillScene();
    }
    
    // fills the scene with the information
    private void fillScene() {
    	Calendar cal = new Calendar();
		Date today = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("E/M/d/yyyy");
		String fullDate = ft.format(today);
		String[] days = cal.getDays(fullDate); // days[7] stores weekly total cals
		int shift = 0;
		String[] months = {"", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
		
		
		// displays all the reports on their respective displays
		sunBox.getItems().add(days[0]);
		monBox.getItems().add(days[1]);
		tueBox.getItems().add(days[2]);
		wedBox.getItems().add(days[3]);
		thuBox.getItems().add(days[4]);
		friBox.getItems().add(days[5]);
		satBox.getItems().add(days[6]);
		
		
		// creates the header Week of: MONTH DAY - MONTH DAY
		String[] splitDate = fullDate.split("/");
		shift = cal.findSundaySwitch(splitDate[0]);
		String startDate = cal.shiftDate(fullDate, shift);
		String[] splitStart = startDate.split("/");
		int month1 = Integer.parseInt(splitStart[1]);
		String endDate = cal.shiftDate(startDate, 6);
		String[] splitEnd = endDate.split("/");
		int month2 = Integer.parseInt(splitEnd[1]);
		weekString.setText(months[month1] + " " + splitStart[2] + " - " + months[month2] + " " + splitEnd[2]);
		
		// displays the weekly total
		CalendarWeeklyNumber.setText(days[7]);
		
	}

	@FXML
    void replaceSceneContentHome(ActionEvent event) {
    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			loader.setController(new MainController());
			AnchorPane root = (AnchorPane)loader.load();
			primaryStage.getScene().setRoot(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
    }

}
