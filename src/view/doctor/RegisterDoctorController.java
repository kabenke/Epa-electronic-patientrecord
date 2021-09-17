package view.doctor;

/**
 * Sample Skeleton for 'RegisterDoctor.fxml' Controller Class
 */

import java.io.IOException;
import java.util.ArrayList;

import controller.EPAController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Doctor;
import view.main.LoginPageController;
import javafx.scene.text.Text;

public class RegisterDoctorController extends VBox {

    @FXML // fx:id="addressField"
    private TextField addressField; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="expertiseField"
    private ChoiceBox<String> expertiseDrop;

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML
    private Label errorLabel;

    @FXML
    private Text errorDoubleText;

    private EPAController ePAController;

    private Stage primaryStage;

    public RegisterDoctorController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/doctor/RegisterDoctor.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorLabel.setVisible(false);
        errorDoubleText.setVisible(false);
        expertiseDrop.getItems().add("Family doctor"); //Hausarzt
        expertiseDrop.getItems().add("Dermatologist"); //Hautarzt
        expertiseDrop.getItems().add("Radiologist"); //Radiologe
        expertiseDrop.getItems().add("Neurologist"); //Neurologe
        expertiseDrop.getItems().add("Cardiologist"); //Kardiologe
        expertiseDrop.getItems().add("Urologist"); //Urologe
        expertiseDrop.getItems().add("Psychologist"); //Psychologe
    }

    @FXML
    private void registerClick(ActionEvent event) {
        try {
            String expertise = "";
            if(expertiseDrop.getSelectionModel().getSelectedItem()!=null) {
                expertise = expertiseDrop.getSelectionModel().getSelectedItem();
            }
            ePAController.getDoctorListController().createDoctor(nameField.getText(), expertise, addressField.getText());
            errorLabel.setVisible(false);
            errorDoubleText.setVisible(false);
            nameField.setText("");
            expertiseDrop.getItems().clear();
            addressField.setText("");

            try {
                LoginPageController loginPageController = new LoginPageController(primaryStage, ePAController);
                Scene scene = new Scene(loginPageController);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch(IllegalArgumentException e){
            if(e.getMessage().equals("Invalid data")){
                errorLabel.setVisible(true);
                errorDoubleText.setVisible(false);
            }
            else if(e.getMessage().equals("Doctor already in the system")){
                errorDoubleText.setVisible(true);
                errorLabel.setVisible(false);
            }

        }
    }

    @FXML
    private void cancelClick(ActionEvent event){
        try {
            LoginPageController loginPageController = new LoginPageController(primaryStage, ePAController);
            Scene scene = new Scene(loginPageController);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'RegisterDoctor.fxml'.";
        //assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'RegisterDoctor.fxml'.";
        //assert expertiseField != null : "fx:id=\"expertiseField\" was not injected: check your FXML file 'RegisterDoctor.fxml'.";
        //assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'RegisterDoctor.fxml'.";
        //assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RegisterDoctor.fxml'.";
    }
}

