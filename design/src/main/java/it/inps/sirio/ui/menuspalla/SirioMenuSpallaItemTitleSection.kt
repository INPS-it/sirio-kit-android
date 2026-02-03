package it.inps.sirio.ui.menuspalla

import androidx.annotation.Keep
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.menuSpallaItemTitleSectionBorderWidth
import it.inps.sirio.theme.menuSpallaItemTitleSectionHeight
import it.inps.sirio.theme.menuSpallaItemTitleSectionPaddingHorizontal
import it.inps.sirio.theme.menuSpallaItemTitleSectionPaddingTop
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border
import it.inps.sirio.utils.takeTwoWords

@Composable
fun SirioMenuSpallaItemTitleSection(
    title: String,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(menuSpallaItemTitleSectionHeight.dp)
            .border(
                bottom = Border(
                    menuSpallaItemTitleSectionBorderWidth.dp,
                    SirioTheme.colors.menuSpalla.itemTitleSection.border,
                )
            ),
        color = SirioTheme.colors.menuSpalla.itemTitleSection.background,
        contentColor = SirioTheme.colors.menuSpalla.itemTitleSection.content,
    ) {
        SirioTextCommon(
            text = title,
            modifier = Modifier
                .padding(horizontal = menuSpallaItemTitleSectionPaddingHorizontal.dp)
                .padding(top = menuSpallaItemTitleSectionPaddingTop.dp)
                .testTag("sirioSpalla${title.takeTwoWords()}"),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            typography = SirioTheme.foundationTypography.headlineMdHeavy,
        )
    }
}

@Keep
data class SirioMenuSpallaItemTitleSectionColors(
    val background: Color,
    val border: Color,
    val content: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioMenuSpallaItemTitleSectionColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            content = Color.Unspecified,
        )
    }
}

@Keep
data class SirioMenuSpallaItemTitleSectionTypography(
    val title: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioMenuSpallaItemTitleSectionTypography(
            title = TextStyle.Default,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SirioMenuSpallaItemTitleSectionPreview() {
    SirioTheme {
        SirioMenuSpallaItemTitleSection("Label menu")
    }
}
