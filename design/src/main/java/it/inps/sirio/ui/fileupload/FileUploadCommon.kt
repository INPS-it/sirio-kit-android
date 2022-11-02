//
// FileUploadCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fileupload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.chip.ChipLabelClose
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.fileUploadItemsPadding
import it.inps.sirio.theme.fileUploadTextPaddingBottom
import it.inps.sirio.theme.fileUploadTitlePaddingBottom

/**
 * Component that handle file selection with a title, a text, a button and a list of file names
 *
 * @param title The component title, placed on top
 * @param text The component text, placed under the title
 * @param enabled Whether the component allow items add/remove
 * @param uploadList The list of already added file names
 * @param onDeleteClick The callback when a file delete button is pressed. It pass index and name of the selected file
 * @param onUploadClick The callback when the upload button is pressed
 */
@Composable
internal fun FileUploadCommon(
    title: String? = null,
    text: String? = null,
    enabled: Boolean = true,
    uploadList: List<String>,
    onDeleteClick: (index: Int, value: String) -> Unit,
    onUploadClick: () -> Unit,
) {
    Column(modifier = Modifier.wrapContentSize()) {
        title?.let {
            Text(
                text = it,
                color = SirioTheme.colors.fileUploadTitle,
                style = SirioTheme.typography.sliderTitle,
            )
            Spacer(modifier = Modifier.height(fileUploadTitlePaddingBottom))
        }
        text?.let {
            Text(
                text = it,
                color = SirioTheme.colors.fileUploadText,
                style = SirioTheme.typography.sliderText,
            )
            Spacer(modifier = Modifier.height(fileUploadTextPaddingBottom))
        }
        SirioButton(
            text = "Upload",
            icon = FaIcons.ArrowUp,
            enabled = enabled,
            useMaxWidth = false,
            onClick = onUploadClick,
            size = ButtonSize.Large,
            style = ButtonStyle.Primary,
        )
        Spacer(modifier = Modifier.height(fileUploadItemsPadding))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            mainAxisSpacing = fileUploadItemsPadding,
            crossAxisSpacing = fileUploadItemsPadding,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            lastLineMainAxisAlignment = FlowMainAxisAlignment.Start,
        ) {
            uploadList.forEachIndexed { index, item ->
                ChipLabelClose(label = item, enabled = enabled) {
                    onDeleteClick(index, item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SliderCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            FileUploadCommon(
                title = "Label",
                text = "*Info upload file",
                enabled = true,
                uploadList = listOf("Nome file", "Nome file 2"),
                onDeleteClick = { _, _ -> },
                onUploadClick = {}
            )
        }
    }
}