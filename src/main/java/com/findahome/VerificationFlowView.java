package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Circle;

public class VerificationFlowView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private VBox content;

    public VerificationFlowView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        content = new VBox(20);
        content.setPadding(new Insets(20));
        VBox.setVgrow(content, Priority.ALWAYS);

        showIntro();
        getChildren().add(content);
    }

    private void showIntro() {
        content.getChildren().clear();
        content.setSpacing(30);
        content.setAlignment(Pos.CENTER);

        // Header placeholder
        VBox hero = new VBox(15);
        hero.setAlignment(Pos.CENTER);
        hero.setPrefHeight(200);
        hero.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 20;");
        Label lock = new Label("\ud83d\udee1");
        lock.setStyle("-fx-font-size: 50;");
        hero.getChildren().add(lock);

        Label title = new Label("Boost Your Property Business");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        title.setWrapText(true);
        title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label sub = new Label("Verified agents receive up to 3x more leads.");
        sub.setTextFill(Color.GRAY);
        sub.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        VBox points = new VBox(15);
        points.getChildren().addAll(
                createPoint("Trust Badge", "Gain instant credibility"),
                createPoint("Higher Visibility", "Appear at the top of results"));

        Button start = new Button("Get Started");
        start.setMaxWidth(Double.MAX_VALUE);
        start.setPrefHeight(50);
        start.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: #102216; -fx-background-radius: 12; -fx-font-weight: bold;");
        start.setOnAction(e -> showUpload());

        content.getChildren().addAll(hero, title, sub, points, start);
    }

    private void showUpload() {
        content.getChildren().clear();
        content.setSpacing(20);
        content.setAlignment(Pos.TOP_LEFT);

        Label step = new Label("Step 1/2: Document Upload");
        step.setTextFill(Color.web(PRIMARY));

        Label title = new Label("Upload ID and License");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 22));

        VBox form = new VBox(15);
        form.getChildren().addAll(
                createField("ID/Passport Number", "e.g. 12345678"),
                new Label("ID Card (Front)"),
                createUploadBox(),
                createField("License Number", "RE-19283746"));

        Button cont = new Button("Continue");
        cont.setMaxWidth(Double.MAX_VALUE);
        cont.setPrefHeight(50);
        cont.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: #102216; -fx-background-radius: 12; -fx-font-weight: bold;");
        cont.setOnAction(e -> showLiveness());

        content.getChildren().addAll(step, title, form, cont);
    }

    private void showLiveness() {
        content.getChildren().clear();
        content.setSpacing(20);
        content.setAlignment(Pos.CENTER);

        Label title = new Label("Face Liveness Check");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 22));

        StackPane cam = new StackPane();
        cam.setPrefSize(280, 350);
        cam.setStyle("-fx-background-color: #000; -fx-background-radius: 140; -fx-border-color: " + PRIMARY
                + "; -fx-border-radius: 140; -fx-border-width: 4;");
        Label icon = new Label("\ud83d\udcf7");
        icon.setStyle("-fx-font-size: 40;");
        cam.getChildren().add(icon);

        Label inst = new Label("Slowly turn your head to the right");
        inst.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-text-fill: " + PRIMARY
                + "; -fx-padding: 10 20; -fx-background-radius: 20; -fx-font-weight: bold;");

        Button complete = new Button("\ud83d\udcf7 Capture");
        complete.setPrefSize(80, 80);
        complete.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 40; -fx-font-size: 14;");
        complete.setOnAction(e -> MainApp.navigateTo(new SuccessView()));

        content.getChildren().addAll(title, cam, inst, complete);
    }

    private HBox createPoint(String t, String s) {
        HBox p = new HBox(10);
        Circle dot = new Circle(4, Color.web(PRIMARY));
        VBox v = new VBox(2);
        Label tl = new Label(t);
        tl.setTextFill(Color.WHITE);
        tl.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label sl = new Label(s);
        sl.setTextFill(Color.GRAY);
        sl.setFont(Font.font(10));
        v.getChildren().addAll(tl, sl);
        p.getChildren().addAll(new StackPane(dot), v);
        return p;
    }

    private VBox createField(String l, String p) {
        VBox v = new VBox(5);
        Label lbl = new Label(l);
        lbl.setTextFill(Color.WHITE);
        TextField tf = new TextField();
        tf.setPromptText(p);
        tf.setStyle(
                "-fx-background-color: #1c271f; -fx-text-fill: white; -fx-background-radius: 8; -fx-pref-height: 45;");
        v.getChildren().addAll(lbl, tf);
        return v;
    }

    private StackPane createUploadBox() {
        StackPane sp = new StackPane();
        sp.setPrefHeight(100);
        sp.setStyle(
                "-fx-background-color: #1c271f; -fx-border-color: #3b5443; -fx-border-style: dashed; -fx-border-radius: 12; -fx-background-radius: 12;");
        sp.getChildren().add(new Label("Click to upload"));
        return sp;
    }
}
