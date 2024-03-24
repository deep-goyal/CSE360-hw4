package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class hw4 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            VBox vbox = new VBox();
            
            Button patientViewButton = new Button("Patient View");
            Button nurseViewButton = new Button("Receptionist View");
            Button technicianViewButton = new Button("Technician View");
            
            patientViewButton.setOnAction(e -> primaryStage.setScene(new Patient().getScene()));
            nurseViewButton.setOnAction(e -> primaryStage.setScene(new Receptionist().getScene()));
            technicianViewButton.setOnAction(e -> primaryStage.setScene(new Technician().getScene()));
            
            vbox.getChildren().addAll(patientViewButton, nurseViewButton, technicianViewButton);
            root.setCenter(vbox);
            
            Scene scene = new Scene(root,400,400);
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
