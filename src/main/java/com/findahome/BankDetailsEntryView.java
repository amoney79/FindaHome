package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BankDetailsEntryView extends BorderPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String TEXT_GRAY = "#9db9a6";
        private static final String INPUT_BG = "#1c271f";
        private static final String BORDER_COLOR = "#3b5443";

        public BankDetailsEntryView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Top Navigation
                HBox topNav = new HBox(15);
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPadding(new Insets(15, 20, 15, 20));
                topNav.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

                Label backIcon = new Label("\u2039");
                backIcon.setTextFill(Color.WHITE);
                backIcon.setStyle("-fx-font-size: 28; -fx-cursor: hand;");

                Label backText = new Label("Back");
                backText.setTextFill(Color.WHITE);
                backText.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                HBox backGroup = new HBox(5, backIcon, backText);
                backGroup.setAlignment(Pos.CENTER_LEFT);
                backGroup.setCursor(javafx.scene.Cursor.HAND);
                backGroup.setOnMouseClicked(
                                e -> MainApp.navigateCached("landlord_payout_payout", LinkPayoutMethodView::new));

                Label navTitle = new Label("Enter Bank Details");
                navTitle.setTextFill(Color.WHITE);
                navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                navTitle.setAlignment(Pos.CENTER);
                navTitle.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(navTitle, Priority.ALWAYS);
                HBox.setMargin(navTitle, new Insets(0, 48, 0, 0));

                topNav.getChildren().addAll(backGroup, navTitle);

                // Scroll Content
                VBox scrollContent = new VBox(0);
                scrollContent.setAlignment(Pos.TOP_CENTER);
                scrollContent.setPadding(new Insets(0, 0, 20, 0));

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                // Progress Bar Section
                VBox progressSect = new VBox(8);
                progressSect.setPadding(new Insets(20));

                HBox progressInfo = new HBox();
                Label stepLbl = new Label("Step 1 of 2");
                stepLbl.setTextFill(Color.web(PRIMARY));
                stepLbl.setFont(Font.font("System", FontWeight.BOLD, 14));
                Region ps = new Region();
                HBox.setHgrow(ps, Priority.ALWAYS);
                Label stepDesc = new Label("Personal Info & Bank");
                stepDesc.setTextFill(Color.web(TEXT_GRAY));
                stepDesc.setFont(Font.font(12));
                progressInfo.getChildren().addAll(stepLbl, ps, stepDesc);

                StackPane track = new StackPane();
                track.setPrefHeight(6);
                track.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 3;");
                Region bar = new Region();
                bar.setMaxWidth(Double.MAX_VALUE);
                bar.setPrefWidth(215); // 50%
                bar.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
                StackPane.setAlignment(bar, Pos.CENTER_LEFT);
                track.getChildren().add(bar);

                progressSect.getChildren().addAll(progressInfo, track);

                // Headline
                VBox headline = new VBox(5);
                headline.setPadding(new Insets(10, 20, 10, 20));
                Label h1 = new Label("Financial Setup");
                h1.setTextFill(Color.WHITE);
                h1.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label sub = new Label("Provide your bank account details for secure rent withdrawals.");
                sub.setTextFill(Color.web(TEXT_GRAY));
                sub.setFont(Font.font(14));
                sub.setWrapText(true);
                headline.getChildren().addAll(h1, sub);

                // Form
                VBox form = new VBox(20);
                form.setPadding(new Insets(20));

                form.getChildren().addAll(
                                createFormField("Account Holder Name", "Enter full name", false),
                                createBankDropdown(),
                                createFormField("Account Number", "0000 0000 0000", false),
                                createFormField("SWIFT / IBAN", "Enter code", true));

                // Default Toggle
                HBox toggleRow = new HBox(15);
                toggleRow.setAlignment(Pos.CENTER_LEFT);
                toggleRow.setPadding(new Insets(15));
                toggleRow.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1);");
                VBox.setMargin(toggleRow, new Insets(10, 20, 20, 20));

                VBox toggleText = new VBox(2);
                Label toggleTitle = new Label("Set as Default");
                toggleTitle.setTextFill(Color.WHITE);
                toggleTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label toggleSub = new Label("Use for all current and future properties");
                toggleSub.setTextFill(Color.web(TEXT_GRAY));
                toggleSub.setFont(Font.font(12));
                toggleText.getChildren().addAll(toggleTitle, toggleSub);
                HBox.setHgrow(toggleText, Priority.ALWAYS);

                // iOS Toggle Simulation
                boolean[] isDefault = { true };
                StackPane iosToggle = new StackPane();
                iosToggle.setPrefSize(44, 24);
                iosToggle.setCursor(javafx.scene.Cursor.HAND);
                Rectangle tBg = new Rectangle(44, 24, Color.web(PRIMARY));
                tBg.setArcWidth(24);
                tBg.setArcHeight(24);
                Circle tThumb = new Circle(10, Color.WHITE);
                StackPane.setAlignment(tThumb, Pos.CENTER_RIGHT);
                StackPane.setMargin(tThumb, new Insets(0, 2, 0, 0));
                iosToggle.getChildren().addAll(tBg, tThumb);

                iosToggle.setOnMouseClicked(e -> {
                        isDefault[0] = !isDefault[0];
                        if (isDefault[0]) {
                                tBg.setFill(Color.web(PRIMARY));
                                StackPane.setAlignment(tThumb, Pos.CENTER_RIGHT);
                                StackPane.setMargin(tThumb, new Insets(0, 2, 0, 0));
                        } else {
                                tBg.setFill(Color.web("#3b5443"));
                                StackPane.setAlignment(tThumb, Pos.CENTER_LEFT);
                                StackPane.setMargin(tThumb, new Insets(0, 0, 0, 2));
                        }
                });

                toggleRow.getChildren().addAll(toggleText, iosToggle);

                // Security Info
                HBox securityInfo = new HBox(12);
                securityInfo.setPadding(new Insets(0, 20, 0, 20));
                Label lock = new Label("\ud83d\udee1\ufe0f");
                lock.setTextFill(Color.web(PRIMARY));
                Label lockText = new Label(
                                "Your financial data is encrypted and stored securely. We use this information only for processing your rental income payouts.");
                lockText.setTextFill(Color.web(TEXT_GRAY));
                lockText.setFont(Font.font(11));
                lockText.setWrapText(true);
                HBox.setHgrow(lockText, Priority.ALWAYS);
                securityInfo.getChildren().addAll(lock, lockText);

                scrollContent.getChildren().addAll(progressSect, headline, form, toggleRow, securityInfo);

                // Footer Action
                VBox footer = new VBox();
                footer.setPadding(new Insets(15, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");

                Button verifyBtn = new Button("Verify Account");
                verifyBtn.setGraphic(new Label("\u203a"));
                verifyBtn.setContentDisplay(ContentDisplay.RIGHT);
                verifyBtn.setMaxWidth(Double.MAX_VALUE);
                verifyBtn.setPrefHeight(56);
                verifyBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                verifyBtn.setOnAction(e -> MainApp.navigateCached("bank_verification_success",
                                BankVerificationSuccessView::new));

                footer.getChildren().add(verifyBtn);

                setTop(topNav);
                setCenter(scroll);
                setBottom(footer);
        }

        private VBox createFormField(String label, String placeholder, boolean optional) {
                VBox field = new VBox(8);

                HBox labelRow = new HBox();
                Label l = new Label(label);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                labelRow.getChildren().add(l);

                if (optional) {
                        Region s = new Region();
                        HBox.setHgrow(s, Priority.ALWAYS);
                        Label opt = new Label("OPTIONAL");
                        opt.setTextFill(Color.web(TEXT_GRAY));
                        opt.setOpacity(0.6);
                        opt.setFont(Font.font("System", FontWeight.BOLD, 9));
                        labelRow.getChildren().addAll(s, opt);
                }

                TextField tf = new TextField();
                tf.setPromptText(placeholder);
                tf.setPrefHeight(56);
                tf.setStyle("-fx-background-color: " + INPUT_BG
                                + "; -fx-text-fill: white; -fx-prompt-text-fill: rgba(157, 185, 166, 0.4); -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_COLOR + "; -fx-border-radius: 12; -fx-padding: 0 15;");

                field.getChildren().addAll(labelRow, tf);
                return field;
        }

        private VBox createBankDropdown() {
                VBox field = new VBox(8);
                Label l = new Label("Bank Name");
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                ComboBox<String> cb = new ComboBox<>();
                cb.setPromptText("Select your bank");
                cb.getItems().addAll("Kenya Commercial Bank (KCB)", "Equity Bank", "ABSA Bank Kenya",
                                "Co-operative Bank",
                                "Stanbic Bank");
                cb.setMaxWidth(Double.MAX_VALUE);
                cb.setPrefHeight(56);
                cb.setStyle("-fx-background-color: " + INPUT_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_COLOR + "; -fx-border-radius: 12; -fx-padding: 0 10;");

                field.getChildren().addAll(l, cb);
                return field;
        }
}
