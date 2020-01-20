package com.adex;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Box;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH = 1500.0;
    private static final double HEIGHT = 800.0;

    private double AnchorX, AnchorY;
    private double AnchorAngleX = 0;
    private double AnchorAngleY = 0;
    private final DoubleProperty AngleX = new SimpleDoubleProperty();
    private final DoubleProperty AngleY = new SimpleDoubleProperty();

    private void InitMouseControl(RotationGroup group, Scene scene, Stage stage) {
        Rotate XRotate, YRotate;
        group.getTransforms().addAll(
                XRotate = new Rotate(0, Rotate.X_AXIS),
                YRotate = new Rotate(0, Rotate.Y_AXIS)
        );

        XRotate.angleProperty().bind(AngleX);
        YRotate.angleProperty().bind(AngleY);

        scene.setOnMousePressed(event -> {
            AnchorX = event.getSceneX();
            AnchorY = event.getSceneY();
            AnchorAngleX = AngleX.get();
            AnchorAngleY = AngleY.get();
            // System.out.println(String.format("%f, %f", event.getSceneX(), event.getSceneY()));
        });

        scene.setOnMouseDragged(event -> {
            AngleX.set(AnchorAngleX - (AnchorY - event.getSceneY()));
            AngleY.set(AnchorAngleY + (AnchorX - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.setTranslateZ(group.getTranslateZ() + delta);
        });
    }

    class RotationGroup extends Group {
        Rotate rotate;
        Transform transform = new Rotate();

        private void RotateX(int angle) {
            rotate = new Rotate(angle, Rotate.X_AXIS);
            transform = transform.createConcatenation(rotate);
            this.getTransforms().clear();
            this.getTransforms().addAll(transform);
        }

        private void RotateY(int angle) {
            rotate = new Rotate(angle, Rotate.Y_AXIS);
            transform = transform.createConcatenation(rotate);
            this.getTransforms().clear();
            this.getTransforms().addAll(transform);
        }

        private void RotateZ(int angle) {
            rotate = new Rotate(angle, Rotate.Z_AXIS);
            transform = transform.createConcatenation(rotate);
            this.getTransforms().clear();
            this.getTransforms().addAll(transform);
        }
    }

    private Box PrepareBox() {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/apple.jpg")));
        material.setSpecularColor(Color.valueOf("#424242")); // Can also use specular map
        Box box = new Box(600, 140, 300);
        box.setMaterial(material);
        return box;
    }

    private Node[] PrepareLightSource() {
//        AmbientLight ambientLight = new AmbientLight();
//        ambientLight.setColor(Color.GREEN);
//        return ambientLight;
        PointLight pointLight = new PointLight();
        pointLight.setColor(Color.WHITE);
        pointLight.getTransforms().add(new Translate(WIDTH / 2, HEIGHT / 2 -50, 400));

        Sphere sphere = new Sphere(20);
        sphere.getTransforms().addAll(pointLight.getTransforms());
        return new Node[]{pointLight, sphere};
    }

    @Override
    public void start(Stage stage) {
        Box box = PrepareBox();
        String title = "Applesque Rectangular Prism";

        RotationGroup group = new RotationGroup();
        group.getChildren().addAll(box);
        Node[] lightArray = PrepareLightSource();
        group.getChildren().addAll(lightArray);

        group.setTranslateX(WIDTH / 2);
        group.setTranslateY(HEIGHT / 2);
        group.setTranslateZ(300);

        Camera camera = new PerspectiveCamera(); // fixedEyeAtCameraZero: true
        /*
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-500);
        camera.setNearClip(1);
        camera.setFarClip(1);
         */

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.WHITE);
        scene.setCamera(camera);

        InitMouseControl(group, scene, stage);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    group.setTranslateZ(group.getTranslateZ() + 150);
                    break;
                case S:
                    group.setTranslateZ(group.getTranslateZ() - 150);
                    break;
                case D:
                    group.RotateX(10);
                    break;
                case A:
                    group.RotateX(-10);
                    break;
                case Q:
                    group.RotateY(10);
                    break;
                case E:
                    group.RotateY(-10);
                    break;
                case J:
                    lightArray[0].setTranslateX(lightArray[0].getTranslateX() + 10);
                    lightArray[1].setTranslateX(lightArray[1].getTranslateX() + 10);
                    break;
                case K:
                    lightArray[0].setTranslateX(lightArray[0].getTranslateX() - 10);
                    lightArray[1].setTranslateX(lightArray[1].getTranslateX() - 10);
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
