package me.androidbox.cakelightingcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val candleViewModel by viewModels<CandleViewModel>()
            val collectedCandleStates by candleViewModel.candleState.collectAsStateWithLifecycle()
            val candleEvent by candleViewModel.allLitCandlesSharedState.collectAsStateWithLifecycle(initialValue = CandleEvent.AllCandlesList(false))

            println("MainActivity setContent: First candle isLit = ${collectedCandleStates.firstOrNull()?.isLit}")

            when(val event = candleEvent) {
                is CandleEvent.AllCandlesList -> {
                    println("MainActivity setContent: All candles Lit ${event.allLit}")
                }
            }

            App(
                candleStates = collectedCandleStates,
                onAction = { action ->
                   candleViewModel.onAction(action)
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        candleStates = listOf(CandleState()),
        onAction = {}
    )
}
