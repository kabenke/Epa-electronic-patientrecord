package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.UnsupportedOperationException;

/**
 * This class represents a Revision made in a Treatment Entry
 */
public class Revision implements Serializable {

    /**
 	 * The finding of the doctor
 	 */
    private String finding;

    /**
 	 * The doctor`s diagnosis
 	 */
    private String diagnosis;

    /**
 	 * The therapy chosen by the doctor for the patient
 	 */
    private String therapy;

    /**
 	 * The medication the doctor prescribed for the patient
 	 */
    private String medicationPlans;

    /**
 	 * The date when the Treatment Entry was created
 	 */
    private Date date;

    /**
 	 * The time at which the Treatment Entry was created
 	 */
    private String notes;

    /**
 	 * The patient's symptoms
 	 */
    private String symptomes;

    /**
 	 * Transfer to a specialist
 	 */
    private Transfer transfer;

    /**
     * Constructor for a revision
     * @param finding Finding
     * @param diagnosis Diagnosis
     * @param therapy Therapy
     * @param medicationPlans Medication
     * @param date Date
     * @param notes Notes
     * @param symptomes Symptomes
     */
    public Revision(String finding, String diagnosis, String therapy, String medicationPlans, Date date, String notes, String symptomes) {
        this.finding = finding;
        this.diagnosis = diagnosis;
        this.therapy = therapy;
        this.medicationPlans = medicationPlans;
        this.date = date;
        this.notes = notes;
        this.symptomes = symptomes;
        transfer = new Transfer();
    }

    /**
     * Get-Method for a finding
     * @return finding
     */
    public String getFinding() {
        return finding;
    }

    /**
     * Set-Method for a finding
     * @param finding Finding
     */
    public void setFinding(String finding) {
        this.finding = finding;
    }

    /**
     * Get-Method for a diagnosis
     * @return diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Set-Method for a diagnosis
     * @param diagnosis Diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Get-Method for the therapy
     * @return therapy
     */
    public String getTherapy() {
        return therapy;
    }

    /**
     * Set-Methode für die therapy
     * @param therapy Therapy
     */
    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    /**
     * Get-Method for the medication
     * @return medicationPlans
     */
    public String getMedicationPlans() {
        return medicationPlans;
    }

    /**
     * Set-Method for the medication
     * @param medicationPlans Medication
     */
    public void setMedicationPlans(String medicationPlans) {
        this.medicationPlans = medicationPlans;
    }

    /**
     * Get-Method for a date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set-Method for a date
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get-Method for the notes
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set-Method for the notes
     * @param notes Notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Get-Method for the Symptoms
     * @return symptomes
     */
    public String getSymptomes() {
        return symptomes;
    }

    /**
     * Set-Methode für die Symptome
     * @param symptomes Symptome
     */
    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }


    /**
     * Get-Method for the transfer data
     * @return transfer
     */
    public Transfer getTransfer() {
        return transfer;
    }

    /**
     * Set-Method for the transfer data
     * @param transfer Transfer Data
     */
    public void setTransfer(Transfer transfer) {this.transfer = transfer;}

    /**
     * Tostring method that returns a String of the format "Date Time ICD-key" of the revision
     * @return a String of the format "Date Time ICD-key" of the revision
     */
    @Override
    public String toString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdfDate.format(date);
        String formattedTime = sdfTime.format(date);
        return formattedDate + " " + formattedTime + " " + diagnosis;
    }
}
