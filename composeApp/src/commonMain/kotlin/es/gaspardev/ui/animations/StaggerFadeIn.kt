package es.gaspardev.ui.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * A composable that animates its children in a staggered manner. The children are animated in a vertical or horizontal
 * orientation, and the animation is performed in a staggered manner, meaning that each child is animated after the previous
 * one has finished animating.
 *
 * @param modifier The modifier to apply to the composable.
 * @param animationDuration The duration of the animation. If null, the animation duration will be calculated based on the
 * size of the content list.
 * @param staggerDelay The delay between each animation. This value is used to calculate the animation duration if the
 * animationDuration parameter is null.
 * @param orientation Whether to animate the children in a vertical or horizontal orientation.
 * @param visibility Whether the children are visible or not.
 * @param contentSpacing The spacing between each child.
 * @param easingType The type of easing to use for the animation. See the [EasingType] enum for the different types of easing.
 * @param cubicBezier The cubic bezier easing to use if the [easingType] is [EasingType.CubicBezier].
 * @param content The list of children to animate.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StaggerFadeIn(
    modifier: Modifier = Modifier,
    animationDuration: Int? = null,
    staggerDelay: Int = 50,
    orientation: Orientation = Orientation.Vertical,
    visibility: Boolean = false,
    contentSpacing: Int = 16,
    easingType: EasingType = EasingType.EaseInOut,
    cubicBezier: CubicBezierEasing? = null,
    content: List<@Composable () -> Unit> = emptyList()
) {
    var localAnimationDuration = animationDuration // declare a local variable
    var localStaggerAnimationDuration = staggerDelay
    if (localAnimationDuration == null) {
        localAnimationDuration = staggerDelay * content.size // reassign the value
    } else {
        localStaggerAnimationDuration = localAnimationDuration / content.size
    }

    /**
     * Gets the easing based on the given [easingType] and optional [cubicBezier].
     */
    fun getEasing(easingType: EasingType, cubicBezier: CubicBezierEasing? = null): Easing {
        return when (easingType) {
            EasingType.Linear -> LinearEasing
            EasingType.EaseIn -> FastOutSlowInEasing
            EasingType.EaseOut -> FastOutLinearInEasing
            EasingType.EaseInOut -> LinearOutSlowInEasing
            EasingType.CubicBezier -> cubicBezier ?: CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
        }
    }

    val easing = getEasing(easingType, cubicBezier)

    when (orientation) {
        Orientation.Vertical -> {
            FlowColumn(
                verticalArrangement = Arrangement.spacedBy(contentSpacing.dp),
                horizontalArrangement = Arrangement.Center,
                modifier = modifier

            ) {
                content.forEachIndexed { index, item ->
                    AnimatedVisibility(
                        visible = visibility,
                        enter = fadeIn(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ) + slideInVertically(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ),
                        exit = fadeOut(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ) + slideOutVertically(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        )
                    ) {
                        item()
                    }
                }
            }
        }

        Orientation.Horizontal -> {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(contentSpacing.dp),
                verticalArrangement = Arrangement.Center,
                modifier = modifier

            ) {
                content.reversed().forEachIndexed { index, item ->
                    AnimatedVisibility(
                        visible = visibility,
                        enter = fadeIn(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ) + slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ),
                        exit = fadeOut(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        ) + slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = localAnimationDuration,
                                delayMillis = localStaggerAnimationDuration * index,
                                easing = easing
                            )
                        )
                    ) {
                        item()
                    }
                }
            }
        }
    }
}

enum class EasingType {
    Linear,
    EaseIn,
    EaseOut,
    EaseInOut,
    CubicBezier
}