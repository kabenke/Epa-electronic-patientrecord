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
import model.Doctor;
import model.Revision;
import model.TreatmentEntry;

public class TransferController extends VBox {

    @FXML // fx:id="menuList"
    private ListView<Doctor> menuList; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML
    private Button transferButton;

    @FXML
    private Text nameText;

    @FXML
    private Text insuranceNBRText;

    private EPAController ePAController;

    private Stage primaryStage;

    public TransferController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/Transfer.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nameText.setText("Name: "+ePAController.getePA().getActivePatient().getName());
        insuranceNBRText.setText("Insurance number: "+ePAController.getePA().getActivePatient().getInsuranceNBR());

        ArrayList<Doctor> doctors = ePAController.getePA().getDoctorRegister();
        for(int i=0; i < doctors.size(); i++){
            if(!(doctors.get(i).getName().equals(ePAController.getePA().getActiveDoctor().getName())
                    && doctors.get(i).getAddress().equals(ePAController.getePA().getActiveDoctor().getAddress())))
            {
                if(!doctors.get(i).getSubject().equals("Family doctor")){
                    menuList.getItems().add(doctors.get(i));
                }
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

    @FXML
    private void transferClick(ActionEvent event) {
        Doctor selectedDoctor = menuList.getSelectionModel().getSelectedItem();
        if(selectedDoctor == null){
            //fehlermeldung
        }
        else{
            try {
                ePAController.getDoctorListController().makeTransfer(selectedDoctor.getName(), selectedDoctor.getAddress(), ePAController.getePA().getActiveEntry());

                MainMenuController mainMenuController = new MainMenuController(primaryStage, ePAController);
                Scene scene = new Scene(mainMenuController);
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
