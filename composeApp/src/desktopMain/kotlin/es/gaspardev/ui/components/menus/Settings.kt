package es.gaspardev.ui.components.menus

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import entregafinal.composeapp.generated.resources.*
import es.gaspardev.providers.AppState
import es.gaspardev.providers.ThemeProvider
import es.gaspardev.seals.Language
import es.gaspardev.ui.animations.StaggerFadeIn
import java.util.*


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun Settings() {

    val (settingsOpen, setSettingsOpen) = remember { mutableStateOf(false) }
    val (showLanguageOptions, setShowLanguageOptions) = remember { mutableStateOf(false) }

    FlowRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(126.dp)
            .zIndex(150.0f)
            .absoluteOffset(
                x = AppState.screen.width - 10.dp,
                y = 10.dp
            )
    ) {

        StaggerFadeIn(
            visibility = settingsOpen,
            orientation = Orientation.Horizontal,
            staggerDelay = 75,
            modifier = Modifier.zIndex(150.0f),
            content = listOf(
                {
                    TooltipArea(tooltip = {
                        Text(
                            text = if (ThemeProvider.isDark) {
                                if (AppState.platform == "Mac OS X") "Use CMD + L" else "Use CTRL + L"
                            } else {
                                if (AppState.platform == "Mac OS X") "Use CMD + D" else "Use CTRL + D"
                            },
                            modifier = Modifier.background(
                                ThemeProvider.colors.backgroundVariation,
                                MaterialTheme.shapes.small
                            ).border(1.dp, ThemeProvider.colors.borders, MaterialTheme.shapes.small)
                                .padding(8.dp, 2.dp),
                            color = ThemeProvider.colors.secondaryHighlight
                                ?: ThemeProvider.colors.secondary
                        )
                    }) {
                        Button(
                            onClick = {
                                AppState.isDark = !AppState.isDark
                                ThemeProvider.toggleDarkMode()
                            },
                            modifier = Modifier.size(28.dp),
                            shape = CircleShape,
                            contentPadding = PaddingValues(4.dp),
                        ) {
                            Image(
                                painter = org.jetbrains.compose.resources.painterResource(if (ThemeProvider.isDark) Res.drawable.sun else Res.drawable.moon),
                                contentDescription = "Settings",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }

                },
                {
                    Button(
                        onClick = {
                            setShowLanguageOptions(!showLanguageOptions)
                        },
                        modifier = Modifier.size(28.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(4.dp),
                    ) {
                        Text(
                            AppState.language.substring(0, 2).uppercase(Locale.forLanguageTag(AppState.language)),
                            fontSize = 12.sp
                        )
                    }
                    StaggerFadeIn(
                        modifier = Modifier.offset { IntOffset(x = 0, y = 36) },
                        contentSpacing = 8,
                        visibility = showLanguageOptions,
                        orientation = Orientation.Vertical,
                        staggerDelay = 75,
                        content = listOf(
                            {
                                Button(
                                    modifier = Modifier.size(28.dp),
                                    shape = CircleShape,
                                    contentPadding = PaddingValues(4.dp),
                                    onClick = {
                                        AppState.language = Language.ES.isoFormat
                                        Locale.setDefault(Locale.forLanguageTag(AppState.language))
                                        setShowLanguageOptions(false)
                                    }
                                ) {
                                    Text("ES", fontSize = 12.sp)
                                }
                            },
                            {
                                Button(
                                    modifier = Modifier.size(28.dp),
                                    shape = CircleShape,
                                    contentPadding = PaddingValues(4.dp),
                                    onClick = {
                                        AppState.language = Language.EN.isoFormat
                                        Locale.setDefault(Locale.forLanguageTag(AppState.language))
                                        setShowLanguageOptions(false)
                                    }
                                ) {
                                    Text("EN", fontSize = 12.sp)
                                }
                            }, {
                                Button(
                                    modifier = Modifier.size(28.dp),
                                    shape = CircleShape,
                                    contentPadding = PaddingValues(4.dp),
                                    onClick = {
                                        AppState.language = Language.IT.isoFormat
                                        Locale.setDefault(Locale.forLanguageTag(AppState.language))
                                        setShowLanguageOptions(false)
                                    }
                                ) {
                                    Text("IT", fontSize = 12.sp)
                                }
                            }, {
                                Button(
                                    modifier = Modifier.size(28.dp),
                                    shape = CircleShape,
                                    contentPadding = PaddingValues(4.dp),
                                    onClick = {
                                        AppState.language = Language.FR.isoFormat
                                        Locale.setDefault(Locale.forLanguageTag(AppState.language))
                                        setShowLanguageOptions(false)
                                    }
                                ) {
                                    Text("FR", fontSize = 12.sp)
                                }
                            }, {
                                Button(
                                    modifier = Modifier.size(28.dp),
                                    shape = CircleShape,
                                    contentPadding = PaddingValues(4.dp),
                                    onClick = {
                                        AppState.language = Language.DE.isoFormat
                                        Locale.setDefault(Locale.forLanguageTag(AppState.language))
                                        setShowLanguageOptions(false)
                                    }
                                ) {
                                    Text("DE", fontSize = 12.sp)
                                }
                            }
                        )
                    )
                },
            )

        )
        Button(
            onClick = {
                setSettingsOpen(!settingsOpen)
            },
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
        ) {
            Image(
                painter = org.jetbrains.compose.resources.painterResource(if (ThemeProvider.isDark) Res.drawable.settings else Res.drawable.settingsDark),
                contentDescription = "Settings",
                contentScale = ContentScale.FillBounds
            )
        }
    }

}