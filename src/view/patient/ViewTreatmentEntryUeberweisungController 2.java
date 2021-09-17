package view.patient;

/**
 * Sample Skeleton for 'ViewTreatmentEntryUeberweisung.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.text.DateFormat;

import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import controller.EPAController;

public class ViewTreatmentEntryUeberweisungController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="restrictDataButton"
    private Button restrictDataButton; // Value injected by FXMLLoader

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    @FXML
    private Text dateText;

    @FXML
    private Text timeText;

    @FXML
    private Text dateOfBirthText;

    @FXML
    private Text genderText;

    @FXML
    private Text symptomsText;

    @FXML
    private Text findingText;

    @FXML
    private Text therapyText;

    @FXML
    private Text medicationText;

    @FXML
    private Text notesText;

    @FXML
    private Text doctorText;

    @FXML
    private Text diagnosisText;

    private EPAController ePAController;

    private Stage primaryStage;

    public ViewTreatmentEntryUeberweisungController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/ViewTreatmentEntryUeberweisung.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateText.setText("Date: " + dateFormat.format(ePAController.getePA().getActiveRevision().getDate()));
        dateOfBirthText.setText("DateOfBirth: " + dateFormat.format(ePAController.getePA().getActivePatient().getDateOfBirth()));
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        timeText.setText("Time: " + dateFormat.format(ePAController.getePA().getActiveRevision().getDate()));
        genderText.setText("Gender: "+ePAController.getePA().getActivePatient().getGender());

        diagnosisText.setText("Diagnosis: "+ePAController.getePA().getActiveRevision().getDiagnosis());
        symptomsText.setText("Symptoms: "+ePAController.getePA().getActiveRevision().getSymptomes());
        findingText.setText("Finding: "+ePAController.getePA().getActiveRevision().getFinding());
        therapyText.setText("Therapy: "+ePAController.getePA().getActiveRevision().getTherapy());
        medicationText.setText("Medication: "+ePAController.getePA().getActiveRevision().getMedicationPlans());
        notesText.setText("Notes: "+ePAController.getePA().getActiveRevision().getNotes());

        doctorText.setText("Doctor: "+ePAController.getePA().getActiveEntry().getDoctor().getName());

    }

    @FXML
    private void restrictClick(ActionEvent event) {
        try {
            RestrictDataController restrictDataController = new RestrictDataController(primaryStage, ePAController);
            Scene scene = new Scene(restrictDataController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            MedicalHistoryController medicalHistoryController = new MedicalHistoryController(primaryStage, ePAController);
            Scene scene = new Scene(medicalHistoryController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ViewTreatmentEntryUeberweisung.fxml'.";
        //assert restrictDataButton != null : "fx:id=\"restrictDataButton\" was not injected: check your FXML file 'ViewTreatmentEntryUeberweisung.fxml'.";
    }
}

