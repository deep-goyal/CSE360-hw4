package application;

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

public class Technician {
    private Scene scene;

    public Technician() {
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

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        
        String buttonStyle = "-fx-background-color: rgb(90, 97, 203); "
        		+ "-fx-text-fill: black;"
        		+ "-fx-pref-width: 60px;"
        		+ "-fx-pref-height: 30px;";
        saveButton.setStyle(buttonStyle);
        
        
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        
        root.setPadding(new Insets(10,30,80,30));

        // Add form and button to the root
        root.setCenter(form);
        root.setBottom(buttonBox);

        // Set the scene
        scene = new Scene(root, 500, 380);
    }

    public Scene getScene() {
        return scene;
    }
}
