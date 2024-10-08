package es.gaspardev

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.*
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.app_name
import es.gaspardev.core.auxiliars.UseAddress
import es.gaspardev.core.cruds.task.Task
import es.gaspardev.core.cruds.task.TaskBuilder
import es.gaspardev.core.cruds.user.User
import es.gaspardev.core.cruds.user.UserBuilder
import es.gaspardev.core.enums.CompanyDepartment
import es.gaspardev.core.enums.TaskPriority
import es.gaspardev.core.enums.TaskStatus
import es.gaspardev.core.enums.UserRoles
import es.gaspardev.ui.components.task.TaskSection
import es.gaspardev.ui.components.user.UserNotch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Maximized
    )


    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = state,

        ) {
        TaskSection(createTask())
    }
}

private fun createTask(): Task {
    UserBuilder.newUser(
        name = "TestUSer",
        email = "test@user",
        userAccess = UserRoles.ADMIN
    )
    TaskBuilder.newTask(
        UserBuilder.build()
    )
    TaskBuilder.setTitle("Test Title")
    TaskBuilder.setDescription("Test Description")
    TaskBuilder.setMessage("THIS IS A MESSAGE")
    TaskBuilder.setPriority(TaskPriority.MEDIUM)
    return TaskBuilder.build()
}