package view.doctor;

/**
 * Sample Skeleton for 'NewTratmentEntry.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import controller.EPAController;
import model.Revision;
import model.TreatmentEntry;

public class NewTreatmentEntryController extends VBox {

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="createButton"
    private Button createButton; // Value injected by FXMLLoader

    @FXML // fx:id="symptomesField"
    private TextField symptomesField; // Value injected by FXMLLoader

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
    private Text dateOfBirthText;

    @FXML
    private Text genderText;

    @FXML
    private Text doctorText;

    @FXML
    private Text errorText;

    private EPAController ePAController;

    private Stage primaryStage;

    public NewTreatmentEntryController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/NewTreatmentEntry.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorText.setVisible(false);
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date now = new Date(System.currentTimeMillis());
        dateText.setText("Date: " + dateFormat.format(now));
        dateOfBirthText.setText("DateOfBirth: " + dateFormat.format(ePAController.getePA().getActivePatient().getDateOfBirth()));
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        timeText.setText("Time: " + dateFormat.format(now));
        genderText.setText("Gender: "+ePAController.getePA().getActivePatient().getGender());
        doctorText.setText("Doctor: "+ePAController.getePA().getActiveDoctor().getName());
    }

    @FXML
    private void createClick(ActionEvent event) {
        try{
            Date date = new Date(System.currentTimeMillis());
            ePAController.getPatientRecordController().createRevision(ePAController.getePA().getActivePatient().getInsuranceNBR(), findingField.getText(), diagnosisField.getText(), therapyField.getText(), medicationField.getText(), date , notesField.getText(), symptomesField.getText(), new TreatmentEntry(ePAController.getePA().getActiveDoctor()));

            try {
                MainMenuController mainMenuController = new MainMenuController(primaryStage, ePAController);
                Scene scene = new Scene(mainMenuController);
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
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert symptomesField != null : "fx:id=\"symptomesField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert findingField != null : "fx:id=\"findingField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert therapyField != null : "fx:id=\"therapyField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert medicationField != null : "fx:id=\"medicationField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert notesField != null : "fx:id=\"notesField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert diagnosisField != null : "fx:id=\"diagnosisField\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
        //assert summaryButton != null : "fx:id=\"summaryButton\" was not injected: check your FXML file 'NewTratmentEntry.fxml'.";
    }
}

