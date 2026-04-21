package com.nina.tv.core.addon

/**
 * Embedded default Stremio addons that auto-install on first launch.
 * Provides instant streaming without manual configuration.
 *
 * Ported from ninaflix-tizen DEFAULT_ADDONS — direct-link providers only
 * (no torrent/magnet addons).
 */
object DefaultAddons {

    /**
     * Primary addon URLs in priority order.
     * These are fetched as Stremio manifest URLs and seeded into
     * AddonPreferences on first launch if the addon list is empty.
     */
    val MANIFEST_URLS: List<String> = listOf(
        // Cinemeta — primary metadata catalog (movies + series)
        "https://cinemeta-catalogs.strem.io/addon/com.stremio.cinemeta/manifest.json",

        // Streaming providers — direct HTTP links
        "https://addon.seedr.io/manifest.json",

        // Anime catalogs
        "https://7a82163c7618-stremio-anime-catalogs.baby-beamup.club/manifest.json",

        // TV addon
        "https://94c8cb9f702d-tv-addon.baby-beamup.club/manifest.json",

        // Subtitles
        "https://opensubtitles-v3.strem.io/addon/manifest.json"
    )

    /**
     * Optional: extra addon URLs for users who want more sources.
     * Not auto-installed — available via settings "Install recommended extras".
     */
    val EXTRA_URLS: List<String> = listOf(
        "https://stremio-jackett.hybrid.up/reset/manifest.json",
        "https://v3-cinemeta.strem.io/addon/com.stremio.cinemeta/manifest.json"
    )

    /**
     * Storage key used to track whether defaults have been seeded.
     */
    const val PREF_SEEDED_KEY = "default_addons_seeded"
}
