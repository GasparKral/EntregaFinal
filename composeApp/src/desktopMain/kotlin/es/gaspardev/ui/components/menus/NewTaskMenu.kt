package es.gaspardev.ui.components.menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.new_task_dialog_tags_labels
import es.gaspardev.controllers.DataBaseConnection
import es.gaspardev.controllers.LoggedUser
import es.gaspardev.core.auxiliars.seals.UseAddress
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.cruds.task.TaskBuilder
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.providers.AppState
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.generics.SelectionField
import es.gaspardev.ui.components.generics.TextField
import org.jetbrains.compose.resources.stringResource
import java.time.Instant
import java.util.*
import kotlin.collections.HashSet


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewTaskMenu() {

    val labels = stringResource(Res.string.new_task_dialog_tags_labels).split(",")

    val (title, setTitle) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }
    val (message, setMessage) = remember { mutableStateOf("") }
    val (priority, setPriority) = remember { mutableStateOf(TaskPriority.MEDIUM) }
    val (addressTo, setAddressTo) = remember { mutableStateOf(UseAddress.User(LoggedUser.user!!)) }

    val dateRange by remember { mutableStateOf(DateRangePickerState(locale = CalendarLocale.forLanguageTag(AppState.language))) }


    var notification by remember { mutableStateOf("") }
    var notificationColor by remember { mutableStateOf(Color.White) }

    val colors: DatePickerColors = DatePickerDefaults.colors(
        dayInSelectionRangeContentColor = ThemeProvider.colors.accent,
        dayInSelectionRangeContainerColor = ThemeProvider.colors.background,
        dayContentColor = ThemeProvider.colors.secondary,
        containerColor = ThemeProvider.colors.backgroundVariation,
        dividerColor = ThemeProvider.colors.borders,
        selectedDayContainerColor = ThemeProvider.colors.accent,
        selectedDayContentColor = Color.White,
        todayContentColor = ThemeProvider.colors.accent,
        todayDateBorderColor = ThemeProvider.colors.accentVariation ?: ThemeProvider.colors.secondaryVariation
        ?: ThemeProvider.colors.secondary,
        headlineContentColor = ThemeProvider.colors.secondary,
        subheadContentColor = ThemeProvider.colors.secondary,
        weekdayContentColor = ThemeProvider.colors.accentHighlight ?: ThemeProvider.colors.accent,
        dateTextFieldColors = TextFieldDefaults.colors(
            focusedPlaceholderColor = ThemeProvider.colors.accent,
            focusedIndicatorColor = ThemeProvider.colors.accent,
            focusedContainerColor = Color.Transparent,
            unfocusedPlaceholderColor = ThemeProvider.colors.secondary,
            unfocusedIndicatorColor = ThemeProvider.colors.secondary,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = ThemeProvider.colors.accentHighlight ?: ThemeProvider.colors.accent,
            unfocusedTextColor = ThemeProvider.colors.secondary,
            focusedLabelColor = ThemeProvider.colors.accentHighlight ?: ThemeProvider.colors.accent,
            unfocusedLabelColor = ThemeProvider.colors.accentHighlight ?: ThemeProvider.colors.accent
        )
    )

    FlowColumn(
        modifier = Modifier.padding(
            horizontal = ThemeProvider.Padding.LARGE.padding.dp,
            vertical = ThemeProvider.Padding.MEDIUM.padding.dp
        ).fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(ThemeProvider.Padding.MEDIUM.padding.dp),
    ) {

        TextField(
            value = title,
            onValueChange = { setTitle(it) },
            label = labels[0],
            placeholder = "Crear String Array de Cadenas de ejemplos"
        )

        TextField(
            value = description,
            onValueChange = { setDescription(it) },
            label = labels[1],
            placeholder = "Crear String Array de Cadenas de ejemplos"
        )

        TextField(
            value = message,
            onValueChange = { setMessage(it) },
            label = labels[2],
            placeholder = "Crear String Array de Cadenas de ejemplos",
            multiline = true
        )

        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = ThemeProvider.Padding.LARGE.padding.dp)
        ) {

            SelectionField(
                value = priority.toString(),
                onValueChange = { setPriority(TaskPriority.valueOf(it)) },
                label = labels[3],
                options = TaskPriority.entries.map { it.toString() },
                icon = false
            )

            SelectionField(
                value = "a",
                onValueChange = {
                    setAddressTo(UseAddress.User(LoggedUser.user!!))
                },
                label = "Enviar A",
                options = listOf(""),
                icon = false
            )


        }

        DateRangePicker(
            state = dateRange,
            modifier = Modifier.size(
                (AppState.screen.width / 2 - (ThemeProvider.Padding.LARGE.padding * 2).dp),
                350.dp
            ),
            showModeToggle = true,
            colors = colors
        )

        Text(notification, color = notificationColor)

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                try {
                    if (title.isNotEmpty() && description.isNotEmpty() && message.isNotEmpty()) {
                        TaskBuilder.newTask(LoggedUser.user!!)
                        TaskBuilder.setTitle(title)
                        TaskBuilder.setMessage(message)
                        TaskBuilder.setDescription(description)
                        TaskBuilder.setPriority(priority)
                        TaskBuilder.setDueDate(Date(dateRange.selectedEndDateMillis ?: Instant.now().toEpochMilli()))
                        dateRange.selectedStartDateMillis?.let { TaskBuilder.setInitialDate(Date(it)) }
                        TaskBuilder.setAddressFrom(UseAddress.User(LoggedUser.user!!))
                        TaskBuilder.setAddressTo(addressTo)

                        //TODO(CAMBIAR A DB_QUERY)
                        //  DataBaseConnection.addNewTask(TaskBuilder.build())
                        AppState.tasks = (AppState.tasks + TaskBuilder.build()) as HashSet<Task>

                        notificationColor = ThemeProvider.colors.accent
                        notification = "Tarea creada con exito"
                    } else {
                        notificationColor = Color.Red
                        notification = "Faltan campos por rellenar"
                    }
                } catch (e: Exception) {
                    notificationColor = Color.Red
                    notification = e.message.toString()
                    println(e.stackTraceToString())
                }

            }
        ) {
            Text("Añadir Nueva Tarea")
        }
    }


}