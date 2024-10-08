package es.gaspardev.ui.components.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.user
import org.jetbrains.compose.resources.painterResource


@Composable
fun UserAvatar(/*userAvatar: Any,*/ userName: String, measures: Pair<Int, Int>? = null) {
    Image(
        painter = painterResource(Res.drawable.user),
        contentDescription = "User avatar of $userName",
        modifier = Modifier
            .width(measures?.first?.dp ?: 48.dp)
            .height(measures?.second?.dp ?: 48.dp)
            
    )
}