package it.inps.sirio.ui.appnavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirst
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationHeight
import it.inps.sirio.theme.appNavigationPaddingHorizontal
import kotlin.math.max

/**
 * [SirioTopAppBar] is the standard implementation of a Sirio top app bar.
 *
 * @param title The composable title of the app bar.
 * @param centerTitle Whether to center the title or not.
 * @param navigationIcon The composable navigation icon.
 * @param containerColor The background color of the app bar.
 * @param actions The composable actions of the app bar.
 * @receiver [RowScope]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioTopAppBar(
    title: @Composable () -> Unit,
    centerTitle: Boolean = false,
    navigationIcon: @Composable () -> Unit = {},
    containerColor: Color = SirioTheme.colors.appNavigation.background,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(appNavigationHeight.dp),
        color = containerColor,
    ) {
        Layout(
            {
                Box(Modifier.layoutId("navigationIcon")) { navigationIcon() }
                Box(Modifier.layoutId("title"), contentAlignment = Alignment.Center) { title() }
                Row(Modifier.layoutId("actionIcons")) { actions() }
            },
            modifier = Modifier
                .windowInsetsPadding(windowInsets)
                // clip after padding so we don't show the title over the inset area
                .clipToBounds()
        ) { measurables, constraints ->
            val navigationIconPlaceable =
                measurables
                    .fastFirst { it.layoutId == "navigationIcon" }
                    .measure(constraints.copy(minWidth = 0))
            val actionIconsPlaceable =
                measurables
                    .fastFirst { it.layoutId == "actionIcons" }
                    .measure(constraints.copy(minWidth = 0))
            val maxTitleWidth =
                if (constraints.maxWidth == Constraints.Infinity) {
                    constraints.maxWidth
                } else {
                    (
                            constraints.maxWidth
                                    - max(
                                appNavigationPaddingHorizontal.dp.roundToPx(),
                                navigationIconPlaceable.width,
                            )
                                    - max(
                                appNavigationPaddingHorizontal.dp.roundToPx(),
                                actionIconsPlaceable.width,
                            )
                            )
                        .coerceAtLeast(0)
                }
            val titlePlaceable =
                measurables
                    .fastFirst { it.layoutId == "title" }
                    .measure(constraints.copy(minWidth = 0, maxWidth = maxTitleWidth))

            layout(constraints.maxWidth, constraints.maxHeight) {
                // Navigation icon
                navigationIconPlaceable.placeRelative(
                    x = 0,
                    y = (constraints.maxHeight - navigationIconPlaceable.height) / 2
                )

                // Title
                titlePlaceable.placeRelative(
                    x =
                        when {
                            centerTitle -> {
                                var baseX = (constraints.maxWidth - titlePlaceable.width) / 2
                                if (baseX < navigationIconPlaceable.width) {
                                    // May happen if the navigation is wider than the actions and the
                                    // title is long. In this case, prioritize showing more of the title
                                    // by
                                    // offsetting it to the right.
                                    baseX += (navigationIconPlaceable.width - baseX)
                                } else if (
                                    baseX + titlePlaceable.width >
                                    constraints.maxWidth - actionIconsPlaceable.width
                                ) {
                                    // May happen if the actions are wider than the navigation and the
                                    // title
                                    // is long. In this case, offset to the left.
                                    baseX +=
                                        ((constraints.maxWidth - actionIconsPlaceable.width) -
                                                (baseX + titlePlaceable.width))
                                }
                                baseX
                            }

                            // appNavigationPaddingHorizontal will make sure the title is offset in case the
                            // navigation icon is missing.
                            else -> max(
                                appNavigationPaddingHorizontal.dp.roundToPx(),
                                navigationIconPlaceable.width,
                            )
                        },
                    y = (constraints.maxHeight - titlePlaceable.height) / 2
                )

                // Action icons
                actionIconsPlaceable.placeRelative(
                    x = constraints.maxWidth - actionIconsPlaceable.width,
                    y = (constraints.maxHeight - actionIconsPlaceable.height) / 2
                )
            }
        }
    }
}

@Preview(heightDp = 1010)
@Composable
private fun SirioTopAppBarPreview() {
    Column {
        val title = @Composable { SirioAppNavigationTitle("Titolo pagina") }
        val longTitle =
            @Composable { SirioAppNavigationTitle("Lorem ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum") }
        val navigationIcon = @Composable {
            SirioAppNavigationButton(
                SirioAppNavigationItemData(icon = FaIcons.ChevronLeft, action = {})
            )
        }
        val actions: @Composable RowScope.() -> Unit = {
            SirioAppNavigationButton(SirioAppNavigationItemData(icon = FaIcons.User, action = {}))
            SirioAppNavigationButton(SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}))
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
        SirioTheme(darkTheme = true) {
            SirioTopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                centerTitle = true,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                centerTitle = true,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                navigationIcon = navigationIcon,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                navigationIcon = navigationIcon,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                centerTitle = true,
                navigationIcon = navigationIcon,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                centerTitle = true,
                navigationIcon = navigationIcon,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                centerTitle = true,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                centerTitle = true,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = title,
                centerTitle = true,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
        SirioTheme {
            SirioTopAppBar(
                title = longTitle,
                centerTitle = true,
                navigationIcon = navigationIcon,
                actions = actions,
            )
        }
    }
}