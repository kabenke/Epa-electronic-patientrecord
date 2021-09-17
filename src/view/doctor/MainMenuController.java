package view.doctor;

/**
 * Sample Skeleton for 'MainMenu.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import model.Revision;
import model.TreatmentEntry;

public class MainMenuController extends VBox {

    @FXML // fx:id="newEntryButton"
    private Button newEntryButton; // Value injected by FXMLLoader

    @FXML // fx:id="summaryButton"
    private Button summaryButton; // Value injected by FXMLLoader

    @FXML // fx:id="menuList"
    private ListView<TreatmentEntry> menuList; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML
    private Button transferButton;

    @FXML // fx:id="confirmButton"
    private Button confirmButton; // Value injected by FXMLLoader

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    private EPAController ePAController;

    private Stage primaryStage;

    public MainMenuController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/MainMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());

        if(!ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
            transferButton.setVisible(false);
        }

        ArrayList<TreatmentEntry> entrys = ePAController.getPatientRecordController().loadPatientRecord(ePAController.getePA().getActivePatient().getInsuranceNBR()).getTreatmentEntrys();
        if(ePAController.getePA().getActiveDoctor().getSubject().equals("Family doctor")){
            for(int i=0; i < entrys.size(); i++){
                menuList.getItems().add(entrys.get(i));
            }
        }
        else{
            for(int i=0; i < entrys.size(); i++){
                if(entrys.get(i).getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                        && entrys.get(i).getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                {
                    menuList.getItems().add(entrys.get(i));
                }
                else{
                    ArrayList<Revision> revisions = entrys.get(i).getRevisions();
                    Revision currentRevision = revisions.get(revisions.size()-1);
                    if(currentRevision.getTransfer().getDoctor() != null
                            && currentRevision.getTransfer().getDoctor().getName().equals(ePAController.getePA().getActiveDoctor().getName())
                            && currentRevision.getTransfer().getDoctor().getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress()))
                    {
                        menuList.getItems().add(entrys.get(i));
                    }
                }
            }
        }
    }

    @FXML
    private void transferClick(ActionEvent event) {
        TreatmentEntry selectedEntry = menuList.getSelectionModel().getSelectedItem();
        if(selectedEntry == null){
            //fehlermeldung
        }
        else{
            ArrayList<Revision> revisions = selectedEntry.getRevisions();
            Revision selectedRevision = revisions.get(revisions.size()-1);
            ePAController.getePA().setActiveRevision(selectedRevision);
            ePAController.getePA().setActiveEntry(selectedEntry);

            try {
                TransferController transferController = new TransferController(primaryStage, ePAController);
                Scene scene = new Scene(transferController);
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
            DoctorController doctorController = new DoctorController(primaryStage, ePAController);
            Scene scene = new Scene(doctorController);
            primaryStage.setScene(scene);
            primaryStage.show();
            ePAController.getePA().setActivePatient(null);
            ePAController.getePA().setActiveDoctor(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void summaryClick(ActionEvent event) {
        try {
            SummaryOfTreatmentProceduresController summaryOfTreatmentProceduresController = new SummaryOfTreatmentProceduresController(primaryStage, ePAController);
            Scene scene = new Scene(summaryOfTreatmentProceduresController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void newEntryClick(ActionEvent event) {
        try {
            NewTreatmentEntryController newTreatmentEntryController = new NewTreatmentEntryController(primaryStage, ePAController);
            Scene scene = new Scene(newTreatmentEntryController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void confirmClick(ActionEvent event) {
        TreatmentEntry selectedEntry = menuList.getSelectionModel().getSelectedItem();
        if(selectedEntry == null){
            //fehlermeldung
        }
        else{
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert newEntryButton != null : "fx:id=\"newEntryButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        //assert summaryButton != null : "fx:id=\"summaryButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        //assert menuList != null : "fx:id=\"menuList\" was not injected: check your FXML file 'MainMenu.fxml'.";
        //assert menuScrollBar != null : "fx:id=\"menuScrollBar\" was not injected: check your FXML file 'MainMenu.fxml'.";
        //assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        //assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }
}
