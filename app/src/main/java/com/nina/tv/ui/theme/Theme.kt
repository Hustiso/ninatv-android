package com.nina.tv.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import com.nina.tv.domain.model.AppFont
import com.nina.tv.domain.model.AppTheme

data class NinaExtendedColors(
    val backgroundElevated: Color,
    val backgroundCard: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val focusRing: Color,
    val focusBackground: Color,
    val rating: Color
)

val LocalNinaColors = staticCompositionLocalOf {
    NinaColorScheme(ThemeColors.Coral)
}

val LocalNinaExtendedColors = staticCompositionLocalOf {
    NinaExtendedColors(
        backgroundElevated = Color(0xFF111111),
        backgroundCard = Color(0xFF181818),
        textSecondary = Color(0xFF999999),
        textTertiary = Color(0xFF555555),
        focusRing = ThemeColors.Coral.focusRing,
        focusBackground = ThemeColors.Coral.focusBackground,
        rating = Color(0xFFFeca57)
    )
}

val LocalAppTheme = staticCompositionLocalOf { AppTheme.CORAL }

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NinaTheme(
    appTheme: AppTheme = AppTheme.CORAL,
    appFont: AppFont = AppFont.INTER,
    content: @Composable () -> Unit
) {
    val palette = ThemeColors.getColorPalette(appTheme)
    val colorScheme = NinaColorScheme(palette)

    val materialColorScheme = darkColorScheme(
        primary = colorScheme.Primary,
        onPrimary = colorScheme.OnPrimary,
        secondary = colorScheme.Secondary,
        onSecondary = colorScheme.OnSecondary,
        background = colorScheme.Background,
        surface = colorScheme.Surface,
        surfaceVariant = colorScheme.SurfaceVariant,
        onBackground = colorScheme.TextPrimary,
        onSurface = colorScheme.TextPrimary,
        onSurfaceVariant = colorScheme.TextSecondary,
        error = colorScheme.Error
    )

    val extendedColors = NinaExtendedColors(
        backgroundElevated = colorScheme.BackgroundElevated,
        backgroundCard = colorScheme.BackgroundCard,
        textSecondary = colorScheme.TextSecondary,
        textTertiary = colorScheme.TextTertiary,
        focusRing = colorScheme.FocusRing,
        focusBackground = colorScheme.FocusBackground,
        rating = colorScheme.Rating
    )

    CompositionLocalProvider(
        LocalNinaColors provides colorScheme,
        LocalNinaExtendedColors provides extendedColors,
        LocalAppTheme provides appTheme
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = buildNinaTypography(getFontFamily(appFont)),
            content = content
        )
    }
}

object NinaTheme {
    val colors: NinaColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalNinaColors.current

    val extendedColors: NinaExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNinaExtendedColors.current

    val currentTheme: AppTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTheme.current
}
