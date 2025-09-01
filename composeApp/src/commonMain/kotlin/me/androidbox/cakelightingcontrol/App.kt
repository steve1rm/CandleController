package me.androidbox.cakelightingcontrol

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    candleViewModel: CandleViewModel
) {

    val candleStates by candleViewModel.candleState.collectAsState()
    val candleEvent by candleViewModel.allLitCandlesSharedState.collectAsState(initial = CandleEvent.AllCandlesList(false))

    println("MainActivity setContent: First candle isLit = ${candleStates.firstOrNull()?.isLit}")

    when(val event = candleEvent) {
        is CandleEvent.AllCandlesList -> {
            println("MainActivity setContent: All candles Lit ${event.allLit}")
        }
    }

    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xff1A597B)
        ) { paddingValues ->
            CandleScreen(
                candleStates = candleStates,
                onAction= candleViewModel::onAction,
                modifier = Modifier.padding(paddingValues))
        }
    }
}