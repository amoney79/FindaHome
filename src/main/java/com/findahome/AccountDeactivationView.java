package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountDeactivationView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String TEXT_GRAY = "#9db9a6";

        public AccountDeactivationView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Decorative background glow
                Circle glow = new Circle(128, Color.web(PRIMARY, 0.05));
                glow.setEffect(new GaussianBlur(100));
                StackPane.setAlignment(glow, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(glow, new Insets(0, -100, -100, 0));

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Top App Bar
                HBox topBar = new HBox(15);
                topBar.setAlignment(Pos.CENTER_LEFT);
                topBar.setPadding(new Insets(15));
                topBar.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

                Label title = new Label("Account");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                HBox.setHgrow(title, Priority.ALWAYS);

                topBar.getChildren().addAll(backBtn, title);

                // Content
                VBox content = new VBox(0);
                content.setAlignment(Pos.TOP_CENTER);
                VBox.setVgrow(content, Priority.ALWAYS);

                // Headline Section
                VBox headlineSect = new VBox(24);
                headlineSect.setAlignment(Pos.CENTER);
                headlineSect.setPadding(new Insets(32, 20, 16, 20));

                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(80, 80);
                iconCircle.setMaxSize(80, 80);
                iconCircle.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 40;");
                Label sadIcon = new Label("\u263b"); // Sentiment dissatisfied
                sadIcon.setTextFill(Color.web(PRIMARY));
                sadIcon.setStyle("-fx-font-size: 48;");
                iconCircle.getChildren().add(sadIcon);

                Label h1 = new Label("We're sorry to see you go");
                h1.setTextFill(Color.WHITE);
                h1.setFont(Font.font("System", FontWeight.BOLD, 28));
                h1.setWrapText(true);
                h1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                Label p = new Label(
                                "Deactivating your account means you will lose access to your property profile and history. This action cannot be undone easily.");
                p.setTextFill(Color.web(TEXT_GRAY));
                p.setFont(Font.font(16));
                p.setWrapText(true);
                p.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                VBox.setMargin(p, new Insets(0, 20, 0, 20));

                headlineSect.getChildren().addAll(iconCircle, h1, p);

                // List Section Header
                VBox listHeader = new VBox(15);
                listHeader.setPadding(new Insets(10, 20, 10, 20));
                Label sectionTitle = new Label("What you'll lose");
                sectionTitle.setTextFill(Color.WHITE);
                sectionTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region divider = new Region();
                divider.setPrefHeight(1);
                divider.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
                listHeader.getChildren().addAll(sectionTitle, divider);

                // Consequences List
                VBox consequences = new VBox(5);
                consequences.setPadding(new Insets(10, 10, 0, 10));
                consequences.getChildren().addAll(
                                createConsequenceItem("\u2661", "Saved Homes",
                                                "Your list of 15+ favorite properties will be deleted."),
                                createConsequenceItem("\ud83d\udcac", "Chat History",
                                                "All conversations with landlords and agents will be lost."),
                                createConsequenceItem("\ud83d\udcc4", "Active Applications",
                                                "Pending applications for rentals will be cancelled."));

                content.getChildren().addAll(headlineSect, listHeader, consequences);

                // Footer Actions
                VBox footer = new VBox(15);
                footer.setPadding(new Insets(20, 20, 48, 20));

                Button keepBtn = new Button("Keep My Account");
                keepBtn.setMaxWidth(Double.MAX_VALUE);
                keepBtn.setPrefHeight(56);
                keepBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");
                keepBtn.setOnAction(e -> MainApp.navigateTo(new TenantProfileView()));

                Button deactivateBtn = new Button("Continue to Deactivate");
                deactivateBtn.setMaxWidth(Double.MAX_VALUE);
                deactivateBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-font-weight: bold; -fx-font-size: 14; -fx-cursor: hand;");
                deactivateBtn.setOnAction(e -> MainApp.navigateTo(new DeactivationSurveyView()));
                deactivateBtn.setOnMouseEntered(e -> deactivateBtn.setTextFill(Color.web("#ef4444")));
                deactivateBtn.setOnMouseExited(e -> deactivateBtn.setTextFill(Color.web("#94a3b8")));

                footer.getChildren().addAll(keepBtn, deactivateBtn);

                layout.getChildren().addAll(topBar, content, footer);
                getChildren().addAll(glow, layout);
        }

        private HBox createConsequenceItem(String icon, String title, String desc) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12, 16, 12, 16));
                row.setStyle("-fx-background-color: transparent; -fx-background-radius: 12;");

                row.setOnMouseEntered(
                                e -> row.setStyle(
                                                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12;"));
                row.setOnMouseExited(
                                e -> row.setStyle("-fx-background-color: transparent; -fx-background-radius: 12;"));

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(48, 48);
                iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.2); -fx-background-radius: 8;");
                Label i = new Label(icon);
                i.setTextFill(Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 20;");
                iconBox.getChildren().add(i);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.MEDIUM, 16));
                Label d = new Label(desc);
                d.setTextFill(Color.web(TEXT_GRAY));
                d.setFont(Font.font(14));
                d.setWrapText(true);
                text.getChildren().addAll(t, d);
                HBox.setHgrow(text, Priority.ALWAYS);

                row.getChildren().addAll(iconBox, text);
                return row;
        }
}
