# Pregnancy Vitals Tracker 
An Android assignment given to me by Janitri and submitted as part of their recruitment/assessment process.
An **Android (Kotlin + Jetpack Compose)** app that lets expectant parents log blood-pressure, heart-rate, weight, and baby-kick counts, then reminds them every day at a custom time.
---
# ✨ Features
| Capability | Details |
|------------|---------|
| Daily reminder notification | Custom **clock picker** lets users choose any hour & minute; WorkManager fires a notification titled *“Time to log your vitals!”* with the message *“Stay on top of your health. Please update your vitals now!”*. |
| Jetpack Compose UI | Modern, declarative layouts with Material components. |
| **Dark-mode toggle** | Moon/sun icon in the top bar instantly switches between light and dark palettes, state saved with DataStore. |
| **Swipe-to-delete** | Left-swipe a card to remove an entry from Room and the on-screen list. |
| Add vitals dialog | Floating Action Button opens a form for BP, heart-rate, weight, baby-kicks. |
| MVVM + Room | Reactive StateFlow updates; data survives restarts. |
---
## 📸 Screenshots
| Main list | Add dialog | Reminder picker |
|-----------|------------|-----------------|
| ![Main list](docs/IMG-20250801-WA0016.jpg) | *insert image* | *insert image* |
---
## 🏗️ Tech Stack
| Layer | Library | Notes |
|-------|---------|-------|
| UI | Jetpack Compose 1.6.x | Material 3 components |
| State | Kotlin Coroutines + StateFlow | Reactive updates |
| DB | Room 2.6.x | DAO, Entity, Database singleton |
| DI | Simple factory | Avoids extra DI libs |
| Background | WorkManager 2.9.x | Exact-time daily reminder |
| Storage | DataStore 1.1.x | Saves reminder time & theme |
---
## 🚀 Getting Started

1. Open **Android Studio ≥ Iguana** → *Open an existing project*.  
2. Let Gradle sync & build; compose compiler plugin handles Kotlin 2.x.  
3. **Run** on an emulator or real device (API 24+).
---
## 🔧 Customising

| Task | Where |
|------|-------|
| Change notification text | `worker/ReminderWorker.kt` |
| Default reminder time | `Prefs.reminderTime` fallback |
| App colours & typography | `ui/theme/Color.kt`, `Theme.kt` |
| Database schema | `data/local/VitalsEntity.kt` |

---

## 🗄️ Project Structure (trimmed)
pregnancy-vitals-tracker/
 ├─ app/
 │   ├─ src/
 │   │   ├─ main/
 │   │   │   ├─ java/
 │   │   │   │   └─ com/
 │   │   │   │       └─ example/
 │   │   │   │           └─ preg_vitals/
 │   │   │   │               ├─ data/
 │   │   │   │               │   ├─ local/
 │   │   │   │               │   │   ├─ Prefs.kt
 │   │   │   │               │   │   ├─ VitalsDao.kt
 │   │   │   │               │   │   ├─ VitalsDatabase.kt
 │   │   │   │               │   │   └─ VitalsEntity.kt
 │   │   │   │               │   └─ repository/
 │   │   │   │               │       └─ VitalsRepository.kt
 │   │   │   │               ├─ ui/
 │   │   │   │               │   ├─ components/
 │   │   │   │               │   │   ├─ AddVitalsDialog.kt
 │   │   │   │               │   │   └─ VitalsListItem.kt
 │   │   │   │               │   ├─ screens/
 │   │   │   │               │   │   ├─ ReminderScreen.kt
 │   │   │   │               │   │   └─ VitalsMainScreen.kt
 │   │   │   │               │   └─ theme/
 │   │   │   │               │       ├─ Color.kt
 │   │   │   │               │       ├─ Theme.kt
 │   │   │   │               │       └─ Type.kt
 │   │   │   │               ├─ viewmodel/
 │   │   │   │               │   ├─ VitalsViewModel.kt
 │   │   │   │               │   └─ VitalsViewModelFactory.kt
 │   │   │   │               ├─ worker/
 │   │   │   │               │   ├─ ReminderScheduler.kt
 │   │   │   │               │   └─ ReminderWorker.kt
 │   │   │   │               ├─ MainActivity.kt
 │   │   │   │               └─ PlaceholderScreen.kt   ← optional/test
 │   │   │   ├─ AndroidManifest.xml
 │   │   │   └─ res/
 │   │   │       ├─ drawable/
 │   │   │       │   └─ ic_notification.xml
 │   │   │       ├─ mipmap/ (launcher icons)
 │   │   │       └─ values/
 │   │   │           ├─ colors.xml
 │   │   │           ├─ strings.xml
 │   │   │           └─ themes.xml
 │   │   └─ test/ & androidTest/ (unit/UI tests)
 │   ├─ build.gradle(.kts)
 ├─ .gitignore
 ├─ build.gradle(.kts)   (project-level)
 ├─ settings.gradle(.kts)
 ├─ README.md
 └─ docs/
     ├─ screenshot_main.png
     ├─ screenshot_dialog.png
     └─ screenshot_dark.png



Thank You for reading 
-Abhay Sharma
