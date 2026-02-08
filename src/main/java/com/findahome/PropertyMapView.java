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

public class PropertyMapView extends StackPane {

        private static final String BACKGROUND_DARK = "#0d1117";
        private static final String PRIMARY = "#135bec";
        private static final String CARD_BG = "#161b22";

        public PropertyMapView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Map Background
                ImageView mapBackground = new ImageView();
                try {
                        // Enable background loading (last parameter true)
                        mapBackground.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDwtauKyKjYN3jluMyQ6Rbeype-DIhS6PV91dnbXPj5LzqdH76LstPtprFPYM4XWygs3tCi2efX3jLUpW3dmnoWvK8KyIIfWgZa5S86HWJlYUxbzwhK05BG8vflEee6ja-OqAERejqYgWypKpEnIDot0efCAylWTY56B9hx42qxw_2y4Dk_CLQM2SfdvPldN6_L8qKtiWNPBAcd_hIPSH6fq5gyssD2S3zzxccIoF7HAEwBFR4uhttXIdVeuQCNkCgazTW1nU9CzOo",
                                        800, 1200, false, true, true));
                } catch (Exception e) {
                }

                // Bind to StackPane size to avoid forcing layout growth
                mapBackground.fitWidthProperty().bind(widthProperty());
                mapBackground.fitHeightProperty().bind(heightProperty());
                mapBackground.setPreserveRatio(false);
                mapBackground.setOpacity(0.4);
                // IMPORTANT: Set unmanaged to prevent StackPane from using Image size for its
                // own preferred size calculation
                mapBackground.setManaged(false);

                // Clip to bounds to prevent overflowing into navigation bar
                Rectangle clipRect = new Rectangle();
                clipRect.widthProperty().bind(widthProperty());
                clipRect.heightProperty().bind(heightProperty());
                setClip(clipRect);

                // Map Interaction Layer
                Pane interactionLayer = new Pane();
                interactionLayer.getChildren().addAll(
                                createClusterMarker("15", 100, 240, 48),
                                createClusterMarker("42", 240, 400, 56),
                                createSinglePin("$1.2k", 160, 360));

                // Header Overlay
                VBox topOverlay = new VBox(12);
                topOverlay.setPadding(new Insets(50, 20, 0, 20));
                topOverlay.setPickOnBounds(false);

                HBox searchContainer = new HBox(5);
                searchContainer.setAlignment(Pos.CENTER_LEFT);
                searchContainer.setPadding(new Insets(8, 12, 8, 12));
                searchContainer.setStyle(
                                "-fx-background-color: " + CARD_BG
                                                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 16; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 5);");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 26; -fx-cursor: hand; -fx-padding: 0 10 0 0;");
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web("#ffffff", 0.6));
                searchIcon.setStyle("-fx-font-size: 16;");

                TextField tf = new TextField("Westlands, Nairobi");
                tf.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 0 10; -fx-font-size: 14;");
                HBox.setHgrow(tf, Priority.ALWAYS);

                Button tuneBtn = new Button("\u2312");
                tuneBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-size: 20; -fx-cursor: hand;");
                searchContainer.getChildren().addAll(backBtn, searchIcon, tf, tuneBtn);

                HBox chips = new HBox(8);
                chips.getChildren().addAll(
                                createFilterChip("Apartments", false),
                                createFilterChip("Price", false),
                                createFilterChip("2+ Bedrooms", true));
                // Breadcrumb / Location Header
                HBox breadcrumb = new HBox(5);
                breadcrumb.setAlignment(Pos.CENTER_LEFT);
                breadcrumb.setPadding(new Insets(10, 15, 10, 15));
                breadcrumb.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 25; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 25;");
                breadcrumb.setMaxWidth(Double.MAX_VALUE); // Let it expand or use distinct width
                // Actually better to have it sized to content or fixed width? wrapper VBox has
                // padding 20.
                // Let's make it inline-block-ish or fill width.

                Label locationIcon = new Label("\ud83d\udccd");
                locationIcon.setTextFill(Color.web(PRIMARY));
                locationIcon.setStyle("-fx-font-size: 16;");

                Label locationText = new Label("Westlands, Nairobi");
                locationText.setTextFill(Color.WHITE);
                locationText.setFont(Font.font("System", FontWeight.BOLD, 14));

                Label chevron = new Label("\u2304"); // Down arrow
                chevron.setTextFill(Color.GRAY);
                chevron.setStyle("-fx-font-size: 12; -fx-padding: 0 0 0 5;");

                Region spacerBC = new Region();
                HBox.setHgrow(spacerBC, Priority.ALWAYS);

                breadcrumb.getChildren().addAll(locationIcon, locationText, chevron, spacerBC);
                breadcrumb.setCursor(javafx.scene.Cursor.HAND);
                breadcrumb.setOnMouseClicked(e -> MainApp.navigateCachedFullScreen("ward_selection",
                                WardBoundarySelectionView::new));

                VBox.setMargin(breadcrumb, new Insets(0, 0, 10, 0));

                topOverlay.getChildren().addAll(breadcrumb, searchContainer, chips);

                // Map Controls
                VBox controls = new VBox(10);
                controls.setAlignment(Pos.CENTER);
                controls.setPickOnBounds(false);
                StackPane.setAlignment(controls, Pos.TOP_RIGHT);
                StackPane.setMargin(controls, new Insets(180, 20, 0, 0));

                VBox zoomGrp = new VBox(0);
                zoomGrp.setStyle(
                                "-fx-background-color: " + CARD_BG
                                                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");
                Button p = createSquareBtn("+", true);
                Button m = createSquareBtn("-", false);
                zoomGrp.getChildren().addAll(p, m);

                Button loc = createSquareBtn("\ud83c\udfaf", false);
                loc.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12; -fx-text-fill: white;");

                controls.getChildren().addAll(zoomGrp, loc);

                // Switch to List FAB
                Button switchBtn = new Button("\u2630  Switch to List");
                switchBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 30; -fx-padding: 12 24; -fx-font-weight: bold; -fx-cursor: hand;");
                StackPane.setAlignment(switchBtn, Pos.BOTTOM_CENTER);
                StackPane.setMargin(switchBtn, new Insets(0, 0, 360, 0));
                switchBtn.setOnAction(e -> MainApp.showHome());

                // Bottom Sheet
                VBox bottomSheet = new VBox(15);
                bottomSheet.setPrefHeight(340);
                bottomSheet.setMaxHeight(340);
                bottomSheet.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-background-radius: 32 32 0 0; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");
                StackPane.setAlignment(bottomSheet, Pos.BOTTOM_CENTER);

                Region handle = new Region();
                handle.setPrefSize(40, 4);
                handle.setMaxSize(40, 4);
                handle.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 2;");
                VBox handleBox = new VBox(handle);
                handleBox.setAlignment(Pos.CENTER);
                handleBox.setPadding(new Insets(12, 0, 5, 0));

                HBox sheetHeader = new HBox();
                sheetHeader.setPadding(new Insets(0, 24, 0, 24));
                sheetHeader.setAlignment(Pos.CENTER_LEFT);
                VBox titleBox = new VBox(2);
                Label countLbl = new Label("15 properties found");
                countLbl.setTextFill(Color.WHITE);
                countLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
                Label areaLbl = new Label("Westlands, Nairobi");
                areaLbl.setTextFill(Color.web("#ffffff", 0.5));
                areaLbl.setFont(Font.font(13));
                titleBox.getChildren().addAll(countLbl, areaLbl);

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label sort = new Label("Sort by: Price");
                sort.setTextFill(Color.web(PRIMARY));
                sort.setFont(Font.font("System", FontWeight.MEDIUM, 13));
                sheetHeader.getChildren().addAll(titleBox, s, sort);

                VBox cardList = new VBox(12);
                cardList.setPadding(new Insets(0, 20, 40, 20));
                cardList.getChildren().addAll(
                                createListCard("Pine Gardens Residences", "$1,200", "4.8",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuC_bgHqVM_iUYztr7dRYU30EAFbapBKbTarCbg-p1m7x-aD6NUZ9NX1QMTN6iK4U75m4gDRMihGGM0FwWW07D-R_thVRYceq95x9YcmgJSxZr2MGQMeDX-WlI9CH90bweV8JY9POH9MbJQJE-5Fg_HrcPgQrDzAtwU7-Xu0VWEfQ9a5fcCfR53q1NSHXaEkovVoIwVBldzEG3FCeBo53KVbJsrXkhC2D4A2znVGmdkIVgrahHrSchbOZRgg_M00lhcE3FuXqi--gLQ"),
                                createListCard("The Habitat Apartments", "$1,850", "4.9",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuBa0u4FBx-rn5II63PtCG3RLaHyj6OI8vTrKnP_iaU3YH_z0_l7DnnxbplTVfxCYPa1JHO0Jj8tvU5pLAGMPAhMisuF6hc_y8GgEw8bL_mIauCMbklfHp98tYELsTWF9z0EGKuCx5zCWP28prAgikjmEp79qbAxVJDA1LmiPSlk_CvHlIxMHBBBoomjGNtY1fYAeN_3_VwkA4YVOoqcpYbcKxXTPkJnEX0rQkbUqdiKXa611fdFys0Zc3j9opZwu7h2xUI7aAyM6i8"),
                                createListCard("Urban Loft Studios", "$850", "4.6",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuARwyL1ZkzksMuDk-l1g1KoJEg0hHdw1WVNDxWexlgO8lN4lr7W_mAv23sDSC6J1C1T_3dMLkMh2sc-5EzkwPb4MhMdNt_hTrpPQV8vNdTuzkTaqkWuC25cNiFHBMbSIUyf0gZOIJBb_dFPLUs1QQVv4sSa8lDxlrC2okSvDiApIVs-srnWF_S4BSZi51Ij6g88D-P2MQF7BfwqnEsOz14d1dHexOdF7XMF47YOeS9rLCARuXScEGnlRjrvLBihTMmYALaWlwauilc"));
                ScrollPane cardScroll = new ScrollPane(cardList);
                cardScroll.setFitToWidth(true);
                cardScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                cardScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                cardScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                bottomSheet.getChildren().addAll(handleBox, sheetHeader, cardScroll);

                getChildren().addAll(mapBackground, interactionLayer, topOverlay, controls, switchBtn, bottomSheet);
        }

        private StackPane createClusterMarker(String count, double x, double y, double size) {
                StackPane c = new StackPane();
                c.setLayoutX(x);
                c.setLayoutY(y);
                c.setPrefSize(size, size);
                c.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: " + (size / 2)
                                + "; -fx-border-color: rgba(19, 127, 236, 0.3); -fx-border-width: 4; -fx-border-radius: "
                                + (size / 2) + ";");
                Label l = new Label(count);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.BOLD, 14));
                c.getChildren().add(l);
                c.setCursor(javafx.scene.Cursor.HAND);
                return c;
        }

        private VBox createSinglePin(String price, double x, double y) {
                VBox pin = new VBox(2);
                pin.setAlignment(Pos.CENTER);
                pin.setLayoutX(x);
                pin.setLayoutY(y);

                StackPane bubble = new StackPane();
                bubble.setPadding(new Insets(4, 8, 4, 8));
                bubble.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 8;");
                Label l = new Label(price);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.BOLD, 12));
                bubble.getChildren().add(l);

                Label icon = new Label("\ud83d\udccd");
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 20;");

                pin.getChildren().addAll(bubble, icon);
                return pin;
        }

        private Button createFilterChip(String text, boolean active) {
                Button b = new Button(text + (active ? "" : " \u2304"));
                b.setStyle("-fx-background-color: " + (active ? PRIMARY : "rgba(16, 25, 34, 0.9)")
                                + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 8 16; -fx-font-size: 13; -fx-font-weight: bold; -fx-border-color: "
                                + (active ? "transparent" : "rgba(255,255,255,0.05)") + "; -fx-border-radius: 8;");
                return b;
        }

        private Button createSquareBtn(String text, boolean borderBottom) {
                Button b = new Button(text);
                b.setPrefSize(48, 48);
                b.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand; -fx-border-color: "
                                + (borderBottom ? "rgba(255,255,255,0.05)" : "transparent")
                                + "; -fx-border-width: 0 0 1 0;");
                return b;
        }

        private HBox createListCard(String title, String price, String rating, String imgUrl) {
                HBox card = new HBox(15);
                card.setPadding(new Insets(12));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 16;");

                StackPane imgBox = new StackPane();
                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(imgUrl, 96, 96, false, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(96);
                iv.setFitHeight(96);
                Rectangle clip = new Rectangle(96, 96);
                clip.setArcWidth(16);
                clip.setArcHeight(16);
                iv.setClip(clip);
                imgBox.getChildren().add(iv);

                VBox details = new VBox(4);
                HBox.setHgrow(details, Priority.ALWAYS);

                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 14));

                Label sub = new Label("2.5 km \u2022 Lower Kabete");
                sub.setTextFill(Color.web("#ffffff", 0.5));
                sub.setFont(Font.font(11));

                HBox specs = new HBox(8);
                specs.getChildren().addAll(createTag("2 Bed"), createTag("1 Bath"));

                Region spacer = new Region();
                VBox.setVgrow(spacer, Priority.ALWAYS);

                HBox bottom = new HBox();
                bottom.setAlignment(Pos.BOTTOM_LEFT);
                Label p = new Label(price);
                p.setTextFill(Color.web(PRIMARY));
                p.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label unit = new Label("/mo");
                unit.setTextFill(Color.web("#ffffff", 0.5));
                unit.setFont(Font.font(10));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label r = new Label("\u2b50 " + rating);
                r.setTextFill(Color.WHITE);
                r.setFont(Font.font(12));

                bottom.getChildren().addAll(new HBox(p, unit), s, r);

                details.getChildren().addAll(t, sub, specs, spacer, bottom);
                card.getChildren().addAll(imgBox, details);
                card.setCursor(javafx.scene.Cursor.HAND);
                card.setOnMouseClicked(e -> MainApp.navigateTo(
                                new PropertyDetailView(
                                                new Property(title, "Nairobi", price, imgUrl, true, "Apartment"))));
                return card;
        }

        private Label createTag(String txt) {
                Label l = new Label(txt);
                l.setTextFill(Color.web(PRIMARY));
                l.setPadding(new Insets(2, 6, 2, 6));
                l.setStyle("-fx-background-color: rgba(19, 127, 236, 0.2); -fx-background-radius: 4; -fx-font-size: 10;");
                return l;
        }
}
