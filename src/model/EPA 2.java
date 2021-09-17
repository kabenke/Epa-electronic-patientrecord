package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the administration of the ePA
 */
public class EPA implements Serializable {

    /**
	 * A list of the Records of the patient
 	 */
    private ArrayList<PatientRecord> patientRecords;

    /**
 	 * A list of the doctors of the patient
 	 */
    private ArrayList<Doctor> doctorRegister;

	/**
	 * The active patient in the System
	 */
	private Patient activePatient;

	/**
	 * The active doctor in the System
	 */
	private Doctor activeDoctor;

	/**
	 * The active entry in the System
	 */
	private TreatmentEntry activeEntry;

	/**
	 * The active revision in the System
	 */
	private Revision activeRevision;

	/**
	 * EPA Constructor
	 */
	public EPA() {
		this.patientRecords = new ArrayList<>();
		this.doctorRegister = new ArrayList<>();
    }

    /**
	 * This method adds a new doctor to the list of registered doctors.
	 * @param doctor the doctor to be added to the system
 	 */
    public void addDoctor(Doctor doctor) {
        this.doctorRegister.add(doctor);
    }

    /**
	 * This method adds a new patient record into the system.
 	 * @param patientRecord the added patient record
 	 */
    public void addPatientRecord(PatientRecord patientRecord) {
       this.patientRecords.add(patientRecord);
    }

	/**
	 * get-Method patientRecords
	 * @return patientRecords
	 */
	public ArrayList<PatientRecord> getPatientRecords() {
		return patientRecords;
	}

	/**
	 * set-Method patientRecords
	 * @param patientRecords
	 */
	public void setPatientRecords(ArrayList<PatientRecord> patientRecords) {
		this.patientRecords = patientRecords;
	}

	/**
	 * get-Method doctorRegister
	 * @return doctorRegister
	 */
	public ArrayList<Doctor> getDoctorRegister() {
		return doctorRegister;
	}

	/**
	 * set-Method doctorRegister
	 * @param doctorRegister
	 */
	public void setDoctorRegister(ArrayList<Doctor> doctorRegister) {
		this.doctorRegister = doctorRegister;
	}

	/**
	 * get-Method activePatient
	 * @return activePatient
	 */
	public Patient getActivePatient() {
		return activePatient;
	}

	/**
	 * set-Method activePatient
	 * @param patient
	 */
	public void setActivePatient(Patient patient) {
		this.activePatient = patient;
	}

	/**
	 * get-Method activeDoctor
	 * @return activeDoctor
	 */
	public Doctor getActiveDoctor() {
		return activeDoctor;
	}

	/**
	 * set-Method activeDoctor
	 * @param doctor
	 */
	public void setActiveDoctor(Doctor doctor) {
		this.activeDoctor = doctor;
	}

	/**
	 * get-Method activeEntry
	 * @return activeEntry
	 */
	public TreatmentEntry getActiveEntry() {
		return activeEntry;
	}

	/**
	 * set-Method activeEntry
	 * @param entry
	 */
	public void setActiveEntry(TreatmentEntry entry) {
		this.activeEntry = entry;
	}

	/**
	 * get-Method activeRevision
	 * @return activeRevision
	 */
	public Revision getActiveRevision() {
		return activeRevision;
	}

	/**
	 * set-Method activeRevision
	 * @param revision
	 */
	public void setActiveRevision(Revision revision) {
		this.activeRevision = revision;
	}
}
