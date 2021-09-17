package view.patient;

/**
 * Sample Skeleton for 'RegisterPatient.fxml' Controller Class
 */

import controller.EPAController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import view.main.LoginPageController;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class RegisterPatientController extends VBox {

    @FXML // fx:id="addressField"
    private TextField addressField; // Value injected by FXMLLoader

    @FXML
    private Label dataErrorLabel;

    @FXML
    private Label doubleErrorLabel;

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="insuranceField"
    private TextField insuranceField; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="genderFemaleRadio"
    private RadioButton genderFemaleRadio; // Value injected by FXMLLoader

    @FXML // fx:id="genderMaleRadio"
    private RadioButton genderMaleRadio; // Value injected by FXMLLoader

    @FXML // fx:id="genderOtherRadio"
    private RadioButton genderOtherRadio; // Value injected by FXMLLoader

    private ToggleGroup genderGroup;

    @FXML // fx:id="dateOfBirthDatePicker"
    private DatePicker dateOfBirthDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="insuranceNBRField"
    private TextField insuranceNBRField; // Value injected by FXMLLoader

    private EPAController ePAController;

    private Stage primaryStage;

    public RegisterPatientController(Stage primaryStage, EPAController ePAController){
        this.ePAController = ePAController;
        ePAController.getIOController().save();
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patient/RegisterPatient.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        genderGroup = new ToggleGroup();
        genderMaleRadio.setToggleGroup(genderGroup);
        genderFemaleRadio.setToggleGroup(genderGroup);
        genderOtherRadio.setToggleGroup(genderGroup);
        dataErrorLabel.setVisible(false);
        doubleErrorLabel.setVisible(false);
    }

    @FXML
    private void registerClick(ActionEvent event) {
        try{
            //Gender auslesen
            String genderString = "";
            if(genderMaleRadio.isSelected()){ genderString="Male"; }
            else if(genderFemaleRadio.isSelected()){ genderString="Female"; }
            else if(genderOtherRadio.isSelected()){ genderString="Other"; }

            //LocalDate aus DatePicker zu Date konvertieren
            Date date = null;
            if(dateOfBirthDatePicker.getValue()!=null) {
                LocalDate localDate = dateOfBirthDatePicker.getValue();
                Calendar calendar = Calendar.getInstance();
                calendar.set(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth());
                date = calendar.getTime();
            }

            ePAController.getPatientRecordController().createPatientRecord(insuranceNBRField.getText(), nameField.getText(), addressField.getText(), genderString, date, insuranceField.getText());
            dataErrorLabel.setVisible(false);
            doubleErrorLabel.setVisible(false);
            genderFemaleRadio.setSelected(false);
            genderMaleRadio.setSelected(false);
            genderOtherRadio.setSelected(false);
            nameField.setText("");
            addressField.setText("");
            insuranceField.setText("");
            insuranceNBRField.setText("");
            dateOfBirthDatePicker.setValue(null);

            try {
                LoginPageController loginPageController = new LoginPageController(primaryStage, ePAController);
                Scene scene = new Scene(loginPageController);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch(IllegalArgumentException e){
            if(e.getMessage().equals("Check your information")){
                dataErrorLabel.setVisible(true);
                doubleErrorLabel.setVisible(false);
            }
            else if(e.getMessage().equals("Patient already has a PatientRecord")){
                dataErrorLabel.setVisible(false);
                doubleErrorLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void cancelClick(ActionEvent event) {
        try {
            LoginPageController loginPageController = new LoginPageController(primaryStage, ePAController);
            Scene scene = new Scene(loginPageController);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert addressField != null : "fx:id=\"addressField\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert insuranceField != null : "fx:id=\"insuranceField\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert genderFemaleRadio != null : "fx:id=\"genderFemaleRadio\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert genderMaleRadio != null : "fx:id=\"genderMaleRadio\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert genderOtherRadio != null : "fx:id=\"genderOtherRadio\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert dateOfBirthDatePicker != null : "fx:id=\"dateOfBirthDatePicker\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
        //assert insuranceNBRField != null : "fx:id=\"insuranceNBRField\" was not injected: check your FXML file 'RegisterPatient.fxml'.";
    }
}

