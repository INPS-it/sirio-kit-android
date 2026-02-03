package it.inps.design.foundation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.foundation.foundationTypography
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.searchbar.SirioSearchBar

private const val TYPOGRAPHY = "Typography"
private const val COLOR = "Color"

class FoundationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SirioTheme {
                FoundationDemoView()
            }
        }
    }
}

@Composable
private fun FoundationDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text(text = "Foundation token") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FoundationColor.colorGlobalPrimary100)
            )
        }
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "/",
            modifier = Modifier.padding(it),
        ) {
            composable("/") {
                FoundationMenuDemo(navController = navController)
            }
            composable(TYPOGRAPHY) {
                FoundationTypographyDemo()
            }
            composable(COLOR) {
                FoundationColorDemo()
            }
        }
    }
}

@Composable
private fun FoundationMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            SirioListItem(TYPOGRAPHY) {
                navController.navigate(TYPOGRAPHY)
            }
            SirioListItem(COLOR, showDivider = false) {
                navController.navigate(COLOR)
            }
        }
    }
}

@Composable
private fun FoundationColorDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
    ) {
        var filter by remember { mutableStateOf("") }
        SirioSearchBar(
            searchText = filter,
            placeholder = "Cerca un colore",
            onSearchTextChange = { filter = it },
        )
        LazyColumn(
            modifier = Modifier.imePadding(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = colorMap.keys.toList().filter { it.contains(filter) },
                key = { it }
            ) { key ->
                val color = colorMap[key]
                Card {
                    Column(
                        Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = key)
                        val sizeModifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .fillMaxWidth()
                            .height(100.dp)
                        val modifier: Modifier = when (color) {
                            is Color -> {
                                sizeModifier.background(color)
                            }

                            is List<*> -> {
                                val gradient: List<Color> = color
                                    .filterIsInstance<Color>() // Filter elements that are instances of Color
                                    .takeIf { it.isNotEmpty() } // Check if the filtered list is not empty
                                    ?.map { it } // If not empty, map the elements to themselves (effectively a cast)
                                    ?: listOf()
                                sizeModifier.background(Brush.verticalGradient(gradient))
                            }

                            else -> Modifier
                        }
                        Box(modifier)
                    }
                }
            }
        }
    }
}

@Composable
private fun FoundationTypographyDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
    ) {
        var filter by remember { mutableStateOf("") }
        SirioSearchBar(
            searchText = filter,
            placeholder = "Cerca una typography",
            onSearchTextChange = { filter = it },
        )
        val text = "Progettare per il Cittadino da oggi è più semplice"
        LazyColumn(
            modifier = Modifier.imePadding(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(typographyMap.keys.toList().filter { it.contains(filter) }, key = { it }) { key ->
                val typography = typographyMap[key]!!
                Card {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = key)
                        Text(text = text, style = typography)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FoundationColorDemoPreview() {
    SirioTheme {
        FoundationColorDemo()
    }
}

@Preview
@Composable
private fun FoundationTypographyDemoPreview() {
    SirioTheme {
        FoundationTypographyDemo()
    }
}

val colorMap = mapOf(
    "alias/app-interactive-primary-000-default" to FoundationColor.colorAliasAppInteractivePrimary000Default,
    "alias/app-interactive-primary-active" to FoundationColor.colorAliasAppInteractivePrimaryActive,
    "alias/app-interactive-secondary-default" to FoundationColor.colorAliasAppInteractiveSecondaryDefault,
    "alias/background-color-disabled" to FoundationColor.colorAliasBackgroundColorDisabled,
    "alias/background-color-primary-dark-110" to FoundationColor.colorAliasBackgroundColorPrimaryDark110,
    "alias/background-color-primary-dark-115" to FoundationColor.colorAliasBackgroundColorPrimaryDark115,
    "alias/background-color-primary-dark-120" to FoundationColor.colorAliasBackgroundColorPrimaryDark120,
    "alias/background-color-primary-light-0" to FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    "alias/background-color-primary-light-40" to FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    "alias/background-color-primary-light-50" to FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    "alias/background-color-primary-light-60" to FoundationColor.colorAliasBackgroundColorPrimaryLight60,
    "alias/background-color-secondary-dark-110" to FoundationColor.colorAliasBackgroundColorSecondaryDark110,
    "alias/background-color-secondary-dark-120" to FoundationColor.colorAliasBackgroundColorSecondaryDark120,
    "alias/background-color-secondary-dark-125" to FoundationColor.colorAliasBackgroundColorSecondaryDark125,
    "alias/background-color-secondary-dark-130" to FoundationColor.colorAliasBackgroundColorSecondaryDark130,
    "alias/background-color-secondary-light-40" to FoundationColor.colorAliasBackgroundColorSecondaryLight40,
    "alias/background-color-secondary-light-50" to FoundationColor.colorAliasBackgroundColorSecondaryLight50,
    "alias/background-color-secondary-mid-90" to FoundationColor.colorAliasBackgroundColorSecondaryMid90,
    "alias/gradient-accent-primary" to FoundationColor.colorAliasGradientAccentPrimary,
    "alias/gradient-accent-primary-dark" to FoundationColor.colorAliasGradientAccentPrimaryDark,
    "alias/interactive-accent-default" to FoundationColor.colorAliasInteractiveAccentDefault,
    "alias/interactive-accent-focus" to FoundationColor.colorAliasInteractiveAccentFocus,
    "alias/interactive-accent-hover" to FoundationColor.colorAliasInteractiveAccentHover,
    "alias/interactive-accent-pressed" to FoundationColor.colorAliasInteractiveAccentPressed,
    "alias/interactive-alert-default" to FoundationColor.colorAliasInteractiveAlertDefault,
    "alias/interactive-alert-focus" to FoundationColor.colorAliasInteractiveAlertFocus,
    "alias/interactive-alert-hover" to FoundationColor.colorAliasInteractiveAlertHover,
    "alias/interactive-alert-pressed" to FoundationColor.colorAliasInteractiveAlertPressed,
    "alias/interactive-border-focus" to FoundationColor.colorAliasInteractiveBorderFocus,
    "alias/interactive-primary-000-default" to FoundationColor.colorAliasInteractivePrimary000Default,
    "alias/interactive-primary-default" to FoundationColor.colorAliasInteractivePrimaryDefault,
    "alias/interactive-primary-focus" to FoundationColor.colorAliasInteractivePrimaryFocus,
    "alias/interactive-primary-hover" to FoundationColor.colorAliasInteractivePrimaryHover,
    "alias/interactive-primary-pressed" to FoundationColor.colorAliasInteractivePrimaryPressed,
    "alias/interactive-secondary-default" to FoundationColor.colorAliasInteractiveSecondaryDefault,
    "alias/interactive-secondary-focus" to FoundationColor.colorAliasInteractiveSecondaryFocus,
    "alias/interactive-secondary-hover" to FoundationColor.colorAliasInteractiveSecondaryHover,
    "alias/interactive-secondary-pressed" to FoundationColor.colorAliasInteractiveSecondaryPressed,
    "alias/interactive-success-default" to FoundationColor.colorAliasInteractiveSuccessDefault,
    "alias/interactive-success-focus" to FoundationColor.colorAliasInteractiveSuccessFocus,
    "alias/interactive-success-hover" to FoundationColor.colorAliasInteractiveSuccessHover,
    "alias/interactive-success-pressed" to FoundationColor.colorAliasInteractiveSuccessPressed,
    "alias/interactive-warning-default" to FoundationColor.colorAliasInteractiveWarningDefault,
    "alias/interactive-warning-focus" to FoundationColor.colorAliasInteractiveWarningFocus,
    "alias/interactive-warning-hover" to FoundationColor.colorAliasInteractiveWarningHover,
    "alias/interactive-warning-pressed" to FoundationColor.colorAliasInteractiveWarningPressed,
    "alias/overlay-15-secondary-100" to FoundationColor.colorAliasOverlay15Secondary100,
    "alias/overlay-25-primary-000" to FoundationColor.colorAliasOverlay25Primary000,
    "alias/overlay-90-dark-primary-120" to FoundationColor.colorAliasOverlay90DarkPrimary120,
    "alias/text-color-disabled" to FoundationColor.colorAliasTextColorDisabled,
    "alias/text-color-primary-100" to FoundationColor.colorAliasTextColorPrimary100,
    "alias/text-color-primary-dark-110" to FoundationColor.colorAliasTextColorPrimaryDark110,
    "alias/text-color-primary-light-0" to FoundationColor.colorAliasTextColorPrimaryLight0,
    "alias/text-color-primary-light-40" to FoundationColor.colorAliasTextColorPrimaryLight40,
    "alias/text-color-primary-light-50" to FoundationColor.colorAliasTextColorPrimaryLight50,
    "alias/text-color-secondary-dark-100" to FoundationColor.colorAliasTextColorSecondaryDark100,
    "alias/text-color-secondary-dark-130" to FoundationColor.colorAliasTextColorSecondaryDark130,
    "global/accent-100" to FoundationColor.colorGlobalAccent100,
    "global/accent-110" to FoundationColor.colorGlobalAccent110,
    "global/accent-120" to FoundationColor.colorGlobalAccent120,
    "global/accent-70" to FoundationColor.colorGlobalAccent70,
    "global/dark-primary-110" to FoundationColor.colorGlobalDarkPrimary110,
    "global/dark-primary-115" to FoundationColor.colorGlobalDarkPrimary115,
    "global/dark-primary-120" to FoundationColor.colorGlobalDarkPrimary120,
    "global/dark-primary-130" to FoundationColor.colorGlobalDarkPrimary130,
    "global/dark-secondary-110" to FoundationColor.colorGlobalDarkSecondary110,
    "global/dark-secondary-120" to FoundationColor.colorGlobalDarkSecondary120,
    "global/dark-secondary-125" to FoundationColor.colorGlobalDarkSecondary125,
    "global/dark-secondary-130" to FoundationColor.colorGlobalDarkSecondary130,
    "global/gradient-01" to FoundationColor.colorGlobalGradient01,
    "global/gradient-02" to FoundationColor.colorGlobalGradient02,
    "global/gradient-03" to FoundationColor.colorGlobalGradient03,
    "global/light-primary-40" to FoundationColor.colorGlobalLightPrimary40,
    "global/light-primary-50" to FoundationColor.colorGlobalLightPrimary50,
    "global/light-primary-60" to FoundationColor.colorGlobalLightPrimary60,
    "global/light-secondary-40" to FoundationColor.colorGlobalLightSecondary40,
    "global/light-secondary-50" to FoundationColor.colorGlobalLightSecondary50,
    "global/light-secondary-60" to FoundationColor.colorGlobalLightSecondary60,
    "global/mid-primary-70" to FoundationColor.colorGlobalMidPrimary70,
    "global/mid-primary-80" to FoundationColor.colorGlobalMidPrimary80,
    "global/mid-primary-90" to FoundationColor.colorGlobalMidPrimary90,
    "global/mid-secondary-70" to FoundationColor.colorGlobalMidSecondary70,
    "global/mid-secondary-80" to FoundationColor.colorGlobalMidSecondary80,
    "global/mid-secondary-90" to FoundationColor.colorGlobalMidSecondary90,
    "global/primary-000" to FoundationColor.colorGlobalPrimary000,
    "global/primary-100" to FoundationColor.colorGlobalPrimary100,
    "global/secondary-100" to FoundationColor.colorGlobalSecondary100,
    "global/secondary-90" to FoundationColor.colorGlobalSecondary90,
    "global/semantic-alert-100" to FoundationColor.colorGlobalSemanticAlert100,
    "global/semantic-alert-110" to FoundationColor.colorGlobalSemanticAlert110,
    "global/semantic-alert-120" to FoundationColor.colorGlobalSemanticAlert120,
    "global/semantic-alert-130" to FoundationColor.colorGlobalSemanticAlert130,
    "global/semantic-info-100" to FoundationColor.colorGlobalSemanticInfo100,
    "global/semantic-success-100" to FoundationColor.colorGlobalSemanticSuccess100,
    "global/semantic-success-110" to FoundationColor.colorGlobalSemanticSuccess110,
    "global/semantic-success-120" to FoundationColor.colorGlobalSemanticSuccess120,
    "global/semantic-success-130" to FoundationColor.colorGlobalSemanticSuccess130,
    "global/semantic-warning-100" to FoundationColor.colorGlobalSemanticWarning100,
    "global/semantic-warning-110" to FoundationColor.colorGlobalSemanticWarning110,
    "global/semantic-warning-80" to FoundationColor.colorGlobalSemanticWarning80,
    "global/semantic-warning-90" to FoundationColor.colorGlobalSemanticWarning90,
    "specific/card-overlay-gradient-img" to FoundationColor.colorSpecificCardOverlayGradientImg,
    "specific/data-entry-border-color-alert" to FoundationColor.colorSpecificDataEntryBorderColorAlert,
    "specific/data-entry-border-color-default" to FoundationColor.colorSpecificDataEntryBorderColorDefault,
    "specific/data-entry-border-color-focus" to FoundationColor.colorSpecificDataEntryBorderColorFocus,
    "specific/data-entry-border-color-hover" to FoundationColor.colorSpecificDataEntryBorderColorHover,
    "specific/data-entry-border-color-success" to FoundationColor.colorSpecificDataEntryBorderColorSuccess,
    "specific/data-entry-border-color-valued" to FoundationColor.colorSpecificDataEntryBorderColorValued,
    "specific/data-entry-border-color-warning" to FoundationColor.colorSpecificDataEntryBorderColorWarning,
    "specific/data-entry-label-color-alert" to FoundationColor.colorSpecificDataEntryLabelColorAlert,
    "specific/data-entry-label-color-default" to FoundationColor.colorSpecificDataEntryLabelColorDefault,
    "specific/data-entry-label-color-focus" to FoundationColor.colorSpecificDataEntryLabelColorFocus,
    "specific/data-entry-label-color-hover" to FoundationColor.colorSpecificDataEntryLabelColorHover,
    "specific/data-entry-label-color-success" to FoundationColor.colorSpecificDataEntryLabelColorSuccess,
    "specific/data-entry-label-color-valued" to FoundationColor.colorSpecificDataEntryLabelColorValued,
    "specific/data-entry-label-color-warning" to FoundationColor.colorSpecificDataEntryLabelColorWarning,
    "specific/data-entry-placeholder-color-alert" to FoundationColor.colorSpecificDataEntryPlaceholderColorAlert,
    "specific/data-entry-placeholder-color-default" to FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
    "specific/data-entry-placeholder-color-focus" to FoundationColor.colorSpecificDataEntryPlaceholderColorFocus,
    "specific/data-entry-placeholder-color-hover" to FoundationColor.colorSpecificDataEntryPlaceholderColorHover,
    "specific/data-entry-placeholder-color-success" to FoundationColor.colorSpecificDataEntryPlaceholderColorSuccess,
    "specific/data-entry-placeholder-color-valued" to FoundationColor.colorSpecificDataEntryPlaceholderColorValued,
    "specific/data-entry-placeholder-color-warning" to FoundationColor.colorSpecificDataEntryPlaceholderColorWarning,
    "specific/option-background-color-focus" to FoundationColor.colorSpecificOptionBackgroundColorFocus,
    "specific/option-background-color-hover" to FoundationColor.colorSpecificOptionBackgroundColorHover,
    "specific/option-background-color-pressed" to FoundationColor.colorSpecificOptionBackgroundColorPressed,
    "specific/option-background-color-valued" to FoundationColor.colorSpecificOptionBackgroundColorValued,
    "specific/progressbar-background-color" to FoundationColor.colorSpecificProgressbarBackgroundColor,
)

val typographyMap = mapOf(
    "display-lg-regular" to foundationTypography.displayLgRegular,
    "display-lg-middle" to foundationTypography.displayLgMiddle,
    "display-lg-heavy" to foundationTypography.displayLgHeavy,
    "display-md-regular" to foundationTypography.displayMdRegular,
    "display-md-middle" to foundationTypography.displayMdMiddle,
    "display-md-heavy" to foundationTypography.displayMdHeavy,
    "display-sm-regular" to foundationTypography.displaySmRegular,
    "display-sm-middle" to foundationTypography.displaySmMiddle,
    "display-sm-heavy" to foundationTypography.displaySmHeavy,
    "headline-lg-regular" to foundationTypography.headlineLgRegular,
    "headline-lg-middle" to foundationTypography.headlineLgMiddle,
    "headline-lg-heavy" to foundationTypography.headlineLgHeavy,
    "headline-md-regular" to foundationTypography.headlineMdRegular,
    "headline-md-middle" to foundationTypography.headlineMdMiddle,
    "headline-md-heavy" to foundationTypography.headlineMdHeavy,
    "headline-sm-regular" to foundationTypography.headlineSmRegular,
    "headline-sm-middle" to foundationTypography.headlineSmMiddle,
    "headline-sm-heavy" to foundationTypography.headlineSmHeavy,
    "body-md-regular" to foundationTypography.bodyMdRegular,
    "body-md-heavy" to foundationTypography.bodyMdHeavy,
    "body-sm-regular" to foundationTypography.bodySmRegular,
    "body-sm-heavy" to foundationTypography.bodySmHeavy,
    "link-lg-regular" to foundationTypography.linkLgRegular,
    "link-lg-heavy" to foundationTypography.linkLgHeavy,
    "link-md-regular" to foundationTypography.linkMdRegular,
    "link-md-heavy" to foundationTypography.linkMdHeavy,
    "link-sm-regular" to foundationTypography.linkSmRegular,
    "link-sm-heavy" to foundationTypography.linkSmHeavy,
    "label-md-regular" to foundationTypography.labelMdRegular,
    "label-md-middle" to foundationTypography.labelMdMiddle,
    "label-md-heavy" to foundationTypography.labelMdHeavy,
    "label-sm-regular" to foundationTypography.labelSmRegular,
    "label-sm-middle" to foundationTypography.labelSmMiddle,
    "label-sm-heavy" to foundationTypography.labelSmHeavy,
    "label-number-md-regular" to foundationTypography.labelNumberMdRegular,
    "label-number-md-heavy" to foundationTypography.labelNumberMdHeavy,
    "label-number-sm-regular" to foundationTypography.labelNumberSmRegular,
    "label-number-sm-heavy" to foundationTypography.labelNumberSmHeavy,
    "signature-serif-md-regular" to foundationTypography.signatureSerifMdRegular,
    "signature-serif-md-heavy" to foundationTypography.signatureSerifMdHeavy,
    "signature-serif-sm-regular" to foundationTypography.signatureSerifSmRegular,
    "signature-serif-sm-heavy" to foundationTypography.signatureSerifSmHeavy,
    "tabbar-label-xs-regular" to foundationTypography.tabbarLabelXsRegular
)
