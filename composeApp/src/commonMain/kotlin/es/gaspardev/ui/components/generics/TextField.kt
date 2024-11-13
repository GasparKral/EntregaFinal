package es.gaspardev.ui.components.generics

import es.gaspardev.providers.ThemeProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


/**
 * A composable that displays a text field with a label, a placeholder, and optionally a leading
 * or trailing icon. The text field is multiline if the `multiline` parameter is true.
 *
 * @param value The text to display in the text field.
 * @param onValueChange The function to call when the text field's value is changed.
 * @param modifier The modifier to apply to the outermost composable.
 * @param textFieldModifier The modifier to apply to the text field itself.
 * @param placeholder The placeholder text to display when the text field is empty and unfocused.
 * @param label The label to display above the text field. If null, no label is displayed.
 * @param multiline Whether the text field should be multiline.
 * @param leadingIcon The leading icon to display. If null, no icon is displayed.
 * @param trailingIcon The trailing icon to display. If null, no icon is displayed.
 */
@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    placeholder: String = "",
    label: String? = null,
    multiline: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .focusable(interactionSource = interactionSource)
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focusRequester.requestFocus()
            },
        verticalAlignment = if (multiline) Alignment.Top else Alignment.CenterVertically
    ) {
        if (label != null) {
            Text(
                "$label ",
                modifier = Modifier.defaultMinSize(minWidth = 100.dp),
                color = if (isFocused) ThemeProvider.colors.accent
                else ThemeProvider.colors.secondary
            )
        }

        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isFocused = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(ThemeProvider.colors.background, MaterialTheme.shapes.small)
                .height(if (multiline) 100.dp else 40.dp)
                .then(textFieldModifier),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ThemeProvider.Padding.SMALL.padding.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    leadingIcon?.invoke()
                    Box {
                        if (value.isEmpty() && !isFocused) {
                            Text(
                                text = placeholder,
                                style = TextStyle(color = Color.Gray)
                            )
                        }
                        innerTextField()
                    }
                    trailingIcon?.invoke()
                }
            },
            textStyle = TextStyle(
                color = if (value.isEmpty()) Color.Gray else ThemeProvider.colors.secondaryHighlight
                    ?: ThemeProvider.colors.secondary,
            )
        )
    }
}