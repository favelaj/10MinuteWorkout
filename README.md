
# 10 Minute Workout

10 Minute Workout is a Kotlin-based Android app designed to help users build and manage customized 10-minute fitness routines. The app features a guided onboarding process, personalized workout recommendations, and reminders to stay on track.

## Features

- Guided multi-step profile setup (gender, age, fitness level, goals)
- Personalized 7-minute workout programs
- Schedule reminders for workouts (day and time picker)
- Animated splash screen and gradient-themed UI
- Modern Android architecture using Jetpack Compose
- Navigation with conditional routing (based on profile completion)
- MVVM design pattern with ViewModel and state management
- Modular project structure with packages: `screens`, `model`, `viewmodel`, `network`, `repository`, and `utils`

## Tech Stack

- Language: Kotlin
- UI Framework: Jetpack Compose
- Architecture: MVVM (Model-View-ViewModel)
- Navigation: Jetpack Navigation Compose
- State Management: remember, mutableStateOf
- Dependency Injection: (optional: Hilt or manual DI)
- API Client: Retrofit (for remote exercise sets or goal tracking)
- Build Tool: Gradle

## Folder Structure

```
com.example.a10minuteworkout/
├── components/
├── model/
├── navigation/
├── network/
├── repository/
├── screens/
├── ui.theme/
├── utils/
├── viewmodel/
└── MainActivity.kt
```

## Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/your-username/10MinuteWorkout.git
   cd 10MinuteWorkout
   ```

2. Open in Android Studio  
   Android Studio > File > Open > Select the project root directory

3. Build the project  
   Let Gradle sync and install any missing dependencies.

4. Run on emulator or physical device  
   Choose a target device and click Run.

## Author

Maintained by Jaime Favela
GitHub: https://github.com/favelaj