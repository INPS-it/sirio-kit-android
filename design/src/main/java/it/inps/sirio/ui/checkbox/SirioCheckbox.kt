//
// SirioCheckbox.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio checkbox
 *
 * @param text The string on the checkbox right
 * @param checked Whether the checkbox is checked
 * @param enabled Whether the checkbox is enabled
 * @param onCheckedChange The callback when the checkbox state change
 */
@Composable
fun SirioCheckbox(
    text: String? = null,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxCommon(
        checked = checked,
        text = text,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
    )
}

/**
 * The Sirio checkbox
 *
 * @param text The string on the checkbox right
 * @param checked Whether the checkbox is checked
 * @param enabled Whether the checkbox is enabled
 * @param onCheckedChange The callback when the checkbox state change
 */
@Composable
fun SirioCheckbox(
    text: AnnotatedString,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxCommon(
        checked = checked,
        text = text,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
    )
}

@Preview
@Composable
private fun CheckboxPreview() {
    SirioTheme {
        Column {
            SirioCheckbox(
                text = "Title",
                checked = false,
                onCheckedChange = {},
                enabled = true
            )
        }
    }
}
