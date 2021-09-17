package controller;

import model.EPA;
import java.lang.UnsupportedOperationException;

/**
 * This class is the central communication interface between controller and model
 */
public class EPAController {

    /**
 	 * The EPA
 	 */
    private EPA ePA;

    /**
 	 * The IOController
 	 */
    private IOController iOController;

    /**
 	 * The PatientRecordController
 	 */
    private PatientRecordController patientRecordController;

    /**
 	 * The DoctorListController
 	 */
    private DoctorListController doctorListController;

    /**
 	 * The PatientController
 	 */
    private PatientController patientController;

	/**
	 * Constructor for the EPAController
	 */
	public EPAController() {
		ePA = new EPA();
		iOController = new IOController(this);
		patientRecordController = new PatientRecordController(this);
		doctorListController = new DoctorListController(this);
		patientController = new PatientController(this);

		iOController.load();
    }

    /**
 	 *
 	 * This method gives us access to the IOController
 	 * @return IOController
 	 */
    public IOController getIOController() {
        return iOController;
    }

    /**
 	 *
 	 * This method gives us access to the DoctorListController
 	 * @return DoctorListController
 	 */
    public DoctorListController getDoctorListController() throws UnsupportedOperationException {
     	return doctorListController;
    }

    /**
 	 *
 	 * This method gives us access to the PatientController
 	 * @return PatientController
 	 */
    public PatientController getPatientController() throws UnsupportedOperationException {
     	return patientController;
    }

    /**
 	 *
 	 * This method gives us access to the PatientRecordController
 	 * @return PatientRecordController
 	 */
    public PatientRecordController getPatientRecordController() throws UnsupportedOperationException {
        return patientRecordController;
    }

	/**
	 * Get-Method for EPA
	 * @return ePA - gibt eine Referenz von EPA
	 */
	public EPA getePA() {
		return ePA;
	}

	/**
	 * Set-Method for EPA
	 * @param ePA EPA
	 */
	public void setePA(EPA ePA) {
		this.ePA = ePA;
	}

	/**
	 * Get-Method for the IOController
	 * @return IOController
	 */
	public IOController getiOController() {
		return iOController;
	}

	/**
	 * Set-Method for the IOController
	 * @param iOController IOController
	 */
	public void setiOController(IOController iOController) {
		this.iOController = iOController;
	}

	/**
	 * Set-Method for the PatientRecordController
	 * @param patientRecordController PatientRecordController
	 */
	public void setPatientRecordController(PatientRecordController patientRecordController) {
		this.patientRecordController = patientRecordController;
	}

	/**
	 * Set-Method for the DoctorListController
	 * @param doctorListController DoctorListController
	 */
	public void setDoctorListController(DoctorListController doctorListController) {
		this.doctorListController = doctorListController;
	}

	/**
	 * Set-Method for the PatientController
	 * @param patientController PatientController
	 */
	public void setPatientController(PatientController patientController) {
		this.patientController = patientController;
	}
}
