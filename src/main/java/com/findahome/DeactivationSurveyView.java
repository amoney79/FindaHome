package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DeactivationSurveyView extends BorderPane {

        private static final String BACKGROUND_DARK = "#121212";
        private static final String CARD_BG = "#1e1e1e";
        private static final String PRIMARY_RED = "#ec1313";
        private static final String TEXT_GRAY = "#94a3b8";
        private static final String BORDER_DARK = "#333333";

        public DeactivationSurveyView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc; -fx-border-color: " + BORDER_DARK
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039"); // arrow_back_ios
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 24; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateCached("deactivate", AccountDeactivationView::new));

                Label title = new Label("Deactivate Account");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 16));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);
                HBox.setMargin(title, new Insets(0, 40, 0, 0)); // Center offset

                header.getChildren().addAll(backBtn, title);

                // Scroll Content
                VBox scrollContent = new VBox(25);
                scrollContent.setAlignment(Pos.TOP_LEFT);
                scrollContent.setPadding(new Insets(0, 20, 20, 20));

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                // Headline
                VBox headline = new VBox(8);
                headline.setPadding(new Insets(32, 0, 10, 0));
                Label h1 = new Label("Why are you leaving?");
                h1.setTextFill(Color.WHITE);
                h1.setFont(Font.font("System", FontWeight.BOLD, 28));
                Label sub = new Label("We're sorry to see you go. Help us improve FindaHome by telling us why.");
                sub.setTextFill(Color.web(TEXT_GRAY));
                sub.setFont(Font.font(14));
                sub.setWrapText(true);
                headline.getChildren().addAll(h1, sub);

                // Group 1: Primary Reason
                VBox primaryReasonSect = new VBox(12);
                Label primaryTitle = new Label("What's the main reason?");
                primaryTitle.setTextFill(Color.WHITE);
                primaryTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

                ToggleGroup group = new ToggleGroup();
                VBox reasons = new VBox(10);
                reasons.getChildren().addAll(
                                createReasonOption("Found a home elsewhere", group, true),
                                createReasonOption("App is hard to use", group, false),
                                createReasonOption("Privacy concerns", group, false),
                                createReasonOption("Too many notifications", group, false));
                primaryReasonSect.getChildren().addAll(primaryTitle, reasons);

                // Group 2: Feedback
                VBox feedbackSect = new VBox(10);
                Label feedbackTitle = new Label("How can we improve?");
                feedbackTitle.setTextFill(Color.WHITE);
                feedbackTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

                TextArea commentsArea = new TextArea();
                commentsArea.setPromptText("Share your experience or suggestions...");
                commentsArea.setPrefHeight(120);
                commentsArea.setWrapText(true);
                commentsArea.setStyle("-fx-control-inner-background: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-border-color: " + BORDER_DARK
                                + "; -fx-border-radius: 12;");

                feedbackSect.getChildren().addAll(feedbackTitle, commentsArea);

                scrollContent.getChildren().addAll(headline, primaryReasonSect, feedbackSect);

                // Footer
                VBox footer = new VBox(20);
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_DARK
                                + "; -fx-border-width: 1 0 0 0;");

                HBox warningBox = new HBox(10);
                warningBox.setAlignment(Pos.TOP_LEFT);
                Label warnIcon = new Label("\u26a0\ufe0f"); // Warning icon
                warnIcon.setTextFill(Color.web(PRIMARY_RED));
                Label warnText = new Label(
                                "This action is permanent and cannot be undone. All your saved listings and message history will be deleted.");
                warnText.setTextFill(Color.web(TEXT_GRAY));
                warnText.setFont(Font.font(12));
                warnText.setWrapText(true);
                HBox.setHgrow(warnText, Priority.ALWAYS);
                warningBox.getChildren().addAll(warnIcon, warnText);

                Button finalBtn = new Button("Deactivate Account");
                finalBtn.setMaxWidth(Double.MAX_VALUE);
                finalBtn.setPrefHeight(56);
                finalBtn.setStyle("-fx-background-color: " + PRIMARY_RED
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                finalBtn.setOnAction(e -> MainApp.showHome());

                footer.getChildren().addAll(warningBox, finalBtn);

                setTop(header);
                setCenter(scroll);
                setBottom(footer);
        }

        private HBox createReasonOption(String text, ToggleGroup group, boolean selected) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(15));
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_DARK + "; -fx-border-radius: 12; -fx-cursor: hand;");

                Label lbl = new Label(text);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                HBox.setHgrow(lbl, Priority.ALWAYS);

                RadioButton rb = new RadioButton();
                rb.setToggleGroup(group);
                rb.setSelected(selected);

                row.getChildren().addAll(lbl, rb);
                row.setOnMouseClicked(e -> rb.setSelected(true));

                rb.selectedProperty().addListener((obs, oldVal, newVal) -> {
                        if (newVal) {
                                row.setStyle("-fx-background-color: rgba(236, 19, 19, 0.05); -fx-background-radius: 12; -fx-border-color: "
                                                + PRIMARY_RED + "; -fx-border-radius: 12;");
                        } else {
                                row.setStyle("-fx-background-color: " + CARD_BG
                                                + "; -fx-background-radius: 12; -fx-border-color: "
                                                + BORDER_DARK + "; -fx-border-radius: 12;");
                        }
                });

                if (selected) {
                        row.setStyle("-fx-background-color: rgba(236, 19, 19, 0.05); -fx-background-radius: 12; -fx-border-color: "
                                        + PRIMARY_RED + "; -fx-border-radius: 12;");
                }

                return row;
        }
}
