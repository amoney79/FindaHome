package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LegalTermsView extends BorderPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String TEXT_GRAY = "#9db9a6";

        public LegalTermsView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "f0; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateCached("profile", TenantProfileView::new));

                Label title = new Label("Legal Terms");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                HBox.setHgrow(title, Priority.ALWAYS);

                Button downloadIconBtn = new Button("\u2913"); // Download arrow icon
                downloadIconBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-size: 18; -fx-padding: 0; -fx-cursor: hand;");

                header.getChildren().addAll(backBtn, title, downloadIconBtn);

                // Progress Bar container (Top of Center)
                Region progressBar = new Region();
                progressBar.setPrefHeight(2);
                progressBar.setStyle("-fx-background-color: " + PRIMARY + ";");
                progressBar.setMaxWidth(Double.MAX_VALUE);

                // Scroll Content
                VBox scrollContent = new VBox(25);
                scrollContent.setPadding(new Insets(30, 20, 30, 20));
                scrollContent.setAlignment(Pos.TOP_LEFT);

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                VBox centerContainer = new VBox(progressBar, scroll);
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Headline
                VBox headlineSect = new VBox(10);
                Label mainTitle = new Label("Terms of Service and Privacy Policy");
                mainTitle.setTextFill(Color.WHITE);
                mainTitle.setFont(Font.font("System", FontWeight.BOLD, 24));
                mainTitle.setWrapText(true);

                VBox metaSect = new VBox(5);
                Label lastUpdated = new Label("Last Updated: October 24, 2023");
                lastUpdated.setTextFill(Color.web(TEXT_GRAY));
                lastUpdated.setFont(Font.font(14));

                HBox pdfLink = new HBox(6);
                pdfLink.setAlignment(Pos.CENTER_LEFT);
                Label pdfIcon = new Label("\ud83d\udcc4");
                pdfIcon.setTextFill(Color.web(PRIMARY));
                Label pdfText = new Label("Download PDF Version");
                pdfText.setTextFill(Color.web(PRIMARY));
                pdfText.setFont(Font.font("System", FontWeight.BOLD, 14));
                pdfLink.setCursor(javafx.scene.Cursor.HAND);
                pdfLink.getChildren().addAll(pdfIcon, pdfText);

                metaSect.getChildren().addAll(lastUpdated, pdfLink);
                metaSect.setPadding(new Insets(0, 0, 15, 0));
                metaSect.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

                headlineSect.getChildren().addAll(mainTitle, metaSect);

                // Sections
                scrollContent.getChildren().add(headlineSect);

                VBox sectionsContainer = new VBox(15);
                sectionsContainer.getChildren().addAll(
                                createModernLegalSect("1. Introduction",
                                                "Welcome to FindaHome. By using our property marketplace, you agree to these terms. Please read them carefully to understand your rights and obligations as a buyer, seller, or agent within our ecosystem."),
                                createModernLegalSect("2. User Eligibility",
                                                "To use FindaHome, you must be at least 18 years old and capable of forming a binding contract. You agree to provide accurate, current, and complete information."),
                                createModernLegalSect("3. Property Listings",
                                                "Sellers and agents are responsible for the accuracy of their listings. FindaHome reserves the right to remove any listing that violates our community standards."),
                                createModernLegalSect("4. Privacy & Data",
                                                "Your privacy is important to us. We collect personal information to provide and improve our services."),
                                createModernLegalSect("5. Limitation of Liability",
                                                "FindaHome shall not be liable for any indirect, incidental, special, consequential or punitive damages."),
                                createModernLegalSect("6. Termination",
                                                "We may terminate or suspend your access to our services immediately, without prior notice or liability, for any reason."));

                scrollContent.getChildren().add(sectionsContainer);

                Label endText = new Label("End of document. Please accept to continue.");
                endText.setTextFill(Color.web(TEXT_GRAY));
                endText.setFont(Font.font("System", FontWeight.NORMAL, 12));
                endText.setPadding(new Insets(20, 0, 20, 0));
                endText.setAlignment(Pos.CENTER);
                endText.setMaxWidth(Double.MAX_VALUE);
                scrollContent.getChildren().add(endText);

                // Footer
                VBox footer = new VBox(15);
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");

                HBox agreementRow = new HBox(12);
                agreementRow.setAlignment(Pos.TOP_LEFT);
                CheckBox agreeCheck = new CheckBox();
                agreeCheck.setSelected(true);
                agreeCheck.setDisable(true); // As per design
                agreeCheck.setStyle("-fx-base: " + PRIMARY + ";");

                Label agreeText = new Label("I have read and agree to the Terms of Service and Privacy Policy.");
                agreeText.setTextFill(Color.web("#9ca3af"));
                agreeText.setFont(Font.font(13));
                agreeText.setWrapText(true);
                HBox.setHgrow(agreeText, Priority.ALWAYS);
                agreementRow.getChildren().addAll(agreeCheck, agreeText);

                Button acceptBtn = new Button("Accept & Continue");
                acceptBtn.setGraphic(new Label("\u2192"));
                acceptBtn.setContentDisplay(ContentDisplay.RIGHT);
                acceptBtn.setMaxWidth(Double.MAX_VALUE);
                acceptBtn.setPrefHeight(56);
                acceptBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.2), 10, 0, 0, 5);");
                acceptBtn.setOnAction(e -> MainApp.showHome());

                Button declineBtn = new Button("I do not accept");
                declineBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXT_GRAY
                                + "; -fx-font-weight: bold; -fx-font-size: 14; -fx-cursor: hand;");
                declineBtn.setMaxWidth(Double.MAX_VALUE);
                declineBtn.setAlignment(Pos.CENTER);
                declineBtn.setOnAction(e -> MainApp.navigateCached("profile", TenantProfileView::new));

                footer.getChildren().addAll(agreementRow, acceptBtn, declineBtn);

                setTop(header);
                setCenter(centerContainer);
                setBottom(footer);
        }

        private VBox createModernLegalSect(String title, String content) {
                VBox card = new VBox(10);
                card.setPadding(new Insets(20));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05);");

                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 16));

                Label c = new Label(content);
                c.setTextFill(Color.web("#d1d5db"));
                c.setFont(Font.font(14));
                c.setWrapText(true);
                c.setLineSpacing(4);

                card.getChildren().addAll(t, c);
                return card;
        }
}
