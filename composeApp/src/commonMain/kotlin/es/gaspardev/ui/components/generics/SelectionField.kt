package es.gaspardev.ui.components.generics

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.caret
import entregafinal.composeapp.generated.resources.caretDark
import es.gaspardev.providers.ThemeProvider
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
/**
 * A composable that displays a text field with a label and a list of options to select from. The list of options
 * is displayed when the text field is clicked. The selected value is stored in a mutable state variable.
 *
 * @param modifier The modifier to apply to the composable.
 * @param optionsModifier The modifier to apply to the options list.
 * @param label The label to display above the text field. If null, no label is displayed.
 * @param options The list of options to select from.
 * @param value The selected value. This value is stored in a mutable state variable.
 * @param onValueChange The function to call when the selected value is changed.
 */
@Composable
fun SelectionField(
    modifier: Modifier = Modifier,
    optionsModifier: Modifier = Modifier,
    label: String? = null,
    options: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    icon: Boolean = true,
    customOptionComponent: (@Composable (String) -> Unit)? = null
) {

    val (openSelection, setOpenSelection) = remember { mutableStateOf(false) }
    val with = options.maxOf { it.length } * 10 + 16

    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            if (label != null) {
                Text(
                    label,
                    modifier = Modifier,
                    color = if (openSelection) ThemeProvider.colors.accent else ThemeProvider.colors.secondaryHighlight
                        ?: ThemeProvider.colors.secondary
                )
            }
            Row(
                modifier = Modifier
                    .background(ThemeProvider.colors.backgroundVariation, MaterialTheme.shapes.small)
                    .border(1.dp, ThemeProvider.colors.borders, MaterialTheme.shapes.small)
                    .padding(ThemeProvider.Padding.SMALL.padding.dp)
                    .width(with.dp)
                    .then(modifier)
            ) {

                Column {
                    Row(
                        modifier = Modifier.clickable {
                            setOpenSelection(!openSelection)
                        }
                    ) {
                        if (icon) {
                            Image(
                                painterResource(if (!ThemeProvider.isDark) Res.drawable.caret else Res.drawable.caretDark),
                                label,
                                modifier = Modifier.rotate(
                                    if (openSelection) 180f else 0f
                                )
                            )
                        }

                        if (customOptionComponent != null) {
                            customOptionComponent(value)
                        } else {
                            Text(
                                value,
                                color = ThemeProvider.colors.secondaryHighlight
                                    ?: ThemeProvider.colors.secondary
                            )
                        }
                    }
                    AnimatedVisibility(
                        visible = openSelection,
                        enter = expandVertically(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        FlowColumn(
                            modifier = Modifier.let {
                                if (options.size > 5) it.scrollable(
                                    orientation = Orientation.Vertical,
                                    state = ScrollState(0)
                                )
                                else it
                            }
                        ) {
                            options.filter { option -> option != value }.forEach { option ->
                                if (customOptionComponent != null) {
                                    Box(

                                        modifier = modifier
                                            .clickable(
                                                indication = null,
                                                interactionSource = MutableInteractionSource()
                                            ) {
                                                onValueChange(option)
                                                setOpenSelection(false)
                                            }
                                            .padding(ThemeProvider.Padding.SMALL.padding.dp)

                                    ) {
                                        customOptionComponent(option)
                                    }
                                } else {
                                    Text(
                                        option,
                                        color = ThemeProvider.colors.secondaryHighlight
                                            ?: ThemeProvider.colors.secondary,
                                        modifier = if (icon) {
                                            Modifier.clickable {
                                                onValueChange(option)
                                                setOpenSelection(false)
                                            }.padding(vertical = 4.dp).then(optionsModifier).padding(start = 24.dp)
                                        } else {
                                            Modifier.clickable {
                                                onValueChange(option)
                                                setOpenSelection(false)
                                            }.padding(vertical = 4.dp).then(optionsModifier)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}