🌦️ Weather Forecast Application
📌 Overview
The Weather Forecast Application is a modern, user-friendly Android app built using Jetpack Compose that provides real-time weather updates and multi-day forecasts. It integrates with a third-party weather API (e.g., OpenWeatherMap) to deliver accurate and up-to-date weather information. The app follows the Clean Architecture pattern, ensuring scalability, maintainability, and testability.

✨ Features
✔ Real-Time Weather Data – Displays current temperature, humidity, wind speed, and precipitation.
✔ 7-Day Forecast – View upcoming weather conditions with an intuitive interface.
✔ Location-Based Services – Automatically detects user location or allows manual city search.
✔ Dark/Light Mode – Supports dynamic theming based on user preferences or system settings.
✔ Offline Mode & Error Handling – Handles network failures gracefully and provides meaningful feedback.
✔ Comprehensive Testing – Unit testing & UI testing ensure reliability.

🏗️ Architecture
The app follows the Clean Architecture approach, structured into three layers:

📌 Presentation Layer – Built with Jetpack Compose for a modern UI.
📌 Domain Layer – Contains business logic and use cases.
📌 Data Layer – Manages data sources (API, local storage).

🔌 API Integration
✅ Integrated with OpenWeatherMap API using Retrofit for efficient HTTP requests.
✅ Used Moshi/Gson for JSON parsing.
✅ Implemented a Result Wrapper to manage API responses consistently.

📊 State Management
Used ViewModel for lifecycle-aware UI state management.
LiveData/StateFlow ensures smooth UI updates.
⚡ Error Handling
✔ Developed robust mechanisms to handle:

Network failures
Invalid API responses
Unexpected exceptions
✔ Implemented retry logic & fallback strategies to enhance resilience.
🎨 UI/UX Design
Built with Jetpack Compose for an interactive, modern design.
Adheres to Material Design principles for accessibility.
🛠️ Technologies Used
Programming Language: Kotlin
Framework: Jetpack Compose
Architecture: Clean Architecture
Networking: Retrofit
Data Serialization: Moshi/Gson
State Management: ViewModel, LiveData/StateFlow
Dependency Injection: Hilt/Dagger (if applicable)
Testing: JUnit, Mockito, Espresso
📸 Screenshots
🌍 Home Screen
Displays current weather and forecast

📅 Forecast Screen
Shows multi-day weather predictions
