//
// SirioFileUploadCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.sirio.ui.fileupload

import androidx.annotation.Keep
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
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.fileUploadItemsPadding
import it.inps.sirio.theme.fileUploadTextPaddingBottom
import it.inps.sirio.theme.fileUploadTitlePaddingBottom
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.chip.SirioChip
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIconSource

/**
 * A composable function that provides a common UI for file uploads.
 *
 * @param uploadList The list of uploaded file names.
 * @param onDeleteClick Callback invoked when a delete action is triggered on an uploaded file.
 * The callback provides the index and value of the deleted item.
 * @param label Optional label text to be displayed above the upload area.
 * @param text Optional descriptive text to be displayed below the label.
 * @param buttonText The text to be displayed on the upload button. Defaults to "Upload".
 * @param buttonIcon The icon to be displayed on the upload button. Defaults to an upward arrow.
 * @param enabled Boolean indicating whether the component is enabled or not.
 * @param closeContentDescription Optional accessibility description for the close button of the chip.
 * @param onUploadClick Callback invoked when the upload button is clicked.
 */
@Composable
internal fun SirioFileUploadCommon(
    uploadList: List<String>,
    onDeleteClick: (index: Int, value: String) -> Unit,
    label: String? = null,
    text: String? = null,
    buttonText: String = "Upload",
    buttonIcon: FaIconType = FaIcons.ArrowUp,
    enabled: Boolean = true,
    closeContentDescription: String? = null,
    onUploadClick: () -> Unit,
) {
    Column(modifier = Modifier.wrapContentSize()) {
        label?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.fileUpload.label,
                typography = SirioTheme.foundationTypography.labelMdMiddle,
            )
            Spacer(modifier = Modifier.height(fileUploadTitlePaddingBottom.dp))
        }
        text?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.fileUpload.text,
                typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
            )
            Spacer(modifier = Modifier.height(fileUploadTextPaddingBottom.dp))
        }
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = SirioButtonHierarchy.Primary,
            text = buttonText,
            icon = SirioIconSource.FaIcon(buttonIcon),
            enabled = enabled,
            onClick = onUploadClick,
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
        ) {
            uploadList.forEachIndexed { index, item ->
                SirioChip(
                    text = item,
                    enabled = enabled,
                    closeContentDescription = closeContentDescription,
                ) {
                    onDeleteClick(index, item)
                }
            }
        }
    }
}

@Keep
data class SirioFileUploadColors(
    val label: Color,
    val text: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioFileUploadColors(
            label = Color.Unspecified,
            text = Color.Unspecified,
        )
    }
}

internal val fileUploadLightColors = SirioFileUploadColors(
    label = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorAliasInteractiveSecondaryDefault,
)

internal val fileUploadDarkColors = fileUploadLightColors

@Preview
@Composable
private fun SirioFileUploadCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            SirioFileUploadCommon(
                label = "Label",
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