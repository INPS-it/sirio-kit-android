//
// SirioButtonTypes.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonMinWidthLarge
import it.inps.sirio.theme.buttonMinWidthMedium
import it.inps.sirio.theme.buttonMinWidthSmall
import it.inps.sirio.theme.buttonNewHeightLarge
import it.inps.sirio.theme.buttonNewHeightMedium
import it.inps.sirio.theme.buttonNewHeightSmall

/** Button style, when Dark/Light is provided it force the related theme, device theme is used otherwise. */
enum class SirioButtonHierarchy {
    Primary,
    Secondary,
    TertiaryDark,
    TertiaryLight,
    GhostLight,
    GhostDark,
    Danger,
    DangerTertiary,
    DangerGhost,
}

enum class SirioButtonSize { Large, Medium, Small }

enum class SirioButtonIconPosition { Left, Center, Right }

fun SirioButtonSize.toMinWidth(): Dp = when (this) {
    SirioButtonSize.Large -> buttonMinWidthLarge.dp
    SirioButtonSize.Medium -> buttonMinWidthMedium.dp
    SirioButtonSize.Small -> buttonMinWidthSmall.dp
}

fun SirioButtonSize.toHeight(): Dp = when (this) {
    SirioButtonSize.Large -> buttonNewHeightLarge.dp
    SirioButtonSize.Medium -> buttonNewHeightMedium.dp
    SirioButtonSize.Small -> buttonNewHeightSmall.dp
}

@Composable
fun SirioButtonHierarchy.toContainerColor(pressed: Boolean, disabled: Boolean): Color =
    when (this) {
        SirioButtonHierarchy.Primary ->
            SirioTheme.colors.button.primary.container.get(pressed, disabled)

        SirioButtonHierarchy.Secondary ->
            SirioTheme.colors.button.secondary.container.get(pressed, disabled)

        SirioButtonHierarchy.TertiaryDark ->
            SirioTheme.colors.button.tertiaryDark.container.get(pressed, disabled)

        SirioButtonHierarchy.TertiaryLight ->
            SirioTheme.colors.button.tertiaryLight.container.get(pressed, disabled)

        SirioButtonHierarchy.GhostLight ->
            SirioTheme.colors.button.ghostLight.container.get(pressed, disabled)

        SirioButtonHierarchy.GhostDark ->
            SirioTheme.colors.button.ghostDark.container.get(pressed, disabled)

        SirioButtonHierarchy.Danger ->
            SirioTheme.colors.button.danger.container.get(pressed, disabled)

        SirioButtonHierarchy.DangerTertiary ->
            SirioTheme.colors.button.dangerTertiary.container.get(pressed, disabled)

        SirioButtonHierarchy.DangerGhost ->
            SirioTheme.colors.button.dangerGhost.container.get(pressed, disabled)
    }

@Composable
fun SirioButtonHierarchy.toContentColor(pressed: Boolean, disabled: Boolean): Color = when (this) {
    SirioButtonHierarchy.Primary ->
        SirioTheme.colors.button.primary.content.get(pressed, disabled)

    SirioButtonHierarchy.Secondary ->
        SirioTheme.colors.button.secondary.content.get(pressed, disabled)

    SirioButtonHierarchy.TertiaryDark ->
        SirioTheme.colors.button.tertiaryDark.content.get(pressed, disabled)

    SirioButtonHierarchy.TertiaryLight ->
        SirioTheme.colors.button.tertiaryLight.content.get(pressed, disabled)

    SirioButtonHierarchy.GhostLight ->
        SirioTheme.colors.button.ghostLight.content.get(pressed, disabled)

    SirioButtonHierarchy.GhostDark ->
        SirioTheme.colors.button.ghostDark.content.get(pressed, disabled)

    SirioButtonHierarchy.Danger ->
        SirioTheme.colors.button.danger.content.get(pressed, disabled)

    SirioButtonHierarchy.DangerTertiary ->
        SirioTheme.colors.button.dangerTertiary.content.get(pressed, disabled)

    SirioButtonHierarchy.DangerGhost ->
        SirioTheme.colors.button.dangerGhost.content.get(pressed, disabled)
}

@Composable
fun SirioButtonHierarchy.toBorderColor(pressed: Boolean, disabled: Boolean): Color? = when (this) {
    SirioButtonHierarchy.Primary ->
        SirioTheme.colors.button.primary.border?.get(pressed, disabled)

    SirioButtonHierarchy.Secondary ->
        SirioTheme.colors.button.secondary.border?.get(pressed, disabled)

    SirioButtonHierarchy.TertiaryDark ->
        SirioTheme.colors.button.tertiaryDark.border?.get(pressed, disabled)

    SirioButtonHierarchy.TertiaryLight ->
        SirioTheme.colors.button.tertiaryLight.border?.get(pressed, disabled)

    SirioButtonHierarchy.GhostLight ->
        SirioTheme.colors.button.ghostLight.border?.get(pressed, disabled)

    SirioButtonHierarchy.GhostDark ->
        SirioTheme.colors.button.ghostDark.border?.get(pressed, disabled)

    SirioButtonHierarchy.Danger ->
        SirioTheme.colors.button.danger.border?.get(pressed, disabled)

    SirioButtonHierarchy.DangerTertiary ->
        SirioTheme.colors.button.dangerTertiary.border?.get(pressed, disabled)

    SirioButtonHierarchy.DangerGhost ->
        SirioTheme.colors.button.dangerGhost.border?.get(pressed, disabled)
}
