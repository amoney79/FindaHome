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

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.beans.property.*;

public class PropertyFeedView extends VBox {

        private static final String PRIMARY = "#13ec5b";
        private static final String CARD_BG = "#1c271f";
        private static final String TEXT_GRAY = "#9db9a6";

        private FlowPane propertyList;
        private boolean isLoading = false;
        private Label loadingLabel;

        private DoubleProperty cardWidth = new SimpleDoubleProperty(360);
        private IntegerProperty columns = new SimpleIntegerProperty(1);

        public PropertyFeedView() {
                setSpacing(20);
                setPadding(new Insets(10, 0, 100, 0));

                // Breadcrumb / Location Header
                HBox breadcrumb = new HBox(5);
                breadcrumb.setAlignment(Pos.CENTER_LEFT);
                breadcrumb.setPadding(new Insets(0, 20, 0, 20));

                Label locationIcon = new Label("\ud83d\udccd");
                locationIcon.setTextFill(Color.web(PRIMARY));

                Label locationText = new Label("Westlands, Nairobi");
                locationText.setTextFill(Color.WHITE);
                locationText.setFont(Font.font("System", FontWeight.BOLD, 14));

                Label chevron = new Label("\u2304"); // Down arrow
                chevron.setTextFill(Color.GRAY);

                breadcrumb.getChildren().addAll(locationIcon, locationText, chevron);
                breadcrumb.setCursor(javafx.scene.Cursor.HAND);
                breadcrumb.setOnMouseClicked(e -> MainApp.navigateTo(new WardBoundarySelectionView()));

                // Feed Title
                Label feedTitle = new Label("Recommended for You");
                feedTitle.setTextFill(Color.WHITE);
                feedTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                feedTitle.setPadding(new Insets(10, 20, 0, 20));

                propertyList = new FlowPane();
                propertyList.setHgap(15);
                propertyList.setVgap(20);
                propertyList.setPadding(new Insets(0, 20, 0, 20));
                propertyList.setAlignment(Pos.TOP_CENTER);

                // Responsive Listener
                widthProperty().addListener((obs, oldVal, newVal) -> {
                        double w = newVal.doubleValue();
                        if (w > 1000)
                                columns.set(3);
                        else if (w > 600)
                                columns.set(2);
                        else
                                columns.set(1);

                        double availableWidth = w - 40 - (columns.get() - 1) * propertyList.getHgap();
                        cardWidth.set(Math.max(100, availableWidth / columns.get()));
                });

                loadingLabel = new Label("Loading more properties...");
                loadingLabel.setTextFill(Color.web(TEXT_GRAY));
                loadingLabel.setFont(Font.font(12));
                loadingLabel.setPadding(new Insets(10));
                loadingLabel.setVisible(false);
                loadingLabel.setAlignment(Pos.CENTER);
                loadingLabel.setMaxWidth(Double.MAX_VALUE);

                // Load Initial Data
                loadMoreProperties();

                getChildren().addAll(breadcrumb, feedTitle, propertyList, loadingLabel);
        }

        public void loadMoreProperties() {
                if (isLoading)
                        return;

                isLoading = true;
                loadingLabel.setVisible(true);

                // Simulate network delay
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(e -> {
                        propertyList.getChildren().addAll(
                                        createLargePropertyCard("Skyline Luxury Penthouse", "Kilimani, Nairobi",
                                                        "KSh 250,000",
                                                        "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=400&q=80"),
                                        createLargePropertyCard("Garden Oasis Villa", "Karen, Nairobi", "KSh 180,000",
                                                        "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?auto=format&fit=crop&w=400&q=80"),
                                        createLargePropertyCard("Modern Urban Studio", "Westlands, Nairobi",
                                                        "KSh 85,000",
                                                        "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=400&q=80"));
                        isLoading = false;
                        loadingLabel.setVisible(false);
                });
                pause.play();
        }

        private VBox createLargePropertyCard(String title, String loc, String price, String imgUrl) {
                VBox card = new VBox(0);
                card.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 24; -fx-overflow: hidden;");
                card.setCursor(javafx.scene.Cursor.HAND);
                card.prefWidthProperty().bind(cardWidth);
                card.maxWidthProperty().bind(cardWidth);

                StackPane imgStack = new StackPane();
                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(imgUrl, 600, 0, true, true));
                } catch (Exception e) {
                }
                iv.fitWidthProperty().bind(cardWidth);
                iv.fitHeightProperty().bind(cardWidth.multiply(0.66)); // 3:2 Aspect Ratio

                Rectangle clip = new Rectangle();
                clip.widthProperty().bind(cardWidth);
                clip.heightProperty().bind(cardWidth.multiply(0.66));
                clip.setArcWidth(48);
                clip.setArcHeight(48);
                iv.setClip(clip);

                Label tag = new Label("STORY");
                tag.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-padding: 4 10; -fx-background-radius: 8; -fx-font-size: 10;");
                StackPane.setAlignment(tag, Pos.TOP_LEFT);
                StackPane.setMargin(tag, new Insets(15));

                imgStack.getChildren().addAll(iv, tag);

                VBox details = new VBox(5);
                details.setPadding(new Insets(15));

                HBox priceRow = new HBox();
                Label p = new Label(price);
                p.setTextFill(Color.WHITE);
                p.setFont(Font.font("System", FontWeight.BOLD, 20));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label like = new Label("\u2661");
                like.setTextFill(Color.WHITE);
                like.setStyle("-fx-font-size: 20;");
                priceRow.getChildren().addAll(p, s, like);

                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 16));

                Label l = new Label("\ud83d\udccd " + loc);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font(12));

                details.getChildren().addAll(priceRow, t, l);
                card.getChildren().addAll(imgStack, details);

                card.setOnMouseClicked(e -> {
                        Property pObj = new Property(title, loc, price, imgUrl, true, "STORY");
                        MainApp.navigateToFullScreen(new PropertyDetailView(pObj));
                });

                return card;
        }
}
