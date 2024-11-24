package es.gaspardev.ui.components.task

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.sort_by
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.enums.SortBy
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.generics.SelectionField
import org.jetbrains.compose.resources.stringArrayResource
import java.time.Instant
import java.util.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskBox(
    tasks: HashSet<Task>,
) {

    val (sortBy, setSortBy) = remember { mutableStateOf(SortBy.PRIORITY) }
    val (columnWidth, setColumnWidth) = remember { mutableStateOf(300) }

    fun sortByPriority(task: HashSet<Task>): List<List<Task>> {
        return task.groupBy { it.priority }.values.toList()
    }

    fun sortByTaskStatus(tasks: HashSet<Task>): List<List<Task>> {
        return tasks.groupBy { it.status }.values.toList()
    }

    fun sortByDueDate(tasks: HashSet<Task>): List<List<Task>> {

        val nowDate = Date.from(Instant.now())

        // Filter and map only valid dates once
        val validDates = tasks.mapNotNull { it.dueDate?.time }

        if (validDates.isEmpty()) {
            return listOf(emptyList(), emptyList(), emptyList()) // Handle empty case
        }

        val averageDate = validDates.average().toLong()
        val maxDate = validDates.maxOrNull() ?: averageDate

        // Calculate limits for deadlines
        val firstDeadlineLimit = (averageDate + nowDate.time) / 2
        val secondDeadlineLimit = (averageDate + maxDate) / 2

        // Classify tasks by deadline
        val (shortTerm, mediumAndLongTerm) = tasks.partition {
            it.dueDate != null && it.dueDate!!.time < firstDeadlineLimit
        }

        val (mediumTerm, longTerm) = mediumAndLongTerm.partition {
            it.dueDate != null && it.dueDate!!.time <= secondDeadlineLimit
        }

        return listOf(shortTerm, mediumTerm, longTerm)
    }

    fun sortByCreationDate(tasks: HashSet<Task>): List<List<Task>> {

        val nowDate = Date.from(Instant.now())

        // Filter and map only valid creation dates once
        val validCreationDates = tasks.map { it.creationDate.time }

        if (validCreationDates.isEmpty()) {
            return listOf(emptyList(), emptyList(), emptyList()) // Handle empty case
        }

        val averageCreationDate = validCreationDates.average().toLong()
        val maxCreationDate = validCreationDates.maxOrNull() ?: averageCreationDate

        // Calculate limits for deadlines
        val firstDeadlineLimit = (averageCreationDate + nowDate.time) / 2
        val secondDeadlineLimit = (averageCreationDate + maxCreationDate) / 2

        // Classify tasks by creation date
        val (shortTerm, mediumAndLongTerm) = tasks.partition {
            it.creationDate.time < firstDeadlineLimit
        }

        val (mediumTerm, longTerm) = mediumAndLongTerm.partition {
            it.creationDate.time <= secondDeadlineLimit
        }

        return listOf(shortTerm, mediumTerm, longTerm)
    }

    fun sortBy(sortingDirective: SortBy): List<List<Task>> {
        return when (sortingDirective) {
            SortBy.PRIORITY -> sortByPriority(tasks)
            SortBy.DUE_DATE -> sortByDueDate(tasks)
            SortBy.CREATION_DATE -> sortByCreationDate(tasks)
            SortBy.TASK_STATUS -> sortByTaskStatus(tasks)
        }
    }


    Box(
        modifier = Modifier.scrollable(
            orientation = Orientation.Vertical,
            state = ScrollableState { 0f },
            enabled = true
        )
            .fillMaxSize()
            .padding(ThemeProvider.Padding.MEDIUM.padding.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            FlowRow(modifier = Modifier.fillMaxWidth()) {
                SelectionField(
                    value = stringArrayResource(Res.array.sort_by)[sortBy.ordinal],
                    onValueChange = {
                        setSortBy(SortBy.fromString(it))
                    },
                    options = stringArrayResource(Res.array.sort_by),
                )
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth()
                    .onSizeChanged { size ->
                        setColumnWidth(size.width)
                    },
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sortBy(sortBy).forEach { taskList ->
                    FlowColumn(
                        modifier = Modifier
                            .let {
                                when (sortBy.name) {
                                    "PRIORITY" -> it.width((columnWidth / 5 - 8 * 4).dp)
                                    "TASK_STATUS" -> it.width((columnWidth / 6 - 8 * 5).dp)
                                    else -> it.width((columnWidth / 3 - 8 * 2).dp)
                                }
                            },
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        when (sortBy.name) {
                            "PRIORITY" -> {
                                Text(
                                    text = taskList[0].priority.name,
                                    color = ThemeProvider.colors.secondary,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Divider(
                                    color = ThemeProvider.colors.accent,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }

                            "TASK_STATUS" -> {
                                Text(
                                    text = taskList[0].status.name,
                                    color = ThemeProvider.colors.secondary,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Divider(
                                    color = ThemeProvider.colors.accent,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }

                        taskList.forEach { task ->
                            TaskSection(task)
                        }
                    }
                }
            }
        }

    }
}