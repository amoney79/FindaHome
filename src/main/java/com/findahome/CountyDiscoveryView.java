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

public class CountyDiscoveryView extends BorderPane {

        private static final String BACKGROUND_LIGHT = "#f8f6f5";
        private static final String PRIMARY = "#f46a25";
        private static final String TEXT_GRAY = "#64748b";

        public CountyDiscoveryView() {
                setStyle("-fx-background-color: " + BACKGROUND_LIGHT + ";");

                // Header Section
                VBox topArea = new VBox(0);

                // Status Bar
                HBox statusBar = new HBox();
                statusBar.setPadding(new Insets(8, 24, 8, 24));
                statusBar.getChildren().addAll(new Label("9:41") {
                        {
                                setFont(Font.font("System", FontWeight.BOLD, 12));
                        }
                }, new Region() {
                        {
                                HBox.setHgrow(this, Priority.ALWAYS);
                        }
                }, new Label("\uD83D\uDCF6 \uD83D\uDD0B"));

                // Header
                VBox header = new VBox(16);
                header.setPadding(new Insets(10, 20, 16, 20));
                HBox topRow = new HBox();
                topRow.setAlignment(Pos.CENTER_LEFT);

                HBox logo = new HBox(8);
                logo.setAlignment(Pos.CENTER_LEFT);
                StackPane lBox = new StackPane(new Label("\u2302") {
                        {
                                setTextFill(Color.WHITE);
                        }
                });
                lBox.setPrefSize(32, 32);
                lBox.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 8;");
                logo.getChildren().addAll(lBox, new Label("FindaHome") {
                        {
                                setFont(Font.font("System", FontWeight.EXTRA_BOLD, 20));
                        }
                });
                HBox.setHgrow(logo, Priority.ALWAYS);

                topRow.getChildren().addAll(logo, new Button("\uD83D\uDD14") {
                        {
                                setStyle("-fx-background-color: #e2e8f0; -fx-background-radius: 20; -fx-padding: 8;");
                        }
                });

                // Search
                HBox search = new HBox(12);
                search.setAlignment(Pos.CENTER_LEFT);
                search.setPadding(new Insets(14, 16, 14, 16));
                search.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
                search.getChildren().addAll(new Label("\uD83D\uDD0D") {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                        }
                }, new TextField() {
                        {
                                setPromptText("Search for a County...");
                                setStyle("-fx-background-color: transparent;");
                                HBox.setHgrow(this, Priority.ALWAYS);
                        }
                });

                header.getChildren().addAll(topRow, search);
                topArea.getChildren().addAll(statusBar, header);
                setTop(topArea);

                // Scroll Content
                VBox content = new VBox(32);
                content.setPadding(new Insets(20, 0, 40, 0));

                // 1. Trending
                VBox trending = new VBox(16);
                HBox trendHead = new HBox(new Label("Popular Counties") {
                        {
                                setFont(Font.font("System", FontWeight.BOLD, 18));
                        }
                }, new Region() {
                        {
                                HBox.setHgrow(this, Priority.ALWAYS);
                        }
                }, new Button("See All") {
                        {
                                setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                                + "; -fx-font-weight: bold;");
                        }
                });
                trendHead.setPadding(new Insets(0, 20, 0, 20));

                HBox cards = new HBox(16);
                cards.setPadding(new Insets(0, 20, 0, 20));
                cards.getChildren().addAll(
                                createPopularCard("Nairobi",
                                                "https://images.unsplash.com/photo-1542665093-8012eba2d4cc?w=400",
                                                true),
                                createPopularCard("Mombasa",
                                                "https://images.unsplash.com/photo-1580216643062-cf460548a66a?w=400",
                                                false));

                ScrollPane trendScroll = new ScrollPane(cards);
                trendScroll.setFitToHeight(true);
                trendScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                trendScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                trendScroll.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                trending.getChildren().addAll(trendHead, trendScroll);

                // 2. County Grid
                VBox gridSection = new VBox(16);
                gridSection.setPadding(new Insets(0, 20, 0, 20));
                gridSection.getChildren().add(new Label("Browse by County") {
                        {
                                setFont(Font.font("System", FontWeight.BOLD, 18));
                        }
                });

                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(12);
                grid.add(createCountyBox("01", "Mombasa"), 0, 0);
                grid.add(createCountyBox("12", "Meru"), 1, 0);
                grid.add(createCountyBox("19", "Nyeri"), 2, 0);
                grid.add(createCountyBox("30", "Baringo"), 0, 1);
                grid.add(createCountyBox("32", "Nakuru"), 1, 1);
                grid.add(createCountyBox("47", "Nairobi", true), 2, 1);

                gridSection.getChildren().add(grid);

                content.getChildren().addAll(trending, gridSection);

                ScrollPane mainScroll = new ScrollPane(content);
                mainScroll.setFitToWidth(true);
                mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                mainScroll.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(mainScroll);

                // Bottom Navigation (Pinned)
                HBox bottomNav = new HBox();
                bottomNav.setPadding(new Insets(12, 24, 32, 24));
                bottomNav.setAlignment(Pos.CENTER);
                bottomNav.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.95); -fx-border-color: #e2e8f0; -fx-border-width: 1 0 0 0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, -2);");

                Region s1 = new Region();
                HBox.setHgrow(s1, Priority.ALWAYS);
                Region s2 = new Region();
                HBox.setHgrow(s2, Priority.ALWAYS);
                Region s3 = new Region();
                HBox.setHgrow(s3, Priority.ALWAYS);
                Region s4 = new Region();
                HBox.setHgrow(s4, Priority.ALWAYS);

                bottomNav.getChildren().addAll(
                                createNavItem("\uD83E\uDDED", "Discover", true), s1,
                                createNavItem("\uD83D\uDDFA", "Map", false), s2,
                                new VBox() {
                                        {
                                                setAlignment(Pos.CENTER);
                                                getChildren().addAll(new Button("+") {
                                                        {
                                                                setStyle("-fx-background-color: " + PRIMARY
                                                                                + "; -fx-text-fill: white; -fx-background-radius: 25; -fx-font-size: 24; -fx-pref-width: 50; -fx-pref-height: 50;");
                                                                setTranslateY(-30);
                                                        }
                                                }, new Label("List") {
                                                        {
                                                                setFont(Font.font(10));
                                                                setTranslateY(-25);
                                                        }
                                                });
                                        }
                                }, s3,
                                createNavItem("\u2665", "Saved", false), s4,
                                createNavItem("\uD83D\uDC64", "Profile", false));

                setBottom(bottomNav);
        }

        private StackPane createPopularCard(String city, String url, boolean trending) {
                StackPane p = new StackPane();
                p.setPrefSize(240, 300);
                try {
                        ImageView iv = new ImageView(new Image(url, 240, 300, false, true, true));
                        iv.setFitWidth(240);
                        iv.setFitHeight(300);
                        iv.setPreserveRatio(false);
                        Rectangle clip = new Rectangle(240, 300) {
                                {
                                        setArcWidth(24);
                                        setArcHeight(24);
                                }
                        };
                        iv.setClip(clip);
                        VBox overlay = new VBox(8) {
                                {
                                        setPadding(new Insets(20));
                                        setAlignment(Pos.BOTTOM_LEFT);
                                        setStyle("-fx-background-color: linear-gradient(to top, rgba(0,0,0,0.8), transparent); -fx-background-radius: 12;");
                                }
                        };
                        if (trending)
                                overlay.getChildren().add(new Label("TRENDING") {
                                        {
                                                setStyle("-fx-background-color: " + PRIMARY
                                                                + "; -fx-text-fill: white; -fx-font-size: 10; -fx-padding: 3 8; -fx-background-radius: 8;");
                                        }
                                });
                        overlay.getChildren().add(new Label(city) {
                                {
                                        setFont(Font.font("System", FontWeight.BOLD, 22));
                                        setTextFill(Color.WHITE);
                                }
                        });
                        p.getChildren().addAll(iv, overlay);
                } catch (Exception e) {
                }
                return p;
        }

        private VBox createCountyBox(String num, String name) {
                return createCountyBox(num, name, false);
        }

        private VBox createCountyBox(String num, String name, boolean active) {
                VBox v = new VBox(4);
                v.setAlignment(Pos.CENTER);
                v.setPadding(new Insets(16));
                v.setPrefSize(100, 80);
                v.setStyle("-fx-background-color: " + (active ? "rgba(244,106,37,0.05)" : "white")
                                + "; -fx-background-radius: 12; -fx-border-color: " + (active ? PRIMARY : "#f1f5f9")
                                + "; -fx-border-radius: 12; -fx-cursor: hand;");
                v.getChildren().addAll(new Label(num) {
                        {
                                setTextFill(Color.web(PRIMARY));
                                setFont(Font.font("System", FontWeight.EXTRA_BOLD, 18));
                        }
                }, new Label(name) {
                        {
                                setFont(Font.font(11));
                        }
                });
                return v;
        }

        private VBox createNavItem(String icon, String label, boolean active) {
                VBox v = new VBox(4);
                v.setAlignment(Pos.CENTER);
                v.setCursor(javafx.scene.Cursor.HAND);
                v.getChildren().addAll(new Label(icon) {
                        {
                                setFont(Font.font(20));
                                setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                        }
                }, new Label(label) {
                        {
                                setFont(Font.font(10));
                                setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                        }
                });
                if (label.equals("Profile"))
                        v.setOnMouseClicked(e -> MainApp.navigateCached("profile", TenantProfileView::new));
                if (label.equals("Saved"))
                        v.setOnMouseClicked(e -> MainApp.navigateCachedFullScreen("saved_properties",
                                        SavedPropertiesView::new));
                if (label.equals("Map"))
                        v.setOnMouseClicked(e -> MainApp.navigateToMap());
                return v;
        }
}
