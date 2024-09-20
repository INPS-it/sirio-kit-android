package it.inps.sirio.ui.menuspalla

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class SirioMenuSpallaItemData(
    val title: String,
    val tag: String = "",
    val enabled: Boolean = true,
    val children: List<SirioMenuSpallaItemData> = emptyList(),
    val onClick: () -> Unit,
) {
    val id: String = UUID.randomUUID().toString()

    fun hasChild(childId: String): Boolean {
        if (this.id == childId) return true

        for (item in this.children) {
            if (item.id == childId) return true
            if (item.hasChild(childId)) return true
        }

        return false
    }
}

fun SirioMenuSpallaItemData.hasInvalidDeep(): Boolean {
    fun checkDepth(item: SirioMenuSpallaItemData, currentDepth: Int): Boolean {
        if (currentDepth > 2) return true
        if (item.children.isEmpty()) return false
        return item.children.any { checkDepth(it, currentDepth + 1) }
    }
    return checkDepth(this, 0)
}