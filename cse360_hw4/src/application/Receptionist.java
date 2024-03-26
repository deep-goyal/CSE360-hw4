package application;

import java.io.IOException;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Receptionist { 
    private Scene scene;

    public Scene ReceptionistView(Stage stage, Scene mainScene) {
        BorderPane root = new BorderPane();
        
        //title
        Text title = new Text("Patient Intake Form");
        
        //title styles
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));

        //form elements
        GridPane form = new GridPane();
        
        //first name
        Text fnameText = new Text("First Name: ");
        TextField fnameField = new TextField("");
      
        //last name
        Text lnameText = new Text("Last Name: ");
        TextField lnameField = new TextField("");
        
        //email
        Text emailText = new Text("Email: ");
        TextField emailField = new TextField("");
        
        //phone number
        Text pnumText = new Text("Phone Number: ");
        TextField pnumField = new TextField("");
        
        //health history
        Text hhistText = new Text("Health History: ");
        TextField hhistField = new TextField("");
        
        //insurance id
        Text iidText = new Text("Insurance ID: ");
        TextField iidField = new TextField("");
        
        //save button
        Button saveButton = new Button("Save");
        
        //error space
        Text bufSpace = new Text("");
        
        //save button event handler
		saveButton.setOnAction(e -> {
			//check for empty fields
			if (fnameField.getText().trim().isEmpty()) {
				bufSpace.setText("First Name Field is Empty");
			} else if (lnameField.getText().trim().isEmpty()) {
				bufSpace.setText("Last Name Field is Empty");
			} else if (emailField.getText().trim().isEmpty()) {
				bufSpace.setText("Email Field is Empty");
			} else if (pnumField.getText().trim().isEmpty()) {
				bufSpace.setText("Phone Number is Missing");
			} else if (hhistField.getText().trim().isEmpty()) {
				bufSpace.setText("Health history field empty. If the patient does not have a history, enter NONE");
			} else if (iidField.getText().trim().isEmpty()) {
				bufSpace.setText("Insurance ID missing");
			} else {
				String id = Utility.createUniquePatientID();
				bufSpace.setText("Successful! Patient ID: " + id);
				
				//capture all data
				String FNAME = fnameField.getText().trim();
				String LNAME = lnameField.getText().trim();
				String EMAIL = emailField.getText().trim();
				String PNUM = pnumField.getText().trim();
				String HHIST = hhistField.getText().trim();
				String IID = iidField.getText().trim();
				
				String SEPARATOR = "\\";
				
				String dataOnFile = FNAME + SEPARATOR + LNAME + SEPARATOR + EMAIL + SEPARATOR + PNUM + SEPARATOR + HHIST + SEPARATOR + IID;
				
				try {
					Utility.writePatientInfoToFile(id, dataOnFile);
					// Delay for a few seconds before switching scenes
			        PauseTransition delay = new PauseTransition(Duration.millis(3000));
			        delay.setOnFinished(event -> stage.setScene(mainScene));
			        delay.play();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        });
       
        //button styles
        String buttonStyle = "-fx-background-color: rgb(90, 97, 203); "
        		+ "-fx-text-fill: black;"
        		+ "-fx-pref-width: 60px;"
        		+ "-fx-pref-height: 30px;";
        saveButton.setStyle(buttonStyle);
        
        form.setHgap(10); // Horizontal gap between columns
        form.setVgap(10); // Vertical gap between rows
        form.setAlignment(Pos.CENTER);

        // Constraints to align right
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS); 
        form.getColumnConstraints().addAll(column1, column2); 

        // Adding the labels and text fields to the grid
        form.add(fnameText, 0, 0);
        form.add(fnameField, 1, 0);
        form.add(lnameText, 0, 1);
        form.add(lnameField, 1, 1);
        form.add(emailText, 0, 2);
        form.add(emailField, 1, 2);
        form.add(pnumText, 0, 3);
        form.add(pnumField, 1, 3);
        form.add(hhistText, 0, 4);
        form.add(hhistField, 1, 4);
        form.add(iidText, 0, 5);
        form.add(iidField, 1, 5);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        form.add(saveButton, 1, 6);
        form.add(bufSpace, 0, 7);

        // form alignments
        double fieldWidth = 200; 
        fnameField.setMaxWidth(fieldWidth);
        lnameField.setMaxWidth(fieldWidth);
        emailField.setMaxWidth(fieldWidth);
        pnumField.setMaxWidth(fieldWidth);
        hhistField.setMaxWidth(fieldWidth);
        iidField.setMaxWidth(fieldWidth);
        form.setPadding(new Insets(20));
       
        //root alignments
        BorderPane.setAlignment(title, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(form, Pos.CENTER);
        root.setPadding(new Insets(40,50,20,50));
        
        //add nodes to root
        root.setTop(title);
        root.setCenter(form);
        
        //add root to a scene
        scene = new Scene(root, 500, 350);
        return scene;
    }

  
}