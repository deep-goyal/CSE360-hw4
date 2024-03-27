/**
 *	Technician class contains all implementation for report generation and 
 *	UI components for technician interface
 *	
 *	@author Deep Goyal
 *	@ref Prof. Lynn Robert Carter
 *	@version 1.0
 *	@since 03-26-2024
 */

package application;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Technician {
    private Scene scene;
    
    /**
     * Render technician UI components and event handlers for buttons
     * 
     * @param stage Stage for scene updates
     * @param mainScene Main UI scene for backtrack
     * @throws IOException if file writing features encounter issues
     * @return scene with a borderpane containing all UI components
     * 
     */
    public Scene TechnicianView(Stage stage, Scene mainScene) {
        BorderPane root = new BorderPane();
        GridPane form = new GridPane();

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20, 40, 20, 40));

        // Patient ID
        Text patientIdText = new Text("Patient ID:");
        TextField patientIdField = new TextField();
        form.add(patientIdText, 0, 0);
        form.add(patientIdField, 1, 0);

        // The total Agatston CAC score
        Text totalAgatstonText = new Text("The total Agatston CAC score");
        TextField totalAgatstonField = new TextField();
        form.add(totalAgatstonText, 0, 1);
        form.add(totalAgatstonField, 1, 1);
        
        Text vesselLabel = new Text("Vessel level Agatston CAC score");
        form.add(vesselLabel, 0, 2);
        
        GridPane miniform = new GridPane();
        
        miniform.setHgap(10);
        miniform.setVgap(10);
        
        //miniform elements
        //lm
        Text lmLabel = new Text("LM: ");
        TextField lmField = new TextField("");
        
        //lad
        Text ladLabel = new Text("LAD: ");
        TextField ladField = new TextField("");
        
        //lcx
        Text lcxLabel = new Text("LCX: ");
        TextField lcxField = new TextField("");
        
        //rca
        Text rcaLabel = new Text("RCA: ");
        TextField rcaField = new TextField("");
        
        //pda
        Text pdaLabel = new Text("PDA: ");
        TextField pdaField = new TextField("");
        
        //add elements to form
        miniform.add(lmLabel, 0, 0);
        miniform.add(lmField, 1, 0);
        miniform.add(ladLabel, 0, 1);
        miniform.add(ladField, 1, 1);
        miniform.add(lcxLabel, 0, 2);
        miniform.add(lcxField, 1, 2);
        miniform.add(rcaLabel, 0, 3);
        miniform.add(rcaField, 1, 3);
        miniform.add(pdaLabel, 0, 4);
        miniform.add(pdaField, 1, 4);
        
        form.add(miniform, 0,4);
        
        //buffer space for messages
        Text bufferSpace = new Text("");
        form.add(bufferSpace, 0, 5);
        
        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(mainScene);
        });
        backButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        
        //save button event handler
        saveButton.setOnAction(e -> {
        	//check for empty fields
        	if (totalAgatstonField.getText().trim().isEmpty()) {
        		bufferSpace.setText("Total Agatston Field empty!");
        	} else if (lmField.getText().trim().isEmpty()) {
        		bufferSpace.setText("LM field empty!");
        	} else if (ladField.getText().trim().isEmpty()) {
        		bufferSpace.setText("LAD field empty!");
        	} else if (lcxField.getText().trim().isEmpty()) {
        		bufferSpace.setText("LCX field empty!");
        	} else if (rcaField.getText().trim().isEmpty()) {
        		bufferSpace.setText("RCA field empty!");
        	} else if (pdaField.getText().trim().isEmpty()) {
        		bufferSpace.setText("PDA field empty!");
        	} else if (patientIdField.getText().trim().isEmpty()) {
        		bufferSpace.setText("Patient ID field empty!");
        	} else {
        		String PATIENTID = patientIdField.getText();
     
	        	//check if patient file exists
	        	if (Utility.checkPatientInfoExists(PATIENTID)) {
	        		try {
	        			//write to file
	        			String totalAgatston = totalAgatstonField.getText().trim();
	        			String LM = lmField.getText().trim();
	        			String LAD = ladField.getText().trim();
	        			String LCX = ladField.getText().trim();
	        			String RCA = rcaField.getText().trim();
	        			String PDA = pdaField.getText().trim();
	        			
	        			String SEPARATOR = "\\";
	        			
	        			String reportData = totalAgatston + SEPARATOR + LM + SEPARATOR + LAD + SEPARATOR + LCX + SEPARATOR + RCA + SEPARATOR + PDA;
	        			
						Utility.writePatientReportToFile(PATIENTID, reportData);
						bufferSpace.setText("Patient Report Written");
						
						// Delay for a few seconds before switching scenes
				        PauseTransition delay = new PauseTransition(Duration.millis(3000));
				        delay.setOnFinished(event -> stage.setScene(mainScene));
				        delay.play();
				        
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        	} else {
	        		bufferSpace.setText("Patient File Missing!");
	        	}
        	}
        });
        
        //save button styles
        String buttonStyle = "-fx-background-color: rgb(90, 97, 203); "
        		+ "-fx-text-fill: black;"
        		+ "-fx-pref-width: 60px;"
        		+ "-fx-pref-height: 30px;";
        saveButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);
        
        HBox buttonBox = new HBox(10, backButton, saveButton); // Added 10 as spacing between buttons
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        
        root.setPadding(new Insets(10,30,80,30));

        // Add form and button to the root
        root.setCenter(form);
        root.setBottom(buttonBox);

        // Set the scene
        scene = new Scene(root, 500, 410);
        return scene;
    }

}
