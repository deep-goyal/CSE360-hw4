/**
 *	Utility class with static functions for modularized File IO and data handling
 * 
 *	@author Deep Goyal
 *	@ref Prof. Lynn Robert Carter
 *	@version 1.0
 *	@since 03-26-2023
 */

package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Utility {
	
	//directory paths
	private static final String DIRECTORY = System.getProperty("user.dir") + File.separator + "PatientInfo";
	private static final String REPORTDIRECTORY = System.getProperty("user.dir") + File.separator + "PatientReports";
	
	//random obj instance for random patientID generation
    private static final Random random = new Random();
    
    /**
     * creates patientInfo directories if they do not exist already and uses the 
     * random lib to generate unique patientIDs
     * 
     * @return unique patientID
     */
    public static String createUniquePatientID() {
        // Create a directory if it does not exist
        new File(DIRECTORY).mkdir();
        new File(REPORTDIRECTORY).mkdir();

        String id;
        do {
            id = String.format("%05d", random.nextInt(100000));
        } while (idExists(id));

        return id;
    }
    
    /**
     * Checks if the id exists in the lab directories
     * 
     * @param id
     * @return true if found, false otherwise
     */
    private static boolean idExists(String id) {
        File file = new File(DIRECTORY, id + "_PatientInfo.txt");
        return file.exists();
    }
    
    /**
     * Writes patient personal information to patientInfo file
     * 
     * @param patientID
     * @param data- personal info
     * @throws IOException if writing was not successful
     */
    public static void writePatientInfoToFile(String patientID, String data) throws IOException {
    	//filename rule: XXXXX_PatientInfo.txt
        String filename = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
            writer.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Writes reports from technician to patient report file
     * 
     * @param patientID
     * @param report
     * @throws IOException
     */
    public static void writePatientReportToFile(String patientID, String report) throws IOException {
    	//filename rule: XXXXXCTResults.txt
    	String filename = REPORTDIRECTORY + File.separator + patientID + "CTResults.txt";
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(report);
            writer.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Checks if patientInfo exists
     * 
     * @param patientID
     * @return boolean indicating file existance
     */
    public static boolean checkPatientInfoExists(String patientID) {
        String filePath = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
        return new File(filePath).exists();
    }
    
    /**
     * Checks if patient reports exist
     * 
     * @param patientID
     * @return true if reports exist, false otherwise
     */
    public static boolean checkPatientReportExists(String patientID) {
    	String reportPath = REPORTDIRECTORY + File.separator + patientID + "CTResults.txt";
    	return new File(reportPath).exists();    
    }
    
    /**
     * Parses the patient report file by splitting the string by \
     * 
     * @param patientID
     * @return an array of string with report data in order
     * @throws IOException
     */
    public static String[] getPatientReportData(String patientID) throws IOException {
        String reportPath = REPORTDIRECTORY + File.separator + patientID + "CTResults.txt";
        if (!checkPatientReportExists(patientID)) {
            throw new IOException("Report file for patient ID " + patientID + " does not exist.");
        }

        String reportData = new String(Files.readAllBytes(Paths.get(reportPath)));
        // Split the string by a sequence of three backslashes
        return reportData.split("\\\\");
    }
}










