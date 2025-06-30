//
// SirioStepControls.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepControlsPaddingHorizontal
import it.inps.sirio.theme.stepControlsPaddingIntra
import it.inps.sirio.theme.stepControlsPaddingVertical
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownItemData
import it.inps.sirio.ui.dropdownmenu.SirioDropdownMenu
import it.inps.sirio.ui.dropdownmenu.SirioPopupState
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioStepControls(
    back: SirioStepControlData,
    next: SirioStepControlData,
    other: List<SirioStepControlData> = emptyList(),
) {
    Row(
        Modifier.padding(
            horizontal = stepControlsPaddingHorizontal.dp,
            vertical = stepControlsPaddingVertical.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(stepControlsPaddingIntra.dp),
    ) {
        if (other.isNotEmpty()) {
            Box {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.Start
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset(0.dp, 8.dp),
                    items = other.map {
                        SirioDropdownItemData(
                            value = it.text,
                            contentDescription = it.contentDescription,
                            action = it.action,
                        )
                    }
                )
                SirioButton(
                    size = SirioButtonSize.Large,
                    hierarchy = SirioButtonHierarchy.TertiaryLight,
                    icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                ) {
                    sirioPopupState.isVisible = true
                }
            }
        }
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = SirioButtonHierarchy.Secondary,
            modifier = Modifier.weight(1f),
            text = back.text,
            enabled = back.enabled,
            onClick = back.action,
        )
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = SirioButtonHierarchy.Primary,
            modifier = Modifier.weight(1f),
            text = next.text,
            enabled = next.enabled,
            onClick = next.action,
        )
    }
}

@Keep
data class SirioStepControlData(
    val text: String,
    val enabled: Boolean,
    val contentDescription: String? = null,
    val action: () -> Unit,
)

@Preview
@Composable
private fun SirioStepControlsPreview() {
    SirioTheme {
        val back = SirioStepControlData(
            text = "Indietro",
            enabled = true,
            action = {},
        )
        val next = SirioStepControlData(
            text = "Avanti",
            enabled = true,
            action = {},
        )
        val other = listOf(
            SirioStepControlData(
                text = "Salva bozza",
                enabled = true,
                action = {},
            ),
            SirioStepControlData(
                text = "Annulla",
                enabled = true,
                action = {},
            ),
        )
        Column(Modifier.background(Color.White), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SirioStepControls(back = back, next = next)
            SirioStepControls(back = back, next = next, other = other)
            SirioStepControls(back = back, next = next, other = other)
            SirioStepControls(back = back, next = next, other = other)
        }
    }
}
