package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the methods of a Doctor
 */
public class DoctorTest {

    private Doctor doctor;

    /**
     * Creates a new test environment before each test method
     * @throws Exception Possible Exceptions with setUp
     */
    @Before
    public void setUp() throws Exception{
        this.doctor = new Doctor("Doktor Oetker","Pudding","Luetterstraße14");
    }

    /**
     * Method to test the setName method of the Doctor class
     */
    @Test
    public void testSetName(){
        doctor.setName("Doktor Zoidberg");
        Assert.assertEquals("Doktor Zoidberg",doctor.getName());
    }

    /**
     * Method to test the setSubject method of the Doctor class
     */
    @Test
    public void testSetSubject(){
        doctor.setSubject("Psychology");
        Assert.assertEquals("Psychology",doctor.getSubject());
    }

    /**
     * Method to test the setAddress method of the Doctor class
     */
    @Test
    public void testSetAddress(){
        doctor.setAddress("Sampleallee 13");
        Assert.assertEquals("Sampleallee 13",doctor.getAddress());
    }

    /**
     * Method to test the setIsActive method of the Doctor class
     */
    @Test
    public void testIsActive(){
        doctor.setIsActive(true);
        Assert.assertEquals(true,doctor.getIsActive());
    }

    /**
     * Method to test the toString method of Doctor class
     */
    @Test
    public void testToString(){
        doctor.toString();
        assertEquals("["+doctor.getSubject()+"] "+doctor.getName()+" ("+doctor.getAddress()+")", doctor.toString());
    }
}
