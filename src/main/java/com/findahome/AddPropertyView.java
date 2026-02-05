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

public class AddPropertyView extends BorderPane {

        private static final String BACKGROUND_DARK = "#101922";
        private static final String CARD_BG = "#1c2127";
        private static final String PRIMARY = "#137fec";
        private static final String TEXT_MUTED = "#9dabb9";
        private static final String BORDER_COLOR = "#3b4754";

        private VBox scrollContent;
        private Label pTitle;
        private Label pStep;
        private ProgressBar pBar;
        private StackPane footerArea;
        private VBox headerArea;

        public AddPropertyView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Top Navigation Bar
                headerArea = new VBox(0);
                headerArea.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

                HBox topBar = new HBox(15);
                topBar.setAlignment(Pos.CENTER_LEFT);
                topBar.setPadding(new Insets(15, 20, 15, 20));

                Label closeBtn = new Label("\u2715");
                closeBtn.setTextFill(Color.WHITE);
                closeBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
                closeBtn.setOnMouseClicked(
                                e -> MainApp.navigateCached("landlord_dashboard", LandlordDashboardView::new));

                Label title = new Label("Add New Property");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label saveBtn = new Label("Save");
                saveBtn.setTextFill(Color.web(PRIMARY));
                saveBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
                saveBtn.setCursor(javafx.scene.Cursor.HAND);

                topBar.getChildren().addAll(closeBtn, title, saveBtn);

                // Progress Bar Component
                VBox progressBox = new VBox(8);
                progressBox.setPadding(new Insets(0, 20, 15, 20));

                HBox pLabels = new HBox();
                pTitle = new Label("Basic Info");
                pTitle.setTextFill(Color.WHITE);
                pTitle.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                pStep = new Label("Step 1 of 3");
                pStep.setTextFill(Color.web(TEXT_MUTED));
                pStep.setFont(Font.font(12));
                pLabels.getChildren().addAll(pTitle, pSpacer, pStep);

                pBar = new ProgressBar(0.33);
                pBar.setMaxWidth(Double.MAX_VALUE);
                pBar.setPrefHeight(6);
                pBar.setStyle("-fx-accent: " + PRIMARY
                                + "; -fx-control-inner-background: #1c2127; -fx-background-radius: 10;");

                progressBox.getChildren().addAll(pLabels, pBar);
                headerArea.getChildren().addAll(topBar, progressBox);

                // Scrollable Content container
                scrollContent = new VBox(0);
                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                // Footer container
                footerArea = new StackPane();
                footerArea.setPadding(new Insets(15, 20, 35, 20));
                footerArea.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");

                setTop(headerArea);
                setCenter(scroll);
                setBottom(footerArea);

                showStep1();
        }

        private void showStep1() {
                scrollContent.getChildren().clear();
                footerArea.getChildren().clear();

                pTitle.setText("Basic Info");
                pStep.setText("Step 1 of 3");
                pBar.setProgress(0.33);

                // Section: Property Photos
                VBox photoSection = new VBox(15);
                photoSection.setPadding(new Insets(20));

                HBox photoHeader = new HBox();
                photoHeader.setAlignment(Pos.BOTTOM_LEFT);
                VBox photoTitleBox = new VBox(5);
                Label pt = new Label("Property Photos");
                pt.setTextFill(Color.WHITE);
                pt.setFont(Font.font("System", FontWeight.BOLD, 18));
                Label ps = new Label("Add at least 3 high-quality photos");
                ps.setTextFill(Color.web(TEXT_MUTED));
                ps.setFont(Font.font(12));
                photoTitleBox.getChildren().addAll(pt, ps);

                Region phSpacer = new Region();
                HBox.setHgrow(phSpacer, Priority.ALWAYS);
                Label pc = new Label("1/5 Selected");
                pc.setTextFill(Color.web(PRIMARY));
                pc.setFont(Font.font("System", FontWeight.BOLD, 12));
                photoHeader.getChildren().addAll(photoTitleBox, phSpacer, pc);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);

                VBox mainSlot = new VBox(10);
                mainSlot.setAlignment(Pos.CENTER);
                mainSlot.setPrefHeight(200);
                mainSlot.setStyle("-fx-background-color: " + CARD_BG + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-style: dashed; -fx-border-radius: 16; -fx-background-radius: 16; -fx-cursor: hand;");

                StackPane ic = new StackPane(new Label("\ud83d\udcf7"));
                ic.setPrefSize(50, 50);
                ic.setMaxSize(50, 50);
                ic.setStyle("-fx-background-color: rgba(19, 127, 236, 0.1); -fx-background-radius: 25;");
                ((Label) ic.getChildren().get(0)).setStyle("-fx-font-size: 24;");
                ((Label) ic.getChildren().get(0)).setTextFill(Color.web(PRIMARY));

                Label at = new Label("Add Main Photo");
                at.setTextFill(Color.WHITE);
                at.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label ast = new Label("This will be shown in search results");
                ast.setTextFill(Color.web(TEXT_MUTED));
                ast.setFont(Font.font(11));
                mainSlot.getChildren().addAll(ic, at, ast);
                grid.add(mainSlot, 0, 0, 3, 1);

                for (int i = 0; i < 2; i++)
                        grid.add(createSmallSlot(), i, 1);

                StackPane imgSlot = new StackPane();
                imgSlot.setPrefSize(120, 120);
                try {
                        ImageView iv = new ImageView(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDCjJVqxz3cTT5HFaSA8q7kqjg8W_WuBHqtFY9PtgqXOpjj5j4qRz1HBe5yYia93Qeb7oyP67S0EEw-eCPuCBPu-RAw474quwU4TGQaG6Su0Sm5zY2Fu6XsyQQPorQM8XtKIDvatVdmO8BTwBVdWyVn4Gf9TtDM6dX2D2I_G3DAeILA-f2LxOUNOzvvHZD4KEBrtXlQlG_rp1EaZN-AMuGnPRHN-2uPFG31T1dV8qVtJLN5j4XZ0HjT3xWjbOK_1ZQUCY0Q5FTixSw",
                                        120, 120, false, true));
                        iv.setFitWidth(120);
                        iv.setFitHeight(120);
                        Rectangle clip = new Rectangle(120, 120);
                        clip.setArcWidth(24);
                        clip.setArcHeight(24);
                        iv.setClip(clip);
                        imgSlot.getChildren().add(iv);
                        StackPane ov = new StackPane(new Label("\ud83d\uddd1\ufe0f"));
                        ov.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 12; -fx-opacity: 0;");
                        imgSlot.getChildren().add(ov);
                        imgSlot.setOnMouseEntered(e -> ov.setOpacity(1));
                        imgSlot.setOnMouseExited(e -> ov.setOpacity(0));
                } catch (Exception e) {
                }
                grid.add(imgSlot, 2, 1);

                photoSection.getChildren().addAll(photoHeader, grid);

                Region div = new Region();
                div.setPrefHeight(15);
                div.setStyle("-fx-background-color: rgba(0,0,0,0.2);");

                VBox details = new VBox(25);
                details.setPadding(new Insets(20));
                Label dt = new Label("Essential Details");
                dt.setTextFill(Color.WHITE);
                dt.setFont(Font.font("System", FontWeight.BOLD, 18));
                VBox tf = createField("Property Title", "e.g. Modern 2 Bedroom in Kilimani");
                VBox cf = createChoiceField("Category", "Select property type", "Apartment", "Bedsitter", "Mansionette",
                                "Studio", "Office Space");
                VBox rf = createPriceField("Monthly Rent", "0.00");

                details.getChildren().addAll(dt, tf, cf, rf);
                scrollContent.getChildren().addAll(photoSection, div, details);

                Button nextBtn = new Button("Next: Location Details \u2192");
                nextBtn.setMaxWidth(Double.MAX_VALUE);
                nextBtn.setPrefHeight(56);
                nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 16; -fx-font-weight: bold; -fx-font-size: 16; -fx-cursor: hand;");
                nextBtn.setOnAction(e -> showStep2());
                footerArea.getChildren().add(nextBtn);
        }

        private void showStep2() {
                scrollContent.getChildren().clear();
                footerArea.getChildren().clear();

                pTitle.setText("Details & Amenities");
                pStep.setText("Step 2 of 3");
                pBar.setProgress(0.66);

                VBox content = new VBox(25);
                content.setPadding(new Insets(20));

                Label subHeader = new Label("Almost there! Just a few more details.");
                subHeader.setTextFill(Color.web(PRIMARY));
                subHeader.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                // Basic Specifications
                VBox specs = new VBox(15);
                Label specsHeader = new Label("Basic Specifications");
                specsHeader.setTextFill(Color.WHITE);
                specsHeader.setFont(Font.font("System", FontWeight.BOLD, 18));

                HBox specGrid = new HBox(15);
                VBox beds = createChoiceField("Bedrooms", "Select", "Studio", "1", "2", "3", "4+");
                VBox baths = createChoiceField("Bathrooms", "Select", "1", "1.5", "2", "2.5", "3+");
                HBox.setHgrow(beds, Priority.ALWAYS);
                HBox.setHgrow(baths, Priority.ALWAYS);
                specGrid.getChildren().addAll(beds, baths);
                specs.getChildren().addAll(specsHeader, specGrid);

                // Amenities
                VBox amenities = new VBox(15);
                Label amHeader = new Label("Amenities");
                amHeader.setTextFill(Color.WHITE);
                amHeader.setFont(Font.font("System", FontWeight.BOLD, 18));
                Label amSub = new Label("Select all that apply to your property.");
                amSub.setTextFill(Color.web(TEXT_MUTED));
                amSub.setFont(Font.font(12));

                GridPane amGrid = new GridPane();
                amGrid.setHgap(12);
                amGrid.setVgap(12);
                String[] ams = { "WiFi", "\ud83d\udcf6", "Parking", "\ud83d\ude97", "Gym", "\ud83c\udfcb\ufe0f",
                                "Security",
                                "\ud83d\udee1\ufe0f", "Pool", "\ud83c\udfca", "AC", "\u2744\ufe0f" };
                for (int i = 0; i < ams.length / 2; i++) {
                        amGrid.add(createAmenityToggle(ams[i * 2], ams[i * 2 + 1]), i % 2, i / 2);
                }
                amenities.getChildren().addAll(amHeader, amSub, amGrid);

                // Description
                VBox descSection = new VBox(10);
                Label descHeader = new Label("Description");
                descHeader.setTextFill(Color.WHITE);
                descHeader.setFont(Font.font("System", FontWeight.BOLD, 18));
                Label descSub = new Label("Tell us more about the property");
                descSub.setTextFill(Color.web(TEXT_MUTED));
                descSub.setFont(Font.font(12));

                TextArea ta = new TextArea();
                ta.setPromptText("Describe the neighborhood, nearby landmarks, or any unique features of the home...");
                ta.setPrefHeight(160);
                ta.setWrapText(true);
                ta.setStyle("-fx-background-color: " + CARD_BG + "; -fx-control-inner-background: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12; -fx-padding: 10;");

                Label charCount = new Label("0 / 2000 characters");
                charCount.setTextFill(Color.web(TEXT_MUTED));
                charCount.setFont(Font.font(11));
                StackPane charContainer = new StackPane(charCount);
                StackPane.setAlignment(charCount, Pos.CENTER_RIGHT);

                descSection.getChildren().addAll(descHeader, descSub, ta, charContainer);

                content.getChildren().addAll(subHeader, specs, amenities, descSection);
                scrollContent.getChildren().add(content);

                // Footer Actions
                HBox footerBtns = new HBox(15);
                Button backBtn = new Button("Back");
                backBtn.setPrefHeight(56);
                backBtn.setPrefWidth(120);
                backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: "
                                + BORDER_COLOR
                                + "; -fx-border-radius: 16; -fx-font-weight: bold; -fx-cursor: hand;");
                backBtn.setOnAction(e -> showStep1());

                Button nextBtn = new Button("Next Step");
                nextBtn.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(nextBtn, Priority.ALWAYS);
                nextBtn.setPrefHeight(56);
                nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 16; -fx-font-weight: bold; -fx-font-size: 16; -fx-cursor: hand;");
                nextBtn.setOnAction(e -> showStep3());
                footerBtns.getChildren().addAll(backBtn, nextBtn);
                footerArea.getChildren().add(footerBtns);
        }

        private void showStep3() {
                scrollContent.getChildren().clear();
                footerArea.getChildren().clear();

                pTitle.setText("Location");
                pStep.setText("Step 3 of 3");
                pBar.setProgress(1.0);

                VBox content = new VBox(25);
                content.setPadding(new Insets(20));

                VBox headlineBox = new VBox(5);
                Label headline = new Label("Location Details");
                headline.setTextFill(Color.WHITE);
                headline.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label subTxt = new Label(
                                "Pinpoint your property's precise location for accurate search results.");
                subTxt.setTextFill(Color.web(TEXT_MUTED));
                subTxt.setFont(Font.font(13));
                subTxt.setWrapText(true);
                headlineBox.getChildren().addAll(headline, subTxt);

                // Map simulation with better organization
                VBox mapContainer = new VBox(10);
                StackPane mapRoot = new StackPane();
                mapRoot.setPrefHeight(180);
                mapRoot.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-overflow: hidden; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12;");

                ImageView mapImg = new ImageView();
                try {
                        mapImg.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCyasgGRNx5tRGMuc_AEZIJ22unAv5veHNgrniiBfnvdptdDscv2G8VMkuL3A-Umg33tapqmv68vQ2cfNgCiS308MNxsf1FkGtVBnRVqs9zNakB1pP5KsNw7AAmsDPLxFcAQjFR7JmMwYbz7znRGFqjEoV_ngOEHnxh1VyaX9k85Sv6y_YiIj2Lne8j7LaVUrKEg5fLuE4tdnZH1sBhCWyGGtPgZn9DMTYhCUwFuuGI-AnH7mfgOg0mx545jxs3_heSaYnMW5NtSFc",
                                        400, 200, false, true));
                        mapImg.setFitWidth(360);
                        mapImg.setFitHeight(180);
                        mapImg.setOpacity(0.7);
                        Rectangle clip = new Rectangle(360, 180);
                        clip.setArcWidth(24);
                        clip.setArcHeight(24);
                        mapImg.setClip(clip);
                        mapRoot.getChildren().add(mapImg);
                } catch (Exception e) {
                }

                Label marker = new Label("\ud83d\udccd");
                marker.setStyle("-fx-font-size: 32;");
                marker.setTextFill(Color.web(PRIMARY));

                mapRoot.getChildren().add(marker);
                mapContainer.getChildren().add(mapRoot);

                VBox locationForm = new VBox(15);
                locationForm.getChildren().addAll(
                                createChoiceField("County", "Select County", "Nairobi", "Mombasa", "Kiambu", "Nakuru",
                                                "Uasin Gishu"),
                                createChoiceField("Sub-County / Ward", "Select Ward", "Kilimani", "Kileleshwa",
                                                "Westlands", "Parklands", "Lavington"),
                                createIconField("Estate / Building", "e.g. Ocean View Apts, Wing A", "\ud83c\udfe2"),
                                createIconField("Specific Address", "e.g. House 4, 3rd Floor, Ngong Rd",
                                                "\ud83d\udccd"),
                                createIconField("Nearest Landmark", "e.g. Near Junction Mall", "\ud83c\udfaf"));

                // Privacy Toggle
                HBox privacyBox = new HBox(12);
                privacyBox.setPadding(new Insets(15));
                privacyBox.setAlignment(Pos.CENTER_LEFT);
                privacyBox.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-border-color: " + BORDER_COLOR + ";");

                VBox privTxt = new VBox(2);
                Label privLbl = new Label("Protect Location Privacy");
                privLbl.setTextFill(Color.WHITE);
                privLbl.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label privSub = new Label("Show general neighborhood instead of exact pin.");
                privSub.setTextFill(Color.web(TEXT_MUTED));
                privSub.setFont(Font.font(11));
                privTxt.getChildren().addAll(privLbl, privSub);

                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                CheckBox pCheck = new CheckBox();
                pCheck.setStyle("-fx-mark-color: white; -fx-box-color: " + PRIMARY + ";");

                privacyBox.getChildren().addAll(privTxt, pSpacer, pCheck);

                content.getChildren().addAll(headlineBox, mapContainer, locationForm, privacyBox);
                scrollContent.getChildren().add(content);

                // Footer
                VBox footerContent = new VBox(12);
                HBox feeRow = new HBox();
                Label feeLbl = new Label("Service Fee");
                feeLbl.setTextFill(Color.web(TEXT_MUTED));
                Region feeSpacer = new Region();
                HBox.setHgrow(feeSpacer, Priority.ALWAYS);
                Label feeVal = new Label("KES 1,200");
                feeVal.setTextFill(Color.WHITE);
                feeVal.setFont(Font.font("System", FontWeight.BOLD, 14));
                feeRow.getChildren().addAll(feeLbl, feeSpacer, feeVal);

                Button publishBtn = new Button("Publish Listing");
                publishBtn.setMaxWidth(Double.MAX_VALUE);
                publishBtn.setPrefHeight(56);
                publishBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 16; -fx-font-weight: bold; -fx-font-size: 16; -fx-cursor: hand;");
                publishBtn.setOnAction(e -> MainApp.navigateCached("success_publish", () -> new SuccessView(
                                "Listing Published!",
                                "Your property is now live and visible to potential tenants.", "View Dashboard",
                                () -> MainApp.navigateCached("landlord_dashboard", LandlordDashboardView::new))));

                footerContent.getChildren().addAll(feeRow, publishBtn);
                footerArea.getChildren().add(footerContent);
        }

        private VBox createIconField(String label, String prompt, String icon) {
                VBox v = new VBox(8);
                Label lbl = new Label(label);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                HBox box = new HBox(12);
                box.setPadding(new Insets(0, 15, 0, 15));
                box.setPrefHeight(56);
                box.setAlignment(Pos.CENTER_LEFT);
                box.setStyle("-fx-background-color: " + CARD_BG + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12; -fx-background-radius: 12;");

                Label i = new Label(icon);
                i.setTextFill(Color.web(TEXT_MUTED));
                i.setStyle("-fx-font-size: 18;");

                TextField tf = new TextField();
                tf.setPromptText(prompt);
                tf.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 0;");
                HBox.setHgrow(tf, Priority.ALWAYS);

                box.getChildren().addAll(i, tf);
                v.getChildren().addAll(lbl, box);
                return v;
        }

        private HBox createAmenityToggle(String name, String icon) {
                HBox row = new HBox(10);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12));
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand;");
                row.setPrefWidth(185);

                CheckBox cb = new CheckBox();
                cb.setStyle("-fx-mark-color: white; -fx-box-color: " + PRIMARY + ";");

                Label l = new Label(name);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.MEDIUM, 13));

                row.getChildren().addAll(cb, l);
                row.setOnMouseClicked(e -> cb.setSelected(!cb.isSelected()));
                return row;
        }

        private StackPane createSmallSlot() {
                StackPane slot = new StackPane();
                slot.setPrefSize(120, 120);
                slot.setStyle("-fx-background-color: " + CARD_BG + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-style: dashed; -fx-border-radius: 16; -fx-background-radius: 16; -fx-cursor: hand;");
                Label plus = new Label("+");
                plus.setTextFill(Color.web(TEXT_MUTED));
                plus.setStyle("-fx-font-size: 24;");
                slot.getChildren().add(plus);
                return slot;
        }

        private VBox createField(String label, String prompt) {
                VBox v = new VBox(8);
                Label lbl = new Label(label);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                TextField tf = new TextField();
                tf.setPromptText(prompt);
                tf.setPrefHeight(56);
                tf.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12; -fx-padding: 0 15;");
                v.getChildren().addAll(lbl, tf);
                return v;
        }

        private VBox createChoiceField(String label, String prompt, String... options) {
                VBox v = new VBox(8);
                Label lbl = new Label(label);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                ComboBox<String> cb = new ComboBox<>();
                cb.setPromptText(prompt);
                cb.getItems().addAll(options);
                cb.setMaxWidth(Double.MAX_VALUE);
                cb.setPrefHeight(56);
                cb.setStyle("-fx-background-color: " + CARD_BG + "; -fx-control-inner-background: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-radius: 12;");
                v.getChildren().addAll(lbl, cb);
                return v;
        }

        private VBox createPriceField(String label, String prompt) {
                VBox v = new VBox(8);
                Label lbl = new Label(label);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                HBox inputField = new HBox(10);
                inputField.setAlignment(Pos.CENTER_LEFT);
                inputField.setPadding(new Insets(0, 15, 0, 15));
                inputField.setPrefHeight(56);
                inputField.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                                + BORDER_COLOR + "; -fx-border-radius: 12;");
                Label currency = new Label("KSh");
                currency.setTextFill(Color.web(TEXT_MUTED));
                currency.setStyle("-fx-font-weight: bold; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 1 0 0; -fx-padding: 0 10 0 0;");
                TextField tf = new TextField();
                tf.setPromptText(prompt);
                tf.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 0;");
                HBox.setHgrow(tf, Priority.ALWAYS);
                inputField.getChildren().addAll(currency, tf);
                v.getChildren().addAll(lbl, inputField);
                return v;
        }
}
