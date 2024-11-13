package es.gaspardev.ui.components.user

import es.gaspardev.providers.ThemeProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.user
import entregafinal.composeapp.generated.resources.userDark
import org.jetbrains.compose.resources.painterResource


@Composable
fun UserAvatar(
    userName: String,
    measures: Pair<Int, Int>? = null,
) {

    // TODO: FETCH USER IMAGE

    Image(
        painter = painterResource(if (ThemeProvider.isDark) Res.drawable.user else Res.drawable.userDark),
        contentDescription = "User avatar of $userName",
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {
                placeable.place(0, 4)
            }
        }.width(measures?.first?.dp ?: 48.dp)
            .height(measures?.second?.dp ?: 48.dp)
    )
}