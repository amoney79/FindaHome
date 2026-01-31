package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

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
                content.setSpacing(0);
                content.setPadding(Insets.EMPTY);

                // Top Navigation Area
                HBox nav = new HBox();
                nav.setPadding(new Insets(10, 20, 10, 20));
                nav.setAlignment(Pos.CENTER_LEFT);
                Label closeBtn = new Label("\u2715");
                closeBtn.setTextFill(Color.WHITE);
                closeBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
                closeBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label navTitle = new Label("Agent Onboarding");
                navTitle.setTextFill(Color.WHITE);
                navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                navTitle.setAlignment(Pos.CENTER);
                navTitle.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(navTitle, Priority.ALWAYS);

                Region navSpacer = new Region();
                navSpacer.setPrefWidth(20);

                nav.getChildren().addAll(closeBtn, navTitle, navSpacer);

                // Hero Illustration Section
                StackPane heroStack = new StackPane();
                heroStack.setPadding(new Insets(10, 20, 10, 20));

                StackPane heroContainer = new StackPane();
                heroContainer.setPrefHeight(240);
                heroContainer.setStyle("-fx-background-radius: 20; -fx-overflow: hidden;");

                ImageView heroImg = new ImageView();
                try {
                        heroImg.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDi9ekfuyG4ZMCQQeywG81RdUaioqR49I3Oe-jSXonJfbkYEP4rlBLilU1BquVH8iknox01NNg220loYWdJR3tFf7Ux4SXFy5Tu8aXtb8S1OfryWa2aK5xRaI75MNkskDAXfEczVeaMuhYZ9-WX_7_AVYL9OetEmRn4CFHEtB292qkS7KVj-eUxm72vPz4E__rDO4dkU7opd-YwiqjNR8uw5mB8PcIBMTSCy-q-48IiiW8c-fr3gfm7hOEQQ1q-VSRK06atuPFgO6s",
                                        430, 240, false, true));
                } catch (Exception e) {
                }
                heroImg.setFitWidth(400);
                heroImg.setFitHeight(240);
                heroImg.setPreserveRatio(false);
                Rectangle heroClip = new Rectangle(400, 240);
                heroClip.setArcWidth(40);
                heroClip.setArcHeight(40);
                heroImg.setClip(heroClip);

                StackPane overlay = new StackPane();
                overlay.setStyle(
                                "-fx-background-color: linear-gradient(to bottom, rgba(16, 34, 22, 0.2), rgba(19, 236, 91, 0.1));");

                StackPane verifiedBadge = new StackPane();
                verifiedBadge.setPrefSize(60, 60);
                verifiedBadge.setMaxSize(60, 60);
                verifiedBadge.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-background-radius: 30; -fx-effect: dropshadow(three-pass-box, rgba(19,236,91,0.4), 15, 0, 0, 0);");
                Label vIcon = new Label("\u2705");
                vIcon.setStyle("-fx-font-size: 30;");
                vIcon.setTextFill(Color.web(BACKGROUND_DARK));
                verifiedBadge.getChildren().add(vIcon);

                heroContainer.getChildren().addAll(heroImg, overlay, verifiedBadge);
                heroStack.getChildren().add(heroContainer);

                // Page Indicators
                HBox indicators = new HBox(8);
                indicators.setAlignment(Pos.CENTER);
                indicators.setPadding(new Insets(20, 0, 20, 0));
                Region dot1 = new Region();
                dot1.setPrefSize(24, 8);
                dot1.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
                Region dot2 = new Region();
                dot2.setPrefSize(8, 8);
                dot2.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 4;");
                Region dot3 = new Region();
                dot3.setPrefSize(8, 8);
                dot3.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 4;");
                indicators.getChildren().addAll(dot1, dot2, dot3);

                // Headline and Subtext
                VBox textSection = new VBox(10);
                textSection.setAlignment(Pos.CENTER);
                textSection.setPadding(new Insets(0, 30, 20, 30));
                Label title = new Label("Boost Your Property Business");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 26));
                title.setWrapText(true);
                title.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                Label sub = new Label(
                                "Verified agents on FindaHome receive up to 3x more leads and gain instant trust from potential tenants.");
                sub.setTextFill(Color.web("white", 0.7));
                sub.setWrapText(true);
                sub.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                sub.setFont(Font.font(14));
                textSection.getChildren().addAll(title, sub);

                // Value Propositions
                VBox props = new VBox(20);
                props.setPadding(new Insets(0, 30, 30, 30));
                props.getChildren().addAll(
                                createValueProp("Trust Badge",
                                                "Gain instant credibility with a verified profile badge.",
                                                "\ud83d\udee1\ufe0f"),
                                createValueProp("Higher Visibility",
                                                "Your listings appear at the top of search results.",
                                                "\ud83d\udcc8"),
                                createValueProp("Exclusive Leads",
                                                "Get priority access to high-intent tenant inquiries.", "\u26a1"));

                // Footer Actions
                VBox footer = new VBox(15);
                footer.setPadding(new Insets(0, 30, 40, 30));
                Button startBtn = new Button("Get Started");
                startBtn.setMaxWidth(Double.MAX_VALUE);
                startBtn.setPrefHeight(56);
                startBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 16;");
                startBtn.setOnAction(e -> showUpload());

                Hyperlink tenantLink = new Hyperlink("Not an agent? Sign in as Tenant");
                tenantLink.setStyle("-fx-text-fill: rgba(255,255,255,0.5); -fx-font-size: 13; -fx-underline: false;");
                tenantLink.setMaxWidth(Double.MAX_VALUE);
                tenantLink.setAlignment(Pos.CENTER);

                footer.getChildren().addAll(startBtn, tenantLink);

                content.getChildren().addAll(nav, heroStack, indicators, textSection, props, footer);
        }

        private HBox createValueProp(String title, String sub, String iconStr) {
                HBox box = new HBox(15);
                box.setAlignment(Pos.TOP_LEFT);

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(40, 40);
                iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.2); -fx-background-radius: 8;");
                Label icon = new Label(iconStr);
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(icon);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.SEMI_BOLD, 15));
                Label s = new Label(sub);
                s.setTextFill(Color.web("white", 0.6));
                s.setFont(Font.font(13));
                s.setWrapText(true);
                text.getChildren().addAll(t, s);

                box.getChildren().addAll(iconBox, text);
                return box;
        }

        private void showUpload() {
                getChildren().clear();
                VBox layout = new VBox(0);
                VBox.setVgrow(layout, Priority.ALWAYS);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 10, 20));
                Label back = new Label("\u2039");
                back.setStyle("-fx-font-size: 24; -fx-text-fill: white; -fx-cursor: hand;");
                back.setOnMouseClicked(e -> showIntro());
                Label title = new Label("Agent Verification");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);
                Region s = new Region();
                s.setPrefWidth(24);
                header.getChildren().addAll(back, title, s);

                VBox scrollContent = new VBox(25);
                scrollContent.setPadding(new Insets(10, 20, 30, 20));

                // Progress
                VBox progress = new VBox(8);
                HBox pLabel = new HBox();
                Label pStep = new Label("Step 1: Document Upload");
                pStep.setTextFill(Color.WHITE);
                pStep.setFont(Font.font("System", FontWeight.MEDIUM, 15));
                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                Label pCount = new Label("1/2");
                pCount.setTextFill(Color.WHITE);
                pLabel.getChildren().addAll(pStep, pSpacer, pCount);

                ProgressBar pBar = new ProgressBar(0.5);
                pBar.setMaxWidth(Double.MAX_VALUE);
                pBar.setPrefHeight(8);
                pBar.setStyle("-fx-accent: " + PRIMARY
                                + "; -fx-control-inner-background: #3b5443; -fx-background-radius: 4;");

                Label pSub = new Label("Verification process builds trust with renters.");
                pSub.setTextFill(Color.web("#9db9a6"));
                pSub.setFont(Font.font(12));
                progress.getChildren().addAll(pLabel, pBar, pSub);

                // Headline
                VBox headline = new VBox(5);
                Label hTitle = new Label("Upload ID and License");
                hTitle.setTextFill(Color.WHITE);
                hTitle.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label hSub = new Label("Please provide your identification and professional license for verification.");
                hSub.setTextFill(Color.web("white", 0.7));
                hSub.setWrapText(true);
                headline.getChildren().addAll(hTitle, hSub);

                // Form Section
                VBox form = new VBox(20);

                VBox idNumField = createField("ID/Passport Number", "e.g. 12345678");

                HBox idGrid = new HBox(12);
                idGrid.getChildren().addAll(
                                createSquareUpload("ID Card (Front)", "\ud83d\udcf7"),
                                createSquareUpload("ID Card (Back)", "\ud83d\udcf7"));

                VBox licNumField = createField("Real Estate License Number", "e.g. RE-19283746");

                VBox licBox = new VBox(8);
                Label licLbl = new Label("OFFICIAL LICENSE DOCUMENT");
                licLbl.setTextFill(Color.web("white", 0.7));
                licLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 10;");
                licBox.getChildren().addAll(licLbl,
                                createLongUploadBox("Click to upload document", "PDF, JPG or PNG (Max 5MB)",
                                                "\ud83d\udcc1"));

                form.getChildren().addAll(idNumField, idGrid, licNumField, licBox);

                // Privacy
                HBox privacy = new HBox(10);
                Label privIcon = new Label("\ud83d\udee1\ufe0f");
                privIcon.setTextFill(Color.web(PRIMARY));
                Label privText = new Label(
                                "Your documents are encrypted and stored securely. We use this information strictly for identity verification and do not share it with third parties.");
                privText.setTextFill(Color.web("#9db9a6"));
                privText.setFont(Font.font(11));
                privText.setWrapText(true);
                privacy.getChildren().addAll(privIcon, privText);

                scrollContent.getChildren().addAll(progress, headline, form, privacy);

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                // Footer
                HBox footer = new HBox();
                footer.setPadding(new Insets(15, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "f2; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");
                Button contBtn = new Button("Continue \u2192");
                contBtn.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(contBtn, Priority.ALWAYS);
                contBtn.setPrefHeight(56);
                contBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 16;");
                contBtn.setOnAction(e -> showLiveness());
                footer.getChildren().add(contBtn);

                layout.getChildren().addAll(header, scroll);
                getChildren().setAll(layout, footer);
                VBox.setVgrow(scroll, Priority.ALWAYS);
        }

        private void showLiveness() {
                getChildren().clear();
                VBox layout = new VBox(0);
                VBox.setVgrow(layout, Priority.ALWAYS);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 10, 20));
                Label back = new Label("\u2039");
                back.setStyle("-fx-font-size: 24; -fx-text-fill: white; -fx-cursor: hand;");
                back.setOnMouseClicked(e -> showUpload());
                Label title = new Label("Identity Verification");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);
                Label stepCount = new Label("Step 2/2"); // Changed to 2/2 to match previous 1/2
                stepCount.setTextFill(Color.web(PRIMARY));
                stepCount.setFont(Font.font("System", FontWeight.BOLD, 14));
                header.getChildren().addAll(back, title, stepCount);

                VBox contentArea = new VBox(0);
                contentArea.setAlignment(Pos.TOP_CENTER);
                contentArea.setPadding(new Insets(20, 20, 0, 20));

                Label hTitle = new Label("Face Liveness Check");
                hTitle.setTextFill(Color.WHITE);
                hTitle.setFont(Font.font("System", FontWeight.BOLD, 26));

                Label hSub = new Label(
                                "To keep FindaHome safe, we need to confirm it's really you. This will be matched with your uploaded ID.");
                hSub.setTextFill(Color.web("white", 0.7));
                hSub.setWrapText(true);
                hSub.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                hSub.setPadding(new Insets(10, 20, 30, 20));

                // Biometric Camera Area
                StackPane camRoot = new StackPane();
                camRoot.setMaxSize(300, 400);
                camRoot.setStyle(
                                "-fx-background-color: #1a1f1a; -fx-background-radius: 48; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 48; -fx-border-width: 4;");

                ImageView faceSim = new ImageView();
                try {
                        faceSim.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuB5vsJ6mDBdboCFsh9JaBAyoUkJx_LTOgQI16joJ1Eg8C-KkjXHYeBitgDyv9fJshGXnmHuemv0TYeXp7Hyk0t_EQuNRtF6s3-W0ou8nAzD6Qp3J9XsDurYI-87NwE0-i6o8vQGoZb7ysi8-azKRHo17L56VMftPRh5HsqQTQKiPuRwyiAhkgxevgofim28NL4WtWYzWh3w_EQMomij4Aag9axtOZX3gQ4CpXD-gbT_Xw1EBBOZeowh-PCm60DDsyCysXhPNWWnOjc",
                                        300, 400, false, true));
                } catch (Exception e) {
                }
                faceSim.setFitWidth(300);
                faceSim.setFitHeight(400);
                Rectangle camClip = new Rectangle(300, 400);
                camClip.setArcWidth(96);
                camClip.setArcHeight(96);
                faceSim.setClip(camClip);

                // Biometric Frame
                StackPane frame = new StackPane();
                frame.setMaxSize(240, 320);
                frame.setStyle("-fx-border-color: " + PRIMARY
                                + "; -fx-border-radius: 120; -fx-border-width: 4; -fx-effect: dropshadow(three-pass-box, "
                                + PRIMARY
                                + "66, 20, 0, 0, 0);");

                // Scan line
                Region scanLine = new Region();
                scanLine.setMaxHeight(2);
                scanLine.setMaxWidth(200);
                scanLine.setStyle("-fx-background-color: " + PRIMARY + "99; -fx-effect: dropshadow(three-pass-box, "
                                + PRIMARY
                                + ", 10, 0.5, 0, 0);");
                scanLine.setTranslateY(-50);

                camRoot.getChildren().addAll(faceSim, frame, scanLine);

                // Instruction Badge
                HBox instr = new HBox(10);
                instr.setAlignment(Pos.CENTER);
                instr.setPadding(new Insets(10, 20, 10, 20));
                instr.setStyle(
                                "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-border-color: rgba(19, 236, 91, 0.2); -fx-background-radius: 30;");
                Label iIcon = new Label("\ud83d\udc64");
                iIcon.setTextFill(Color.web(PRIMARY));
                Label iText = new Label("Slowly turn your head to the right");
                iText.setTextFill(Color.web(PRIMARY));
                iText.setStyle("-fx-font-weight: bold;");
                instr.getChildren().addAll(iIcon, iText);
                VBox.setMargin(instr, new Insets(30, 0, 0, 0));

                // Camera Controls
                HBox controls = new HBox(30);
                controls.setAlignment(Pos.CENTER);
                controls.setPadding(new Insets(40, 0, 20, 0));

                Button flashBtn = createCircleIconButton("\u26a1", 50, "rgba(255,255,255,0.1)");

                Button captureBtn = new Button("\ud83d\udcf7");
                captureBtn.setPrefSize(80, 80);
                captureBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-background-radius: 40; -fx-font-size: 30; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.4), 15, 0, 0, 5);");
                captureBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView()));

                Button flipBtn = createCircleIconButton("\ud83d\udcf3", 50, "rgba(255,255,255,0.1)");
                controls.getChildren().addAll(flashBtn, captureBtn, flipBtn);

                Region pSpacer = new Region();
                VBox.setVgrow(pSpacer, Priority.ALWAYS);

                Label privacy = new Label(
                                "\ud83d\udd12 Your biometric data is encrypted and only used for identity verification.");
                privacy.setTextFill(Color.web("#9db9a6"));
                privacy.setFont(Font.font(11));
                privacy.setPadding(new Insets(0, 0, 40, 0));

                contentArea.getChildren().addAll(hTitle, hSub, camRoot, instr, controls, pSpacer, privacy);
                layout.getChildren().add(contentArea);
                getChildren().setAll(layout);
        }

        private Button createCircleIconButton(String icon, double size, String bg) {
                Button btn = new Button(icon);
                btn.setPrefSize(size, size);
                btn.setStyle("-fx-background-color: " + bg + "; -fx-text-fill: white; -fx-background-radius: "
                                + (size / 2)
                                + "; -fx-font-size: 18; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: "
                                + (size / 2)
                                + ";");
                return btn;
        }

        private VBox createField(String l, String p) {
                VBox v = new VBox(8);
                Label lbl = new Label(l);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                TextField tf = new TextField();
                tf.setPromptText(p);
                tf.setStyle(
                                "-fx-background-color: #1c271f; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-color: #3b5443; -fx-border-radius: 10; -fx-pref-height: 52; -fx-padding: 0 15;");
                v.getChildren().addAll(lbl, tf);
                return v;
        }

        private VBox createSquareUpload(String title, String iconStr) {
                VBox root = new VBox(8);
                Label lbl = new Label(title.toUpperCase());
                lbl.setTextFill(Color.web("white", 0.7));
                lbl.setStyle("-fx-font-size: 10; -fx-font-weight: bold;");

                VBox box = new VBox(10);
                box.setAlignment(Pos.CENTER);
                box.setPrefSize(185, 120);
                box.setStyle(
                                "-fx-background-color: #1c271f; -fx-border-color: #3b5443; -fx-border-style: dashed; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand;");
                Label icon = new Label(iconStr);
                icon.setTextFill(Color.web("#9db9a6"));
                icon.setStyle("-fx-font-size: 24;");
                Label action = new Label("Upload Front");
                if (title.contains("Back"))
                        action.setText("Upload Back");
                action.setTextFill(Color.web("#9db9a6"));
                action.setFont(Font.font(11));
                box.getChildren().addAll(icon, action);

                root.getChildren().addAll(lbl, box);
                return root;
        }

        private StackPane createLongUploadBox(String title, String sub, String iconStr) {
                StackPane sp = new StackPane();
                sp.setPrefHeight(100);
                sp.setStyle(
                                "-fx-background-color: #1c271f; -fx-border-color: #3b5443; -fx-border-style: dashed; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand;");

                HBox boxContent = new HBox(15);
                boxContent.setAlignment(Pos.CENTER_LEFT);
                boxContent.setPadding(new Insets(0, 20, 0, 20));

                Label icon = new Label(iconStr);
                icon.setTextFill(Color.web("#9db9a6"));
                icon.setStyle("-fx-font-size: 30;");

                VBox textVBox = new VBox(2);
                textVBox.setAlignment(Pos.CENTER_LEFT);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                Label s = new Label(sub);
                s.setTextFill(Color.web("#9db9a6"));
                s.setFont(Font.font(11));
                textVBox.getChildren().addAll(t, s);

                boxContent.getChildren().addAll(icon, textVBox);
                sp.getChildren().add(boxContent);
                return sp;
        }
}
