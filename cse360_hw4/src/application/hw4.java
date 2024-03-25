package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class hw4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	//root init
            BorderPane root = new BorderPane();
            
            //welcome text
            Text welcome = new Text("Welcome to Heart Health Imaging and Recording System");
            
            //button defs
            VBox vbox = new VBox();
            Button patientViewButton = new Button("Patient View");
            Button receptionistViewButton = new Button("Patient Intake");
            Button technicianViewButton = new Button("CT Scan Tech View");
            
            //button event handlers
            patientViewButton.setOnAction(e -> primaryStage.setScene(new Patient().getScene()));
            receptionistViewButton.setOnAction(e -> primaryStage.setScene(new Receptionist().getScene()));
            technicianViewButton.setOnAction(e -> primaryStage.setScene(new Technician().getScene()));
            
            vbox.getChildren().addAll(receptionistViewButton, technicianViewButton, patientViewButton);
            
            //add elements to the borderpane
            root.setTop(welcome);
            root.setCenter(vbox);
            
            //button styles
            String buttonStyle = "-fx-background-color: rgb(90, 97, 203); -fx-text-fill: black; -fx-pref-width: 150px; -fx-pref-height: 40px";
            patientViewButton.setStyle(buttonStyle);
            receptionistViewButton.setStyle(buttonStyle);
            technicianViewButton.setStyle(buttonStyle);
            
            //vbox alignments
            vbox.setSpacing(20);
            vbox.setAlignment(Pos.CENTER);
            
            //welcome styles
            welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
            
            //borderpane alignment
            root.setPadding(new Insets(40));
            BorderPane.setAlignment(vbox, Pos.CENTER);
            BorderPane.setAlignment(welcome, Pos.CENTER);
            
            //set scene
            Scene scene = new Scene(root,500,320);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
