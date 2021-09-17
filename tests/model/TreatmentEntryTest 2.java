package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the methods of a TreatmentEntry
 */
public class TreatmentEntryTest {

    private TreatmentEntry treatmentEntry;
    private Revision currentRevision;
    private Date date;
    private Doctor doctor;
    private ArrayList revisions;
    private String formattedDate;
    private String formattedTime;
    private ArrayList<Revision> revision;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception{
        this.date = new Date();
        this.currentRevision = new Revision("finding","diagnosis","therapy","medication",date,"notes","symptomes");
        this.treatmentEntry = new TreatmentEntry();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        this.formattedDate = sdfDate.format(date);
        this.formattedTime = sdfTime.format(date);
        this.treatmentEntry.addRevision(currentRevision);
        this.doctor = new Doctor("Doktor Oetker","Pudding","Luetterstraße 14");
        this.revisions = new ArrayList();
        revisions.add(currentRevision);
    }

    /**
     * Method to test the toString method of class TreatmentEntry
      */
    @Test
    public void testToString(){
       ArrayList<Revision> revisions = treatmentEntry.getRevisions();
       assertEquals(formattedDate + " " + formattedTime + " " + revisions.get(revisions.size() - 1).getDiagnosis(), treatmentEntry.toString());
    }

    /**
     * Method to test the setDoctor method of the class TreatmentEntry
     */
    @Test
    public void testSetDoctor(){
       treatmentEntry.setDoctor(doctor);
       assertEquals(doctor, treatmentEntry.getDoctor());
    }

    /**
     * Method to test the setRevision method of the class TreatmentEntry
     */
    @Test
    public void testSetRevision(){
        treatmentEntry.setRevisions(revisions);
        assertEquals(currentRevision, revisions.get(revisions.size()-1));
        assertEquals(1, revisions.size());
    }

    /**
     * Method to test the TreatmentEntry constructor
     */
    @Test
    public void testTreatmentEntry(){
        TreatmentEntry treatmentEntry = new TreatmentEntry(doctor);
        Assert.assertEquals("Doktor Oetker",doctor.getName());
        Assert.assertEquals("Pudding",doctor.getSubject());
        Assert.assertEquals("Luetterstraße 14",doctor.getAddress());
    }
}
