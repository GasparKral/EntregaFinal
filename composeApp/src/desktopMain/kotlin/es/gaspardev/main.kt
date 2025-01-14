package es.gaspardev


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.app_name
import es.gaspardev.pages.Chats
import es.gaspardev.pages.Dashboard
import es.gaspardev.pages.Diagram
import es.gaspardev.pages.Login
import es.gaspardev.providers.AppState
import es.gaspardev.providers.LocalizationProvider
import es.gaspardev.providers.TaskerTheme
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.menus.Settings
import es.gaspardev.ui.components.menus.SideBar
import es.gaspardev.ui.popovers.FloatingDialog
import org.jetbrains.compose.resources.stringResource


fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Maximized,
    )

    AppState.updateScreenSize(state.size)

    val pages: Map<Int, @Composable () -> Unit> = mapOf(
        0 to { Login() },
        1 to { Dashboard(AppState.platform) },
        2 to { Chats() },
        3 to { Diagram() }
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = state,
        onKeyEvent = {
            when {

                (it.key == Key.D && it.isCtrlPressed) ||
                        (it.key == Key.D && it.isMetaPressed) -> {
                    AppState.isDark = true
                    ThemeProvider.setDarkMode(true)
                    true
                }

                (it.key == Key.L && it.isCtrlPressed) ||
                        (it.key == Key.L && it.isMetaPressed) -> {
                    AppState.isDark = false
                    ThemeProvider.setDarkMode(false)
                    true
                }

                else -> false
            }

        }) {

        TaskerTheme(darkTheme = AppState.isDark) {
            LocalizationProvider(AppState.language) {

                Settings()

                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .let {
                            if (AppState.isDialogOpen) {
                                it.blur(6.dp, BlurredEdgeTreatment.Rectangle)
                            } else {
                                it
                            }
                        }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(ThemeProvider.Padding.MEDIUM.padding.dp)
                            .padding(top = ThemeProvider.Padding.LARGE.padding.dp)
                    ) {
                        Row {
                            SideBar()
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .padding(start = ThemeProvider.Padding.LARGE.padding.dp)
                                    .clip(MaterialTheme.shapes.large)
                            ) {
                                pages[AppState.currentPage]?.invoke()
                            }
                        }
                    }
                }
            }

            FloatingDialog(
                screen = AppState.screen,
                visibility = AppState.isDialogOpen,
                content = AppState.dialogContent,
                onCloseRequest = { AppState.isDialogOpen = false; AppState.dialogContent = {} }
            )

        }

    }
}