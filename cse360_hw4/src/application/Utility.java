package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class Utility {

	private static final String DIRECTORY = System.getProperty("user.dir") + File.separator + "PatientInfo";
	private static final String REPORTDIRECTORY = System.getProperty("user.dir") + File.separator + "PatientReports";
    private static final Random random = new Random();

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

    private static boolean idExists(String id) {
        File file = new File(DIRECTORY, id + "_PatientInfo.txt");
        return file.exists();
    }

    public static void writePatientInfoToFile(String patientID, String data) throws IOException {
        String filename = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
            writer.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void writePatientReportToFile(String patientID, String report) throws IOException {
    	String filename = REPORTDIRECTORY + File.separator + patientID + "CTResults.txt";
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(report);
            writer.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static boolean checkPatientInfoExists(String patientID) {
        String filePath = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
        return new File(filePath).exists();
    }
}










