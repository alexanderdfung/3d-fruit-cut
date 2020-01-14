package com.adex;

import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH = 1500.0;
    private static final double HEIGHT = 800.0;

    @Override
    public void start(Stage stage) {
        Box box = new Box();
        box.setWidth(100.0);
        box.setHeight(300.0);
        box.setDepth(500.0);

        Sphere sphere = new Sphere(150.0);

        // Box shape = box; String title = "Moving Box";
        Sphere shape = sphere; String title = "Moving Sphere";

        PhongMaterial textureMaterial = new PhongMaterial();
        textureMaterial.setDiffuseColor(Color.LIGHTBLUE);
        shape.setMaterial(textureMaterial);

        Group group = new Group();
        group.getChildren().addAll(shape);

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.WHITE);
        scene.setCamera(new PerspectiveCamera());

        shape.setTranslateX(WIDTH / 2.0);
        shape.setTranslateY(HEIGHT / 2.0);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    shape.setTranslateZ(shape.getTranslateZ() + 150.0);
                    break;
                case S:
                    shape.setTranslateZ(shape.getTranslateZ() - 150.0);
                    break;
            }
        });

        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
