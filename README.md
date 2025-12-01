📱 Quotes Browser App

A modern Android application built using Kotlin and XML that allows users to explore inspirational quotes, view detailed screens with smooth animations, and save their favorites locally.
This project showcases Clean UI Architecture, Jetpack Navigation, and Material Design 3 principles.

🎯 Objective

The goal of this assignment was to build a 3-screen Android app demonstrating:

✔ Clean UI using XML Layouts & Material Design 3
✔ Smooth navigation using Jetpack Navigation Component
✔ Data passing via Safe Args
✔ MVVM architecture with ViewModel & LiveData
✔ Shared Element Transition for a polished user experience

📱 App Overview
🖥 Screens
Screen	Description
Home Screen	Displays a scrollable list of quotes with images using RecyclerView. Images are loaded using Coil.
Detail Screen	Shows full quote, author, and HD image. Users can mark/unmark as Favorite.
Favorites Screen	Accessible via top menu; displays all saved quotes.
⭐ Features

🧭 Jetpack Navigation Component

🧱 Material Design 3 (M3) Components

🌗 Dark Mode Support

💾 Local Persistence using SharedPreferences

🎬 Shared Element Transition (Bonus)

🚀 MVVM + Repository Pattern

## 📸 Screenshots

| Home Screen | Detail Screen | Favorites Screen |
|-------------|----------------|------------------|
| ![Home](screenshots/home.png) | ![Detail](screenshots/detail.png) | ![Favorites](screenshots/favorites.png) |


🛠 Tech Stack
Category	Technology
Language	Kotlin
UI Toolkit	XML + Material Components (M3)
Architecture	MVVM + Repository Pattern
Image Loading	Coil
Data Serialization	Gson
Storage	SharedPreferences
Navigation	Jetpack Navigation Component (Single Activity)
Testing	JUnit, Espresso
🧭 Navigation & Argument Handling

The app follows Single Activity Architecture using MainActivity as the host for NavHostFragment.
Navigation is defined inside nav_graph.xml.

🔐 Safe Args – Type-Safe Argument Passing
From	To	Data Passed	Method
HomeFragment	DetailFragment	quoteId (String)	HomeFragmentDirections.actionHomeToDetail(quote.id)
FavoritesFragment	DetailFragment	quoteId (String)	FavoritesFragmentDirections.actionFavoritesToDetail(quote.id)
🎞 Shared Element Transition (Bonus)

Image smoothly expands from Home list item to Detail screen.

Implemented using:

FragmentNavigatorExtras (HomeFragment)

TransitionInflater (DetailFragment)

💾 Data & Storage
Feature	Implementation
Quotes Data	Hardcoded + Parsed JSON (Simulating API)
Images	Loaded using Coil
Data Parsing	Gson
Local Storage	SharedPreferences
Repository	Handles persistence & data logic
ViewModel	Exposes LiveData for reactive UI updates
🚀 Setup & Installation
git clone https://github.com/your-username/quotes-browser-app.git


Open in Android Studio → Sync Gradle → Run on Emulator/Device
📱 Minimum SDK: 24

📂 Project Structure
com.example.quotesbrowserapp
├── data                # Models & Repository   (Quote.kt, QuoteRepository.kt)
├── HomeFragment.kt     # Main list view
├── DetailFragment.kt   # Detail view + transition logic
├── FavoritesFragment.kt# Saved quotes screen
├── QuoteAdapter.kt     # RecyclerView Adapter
├── QuoteViewModel.kt   # Shared ViewModel (MVVM)
└── MainActivity.kt     # Host Activity

👨‍💻 Developed by: Snehil

For Android UI Assignment — demonstrating XML + MVVM + Navigation + Material Design

Let me know if you want:
✔ README.md file format
✔ Screenshots design template
✔ GitHub repository description
✔ Resume project section

Would you like me to convert this into a README.md file with proper GitHub formatting? 🚀
