package es.gaspardev.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.new_task_button
import es.gaspardev.controllers.DataBaseConnection
import es.gaspardev.providers.AppState
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.menus.NewTaskMenu
import es.gaspardev.ui.components.task.TaskBox
import es.gaspardev.ui.popovers.FloatingDialog
import org.jetbrains.compose.resources.stringResource
import java.awt.Dimension
import java.awt.Toolkit

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun Dashboard(
    platform: String,
    screen: Dimension = Toolkit.getDefaultToolkit().screenSize
) {

    LaunchedEffect(Unit) {
        AppState.tasks = DataBaseConnection.getTasks()
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Barra de opciones horizontal (cabezera de contenido)
        Box(
            modifier = Modifier.padding(ThemeProvider.Padding.MEDIUM.padding.dp)
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(ThemeProvider.Padding.MEDIUM.padding.dp),
                maxItemsInEachRow = 3,
                modifier = Modifier.height(200.dp)
            ) {
                TooltipArea(
                    tooltip = {
                        Text(
                            if (platform == "Max OS X") "Use CMD + N" else "Use CRT + N",
                            modifier = Modifier.background(
                                ThemeProvider.colors.backgroundVariation,
                                MaterialTheme.shapes.small
                            ).border(
                                1.dp,
                                ThemeProvider.colors.borders,
                                MaterialTheme.shapes.small
                            )
                                .padding(8.dp, 2.dp),
                            color = ThemeProvider.colors.secondaryHighlight
                                ?: ThemeProvider.colors.secondary
                        )
                    }
                ) {
                    Button(
                        onClick = {
                            AppState.isDialogOpen = true
                            AppState.dialogContent = {
                                NewTaskMenu()
                            }
                        },
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(stringResource(Res.string.new_task_button))
                    }
                }

            }
        }
        TaskBox(
            tasks = AppState.tasks,
            screen = AppState.screen
        )

        FloatingDialog(
            visibility = AppState.isDialogOpen,
            screen = screen,
            onCloseRequest = { AppState.isDialogOpen = false; AppState.dialogContent = {} },
            content = { NewTaskMenu() }
        )

    }
}