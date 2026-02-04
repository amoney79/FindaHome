package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PropertyDetailView extends StackPane {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#135bec";
        private static final String TEXT_GRAY = "#9ca3af";

        public PropertyDetailView(Property property) {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);

                // Scrollable Content
                VBox scrollContent = new VBox(0);
                scrollContent.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Top Banner Area (Carousel Simulation)
                StackPane bannerArea = new StackPane();
                bannerArea.setPrefHeight(500);

                ImageView mainImg = new ImageView();
                try {
                        Image img = new Image(property.getImageUrl(), 430, 500, false, true, true);
                        mainImg.setImage(img);
                } catch (Exception e) {
                }
                mainImg.setFitWidth(430);
                mainImg.setFitHeight(500);
                mainImg.setPreserveRatio(false);

                // Gradient Overlay for Image
                Region overlay = new Region();
                overlay.setStyle(
                                "-fx-background-color: linear-gradient(to bottom, rgba(0,0,0,0.4) 0%, transparent 20%, transparent 80%, "
                                                + BACKGROUND_DARK + " 100%);");

                // Pagination Dots
                HBox pagination = new HBox(6);
                pagination.setAlignment(Pos.CENTER);
                pagination.setPadding(new Insets(0, 0, 40, 0));
                Region dot1 = new Region();
                dot1.setPrefSize(24, 6);
                dot1.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
                Region dot2 = new Region();
                dot2.setPrefSize(6, 6);
                dot2.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 3;");
                Region dot3 = new Region();
                dot3.setPrefSize(6, 6);
                dot3.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 3;");
                pagination.getChildren().addAll(dot1, dot2, dot3);
                StackPane.setAlignment(pagination, Pos.BOTTOM_CENTER);

                bannerArea.getChildren().addAll(mainImg, overlay, pagination);

                // Details Content
                VBox detailsContent = new VBox(25);
                detailsContent.setPadding(new Insets(30, 20, 150, 20));
                detailsContent.setStyle(
                                "-fx-background-color: " + BACKGROUND_DARK + "; -fx-background-radius: 24 24 0 0;");
                detailsContent.setTranslateY(-25);

                // Verified Badge & Title
                VBox header = new VBox(8);
                HBox badge = new HBox(4);
                badge.setAlignment(Pos.CENTER_LEFT);
                badge.setPadding(new Insets(4, 8, 4, 8));
                badge.setMaxWidth(Region.USE_PREF_SIZE);
                badge.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-background-radius: 4;");
                Label vIcon = new Label("\u2705"); // Verified icon
                vIcon.setTextFill(Color.web(PRIMARY));
                vIcon.setStyle("-fx-font-size: 10;");
                Label vText = new Label("VERIFIED LISTING");
                vText.setStyle("-fx-text-fill: " + PRIMARY + "; -fx-font-size: 10; -fx-font-weight: bold;");
                badge.getChildren().addAll(vIcon, vText);

                Label title = new Label(property.getName());
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 24));
                title.setWrapText(true);
                header.getChildren().addAll(badge, title);

                // Price
                HBox priceBox = new HBox(4);
                priceBox.setAlignment(Pos.BASELINE_LEFT);
                Label price = new Label(property.getPrice());
                price.setTextFill(Color.web(PRIMARY));
                price.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label perMonth = new Label("/ month");
                perMonth.setTextFill(Color.web(TEXT_GRAY));
                perMonth.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                priceBox.getChildren().addAll(price, perMonth);

                // Description
                TextFlow descFlow = new TextFlow();
                Label descText = new Label(
                                "Experience high-end urban living in this sun-drenched sanctuary. Featuring floor-to-ceiling windows, premium smart home integration, and breathtaking city views... ");
                descText.setTextFill(Color.web("#d4d4d8"));
                descText.setFont(Font.font(14));
                Hyperlink readMore = new Hyperlink("Read more");
                readMore.setStyle("-fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-padding: 0; -fx-underline: false;");
                descFlow.getChildren().addAll(descText, readMore);

                // Amenities
                VBox amenitiesSection = new VBox(15);
                Label amTitle = new Label("Amenities");
                amTitle.setTextFill(Color.WHITE);
                amTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                GridPane amGrid = new GridPane();
                amGrid.setHgap(20);
                amGrid.setVgap(15);
                amGrid.add(createAmenityItem("Free WiFi", "\ud83d\udcf6"), 0, 0);
                amGrid.add(createAmenityItem("Parking", "\ud83c\udd7f\ufe0f"), 1, 0);
                amGrid.add(createAmenityItem("Pool", "\ud83c\udfca"), 2, 0);
                amGrid.add(createAmenityItem("Gym", "\ud83c\udfcb\ufe0f"), 3, 0);
                amenitiesSection.getChildren().addAll(amTitle, amGrid);

                // Location
                VBox locationSection = new VBox(15);
                HBox locHeader = new HBox();
                Label locTitle = new Label("Location");
                locTitle.setTextFill(Color.WHITE);
                locTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region locSpacer = new Region();
                HBox.setHgrow(locSpacer, Priority.ALWAYS);
                Label viewMap = new Label("View map");
                viewMap.setTextFill(Color.web(PRIMARY));
                viewMap.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                locHeader.getChildren().addAll(locTitle, locSpacer, viewMap);

                StackPane mapContainer = new StackPane();
                mapContainer.setPrefHeight(160);
                mapContainer.setStyle("-fx-background-radius: 16;");
                Rectangle mapClip = new Rectangle();
                mapClip.setArcWidth(32);
                mapClip.setArcHeight(32);
                mapContainer.setClip(mapClip);
                mapContainer.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                        mapClip.setWidth(newVal.getWidth());
                        mapClip.setHeight(newVal.getHeight());
                });

                ImageView mapImg = new ImageView();
                try {
                        Image mImg = new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDmJKi3AjCR1tsFqjXnk8X10w99rFPOyb_vJCEmNpq90mdmykPyJFYThswbvyF1zam4SkcuiMAE67JO8RwF7HkYV5Wmst3h1nx0UYBR3l1Nn8Fote_CNUyiJ1uRGSx37gXe_CNLMio3HC1h9cXJFZP1aQ7FyD5pvXr2iH8Nny_DuiSEgmEXpJnKT6YgfowuMOdBNyNN-_yI89HnYS1Els2s5AUpLi1lPY_KxYp9bFEJqRfVyl5k-3W3wouiPcGVrAhAJ4WgPr_XMrw",
                                        400, 160, false, true, true);
                        mapImg.setImage(mImg);
                } catch (Exception e) {
                }
                mapImg.setFitWidth(400);
                mapImg.setFitHeight(160);
                mapImg.setPreserveRatio(false);

                StackPane pin = new StackPane();
                Circle outer = new Circle(20, Color.web(PRIMARY, 0.3));
                outer.setStroke(Color.web(PRIMARY));
                outer.setStrokeWidth(2);
                Circle inner = new Circle(6, Color.web(PRIMARY));
                pin.getChildren().addAll(outer, inner);

                HBox locBadge = new HBox(6);
                locBadge.setAlignment(Pos.CENTER_LEFT);
                locBadge.setPadding(new Insets(6, 12, 6, 12));
                locBadge.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-background-radius: 8;");
                Label lIcon = new Label("\ud83d\udccd");
                lIcon.setTextFill(Color.web(PRIMARY));
                Label lText = new Label(property.getLocation());
                lText.setTextFill(Color.WHITE);
                lText.setFont(Font.font(12));
                locBadge.getChildren().addAll(lIcon, lText);
                StackPane.setAlignment(locBadge, Pos.BOTTOM_LEFT);
                StackPane.setMargin(locBadge, new Insets(12));

                mapContainer.getChildren().addAll(mapImg, pin, locBadge);
                locationSection.getChildren().addAll(locHeader, mapContainer);

                // Agent Section
                VBox agentCard = new VBox(0);
                agentCard.setPadding(new Insets(15));
                agentCard.setStyle("-fx-background-color: #1a1f2e; -fx-background-radius: 16;");

                HBox agentInfo = new HBox(12);
                agentInfo.setAlignment(Pos.CENTER_LEFT);

                StackPane avatarBox = new StackPane();
                avatarBox.setCursor(javafx.scene.Cursor.HAND);
                avatarBox.setOnMouseClicked(e -> MainApp.navigateTo(new AgentRatingProfileView()));
                Circle avatarClip = new Circle(24, 24, 24);
                ImageView avatar = new ImageView();
                try {
                        avatar.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuADYVf8eJncumdqgsYLbFLdbO5JZWBSUaW6QfAzQj_wCxAKckepu4JQsmELaxJs1c3xJSvwrtqDYG6h7Rvc0b7V9eZLOcDBrvt-QP61mEq4NKlEKPI0MzRsUpU5gnbicTnnjMtbLHkT0ivL0czKWbCHzepGr-D5cpqtx6wgEih7-1g2PVtq123pu2oIstuKRHLOLtwzxIAb59x6p5scEi-Y-wZ2X_EZfRjm3C4piglsRhbnbrX42ozb61TFblD2ndYxjnLQ65Hxhts",
                                        48, 48, true, true, true));
                } catch (Exception e) {
                }
                avatar.setClip(avatarClip);
                avatarBox.getChildren().add(avatar);

                VBox agentMeta = new VBox(2);
                Label agentName = new Label("Kelvin Mwangi");
                agentName.setTextFill(Color.WHITE);
                agentName.setFont(Font.font("System", FontWeight.BOLD, 14));
                agentName.setCursor(javafx.scene.Cursor.HAND);
                agentName.setOnMouseClicked(e -> MainApp.navigateTo(new AgentRatingProfileView()));
                HBox ratingBox = new HBox(4);
                ratingBox.setAlignment(Pos.CENTER_LEFT);
                ratingBox.setCursor(javafx.scene.Cursor.HAND);
                ratingBox.setOnMouseClicked(e -> MainApp.navigateTo(new PropertyReviewsView()));
                Label star = new Label("\u2b50");
                star.setStyle("-fx-font-size: 10;");
                Label ratingText = new Label("4.9 (124 reviews)");
                ratingText.setTextFill(Color.web(TEXT_GRAY));
                ratingText.setFont(Font.font(12));
                ratingBox.getChildren().addAll(star, ratingText);
                agentMeta.getChildren().addAll(agentName, ratingBox);

                Region agentSpacer = new Region();
                HBox.setHgrow(agentSpacer, Priority.ALWAYS);

                HBox agentActions = new HBox(8);
                Button callBtn = createCircleBtn("\ud83d\udcde");
                Button msgBtn = createCircleBtn("\ud83d\udcac");
                agentActions.getChildren().addAll(callBtn, msgBtn);

                agentInfo.getChildren().addAll(avatarBox, agentMeta, agentSpacer, agentActions);
                agentCard.getChildren().add(agentInfo);

                detailsContent.getChildren().addAll(header, priceBox, descFlow, amenitiesSection, locationSection,
                                agentCard);
                scrollContent.getChildren().addAll(bannerArea, detailsContent);

                ScrollPane scrollScroll = new ScrollPane(scrollContent);
                scrollScroll.setFitToWidth(true);
                scrollScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                // Bottom Bar
                HBox footer = new HBox(20);
                footer.setAlignment(Pos.CENTER_LEFT);
                footer.setPadding(new Insets(15, 20, 30, 20));
                footer.setStyle("-fx-background-color: rgba(16, 22, 34, 0.9); -fx-background-radius: 0; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

                VBox footerPrice = new VBox(2);
                Label totalLbl = new Label("TOTAL/MO");
                totalLbl.setTextFill(Color.web(TEXT_GRAY));
                totalLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                Label finalPriceLbl = new Label(property.getPrice());
                finalPriceLbl.setTextFill(Color.WHITE);
                finalPriceLbl.setFont(Font.font("System", FontWeight.BOLD, 22));
                footerPrice.getChildren().addAll(totalLbl, finalPriceLbl);

                Button bookBtn = new Button("Book Viewing \ud83d\udcc5");
                HBox.setHgrow(bookBtn, Priority.ALWAYS);
                bookBtn.setMaxWidth(Double.MAX_VALUE);
                bookBtn.setPrefHeight(60);
                bookBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16;");
                bookBtn.setOnAction(e -> MainApp.navigateTo(new ScheduleView()));

                footer.getChildren().addAll(footerPrice, bookBtn);

                layout.getChildren().addAll(scrollScroll);

                // Top Navigation (Nav Overlay)
                HBox topNav = new HBox();
                topNav.setPadding(new Insets(15));
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPickOnBounds(false); // Let clicks pass if not on button

                Button backBtn = createNavBtn("\u2039");
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Region navSpacer = new Region();
                HBox.setHgrow(navSpacer, Priority.ALWAYS);

                HBox navActions = new HBox(8);
                Button favBtn = createNavBtn("\u2661");
                Button shareBtn = createNavBtn("\ud83d\udce4");
                shareBtn.setOnAction(e -> {
                        SharePropertyView shareSheet = new SharePropertyView(property,
                                        () -> getChildren().removeIf(node -> node instanceof SharePropertyView));
                        getChildren().add(shareSheet);
                });
                navActions.getChildren().addAll(favBtn, shareBtn);

                topNav.getChildren().addAll(backBtn, navSpacer, navActions);

                getChildren().addAll(layout, topNav, footer);
                StackPane.setAlignment(topNav, Pos.TOP_CENTER);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
        }

        private VBox createAmenityItem(String text, String iconCode) {
                VBox box = new VBox(8);
                box.setAlignment(Pos.CENTER);
                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(48, 48);
                iconBox.setStyle("-fx-background-color: #1a1f2e; -fx-background-radius: 12;");
                Label icon = new Label(iconCode);
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 20;");
                iconBox.getChildren().add(icon);
                Label lbl = new Label(text);
                lbl.setTextFill(Color.web(TEXT_GRAY));
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 11));
                box.getChildren().addAll(iconBox, lbl);
                return box;
        }

        private Button createNavBtn(String icon) {
                Button btn = new Button(icon);
                btn.setStyle("-fx-background-color: rgba(16, 22, 34, 0.4); -fx-text-fill: white; -fx-font-size: 20; -fx-min-width: 40; -fx-min-height: 40; -fx-background-radius: 20; -fx-padding: 0;");
                btn.setCursor(javafx.scene.Cursor.HAND);
                return btn;
        }

        private Button createCircleBtn(String icon) {
                Button btn = new Button(icon);
                btn.setStyle("-fx-background-color: white; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-size: 16; -fx-min-width: 40; -fx-min-height: 40; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
                btn.setCursor(javafx.scene.Cursor.HAND);
                return btn;
        }
}
