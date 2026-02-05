package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AssignTechnicianView extends BorderPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String CARD_BG = "#1c271f";
        private static final String TEXT_GRAY = "#9db9a6";

        private String selectedTech = "John Mwangi";

        public AssignTechnicianView(String requestTitle, String requestDesc, String imgUrl) {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(
                                e -> MainApp.navigateCached("admin_maintenance", AdminMaintenanceDashboardView::new));

                Label title = new Label("Assign Technician");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label moreBtn = new Label("\u22ee");
                moreBtn.setTextFill(Color.WHITE);
                moreBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

                header.getChildren().addAll(backBtn, title, moreBtn);

                // Scroll Content
                VBox scrollContent = new VBox(25);
                scrollContent.setAlignment(Pos.TOP_CENTER);
                scrollContent.setPadding(new Insets(20, 0, 20, 0));

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

                // Request Summary
                VBox summarySect = new VBox();
                summarySect.setPadding(new Insets(0, 20, 0, 20));
                HBox summaryCard = new HBox(15);
                summaryCard.setPadding(new Insets(15));
                summaryCard.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                VBox info = new VBox(4);
                HBox.setHgrow(info, Priority.ALWAYS);
                Label sLbl = new Label("MAINTENANCE REQUEST");
                sLbl.setTextFill(Color.web(PRIMARY));
                sLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                Label tLbl = new Label(requestTitle);
                tLbl.setTextFill(Color.WHITE);
                tLbl.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label dLbl = new Label(requestDesc);
                dLbl.setTextFill(Color.web(TEXT_GRAY));
                dLbl.setFont(Font.font(13));
                dLbl.setWrapText(true);
                info.getChildren().addAll(sLbl, tLbl, dLbl);

                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(imgUrl, 80, 80, false, true, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(80);
                iv.setFitHeight(80);
                Rectangle clip = new Rectangle(80, 80);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                iv.setClip(clip);

                summaryCard.getChildren().addAll(info, iv);
                summarySect.getChildren().add(summaryCard);

                // Search Bar
                HBox searchBox = new HBox(10);
                searchBox.setPadding(new Insets(0, 20, 0, 20));
                searchBox.setAlignment(Pos.CENTER_LEFT);
                HBox bar = new HBox(10);
                bar.setAlignment(Pos.CENTER_LEFT);
                bar.setPadding(new Insets(0, 15, 0, 15));
                bar.setPrefHeight(50);
                bar.setStyle("-fx-background-color: #1a2e20; -fx-background-radius: 12;");
                HBox.setHgrow(bar, Priority.ALWAYS);
                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web(TEXT_GRAY));
                TextField searchInput = new TextField();
                searchInput.setPromptText("Search technicians or specialties");
                searchInput.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: "
                                                + TEXT_GRAY + ";");
                HBox.setHgrow(searchInput, Priority.ALWAYS);
                bar.getChildren().addAll(searchIcon, searchInput);
                searchBox.getChildren().add(bar);

                // Filters
                HBox chipsScroll = new HBox(10);
                chipsScroll.setPadding(new Insets(0, 20, 0, 20));
                chipsScroll.getChildren().addAll(
                                createChip("All", true),
                                createChip("Plumbers", false),
                                createChip("Electricians", false),
                                createChip("HVAC", false));
                ScrollPane chipsPane = new ScrollPane(chipsScroll);
                chipsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipsPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                // Providers Section
                VBox providersSect = new VBox(15);
                providersSect.setPadding(new Insets(0, 20, 0, 20));
                HBox providersHead = new HBox();
                Label pTitle = new Label("Recommended Providers");
                pTitle.setTextFill(Color.WHITE);
                pTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                Label viewAll = new Label("View All");
                viewAll.setTextFill(Color.web(PRIMARY));
                providersHead.getChildren().addAll(pTitle, pSpacer, viewAll);

                VBox providerList = new VBox(12);
                providerList.getChildren().addAll(
                                createProviderCard("John Mwangi", "4.9", "Certified HVAC Specialist \u2022 8yr Exp.",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCz7-beo6cIY29pS734QrRzKL4neto-OrwCH0i5gSHdvzK-_pu47ObeyJFYNqFTsbzNmYBAkBIlS4541CwnFpktD-2IVH7QIZ7Dyexa_dufkZSIJA9ASmj-uVUt0jx64dlZLUxYD07enHZaaDWLXfME2Jl1FA8Vc-Ubp6SNOsgZYemCAy0o2kZuhHKc1xSfq33nri95OJ5zTb9GR45lQoZem8vMPjs-KDqf3tn0NdkchWcKIq6_QUtBU5TmFVjVHXo3jPzBt6XD68Q",
                                                true),
                                createProviderCard("Sarah Johnson", "4.7", "General Maintenance \u2022 5yr Exp.",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCba5CtZca7SLDgfS4M4dyWUmlpVAJA8jfymPGw7nsxRbfTN__xfGUNKrkXC8YqHKmyeZ1ReAZlg6tc4zWlL2sz7xKK8SvLKn2AIpjjffPt8UfFudWBHKNf1K6OQd-0jqvgyrKhl9KghincHFrSu4SSbnjwXwilTrDYDMay3o151JB0BtLefNU6hd0BW_j9H5uV5CXNVdFbnhiu520_kKNFWyl0ABlRfmrLK2y5E9LG9CwDEZDZzCtRJx1XVhTO-jqMJnVyoVmsFhg",
                                                false));
                providersSect.getChildren().addAll(providersHead, providerList);

                // Schedule
                VBox scheduleSect = new VBox(15);
                scheduleSect.setPadding(new Insets(0, 20, 0, 20));
                Label scheduleTitle = new Label("Schedule Assignment");
                scheduleTitle.setTextFill(Color.WHITE);
                scheduleTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                HBox slots = new HBox(12);
                slots.getChildren().addAll(
                                createSlot("Service Date", "May 24, 2024", "\ud83d\udcc5"),
                                createSlot("Time Slot", "10:00 AM", "\u23f0"));
                scheduleSect.getChildren().addAll(scheduleTitle, slots);

                scrollContent.getChildren().addAll(summarySect, searchBox, chipsPane, providersSect, scheduleSect);

                // Footer
                VBox footer = new VBox(15);
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                HBox costs = new HBox();
                costs.setAlignment(Pos.CENTER);
                VBox lCost = new VBox(2);
                Label cpTitle = new Label("ESTIMATED COST");
                cpTitle.setTextFill(Color.web(TEXT_GRAY));
                cpTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
                Label cpVal = new Label("KSh 2,500.00");
                cpVal.setTextFill(Color.WHITE);
                cpVal.setFont(Font.font("System", FontWeight.BOLD, 18));
                lCost.getChildren().addAll(cpTitle, cpVal);
                Region costSpacer = new Region();
                HBox.setHgrow(costSpacer, Priority.ALWAYS);
                VBox rCost = new VBox(2);
                rCost.setAlignment(Pos.CENTER_RIGHT);
                Label selTitle = new Label("SELECTED TECH");
                selTitle.setTextFill(Color.web(TEXT_GRAY));
                selTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
                Label selVal = new Label(selectedTech);
                selVal.setTextFill(Color.web(PRIMARY));
                selVal.setFont(Font.font("System", FontWeight.BOLD, 14));
                rCost.getChildren().addAll(selTitle, selVal);
                costs.getChildren().addAll(lCost, costSpacer, rCost);

                Button confirmBtn = new Button("Confirm Assignment");
                confirmBtn.setMaxWidth(Double.MAX_VALUE);
                confirmBtn.setPrefHeight(56);
                confirmBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                confirmBtn.setOnAction(e -> MainApp.navigateCached("success_assignment",
                                () -> new SuccessView("Assignment Confirmed",
                                                "John Mwangi has been scheduled for this maintenance task.",
                                                "Back to Dashboard",
                                                () -> MainApp.navigateCached("admin_maintenance",
                                                                AdminMaintenanceDashboardView::new))));

                footer.getChildren().addAll(costs, confirmBtn);

                setTop(header);
                setCenter(scroll);
                setBottom(footer);
        }

        private Button createChip(String text, boolean active) {
                Button b = new Button(text + (active ? " \u2304" : ""));
                b.setStyle(active
                                ? "-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                                + "; -fx-background-radius: 20; -fx-font-weight: bold;"
                                : "-fx-background-color: #1a2e20; -fx-text-fill: white; -fx-background-radius: 20; -fx-border-color: "
                                                + BORDER_COLOR + ";");
                b.setPadding(new Insets(8, 20, 8, 20));
                return b;
        }

        private VBox createProviderCard(String name, String rating, String meta, String url, boolean selected) {
                VBox card = new VBox(10);
                card.setPadding(new Insets(12));
                card.setStyle(selected
                                ? "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + PRIMARY
                                                + "; -fx-border-width: 2;"
                                : "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR
                                                + "; -fx-opacity: 0.8;");

                HBox body = new HBox(12);
                body.setAlignment(Pos.CENTER_LEFT);

                StackPane imgStack = new StackPane();
                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(url, 56, 56, true, true, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(56);
                iv.setFitHeight(56);
                Circle clip = new Circle(28, 28, 28);
                iv.setClip(clip);
                imgStack.getChildren().add(iv);
                if (selected) {
                        Circle check = new Circle(10, Color.web(PRIMARY));
                        Label icon = new Label("\u2713");
                        icon.setTextFill(Color.web(BACKGROUND_DARK));
                        icon.setFont(Font.font(10));
                        StackPane.setAlignment(check, Pos.BOTTOM_RIGHT);
                        StackPane.setAlignment(icon, Pos.BOTTOM_RIGHT);
                        imgStack.getChildren().addAll(check, icon);
                }

                VBox info = new VBox(2);
                HBox.setHgrow(info, Priority.ALWAYS);
                HBox nameRow = new HBox(8);
                nameRow.setAlignment(Pos.CENTER_LEFT);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label r = new Label("\u2b50 " + rating);
                r.setTextFill(Color.web(PRIMARY));
                r.setFont(Font.font("System", FontWeight.BOLD, 11));
                nameRow.getChildren().addAll(n, r);
                Label m = new Label(meta);
                m.setTextFill(Color.web(TEXT_GRAY));
                m.setFont(Font.font(11));

                HBox badges = new HBox(8);
                badges.setPadding(new Insets(4, 0, 0, 0));
                badges.getChildren().addAll(
                                createBadge(selected ? "FAST RESPONSE" : "AVAILABLE NOW", selected),
                                createBadge("VERIFIED", false));
                info.getChildren().addAll(nameRow, m, badges);

                VBox actions = new VBox(8);
                actions.getChildren().addAll(createIconBtn("\ud83d\udcde"), createIconBtn("\ud83d\udcac"));

                body.getChildren().addAll(imgStack, info, actions);
                card.getChildren().add(body);
                return card;
        }

        private Button createBadge(String text, boolean highlight) {
                Button b = new Button(text);
                b.setStyle(highlight
                                ? "-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY
                                                + "; -fx-font-size: 8; -fx-font-weight: bold; -fx-background-radius: 10;"
                                : "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: " + TEXT_GRAY
                                                + "; -fx-font-size: 8; -fx-font-weight: bold; -fx-background-radius: 10;");
                b.setPadding(new Insets(2, 8, 2, 8));
                return b;
        }

        private Button createIconBtn(String icon) {
                Button b = new Button(icon);
                b.setPrefSize(32, 32);
                b.setStyle("-fx-background-color: #28392e; -fx-background-radius: 16; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-size: 14;");
                return b;
        }

        private VBox createSlot(String label, String val, String icon) {
                VBox box = new VBox(4);
                HBox.setHgrow(box, Priority.ALWAYS);
                Label l = new Label(label.toUpperCase());
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", FontWeight.BOLD, 9));

                HBox row = new HBox();
                row.setPadding(new Insets(12, 15, 12, 15));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_COLOR + ";");
                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.MEDIUM, 13));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label i = new Label(icon);
                i.setTextFill(Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 16;");
                row.getChildren().addAll(v, s, i);

                box.getChildren().addAll(l, row);
                return box;
        }
}
