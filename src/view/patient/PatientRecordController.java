package view.patient;

/**
 * Sample Skeleton for 'PatientRecord.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import view.main.LoginPageController;

public class PatientRecordController extends VBox {

    @FXML // fx:id="insuranceNBRField"
    private TextField insuranceNBRField; // Value injected by FXMLLoader

    @FXML // fx:id="searchButton"
    private Button searchButton; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="errorText"
    private Text errorText; // Value injected by FXMLLoader

    private EPAController ePAController;

    private Stage primaryStage;

    public PatientRecordController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/PatientRecord.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorText.setVisible(false);
    }

    @FXML
    private void searchClick(ActionEvent event) {
        if(ePAController.getPatientRecordController().loadPatientRecord(insuranceNBRField.getText()) == null){
            errorText.setVisible(true);
        }
        else {
            errorText.setVisible(false);
            ePAController.getePA().setActivePatient(ePAController.getPatientRecordController().loadPatientRecord(insuranceNBRField.getText()).getPatient());
            try {
                PatientMainMenuController patientMainMenuController = new PatientMainMenuController(primaryStage, ePAController);
                Scene scene = new Scene(patientMainMenuController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            LoginPageController loginPageController = new LoginPageController(primaryStage, ePAController);
            Scene scene = new Scene(loginPageController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert insuranceNBRField != null : "fx:id=\"insuranceNBRField\" was not injected: check your FXML file 'PatientRecord.fxml'.";
        //assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'PatientRecord.fxml'.";
        //assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'PatientRecord.fxml'.";
    }
}

