package com.adex;

import javafx.application.Application;
import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
// import javafx.scene.shape.Sphere;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Sphere sphere = new Sphere();
        // sphere.setRadius(50.0);
        // sphere.setTranslateX(200);
        // sphere.setTranslateY(150);
        // Group root = new Group(sphere);
        // Scene scene = new Scene(root, 600, 300);
        // stage.setTitle("Sphere object");
        Box box = new Box();
        box.setWidth(200.0);
        box.setHeight(400.0);
        box.setDepth(200.0);
        box.setTranslateX(300);
        box.setTranslateY(300);
        PhongMaterial textureMaterial = new PhongMaterial();
        textureMaterial.setDiffuseColor(Color.RED);
        box.setMaterial(textureMaterial);
        Group root = new Group(box);
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Drawing a Box");

        // String javaVersion = System.getProperty("java.version");
        // String javafxVersion = System.getProperty("javafx.version");
        // Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
