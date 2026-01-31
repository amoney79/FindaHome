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

public class PropertyDetailView extends VBox {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#135bec";

        public PropertyDetailView(Property property) {
                setSpacing(0);
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Top Banner Image
                StackPane banner = new StackPane();
                banner.setPrefHeight(350);

                ImageView iv = new ImageView();
                try {
                        Image img = new Image(property.getImageUrl(), 400, 350, false, true);
                        iv.setImage(img);
                } catch (Exception e) {
                }
                iv.setFitWidth(400);
                iv.setFitHeight(350);
                iv.setPreserveRatio(false);

                HBox topActions = new HBox(10);
                topActions.setPadding(new Insets(15));
                topActions.setAlignment(Pos.CENTER_LEFT);
                Label back = new Label("\u2190"); // Back arrow
                back.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: white; -fx-padding: 8 12; -fx-background-radius: 20; -fx-font-size: 18; -fx-cursor: hand;");
                back.setOnMouseClicked(e -> MainApp.showHome());
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label fav = new Label("\u2661");
                fav.setStyle(
                                "-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: white; -fx-padding: 8; -fx-background-radius: 20; -fx-font-size: 18; -fx-cursor: hand;");
                topActions.getChildren().addAll(back, spacer, fav);

                banner.getChildren().addAll(iv, topActions);
                StackPane.setAlignment(topActions, Pos.TOP_CENTER);

                // Content Area
                VBox content = new VBox(20);
                content.setPadding(new Insets(25, 20, 150, 20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-background-radius: 30 30 0 0;");
                content.setTranslateY(-30);

                Label name = new Label(property.getName());
                name.setTextFill(Color.WHITE);
                name.setFont(Font.font("System", FontWeight.BOLD, 22));

                HBox priceBox = new HBox(5);
                priceBox.setAlignment(Pos.BASELINE_LEFT);
                Label p = new Label(property.getPrice());
                p.setTextFill(Color.web(PRIMARY));
                p.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label mo = new Label("/ month");
                mo.setTextFill(Color.GRAY);
                priceBox.getChildren().addAll(p, mo);

                Label desc = new Label(
                                "Experience high-end urban living in this sun-drenched sanctuary. Featuring floor-to-ceiling windows, premium smart home integration, and breathtaking city views...");
                desc.setTextFill(Color.LIGHTGRAY);
                desc.setWrapText(true);
                desc.setFont(Font.font(14));

                // Amenities
                VBox amenities = new VBox(10);
                Label amTitle = new Label("Amenities");
                amTitle.setTextFill(Color.WHITE);
                amTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                HBox amIcons = new HBox(20);
                amIcons.getChildren().addAll(
                                createAmIcon("WiFi", "\ud83d\udcf6"),
                                createAmIcon("Pool", "\ud83c\udfca"),
                                createAmIcon("Gym", "\ud83c\udfcb\ufe0f"));
                amenities.getChildren().addAll(amTitle, amIcons);

                content.getChildren().addAll(name, priceBox, desc, amenities);

                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Bottom Bar
                HBox footer = new HBox(15);
                footer.setPadding(new Insets(15, 20, 30, 20));
                footer.setAlignment(Pos.CENTER_LEFT);
                footer.setStyle(
                                "-fx-background-color: rgba(16, 22, 34, 0.9); -fx-border-color: #333; -fx-border-width: 1 0 0 0;");

                VBox priceFooter = new VBox(2);
                Label total = new Label("Total");
                total.setTextFill(Color.GRAY);
                total.setFont(Font.font(10));
                Label finalPrice = new Label(property.getPrice());
                finalPrice.setTextFill(Color.WHITE);
                finalPrice.setFont(Font.font("System", FontWeight.BOLD, 18));
                priceFooter.getChildren().addAll(total, finalPrice);

                Button bookBtn = new Button("Book Viewing");
                bookBtn.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(bookBtn, Priority.ALWAYS);
                bookBtn.setPrefHeight(50);
                bookBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-font-weight: bold;");
                bookBtn.setOnAction(e -> MainApp.navigateTo(new ScheduleView()));

                footer.getChildren().addAll(priceFooter, bookBtn);

                getChildren().addAll(banner, scroll, footer);
        }

        private VBox createAmIcon(String name, String icon) {
                VBox box = new VBox(8);
                box.setAlignment(Pos.CENTER);
                StackPane iconBg = new StackPane();
                iconBg.setPrefSize(50, 50);
                iconBg.setStyle("-fx-background-color: #1a1f2e; -fx-background-radius: 12;");
                Label lbl = new Label(icon);
                lbl.setStyle("-fx-font-size: 20;");
                lbl.setTextFill(Color.web(PRIMARY));
                iconBg.getChildren().add(lbl);
                Label n = new Label(name);
                n.setTextFill(Color.GRAY);
                n.setFont(Font.font(10));
                box.getChildren().addAll(iconBg, n);
                return box;
        }
}
