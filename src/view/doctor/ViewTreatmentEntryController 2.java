package view.doctor;

/**
 * Sample Skeleton for 'ViewTreatmentEntry.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class ViewTreatmentEntryController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="editInformationButton"
    private Button editInformationButton; // Value injected by FXMLLoader

    @FXML // fx:id="viewRevisionsButton"
    private Button viewRevisionsButton; // Value injected by FXMLLoader

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

    @FXML

    private EPAController ePAController;

    private Stage primaryStage;

    public ViewTreatmentEntryController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/ViewTreatmentEntry.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dateText.setText("Date: " + dateFormat.format(ePAController.getePA().getActiveRevision().getDate()));
        dateOfBirthText.setText("DateOfBirth: " + dateFormat.format(ePAController.getePA().getActivePatient().getDateOfBirth()));
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        timeText.setText("Time: " + dateFormat.format(ePAController.getePA().getActiveRevision().getDate()));
        genderText.setText("Gender: "+ePAController.getePA().getActivePatient().getGender());
        doctorText.setText("Doctor: "+ePAController.getePA().getActiveEntry().getDoctor().getName());

        diagnosisText.setText("Diagnosis: "+ePAController.getePA().getActiveRevision().getDiagnosis());
        symptomsText.setText("Symptoms: "+ePAController.getePA().getActiveRevision().getSymptomes());
        findingText.setText("Finding: "+ePAController.getePA().getActiveRevision().getFinding());
        therapyText.setText("Therapy: "+ePAController.getePA().getActiveRevision().getTherapy());
        medicationText.setText("Medication: "+ePAController.getePA().getActiveRevision().getMedicationPlans());
        notesText.setText("Notes: "+ePAController.getePA().getActiveRevision().getNotes());

        if(!ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")) {
            if(!(ePAController.getePA().getActiveEntry().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                && ePAController.getePA().getActiveEntry().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress())))
            {
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[0]){
                    symptomsText.setText("Symptoms: -----");
                }
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[1]){
                    findingText.setText("Finding: -----");
                }
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[2]){
                    diagnosisText.setText("Diagnosis: -----");
                }
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[3]){
                    therapyText.setText("Therapy: -----");
                }
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[4]){
                    medicationText.setText("Medication: -----");
                }
                if(!ePAController.getePA().getActiveRevision().getTransfer().getTransferData()[5]){
                    notesText.setText("Notes: -----");
                }
            }
        }


        if(!ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
            viewRevisionsButton.setVisible(false);
        }

        if(!(ePAController.getePA().getActiveEntry().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                && ePAController.getePA().getActiveEntry().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress())))
        {
            editInformationButton.setVisible(false);
        }
    }

    @FXML
    private void revisionsClick(ActionEvent event) {
        try {
            ViewRevisionsController viewRevisionsController = new ViewRevisionsController(primaryStage, ePAController);
            Scene scene = new Scene(viewRevisionsController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editClick(ActionEvent event) {
        try {
            EditTreatmentController editTreatmentController = new EditTreatmentController(primaryStage, ePAController);
            Scene scene = new Scene(editTreatmentController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backClick(ActionEvent event) {
        try {
            MainMenuController mainMenuController = new MainMenuController(primaryStage, ePAController);
            Scene scene = new Scene(mainMenuController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ViewTreatmentEntry.fxml'.";
        //assert editInformationButton != null : "fx:id=\"editInformationButton\" was not injected: check your FXML file 'ViewTreatmentEntry.fxml'.";
        //assert viewRevisionsButton != null : "fx:id=\"viewRevisionsButton\" was not injected: check your FXML file 'ViewTreatmentEntry.fxml'.";
    }
}

