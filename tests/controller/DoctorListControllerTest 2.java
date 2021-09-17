package controller;

        import model.Doctor;
        import model.EPA;
        import model.Revision;
        import model.TreatmentEntry;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.ArrayList;
        import java.util.Date;

        import static org.junit.Assert.*;


/**
 * This class is used to test the methods of the DoctorListController
 */
public class DoctorListControllerTest {

    private EPAController ePAController, ePAController1;
    private EPA ePA, ePA1;
    private DoctorListController doctorListController, doctorListController1;
    private Doctor doc, doc1, doc2;
    private TreatmentEntry treatmentEntry;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception {
        this.ePAController = new EPAController();
        this.ePAController1 = new EPAController();
        this.ePA = new EPA();
        this.ePA1 = new EPA();
        this.treatmentEntry = new TreatmentEntry();
        this.treatmentEntry.addRevision(new Revision("itchy eye", "H01", "therapy", "eye drops", new Date(), "", "symptomes"));
        this.ePAController.setePA(ePA);
        this.ePAController1.setePA(ePA1);
        this.doctorListController = new DoctorListController(ePAController);
        this.doctorListController1 = new DoctorListController(ePAController1);
        this.doc1 = new Doctor("Doktor Oetker","Pudding","Lutterstraße 14");
        this.doc2 = new Doctor("Doktor Zoidberg", "Physiology", "Neu NewYork 3");
        ePA1.addDoctor(doc1);
        ePA1.addDoctor(doc2);

    }
    private static final String name = "Name";
    private static final String address = "Address";
    private static final String subject = "Subject";

    /**
     * Test if the new Doctor Object is correct created
     * and  test if an existing doc is trying to register twice
     */
    @Test
    public void testCreateDoctor(){

        ArrayList<Doctor> doctorRegister = ePA.getDoctorRegister();
        assertEquals(0, doctorRegister.size());
        doctorListController.createDoctor(name, subject, address );
        assertEquals(1, doctorRegister.size());

        assertEquals(name, doctorRegister.get(0).getName());
        assertEquals(subject, doctorRegister.get(0).getSubject());
        assertEquals(address, doctorRegister.get(0).getAddress());
    }

    /**
     * Test register the same Doctor twice
     * @throws IllegalArgumentException exception if duplicated
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicatedDoctor(){
        ArrayList<Doctor> doctorRegister = ePA.getDoctorRegister();
        // register Doctor
        doctorListController.createDoctor(name, subject, address);
        //register the same Doctor
        doctorListController.createDoctor(name, subject, address);
    }


    /**
     * Doctor has invalid Data Input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidDoctor() throws IllegalArgumentException{
        doctorListController.createDoctor(null,subject,address);
    }

    /**
     * Test if the validDoctorInput method is implemented correctly
     */
    @Test
    public void testValidDoctorInput() {
        /* valid inputs*/
        String validName = "Max Mustermann";
        String validAddress = "Sampleallee 14";
        String validSubject = "orthopedics";

        /* invalid inputs*/
        String invalidName = "";
        String invalidAddress = "";
        String invalidSubject = "";

        /* In the following comments 1 represents valid and 0 represents invalid */

        /* Inputs: 1 1 1 */
        assertTrue("Valid inputs", doctorListController.validDoctorInput(validName, validAddress, validSubject));
        /* Inputs: 0 1 1 */
        assertFalse("First input in  valid", doctorListController.validDoctorInput(invalidName, validAddress, validSubject));
        /* Inputs: 1 0 1 */
        assertFalse("Second input invalid", doctorListController.validDoctorInput(validName, invalidAddress, validSubject));
        /* Inputs: 1 1 0 */
        assertFalse("Third input invalid", doctorListController.validDoctorInput(validName, validAddress, invalidSubject));
        /* Inputs: 0 0 1 */
        assertFalse("First and second invalid", doctorListController.validDoctorInput(invalidName, invalidAddress, validSubject));
        /* Inputs: 0 1 0 */
        assertFalse("First and third invalid", doctorListController.validDoctorInput(invalidName, validAddress, invalidSubject));
        /* Inputs: 1 0 0 */
        assertFalse("Second and third invalid", doctorListController.validDoctorInput(validName, invalidAddress, invalidSubject));
        /* Inputs: 0 0 0 */
        assertFalse("Invalid inputs", doctorListController.validDoctorInput(invalidName, invalidAddress, invalidSubject));
    }

    /**
     * Doctor is in the DoctorRegister and everything should work
     */
    @Test
    public void testLoadDoctorValid() {
        String validName = "Doktor Oetker";
        String validAddress = "Lutterstraße 14";

        ArrayList<Doctor> doctorRegister = ePA1.getDoctorRegister();
        assertEquals(2, doctorRegister.size());

        doc = doctorListController1.loadDoctor(validName, validAddress);
        doctorListController1.loadDoctor(validName, validAddress);
        assertEquals(validName, doc.getName());
    }

    /**
     * Doctor is not in the DoctorRegister and we should get null from LoadDoctor
     */
    @Test
    public void testLoadDoctorInvalid() {
        String validName = "Doktor Hans";
        String validAddress = "Sampleallee 2";
        doc = doctorListController1.loadDoctor(validName, validAddress);
        doctorListController.loadDoctor(validName, validAddress);
        assertEquals(null, doc);
    }

    /**
     * inputs to the method are null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMakeTransferNull() {
        doctorListController.makeTransfer(null, null, null);
    }

    /**
     * inputs doctor credentials that do not represent a doctor in the system
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMakeTransferNoDoctorFound() {
        doctorListController.makeTransfer("NoName", "NoAddress", new TreatmentEntry());
    }

    /**
     * makes a transfer to a given doctor
     */
    @Test
    public void testMakeTransferValid() {
        assertNotEquals(doc1, treatmentEntry.getRevisions().get(0).getTransfer().getDoctor());
        doctorListController1.makeTransfer("Doktor Oetker", "Lutterstraße 14", treatmentEntry);
        assertEquals(doc1, treatmentEntry.getRevisions().get(0).getTransfer().getDoctor());
    }

    /**
     * Method to test the setePAController method of the DoctorListController
     */
    @Test
    public void testSetePAController(){
        doctorListController.setePAController(ePAController);
        Assert.assertEquals(ePAController, doctorListController.getePAController());
    }
}
