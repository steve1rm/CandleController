package me.androidbox.cakelightingcontrol

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val candleViewModel = CandleViewModel()
    App(
       candleViewModel = candleViewModel
    )
}