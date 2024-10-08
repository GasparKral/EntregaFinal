package es.gaspardev.ui.components.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.enums.TaskPriority

@Composable
fun TaskSection(task: Task) {

    val borderColor = when (task.priority) {
        TaskPriority.CRITICAL -> Color.hsl(0.0f, 1.0f, 0.30f)
        TaskPriority.HIGH -> Color.Red
        TaskPriority.MEDIUM -> Color.Yellow
        TaskPriority.LOW -> Color.Green
        else -> Color.Gray
    }

    val backGroundColor = when (task.priority) {
        TaskPriority.CRITICAL -> Color.hsl(0.0f, 1.0f, 0.6f)
        TaskPriority.HIGH -> Color.hsl(0.0f, 1.0f, 0.68f)
        TaskPriority.MEDIUM -> Color.hsl(60.0f, 1.0f, 0.8f)
        TaskPriority.LOW -> Color.hsl(120.0f, 1.0f, 0.8f)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier.fillMaxSize().border(2.dp, borderColor, RoundedCornerShape(24.dp))
            .background(backGroundColor, RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp, 8.dp)
        ) {
            Text(
                text = task.title as String,
            )
            Text(
                text = task.message as String,
            )
        }
    }

}