package model;

import java.io.Serializable;

/**
 * This class represents a Transfer to a specialist
 */
public class Transfer implements Serializable {

    /**
 	 * Boolean List of which Data is visible for the Doctor
 	 */
    private boolean[] transferData;

    /**
 	 * The specialist to whom the transfer is made
 	 */
    private Doctor doctor;

    /**
     * Constructor for the Transfer
     */
    public Transfer() {
        transferData = new boolean[6];
        transferData[0] = true;
        transferData[1] = true;
        transferData[2] = true;
        transferData[3] = true;
        transferData[4] = true;
        transferData[5] = true;
    }

    /**
     * Get-Method for the Transfer Data
     * @return TransferData
     */
    public boolean[] getTransferData() {
        return transferData;
    }

    /**
     * Set-Method for the Transfer Data
     * @param transferData Boolean Liste welche Daten sichtbar sind
     */
    public void setTransferData(boolean[] transferData) {
        this.transferData = transferData;
    }

    /**
     * Get-Method for the Doctor
     * @return Doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Set-Method for the Doctor
     * @param doctor Doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
