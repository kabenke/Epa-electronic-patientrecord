package controller;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Date;

/**
 * This class is used to test the methods of the TransferController
 */
public class TransferControllerTest {
    private EPAController ePAController;
    private TransferController transferController ;
    private Date datum;
    private Date time;
    private EPA ePA;
    private Doctor doctor1,doctor2 ;
    private Transfer validTransfer,invalidTransfer, transfer ;
    private Revision revision;


    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception {
        this.datum = new Date();
        datum.setTime(0);
        this.time = new Date();
        time.setTime(0);
        this.revision = new Revision("finding","AnyDiagnose","AnyTherapy","AnyPlan",datum,"notes", "symptoms");
        this.ePAController = new EPAController();
        this.transfer = new Transfer();
        this.ePA = new EPA();
        this.ePAController.setePA(ePA);
        this.transferController = new TransferController(ePAController);
        this.doctor1=new Doctor("Max Mustermann","Sampleallee 14","orthopedics");
        this.doctor2=new Doctor("Max Mustermann","Sampleallee 15","orthopedics");
        this.validTransfer=new Transfer();
        validTransfer.setDoctor(doctor1);
        this.invalidTransfer=new Transfer();
        invalidTransfer.setDoctor(doctor2);
    }

    /**
     * Valid Transfer, everything should work
     * param doctor - valid
     * param transfer - valid
     */
    @Test
    public void testCheckValidTransfer(){
        Assert.assertTrue(transferController.checkTransfer(doctor1,validTransfer));
    }

    /**
     * Invalid Transfer
     * param doctor - valid
     * param transfer - invalid
     */
    @Test
    public void testCheckInvalidTransfer(){
        Assert.assertFalse(transferController.checkTransfer(doctor1,invalidTransfer));
    }

    /**
     * Invalid Doctor Input
     * param doctor - null
     * param transfer - valid
     */
    @Test
    public void testCheckTransferInvalidDoctor(){
        Assert.assertFalse(transferController.checkTransfer(null,invalidTransfer));
    }

    /**
     * Invalid Transfer Input
     * param doctor - valid
     * param transfer - null
     */
    @Test
    public void testCheckTransferInvalidTransfer(){
        Assert.assertFalse(transferController.checkTransfer(doctor1,null));
    }

    /**
     *
     * param revision - a test revision reference to add Transfer
     * param transfer - a transfer Object reference
     */
    @Test
    public void testAddTransfer(){
        transferController.addTransfer(revision, transfer);
        Assert.assertEquals(transfer, revision.getTransfer());
    }

    /**
     * Method to test the setePAController method of the TransferController
     */
    @Test
    public void testSetePAController(){
        transferController.setePAController(ePAController);
        Assert.assertEquals(ePAController, transferController.getePAController());
    }
}