package controller;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import model.EPA;

/**
 * This class is used to Load and Save the Files
 */
public class IOController {

    /**
 	 * The ePA Controller
 	 */
    private EPAController ePAController;
    private static final File SAVE_FILE = new File("SAVE_FILE");

    /**
     * Constructor for the ePA Controller
     * @param ePAController ePA Controller
     */
    public IOController(EPAController ePAController) {
        this.ePAController = ePAController;
    }

    /**
 	 * Ability to save the Files
 	 */
    public void save(){
        try{
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
            stream.writeObject(ePAController.getePA());
            stream.close();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
            System.out.println("error while saving");
        }
    }

    /**
 	 *
 	 * Ability to load the Files
 	 */
    public void load() {

            if (!SAVE_FILE.exists()){
                return;
            }
            try {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(SAVE_FILE));
                EPA ePAC = (EPA) stream.readObject();
                ePAController.setePA(ePAC);
                stream.close();

            } catch (Exception ioException) {
                ioException.printStackTrace();
                System.out.println("error while loading");
            }
        }

    /**
     * Get-Method for the EPA Controller
     * @return ePAController
     */
    public EPAController getePAController() {
        return ePAController;
    }

    /**
     * Set-Method for the EPA Controller
     * @param ePAController EPA Controller
     */
    public void setePAController(EPAController ePAController) {
        this.ePAController = ePAController;
    }
}
