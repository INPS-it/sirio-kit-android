package it.inps.sirio.ui.menuspalla

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ColumnScope.SirioMenuSpallaExpandableItem(
    item: SirioMenuSpallaItemData,
    level: Int,
    selectedId: String,
    parentId: String,
    onClick: (id: String) -> Unit,
) {
    val hasChildred = item.children.isNotEmpty()
    val isSelected by remember(selectedId) { derivedStateOf { item.hasChild(selectedId) } }
    val isExpanded by remember(hasChildred, isSelected)
    { mutableStateOf(hasChildred && isSelected) }
    SirioMenuSpallaItem(
        title = item.title,
        level = SirioMenuSpallaItemLevel.fromInt(level),
        tagText = item.tag,
        selected = isSelected,
        enabled = item.enabled,
        hasSubItems = hasChildred,
        onClick = {
            if (isSelected && isExpanded) {
                onClick(parentId)
            } else {
                onClick(item.id)
            }
            item.onClick()
        },
    )
    if (isExpanded) {
        item.children.forEach { child ->
            SirioMenuSpallaExpandableItem(
                item = child,
                level = level + 1,
                selectedId = selectedId,
                parentId = item.id,
                onClick = onClick,
            )
        }
    }
}