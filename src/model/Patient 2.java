package model;

import java.util.Date;
import java.io.Serializable;

/**
 * This class represents a Patient in the system
 */
public class Patient implements Serializable{
    /**
 	 * Patient's insurance number
 	 */
    private String insuranceNBR;
    /**
 	 * Patient's name
 	 */
    private String name;
    /**
 	 * Patient's office address
 	 */
    private String address;
    /**
 	 * Patient's name
 	 */
    private String gender;
    /**
 	 * Patient's date of birth
 	 */
    private Date dateOfBirth;
    /**
 	 * patient's insurance name
 	 */
    private String insurance;

    /**
     * Konstruktor für einen Patienten
     * @param insuranceNBR Versichertennummer des Patienten
     * @param name Name des Patienten
     * @param address Adresse des Patienten
     * @param gender Geschlecht des Patienten
     * @param dateOfBirth Geburtsdatum des Patienten
     * @param insurance Versicherung des Patienten
     */
    public Patient(String insuranceNBR, String name, String address, String gender, Date dateOfBirth, String insurance) {
        this.insuranceNBR = insuranceNBR;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.insurance = insurance;
    }

    /**
     * insuranceNBR get-Methode
     * @return insuranceNBR
     */
    public String getInsuranceNBR() {
        return insuranceNBR;
    }

    /**
     * insuranceNBR set-Methode
     * @param insuranceNBR
     */
    public void setInsuranceNBR(String insuranceNBR) {
        this.insuranceNBR = insuranceNBR;
    }

    /**
     * name get-Methode
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * name set-Methode
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * address get-Methode
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * address set-Methode
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gender get-Methode
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * gender set-Methode
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * dateOfBirth get-Methode
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * dateOfBirth set-Methode
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * insurance get-Methode
     * @return insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * insurance set-Methode
     * @param insurance
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

}
