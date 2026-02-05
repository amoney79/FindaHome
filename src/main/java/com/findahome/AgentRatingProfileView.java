package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AgentRatingProfileView extends StackPane {

        private static final String BACKGROUND_DARK = "#101922";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

        public AgentRatingProfileView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);

                // Top Navigation Bar
                HBox topNav = new HBox(15);
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPadding(new Insets(15, 20, 15, 20));
                topNav.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label navTitle = new Label("Agent Rating Profile");
                navTitle.setTextFill(Color.WHITE);
                navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                navTitle.setAlignment(Pos.CENTER);
                navTitle.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(navTitle, Priority.ALWAYS);

                Label shareBtn = new Label("\u27a6");
                shareBtn.setTextFill(Color.WHITE);
                shareBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

                topNav.getChildren().addAll(backBtn, navTitle, shareBtn);

                // Scrollable Content
                VBox scrollContent = new VBox(0);
                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Profile Header
                VBox header = new VBox(20);
                header.setAlignment(Pos.CENTER);
                header.setPadding(new Insets(30, 20, 20, 20));

                StackPane avatarStack = new StackPane();
                ImageView avatar = new ImageView();
                try {
                        avatar.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCcovc_tthRujXMxFsQHJnvoBpBclWCtvY7aznsfEgBlSe3_JjYNg5reDDt4o2CdS7scMnNQNzX9DmLpfvCY3QRVKI-84edJ8p6YClD5W9rqTQwbSQUXhVGblu_YqeYO3iZy1PE7cnds5f-ZGDn2wiX3v12bXMsr9whvMvZT88TBhlXE3jq84qTuVOuvRHRy1KwOTnoCDlSH8pDWybCTNLsp1dJS18VnrI5ldRyi2RpFq4yCKdBCs7PpgpEmUh4Q5Nbjy6BUOcUyLo",
                                        128, 128, true, true));
                } catch (Exception e) {
                }
                Circle clip = new Circle(64, 64, 64);
                avatar.setClip(clip);
                avatar.setFitWidth(128);
                avatar.setFitHeight(128);

                Circle border = new Circle(64, 64, 66);
                border.setFill(Color.TRANSPARENT);
                border.setStroke(Color.web(PRIMARY, 0.2));
                border.setStrokeWidth(4);

                Label vIcon = new Label("\u2705");
                vIcon.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 2; -fx-font-size: 10; -fx-border-color: "
                                + BACKGROUND_DARK + "; -fx-border-width: 2; -fx-border-radius: 10;");
                StackPane.setAlignment(vIcon, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(vIcon, new Insets(0, 5, 5, 0));

                avatarStack.getChildren().addAll(border, avatar, vIcon);

                VBox meta = new VBox(5);
                meta.setAlignment(Pos.CENTER);
                Label name = new Label("Alex Mutua");
                name.setTextFill(Color.WHITE);
                name.setFont(Font.font("System", FontWeight.BLACK, 24));

                Label designation = new Label("FINDAHOME CERTIFIED ELITE");
                designation.setTextFill(Color.web(PRIMARY));
                designation.setFont(Font.font("System", FontWeight.BOLD, 12));

                Label subMeta = new Label("120+ Placements \u2022 3+ Years active in Nairobi");
                subMeta.setTextFill(Color.web("#ffffff", 0.6));
                subMeta.setFont(Font.font(14));
                subMeta.setWrapText(true);
                subMeta.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                meta.getChildren().addAll(name, designation, subMeta);

                HBox actions = new HBox(12);
                actions.setAlignment(Pos.CENTER);
                Button msgBtn = new Button("Message Agent");
                msgBtn.setPrefHeight(48);
                HBox.setHgrow(msgBtn, Priority.ALWAYS);
                msgBtn.setMaxWidth(Double.MAX_VALUE);
                msgBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12;");

                Button favBtn = new Button("\u2661");
                favBtn.setPrefSize(48, 48);
                favBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-background-radius: 12; -fx-font-size: 18;");

                actions.getChildren().addAll(msgBtn, favBtn);

                header.getChildren().addAll(avatarStack, meta, actions);

                // KPI Stats Grid
                GridPane kpiGrid = new GridPane();
                kpiGrid.setHgap(15);
                kpiGrid.setPadding(new Insets(0, 20, 20, 20));
                kpiGrid.add(createKPICard("Response Time", "15 mins", "-5% faster", true), 0, 0);
                kpiGrid.add(createKPICard("Satisfaction", "98%", "+2% MoM", false), 1, 0);
                ColumnConstraints col1 = new ColumnConstraints();
                col1.setPercentWidth(50);
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setPercentWidth(50);
                kpiGrid.getColumnConstraints().addAll(col1, col2);

                // Performance Breakdown
                VBox breakdown = new VBox(15);
                breakdown.setPadding(new Insets(10, 20, 20, 20));
                Label breakdownTitle = new Label("Performance Breakdown");
                breakdownTitle.setTextFill(Color.WHITE);
                breakdownTitle.setFont(Font.font("System", FontWeight.BLACK, 20));

                VBox breakCard = new VBox(20);
                breakCard.setPadding(new Insets(20));
                breakCard.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                HBox summaryRow = new HBox(30);
                summaryRow.setAlignment(Pos.CENTER_LEFT);

                VBox scoreBox = new VBox(5);
                Label scoreVal = new Label("4.8");
                scoreVal.setTextFill(Color.WHITE);
                scoreVal.setFont(Font.font("System", FontWeight.BLACK, 48));
                HBox stars = createStarRating(4);
                Label basedOn = new Label("Based on 84 reviews");
                basedOn.setTextFill(Color.web("#ffffff", 0.5));
                basedOn.setFont(Font.font(12));
                scoreBox.getChildren().addAll(scoreVal, stars, basedOn);

                VBox bars = new VBox(12);
                HBox.setHgrow(bars, Priority.ALWAYS);
                bars.getChildren().addAll(
                                createRatingLine("5", 0.85),
                                createRatingLine("4", 0.10),
                                createRatingLine("3", 0.03),
                                createRatingLine("2", 0.01),
                                createRatingLine("1", 0.01));
                summaryRow.getChildren().addAll(scoreBox, bars);
                breakCard.getChildren().add(summaryRow);
                breakdown.getChildren().addAll(breakdownTitle, breakCard);

                // Top Reviews
                VBox reviewsSec = new VBox(15);
                reviewsSec.setPadding(new Insets(10, 20, 120, 20));

                HBox revHeader = new HBox();
                Label revTitle = new Label("Top Reviews");
                revTitle.setTextFill(Color.WHITE);
                revTitle.setFont(Font.font("System", FontWeight.BLACK, 20));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label seeAll = new Label("See All");
                seeAll.setTextFill(Color.web(PRIMARY));
                seeAll.setFont(Font.font("System", FontWeight.BOLD, 14));
                revHeader.getChildren().addAll(revTitle, s, seeAll);

                HBox chips = new HBox(10);
                chips.getChildren().addAll(
                                createFilterChip("Latest", true),
                                createFilterChip("Verified Only", false),
                                createFilterChip("Highest Rated", false));
                ScrollPane chipScroll = new ScrollPane(chips);
                chipScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                VBox reviewList = new VBox(15);
                reviewList.getChildren().addAll(
                                createReviewCard("JM", "John Maina", "Tenant \u2022 2 weeks ago", 5,
                                                "Alex was incredibly helpful. He showed me 3 apartments in one afternoon and helped negotiate the deposit. Very professional and always on time!"),
                                createReviewCard("SK", "Sarah Kamau", "Tenant \u2022 1 month ago", 4,
                                                "Highly recommend Alex for anyone looking in Westlands. He knows the area inside out and actually answers his phone."));

                reviewsSec.getChildren().addAll(revHeader, chipScroll, reviewList);

                scrollContent.getChildren().addAll(header, kpiGrid, breakdown, reviewsSec);

                // Sticky Footer
                HBox footer = new HBox(15);
                footer.setPadding(new Insets(15, 20, 35, 20));
                footer.setStyle("-fx-background-color: rgba(16, 25, 34, 0.9); -fx-background-radius: 0; -fx-border-color: "
                                + BORDER_COLOR + "; -fx-border-width: 1 0 0 0;");

                Button chatBtn = new Button("Start Direct Chat  \ud83d\udcac");
                HBox.setHgrow(chatBtn, Priority.ALWAYS);
                chatBtn.setMaxWidth(Double.MAX_VALUE);
                chatBtn.setPrefHeight(56);
                chatBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: black; -fx-font-size: 16; -fx-background-radius: 12;");

                Button calBtn = new Button("\ud83d\udcc5");
                calBtn.setPrefSize(56, 56);
                calBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-background-radius: 12; -fx-font-size: 20; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                footer.getChildren().addAll(chatBtn, calBtn);

                layout.getChildren().addAll(scroll);

                getChildren().addAll(layout, topNav, footer);
                StackPane.setAlignment(topNav, Pos.TOP_CENTER);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
        }

        private VBox createKPICard(String title, String val, String trend, boolean down) {
                VBox card = new VBox(8);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                HBox top = new HBox(8);
                top.setAlignment(Pos.CENTER_LEFT);
                Label icon = new Label(title.contains("Response") ? "\u26a1" : "\ud83d\ude0a");
                icon.setTextFill(Color.web("#94a3b8"));
                Label lbl = new Label(title);
                lbl.setTextFill(Color.web("#94a3b8"));
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 13));
                top.getChildren().addAll(icon, lbl);

                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BLACK, 22));

                Label t = new Label((down ? "\u2193 " : "\u2191 ") + trend);
                t.setTextFill(Color.web("#10b981"));
                t.setFont(Font.font("System", FontWeight.BOLD, 12));

                card.getChildren().addAll(top, v, t);
                return card;
        }

        private HBox createRatingLine(String star, double pct) {
                HBox h = new HBox(12);
                h.setAlignment(Pos.CENTER_LEFT);
                Label s = new Label(star);
                s.setTextFill(Color.WHITE);
                s.setFont(Font.font("System", FontWeight.BOLD, 12));

                ProgressBar pb = new ProgressBar(pct);
                pb.setPrefHeight(6);
                pb.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(pb, Priority.ALWAYS);
                pb.setStyle("-fx-accent: " + PRIMARY
                                + "; -fx-control-inner-background: rgba(255,255,255,0.1); -fx-background-radius: 10;");

                Label p = new Label((int) (pct * 100) + "%");
                p.setTextFill(Color.web("#ffffff", 0.5));
                p.setFont(Font.font("System", FontWeight.BOLD, 10));
                p.setMinWidth(30);
                p.setAlignment(Pos.CENTER_RIGHT);

                h.getChildren().addAll(s, pb, p);
                return h;
        }

        private HBox createStarRating(int filled) {
                HBox h = new HBox(2);
                for (int i = 0; i < 5; i++) {
                        Label star = new Label("\u2b50");
                        star.setStyle("-fx-font-size: 14;");
                        if (i >= filled)
                                star.setOpacity(0.2);
                        h.getChildren().add(star);
                }
                return h;
        }

        private Button createFilterChip(String text, boolean active) {
                Button b = new Button(text);
                b.setStyle("-fx-background-color: " + (active ? PRIMARY : "rgba(255,255,255,0.05)")
                                + "; -fx-text-fill: "
                                + (active ? "white" : "#cbd5e1")
                                + "; -fx-background-radius: 20; -fx-padding: 8 16; -fx-font-size: 11; -fx-font-weight: bold;");
                return b;
        }

        private VBox createReviewCard(String initials, String name, String meta, int stars, String text) {
                VBox card = new VBox(12);
                card.setPadding(new Insets(20));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                HBox top = new HBox(12);
                top.setAlignment(Pos.CENTER_LEFT);

                StackPane initialBox = new StackPane(new Label(initials));
                initialBox.setPrefSize(40, 40);
                initialBox.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 20;");
                ((Label) initialBox.getChildren().get(0)).setTextFill(Color.WHITE);
                ((Label) initialBox.getChildren().get(0)).setFont(Font.font("System", FontWeight.BOLD, 12));

                VBox userMeta = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label m = new Label(meta.toUpperCase());
                m.setTextFill(Color.web("#94a3b8"));
                m.setFont(Font.font("System", FontWeight.BOLD, 10));
                userMeta.getChildren().addAll(n, m);

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                HBox starBox = createStarRating(stars);

                top.getChildren().addAll(initialBox, userMeta, s, starBox);

                Label content = new Label("\"" + text + "\"");
                content.setTextFill(Color.web("#cbd5e1"));
                content.setFont(Font.font("System", FontWeight.NORMAL, 14));
                content.setWrapText(true);
                content.setStyle("-fx-font-style: italic;");

                HBox verifRow = new HBox(5);
                verifRow.setAlignment(Pos.CENTER_LEFT);
                Label vIcon = new Label("\u2705");
                vIcon.setTextFill(Color.web(PRIMARY));
                vIcon.setStyle("-fx-font-size: 12;");
                Label vLbl = new Label("VERIFIED PLACEMENT");
                vLbl.setTextFill(Color.web(PRIMARY));
                vLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                verifRow.getChildren().addAll(vIcon, vLbl);

                card.getChildren().addAll(top, content, verifRow);
                return card;
        }
}
