package es.gaspardev.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import es.gaspardev.seals.Language

val LocalLocalization = staticCompositionLocalOf { Language.EN.isoFormat }

@Composable
fun LocalizationProvider(language: String = Language.EN.isoFormat, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLocalization provides language,
        content = content
    )
}