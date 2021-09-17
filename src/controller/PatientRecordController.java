package controller;

import java.util.Date;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import abstractuserinterfaces.TreatmentEntryAUI;
import abstractuserinterfaces.RevisionsAUI;
import model.*;


/**
 * This class manages all the Patient Records and their Data
 */
public class PatientRecordController {

	/**
	 * Attribute to access the EPAController
	 */
	private EPAController ePAController;

	/**
	 * Attribute to access the TreatmentEntryAUI
	 */
	private TreatmentEntryAUI treatmentEntryAUI;

	/**
	 * Attribute to access the RevisionsAUI
	 */
	private RevisionsAUI revisionsAUI;

	/**
	 * Constructor of the PatientRecordController
	 * @param ePAController EPAController
	 */
	public PatientRecordController(EPAController ePAController) {
		this.ePAController = ePAController;
	}

	/**
	 *
	 * @param insuranceNBR InsuranceNBR of the Patient
	 * @param name Name of the Patient
	 * @param address Address of the Patient
	 * @param gender Gender of the Patient
	 * @param dateOfBirth DateOfBirth of the Patient
	 * @param insurance Insurance of the Patient
	 */
	public void createPatientRecord(String insuranceNBR, String name, String address, String gender, Date dateOfBirth, String insurance) {
		if(validInput(insuranceNBR, name, address, gender, dateOfBirth, insurance))
		{
			Patient patient = new Patient(insuranceNBR, name, address, gender, dateOfBirth, insurance);
			ArrayList<PatientRecord> records = getePAController().getePA().getPatientRecords();
			if(loadPatientRecord(patient.getInsuranceNBR()) == null) { /* if Patient Record does not exist yet */
				PatientRecord patientRecord = new PatientRecord(patient);
				records.add(patientRecord);
			}
			else{
				throw new IllegalArgumentException("Patient already has a PatientRecord");
			}
		} else {
			throw new IllegalArgumentException("Check your information");
		}

	}

	/**
	 * Creates a new Revision for the Patient with the given Data
	 *
	 * @param insuranceNBR InsuranceNBR of the Patient
	 * @param finding Finding
	 * @param diagnosis Diagnosis
	 * @param therapy Therapy
	 * @param medicationPlans Mediaction Plan
	 * @param date The Date of the Treatment
	 * @param notes Notes for the conversation with the Patient
	 * @param symptomes Symptoms
	 * @param treatmentEntry Treatment Entry
	 */
	public void createRevision(String insuranceNBR, String finding, String diagnosis, String therapy, String medicationPlans, Date date, String notes, String symptomes, TreatmentEntry treatmentEntry)  {
		if(!validRevisionInput(insuranceNBR, finding, diagnosis, therapy, medicationPlans, date, notes, symptomes, treatmentEntry)) {
			throw new IllegalArgumentException("invalid inputs");
		}
		boolean hasBeenAdded = false;
		Revision revision=new Revision(finding,diagnosis,therapy,medicationPlans,date,notes,symptomes);
		ArrayList<PatientRecord> records = getePAController().getePA().getPatientRecords();
		for(PatientRecord patientRecord : records){
			if(patientRecord.getPatient().getInsuranceNBR().equals(insuranceNBR)){
				ArrayList<TreatmentEntry> treatmentEntries = patientRecord.getTreatmentEntrys();
				if(!treatmentEntries.isEmpty()) {
					for(TreatmentEntry currentTreatmentEntry : treatmentEntries) {
						if (currentTreatmentEntry.equals(treatmentEntry)) {
							currentTreatmentEntry.addRevision(revision);
							hasBeenAdded = true;
						}
					}
					if(!hasBeenAdded) {
						patientRecord.addTreatmentEntry(treatmentEntry); /* add entry to the list */
						/* add revision to the last element in the list */
						treatmentEntries.get(treatmentEntries.size() - 1).addRevision(revision);
					}
				} else { /* empty list */
					patientRecord.addTreatmentEntry(treatmentEntry); /* add entry to the list */
					/* add revision to the last element in the list */
					treatmentEntries.get(treatmentEntries.size() - 1).addRevision(revision);
				}
			}
		}
	}

	/**
	 * helping method to validate inputs for revision
	 * @param insuranceNBR the patients insurance number
	 * @param finding the doctors finding
	 * @param diagnosis the doctors diagnosis
	 * @param therapy the recommended therapy
	 * @param medicationPlans the doctors medication plans
	 * @param date the current date
	 * @param notes optional notes
	 * @param symptomes the patients symptomes
	 * @param treatmentEntry the treatment entry to add the revision to
	 * @return true if all the data is valid, false otherwise
	 */
	private boolean validRevisionInput(String insuranceNBR, String finding, String diagnosis, String therapy, String medicationPlans, 
										Date date, String notes, String symptomes, TreatmentEntry treatmentEntry) {
	    if(insuranceNBR != null && finding != null && diagnosis != null && therapy != null && medicationPlans != null && date != null &&
		    notes != null && symptomes != null && treatmentEntry != null && checkICD(diagnosis)) {
			   return true;
		}	
		return false;	
	}

	/**
	 * Restrict the data that gets transferred to a specialist
	 *
	 * @param insuranceNBR the insurance number of the patient
	 * @param transferData a boolean array where a true at index i means that the attribute nbr i gets transfered
	 * @param treatmentEntry the entry which contains the transfer
	 */
	public void restrictData(String insuranceNBR, boolean[] transferData, TreatmentEntry treatmentEntry) {
		ArrayList<Revision> revisions = treatmentEntry.getRevisions();
		for(Revision currentRevision : revisions) {
			Transfer transfer = currentRevision.getTransfer();
			if(transfer != null) {
				transfer.setTransferData(transferData);
			}
		}
	}

	/**
	 * Provides a list of all Treatment Entries for a patient in a particular Time Interval
	 *
	 * @param begin Start Date of the Interval
	 * @param end End Date of the Interval
	 * @param insuranceNBR Insurance NBR of the Patient
	 * @return List of all Treatment Entries in the Time Interval
	 */
	public ArrayList<TreatmentEntry> summaryByDate(Date begin, Date end, String insuranceNBR)
			throws IllegalArgumentException {

		if(!inputsNotNull(begin, end)) {
			throw new IllegalArgumentException("All inputs have to be not equal to null");
		}

		PatientRecord patientRecord = loadPatientRecord(insuranceNBR);

		if(patientRecord == null) {
			throw new IllegalArgumentException("No patient with given insurance number in the system");
		}

		if(begin.after(end))
		{
			throw new IllegalArgumentException("Start Date must be before End Date!");
		}

		ArrayList<TreatmentEntry> treatmentEntrys = patientRecord.getTreatmentEntrys();
		ArrayList<TreatmentEntry> treatmentsByDate = new ArrayList<>();

		for(TreatmentEntry treatmentEntry : treatmentEntrys) {
			ArrayList<Revision> revisions = treatmentEntry.getRevisions();

			if(!revisions.isEmpty()) { /* cant usually be empty, might be redundant */
				Revision currentRevision = revisions.get(revisions.size() - 1); /* last element = current revision */

				if(inTimePeriod(begin, end, currentRevision.getDate()))
				{
					treatmentsByDate.add(treatmentEntry);
				}
			}
		}
		return treatmentsByDate;
	}

	/**
	 * method that determines whether a given date is in a given time period
	 * @param begin the begin of the time period
	 * @param end the end of the time period
	 * @param revisionDate the date to be checked for
	 * @return true if the given date is in the time period, false otherwise
	 */
	private boolean inTimePeriod(Date begin, Date end, Date revisionDate) {
		return (revisionDate.after(begin) && revisionDate.before(end)) ||
				(!revisionDate.before(begin)) && (!revisionDate.after(begin)) ||
				(!revisionDate.before(end)) && (!revisionDate.after(end));
	}

	/**
	 * private method to validate inputs for summaryByDate
	 * @param begin the begin date
	 * @param end the end date
	 * @return whether all objects are not equal to null
	 */
	private boolean inputsNotNull(Date begin, Date end) {
		return (begin != null && end != null);
	}

	/**
	 * Provides the list of all treatment entries for a patient with a specific ICD code
	 *
	 * @param icd The ICD-Code
	 * @param insuranceNBR The Insurance NBR of the Patient
	 * @return List of all Treatment Entries with the given ICD-Code
	 */
	public ArrayList<TreatmentEntry> summaryByICD(String icd, String insuranceNBR)
			throws IllegalArgumentException {
		PatientRecord patientRecord = loadPatientRecord(insuranceNBR);
		if(patientRecord == null) { /* no patient with given insuranceNBR in the system */
			throw new IllegalArgumentException("No patient with given insuranceNBR in the system.");
		}
		if(icd == null) {
			throw new IllegalArgumentException("ICD is null");
		}
		ArrayList<TreatmentEntry> treatmentEntrys = patientRecord.getTreatmentEntrys();
		ArrayList<TreatmentEntry> revisionsWithICD = new ArrayList<>();
		for(TreatmentEntry treatmentEntry : treatmentEntrys) {
			ArrayList<Revision> revisions = treatmentEntry.getRevisions();
			if(!revisions.isEmpty()) { /* cant usually be empty, might be redundant */
				Revision currentRevision = revisions.get(revisions.size() - 1); /* last element = current revision */
				if(currentRevision.getDiagnosis().startsWith(icd)) {
					revisionsWithICD.add(treatmentEntry);
				}
			}
		}
		return revisionsWithICD;
	}

	/**
	 * Checks whether the input is in ICD-Code format
	 *
	 * @param icd The ICD-Code
	 * @return boolean Indicates whether the input is an ICD-Code
	 */
	public boolean checkICD(String icd) {
		final int LENGTH_OF_ICD = 5;

		//to avoid nullpointerexceptions
		if(icd == null) {
			return false;
		}
		//ICD-Code has atleast 3 characters and not exactly 4
		if(icd.length() < 3 || icd.length() == 4){
			return false;
		}
		//First character has to be uppercase
		if(!(Character.isLetter(icd.charAt(0)) && Character.isUpperCase(icd.charAt(0)))){
			return false;
		}
		//Second and third characters have to be numbers
		if(!(Character.isDigit(icd.charAt(1)) && Character.isDigit(icd.charAt(2)))){
			return false;
		}
		//If the code consists of more than 5 characters, the fourth one has to be a dot, and all following have to be numbers
		if(icd.length() >= LENGTH_OF_ICD){
			if(!(icd.charAt(3) == '.')){
				return false;
			}
			for(int i=4; i<icd.length(); i++){
				if(!Character.isDigit(icd.charAt(i))){
					return false;
				}
			}
		}
		//valid ICD-code
		return true;
	}

	/**
	 * Returns (if possible) a Patient Record with a specific Insurance Number
	 * @param insuranceNBR Insurance Number of the Patient
	 * @return PatientRecord The PatientRecord with the specific Insurance Number
	 */
	public PatientRecord loadPatientRecord(String insuranceNBR) {
		ArrayList<PatientRecord> records = getePAController().getePA().getPatientRecords();
		for(int i=0; i<records.size(); i++){
			if(records.get(i).getPatient().getInsuranceNBR().equals(insuranceNBR)){
				return records.get(i);
			}
		}
		return null;
	}

	/**
	 * Checks whether all entries have the correct Format
	 *
	 * @param insuranceNBR Insurance Number of the Patient
	 * @param name Name of the Patient
	 * @param address Address of the Patient
	 * @param gender Gender of the Patient
	 * @param dateOfBirth Patient`s Date of Birth
	 * @param insurance Patient's Insurance
	 * @return boolean Indicates if the Data has the correct format
	 */
	public boolean validInput(String insuranceNBR, String name, String address, String gender, Date dateOfBirth, String insurance){
		if(!insuranceNBR.equals("") && !name.equals("") && !address.equals("") && !gender.equals("") && !(dateOfBirth == null) && !insurance.equals(""))
		{
			return true;
		}
		return false;
	}

	//Getter und Setter:

	/**
	 * Get-Method for the EPAController
	 * @return EPAController
	 */
	public EPAController getePAController() {
		return ePAController;
	}

	/**
	 * Set-Method for the EPAController
	 * @param ePAController EPAController
	 */
	public void setePAController(EPAController ePAController) {
		this.ePAController = ePAController;
	}

	/**
	 * Get-Method for the TreatmentEntryAUI
	 * @return TreatmentEntryAUI
	 */
	public TreatmentEntryAUI getTreatmentEntryAUI() {
		return treatmentEntryAUI;
	}

	/**
	 * Set-Method for the TreatmentEntryAUI
	 * @param treatmentEntryAUI TreatmentEntryAUI
	 */
	public void setTreatmentEntryAUI(TreatmentEntryAUI treatmentEntryAUI) {
		this.treatmentEntryAUI = treatmentEntryAUI;
	}

	/**
	 * Get-Method for the RevisionsAUI
	 * @return RevisionsAUI
	 */
	public RevisionsAUI getRevisionsAUI() {
		return revisionsAUI;
	}

	/**
	 * Set-Method for the RevisionsAUI
	 * @param revisionsAUI RevisionsAUI
	 */
	public void setRevisionsAUI(RevisionsAUI revisionsAUI) {
		this.revisionsAUI = revisionsAUI;
	}
}
