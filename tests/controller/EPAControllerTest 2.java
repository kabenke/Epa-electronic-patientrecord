package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is meant to Test the methods of the EPAController
 */
public class EPAControllerTest {

    private EPAController epaController;
    private IOController ioController;
    private PatientRecordController patientRecordController;
    private PatientController patientController;
    private DoctorListController doctorListController;

    /**
     * Creates a new test environment before each test method
     */
    @Before
    public void setUp(){
        this.epaController = new EPAController();
        this.ioController = new IOController(epaController);
        this.patientController = new PatientController(epaController);
        this.patientRecordController = new PatientRecordController(epaController);
        this.doctorListController = new DoctorListController(epaController);
    }

    /**
     * Method to test the setiOController method of the EPAController
     */
    @Test
    public void testSetIOController(){
        epaController.setiOController(ioController);
        Assert.assertEquals(ioController, epaController.getiOController());
        Assert.assertEquals(ioController, epaController.getIOController());
    }

    /**
     * Method to test the setPatientController method of the EPAController
     */
    @Test
    public void testSetPatientController(){
        epaController.setPatientController(patientController);
        Assert.assertEquals(patientController, epaController.getPatientController());
    }

    /**
     * Method to test the setPatientRecordController method of the EPAController
     */
    @Test
    public void testSetPatientRecordController(){
        epaController.setPatientRecordController(patientRecordController);
        Assert.assertEquals(patientRecordController, epaController.getPatientRecordController());
    }

    /**
     * Method to test the setDoctorListController method of the EPAController
     */
    @Test
    public void testSetDoctorListController(){
        epaController.setDoctorListController(doctorListController);
        Assert.assertEquals(doctorListController, epaController.getDoctorListController());
    }
}
