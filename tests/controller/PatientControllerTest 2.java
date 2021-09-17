package controller;

import abstractuserinterfaces.PersonalDataAUI;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * This class is meant to Test the methods of the PatientController
 */
public class PatientControllerTest {

    private EPAController ePAController;
    private EPA ePA;
    private PatientRecordController patientRecordController;
    private Patient patient;
    private Patient patient2;
    private Patient patient3;
    private PatientRecord patientRecord;
    private PatientController patientController;
    private PersonalDataAUI personalDataAUI;

    private Patient patient_default;
    private Patient patient_current;
    private ArrayList<PatientRecord> patientRecords;

    /* valid input */
    private String vInsuranceNBR = "11111";
    private String vName = "newName";
    private String vAddress = "newAddress";
    private String vGender = "newmale";
    private String vInsurance = "newAOK";
    private Date vDateOfBirth = new Date();

    /* invalid input */
    private String ivInsuranceNBR = "";
    private String ivName = "";
    private String ivAddress = "";
    private String ivGender = "";
    private String ivInsurance = "";
    private Date ivDateOfBirth = null;


    /**
     * Creates a new test environment before each test method
     *
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception {
        this.personalDataAUI = new PersonalDataAUI() {
            @Override
            public void refreshPersonalData() {

            }
        };
        this.ePAController = new EPAController();
        this.ePA = new EPA();
        this.ePAController.setePA(ePA);
        this.patientRecordController = new PatientRecordController(ePAController);
        Date dateOfBirth = new Date();
        dateOfBirth.setTime(0);
        this.patient = new Patient("00000", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        this.patient2 = new Patient("01110", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        this.patient3 = null;
        this.patientRecord = new PatientRecord(patient);
        this.patientController = new PatientController(ePAController);

        this.patient_current= new Patient("Current InsurancdNbr", "Current Name", "Current Address", "Current Gender", dateOfBirth, "Current Insurance");
        this.patient_default = new Patient("Default InsurancdNbr", "Default Name", "Default Address", "Default Gender", dateOfBirth, "Default Insurance");

        this.patientRecordController.createPatientRecord("00000", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        PatientRecord patientRecord2 = this.patientRecordController.loadPatientRecord("00000");
        TreatmentEntry treatmentEntry2 = new TreatmentEntry();
        TreatmentEntry treatmentEntry3 = new TreatmentEntry();
        Date date = new Date();
        treatmentEntry2.addRevision(new Revision("finding","diagnosis", "therapy","medicationPlans",
                date, "notes", "symptoms"));
        treatmentEntry3.addRevision(new Revision("finding2","diagnosis", "therapy","medicationPlans",
                date, "notes", "symptoms"));

        patientRecord2.addTreatmentEntry(treatmentEntry2);
        patientRecord2.addTreatmentEntry(treatmentEntry3);

        this.patientRecords = new ArrayList<PatientRecord>();

        patientRecords.add(new PatientRecord(patient_default));

    }
    /**
     * Test if theEditPatientDetails function correctly if data is valid
     */
    @Test
    public void testEditPatientDetails() {

        /* 1 represents valid and 0 represents invalid */
        /* Input: 1 1 1 1 1 1 1 */
        patientController.editPatientDetails(vInsuranceNBR, vName, vAddress, vGender, vDateOfBirth, vInsurance, patientRecord);
        Assert.assertTrue("Valid Input", (vInsuranceNBR.equals(patient.getInsuranceNBR()) && vName.equals(patient.getName()) && vAddress.equals(patient.getAddress())
        && vGender.equals(patient.getGender()) && vInsurance.equals(patient.getInsurance()) && vDateOfBirth.equals(patient.getDateOfBirth())));

    }

    /**
     * Test if IllegalArgumentException is thrown when data is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEditPatientRecordIlExceptionFirst(){

        /* Inputs: 0 0 0 0 0 0 1 */
        patientController.editPatientDetails("", "", "", "", null, "", patientRecord);
    }

    /**
     * Test if IllegalArgumentException is thrown when patientRecord is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEditPatientRecordIlExceptionSecond(){
        /* valid DateOfBirth input */
        vDateOfBirth.setTime(0);

        /* Inputs: 1 1 1 1 1 1 null  */
        patientController.editPatientDetails(vInsuranceNBR, vName, vAddress, vGender, vDateOfBirth, vInsurance, null);
    }

    /**
     * Start of the Test checkUniqueInsuranceNBR
     */
    @Test
    public void TheInsuranceNumberIsIntheList()
    {
        Assert.assertTrue(patientController.checkUniqueInsuranceNBR(patient_current.getInsuranceNBR(),patientRecords));

    }

    /**
     * Test if the insurance number isn't in the list
     */
    @Test
    public void TheInsuranceNumberIsNotinTheList()
    {
        assertFalse(patientController.checkUniqueInsuranceNBR(patient_default.getInsuranceNBR(),patientRecords));
    }

    /**
     *Test if a nullpointerException is Throw
     */
    @Test
    public void SomeParameterIsSetupWithNUll()
    {
        assertFalse(patientController.checkUniqueInsuranceNBR(null,patientRecords));
    }

    /**
     * Test when patient is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateFileContentPatientNull() {
        this.ePAController.getPatientController().generateFileContent(null);
    }

    /**
     * Test when patient is not found
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGenerateFileContentPatientNotFound() {
        this.ePAController.getPatientController().generateFileContent(patient_current);
    }

    /**
     * Test when patient is valid
     */
    @Test
    public void testGenerateFileContentValidPatient() {
        String res = "";
        res = this.ePAController.getPatientController().generateFileContent(new Patient("00000", "aal", "aal", "aal", new Date(), "TK"));
        assertNotEquals("res should not be empty", "", res);
    }


    /**
     * Test print if patient is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void printPatientNull() throws IOException {
        ePAController.getPatientController().print(patient3);
    }

    /**
     * Test print if patientRecord is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void printPatientRecordNull() throws IOException {
        this.ePAController.getPatientController().print(patient2);
    }

    /**
     * Test print successful
     */
    @Test(expected = Test.None.class)
    public void printSuccessful() throws IOException {
        this.ePAController.getPatientController().print(patient);
    }

    /**
     * Method to test the setePAController method of the PatientController
     */
    @Test
    public void testSetePAController(){
        patientController.setePAController(ePAController);
        Assert.assertEquals(ePAController, patientController.getePAController());
    }

    /**
     * Method to test the setPersonalDataAUI method of the PatientController
     */
    @Test
    public void testSetPersonalDataAUI(){
        patientController.setPersonalDataAUI(personalDataAUI);
        Assert.assertEquals(personalDataAUI, patientController.getPersonalDataAUI());
    }
}
