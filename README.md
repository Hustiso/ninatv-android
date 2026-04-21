# NinaTV

Modern Android TV streaming client. Full-featured media player for movies and series.

## Features

- **Video Playback**: ExoPlayer with custom forks — HLS, DASH, RTSP, FFmpeg decoders, AV1, ASS/SSA subtitles
- **TMDB Integration**: Full metadata — search, discover, cast, ratings, trailers
- **Trakt Sync**: Scrobbling, watchlists, collections, continue watching
- **Addon System**: QuickJS plugins + CloudStream DEX extensions
- **Multi-Profile**: PIN-protected profiles with per-profile settings
- **Library Management**: Local + Trakt library with filtering, sorting, collections
- **Playback Controls**: Subtitle styling, audio track selection, playback speed, aspect ratio, skip intro/recap
- **Auto-Update**: GitHub Releases in-app updater
- **Network Diagnostics**: Latency + download speed test

## Tech Stack

- Kotlin + Jetpack Compose (Material3 + Leanback)
- ExoPlayer/Media3 (custom forked AARs with FFmpeg + AV1)
- Retrofit + OkHttp + Moshi
- Hilt DI + Room + DataStore
- Supabase Auth + Sync
- QuickJS plugin engine + CloudStream DEX extensions

## Build

```bash
# Clone
git clone https://github.com/Hustiso/NinaTV.git
cd NinaTV

# Set API keys in local.properties
cat > local.properties <<EOF
TMDB_API_KEY=your_tmdb_key
TRAKT_CLIENT_ID=your_trakt_id
TRAKT_CLIENT_SECRET=your_trakt_secret
EOF

# Build
./gradlew assembleDebug

# Install
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.nina.tv/.MainActivity
```

## License

See [LICENSE](LICENSE) for details.

Based on [NuvioTV](https://github.com/NuvioMedia/NuvioTV) by Tapframe.
