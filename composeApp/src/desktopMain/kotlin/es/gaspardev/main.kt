package es.gaspardev

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import es.gaspardev.Ui.components.AddNewTaskButton
import androidx.compose.ui.Modifier

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Entrega Final",
    ) {
        AddNewTaskButton(onClick = {}, Modifier.fillMaxSize())
    }
}