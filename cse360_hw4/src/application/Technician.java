package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Technician { // Replace ViewName with PatientView, NurseView, TechnicianView
    private Scene scene;

    public Technician() {
        BorderPane root = new BorderPane();
        Text viewName = new Text("Technician View"); // Replace "View Name" with "Patient View", etc.
        root.setCenter(viewName);
        scene = new Scene(root, 400, 400);
    }

    public Scene getScene() {
        return scene;
    }
}