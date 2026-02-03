//
// SirioFilterDialog.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun SirioFilterFullScreenDialog(
    state: SirioFilterState,
    onDismissRequest: () -> Unit,
    onRemoveFilters: (() -> Unit)?,
    onApplyFilters: (List<SirioFilterDrawerType>) -> Unit,
    title: String = "Filtri",
    closeContentDescription: String? = null,
) {
    // Stato di transizione per avere sia slide-in che slide-out
    val transitionState = remember {
        MutableTransitionState(false).apply {
            targetState = true // entra subito animando
        }
    }

    // Quando la visibilità è diventata false E l'animazione è finita → chiudiamo davvero la dialog
    LaunchedEffect(transitionState.currentState, transitionState.targetState) {
        if (!transitionState.currentState && !transitionState.targetState) {
            onDismissRequest()
        }
    }

    Dialog(
        onDismissRequest = {
            // Avvia animazione di uscita
            transitionState.targetState = false
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false //FULLSCREEN
        )
    ) {
        AnimatedVisibility(
            visibleState = transitionState,
            enter = fadeIn() + slideInHorizontally { fullWidth -> fullWidth },
            exit = fadeOut() + slideOutHorizontally { fullWidth -> fullWidth },
        ) {
            SirioFilterDrawer(
                state = state,
                onClose = {
                    // Chiudi con animazione di uscita
                    transitionState.targetState = false
                },
                onRemoveFilters = {
                    onRemoveFilters?.invoke()
                },
                onApplyFilters = { filters ->
                    onApplyFilters(filters)
                    // dopo aver applicato i filtri → chiudi con animazione
                    transitionState.targetState = false
                },
                title = title,
                closeContentDescription = closeContentDescription,
            )
        }
    }
}