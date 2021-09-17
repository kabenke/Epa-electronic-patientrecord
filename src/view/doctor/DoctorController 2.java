package view.doctor;

/**
 * Sample Skeleton for 'Doctor.fxml' Controller Class
 */

import java.io.IOException;

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import view.main.LoginPageController;

public class DoctorController extends VBox {

    @FXML // fx:id="insuranceNBRField"
    private TextField insuranceNBRField; // Value injected by FXMLLoader

    @FXML // fx:id="loadButton"
    private Button loadButton; // Value injected by FXMLLoader

    @FXML // fx:id="patientErrorText"
    private Text patientErrorText; // Value injected by FXMLLoader

    @FXML // fx:id="doctorErrorText"
    private Text doctorErrorText; // Value injected by FXMLLoader

    @FXML // fx:id="doctorsOfficeField"
    private TextField doctorsOfficeField; // Value injected by FXMLLoader

    @FXML // fx:id="doctorsNameField"
    private TextField doctorsNameField; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    private EPAController ePAController;

    private Stage primaryStage;

    public DoctorController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/Doctor.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        patientErrorText.setVisible(false);
        doctorErrorText.setVisible(false);
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

    @FXML
    private void loadClick(ActionEvent event) {
        if(ePAController.getDoctorListController().loadDoctor(doctorsNameField.getText(), doctorsOfficeField.getText()) == null){
            patientErrorText.setVisible(false);
            doctorErrorText.setVisible(true);
        }
        else{
            doctorErrorText.setVisible(false);
            if(ePAController.getPatientRecordController().loadPatientRecord(insuranceNBRField.getText())==null){
                patientErrorText.setVisible(true);
            }
            else{
                ePAController.getePA().setActiveDoctor(ePAController.getDoctorListController().loadDoctor(doctorsNameField.getText(), doctorsOfficeField.getText()));
                ePAController.getePA().setActivePatient(ePAController.getPatientRecordController().loadPatientRecord(insuranceNBRField.getText()).getPatient());

                try {
                    MainMenuController mainMenuController = new MainMenuController(primaryStage, ePAController);
                    Scene scene = new Scene(mainMenuController);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert insuranceNBRField != null : "fx:id=\"insuranceNBRField\" was not injected: check your FXML file 'Doctor.fxml'.";
        //assert loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'Doctor.fxml'.";
        //assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'Doctor.fxml'.";
        //assert doctorsOfficeField != null : "fx:id=\"doctorsOfficeField\" was not injected: check your FXML file 'Doctor.fxml'.";
        //assert doctorsNameField != null : "fx:id=\"doctorsNameField\" was not injected: check your FXML file 'Doctor.fxml'.";
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'Doctor.fxml'.";

    }
}

