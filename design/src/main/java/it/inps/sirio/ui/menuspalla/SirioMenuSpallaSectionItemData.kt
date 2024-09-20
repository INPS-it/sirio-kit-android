package it.inps.sirio.ui.menuspalla

import androidx.annotation.Keep

@Keep
data class SirioMenuSpallaSectionItemData(
    val title: String? = null,
    val items: List<SirioMenuSpallaItemData>,
)