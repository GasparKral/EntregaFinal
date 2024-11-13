package es.gaspardev.ui.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.animations.StaggerFadeIn

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SideBarComponent(
    text: String,
    icon: @Composable() (() -> Unit)? = null,
    onClickAction: () -> Unit,
    isSelected: Boolean = false,
    foldableList: List<@Composable() () -> Unit>? = null
) {

    val (folded, setFolded) = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            if (foldableList == null) {
                onClickAction()
            } else {
                setFolded(!folded)
            }
        }.fillMaxWidth().background(
            if (isSelected) {
                ThemeProvider.colors.accent
            } else {
                Color.Transparent
            }, MaterialTheme.shapes.small
        ).padding(
            horizontal = ThemeProvider.Padding.MEDIUM.padding.dp,
            vertical = ThemeProvider.Padding.SMALL.padding.dp
        )
    ) {
        if (icon != null) {
            icon()
        }
        Text(
            text,
            color = if (isSelected) {
                ThemeProvider.colors.accentHighlight ?: ThemeProvider.colors.primary
            } else {
                ThemeProvider.colors.secondary
            }
        )
        if (foldableList != null) {
            StaggerFadeIn(
                visibility = folded,
                modifier = Modifier.padding(start = 5.dp),
                content = foldableList
            )
        }
    }

}