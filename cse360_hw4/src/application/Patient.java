package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Patient { 
    private Scene scene;

    public Scene PatientView(Stage stage) {
    	BorderPane root = new BorderPane();
    	
    	VBox vbox = new VBox();
    	
    	//greet text
    	Text greet = new Text("Please enter your Patient ID in the textfield below: ");
    	
    	//patient id textfield
    	TextField pid = new TextField();
    	
    	//submit button
    	Button submitbtn = new Button("Submit");
    	
    	//buffer text for errors or other messages
    	Text bufSpace = new Text("");
    	
    	BorderPane.setAlignment(vbox, Pos.CENTER);
    	vbox.setAlignment(Pos.CENTER);
    	
    	vbox.getChildren().addAll(greet,pid,bufSpace, submitbtn);
    	
    	root.setCenter(vbox);
    	
    	root.setPadding(new Insets(30));
    	vbox.setSpacing(10);
    	pid.setMaxWidth(200);
    	
        // Set the scene
        scene = new Scene(root, 400, 300);
        
        submitbtn.setOnAction(e -> {
    		if (pid.getText().trim().isEmpty()) {
    			bufSpace.setText("Enter a patient id!");
    		} else {
    			if (Utility.checkPatientInfoExists(pid.getText())) {
    				if (Utility.checkPatientReportExists(pid.getText())) {
    				scene = new Scene(getPatientReport(pid.getText().trim()), 400,300);
    				stage.setScene(scene);
    				} else {
    					bufSpace.setText("Patient report does not exist!");
    				}
    			} else {
    				bufSpace.setText("Enter a valid patient ID");
    			}
    		}
    	});
        return scene;
    }
    
    public BorderPane getPatientReport (String patientID) {
    	BorderPane root = new BorderPane();
        GridPane form = new GridPane();

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        // Patient ID
        Text patientGreet = new Text("Hello patient");  
        
        String[] reportData = new String[6];
        
        try {
        	reportData = Utility.getPatientReportData(patientID);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // The total Agatston CAC score
        Text totalAgatstonText = new Text("The total Agatston CAC score: ");
        Text totalAgatstonField = new Text(reportData[0]);
        form.add(totalAgatstonText, 0, 0);
        form.add(totalAgatstonField, 1, 0);
        
        GridPane miniform = new GridPane();
        
        miniform.setHgap(10);
        miniform.setVgap(10);
        
       
        
        //miniform elements
        //lm
        Text lmLabel = new Text("LM: ");
        Text lmField = new Text(reportData[1]);
        
        //lad
        Text ladLabel = new Text("LAD: ");
        Text ladField = new Text(reportData[2]);
        
        //lcx
        Text lcxLabel = new Text("LCX: ");
        Text lcxField = new Text(reportData[3]);
        
        //rca
        Text rcaLabel = new Text("RCA: ");
        Text rcaField = new Text(reportData[4]);
        
        //pda
        Text pdaLabel = new Text("PDA: ");
        Text pdaField = new Text(reportData[5]);
        
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
        
        form.add(miniform, 0,2);
        
        //root alignment
        BorderPane.setAlignment(patientGreet, Pos.CENTER);
        root.setPadding(new Insets(30,10,10,10));

        // Add form to the root
        root.setTop(patientGreet);
        root.setCenter(form);
        
        return root;
    	
    }
}