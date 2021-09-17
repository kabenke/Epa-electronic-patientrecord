package model;

import model.Revision;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the methods of Revision
 */
public class RevisionTest {

    private Revision revision;
    private Date date, date1;
    private String formattedDate;
    private String formattedTime;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception{
        this.date = new Date();
        this.date1 = new Date();
        date1.setTime(0);
        this.revision = new Revision("finding","A01","therapy","medication",date,"notes","symptomes");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        this.formattedDate = sdfDate.format(date);
        this.formattedTime = sdfTime.format(date);
    }

    /**
     * Method to test the ToString method of the Revision class
     */
    @Test
    public void testToString(){
        Assert.assertEquals(formattedDate + " " + formattedTime + " " +revision.getDiagnosis(),revision.toString());
    }

    /**
     * Method to test the setFinding method of the Revision class
     */
    @Test
    public void testSetFinding(){
        revision.setFinding("newFinding");
        Assert.assertEquals("newFinding",revision.getFinding());
    }

    /**
     * Method to test the setDiagnosis method of the Revision class
     */
    @Test
    public void testSetDiagnosis(){
        revision.setDiagnosis("A03");
        Assert.assertEquals("A03",revision.getDiagnosis());
    }

    /**
     * Method to test the setTherapy method of the Revision class
     */
    @Test
    public void testSetTherapy(){
        revision.setTherapy("newTherapy");
        Assert.assertEquals("newTherapy",revision.getTherapy());
    }

    /**
     * Method to test the setMedicationPlans method of the Revision class
     */
    @Test
    public void testSetMedicationPlans(){
        revision.setMedicationPlans("newMedication");
        Assert.assertEquals("newMedication",revision.getMedicationPlans());
    }

    /**
     * Method to test the setDate method of the Revision class
     */
    @Test
    public void testSetDate(){
        revision.setDate(date1);
        Assert.assertEquals(date1,revision.getDate());
    }

    /**
     * Method to test the setDate method of the Revision class
     */
    @Test
    public void testSetNotes(){
        revision.setNotes("newNotes");
        Assert.assertEquals("newNotes",revision.getNotes());
    }

    /**
     * Method to test the setDate method of the Revision class
     */
    @Test
    public void testSetSymptomes(){
        revision.setSymptomes("newSymptomes");
        Assert.assertEquals("newSymptomes",revision.getSymptomes());
    }
}
