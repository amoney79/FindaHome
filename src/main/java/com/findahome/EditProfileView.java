package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EditProfileView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c222c";
    private static final String PRIMARY = "#137fec";
    private static final String BORDER_COLOR = "#3b4754";
    private static final String TEXT_GRAY = "#9da6b9";

    public EditProfileView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

        Label title = new Label("Edit Profile");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));

        header.getChildren().addAll(backBtn, title);

        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        // Avatar
        StackPane avatarBox = new StackPane();
        Circle c = new Circle(50, Color.web("#333"));
        Label l = new Label("JD");
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(24));
        avatarBox.getChildren().addAll(c, l);

        Button changePhoto = new Button("Change Photo");
        changePhoto
                .setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY + "; -fx-font-weight: bold;");

        // Form
        VBox form = new VBox(15);
        form.getChildren().addAll(
                createField("Full Name", "John Doe"),
                createField("Email", "john.doe@example.com"),
                createField("Phone", "+254 712 345 678"),
                createField("Current Location", "Nairobi, Kenya"));

        Button saveBtn = new Button("Save Changes");
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        saveBtn.setPrefHeight(50);
        saveBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        saveBtn.setOnAction(e -> MainApp.navigateTo(new TenantProfileView()));

        content.getChildren().addAll(avatarBox, changePhoto, form, saveBtn);
        scroll.setContent(content);

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private VBox createField(String label, String value) {
        VBox f = new VBox(5);
        Label l = new Label(label);
        l.setTextFill(Color.web(TEXT_GRAY));

        TextField tf = new TextField(value);
        tf.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-radius: 8; -fx-padding: 10;");

        f.getChildren().addAll(l, tf);
        return f;
    }
}
