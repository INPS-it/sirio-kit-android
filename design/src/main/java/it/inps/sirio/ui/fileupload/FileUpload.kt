//
// FileUpload.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fileupload

import androidx.compose.runtime.Composable

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
fun FileUpload(
    title: String? = null,
    text: String? = null,
    enabled: Boolean = true,
    uploadList: List<String>,
    onDeleteClick: (index: Int, value: String) -> Unit,
    onUploadClick: () -> Unit,
) {
    FileUploadCommon(
        title = title,
        text = text,
        enabled = enabled,
        uploadList = uploadList,
        onDeleteClick = onDeleteClick,
        onUploadClick = onUploadClick,
    )
}