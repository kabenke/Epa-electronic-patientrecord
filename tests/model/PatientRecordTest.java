package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the methods of PatientRecord
 */
public class PatientRecordTest {

    private TreatmentEntry treatmentEntry;
    private Date date;
    private ArrayList treatmentEntries;
    private PatientRecord patientRecord;
    private Patient patient;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception{
        this.date = new Date();
        this.patient = new Patient("1337", "Max Mustermann", "Sampleallee 13", "male", date, "TK");
        this.patientRecord = new PatientRecord(patient);
        this.treatmentEntry = new TreatmentEntry();
        this.treatmentEntries = new ArrayList();
        treatmentEntries.add(treatmentEntry);
    }

    /**
     * Method to test the setRevision method of the class TreatmentEntry
     */
    @Test
    public void testSetRevision(){
        patientRecord.setTreatmentEntrys(treatmentEntries);
        assertEquals(treatmentEntry, treatmentEntries.get(treatmentEntries.size()-1));
        assertEquals(1, treatmentEntries.size());
    }

    /**
     * Method to test the setRevision method of the class TreatmentEntry
     */
    @Test
    public void testSetPatient(){
        patientRecord.setPatient(patient);
        assertEquals(patient, patientRecord.getPatient());
    }

    /**
     * Method to test the setIsActive method of the class TreatmentEntry
     */
    @Test
    public void testSetIsActive(){
        patientRecord.setIsActive(true);
        assertEquals(true, patientRecord.getIsActive());
    }
}
