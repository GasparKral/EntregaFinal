package es.gaspardev.ui.components.user

import es.gaspardev.providers.ThemeProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.gaspardev.core.cruds.user.User
import org.jetbrains.compose.resources.InternalResourceApi


@OptIn(InternalResourceApi::class)

@Composable
fun UserNotch(
    user: User?,
    measures: Pair<Int, Int>? = null,
    connectionShow: Boolean = false,

    ) {
    if (user == null) {
        Text("User not found")
        return
    }

    Row(
        modifier = Modifier.padding(8.dp, 0.dp)
    ) {
        UserAvatar(
            userName = user.name,
            measures = measures
        )

        if (connectionShow) {
            ConnectionStateBubble(user.status)
        }
        Column(
            modifier = Modifier.padding(8.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy((-4).dp)
        ) {
            Text(
                text = user.name,
                color = ThemeProvider.colors.secondaryHighlight ?: ThemeProvider.colors.secondary,
            )
            user.department?.let {
                Text(
                    it.key,
                    style = TextStyle(fontSize = 10.5.sp),
                    color = ThemeProvider.colors.secondaryHighlight ?: ThemeProvider.colors.secondary,
                )
            }
        }
    }
}