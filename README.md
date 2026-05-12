# ⚽ Goal26 — FIFA World Cup 2026 Android App

A modern, ad-supported Android app for tracking the FIFA World Cup 2026 across USA, Canada & Mexico.

## Features

- **⚽ Matches** — Full 104-match schedule with live scores, group filters, countdown timers, and add-to-calendar
- **📊 Standings** — Live group standings for all 12 groups with color-coded qualification zones
- **🏆 Knockout Bracket** — Scrollable visualization from Round of 32 to the Final
- **👥 Teams** — All 48 qualified nations with FIFA rankings, coaches, squads (27 teams), and favorites
- **🧠 Trivia** — 60+ World Cup questions across 7 categories with bonus rounds
- **🏟️ Venues** — 16 stadiums across 3 countries with capacity and location data
- **📤 Share** — Share match results and team info
- **🌙 Themes** — Dark-first design with light mode toggle

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Navigation | Compose Navigation (animated) |
| Networking | Retrofit + OkHttp + Moshi |
| Local DB | Room (offline-first, TTL-cached) |
| DI | Hilt (Dagger) |
| Images | Coil |
| Async | Kotlin Coroutines + Flow |
| Ads | Google Mobile Ads SDK (Banner, Interstitial, Rewarded, App Open) |
| Analytics | Firebase Analytics |
| Config | Firebase Remote Config |

## Architecture

```
MVVM + Clean Architecture

UI (Compose)  →  ViewModel  →  Repository  →  API / Room DB / Static Data
     ↑                              ↓
  Navigation                  Offline Fallback
```

## Data Sources

1. **[WC2026 API](https://wc2026api.com)** — Primary source for live match data, standings, teams
2. **Bundled static data** — Offline fallback with all 48 teams, matches, venues, trivia
3. **[openfootball](https://github.com/openfootball/worldcup.json)** — Open-source supplementary data

## Project Structure

```
Goal26/
├── app/
│   ├── src/main/
│   │   ├── java/com/goal26/worldcup/
│   │   │   ├── data/
│   │   │   │   ├── api/          # Retrofit API service
│   │   │   │   ├── db/           # Room DB, DAO, entities, preferences
│   │   │   │   ├── model/        # API response models
│   │   │   │   └── repository/   # Repository, static data, squads, trivia
│   │   │   ├── di/               # Hilt dependency injection
│   │   │   ├── domain/
│   │   │   │   ├── model/        # Domain models (Team, Match, Player, etc.)
│   │   │   │   └── usecase/      # Date/time utilities
│   │   │   └── ui/
│   │   │       ├── components/   # Shared composables (ads, countdown, bracket)
│   │   │       ├── matches/      # Matches screen + detail
│   │   │       ├── standings/    # Standings screen
│   │   │       ├── teams/        # Teams screen + detail
│   │   │       ├── trivia/       # Trivia quiz
│   │   │       ├── venues/       # Venues screen
│   │   │       ├── navigation/   # Nav routes
│   │   │       └── theme/        # Material 3 theming
│   │   └── res/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
├── privacy-policy.html          # GitHub Pages privacy policy
├── STORE_LISTING.md             # Play Store listing draft
└── SPEC.md                      # Development specification
```

## Setup

### Prerequisites
- JDK 17+
- Android SDK (API 35)
- Gradle 8.11+

### Configuration

1. **WC2026 API Key** — Get a free key at [wc2026api.com](https://tally.so/r/zxqlJE)
2. **Firebase** — Place your `google-services.json` in `app/`
3. **AdMob** — Update ad unit IDs in `AdBannerView.kt` → `AdUnitIds`

Add to `gradle.properties`:
```properties
WC2026_API_KEY=your_key_here
UPLOAD_STORE_FILE=upload-keystore.jks
UPLOAD_STORE_PASSWORD=your_password
UPLOAD_KEY_ALIAS=upload
UPLOAD_KEY_PASSWORD=your_password
```

### Build

```bash
# Debug APK
./gradlew assembleDebug

# Release AAB (for Play Store)
./gradlew bundleRelease
```

## AdMob Strategy

Following [Google's World Cup 2026 Optimization Guide](https://www.gstatic.com/growthlab/api/Mua8pDWjqACDE4hfgUBJuOhoUECEAnTZ5E4tidCL.pdf):

- **App Open** — Cold/warm start with 4-hour ad freshness
- **Banner** — Adaptive banners on all list screens
- **Interstitial** — Frequency-capped (every 3 navigations, 2-min cooldown)
- **Rewarded** — Unlock bonus trivia rounds

## Privacy Policy

Live at: [Privacy Policy](https://jduartedj.github.io/goal26-android/privacy-policy.html)

## License

Private — All rights reserved.
