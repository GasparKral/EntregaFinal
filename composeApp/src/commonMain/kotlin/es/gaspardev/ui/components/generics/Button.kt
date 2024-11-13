package es.gaspardev.ui.components.generics

import es.gaspardev.providers.LocalColors
import es.gaspardev.providers.LocalRadius
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val contextModifier = Modifier.clickable {
        onClick()
    }
        .background(LocalColors.current.accentBackground!!, RoundedCornerShape(LocalRadius.current.radius.dp))
        .border(1.dp, LocalColors.current.accent, RoundedCornerShape(LocalRadius.current.radius.dp)).then(modifier)

    Box(
        contentAlignment = Alignment.Center,
        modifier = contextModifier
    ) {
        content()
    }
}