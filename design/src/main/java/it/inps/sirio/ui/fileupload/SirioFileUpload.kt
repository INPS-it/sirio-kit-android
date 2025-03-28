//
// SirioFileUpload.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fileupload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A composable function that displays a file upload component with a list of uploaded files.
 *
 * @param uploadList The list of file names (or representations) that have been uploaded.
 * @param onDeleteClick Callback function triggered when the delete icon is clicked for a specific file.
 *                     It provides the index of the file in the `uploadList` and the file's value.
 * @param label Optional label text to display above the file list and upload button.
 * @param text Optional descriptive text to display below the label.
 * @param buttonText Text to be displayed on the upload button (defaults to "Upload").
 * @param buttonIcon Icon to be displayed on the upload button (defaults to an up arrow).
 * @param enabled Determines if the upload button and delete functionality are enabled. Defaults to true.
 * @param closeContentDescription Optional content description for the close/delete icon.
 * @param onUploadClick Callback function triggered when the upload button is clicked.
 */
@Composable
fun SirioFileUpload(
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
    SirioFileUploadCommon(
        uploadList = uploadList,
        onDeleteClick = onDeleteClick,
        label = label,
        text = text,
        buttonText = buttonText,
        buttonIcon = buttonIcon,
        enabled = enabled,
        closeContentDescription = closeContentDescription,
        onUploadClick = onUploadClick,
    )
}

@Preview
@Composable
private fun SirioFileUploadPreview() {
    SirioTheme {
        Column(
            modifier = Modifier.background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val label = "Label"
            val text = "Info upload file"
            SirioFileUpload(
                label = label,
                text = text,
                enabled = true,
                uploadList = emptyList(),
                onDeleteClick = { _, _ -> },
                onUploadClick = {}
            )
            SirioFileUpload(
                label = label,
                text = text,
                enabled = true,
                uploadList = listOf("Nome file"),
                onDeleteClick = { _, _ -> },
                onUploadClick = {}
            )
            SirioFileUpload(
                label = label,
                text = text,
                enabled = false,
                uploadList = emptyList(),
                onDeleteClick = { _, _ -> },
                onUploadClick = {}
            )
        }
    }
}