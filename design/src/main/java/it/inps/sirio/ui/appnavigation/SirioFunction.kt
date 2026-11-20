//
// SirioFunction.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview(name = "System - Light", showSystemUi = true)
@Preview(name = "System - Dark", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioFunctionPreview() {
    val title = "Titolo funzione"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PreviewCase("1) No SirioTheme") {
            SirioFunction(title = title)
        }

        PreviewCase("2) Outer SirioTheme() + theme=null (DEVE ereditare outer)") {
            SirioTheme {
                SirioFunction(title = title)
            }
        }

        PreviewCase("3) Outer darkTheme=false + theme=null (DEVE essere Light)") {
            SirioTheme(darkTheme = false) {
                SirioFunction(title = title)
            }
        }

        PreviewCase("4) Outer darkTheme=true + theme=null (DEVE essere Dark)") {
            SirioTheme(darkTheme = true) {
                SirioFunction(title = title)
            }
        }

        PreviewCase("5) Outer Light ma Force Dark (override esplicito)") {
            SirioTheme(darkTheme = false) {
                SirioFunction(title = title, theme = SirioThemeMode.Dark)
            }
        }

        PreviewCase("6) Outer Dark ma Force Light (override esplicito)") {
            SirioTheme(darkTheme = true) {
                SirioFunction(title = title, theme = SirioThemeMode.Light)
            }
        }

        PreviewCase("7) Outer SirioTheme() + Force Light") {
            SirioTheme {
                SirioFunction(title = title, theme = SirioThemeMode.Light)
            }
        }

        PreviewCase("8) Outer SirioTheme() + Force Dark") {
            SirioTheme {
                SirioFunction(title = title, theme = SirioThemeMode.Dark)
            }
        }
    }
}

@Composable
private fun PreviewCase(
    label: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x11000000))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
        content()
    }
}
