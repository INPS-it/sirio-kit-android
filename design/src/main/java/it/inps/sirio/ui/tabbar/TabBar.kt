//
// TabBar.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.*
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import kotlin.math.max

/**
 * A bottom navigation with tabs
 *
 * @param items The items [TabBarItemData] to be shown. Min 3 - Max 5
 * @param navController The navigation controller in which perform the routes
 */
@Composable
fun TabBar(items: List<TabBarItemData>, navController: NavHostController) {
    //TabBar should contain 3-5 tabs
    assert(items.size in 3..5)
    BottomNavigation(
        backgroundColor = SirioTheme.colors.tabBarBackground,
        modifier = Modifier
            .fillMaxWidth()
            .height(tabBarHeight)
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        val labelTypography = with(SirioTheme.typography.tabBarItemText) {
            if (LocalConfiguration.current.fontScale > 1) copy(fontSize = 9.sp) else this
        }

        items.take(5).forEach { tabItem ->
            val selected = currentRoute == tabItem.route
            TabBarItem(
                // it currentRoute is equal then its selected route
                selected = selected,

                // navigate on click
                onClick = {
                    try {
                        navController.navigate(tabItem.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    } catch (e: Exception) {
                        Log.e("TabBar", "TabBar: onclick exception ", e)
                    }
                },

                // Icon of tabItem
                icon = {
                    BadgedBox(
                        badge = {
                            if (tabItem.badge) {
                                Badge()
                            }
                        }) {
                        SirioIcon(
                            faIcon = tabItem.icon,
                            size = tabBarItemIconSize,
                            iconColor = if (selected) SirioTheme.colors.tabBarActive else SirioTheme.colors.tabBarContent,
                        )
                    }
                },

                // label
                label = {
                    SirioTextCommon(
                        text = tabItem.label,
                        color = if (selected) SirioTheme.colors.tabBarActive else SirioTheme.colors.tabBarContent,
                        maxLines = 1,
                        typography = labelTypography,
                    )
                },
            )
        }
    }
}

/**
 * A single tab component to be used in tab bar
 *
 * @param label A composable with the tab text
 * @param icon A composable with the tab icon
 * @param selected Whether the tab is the selected one
 * @param onClick The tab click callback
 * @param modifier The modifier is used only by other Sirio components
 * @param enabled Whether the tab can be selected by user
 * @param interactionSource The [MutableInteractionSource] to handle state changes
 * @param selectedContentColor The ripple effect color
 */
@Composable
internal fun RowScope.TabBarItem(
    label: @Composable (() -> Unit),
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
) {
    val styledLabel: @Composable (() -> Unit) = label.let {
        @Composable {
            val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(style, content = label)
        }
    }
    // The color of the Ripple should always the selected color, as we want to show the color
    // before the item is considered selected, and hence before the new contentColor is
    // provided by BottomNavigationTransition.
    val ripple = rememberRipple(bounded = false, color = selectedContentColor)

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        TabBarItemBaselineLayout(
            icon = icon,
            label = styledLabel,
            selected = selected,
        )
    }
}

/**
 * Sirio Tab item layout
 *
 * @param icon A composable with the tab icon
 * @param label A composable with the tab text
 * @param selected Whether the tab is the selected one
 */
@Composable
private fun TabBarItemBaselineLayout(
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit),
    selected: Boolean,
) {
//    val configuration = LocalConfiguration.current
    Layout(
        {
            Box(
                Modifier
                    .layoutId("state")
                    .height(tabBarItemStateIndicatorHeight)
                    .fillMaxWidth()
                    .background(if (selected) SirioTheme.colors.tabBarActive else SirioTheme.colors.tabBarBackground)
            )
            Box(Modifier.layoutId("icon")) { icon() }
            Box(
                Modifier
                    .layoutId("label")
                    .padding(horizontal = tabBarItemHorizontalPadding)
            ) { label() }
        }
    ) { measurables, constraints ->
        val statePlaceable = measurables.first { it.layoutId == "state" }.measure(constraints)

        val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)

        val labelPlaceable = measurables.first { it.layoutId == "label" }.measure(
            // Measure with loose constraints for height as we don't want the label to take up more
            // space than it needs
            constraints.copy(minHeight = 0)
        )

//        when (configuration.orientation) {
//            Configuration.ORIENTATION_LANDSCAPE -> {
//                tabBarItemPlaceLabelAndIconInRow(
//                    statePlaceable,
//                    labelPlaceable,
//                    iconPlaceable,
//                    constraints,
//                )
//            }
//            else -> {
        tabBarItemPlaceLabelAndIconInColumn(
            statePlaceable,
            labelPlaceable,
            iconPlaceable,
            constraints,
        )

//            }
//        }

    }
}

/**
 * Tab bar item layout in column for small screen devices
 */
private fun MeasureScope.tabBarItemPlaceLabelAndIconInColumn(
    statePlaceable: Placeable,
    labelPlaceable: Placeable,
    iconPlaceable: Placeable,
    constraints: Constraints,
): MeasureResult {
    val containerHeight = constraints.maxHeight
    val containerWidth = constraints.maxWidth

    val iconX = (containerWidth - iconPlaceable.width) / 2
    val iconY = tabBarItemVerticalPadding.roundToPx()

    val labelX = (containerWidth - labelPlaceable.width) / 2
    val labelY = iconY + iconPlaceable.height

    return layout(containerWidth, containerHeight) {
        statePlaceable.placeRelative(0, 0)
        iconPlaceable.placeRelative(iconX, iconY)
        labelPlaceable.placeRelative(labelX, labelY)
    }
}

/**
 * Tab bar item layout in row for large screen devices
 */
private fun MeasureScope.tabBarItemPlaceLabelAndIconInRow(
    statePlaceable: Placeable,
    labelPlaceable: Placeable,
    iconPlaceable: Placeable,
    constraints: Constraints,
): MeasureResult {
    val containerHeight = constraints.maxHeight
    val containerWidth = constraints.maxWidth

    val contentWidth = iconPlaceable.width + labelPlaceable.width
    val iconX = max(
        (containerWidth - contentWidth) / 2,
        tabBarItemHorizontalPadding.roundToPx()
    )
    val iconY = (containerHeight - iconPlaceable.height) / 2

    //Eliminate the padding between label and icon
    //Add 1 px to prevent overlap due to round
    val labelX =
        iconX + iconPlaceable.width - tabBarItemHorizontalPadding.roundToPx() + 1
    val labelY = (containerHeight - labelPlaceable.height) / 2

    return layout(containerWidth, containerHeight) {
        statePlaceable.placeRelative(0, 0)
        iconPlaceable.placeRelative(iconX, iconY)
        labelPlaceable.placeRelative(labelX, labelY)
    }
}


@Preview(showSystemUi = true)
@Composable
private fun TabBarPreview() {
    SirioTheme {
        Scaffold(
            bottomBar = {
                TabBar(
                    items = listOf(
                        TabBarItemData(
                            label = "Home",
                            icon = FaIcons.Home,
                            route = "InpsScreen.HomeScreen.route"
                        ),
                        TabBarItemData(
                            label = "News",
                            icon = FaIcons.Newspaper,
                            route = "InpsScreen.NewsScreen.route"
                        ),
                        TabBarItemData(
                            label = "Mappe",
                            icon = FaIcons.Globe,
                            route = "InpsScreen.MapsScreen.route"
                        ),
                        TabBarItemData(
                            label = "Contattaci",
                            icon = FaIcons.CommentAlt,
                            route = "InpsScreen.ContattaciScreen.route"
                        ),
                        TabBarItemData(
                            label = "Servizi",
                            icon = FaIcons.GripHorizontal,
                            route = "InpsScreen.ServiziScreen.route"
                        )
                    ),
                    navController = rememberNavController()
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
            }
        }
    }
}
