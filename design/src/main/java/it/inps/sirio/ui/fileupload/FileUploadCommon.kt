//
// FileUploadCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.sirio.ui.fileupload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.fileUploadItemsPadding
import it.inps.sirio.theme.fileUploadTextPaddingBottom
import it.inps.sirio.theme.fileUploadTitlePaddingBottom
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.chip.SirioChipLabelClose
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Component that handle file selection with a title, a text, a button and a list of file names
 *
 * @param title The component title, placed on top
 * @param text The component text, placed under the title
 * @param enabled Whether the component allow items add/remove
 * @param uploadList The list of already added file names
 * @param closeContentDescription The content description of the close button
 * @param onDeleteClick The callback when a file delete button is pressed. It pass index and name of the selected file
 * @param onUploadClick The callback when the upload button is pressed
 */
@Composable
internal fun FileUploadCommon(
    title: String? = null,
    text: String? = null,
    enabled: Boolean = true,
    uploadList: List<String>,
    closeContentDescription: String? = null,
    onDeleteClick: (index: Int, value: String) -> Unit,
    onUploadClick: () -> Unit,
) {
    Column(modifier = Modifier.wrapContentSize()) {
        title?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.fileUploadTitle,
                typography = SirioTheme.typography.sliderTitle,
            )
            Spacer(modifier = Modifier.height(fileUploadTitlePaddingBottom.dp))
        }
        text?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.fileUploadText,
                typography = SirioTheme.typography.sliderText,
            )
            Spacer(modifier = Modifier.height(fileUploadTextPaddingBottom.dp))
        }
        SirioButton(
            text = "Upload",
            icon = FaIcons.ArrowUp,
            enabled = enabled,
            onClick = onUploadClick,
            size = ButtonSize.Large,
            style = ButtonStyle.Primary,
        )
        Spacer(modifier = Modifier.height(fileUploadItemsPadding.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(
                fileUploadItemsPadding.dp,
                Alignment.Start
            ),
            verticalArrangement = Arrangement.spacedBy(fileUploadItemsPadding.dp, Alignment.Top),
//            lastLineMainAxisAlignment = FlowMainAxisAlignment.Start,
        ) {
            uploadList.forEachIndexed { index, item ->
                SirioChipLabelClose(
                    label = item,
                    enabled = enabled,
                    closeContentDescription = closeContentDescription,
                ) {
                    onDeleteClick(index, item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun FileUploadCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            FileUploadCommon(
                title = "Label",
                text = "*Info upload file",
                enabled = true,
                uploadList = listOf(
                    "Nome file",
                    "Nome file 2",
                    "Nome file 3",
                    "Nome file 4",
                    "Nome file 5"
                ),
                onDeleteClick = { _, _ -> },
                onUploadClick = {}
            )
        }
    }
}
