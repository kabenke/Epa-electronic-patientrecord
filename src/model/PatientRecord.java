package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a Patient Record
 */
public class PatientRecord implements Serializable {

    /**
 	 * The Patient the Patient Record belongs to
 	 */
    private Patient patient;

    /**
 	 * A List of all Treatment Entries
 	 */
    private ArrayList<TreatmentEntry> treatmentEntrys;

    /**
     * Whether the patient is currently active in the system
     */
    private boolean isActive;

    /**
     * Constructor for the Patient
     * @param patient Patient
     */
    public PatientRecord(Patient patient) {
        this.patient = patient;
        this.treatmentEntrys = new ArrayList<>();
        this.isActive = false;
    }

    /**
     * Adds a new Treatment Entry to the Patient Record by adding it to the ArrayList TreatmentEntrys
 	 * @param treatmentEntry - A Treatment Entry
 	 */
    public void addTreatmentEntry(TreatmentEntry treatmentEntry) {
        this.treatmentEntrys.add(treatmentEntry);
    }

    /**
     * Get-Method for the Patient
     * @return Patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Set_Method for the Patient
     * @param patient Patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Get-Method for Treatment Entries
     * @return  ArrayList of the Treatment Entries
     */
    public ArrayList<TreatmentEntry> getTreatmentEntrys() {
        return treatmentEntrys;
    }

    /**
     * Set-Method for Treatment Entries
     * @param treatmentEntrys ArrayList of the Treatment Entries
     */
    public void setTreatmentEntrys(ArrayList<TreatmentEntry> treatmentEntrys) {
        this.treatmentEntrys = treatmentEntrys;
    }

    /**
     * Get-method for the isActive attribute
     * @return the value of the isActive attribute
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * Set-method for the isActive attribute
     * @param isActive boolean value to which the isActive attribute will be set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
