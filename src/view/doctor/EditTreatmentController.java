package view.doctor;

/**
 * Sample Skeleton for 'EditTreatment.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import model.Revision;
import model.TreatmentEntry;

public class EditTreatmentController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="updateButton"
    private Button updateButton; // Value injected by FXMLLoader

    @FXML // fx:id="symptomsField"
    private TextField symptomsField; // Value injected by FXMLLoader

    @FXML // fx:id="findingField"
    private TextField findingField; // Value injected by FXMLLoader

    @FXML // fx:id="therapyField"
    private TextField therapyField; // Value injected by FXMLLoader

    @FXML // fx:id="medicationField"
    private TextField medicationField; // Value injected by FXMLLoader

    @FXML // fx:id="notesField"
    private TextField notesField; // Value injected by FXMLLoader

    @FXML // fx:id="diagnosisField"
    private TextField diagnosisField; // Value injected by FXMLLoader

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    @FXML
    private Text dateText;

    @FXML
    private Text timeText;

    @FXML
    private Text doctorText;

    @FXML
    private Text dateOfBirthText;

    @FXML
    private Text genderText;

    @FXML
    private Text errorText;

    private EPAController ePAController;

    private Stage primaryStage;

    public EditTreatmentController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/EditTreatment.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorText.setVisible(false);
        doctorText.setText("Doctor: " + ePAController.getePA().getActiveEntry().getDoctor().getName());
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date now = new Date(System.currentTimeMillis());
        dateText.setText("Date: " + dateFormat.format(now));
        dateOfBirthText.setText("DateOfBirth: " + dateFormat.format(ePAController.getePA().getActivePatient().getDateOfBirth()));
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        timeText.setText("Time: " + dateFormat.format(now));
        genderText.setText("Gender: "+ePAController.getePA().getActivePatient().getGender());
        diagnosisField.setText(ePAController.getePA().getActiveRevision().getDiagnosis());
        symptomsField.setText(ePAController.getePA().getActiveRevision().getSymptomes());
        findingField.setText(ePAController.getePA().getActiveRevision().getFinding());
        therapyField.setText(ePAController.getePA().getActiveRevision().getTherapy());
        medicationField.setText(ePAController.getePA().getActiveRevision().getMedicationPlans());
        notesField.setText(ePAController.getePA().getActiveRevision().getNotes());
    }

    @FXML
    private void updateClick(ActionEvent event) {

        try{
            Date date = new Date(System.currentTimeMillis());
            ePAController.getPatientRecordController().createRevision(ePAController.getePA().getActivePatient().getInsuranceNBR(), findingField.getText(), diagnosisField.getText(), therapyField.getText(), medicationField.getText(), date , notesField.getText(), symptomsField.getText(), ePAController.getePA().getActiveEntry());
            ArrayList<Revision> revisions = ePAController.getePA().getActiveEntry().getRevisions();
            Revision newestRevision = revisions.get(revisions.size()-1);
            ePAController.getePA().setActiveRevision(newestRevision);
            newestRevision.setTransfer(revisions.get(revisions.size()-2).getTransfer());

            try {
                ViewTreatmentEntryController viewTreatmentEntryController = new ViewTreatmentEntryController(primaryStage, ePAController);
                Scene scene = new Scene(viewTreatmentEntryController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(IllegalArgumentException e){
            errorText.setVisible(true);
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            ViewTreatmentEntryController viewTreatmentEntryController = new ViewTreatmentEntryController(primaryStage, ePAController);
            Scene scene = new Scene(viewTreatmentEntryController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert symptomsField != null : "fx:id=\"symptomsField\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert findingField != null : "fx:id=\"findingField\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert therapyField != null : "fx:id=\"therapyField\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert medicationField != null : "fx:id=\"medicationField\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert notesField != null : "fx:id=\"notesField\" was not injected: check your FXML file 'EditTreatment.fxml'.";
        //assert diagnosisField != null : "fx:id=\"diagnosisField\" was not injected: check your FXML file 'EditTreatment.fxml'.";

    }
}

