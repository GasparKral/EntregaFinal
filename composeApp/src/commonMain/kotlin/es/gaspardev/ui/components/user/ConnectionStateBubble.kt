package es.gaspardev.ui.components.user

import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import es.gaspardev.core.enums.UserStatus

@Composable
fun ConnectionStateBubble(connectionState: UserStatus) {

    BadgedBox(
        badge = {
            Badge(
                contentColor = when (connectionState) {
                    UserStatus.ACTIVE -> Color.Green
                    UserStatus.OFFLINE -> Color.Red
                    UserStatus.UNKNOWN -> Color.Gray
                    UserStatus.IDLE -> Color.Blue
                }
            ) {
                Text("")
            }
        }
    ) {}
}