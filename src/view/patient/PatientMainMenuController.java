package view.patient;

/**
 * Sample Skeleton for 'PatientMainMenu.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Desktop;
import java.io.*;

public class PatientMainMenuController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="editInformationButton"
    private Button editInformationButton; // Value injected by FXMLLoader

    @FXML // fx:id="showEntriesButton"
    private Button showEntriesButton; // Value injected by FXMLLoader

    @FXML
    private Button printButton;

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    @FXML
    private Text errorText;

    @FXML
    private Text insuranceText;

    @FXML
    private Text genderText;

    @FXML
    private Text dateOfBirthText;

    @FXML
    private Text addressText;

    private EPAController ePAController;

    private Stage primaryStage;

    public PatientMainMenuController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/PatientMainMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorText.setVisible(false);
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceText.setText("Insurance: "+ePAController.getePA().getActivePatient().getInsurance());
        insuranceNBRText.setText("InsuranceNBR: "+ePAController.getePA().getActivePatient().getInsuranceNBR());
        genderText.setText("Gender: "+ePAController.getePA().getActivePatient().getGender());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateOfBirthText.setText("DateOfBirth: "+dateFormat.format(ePAController.getePA().getActivePatient().getDateOfBirth()));
        addressText.setText("Address: "+ePAController.getePA().getActivePatient().getAddress());
    }

    @FXML
    private void showEntriesClick(ActionEvent event) {
        try {
            MedicalHistoryController medicalHistoryController = new MedicalHistoryController(primaryStage, ePAController);
            Scene scene = new Scene(medicalHistoryController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editInformationClick(ActionEvent event) {
        try {
            EditDetailsController editDetailsController = new EditDetailsController(primaryStage, ePAController);
            Scene scene = new Scene(editDetailsController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void printClick(ActionEvent event) {
        try{
            ePAController.getPatientController().print(ePAController.getePA().getActivePatient());

            try {
                File file = new File("Report.txt");
                if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                {
                    throw new Exception("not supported");
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                }

                errorText.setVisible(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                errorText.setVisible(true);
            }
        }
        catch (Exception e){
            errorText.setVisible(true);
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            PatientRecordController patientRecordController = new PatientRecordController(primaryStage, ePAController);
            Scene scene = new Scene(patientRecordController);
            primaryStage.setScene(scene);
            primaryStage.show();
            ePAController.getePA().setActivePatient(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'PatientMainMenu.fxml'.";
        //assert editInformationButton != null : "fx:id=\"editInformationButton\" was not injected: check your FXML file 'PatientMainMenu.fxml'.";
        //assert showEntriesButton != null : "fx:id=\"showEntriesButton\" was not injected: check your FXML file 'PatientMainMenu.fxml'.";
    }
}

