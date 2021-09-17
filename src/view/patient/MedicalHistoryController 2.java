package view.patient;

/**
 * Sample Skeleton for 'MedicalHistory.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import model.Revision;
import model.TreatmentEntry;

public class MedicalHistoryController extends VBox {

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

    @FXML // fx:id="errorText"
    private ListView<TreatmentEntry> menuList; // Value injected by FXMLLoader

    private EPAController ePAController;

    private Stage primaryStage;

    public MedicalHistoryController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/MedicalHistory.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorText.setVisible(false);
        menuList.setVisible(false);


    }

    @FXML
    private void onPickerAction(ActionEvent event){
        if(fromDatePicker.getValue()!=null && toDatePicker.getValue()!=null){
            menuList.setVisible(true);
            LocalDate localDate = fromDatePicker.getValue().minusDays(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth());
            Date from = calendar.getTime();
            localDate = toDatePicker.getValue();
            calendar = Calendar.getInstance();
            calendar.set(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth());
            Date to = calendar.getTime();
            menuList.getItems().clear();
            try{
                ArrayList<TreatmentEntry> entrys = ePAController.getPatientRecordController().summaryByDate(from, to, ePAController.getePA().getActivePatient().getInsuranceNBR());
                for(int i=0; i < entrys.size(); i++){
                    menuList.getItems().add(entrys.get(i));
                }
            }
            catch(IllegalArgumentException e){
                //To is after From
                menuList.getItems().clear();
                menuList.setVisible(false);
            }
        }
    }

    @FXML
    private void confirmClick(ActionEvent event) {
        TreatmentEntry selectedEntry = menuList.getSelectionModel().getSelectedItem();
        if(menuList.isVisible() && selectedEntry!=null){
            ArrayList<Revision> revisions = selectedEntry.getRevisions();
            Revision selectedRevision = revisions.get(revisions.size()-1);
            ePAController.getePA().setActiveRevision(selectedRevision);
            ePAController.getePA().setActiveEntry(selectedEntry);
            try {
                ViewTreatmentEntryUeberweisungController viewTreatmentEntryUeberweisungController = new ViewTreatmentEntryUeberweisungController(primaryStage, ePAController);
                Scene scene = new Scene(viewTreatmentEntryUeberweisungController);
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
            PatientMainMenuController patientMainMenuController = new PatientMainMenuController(primaryStage, ePAController);
            Scene scene = new Scene(patientMainMenuController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
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

