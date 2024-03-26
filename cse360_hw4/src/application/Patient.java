package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Patient { 
    private Scene scene;

    public Patient() {
    	BorderPane root = new BorderPane();
        GridPane form = new GridPane();

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        // Patient ID
        Text patientGreet = new Text("Hello patient");    

        // The total Agatston CAC score
        Text totalAgatstonText = new Text("The total Agatston CAC score: ");
        Text totalAgatstonField = new Text("123");
        form.add(totalAgatstonText, 0, 0);
        form.add(totalAgatstonField, 1, 0);
        
        GridPane miniform = new GridPane();
        
        miniform.setHgap(10);
        miniform.setVgap(10);
        
        //miniform elements
        //lm
        Text lmLabel = new Text("LM: ");
        Text lmField = new Text("lm val");
        
        //lad
        Text ladLabel = new Text("LAD: ");
        Text ladField = new Text("lad val");
        
        //lcx
        Text lcxLabel = new Text("LCX: ");
        Text lcxField = new Text("lcx val");
        
        //rca
        Text rcaLabel = new Text("RCA: ");
        Text rcaField = new Text("rca val");
        
        //pda
        Text pdaLabel = new Text("PDA: ");
        Text pdaField = new Text("pda val");
        
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

        // Set the scene
        scene = new Scene(root, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}