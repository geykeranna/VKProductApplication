package ru.testtask.vkproductapplication.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.testtask.vkproductapplication.ui.theme.AccentColor

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value
    background(
        color = AccentColor.copy(alpha=alpha)
    )
}

@Composable
fun ItemCardShimmerEffect(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .height(280.dp)
            .width(260.dp)
            .clip(RoundedCornerShape(10.dp))
            .shimmerEffect()
    )
}
