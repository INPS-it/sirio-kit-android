//
// SirioTableDrawerItemData.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

data class SirioTableDrawerItemData(
    val title: String,
    val text: String,
    val type: SirioTableDrawerItemType,
    val onClick: () -> Unit,
)