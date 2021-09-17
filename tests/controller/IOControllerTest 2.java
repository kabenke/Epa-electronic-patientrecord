package controller;

import model.EPA;
import model.Patient;
import model.PatientRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * This class is meant to Test the methods of the IOController
 */
public class IOControllerTest {

    private EPAController ePAController;
    private EPA ePA;
    private PatientRecordController patientRecordController;
    private Patient patient;
    private PatientRecord patientRecord;
    private IOController ioController;
    private Date dateOfBirth;


    /**
     * Creates a new test environment before each test method
     */
    @Before
    public void setUp(){
        this.ePAController = new EPAController();
        this.ePA = new EPA();
        this.ePAController.setePA(ePA);
        this.patientRecordController = new PatientRecordController(ePAController);
        this.dateOfBirth = new Date();
        dateOfBirth.setTime(0);
        this.patient = new Patient("11111111", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        this.patientRecord = new PatientRecord(patient);
        this.ioController = new IOController(ePAController);
    }


    /**
     * Test if the new Doctor Object is correct created
     * and  test if an existing doc is trying to register twice
     */
    @Test
    public void testIOControllerSave(){
        ioController.save();
        ioController.load();

    }

    /**
     * Method to test the setePAController method of the IOController
     */
    @Test
    public void testSetePAController(){
        ioController.setePAController(ePAController);
        Assert.assertEquals(ePAController, ioController.getePAController());
    }

}
