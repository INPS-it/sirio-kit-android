//
// SirioTableCardSort.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.table.card

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableCardSortButtonPadding
import it.inps.sirio.theme.tableCardSortCornerRadius
import it.inps.sirio.theme.tableCardSortItemSpacing
import it.inps.sirio.theme.tableCardSortPaddingBottom
import it.inps.sirio.theme.tableCardSortPaddingHorizontal
import it.inps.sirio.theme.tableCardSortPaddingTop
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.radiobutton.SirioRadioButton
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SirioTableCardSort(
    title: String,
    buttonText: String,
    options: List<String>,
    initialSelection: Int? = 0,
    onDismiss: () -> Unit,
    onButtonClick: (selectedIndex: Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selected by remember { mutableStateOf(initialSelection) }

    val elevation = with(LocalDensity.current) {
        SirioTheme.colors.table.sort.shadow.blurRadius.toDp()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(
            topStart = tableCardSortCornerRadius.dp,
            topEnd = tableCardSortCornerRadius.dp,
        ),
        containerColor = SirioTheme.colors.table.sort.background,
        tonalElevation = elevation,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = tableCardSortPaddingHorizontal.dp)
                .padding(top = tableCardSortPaddingTop.dp, bottom = tableCardSortPaddingBottom.dp),
        ) {
            SirioButton(
                size = SirioButtonSize.Medium,
                hierarchy = SirioTheme.colors.table.sort.close,
                modifier = Modifier.align(Alignment.End),
                icon = SirioIconSource.FaIcon(FaIcons.Times),
                onClick = onDismiss,
            )
            SirioText(
                text = title,
                color = SirioTheme.colors.table.sort.title,
                typography = SirioTheme.foundationTypography.headlineSmHeavy,
            )
            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
            ) {
                itemsIndexed(options) { index, label ->
                Spacer(Modifier.height(tableCardSortItemSpacing.dp))
                    SirioRadioButton(
                        text = label,
                        selected = selected == index,
                        onClick = { selected = index }
                    )
                }

                item { Spacer(Modifier.height(tableCardSortButtonPadding.dp)) }
            }

            SirioButton(
                size = SirioButtonSize.Large,
                hierarchy = SirioTheme.colors.table.sort.button,
                modifier = Modifier.fillMaxWidth(),
                text = buttonText,
                enabled = selected != null,
                onClick = { selected?.let { onButtonClick(it) } },
            )
        }
    }
}

@Keep
data class SirioTableCardSortColors(
    val background: Color,
    val shadow: Shadow,
    val close: SirioButtonHierarchy,
    val title: Color,
    val text: Color,
    val button: SirioButtonHierarchy,
) {
    companion object Companion {
        @Stable
        val Unspecified = SirioTableCardSortColors(
            background = Color.Unspecified,
            shadow = Shadow.None,
            close = SirioButtonHierarchy.GhostLight,
            title = Color.Unspecified,
            text = Color.Unspecified,
            button = SirioButtonHierarchy.Primary,
        )
    }
}

internal val tableCardSortLightColors = SirioTableCardSortColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    shadow = StyleDictionaryBoxShadow.elevationElevation04,
    close = SirioButtonHierarchy.GhostLight,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    button = SirioButtonHierarchy.Primary,
)

internal val tableCardSortDarkColors = tableCardSortLightColors

@Preview
@Composable
private fun SirioTableCardSortPreview() {
    SirioTheme {
        SirioTableCardSort(
            title = "Ordina",
            buttonText = "Applica",
            options = List(6) { "Lorem ipsum sagittis" },
            initialSelection = 1,
            onButtonClick = {},
            onDismiss = {}
        )
    }
}