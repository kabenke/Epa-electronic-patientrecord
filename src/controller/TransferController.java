package controller;

import model.Doctor;
import model.Revision;
import model.Transfer;

/**
 * This Class manages all Transfers to a Specialist
 */
public class TransferController {

    /**
     * The ePA Controller
     */
    private EPAController ePAController;

    /**
     * Constructor to create a TransferController instance with a given EPAController
     * @param ePAController the controller used to communicate with the system
     */
    public TransferController(EPAController ePAController) {
        this.ePAController = ePAController;
    }

    /**
     * Allows you to get the EPAController stored in the EPAController attribute
     * @return the EPAController used to communicate with the system
     */
    public EPAController getePAController() {
        return this.ePAController;
    }

    /**
     * Allows you to set the EPAController attribute to a given EPAController
     * @param ePAController the EPAController that should be set for this controller
     */
    public void setePAController(EPAController ePAController) {
        this.ePAController = ePAController;
    }

    /**
     * This method allows you to add a Transfer object to the transfer attribute of a given
     * Revision object
     * @param revision the object that has a transfer attribute that will be set
     * @param transfer the Transfer object to which the attribute will be set
     */
    public void addTransfer(Revision revision, Transfer transfer) {
        revision.setTransfer(transfer);
    }

    /**
     * Returns a boolean that represents whether the give doctor is the doctor that the transfer is addressed to.
     * @param doctor given doctor object for which is checked whether it is part of the Transfer object
     * @param transfer given Transfer object that gets checked for the doctor attribute
     * @return true in the case that the given transfer is addressed to the doctor, false otherwise.
     */
    public boolean checkTransfer(Doctor doctor, Transfer transfer) {
         if(!(doctor == null) && !(transfer == null)) {
             return transfer.getDoctor().equals(doctor);
         }
         return false;
    }
}
