package com.adex;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH = 1500.0;
    private static final double HEIGHT = 800.0;

    @Override
    public void start(Stage stage) {
        Box box = new Box(100, 300, 20);
        Sphere sphere = new Sphere(150);

        Box shape = box; String title = "Moving Box";
        // Sphere shape = sphere; String title = "Moving Sphere";

        Transform transform = new Rotate(65, new Point3D(0, 1, 0));
        shape.getTransforms().add(transform);

        class RotationGroup extends Group {
            Rotate rotate;
            Transform transform = new Rotate();

            private void RotateX (int angle) {
                rotate = new Rotate(angle, Rotate.X_AXIS);
                transform = transform.createConcatenation(rotate);
                this.getTransforms().clear();
                this.getTransforms().addAll(transform);
            }

            private void RotateY (int angle) {
                rotate = new Rotate(angle, Rotate.Y_AXIS);
                transform = transform.createConcatenation(rotate);
                this.getTransforms().clear();
                this.getTransforms().addAll(transform);
            }

            private void RotateZ (int angle) {
                rotate = new Rotate(angle, Rotate.Z_AXIS);
                transform = transform.createConcatenation(rotate);
                this.getTransforms().clear();
                this.getTransforms().addAll(transform);
            }
        }

        PhongMaterial textureMaterial = new PhongMaterial();
        textureMaterial.setDiffuseColor(Color.LIGHTBLUE);
        shape.setMaterial(textureMaterial);
        shape.setTranslateX(0);
        shape.setTranslateY(0);
        shape.setTranslateZ(300);

        RotationGroup group = new RotationGroup();
        group.getChildren().addAll(shape);

        Camera camera = new PerspectiveCamera(true);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-500);
        camera.setNearClip(1);
        camera.setFarClip(1);

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.WHITE);
        scene.setCamera(camera);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    camera.setTranslateZ(camera.getTranslateZ() + 150);
                    break;
                case S:
                    camera.setTranslateZ(camera.getTranslateZ() - 150);
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
