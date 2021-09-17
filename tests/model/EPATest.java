package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the methods of the EPA
 */
public class EPATest {

    private Doctor doctor;
    private EPA epa;
    private Patient patient, patient2;
    private Date dateOfBirth;
    private PatientRecord patientRecord, patientRecord2;
    private TreatmentEntry treatmentEntry;
    private Revision revision;
    private ArrayList<Doctor> doctorRegister;
    private ArrayList<PatientRecord> patientRecords;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception{
        this.epa = new EPA();
        this.dateOfBirth = new Date();
        dateOfBirth.setTime(0);
        this.patientRecords = new ArrayList<PatientRecord>();
        this.doctorRegister = new ArrayList<Doctor>();
        this.doctor = new Doctor("Doktor Oetker","Pudding","Luetterstraße14");
        this.patient = new Patient("00000", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        this.patient2 = new Patient("01110", "Any Name", "Any Address", "male", dateOfBirth, "TK");
        this.patientRecord = new PatientRecord(patient);
        this.patientRecord2 = new PatientRecord(patient2);
        epa.addPatientRecord(patientRecord);
        this.treatmentEntry = new TreatmentEntry();
        patientRecord.addTreatmentEntry(treatmentEntry);
        this.revision = new Revision("itchy eye", "H01", "therapy", "eye drops", dateOfBirth, "", "symptomes");
        treatmentEntry.addRevision(revision);
        patientRecords.add(patientRecord);
        doctorRegister.add(doctor);

    }

    /**
     * Method to test the SetActiveDoctor method of the EPA class
     */
    @Test
    public void testSetActiveDoctor(){
        epa.setActiveDoctor(doctor);
        Assert.assertEquals(doctor,epa.getActiveDoctor());
    }

    /**
     * Method to test the SetActivePatient method of the EPA class
     */
    @Test
    public void testSetActivePatient(){
        epa.setActivePatient(patient);
        Assert.assertEquals(patient,epa.getActivePatient());
    }

    /**
     * Method to test the SetActiveEntry method of the EPA class
     */
    @Test
    public void testSetActiveEntry(){
        epa.setActiveEntry(treatmentEntry);
        Assert.assertEquals(treatmentEntry,epa.getActiveEntry());
    }

    /**
     * Method to test the SetActiveRevision method of the EPA class
     */
    @Test
    public void testSetActiveRevision(){
        epa.setActiveRevision(revision);
        Assert.assertEquals(revision,epa.getActiveRevision());
    }

    /**
     * Method to test the SetPatientRegister method of the EPA class
     */
    @Test
    public void testSetPatientRegister(){
        epa.setPatientRecords(patientRecords);
        Assert.assertEquals(patientRecords.size(),epa.getPatientRecords().size());
    }

    /**
     * Method to test the SetPatientRegister method of the EPA class
     */
    @Test
    public void testSetDoctorRegister(){
        epa.setDoctorRegister(doctorRegister);
        Assert.assertEquals(doctorRegister.size(),epa.getDoctorRegister().size());
    }
}
