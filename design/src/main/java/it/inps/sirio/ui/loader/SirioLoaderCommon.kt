//
// SirioLoaderCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.loader

import androidx.annotation.Keep
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.loaderSize

@Composable
fun SirioLoaderCommon() {
    val rotation by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = LinearEasing)
        )
    )
    val loaderColors = SirioTheme.colors.loader
    Canvas(
        modifier = Modifier
            .size(loaderSize.dp)
            .graphicsLayer { rotationZ = rotation }
    ) {
        val strokeWidth = size.minDimension * 0.10f

        val brush = Brush.sweepGradient(
            colors = listOf(
                Color.Transparent,
                loaderColors.indicator.copy(alpha = 0.05f),
                loaderColors.indicator.copy(alpha = 0.25f),
                loaderColors.indicator.copy(alpha = 0.7f),
                loaderColors.indicator,
                Color.Transparent,
            ),
            center = center
        )

        drawArc(
            brush = brush,
            startAngle = 5f,
            sweepAngle = 285f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Keep
@Immutable
data class SirioLoaderColors(
    val indicator: Color,
) {
    companion object {
        val Unspecified = SirioLoaderColors(
            indicator = Color.Unspecified,
        )
    }
}

internal val loaderLightColors = SirioLoaderColors(
    indicator = FoundationColor.colorGlobalPrimary100,
)

internal val loaderDarkColors = loaderLightColors

@Preview(showSystemUi = true)
@Composable
private fun SirioLoaderCommonPreview() {
    SirioTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SirioLoaderCommon()
        }
    }
}