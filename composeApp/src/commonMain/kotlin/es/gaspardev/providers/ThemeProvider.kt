package es.gaspardev.providers

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Typography
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

// Interfaz para definir los colores del tema
interface Colors {
    val primary: Color
    val primaryVariation: Color?
    val primaryBackground: Color?
    val primaryHighlight: Color
    val secondary: Color
    val secondaryVariation: Color?
    val secondaryBackground: Color?
    val secondaryHighlight: Color?
    val accent: Color
    val accentVariation: Color?
    val accentBackground: Color?
    val accentHighlight: Color?
    val background: Color
    val backgroundVariation: Color
    val borders: Color
    val success: Color
    val error: Color
    val warning: Color
}

// Definición de colores para el tema claro
object LightModeColors : Colors {
    override val primary: Color = Color(0xFFFAFAFA)
    override val primaryVariation: Color = Color(0xFF00DDFF)
    override val primaryBackground: Color? = null
    override val primaryHighlight: Color = Color.White
    override val secondary: Color = Color(0xFF1C1C1C)
    override val secondaryVariation: Color? = null
    override val secondaryBackground: Color? = null
    override val secondaryHighlight: Color? = null
    override val accent: Color = Color(0xFF015EF7)
    override val accentVariation: Color? = null
    override val accentBackground: Color? = null
    override val accentHighlight: Color? = null
    override val background: Color = Color(0xFFFBFCF8)
    override val backgroundVariation: Color = Color(0xFFeeeeee)
    override val borders: Color = Color(0xFFececec)
    override val success: Color = Color(0xFF2AB179)
    override val error: Color = Color(0xC8133C)
    override val warning: Color = Color(0xFEA108)
}

// Definición de colores para el tema oscuro
object DarkModeColors : Colors {
    override val primary: Color = Color(0xFF28282A)
    override val primaryVariation: Color? = null
    override val primaryBackground: Color? = null
    override val primaryHighlight: Color = Color(0xFFCCCCCC)
    override val secondary: Color = Color(0xFFDADADA)
    override val secondaryVariation: Color? = null
    override val secondaryBackground: Color? = null
    override val secondaryHighlight: Color = Color(0xFFFAFAFA)
    override val accent: Color = Color(0xFF015EF7)
    override val accentVariation: Color? = null
    override val accentBackground: Color = Color(0xFF18273E)
    override val accentHighlight: Color? = null
    override val background: Color = Color(0xFF1A1A1C)
    override val backgroundVariation: Color = Color(0xFF1f1f21)
    override val borders: Color = Color(0xFF292929)
    override val success: Color = Color(0xFF2AB179)
    override val error: Color = Color(0xC8133C)
    override val warning: Color = Color(0xFEA108)
}

object ThemeProvider {

    var isDark = false

    fun toggleDarkMode() {
        isDark = !isDark
    }

    fun setDarkMode(mode: Boolean) {
        isDark = mode
    }

    enum class Radius(val radius: Int) {
        SMALL(8),
        MEDIUM(16),
        LARGE(24)
    }

    /**
     * Enum that defines the available padding sizes in the app.
     *
     * @property padding size of the padding in pixels.
     */
    enum class Padding(val padding: Int) {
        /**
         * Small padding, 8 pixels.
         */
        SMALL(8),

        /**
         * Medium padding, 16 pixels.
         */
        MEDIUM(16),

        /**
         * Large padding, 32 pixels.
         */
        LARGE(32)
    }

    val defaultTypography = Typography() // Define your typography here

    val colors: Colors
        get() = if (isDark) DarkModeColors else LightModeColors
}

fun Colors.toMaterialColors(): androidx.compose.material.Colors {
    return androidx.compose.material.Colors(
        primary = this.primary,
        primaryVariant = this.primaryVariation ?: this.primary,
        secondary = this.secondary,
        secondaryVariant = this.secondaryVariation ?: this.secondary,
        background = this.background,
        surface = this.backgroundVariation,
        error = this.error,
        onPrimary = this.secondaryHighlight ?: this.secondary,
        onSecondary = this.primaryHighlight,
        onBackground = this.accentHighlight ?: this.accent,
        onSurface = this.primaryHighlight,
        onError = this.primaryHighlight,
        isLight = !ThemeProvider.isDark
    )
}


// Static composition local for accessing the current theme colors
val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalRadius = staticCompositionLocalOf<ThemeProvider.Radius> {
    error("No radius provided")
}

val LocalPadding = staticCompositionLocalOf<ThemeProvider.Padding> {
    error("No padding provided")
}

@Composable
fun TaskerTheme(
    darkTheme: Boolean = false,
    radius: ThemeProvider.Radius = ThemeProvider.Radius.MEDIUM, // Radio por defecto
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkModeColors else LightModeColors

    // Definimos formas predeterminadas para los componentes que usan bordes redondeados
    val shapes = Shapes(
        small = RoundedCornerShape(ThemeProvider.Radius.SMALL.radius.dp),
        medium = RoundedCornerShape(ThemeProvider.Radius.MEDIUM.radius.dp),
        large = RoundedCornerShape(ThemeProvider.Radius.LARGE.radius.dp)
    )


    MaterialTheme(
        colors = colors.toMaterialColors(), // Convierte tus colores a colores de Material
        typography = ThemeProvider.defaultTypography, // Usa tu tipografía personalizada
        shapes = shapes, // Aplica las formas

    ) {
        CompositionLocalProvider(
            LocalColors provides colors,
            LocalRadius provides radius,
            LocalPadding provides ThemeProvider.Padding.SMALL,
            content = content
        )
    }
}