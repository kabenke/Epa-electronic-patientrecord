package view.doctor;

/**
 * Sample Skeleton for 'SummaryOfTreatmentProcedures.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Revision;
import model.TreatmentEntry;
import view.patient.ViewTreatmentEntryUeberweisungController;

public class SummaryOfTreatmentProceduresController extends VBox {

    @FXML // fx:id="icdField"
    private TextField icdField; // Value injected by FXMLLoader

    @FXML // fx:id="loadButton"
    private Button loadButton; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="entryList"
    private ListView<TreatmentEntry> entryList; // Value injected by FXMLLoader

    private EPAController ePAController;

    private Stage primaryStage;

    public SummaryOfTreatmentProceduresController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/SummaryOfTreatmentProcedures.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<TreatmentEntry> entrys = ePAController.getPatientRecordController().loadPatientRecord(ePAController.getePA().getActivePatient().getInsuranceNBR()).getTreatmentEntrys();
        if(ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
            for(int i=0; i < entrys.size(); i++){
                entryList.getItems().add(entrys.get(i));
            }
        }
        else{
            for(int i=0; i < entrys.size(); i++){
                if(entrys.get(i).getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                        && entrys.get(i).getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                {
                    entryList.getItems().add(entrys.get(i));
                }
                else{
                    ArrayList<Revision> revisions = entrys.get(i).getRevisions();
                    Revision currentRevision = revisions.get(revisions.size()-1);
                    if(currentRevision.getTransfer().getDoctor() != null
                            && currentRevision.getTransfer().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                            && currentRevision.getTransfer().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                    {
                        entryList.getItems().add(entrys.get(i));
                    }
                }
            }
        }
    }

    @FXML
    public void onICDChange(KeyEvent event){
        entryList.getItems().clear();
        if(icdField.getText().equals("")){
            ArrayList<TreatmentEntry> entrys = ePAController.getPatientRecordController().loadPatientRecord(ePAController.getePA().getActivePatient().getInsuranceNBR()).getTreatmentEntrys();
            if(ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
                for(int i=0; i < entrys.size(); i++){
                    entryList.getItems().add(entrys.get(i));
                }
            }
            else{
                for(int i=0; i < entrys.size(); i++){
                    if(entrys.get(i).getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                            && entrys.get(i).getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                    {
                        entryList.getItems().add(entrys.get(i));
                    }
                    else{
                        ArrayList<Revision> revisions = entrys.get(i).getRevisions();
                        Revision currentRevision = revisions.get(revisions.size()-1);
                        if(currentRevision.getTransfer().getDoctor() != null
                                && currentRevision.getTransfer().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                                && currentRevision.getTransfer().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                        {
                            entryList.getItems().add(entrys.get(i));
                        }
                    }
                }
            }
        }
        else{
            ArrayList<TreatmentEntry> entrys = ePAController.getPatientRecordController().summaryByICD(icdField.getText(), ePAController.getePA().getActivePatient().getInsuranceNBR());
            if(ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
                for(int i=0; i < entrys.size(); i++){
                    entryList.getItems().add(entrys.get(i));
                }
            }
            else{
                for(int i=0; i < entrys.size(); i++){
                    if(entrys.get(i).getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                            && entrys.get(i).getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                    {
                        entryList.getItems().add(entrys.get(i));
                    }
                    else{
                        ArrayList<Revision> revisions = entrys.get(i).getRevisions();
                        Revision currentRevision = revisions.get(revisions.size()-1);
                        if(currentRevision.getTransfer().getDoctor() != null
                                && currentRevision.getTransfer().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                                && currentRevision.getTransfer().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                        {
                            entryList.getItems().add(entrys.get(i));
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void loadClick(ActionEvent event) {
        TreatmentEntry selectedEntry = entryList.getSelectionModel().getSelectedItem();
        if(entryList.isVisible() && selectedEntry!=null){
            ArrayList<Revision> revisions = selectedEntry.getRevisions();
            Revision selectedRevision = revisions.get(revisions.size()-1);
            ePAController.getePA().setActiveRevision(selectedRevision);
            ePAController.getePA().setActiveEntry(selectedEntry);
            try {
                ViewTreatmentEntryController viewTreatmentEntryController = new ViewTreatmentEntryController(primaryStage, ePAController);
                Scene scene = new Scene(viewTreatmentEntryController);
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
        //assert icdField != null : "fx:id=\"icdField\" was not injected: check your FXML file 'SummaryOfTreatmentProcedures.fxml'.";
        //assert loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'SummaryOfTreatmentProcedures.fxml'.";
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'SummaryOfTreatmentProcedures.fxml'.";
        //assert entryScrollBar != null : "fx:id=\"entryScrollBar\" was not injected: check your FXML file 'SummaryOfTreatmentProcedures.fxml'.";
        //assert entryList != null : "fx:id=\"entryList\" was not injected: check your FXML file 'SummaryOfTreatmentProcedures.fxml'.";
    }
}

