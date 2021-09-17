package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.util.Date;

/**
 * This class represents a TreatmentEntry in a Patients PatientRecord
 */
public class TreatmentEntry implements Serializable{

    /**
 	 * All revisions of a treatmentEntry
 	 */
    private ArrayList<Revision> revisions;

    /**
     *  Which is the doctor that is creating a new treatment entry, or is editing an old one and consequently is creating a revision.
 	 */
    private Doctor doctor;

    /**
     * Treatment entry
     */
    public TreatmentEntry() {
        this.revisions = new ArrayList<Revision>();
    }

    /**
     * Treatment entry
     */
    public TreatmentEntry(Doctor doctor)
    {
        this.doctor = doctor;
        this.revisions = new ArrayList<Revision>();
    }

    /**
 	 * Adds a new Revision object to the list of revisions.
 	 * @param revision - a Revision object that contains all the information of a treatmentEntry
 	 */
    public void addRevision(Revision revision) {
        this.revisions.add(revision);
    }

    /**
     * Show all revisions
     * @return Array List with all revisions
     */
    public ArrayList<Revision> getRevisions() {
        return revisions;
    }

    /**
     * Set Method for Revision
     *
     */
    public void setRevisions(ArrayList<Revision> revisions) {
        this.revisions = revisions;
    }

    /**
     * Get the logged in Doctor
     * @return - Doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Set Method for Doctor
     * @param doctor - the doctor who made the TreatmentEntry
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Tostring method that returns a String of the format "Date Time ICD-key" of the treatmentEntry
     * @return a String of the format "Date Time ICD-key" of the treatmentEntry
     */
    @Override
    public String toString() {
        Revision currentRevision = revisions.get(revisions.size() - 1);
        Date date = currentRevision.getDate();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdfDate.format(date);
        String formattedTime = sdfTime.format(date);
        return formattedDate + " " + formattedTime + " " + currentRevision.getDiagnosis();
    }
}
