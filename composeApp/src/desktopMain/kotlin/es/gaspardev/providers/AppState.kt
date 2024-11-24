package es.gaspardev.providers

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.seals.Language
import java.awt.Dimension
import java.awt.Toolkit


object AppState {
    // Variables reactivas usando mutableStateOf para recomposición
    var language by mutableStateOf(Language.ES.isoFormat)
    var isDark by mutableStateOf(ThemeProvider.isDark)
    var currentPage by mutableStateOf(0)
    var isDialogOpen by mutableStateOf(false)
    var dialogContent by mutableStateOf<@Composable () -> Unit>({})
    var tasks by mutableStateOf(HashSet<Task>())

    // Propiedades estáticas para el entorno de la aplicación
    val platform: String = when (val os = System.getProperty("os.name")) {
        "Mac OS X", "Mac OS" -> "macOS"
        "Linux" -> "Linux"
        else -> if (os.startsWith("Windows")) "Windows" else os
    }
    var screen: DpSize

    init {
        screen = DpSize(
            width = Toolkit.getDefaultToolkit().screenSize.width.toDouble().dp,
            height = Toolkit.getDefaultToolkit().screenSize.height.toDouble().dp
        )
    }

    fun updateScreenSize(size: DpSize) {
        screen = size
    }
}