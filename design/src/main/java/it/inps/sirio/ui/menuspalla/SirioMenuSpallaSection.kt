package it.inps.sirio.ui.menuspalla

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.menuSpallaSectionPaddingTop

@Composable
fun ColumnScope.SirioMenuSpallaSection(
    title: String?,
    items: List<SirioMenuSpallaItemData>,
    selectedId: String,
    onChildSelected: (String) -> Unit,
) {
    title?.let {
        Spacer(modifier = Modifier.height(menuSpallaSectionPaddingTop.dp))
        SirioMenuSpallaItemTitleSection(title = it)
    }
    items.forEach { item ->
        SirioMenuSpallaExpandableItem(
            item = item,
            level = 0,
            selectedId = selectedId,
            parentId = "",
            onClick = { onChildSelected(it) },
        )
    }
}