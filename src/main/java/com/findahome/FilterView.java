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

public class FilterView extends VBox {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#135bec";
        private static final String TEXT_GRAY = "#9ca3af";

        public FilterView() {
                setSpacing(0);
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Create FAB results button
                Button resultsFab = new Button("Show 124 Results \u2192");
                resultsFab.setMaxWidth(Double.MAX_VALUE);
                resultsFab.setPrefHeight(55);
                resultsFab.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");
                VBox.setMargin(resultsFab, new Insets(0, 20, 20, 20));
                resultsFab.setOnAction(e -> MainApp.showHome());

                StackPane mainStack = new StackPane();

                VBox scrollContent = new VBox(25);
                scrollContent.setPadding(new Insets(0, 0, 100, 0));

                // Header
                VBox headerArea = new VBox(10);
                headerArea.setPadding(new Insets(10, 0, 10, 0));
                headerArea.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc;");

                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(10, 20, 10, 20));

                Label backBtn = new Label("\u2190"); // Back icon
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("Search & Filters");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setMaxWidth(Double.MAX_VALUE);

                Label moreBtn = new Label("\u22EE");
                moreBtn.setTextFill(Color.WHITE);
                moreBtn.setStyle("-fx-font-size: 20;");

                header.getChildren().addAll(backBtn, title, moreBtn);

                // Search Bar within Header
                HBox searchContainer = new HBox(10);
                searchContainer.setAlignment(Pos.CENTER_LEFT);
                searchContainer.setPadding(new Insets(0, 20, 10, 20));

                StackPane searchFieldStack = new StackPane();
                HBox.setHgrow(searchFieldStack, Priority.ALWAYS);

                TextField search = new TextField("Nairobi, Westlands");
                search.setPromptText("Search locations, apartments...");
                search.setStyle(
                                "-fx-background-color: #282e39; -fx-text-fill: white; -fx-background-radius: 12; -fx-padding: 10 35 10 35; -fx-font-size: 13;");
                search.setPrefHeight(48);

                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web("#9ca3af"));
                searchIcon.setPadding(new Insets(0, 0, 0, 10));
                StackPane.setAlignment(searchIcon, Pos.CENTER_LEFT);

                Label tuneIcon = new Label("\u2312"); // tune icon
                tuneIcon.setTextFill(Color.web(PRIMARY));
                tuneIcon.setPadding(new Insets(0, 10, 0, 0));
                StackPane.setAlignment(tuneIcon, Pos.CENTER_RIGHT);

                searchFieldStack.getChildren().addAll(search, searchIcon, tuneIcon);
                searchContainer.getChildren().add(searchFieldStack);

                headerArea.getChildren().addAll(header, searchContainer);

                // Price Section
                VBox priceSection = new VBox(15);
                priceSection.setPadding(new Insets(0, 20, 0, 20));
                HBox priceHeader = new HBox();
                Label priceTitle = new Label("Price Range");
                priceTitle.setTextFill(Color.WHITE);
                priceTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                Label resetBtn = new Label("Reset");
                resetBtn.setTextFill(Color.web(PRIMARY));
                resetBtn.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                priceHeader.getChildren().addAll(priceTitle, pSpacer, resetBtn);

                StackPane sliderStack = new StackPane();
                sliderStack.setPadding(new Insets(10, 0, 10, 0));
                sliderStack.setPrefHeight(80);
                sliderStack
                                .setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-padding: 20;");

                ProgressBar pBar = new ProgressBar(0.5);
                pBar.setMaxWidth(Double.MAX_VALUE);
                pBar.setPrefHeight(6);
                pBar.setStyle("-fx-accent: " + PRIMARY + ";");

                sliderStack.getChildren().add(pBar);

                VBox priceRangeLabels = new VBox(2);
                priceRangeLabels.setAlignment(Pos.BOTTOM_CENTER);
                Label rangeValue = new Label("Ksh 15,000 - Ksh 85,000");
                rangeValue.setTextFill(Color.web(TEXT_GRAY));
                rangeValue.setFont(Font.font(12));
                priceRangeLabels.getChildren().add(rangeValue);

                priceSection.getChildren().addAll(priceHeader, sliderStack, rangeValue);

                // Property Type
                VBox typeSection = new VBox(15);
                Label typeTitle = new Label("Property Type");
                typeTitle.setPadding(new Insets(0, 20, 0, 20));
                typeTitle.setTextFill(Color.WHITE);
                typeTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                HBox chips = new HBox(10);
                chips.setPadding(new Insets(0, 20, 0, 20));
                chips.getChildren().addAll(
                                createChip("All Types", true),
                                createChip("Apartment", false),
                                createChip("Bedsitter", false),
                                createChip("Studio", false),
                                createChip("Mansionette", false));
                ScrollPane chipScroll = new ScrollPane(chips);
                chipScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                typeSection.getChildren().addAll(typeTitle, chipScroll);

                // Bedrooms Counter
                VBox bedroomSection = new VBox(15);
                bedroomSection.setPadding(new Insets(0, 20, 0, 20));
                Label bedTitle = new Label("Bedrooms");
                bedTitle.setTextFill(Color.WHITE);
                bedTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                HBox counterBox = new HBox(15);
                counterBox.setAlignment(Pos.CENTER_LEFT);
                counterBox.setPadding(new Insets(12));
                counterBox.setStyle(
                                "-fx-background-color: #1c2433; -fx-background-radius: 16; -fx-border-color: #282e39; -fx-border-radius: 16;");

                Label counterLabel = new Label("Number of rooms");
                counterLabel.setTextFill(Color.web(TEXT_GRAY));
                HBox.setHgrow(counterLabel, Priority.ALWAYS);
                counterLabel.setMaxWidth(Double.MAX_VALUE);

                HBox controls = new HBox(12);
                controls.setAlignment(Pos.CENTER);
                Button minus = new Button("-");
                minus.setStyle(
                                "-fx-background-color: #282e39; -fx-text-fill: white; -fx-background-radius: 8; -fx-min-width: 32; -fx-min-height: 32;");
                Label bedCount = new Label("2");
                bedCount.setTextFill(Color.WHITE);
                bedCount.setFont(Font.font("System", FontWeight.BOLD, 16));
                Button plus = new Button("+");
                plus.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-min-width: 32; -fx-min-height: 32;");

                controls.getChildren().addAll(minus, bedCount, plus);
                counterBox.getChildren().addAll(counterLabel, controls);
                bedroomSection.getChildren().addAll(bedTitle, counterBox);

                // Amenities
                VBox amenitiesSection = new VBox(15);
                amenitiesSection.setPadding(new Insets(0, 20, 0, 20));
                Label amTitle = new Label("Amenities");
                amTitle.setTextFill(Color.WHITE);
                amTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                GridPane amGrid = new GridPane();
                amGrid.setHgap(12);
                amGrid.setVgap(12);
                amGrid.add(createAmenity("WiFi", "\ud83d\udcf6", true), 0, 0);
                amGrid.add(createAmenity("Parking", "\ud83c\udd7f\ufe0f", false), 1, 0);
                amGrid.add(createAmenity("Gym", "\ud83c\udfcb\ufe0f", false), 2, 0);
                amGrid.add(createAmenity("Pool", "\ud83c\udfca", false), 0, 1);
                amGrid.add(createAmenity("Security", "\ud83d\udee1\ufe0f", false), 1, 1);
                amGrid.add(createAmenity("Backup", "\u26a1", false), 2, 1);

                amenitiesSection.getChildren().addAll(amTitle, amGrid);

                // Properties Found Header
                HBox resultsHeader = new HBox();
                resultsHeader.setPadding(new Insets(10, 20, 0, 20));
                Label resultsTitle = new Label("124 Properties Found");
                resultsTitle.setTextFill(Color.WHITE);
                resultsTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region rSpacer = new Region();
                HBox.setHgrow(rSpacer, Priority.ALWAYS);
                Label sortBy = new Label("Sort by: Popular \u2304");
                sortBy.setTextFill(Color.web(TEXT_GRAY));
                sortBy.setFont(Font.font(12));
                resultsHeader.getChildren().addAll(resultsTitle, rSpacer, sortBy);

                // Property Result Cards
                VBox propertyList = new VBox(20);
                propertyList.setPadding(new Insets(0, 20, 20, 20));

                Property p1 = new Property("Luxury 2BR Apartment", "Westlands, Nairobi", "Ksh 45,000",
                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCmoqdFqj4fbelhpGNW5sBxu67T2r_SRnonToK4HJsiSM9z2ZJksIe5EXFlaaXniAZEgEj2mjg0AnwKEZERleElDWTfSHmlJt4dsIlhhCjlN3cgsXXtSzlwwe2P9AsdMdTZIncuqQhww9IY1-cqUVpbxMT8IyH_Dlfsf19XEnRCQ51bwm6KzZsPX7P6Pzxi3z7JXlbvfo5EWOBTCAevGNmiRZ3TYUcYV-VXhjgAmqEmMJC6fTwRNxrqLUHOOIRRaREAbZnzOV_MO-g",
                                true, "Featured");

                Property p2 = new Property("Urban Studio Lofts", "Kilimani, Nairobi", "Ksh 32,000",
                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCStI3MNHS8JJNCftkv1v3hpCqhtBT3R9njL1VwQ6BliWmhLM08_zaVM7zEj4hF_R0qPyoyipBL1PTXD53Ecc2_nCeFmcb1WpcOmI-BoaX-GyeRLW5M6M2jCEE5zZb23dZbGWt8WXQF5RFDanwTgFYwUk-GgouqXTslhxWZohr_39wvp52lvVxhNNWtl31ZVwfwTDPVJw_RKL3q-AJNRNpE1q2M9Fw6OQzmERDkYyQ5kizaOR4YihTkaT7xQqQ_tfbhjkHDepVBM1o",
                                false, null, "1 Bed", "1 Bath", "850 sqft");

                propertyList.getChildren().addAll(createPremiumCard(p1), createPremiumCard(p2));

                scrollContent.getChildren().addAll(priceSection, typeSection, bedroomSection, amenitiesSection,
                                resultsHeader,
                                propertyList);

                ScrollPane mainScroll = new ScrollPane(scrollContent);
                mainScroll.setFitToWidth(true);
                mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                mainScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                VBox layout = new VBox(0);
                layout.getChildren().addAll(headerArea, mainScroll);

                StackPane.setAlignment(resultsFab, Pos.BOTTOM_CENTER);

                // Add Map FAB as well
                Button mapFab = new Button("\ud83d\uddfa");
                mapFab.setStyle(
                                "-fx-background-color: white; -fx-text-fill: #101622; -fx-font-size: 20; -fx-background-radius: 30; -fx-min-width: 55; -fx-min-height: 55; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
                StackPane.setAlignment(mapFab, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(mapFab, new Insets(0, 20, 90, 0));

                mainStack.getChildren().addAll(layout, resultsFab, mapFab);
                getChildren().add(mainStack);
        }

        private Button createChip(String text, boolean active) {
                Button btn = new Button(text);
                btn.setPadding(new Insets(8, 20, 8, 20));
                if (active) {
                        btn.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-text-fill: white; -fx-background-radius: 20; -fx-border-color: "
                                        + PRIMARY
                                        + "; -fx-border-radius: 20;");
                } else {
                        btn.setStyle(
                                        "-fx-background-color: white; -fx-text-fill: #4b5563; -fx-background-radius: 20; -fx-border-color: #e5e7eb; -fx-border-radius: 20;");
                }
                return btn;
        }

        private VBox createAmenity(String name, String icon, boolean active) {
                VBox box = new VBox(5);
                box.setAlignment(Pos.CENTER);
                box.setPrefSize(110, 80);
                box.setCursor(javafx.scene.Cursor.HAND);
                if (active) {
                        box.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-border-color: " + PRIMARY
                                        + "; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-width: 2;");
                } else {
                        box.setStyle(
                                        "-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-background-radius: 12; -fx-border-radius: 12;");
                }

                Label iconLbl = new Label(icon);
                iconLbl.setStyle("-fx-font-size: 20;");
                iconLbl.setTextFill(active ? Color.web(PRIMARY) : Color.web("#9ca3af"));

                Label lbl = new Label(name);
                lbl.setFont(Font.font("System", FontWeight.BOLD, 12));
                lbl.setTextFill(active ? Color.web("#101622") : Color.web("#6b7280"));

                box.getChildren().addAll(iconLbl, lbl);
                return box;
        }

        private VBox createPremiumCard(Property p) {
                VBox card = new VBox(0);
                card.setStyle(
                                "-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

                Rectangle clip = new Rectangle();
                clip.setArcWidth(40);
                clip.setArcHeight(40);
                card.setClip(clip);

                card.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                        clip.setWidth(newVal.getWidth());
                        clip.setHeight(newVal.getHeight());
                });

                StackPane imgContainer = new StackPane();
                imgContainer.setPrefHeight(200);

                ImageView iv = new ImageView();
                try {
                        Image img = new Image(p.getImageUrl(), 400, 200, false, true);
                        iv.setImage(img);
                } catch (Exception e) {
                }
                iv.setFitWidth(400);
                iv.setFitHeight(200);
                iv.setPreserveRatio(false);

                Label fav = new Label("\u2661");
                fav.setStyle(
                                "-fx-background-color: rgba(0,0,0,0.2); -fx-background-radius: 25; -fx-padding: 8; -fx-text-fill: white; -fx-font-size: 18;");
                StackPane.setAlignment(fav, Pos.TOP_RIGHT);
                StackPane.setMargin(fav, new Insets(15));

                if (p.getTag() != null) {
                        Label tag = new Label(p.getTag().toUpperCase());
                        tag.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-text-fill: white; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 4 10; -fx-background-radius: 4;");
                        StackPane.setAlignment(tag, Pos.BOTTOM_LEFT);
                        StackPane.setMargin(tag, new Insets(15));
                        imgContainer.getChildren().add(tag);
                }

                imgContainer.getChildren().addAll(iv, fav);

                VBox content = new VBox(10);
                content.setPadding(new Insets(15));

                HBox topRow = new HBox();
                VBox titleBox = new VBox(2);
                Label name = new Label(p.getName());
                name.setFont(Font.font("System", FontWeight.BOLD, 16));
                name.setTextFill(Color.web("#101622"));

                HBox locBox = new HBox(4);
                locBox.setAlignment(Pos.CENTER_LEFT);
                Label locIcon = new Label("\ud83d\udccd");
                locIcon.setStyle("-fx-font-size: 12;");
                Label loc = new Label(p.getLocation());
                loc.setTextFill(Color.web("#6b7280"));
                loc.setFont(Font.font(13));
                locBox.getChildren().addAll(locIcon, loc);
                titleBox.getChildren().addAll(name, locBox);

                VBox priceBox = new VBox(0);
                priceBox.setAlignment(Pos.TOP_RIGHT);
                Label price = new Label(p.getPrice());
                price.setFont(Font.font("System", FontWeight.BOLD, 18));
                price.setTextFill(Color.web(PRIMARY));
                Label perMo = new Label("per month");
                perMo.setFont(Font.font(10));
                perMo.setTextFill(Color.web("#9ca3af"));
                priceBox.getChildren().addAll(price, perMo);

                HBox.setHgrow(titleBox, Priority.ALWAYS);
                topRow.getChildren().addAll(titleBox, priceBox);

                HBox infoRow = new HBox(15);
                infoRow.setPadding(new Insets(10, 0, 0, 0));
                infoRow.setStyle("-fx-border-color: #f3f4f6; -fx-border-width: 1 0 0 0;");

                infoRow.getChildren().addAll(
                                createInfoItem("\ud83d\udecf", p.getBeds()),
                                createInfoItem("\ud83d\udebf", p.getBaths()),
                                createInfoItem("\u25a2", p.getSqft()));

                Region iSpacer = new Region();
                HBox.setHgrow(iSpacer, Priority.ALWAYS);
                Button bookBtn = new Button("Book Now");
                bookBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 6 15; -fx-font-size: 11;");

                infoRow.getChildren().addAll(iSpacer, bookBtn);

                content.getChildren().addAll(topRow, infoRow);
                card.getChildren().addAll(imgContainer, content);
                return card;
        }

        private HBox createInfoItem(String icon, String text) {
                HBox box = new HBox(4);
                box.setAlignment(Pos.CENTER_LEFT);
                Label i = new Label(icon);
                i.setStyle("-fx-font-size: 14;");
                i.setTextFill(Color.web("#4b5563"));
                Label t = new Label(text);
                t.setFont(Font.font(12));
                t.setTextFill(Color.web("#4b5563"));
                box.getChildren().addAll(i, t);
                return box;
        }
}
