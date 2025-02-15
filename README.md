<head>
    <title>Weather Forecast Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: auto;
        }
        h1, h2, h3 {
            color: #333;
        }
        code {
            background: #eee;
            padding: 2px 6px;
            border-radius: 4px;
        }
        .screenshot {
            text-align: center;
            margin: 20px 0;
        }
        img {
            max-width: 100%;
            border-radius: 8px;
        }
        .section {
            margin-bottom: 30px;
        }
        ul {
            padding-left: 20px;
        }
        .footer {
            text-align: center;
            margin-top: 30px;
            font-size: 14px;
            color: #666;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>🌦️ Weather Forecast Application</h1>

    <div class="section">
        <h2>📌 Overview</h2>
        <p>The <b>Weather Forecast Application</b> is a modern Android app built with <b>Jetpack Compose</b>, providing <b>real-time weather updates</b> and <b>7-day forecasts</b>. It integrates with a <b>third-party weather API</b> (e.g., OpenWeatherMap) and follows the <b>Clean Architecture</b> pattern for scalability and maintainability.</p>
    </div>

    <div class="section">
        <h2>✨ Features</h2>
        <ul>
            <li><b>Real-Time Weather Data</b> – Displays temperature, humidity, wind speed, and precipitation.</li>
            <li><b>7-Day Forecast</b> – Intuitive interface for upcoming weather predictions.</li>
            <li><b>Location-Based Services</b> – Auto-detect location or manual city input.</li>
            <li><b>Dark/Light Mode</b> – Supports system-based theming.</li>
            <li><b>Offline Mode & Error Handling</b> – Gracefully manages network failures.</li>
            <li><b>Comprehensive Testing</b> – Unit & UI testing for reliability.</li>
        </ul>
    </div>

    <div class="section">
        <h2>🏗️ Architecture</h2>
        <p>The app follows the <b>Clean Architecture</b> approach, structured into three layers:</p>
        <ul>
            <li><b>Presentation Layer</b> – Built with Jetpack Compose for a modern UI.</li>
            <li><b>Domain Layer</b> – Contains business logic and use cases.</li>
            <li><b>Data Layer</b> – Manages data sources (API, local storage).</li>
        </ul>
    </div>

    <div class="section">
        <h2>🔌 API Integration</h2>
        <ul>
            <li>Integrated with <b>OpenWeatherMap API</b> using <b>Retrofit</b>.</li>
            <li>Used <b>Moshi/Gson</b> for JSON parsing.</li>
            <li>Implemented a <b>Result Wrapper</b> for consistent API response handling.</li>
        </ul>
    </div>

    <div class="section">
        <h2>📊 State Management</h2>
        <p>Used <b>ViewModel</b> for lifecycle-aware UI state management.</p>
        <p><b>LiveData</b> and <b>StateFlow</b> ensure smooth UI updates.</p>
    </div>

    <div class="section">
        <h2>⚡ Error Handling</h2>
        <ul>
            <li>Manages network failures, invalid API responses, and exceptions.</li>
            <li>Implements <b>retry logic</b> and <b>fallback strategies</b>.</li>
        </ul>
    </div>

    <div class="section">
        <h2>🎨 UI/UX Design</h2>
        <p>Designed with <b>Jetpack Compose</b> for a visually appealing, interactive experience.</p>
        <p>Adheres to <b>Material Design</b> principles for accessibility.</p>
    </div>

    <div class="section">
        <h2>🛠️ Technologies Used</h2>
        <ul>
            <li><b>Programming Language:</b> Kotlin</li>
            <li><b>Framework:</b> Jetpack Compose</li>
            <li><b>Architecture:</b> Clean Architecture</li>
            <li><b>Networking:</b> Retrofit</li>
            <li><b>Data Serialization:</b> Moshi/Gson</li>
            <li><b>State Management:</b> ViewModel, LiveData/StateFlow</li>
            <li><b>Dependency Injection:</b> Hilt/Dagger</li>
            <li><b>Testing:</b> JUnit, Mockito, Espresso</li>
        </ul>
    </div>

    <div class="section screenshot">
        <h2>📸 Screenshots</h2>
        <h3>🌍 Home Screen</h3>
        <img src="path/to/home_screen.png" alt="Home Screen">
        
        <h3>📅 Forecast Screen</h3>
        <img src="path/to/forecast_screen.png" alt="Forecast Screen">
    </div>

    <div class="section">
        <h2>🚀 Getting Started</h2>
        <h3>Prerequisites</h3>
        <ul>
            <li>Android Studio <b>(latest version)</b></li>
            <li>Kotlin <b>(latest version)</b></li>
            <li>API Key from <b>OpenWeatherMap</b></li>
        </ul>

        <h3>Installation</h3>
        <pre><code>git clone https://github.com/your-repo/weather-app.git
cd weather-app</code></pre>

        <p>Open in <b>Android Studio</b> and sync Gradle.</p>
        <p>Add your API Key to <code>local.properties</code>:</p>
        <pre><code>API_KEY="your_openweathermap_api_key"</code></pre>

        <p>Run the app on an emulator or a physical device.</p>
    </div>

    <div class="section">
        <h2>🧪 Testing</h2>
        <p>To run unit and UI tests:</p>
        <pre><code>./gradlew testDebugUnitTest
./gradlew connectedAndroidTest</code></pre>
    </div>

    <div class="section">
        <h2>📜 License</h2>
        <p>This project is licensed under the <b>MIT License</b>.</p>
    </div>

    <div class="section">
        <h2>💡 Contributing</h2>
        <p>Pull requests are welcome! If you have any suggestions, feel free to submit an issue.</p>
    </div>

    <div class="section">
        <h2>📞 Contact</h2>
        <p>👨‍💻 <b>Developer:</b> Your Name</p>
        <p>📧 <b>Email:</b> your.email@example.com</p>
        <p>🔗 <b>GitHub:</b> <a href="https://github.com/your-profile">Your GitHub Profile</a></p>
    </div>

    <div class="footer">
        <p>© 2025 Weather Forecast Application</p>
    </div>
</div>

</body>
</html>
