# 📱 NewsApp

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=material-design&logoColor=white" alt="Material Design">
</div>

<div align="center">
  <h3>🚀 Modern Android News App built with Jetpack Compose</h3>
  <p>A sleek and responsive news application that delivers the latest headlines with a beautiful Material Design 3 interface</p>
</div>

## 🛠️ Tech Stack

| Technology | Usage |
|------------|--------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern UI toolkit |
| **Navigation Compose** | Type-safe navigation |
| **ViewModel & StateFlow** | State management |
| **Jetpack DataStore** | Local data persistence |
| **Retrofit** | REST API client |
| **Gson** | JSON serialization |
| **Coroutines** | Asynchronous programming |
| **Material Design 3** | UI components and theming |

---

## 📋 Prerequisites

Before you begin, ensure you have:

- ✅ **Android Studio** (Meerkat)
- ✅ **JDK 20+**
- ✅ **Android device/emulator** (API 30+)
- ✅ **NewsAPI Key** ([Get yours free here](https://newsapi.org/))

---

## 🚀 Quick Start

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/paybackretr0/db-api.git
cd newsapp
```

> 💡 **Pro Tip**: Never commit your API key to version control!

### 4️⃣ Run the App
- Connect your Android device or start an emulator
- Click **Run** in Android Studio
- Enter any username/password to login
- Enjoy browsing the latest news! 📰

---

## 📁 Project Structure

```
com.example.newsapp/
├── 📂 data/
│   ├── 📂 api/
│   │   └── 📄 ApiConfig.kt          # Retrofit configuration
|   |   └── 📄 ApiService.kt         # Endpoint configuration
│   ├── 📄 DataStorePref.kt          # Local Database management
│   └── 📂 response/
│       └── 📄 NewsResponse.kt       # API response models
├── 📂 ui/
│   └── 📂 news/
│       ├── 📄 NewsAppScreen.kt      # Main UI
│       └── 📄 NewsViewModel.kt      # Business logic
|   └── 📂 auth/
│       ├── 📄 LoginScreen.kt        # Login UI 
└── 📄 MainActivity.kt               # Navigation & app entry point
```

---

## 📦 Dependencies

<details>
<summary>Click to view all dependencies</summary>

```kotlin
dependencies {
    // UI & Navigation
    implementation "androidx.activity:activity-compose:1.9.2"
    implementation "androidx.compose.material3:material3:1.3.0"
    implementation "androidx.navigation:navigation-compose:2.7.7"
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6"
    
    // Data & Storage
    implementation "androidx.datastore:datastore-preferences:1.1.1"
    
    // Networking
    implementation "com.squareup.retrofit2:retrofit:3.0.0"
    implementation "com.squareup.retrofit2:converter-gson:3.0.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"
    
    // Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0"
}
```

</details>

## 📚 Resources & Learning

### 📖 **Documentation**
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [NewsAPI](https://newsapi.org/docs)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Android DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

### 🎓 **Tutorials**
- [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- [MVVM in Android](https://developer.android.com/topic/architecture)
- [Material Design 3](https://m3.material.io/)

---

## 🙏 Acknowledgments

- 📰 **NewsAPI** for providing the news data
- 🎨 **Material Design** for the beautiful components
- 🚀 **Jetpack Compose** team for the amazing UI toolkit
- 💙 **Open Source Community** for inspiration and support

---


<div align="center">
  <h3>🌟 Don't forget to star this repository if you found it helpful! 🌟</h3>
  <p>Made with ❤️ for Mobile Programming OR 14 Neo Telemetri</p>
  
  **[⬆ Back to Top](#-newsapp)**
</div>
