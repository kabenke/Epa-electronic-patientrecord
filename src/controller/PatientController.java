package controller;

import abstractuserinterfaces.PersonalDataAUI;
import model.Patient;
import model.PatientRecord;
import model.Revision;
import model.TreatmentEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

/**
 * This class manages all Patients
 */
public class PatientController {

    /**
 	 * The ePA Controller
 	 */
    private EPAController ePAController;

    /**
 	 * The PersonalDataAUI
 	 */
    private PersonalDataAUI personalDataAUI;

	/**
	 * Constructor for the ePA Controller
	 * @param ePAController ePA Controller
	 */
	public PatientController(EPAController ePAController) {
		this.ePAController = ePAController;
    }

	/**
	 * Ändert die Daten in der Akte eines bestimmten Patienten
	 *
	 * @param insuranceNBR Versichertennummer des Patienten
	 * @param name Name des Patienten
	 * @param address Adresse des Patienten
	 * @param gender Geschlecht des Patienten
	 * @param dateOfBirth Geburtsdatum des Patienten
	 * @param insurance Versicherung des Patienten
	 * @param patientRecord Akte wo die Daten geändert werden sollen
	 * @throws IllegalArgumentException
	 *	 	 	Diese Exception wird geworfen, falls die Eingaben nicht valide sind.
	 */
	public void editPatientDetails(String insuranceNBR, String name, String address, String gender, Date dateOfBirth, String insurance, PatientRecord patientRecord) throws IllegalArgumentException {
		if(patientRecord == null) {
			throw new IllegalArgumentException("Invalid patientRecord");
		}
		if(!getePAController().getPatientRecordController().validInput(insuranceNBR, name, address, gender, dateOfBirth, insurance)){
			throw new IllegalArgumentException("Parameters in false format");
		}
		else{
			Patient patient = patientRecord.getPatient();
			patient.setName(name);
			patient.setAddress(address);
			patient.setGender(gender);
			patient.setDateOfBirth(dateOfBirth);
			patient.setInsurance(insurance);
			patient.setInsuranceNBR(insuranceNBR);
		}
	}

    /**
 	 * Gives the Patient the opportunity to print all their Treatment Entries
 	 * @param patient the patient for whom the report is to be printed
	 * @throws IllegalArgumentException if the patient is null, or not in the system
 	 */
    public void print(Patient patient) throws IOException {
        File report = new File("Report.txt");

        report.createNewFile();

		if(!report.exists()) {
			report.mkdir();
		}

		String fileData = generateFileContent(patient); /* gets the formatted Report */
		FileOutputStream fileOutputStream = new FileOutputStream("Report.txt");
		fileOutputStream.write(fileData.getBytes());
		fileOutputStream.flush();
		fileOutputStream.close();
    }

	/**
	 * helping method to generate the formatted report string
	 * @param patient the patient for whom the report is to be printed
	 * @throws IllegalArgumentException if the patient is null, or not in the system
	 * @return a formatted report string for the given patient
	 */
	public String generateFileContent(Patient patient) {
    	if(patient == null) {
    		throw new IllegalArgumentException("Expected Patient, got null");
		}
		String insuranceNBR = patient.getInsuranceNBR();
    	PatientRecord patientRecord = this.ePAController.getPatientRecordController().loadPatientRecord(insuranceNBR);
		if(patientRecord == null) { /* If the patient is not in the system */
			throw new IllegalArgumentException("No patient record for the given patient");
		}
    	ArrayList<TreatmentEntry> treatmentEntries = patientRecord.getTreatmentEntrys();
    	String formattedDocument = "";
		for(TreatmentEntry treatmentEntry : treatmentEntries) {
			ArrayList<Revision> revisions = treatmentEntry.getRevisions();
			Revision currentRevision = revisions.get(revisions.size() - 1);
			formattedDocument += "date/time: " + currentRevision.getDate().toString() + "\n\n";
			formattedDocument += "finding: " + currentRevision.getFinding() + "\n";
			formattedDocument += "diagnosis: " + currentRevision.getDiagnosis() + "\n";
			formattedDocument += "therapy: " + currentRevision.getTherapy() + "\n";
			formattedDocument += "medication plans: " + currentRevision.getMedicationPlans() + "\n";
			formattedDocument += "symptoms: " + currentRevision.getSymptomes() + "\n";
			formattedDocument += "notes: " + currentRevision.getNotes() + "\n \n \n";
		}
		return formattedDocument;
    }

    /**
 	 * This method goes through the list of patient records to check whether the given insurance number is unique
 	 * @param insuranceNBR Insurance Number to be checked whether it is unique
 	 * @param patientRecords list of patient records to go through
 	 * @return true if the number is unique, false otherwise
 	 */
    public boolean checkUniqueInsuranceNBR(String insuranceNBR, ArrayList<PatientRecord> patientRecords) {
        if(insuranceNBR == null || patientRecords == null) {
			return false;
		}
		for(PatientRecord patientRecord : patientRecords) {
        	Patient patient = patientRecord.getPatient();
        	if(patient.getInsuranceNBR().equals(insuranceNBR)) {
        		return false;
			}
		}
        return true;
    }

	/**
	 * Get-Method for the ePA Controller
	 * @return ePaController
	 */
	public EPAController getePAController() {
		return ePAController;
	}

	/**
	 * Set-Method for the ePA Controller
	 * @param ePAController ePaController
	 */
	public void setePAController(EPAController ePAController) {
		this.ePAController = ePAController;
	}

	/**
	 * Get-Method for the PersonalDataAUI
	 * @return personalDataAUI
	 */
	public PersonalDataAUI getPersonalDataAUI() {
		return personalDataAUI;
	}

	/**
	 * Set-Method for the PersonalDataAUI
	 * @param personalDataAUI personalDataAUI
	 */
	public void setPersonalDataAUI(PersonalDataAUI personalDataAUI) {
		this.personalDataAUI = personalDataAUI;
	}
}
