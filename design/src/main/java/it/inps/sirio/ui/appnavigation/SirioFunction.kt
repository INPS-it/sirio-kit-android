//
// SirioFunction.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode

/**
 * A composable function that displays a Sirio-styled top app bar with a centered title.
 *
 * @param title The text to be displayed as the title in the top app bar.
 */
@Composable
fun SirioFunction(
    title: String,
    theme: SirioThemeMode? = null,
) {
    SirioTheme(theme) {
        SirioTopAppBar(
            title = {
                SirioAppNavigationTitle(
                    title = title,
                    typography = SirioTheme.foundationTypography.headlineSmMiddle,
                    maxLines = Int.MAX_VALUE,
                )
            },
            centerTitle = true,
            windowInsets = WindowInsets(0, 0, 0, 0)
        )
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioFunctionPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val title = "Titolo funzione"
            SirioTheme {
                SirioFunction(title = title)
            }
        }
    }
}
