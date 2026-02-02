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

public class PropertyFeedView extends VBox {

        private static final String PRIMARY = "#13ec5b";
        private static final String CARD_BG = "#1c271f";
        private static final String TEXT_GRAY = "#9db9a6";

        private VBox propertyList;

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

                // Search Bar (Organized)
                HBox searchContainer = new HBox(10);
                searchContainer.setPadding(new Insets(0, 20, 0, 20));

                StackPane searchField = new StackPane();
                HBox.setHgrow(searchField, Priority.ALWAYS);

                TextField input = new TextField();
                input.setPromptText("Search by area, property type...");
                input.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-padding: 0 10 0 35; -fx-pref-height: 44;");

                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.GRAY);
                StackPane.setAlignment(searchIcon, Pos.CENTER_LEFT);
                StackPane.setMargin(searchIcon, new Insets(0, 0, 0, 12));

                searchField.getChildren().addAll(input, searchIcon);

                Button filterBtn = new Button("\u2312"); // Settings icon
                filterBtn.setStyle("-fx-background-color: " + CARD_BG + "; -fx-text-fill: " + PRIMARY
                                + "; -fx-background-radius: 12; -fx-min-width: 44; -fx-min-height: 44; -fx-font-size: 18;");
                filterBtn.setOnAction(e -> MainApp.navigateTo(new FilterView()));

                searchContainer.getChildren().addAll(searchField, filterBtn);

                // Feed Title
                Label feedTitle = new Label("Recommended for You");
                feedTitle.setTextFill(Color.WHITE);
                feedTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                feedTitle.setPadding(new Insets(10, 20, 0, 20));

                propertyList = new VBox(20);
                propertyList.setPadding(new Insets(0, 20, 0, 20));

                // Load Initial Data
                loadMoreProperties();

                getChildren().addAll(breadcrumb, searchContainer, feedTitle, propertyList);
        }

        private void loadMoreProperties() {
                // Simulate infinite scroll logic or just add items
                propertyList.getChildren().addAll(
                                createLargePropertyCard("Skyline Luxury Penthouse", "Kilimani, Nairobi", "KSh 250,000",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuDislB9eDStdQfVeUXT-SyVYIktdYj4dn1rLs71l6k9U2PyNGYNNFrTSNc9vpmx-1nxZvV3C7xTOHKuL_z-JzyJlV_T9zSmkLpWqQELXWnwdeBWTC_gAwAsO4XuJ9XTTKaNGxd6KvkFkqfHdtlaykJTFfvzJjU7r5Dz5nFelagyTDehv6EwDvE3Dmm0Pv4IBvdDn6HaikyLJuu5BGtc6TRELsBd5pTZoYhKM13gtdCCDe07Kg4J7KzTaxaSxrK6staX7TwHfOMKMTQ"),
                                createLargePropertyCard("Garden Oasis Villa", "Karen, Nairobi", "KSh 180,000",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuDislB9eDStdQfVeUXT-SyVYIktdYj4dn1rLs71l6k9U2PyNGYNNFrTSNc9vpmx-1nxZvV3C7xTOHKuL_z-JzyJlV_T9zSmkLpWqQELXWnwdeBWTC_gAwAsO4XuJ9XTTKaNGxd6KvkFkqfHdtlaykJTFfvzJjU7r5Dz5nFelagyTDehv6EwDvE3Dmm0Pv4IBvdDn6HaikyLJuu5BGtc6TRELsBd5pTZoYhKM13gtdCCDe07Kg4J7KzTaxaSxrK6staX7TwHfOMKMTQ"));
        }

        private VBox createLargePropertyCard(String title, String loc, String price, String imgUrl) {
                VBox card = new VBox(0);
                card.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 24; -fx-overflow: hidden;");
                card.setCursor(javafx.scene.Cursor.HAND);

                StackPane imgStack = new StackPane();
                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(imgUrl, 360, 240, false, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(360);
                iv.setFitHeight(240);
                Rectangle clip = new Rectangle(360, 240);
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

                return card;
        }
}
