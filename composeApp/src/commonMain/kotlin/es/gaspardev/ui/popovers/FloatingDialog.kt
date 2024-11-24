package es.gaspardev.ui.popovers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import entregafinal.composeapp.generated.resources.Res
import entregafinal.composeapp.generated.resources.close
import entregafinal.composeapp.generated.resources.closeDark
import es.gaspardev.providers.ThemeProvider
import org.jetbrains.compose.resources.painterResource
import java.awt.Toolkit


/**
 * A composable function that displays a floating dialog with customizable size and content.
 * The dialog is animated to fade in and out based on the visibility parameter.
 *
 * @param visibility Controls the visibility of the dialog. If true, the dialog is displayed.
 * @param onCloseRequest A callback function executed when the close button is clicked.
 * @param screen The screen dimensions, used to center the dialog. Defaults to the current screen size.
 * @param size A pair representing the width and height of the dialog in dp. Defaults to 750x500 dp.
 * @param modifier A [Modifier] applied to the dialog for additional styling or layout adjustments.
 * @param content A composable lambda that defines the content displayed inside the dialog.
 */
@Composable
fun FloatingDialog(
    visibility: Boolean,
    onCloseRequest: () -> Unit,
    screen: DpSize = DpSize(
        Toolkit.getDefaultToolkit().screenSize.width.dp,
        Toolkit.getDefaultToolkit().screenSize.height.dp
    ),
    size: Pair<Dp, Dp> = Pair(screen.width / 2, screen.height / 2 + 350.dp),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    /**
     * Animate the visibility of the dialog box. Fade in and grow vertically when the dialog is visible,
     * and fade out and shrink vertically when the dialog is not visible.
     */
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(), // Fade in and grow vertically
        exit = fadeOut(), // Fade out and shrink vertically
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(0x501C1C1C)) // Set the background color of the dialog box
        ) {

            /**
             * The dialog box itself
             */
            Box(
                modifier = Modifier // Apply the following modifier to the dialog box
                    .zIndex(250.0f) // Set the z-index of the dialog to ensure it is visible on top of other components
                    .size( // Set the size of the dialog box
                        size.first,
                        if (size.second > screen.height) (screen.height - (25 * 2).dp) else size.second
                    )
                    .scrollable(
                        orientation = Orientation.Vertical,
                        enabled = visibility,
                        state = rememberScrollState()
                    )
                    .offset(
                        screen.width / 2 - size.first / 2,
                        if (size.second > screen.height) 25.dp else screen.height / 2 - size.second / 2
                    )
                    .background(
                        ThemeProvider.colors.backgroundVariation,
                        MaterialTheme.shapes.large
                    ) // Set the background color of the dialog
                    .border(
                        2.dp,
                        ThemeProvider.colors.borders,
                        MaterialTheme.shapes.large
                    ) // Add a border around the dialog
                    .padding(ThemeProvider.Padding.MEDIUM.padding.dp) // Add some padding around the dialog
                    .then(modifier), // Apply any additional modifier passed as a parameter
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(if (ThemeProvider.isDark) Res.drawable.close else Res.drawable.closeDark),
                    contentDescription = "Close button",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { onCloseRequest() }
                )
                content() // Render the content of the dialog box
            }
        }
    }

}