package view.doctor;

/**
 * Sample Skeleton for 'MainMenu.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import model.Revision;
import model.TreatmentEntry;

public class ViewRevisionsController extends VBox {

    @FXML // fx:id="menuList"
    private ListView<Revision> menuList; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="confirmButton"
    private Button confirmButton; // Value injected by FXMLLoader

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    private EPAController ePAController;

    private Stage primaryStage;

    public ViewRevisionsController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/ViewRevisions.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());

        ArrayList<Revision> revisions = ePAController.getePA().getActiveEntry().getRevisions();
        for(int i=0; i < revisions.size(); i++){
            menuList.getItems().add(revisions.get(i));
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

    @FXML
    private void confirmClick(ActionEvent event) {
        Revision selectedRevision = menuList.getSelectionModel().getSelectedItem();
        if(selectedRevision == null){
            //fehlermeldung
        }
        else{
            ePAController.getePA().setActiveRevision(selectedRevision);

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
