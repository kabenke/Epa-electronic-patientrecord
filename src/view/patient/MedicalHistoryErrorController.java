package view.patient;

/**
 * Sample Skeleton for 'MedicalHistoryError.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class MedicalHistoryErrorController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="fromDatePicker"
    private DatePicker fromDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="toDatePicker"
    private DatePicker toDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="confirmButton"
    private Button confirmButton; // Value injected by FXMLLoader

    @FXML // fx:id="errorText"
    private Text errorText; // Value injected by FXMLLoader

    private Stage primaryStage;

    public MedicalHistoryErrorController(Stage primaryStage){
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/MedicalHistoryError.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'MedicalHistoryError.fxml'.";
        //assert fromDatePicker != null : "fx:id=\"fromDatePicker\" was not injected: check your FXML file 'MedicalHistoryError.fxml'.";
        //assert toDatePicker != null : "fx:id=\"toDatePicker\" was not injected: check your FXML file 'MedicalHistoryError.fxml'.";
        //assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'MedicalHistoryError.fxml'.";
        //assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'MedicalHistoryError.fxml'.";
    }
}

