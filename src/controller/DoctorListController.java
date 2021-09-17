package controller;

import model.Doctor;
import model.Revision;
import model.Transfer;
import model.TreatmentEntry;

import javax.print.Doc;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;

/**
 * This class is used to manage the Doctors information
 */
public class DoctorListController {

    /**
	 * The EPA Controller
 	 */
    private EPAController ePAController;

	/**
	 * Constructor for the EPA Controller
	 * @param ePAController - ePA Controller
	 */
	public DoctorListController(EPAController ePAController) {
		this.ePAController = ePAController;
    }

    /**
 	 *
 	 * Create new doctor object
	 *
 	 * @param name - doctor's name
	 * @param subject - doctor's professional subject of expertise
	 * @param address - doctor's office address
	 * method createDoctor - creates a new doctor object and add it to ePA System
 	 */
    public void createDoctor(String name, String subject, String address) throws IllegalArgumentException {
		if(!validDoctorInput(name, address, subject)){
			throw new IllegalArgumentException("Invalid data");
		}
		else {
			Doctor doctor = loadDoctor(name, address);
			if(doctor != null) {
				throw new IllegalArgumentException("Doctor already in the system");
			}

			Doctor newDoc = new Doctor(name, subject, address);
			ePAController.getePA().addDoctor(newDoc);
    	}
	}


    /**
 	 *
 	 * Check if Doctors information is valid
 	 * @param name - doctor's name
 	 * @param address - doctor's office address
 	 * @param subject - doctor's professional subject of expertise
 	 * @return boolean - when 'true' the entered information in all entry-fields is in the correct format and no field is left empty(e.g. name = Name )
	 * 					 when 'false' the entered information in at least one field is incorrect or the field is empty(e.g. name = 11)
 	 * @throws UnsupportedOperationException
 	 *	 	 	Diese Exception wird geworfen, fallsdie Methode noch nicht implementiert ist. 
 	 */
    public boolean validDoctorInput(String name, String address, String subject){
    	if( name instanceof String && !name.equals("")
				&& address instanceof String && !address.equals("")
				&& subject instanceof String && !subject.equals("")){
    		return true;
		}else{
    		return false;
		}

    }

	/**
	 * get EpaContoroller
	 * @return ePAController
	 */

	public EPAController getePAController() {
		return ePAController;
	}

	/**
	 * set EpcContoroller
	 * @param ePAController - changed epaContorller object
	 */

	public void setePAController(EPAController ePAController) {
		this.ePAController = ePAController;
	}

	/**
	 * returns a doctor from the doctor list with the given parameters or null if there is none
	 * @param name the name of the doctor that shall be loaded
	 * @param address the address of the doctor that shall be loaded
	 * @return the doctor object that has attributes that correspond to the given parameters, or null if there is none
	 */
	public Doctor loadDoctor(String name, String address) {
		ArrayList<Doctor> doctors = ePAController.getePA().getDoctorRegister();
		for(Doctor doc : doctors) {
			if(doc.getName().equals(name) && doc.getAddress().equals(address)) {
				return doc;
			}
		}
		return null;
	}

	/**
	 * method that allows a doctor to make a transfer to in a given treatment entry to a given doctor
	 * @param name the name of the doctor to be transferred to
	 * @param address the address of the doctor to be transferred to
	 * @param treatmentEntry the treatment entry to add the transfer to
	 */
	public void makeTransfer(String name, String address, TreatmentEntry treatmentEntry) {
		if(name == null || address == null || treatmentEntry == null) {
			throw new IllegalArgumentException("Arguments were null");
		}
		Doctor doctor = loadDoctor(name, address);
		if(doctor == null) {
			throw new IllegalArgumentException("given Doctor not in the system");
		}
		Transfer newTransfer = new Transfer();
		newTransfer.setDoctor(doctor);
		ArrayList<Revision> revisions = treatmentEntry.getRevisions();
		for(Revision currentRevision : revisions) {
			currentRevision.setTransfer(newTransfer);
		}
	}
}
