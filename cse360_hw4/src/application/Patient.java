package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Patient { 
    private Scene scene;

    public Patient() {
        BorderPane root = new BorderPane();
        Text viewName = new Text("Patient View"); 
        root.setCenter(viewName);
        scene = new Scene(root, 400, 400);
    }

    public Scene getScene() {
        return scene;
    }
}