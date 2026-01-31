# 🏡 FindaHome — Modern House Finder App (JavaFX)

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-20-blue)
![Maven](https://img.shields.io/badge/Maven-Build-success)
![Platform](https://img.shields.io/badge/Platform-Desktop-lightgrey)
![Status](https://img.shields.io/badge/Status-Active%20Development-brightgreen)

> **FindaHome** is a modern **JavaFX desktop application** designed to simplify rental house discovery.
> It features a smooth splash screen, animated dashboard navigation, clean UI, and a scalable architecture built for real-world use in Kenya and beyond.

---

## ✨ Key Features

* 🚀 **Animated Splash Screen** with smooth transitions
* 🧭 **Dashboard Layout** (Navbar + Content Panels)
* 🏠 **Home Feed** for featured and recommended houses
* ❤️ **Favorites Panel** to save preferred listings
* 👤 **User Profile Section**
* ⚙️ **Settings Panel** (theme-ready)
* 🎨 **Asset-based Icons & Images** (no fragile icon libraries)
* 🧩 **Modular JavaFX Architecture**
* 💡 **Maven-powered build system**

---

## 🖼️ Screenshots (UI Preview)

### Home Feed

![Home Feed](assets/screenshots/home_feed.png)

### Property Listing Grid

![Property Grid](assets/screenshots/propert_listing_grid.png)

### Property Details Page

![Property Details](assets/screenshots/property_details_page.png)

> Screenshots are stored in `assets/screenshots/`

---

## 🏗️ Project Structure

```text
Home_Finder/
│
├── src/main/java/
│   ├── homefinder/          # Main Application (Splash → Dashboard)
│   ├── UI/                  # Dashboard & Panels
│   │   ├── Dashboard.java
│   │   ├── HomePanel.java
│   │   ├── FavoritesPanel.java
│   │   ├── ProfilePanel.java
│   │   └── SettingsPanel.java
│   ├── utils/               # NavBar, ThemeManager, helpers
│
├── src/main/resources/
│   ├── assets/
│   │   ├── icons/           # PNG/SVG icons
│   │   ├── images/          # App images
│   │   ├── styles/          # CSS themes
│   │   └── screenshots/
│
├── pom.xml
└── README.md
```

---

## 🛠️ Tech Stack

| Technology          | Purpose                       |
| ------------------- | ----------------------------- |
| **Java 17+**        | Core language                 |
| **JavaFX 20**       | UI framework                  |
| **Maven**           | Dependency & build management |
| **CSS**             | Styling & theming             |
| **FXML (optional)** | UI scalability                |
| **JSON**            | Data handling                 |

---

## ▶️ How to Run the App

### Prerequisites

* Java **17 or higher**
* Maven **3.8+**

### Run with Maven

```bash
mvn clean javafx:run
```

### Run from IDE (NetBeans / IntelliJ)

1. Open the project
2. Ensure JDK 17+ is selected
3. Run `homefinder.Home_Finder`

---

## 🔁 App Flow

```text
Splash Screen
      ↓
Dashboard (BorderPane)
 ├── NavBar (Left)
 └── Content Area (Center)
      ├── HomePanel
      ├── FavoritesPanel
      ├── ProfilePanel
      └── SettingsPanel
```

---

## 🧭 Roadmap

* 🔍 Advanced property search & filters
* 🗺️ Map-based listings
* 🔐 User authentication
* ☁️ Backend API integration
* 📱 Mobile version (future)
* 🌙 Dark mode (CSS-based)
* 💾 Persistent storage (DB / API)

---

## 🤝 Contributing

Contributions are welcome and encouraged!

Please see [`CONTRIBUTING.md`](CONTRIBUTING.md) for:

* Setup instructions
* Coding guidelines
* Pull request workflow

---

## 📜 License

This project will be released under the **MIT License** (to be added).

---

## 👨‍💻 Author

**Joseph Amani**
Software Developer | Java | JavaFX | UI/UX
🇰🇪 Kenya

---

## ⭐ Support the Project

If you like this project:

* ⭐ Star the repository
* 🍴 Fork it
* 🐞 Open issues
* 💡 Suggest features

> *Your house. Your comfort. Your home — **FindaHome**.*


