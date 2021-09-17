package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * This class represents a Doctor in the system
 */
public class Doctor implements Serializable {

    /**
     * doctor's name
     */
    private String name;

    /**
     * doctor's professional subject of expertise
     */
    private String subject;

    /**
     * doctor's office address
     */
    private String address;

    /**
     * whether the doctor is currently active in the system
     */
    private boolean isActive;

    /**
     * Constructor to create a Doctor
     * @param name Name of the Doctor
     * @param subject Subject of the Doctor
     * @param address Address of the Doctor
     */
    public Doctor(String name, String subject, String address) {
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.isActive = false;
    }

    /**
     * get-Method name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set-Method name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get-Method subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * set-Method subject
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * get-Method address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set-Method address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get-Method isActive
     * @return isActive
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * set-Method isActive
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Tostring method that returns for the doctor
     * @return a String
     */
    @Override
    public String toString() {
        String string = "["+this.subject+"] "+this.name+" ("+this.address+")";
        return string;
    }

}
