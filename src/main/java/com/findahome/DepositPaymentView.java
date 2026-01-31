package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DepositPaymentView extends StackPane {

        private static final String BACKGROUND_DARK = "#101922";
        private static final String CARD_BG = "#1a1f2e";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String TEXT_GRAY = "#94a3b8";

        public DepositPaymentView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Top Navigation
                HBox topNav = new HBox();
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPadding(new Insets(15, 20, 15, 20));
                topNav.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new LeaseSigningCompleteView()));

                Label title = new Label("Deposit Payment");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Region spacer = new Region();
                spacer.setPrefWidth(28);

                topNav.getChildren().addAll(backBtn, title, spacer);

                // Scrollable Content
                VBox scrollContent = new VBox(0);
                scrollContent.setPadding(new Insets(20));
                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Property Summary Card
                HBox propertyCard = new HBox(15);
                propertyCard.setPadding(new Insets(15));
                propertyCard.setAlignment(Pos.CENTER_LEFT);
                propertyCard.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBnlP1apGOAQRYfHkCl22QFGlRcXN3rBcNAeVJ0iQVmBaFoInVpOmSiMrpDgxxhlofPmLy8m7Ls0SsI3Eg8rv-gBTIRZQ28fnaC7zadAn90fp4Ixbw9ThDEkS5anFwi13LP1M6W7kf2kPfic_ALgvGuPw7XfNbKmc0amda6vpfTaPR2pwOX9NuJWM0aFj-T0xS5l6sTAQoBTxwUVU1qTWnlKdk2ILvkIhhJ-9Qy4RYasM6V_whW_XFs3FfjqHcHDkvRYE3VljyOfoM",
                                        64, 64, true, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(64);
                iv.setFitHeight(64);
                Rectangle clip = new Rectangle(64, 64);
                clip.setArcWidth(12);
                clip.setArcHeight(12);
                iv.setClip(clip);

                VBox propMeta = new VBox(4);
                Label propName = new Label("Modern 2BR Apartment");
                propName.setTextFill(Color.WHITE);
                propName.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label propLoc = new Label("Kilimani, Nairobi");
                propLoc.setTextFill(Color.web(TEXT_GRAY));
                propLoc.setFont(Font.font(13));
                propMeta.getChildren().addAll(propName, propLoc);

                propertyCard.getChildren().addAll(iv, propMeta);

                // Cost Breakdown
                VBox breakdownSec = new VBox(15);
                breakdownSec.setPadding(new Insets(25, 0, 25, 0));
                Label breakTitle = new Label("Cost Breakdown");
                breakTitle.setTextFill(Color.WHITE);
                breakTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox breakCard = new VBox(10);
                breakCard.setPadding(new Insets(20));
                breakCard.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                breakCard.getChildren().addAll(
                                createPriceRow("Security Deposit", "KSh 45,000"),
                                createPriceRow("First Month Rent", "KSh 45,000"),
                                createPriceRow("Service Fee", "KSh 1,500"));

                VBox totalBox = new VBox(5);
                totalBox.setPadding(new Insets(10, 0, 0, 0));
                totalBox.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 1 0 0 0;");
                Label totalLbl = new Label("TOTAL PAYABLE");
                totalLbl.setTextFill(Color.web(TEXT_GRAY));
                totalLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                Label totalVal = new Label("KSh 91,500");
                totalVal.setTextFill(Color.web(PRIMARY));
                totalVal.setFont(Font.font("System", FontWeight.BLACK, 32));
                totalBox.getChildren().addAll(totalLbl, totalVal);

                breakCard.getChildren().add(totalBox);
                breakdownSec.getChildren().addAll(breakTitle, breakCard);

                // Payment Methods
                VBox payMethods = new VBox(15);
                payMethods.setPadding(new Insets(0, 0, 100, 0));
                Label payTitle = new Label("Payment Method");
                payTitle.setTextFill(Color.WHITE);
                payTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox methodList = new VBox(12);

                // M-Pesa Option
                VBox mpesaContainer = new VBox(10);
                HBox mpesaOption = createPaymentOption("M-Pesa Express", "Instant notification", "M-PESA", true);
                VBox mpesaInput = new VBox(8);
                mpesaInput.setPadding(new Insets(5, 0, 5, 0));
                Label inputLbl = new Label("M-PESA NUMBER");
                inputLbl.setTextFill(Color.web(TEXT_GRAY));
                inputLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                TextField phoneField = new TextField("+254 712 345 678");
                phoneField.setPrefHeight(48);
                phoneField.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-background-radius: 10; -fx-prompt-text-fill: #555;");
                mpesaInput.getChildren().addAll(inputLbl, phoneField);
                mpesaContainer.getChildren().addAll(mpesaOption, mpesaInput);

                methodList.getChildren().addAll(
                                mpesaContainer,
                                createPaymentOption("Bank Transfer", "1-2 business days", "\ud83c\udfdb", false),
                                createPaymentOption("Credit / Debit Card", "Visa, Mastercard", "\ud83d\udcb3", false));

                payMethods.getChildren().addAll(payTitle, methodList);

                scrollContent.getChildren().addAll(propertyCard, breakdownSec, payMethods);

                // Sticky Footer CTA
                VBox footer = new VBox(15);
                footer.setPadding(new Insets(15, 20, 35, 20));
                footer.setAlignment(Pos.CENTER);
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "f0; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                HBox trustBadge = new HBox(8);
                trustBadge.setAlignment(Pos.CENTER);
                Label tIcon = new Label("\ud83d\udee1");
                tIcon.setTextFill(Color.web("#10b981"));
                Label tText = new Label("Secured by FindaHome Escrow & 256-bit SSL");
                tText.setTextFill(Color.web(TEXT_GRAY));
                tText.setFont(Font.font(11));
                trustBadge.getChildren().addAll(tIcon, tText);

                Button payBtn = new Button("\ud83d\udd12 Securely Pay KSh 91,500");
                payBtn.setMaxWidth(Double.MAX_VALUE);
                payBtn.setPrefHeight(56);
                payBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                payBtn.setOnAction(e -> MainApp.navigateTo(new PaymentProcessingView()));

                footer.getChildren().addAll(trustBadge, payBtn);

                layout.getChildren().add(scroll);
                getChildren().addAll(layout, topNav, footer);
                StackPane.setAlignment(topNav, Pos.TOP_CENTER);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
        }

        private HBox createPriceRow(String label, String value) {
                HBox row = new HBox();
                row.setAlignment(Pos.CENTER_LEFT);
                Label l = new Label(label);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font(14));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label v = new Label(value);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 14));
                row.getChildren().addAll(l, s, v);
                return row;
        }

        private HBox createPaymentOption(String title, String desc, String iconText, boolean selected) {
                HBox container = new HBox(15);
                container.setAlignment(Pos.CENTER_LEFT);
                container.setPadding(new Insets(15));
                container.setStyle("-fx-background-color: " + (selected ? "rgba(19, 127, 236, 0.05)" : "transparent")
                                + "; -fx-background-radius: 12; -fx-border-color: "
                                + (selected ? PRIMARY : BORDER_COLOR)
                                + "; -fx-border-width: 2;");

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(40, 40);
                iconBox.setStyle("-fx-background-color: " + (selected ? PRIMARY : "rgba(255,255,255,0.05)")
                                + "; -fx-background-radius: 10;");
                Label icon = new Label(iconText);
                icon.setTextFill(selected ? Color.WHITE : Color.web(TEXT_GRAY));
                icon.setFont(Font.font("System", FontWeight.BOLD, 10));
                iconBox.getChildren().add(icon);

                VBox texts = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label d = new Label(desc);
                d.setTextFill(Color.web(TEXT_GRAY));
                d.setFont(Font.font(11));
                texts.getChildren().addAll(t, d);

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);

                Label check = new Label(selected ? "\u2714" : "\u25ef");
                check.setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

                container.getChildren().addAll(iconBox, texts, s, check);
                return container;
        }
}
