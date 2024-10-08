package es.gaspardev.ui.components.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.layout
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.gaspardev.controllers.LoggedUser
import org.jetbrains.compose.resources.InternalResourceApi


@OptIn(InternalResourceApi::class)
@Preview
@Composable
fun UserNotch() {

    val user = LoggedUser.user

    if (user == null) {
        Text("User not found")
        return
    }

    Row(
        modifier = Modifier.padding(8.dp, 8.dp)
    ) {
        UserAvatar(
            userName = user.name
        )

        ConnectionStateBubble(user.status)

        Column(
            modifier = Modifier.padding()
        ) {
            Text(user.name)
            Text(user.department.toString())
        }
    }
}

