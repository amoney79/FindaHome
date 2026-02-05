package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EarningsAnalyticsView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String CARD_BG = "#1a2e20";
        private static final String TEXT_GRAY = "#9db9a6";

        public EarningsAnalyticsView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

                HBox titleGroup = new HBox(10);
                titleGroup.setAlignment(Pos.CENTER_LEFT);
                Label icon = new Label("\ud83d\udcc8");
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 20;");
                Label title = new Label("Earnings");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                titleGroup.getChildren().addAll(icon, title);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button withdrawBtn = new Button("Withdraw");
                withdrawBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 6 20; -fx-font-size: 13;");
                withdrawBtn.setOnAction(
                                e -> MainApp.navigateCached("landlord_payout_payout", LinkPayoutMethodView::new));

                header.getChildren().addAll(titleGroup, spacer, withdrawBtn);

                // Scroll Content
                VBox content = new VBox(0);
                content.setAlignment(Pos.TOP_CENTER);
                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Headline & Meta
                VBox headline = new VBox(5);
                headline.setAlignment(Pos.CENTER);
                headline.setPadding(new Insets(30, 20, 10, 20));

                Label revLabel = new Label("TOTAL REVENUE (2024)");
                revLabel.setTextFill(Color.web(TEXT_GRAY));
                revLabel.setFont(Font.font("System", FontWeight.MEDIUM, 12));
                revLabel.setStyle("-fx-letter-spacing: 1px;");

                Label revAmount = new Label("KSh 450,000");
                revAmount.setTextFill(Color.WHITE);
                revAmount.setFont(Font.font("System", FontWeight.BLACK, 36));

                HBox trendGroup = new HBox(5);
                trendGroup.setAlignment(Pos.CENTER);
                trendGroup.setPadding(new Insets(4, 10, 4, 10));
                trendGroup.setMaxWidth(Region.USE_PREF_SIZE);
                trendGroup.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 8;");
                Label trendIcon = new Label("\u2197");
                trendIcon.setTextFill(Color.web(PRIMARY));
                Label trendText = new Label("+12.4% vs last year");
                trendText.setTextFill(Color.web(PRIMARY));
                trendText.setFont(Font.font("System", FontWeight.BOLD, 11));
                trendGroup.getChildren().addAll(trendIcon, trendText);

                headline.getChildren().addAll(revLabel, revAmount, trendGroup);

                // Chart Section
                VBox chartSection = new VBox(20);
                chartSection.setPadding(new Insets(25, 20, 25, 20));

                VBox chartCard = new VBox(15);
                chartCard.setPadding(new Insets(20));
                chartCard.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 20; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                HBox chartHead = new HBox();
                VBox chartTitleBox = new VBox(2);
                Label chartTitle = new Label("Monthly Income");
                chartTitle.setTextFill(Color.WHITE);
                chartTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label chartSubtitle = new Label("Last 6 Months");
                chartSubtitle.setTextFill(Color.web(TEXT_GRAY));
                chartSubtitle.setFont(Font.font(11));
                chartTitleBox.getChildren().addAll(chartTitle, chartSubtitle);
                Region cs = new Region();
                HBox.setHgrow(cs, Priority.ALWAYS);
                Label more = new Label("\u22ee");
                more.setTextFill(Color.web(TEXT_GRAY));
                more.setStyle("-fx-font-size: 18;");
                chartHead.getChildren().addAll(chartTitleBox, cs, more);

                // Simulated Chart (Polyline)
                Pane chartPane = new Pane();
                chartPane.setPrefHeight(150);
                Polyline line = new Polyline();
                line.getPoints().addAll(
                                0.0, 100.0,
                                60.0, 40.0,
                                120.0, 60.0,
                                180.0, 110.0,
                                240.0, 50.0,
                                300.0, 120.0,
                                340.0, 30.0);
                line.setStroke(Color.web(PRIMARY));
                line.setStrokeWidth(3);

                chartPane.getChildren().add(line);

                HBox labels = new HBox();
                labels.setAlignment(Pos.CENTER);
                for (String m : new String[] { "JAN", "FEB", "MAR", "APR", "MAY", "JUN" }) {
                        Label ml = new Label(m);
                        ml.setTextFill(Color.web(TEXT_GRAY));
                        ml.setFont(Font.font("System", FontWeight.BOLD, 10));
                        ml.setMinWidth(58);
                        ml.setAlignment(Pos.CENTER);
                        labels.getChildren().add(ml);
                }

                chartCard.getChildren().addAll(chartHead, chartPane, labels);
                chartSection.getChildren().add(chartCard);

                // Stats Grid
                HBox statsGrid = new HBox(15);
                statsGrid.setPadding(new Insets(0, 20, 25, 20));
                statsGrid.getChildren().addAll(
                                createStatCard("Rent Collected", "KSh 380k", "+5%", PRIMARY),
                                createStatCard("Pending", "KSh 70k", "-2%", "#ef4444"),
                                createStatCard("Service Fees", "KSh 15k", "+1%", PRIMARY));
                ScrollPane statsPane = new ScrollPane(statsGrid);
                statsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                statsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                statsPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                // Recent Transactions
                VBox transSection = new VBox(15);
                transSection.setPadding(new Insets(0, 20, 50, 20));
                HBox transHead = new HBox();
                Label tTitle = new Label("Recent Transactions");
                tTitle.setTextFill(Color.WHITE);
                tTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region ts = new Region();
                HBox.setHgrow(ts, Priority.ALWAYS);
                Label vAll = new Label("View All");
                vAll.setTextFill(Color.web(PRIMARY));
                vAll.setFont(Font.font("System", FontWeight.BOLD, 13));
                transHead.getChildren().addAll(tTitle, ts, vAll);

                VBox transList = new VBox(12);
                transList.getChildren().addAll(
                                createTransactionRow("John Doe", "Skyline Apts - 4B", "+KSh 45,000", "Oct 12, 2024",
                                                "JD", PRIMARY),
                                createTransactionRow("Sarah Mwangi", "Palm Heights - 12", "+KSh 32,500", "Oct 11, 2024",
                                                "SM",
                                                "#3b82f6"),
                                createTransactionRow("Peter Kamau", "Sunset Villas - C3", "KSh 55,000", "Pending", "PK",
                                                "#f97316"));

                transSection.getChildren().addAll(transHead, transList);

                content.getChildren().addAll(headline, chartSection, statsPane, transSection);

                layout.getChildren().addAll(header, scroll);
                getChildren().add(layout);
        }

        private VBox createStatCard(String label, String val, String change, String color) {
                VBox card = new VBox(8);
                card.setPadding(new Insets(15));
                card.setPrefWidth(160);
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                Label l = new Label(label);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font(12));

                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 20));

                Label c = new Label(change);
                c.setTextFill(Color.web(color));
                c.setFont(Font.font("System", FontWeight.BOLD, 12));

                card.getChildren().addAll(l, v, c);
                return card;
        }

        private HBox createTransactionRow(String name, String loc, String amount, String date, String initials,
                        String accent) {
                HBox row = new HBox(12);
                row.setPadding(new Insets(12));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                StackPane avatar = new StackPane();
                avatar.setPrefSize(40, 40);
                avatar.setStyle("-fx-background-color: " + accent + "33; -fx-background-radius: 20;");
                Label ini = new Label(initials);
                ini.setTextFill(Color.web(accent));
                ini.setFont(Font.font("System", FontWeight.BOLD, 13));
                avatar.getChildren().add(ini);

                VBox info = new VBox(2);
                HBox.setHgrow(info, Priority.ALWAYS);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label l = new Label(loc);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font(11));
                info.getChildren().addAll(n, l);

                VBox amtBox = new VBox(2);
                amtBox.setAlignment(Pos.CENTER_RIGHT);
                Label a = new Label(amount);
                a.setTextFill(date.equals("Pending") ? Color.web(TEXT_GRAY) : Color.web(PRIMARY));
                a.setFont(Font.font("System", FontWeight.BOLD, 14));

                if (date.equals("Pending")) {
                        Label badge = new Label("PENDING");
                        badge.setStyle(
                                        "-fx-background-color: rgba(249, 115, 22, 0.1); -fx-text-fill: #f97316; -fx-font-size: 8; -fx-padding: 2 6; -fx-background-radius: 10; -fx-font-weight: bold;");
                        amtBox.getChildren().addAll(a, badge);
                } else {
                        Label d = new Label(date);
                        d.setTextFill(Color.web(TEXT_GRAY));
                        d.setFont(Font.font(10));
                        amtBox.getChildren().addAll(a, d);
                }

                row.getChildren().addAll(avatar, info, amtBox);
                return row;
        }

}
