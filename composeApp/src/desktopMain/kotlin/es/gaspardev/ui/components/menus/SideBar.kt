package es.gaspardev.ui.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import es.gaspardev.controllers.LoggedUser
import es.gaspardev.providers.AppState
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.ui.components.user.UserNotch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SideBar() {

    Box(
        modifier = Modifier.width(350.dp).fillMaxHeight()
    ) {
        FlowColumn(
            modifier = Modifier.fillMaxSize().let {
                if (ThemeProvider.isDark) {
                    it.background(
                        ThemeProvider.colors.backgroundVariation,
                        MaterialTheme.shapes.large
                    ).border(
                        1.dp,
                        ThemeProvider.colors.borders,
                        MaterialTheme.shapes.large
                    )
                } else {
                    it.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                ThemeProvider.colors.backgroundVariation,
                                ThemeProvider.colors.background
                            )
                        ),
                        MaterialTheme.shapes.large
                    )
                }
            }.padding(ThemeProvider.Padding.MEDIUM.padding.dp)

        ) {
            UserNotch(LoggedUser.user, Pair(32, 32), false)
            Divider(
                thickness = 1.dp,
                color = ThemeProvider.colors.secondaryVariation ?: ThemeProvider.colors.secondary,
                modifier = Modifier.padding(
                    top = 26.72.dp,
                    bottom = ThemeProvider.Padding.MEDIUM.padding.dp
                )
            )
            SideBarComponent(
                text = "Dashboard",
                onClickAction = { AppState.currentPage = 1 },
                isSelected = AppState.currentPage == 1
            )
            SideBarComponent(
                text = "Chat",
                onClickAction = { AppState.currentPage = 2 },
                isSelected = AppState.currentPage == 2
            )
            SideBarComponent(
                text = "Diagram",
                onClickAction = { AppState.currentPage = 3 },
                isSelected = AppState.currentPage == 3
            )


        }


    }

}