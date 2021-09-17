package view.main;

/**
 * Sample Skeleton for 'LoginPage.fxml' Controller Class
 */

import java.io.IOException;
import controller.EPAController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.doctor.DoctorController;
import view.doctor.RegisterDoctorController;
import view.patient.PatientRecordController;
import view.patient.RegisterPatientController;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.File;
import java.awt.Desktop;

public class LoginPageController extends VBox {

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="helpButton"
    private Button helpButton; // Value injected by FXMLLoader

    @FXML
    private Text doctorLabel;

    @FXML
    private Text patientLabel;

    @FXML // fx:id="doctorPng"
    private ImageView doctorPng; // Value injected by FXMLLoader

    @FXML // fx:id="patientPng"
    private ImageView patientPng; // Value injected by FXMLLoader

    @FXML
    private Label registeredLabel;

    @FXML
    private Label notRegisteredLabel;

    private EPAController ePAController;

    private boolean doctorActive;
    private boolean patientActive;

    private Stage primaryStage;

    public LoginPageController(Stage primaryStage) {
        ePAController = new EPAController();
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main/LoginPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setButtonsInvisible();
    }

    public LoginPageController(Stage primaryStage, EPAController ePAController) {
        this.ePAController = ePAController;
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main/LoginPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setButtonsInvisible();
    }

    public void setButtonsInvisible(){
        doctorActive = false;
        patientActive = false;
        registerButton.setVisible(false);
        loginButton.setVisible(false);
        registeredLabel.setVisible(false);
        notRegisteredLabel.setVisible(false);
        // helpButton.setVisible(false);
    }

    @FXML
    private void doctorPngClick(MouseEvent event) {
        doctorActive = true;
        patientActive = false;
        this.refreshPngLabels();
    }

    @FXML
    private void patientPngClick(MouseEvent event) {
        doctorActive = false;
        patientActive = true;
        this.refreshPngLabels();
    }

    private void refreshPngLabels(){
        registerButton.setVisible(true);
        loginButton.setVisible(true);
        registeredLabel.setVisible(true);
        notRegisteredLabel.setVisible(true);
        if(doctorActive){
            doctorLabel.setUnderline(true);
            patientLabel.setUnderline(false);
        }
        if(patientActive){
            doctorLabel.setUnderline(false);
            patientLabel.setUnderline(true);
        }
    }

    @FXML
    private void registerClick(ActionEvent event) {
        if(doctorActive) {
            try {
                RegisterDoctorController registerDoctorController = new RegisterDoctorController(primaryStage, ePAController);
                Scene scene = new Scene(registerDoctorController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(patientActive){
            try {
                RegisterPatientController registerPatientController = new RegisterPatientController(primaryStage, ePAController);
                Scene scene = new Scene(registerPatientController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void helpClick(ActionEvent event) {
        File pdfFile = new File("additionalAssets/EPA_Produktbeschreibung.pdf");

        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(pdfFile);
            }
        }
        catch (IOException ex) {
            //
        }


    }



    @FXML
    private void loginClick(ActionEvent event) {
        if(doctorActive) {
            try {
                DoctorController doctorController = new DoctorController(primaryStage, ePAController);
                Scene scene = new Scene(doctorController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(patientActive){
            try {
                PatientRecordController patientRecordController = new PatientRecordController(primaryStage, ePAController);
                Scene scene = new Scene(patientRecordController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'LoginPage.fxml'.";
        // assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginPage.fxml'.";
    }
}

