package es.gaspardev.ui.components.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.gaspardev.controllers.DataBaseConnection
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.cruds.task.TaskModifier
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.core.enums.TaskStatus
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.user.UserNotch
import java.text.SimpleDateFormat

@Composable
fun TaskSection(task: Task) {

    val (extended, setExtended) = remember { mutableStateOf(false) }

    val accentColor = when (task.priority) {
        TaskPriority.CRITICAL -> Color.hsl(0.0f, 1.0f, 0.30f)
        TaskPriority.HIGH -> Color.Red
        TaskPriority.MEDIUM -> Color.Yellow
        TaskPriority.LOW -> Color.Green
        else -> Color.Gray
    }

    val softColor = when (task.priority) {
        TaskPriority.CRITICAL -> Color.hsl(0.0f, 1.0f, 0.6f)
        TaskPriority.HIGH -> Color.hsl(0.0f, 1.0f, 0.68f)
        TaskPriority.MEDIUM -> Color.hsl(60.0f, 1.0f, 0.8f)
        TaskPriority.LOW -> Color.hsl(120.0f, 1.0f, 0.8f)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier.fillMaxWidth().border(2.dp, accentColor, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                setExtended(!extended)
                TaskModifier.modifyStatus(task, TaskStatus.OPEN)
                //DataBaseConnection.updateTask(task)
            }

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(ThemeProvider.Padding.SMALL.padding.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                UserNotch(task.userCreator, measures = Pair(24, 24))
                Column {
                    Text(
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                        text = task.title as String,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Text(
                        modifier = Modifier.background(softColor, RoundedCornerShape(8.dp)).padding(8.dp, 4.dp),
                        color = accentColor,
                        style = TextStyle(fontSize = 10.sp),
                        text = SimpleDateFormat("dd/MM/YY").format(task.dueDate)
                    )
                }
            }
            Text(
                text = task.message as String,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

}