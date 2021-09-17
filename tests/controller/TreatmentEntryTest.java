package controller;

import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to test the methods of the TreatmentEntry
 */
public class TreatmentEntryTest {
    private EPAController ePAController;
    private EPA ePA;
    private TreatmentEntry treatmentEntry;
    private Revision revision;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception {
        this.ePAController = new EPAController();
        this.ePA = new EPA();
        this.ePAController.setePA(ePA);
        this.treatmentEntry = new TreatmentEntry();
        Date date = new Date();
        date.setTime(0);
        Date time = new Date();
        time.setTime(0);
        this.revision = new Revision("finding","diagnosis", "therapy","medicationPlans",
                                        date, "notes", "symptoms");
    }
}
