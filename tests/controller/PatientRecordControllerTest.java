package controller;

import abstractuserinterfaces.RevisionsAUI;
import abstractuserinterfaces.TreatmentEntryAUI;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * This class is used to test the methods of the PatientRecordController
 */
public class PatientRecordControllerTest {

    private EPAController ePAController;
    private EPA ePA;
    private PatientRecordController patientRecordController;
    private Patient patient,patient2, patient3, patient4, patientNoRecord, patientNoRevision, patientNoTreatmentEntries;
    private PatientRecord patientRecord, invalidPatientRecord, patientRecord2, PatientRecordNoEntries, patientRecord3, patientRecord4;
    private Transfer transfer;
    private Date begin, end, out, in, dateOfBirth;
    private Revision revision, invalidRevision, revision2, revision3;
    private TreatmentEntry treatmentEntry, invalidTreatmentEntry, treatmentEntry2, treatmentEntry3, treatmentEntry4;
    private RevisionsAUI revisionsAUI;
    private TreatmentEntryAUI treatmentEntryAUI;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception {
        this.revisionsAUI = new RevisionsAUI() {
            @Override
            public void refreshRevisions() {

            }
        };
        this.treatmentEntryAUI = new TreatmentEntryAUI() {
            @Override
            public void refreshTreatmentEntry() {

            }

            @Override
            public void refreshTreatmentEntrys() {

            }
        };
        this.ePAController = new EPAController();
        this.ePA = new EPA();
        this.ePAController.setePA(ePA);
        this.patientRecordController = new PatientRecordController(ePAController);
        this.dateOfBirth = new Date();
        dateOfBirth.setTime(0);
        this.patient = new Patient("1337", "Max Mustermann", "Sampleallee 13", "male", dateOfBirth, "TK");
        this.patient2 = new Patient("187", "Erika Mustermann", "Sampleallee 13", "female", dateOfBirth, "TK");
        this.patient3 = new Patient("123","name1","address1","male",dateOfBirth,"TK");
        this.patient4 = new Patient("234","name2","address2","female",dateOfBirth,"TK");
        this.patientNoRecord = new Patient("133", "Max Mustermann", "Sampleallee 13", "male", dateOfBirth, "TK");
        this.patientNoRevision = new Patient("420", "Otto Normalverbraucher","Sampleallee 14","male",dateOfBirth,"AOK");
        this.patientNoTreatmentEntries = new Patient("619","Lieschen Müller","Sampleallee 15","female",dateOfBirth,"AOK");
        this.patientRecord = new PatientRecord(patient);
        this.invalidPatientRecord = new PatientRecord(patient2);
        this.PatientRecordNoEntries = new PatientRecord(patientNoTreatmentEntries);
        this.patientRecord2 = new PatientRecord(patientNoRevision);
        this.patientRecord3 = new PatientRecord(patient3);
        this.patientRecord4 = new PatientRecord(patient4);
        ePA.addPatientRecord(patientRecord);
        ePA.addPatientRecord(invalidPatientRecord);
        ePA.addPatientRecord(PatientRecordNoEntries);
        ePA.addPatientRecord(patientRecord2);
        ePA.addPatientRecord(patientRecord3);
        ePA.addPatientRecord(patientRecord4);
        this.treatmentEntry = new TreatmentEntry();
        this.invalidTreatmentEntry = new TreatmentEntry();
        this.treatmentEntry2 = new TreatmentEntry();
        this.treatmentEntry3 = new TreatmentEntry();
        this.treatmentEntry4 = new TreatmentEntry();
        patientRecord.addTreatmentEntry(treatmentEntry);
        patientRecord.addTreatmentEntry(invalidTreatmentEntry);
        patientRecord2.addTreatmentEntry(treatmentEntry);
        patientRecord3.addTreatmentEntry(treatmentEntry3);
        patientRecord4.addTreatmentEntry(treatmentEntry4);
        this.begin = new Date();
        this.end = new Date();
        this.out = new Date();
        this.in = new Date();
        begin.setTime(1608000000000L); /* Date is 01.01.2021 */
        end.setTime(1610678000000L); /* Date is 01.02.2021 */
        in.setTime(1608001000000L);
        out.setTime(0); /* Date is 01.01.1970 */
        this.revision = new Revision("itchy eye", "H01", "therapy", "eye drops", begin, "", "symptomes");
        this.invalidRevision = new Revision("stomach discomfort", "A04", "therapy", "antibiotics", out, "", "symptomes");
        this.revision2 = new Revision("finding", "H01", "therapy", "medication", end, "", "symptomes");
        this.revision3 = new Revision("finding2", "H01", "therapy2", "medication2", in, "", "symptomes");
        invalidTreatmentEntry.addRevision(invalidRevision);
        treatmentEntry.addRevision(revision);
        treatmentEntry3.addRevision(revision2);
        treatmentEntry4.addRevision(revision3);
        this.transfer = new Transfer();

    }

    /**
     * Invalid icd Input
     * param icd - null
     * param insuranceNBR - valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testICDNull() throws Exception{
        patientRecordController.summaryByICD(null, patient.getInsuranceNBR());
    }


    /**
     * no patient with given insuranceNBR
     * param icd - valid
     * param insuranceNBR - valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPatientRecordNull() throws Exception{
        patientRecordController.summaryByICD("H01","1234");
    }

    /**
     * Patient has a TreatmentEntry with asked ICD
     * param icd - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByICDValidICD() {
        patientRecordController.summaryByICD("H01","1337");
        ArrayList<TreatmentEntry> revisionsWithICD = patientRecordController.summaryByICD("H01","1337");
        Assert.assertEquals(1, revisionsWithICD.size());
        ArrayList<Revision> revisionsOfEntry = revisionsWithICD.get(0).getRevisions();
        Assert.assertEquals(revision.getDiagnosis(),revisionsOfEntry.get(revisionsOfEntry.size() - 1).getDiagnosis());
    }

    /**
     * Patient has no TreatmentEntry with asked ICD
     * param icd - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByICDInvalidICD(){
        patientRecordController.summaryByICD("A03","1337");
        ArrayList<TreatmentEntry> revisionsWithICD2 = patientRecordController.summaryByICD("A03","1337");
        Assert.assertEquals(0, revisionsWithICD2.size());
    }

    /**
     *  test method loadPatientRecord if record is found or null
     */
    @Test
    public void testLoadPatientRecord(){
        Assert.assertEquals(null, patientRecordController.loadPatientRecord("aStringToFail"));
        Assert.assertEquals( "1337", patientRecordController.loadPatientRecord("1337").getPatient().getInsuranceNBR());
    }


    /**
     * Patient already has a PatientRecord
     * param patient - valid
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCreatePatientRecordWithRecord (){
        patientRecordController.createPatientRecord("1337","Max Mustermann","Sampleallee 13","male",dateOfBirth,"TK");

    }

    /**
     * Data of the Patient is invalid
     * param patient - invalid
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCreatePatientRecordInvalid (){
        patientRecordController.createPatientRecord("","","","",null,"");
    }

    /**
     * Patient has no PatientRecord yet and everything works
     * param patient - valid
     */
    @Test
    public  void testCreatePatientRecordValid(){
        patientRecordController.createPatientRecord("133", "Max Mustermann", "Sampleallee 13", "male", dateOfBirth, "TK");
        assertNotEquals(null,patientRecordController.loadPatientRecord(patientNoRecord.getInsuranceNBR()));

    }

    /**
     * Everything should work here
     * param begin - valid
     * param end - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByDateAllValidBegin(){
        ArrayList<TreatmentEntry> treatmentsByDate = patientRecordController.summaryByDate(begin, end, "1337");
        Assert.assertEquals(1, treatmentsByDate.size());
    }

    /**
     * Everything should work here
     * param begin - valid
     * param end - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByDateAllValidEnd(){
        ArrayList<TreatmentEntry> treatmentsByDate = patientRecordController.summaryByDate(begin, end, "123");
        Assert.assertEquals(1, treatmentsByDate.size());
    }

    /**
     * Everything should work here
     * param begin - valid
     * param end - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByDateAllValidIn(){
        ArrayList<TreatmentEntry> treatmentsByDate = patientRecordController.summaryByDate(begin, end, "234");
        Assert.assertEquals(1, treatmentsByDate.size());
    }

    /**
     * Everything should work here
     * param begin - valid
     * param end - valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByDateAllValid(){
        ArrayList<TreatmentEntry> treatmentsByDate = patientRecordController.summaryByDate(begin, begin, "1337");
        Assert.assertEquals(1, treatmentsByDate.size());
    }

    /**
     * End Date before Start Date
     * param begin - valid
     * param end - valid
     * param insuranceNBR - valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryByDateDatesSwitched(){
        patientRecordController.summaryByDate(end, begin, "1337");
    }

    /**
     * No Revision in Time Interval
     * param begin valid
     * param end valid
     * param insuranceNBR - valid
     */
    @Test
    public void testSummaryByDateNoRevision(){
        patientRecordController.summaryByDate(begin, end, "1337");
        ArrayList<TreatmentEntry> treatmentsByDate = patientRecordController.summaryByDate(begin, end, "187");
        Assert.assertEquals(0, treatmentsByDate.size());
    }

    /**
     * PatientRecord is null
     * param begin valid
     * param end valid
     * param insuranceNBR valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryByDatePatientRecordNull(){
        patientRecordController.summaryByDate(begin, end, "69");
    }

    /**
     * Begin Time invalid
     * param begin null
     * param end valid
     * param insuranceNBR - valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryByDateBeginNull(){
        patientRecordController.summaryByDate(null, end, "1337");
    }

    /**
     * End Time invalid
     * param begin null
     * param end valid
     * param insuranceNBR - valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryByDateEndNull(){
        patientRecordController.summaryByDate(begin, null, "1337");
    }


    /**
     * Tests if the CheckICD method is working properly by using valid an invalid ICD-Codes
     */
    @Test
    public void testCheckICD(){
        /* valid ICD-codes */
        String valid1 = "A03";
        String valid2 = "N22";
        String valid3 = "L83.1";
        String valid4 = "V00.187";

        /* invalid ICD-codes */
        String invalid1 = "P3";
        String invalid2 = "a03";
        String invalid3 = "A3V";
        String invalid4 = "B58.";
        String invalid5 = "F31.f";
        String invalid6 = "F31355f";

        /* test valid codes */
        assertTrue("Valid ICD", patientRecordController.checkICD(valid1));
        assertTrue("Valid ICD", patientRecordController.checkICD(valid2));
        assertTrue("Valid ICD", patientRecordController.checkICD(valid3));
        assertTrue("Valid ICD", patientRecordController.checkICD(valid4));

        /* test invalid codes */
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid1));
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid2));
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid3));
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid4));
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid5));
        assertFalse("Invalid ICD", patientRecordController.checkICD(invalid6));
        assertFalse("null ICD", patientRecordController.checkICD(null));
    }

    /**
     * Invalid Revision Input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRevisionInvalidInput(){
        patientRecordController.createRevision("","","","","",null,"","",treatmentEntry);
    }

    /**
     * Patient has no TreatmentEntries in Patient Record
     */
    @Test
    public void testCreateRevisionNoTreatmentEntries(){
        patientRecordController.createRevision("619","finding","A03","therapy","medication",begin,"notes","symptomes",treatmentEntry);
        Assert.assertEquals(1,PatientRecordNoEntries.getTreatmentEntrys().size());
    }

    /**
     * Patient has a TreatmentEntry but not the one we are looking for
     */
    @Test
    public void testCreateRevisionNotTheRightTreatmentEntry(){
        patientRecordController.createRevision("420","finding","A03","therapy","medication",begin,"notes","symptomes",treatmentEntry2);
        Assert.assertEquals(2,patientRecord2.getTreatmentEntrys().size());
        Assert.assertEquals(patientRecord2.getTreatmentEntrys().get(patientRecord2.getTreatmentEntrys().size()-1),treatmentEntry2);
    }

    /**
     * Valid Revision Input and Patient already has a Revision in his TreatmentEntry
     */
    @Test()
    public void testCreateRevisionAlreadyARevision(){
        patientRecordController.createRevision("1337","finding","A03","therapy","medication",begin,"notes","symptomes",treatmentEntry);
        Assert.assertEquals(2,treatmentEntry.getRevisions().size());
    }

    /**
     * Test if transferData is transferred when transfer != null. Expected: transferred
     */
    @Test()
    public void testRestrictDataTransferNotNull() {
        revision.setTransfer(transfer);
        boolean[] transferData = {true, true, true, true, true, true, true, true, true, true};
        patientRecordController.restrictData(patient.getInsuranceNBR(), transferData, treatmentEntry);
        Assert.assertNotNull("Valid inputs, all available", transfer.getTransferData() );
    }

    /**
     * Test if transferData is transferred when transfer = null. Expected: not transferred
     */
    @Test()
    public void testRestrictDataTransferNull(){
        revision.setTransfer(null);
        boolean[] transferData = {true, true, true, true, true, true, true, true, true, true};
        patientRecordController.restrictData(patient.getInsuranceNBR(), transferData, treatmentEntry);
        Assert.assertNull("Valid inputs, all available", revision.getTransfer());
    }

    /**
     * Method to test the setePAController method of the PatientRecordController
     */
    @Test
    public void testSetePAController(){
        patientRecordController.setePAController(ePAController);
        Assert.assertEquals(ePAController, patientRecordController.getePAController());
    }

    /**
     * Method to test the setRevisionsAUI method of the PatientRecordController
     */
    @Test
    public void testSetPersonalDataAUI(){
        patientRecordController.setRevisionsAUI(revisionsAUI);
        Assert.assertEquals(revisionsAUI, patientRecordController.getRevisionsAUI());
    }

    /**
     * Method to test the setTreatmentEntryAUI method of the PatientRecordController
     */
    @Test
    public void testSetTreatmentEntryAUI(){
        patientRecordController.setTreatmentEntryAUI(treatmentEntryAUI);
        Assert.assertEquals(treatmentEntryAUI, patientRecordController.getTreatmentEntryAUI());
    }
}

