package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminReviewDetailView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public AdminReviewDetailView() {
        setSpacing(20);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked(e -> MainApp.navigateTo(new AdminDashboardView()));
        VBox titleBox = new VBox(2);
        Label title = new Label("Agent Review");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label id = new Label("AGENT ID: 8829");
        id.setTextFill(Color.web("#9db9a6"));
        id.setFont(Font.font(10));
        titleBox.getChildren().addAll(title, id);
        header.getChildren().addAll(back, titleBox);

        // Name
        Label name = new Label("Johnathan Doe");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 24));

        // Documents
        Label docTitle = new Label("AGENT DOCUMENTS");
        docTitle.setTextFill(Color.web("#9db9a6"));
        docTitle.setFont(Font.font("System", FontWeight.BOLD, 10));

        HBox docs = new HBox(15);
        docs.getChildren().addAll(createDocView("National ID"), createDocView("RE License"));

        // Liveness Match
        VBox liveness = new VBox(10);
        liveness.setPadding(new Insets(15));
        liveness.setStyle("-fx-background-color: #1c271f; -fx-background-radius: 12;");
        HBox lHead = new HBox();
        Label lTitle = new Label("Liveness Check");
        lTitle.setTextFill(Color.WHITE);
        lTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label lMatch = new Label("98% Match");
        lMatch.setTextFill(Color.web(PRIMARY));
        lMatch.setFont(Font.font("System", FontWeight.BOLD, 14));
        lHead.getChildren().addAll(lTitle, s, lMatch);
        Label lDesc = new Label("Selfie matches ID document biometric data perfectly.");
        lDesc.setTextFill(Color.GRAY);
        lDesc.setFont(Font.font(12));
        liveness.getChildren().addAll(lHead, lDesc);

        // Actions
        HBox actions = new HBox(15);
        Button reject = new Button("Reject Request");
        reject.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(reject, Priority.ALWAYS);
        reject.setStyle(
                "-fx-background-color: rgba(239, 68, 68, 0.1); -fx-text-fill: #ef4444; -fx-border-color: #ef4444; -fx-background-radius: 12; -fx-border-radius: 12; -fx-pref-height: 50;");
        reject.setOnAction(e -> MainApp.navigateTo(new AdminDashboardView()));

        Button approve = new Button("Approve");
        approve.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(approve, Priority.ALWAYS);
        approve.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: #102216; -fx-background-radius: 12; -fx-font-weight: bold; -fx-pref-height: 50;");
        approve.setOnAction(e -> MainApp.navigateTo(new AdminDashboardView()));
        actions.getChildren().addAll(reject, approve);

        getChildren().addAll(header, name, docTitle, docs, liveness, actions);
    }

    private VBox createDocView(String name) {
        VBox box = new VBox(10);
        box.setPrefWidth(180);
        StackPane img = new StackPane(new Label("DOC IMG"));
        img.setPrefHeight(200);
        img.setStyle(
                "-fx-background-color: #161b22; -fx-background-radius: 12; -fx-border-color: #30363d; -fx-border-radius: 12;");
        Label l = new Label(name);
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(12));
        box.getChildren().addAll(img, l);
        return box;
    }
}
