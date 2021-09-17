package view.patient;

/**
 * Sample Skeleton for 'RestrictData.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.IOException;
import java.text.DateFormat;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class RestrictDataController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="symptomesCheckBox"
    private CheckBox symptomsCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="diagnosisCheckBox"
    private CheckBox diagnosisCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="findingCheckBox"
    private CheckBox findingCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="therapyCheckBox"
    private CheckBox therapyCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="medicationCheckBox"
    private CheckBox medicationCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="notesCheckBox"
    private CheckBox notesCheckBox; // Value injected by FXMLLoader

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
    private Text doctorText;

    private EPAController ePAController;

    private Stage primaryStage;

    public RestrictDataController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/RestrictData.fxml"));
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

        diagnosisCheckBox.setText("Diagnosis: "+ePAController.getePA().getActiveRevision().getDiagnosis());
        symptomsCheckBox.setText("Symptoms: "+ePAController.getePA().getActiveRevision().getSymptomes());
        findingCheckBox.setText("Finding: "+ePAController.getePA().getActiveRevision().getFinding());
        therapyCheckBox.setText("Therapy: "+ePAController.getePA().getActiveRevision().getTherapy());
        medicationCheckBox.setText("Medication: "+ePAController.getePA().getActiveRevision().getMedicationPlans());
        notesCheckBox.setText("Notes: "+ePAController.getePA().getActiveRevision().getNotes());

        doctorText.setText("Doctor: "+ePAController.getePA().getActiveEntry().getDoctor().getName());

        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[0]){
            symptomsCheckBox.setSelected(true);
        }
        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[1]){
            findingCheckBox.setSelected(true);
        }
        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[2]){
            diagnosisCheckBox.setSelected(true);
        }
        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[3]){
            therapyCheckBox.setSelected(true);
        }
        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[4]){
            medicationCheckBox.setSelected(true);
        }
        if(ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[5]){
            notesCheckBox.setSelected(true);
        }

    }

    @FXML
    private void saveClick(ActionEvent event) {

        boolean[] restrictedData = new boolean[6];
        restrictedData[0] = symptomsCheckBox.isSelected();
        restrictedData[1] = findingCheckBox.isSelected();
        restrictedData[2] = diagnosisCheckBox.isSelected();
        restrictedData[3] = therapyCheckBox.isSelected();
        restrictedData[4] = medicationCheckBox.isSelected();
        restrictedData[5] = notesCheckBox.isSelected();

        ePAController.getPatientRecordController().restrictData(ePAController.getePA().getActivePatient().getInsuranceNBR(), restrictedData, ePAController.getePA().getActiveEntry());

        try {
            ViewTreatmentEntryUeberweisungController viewTreatmentEntryUeberweisungController = new ViewTreatmentEntryUeberweisungController(primaryStage, ePAController);
            Scene scene = new Scene(viewTreatmentEntryUeberweisungController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            ViewTreatmentEntryUeberweisungController viewTreatmentEntryUeberweisungController = new ViewTreatmentEntryUeberweisungController(primaryStage, ePAController);
            Scene scene = new Scene(viewTreatmentEntryUeberweisungController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert symptomesCheckBox != null : "fx:id=\"symptomesCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert diagnosisCheckBox != null : "fx:id=\"diagnosisCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert findingCheckBox != null : "fx:id=\"findingCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert therapyCheckBox != null : "fx:id=\"therapyCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert medicationCheckBox != null : "fx:id=\"medicationCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";
        //assert notesCheckBox != null : "fx:id=\"notesCheckBox\" was not injected: check your FXML file 'RestrictData.fxml'.";

    }
}

