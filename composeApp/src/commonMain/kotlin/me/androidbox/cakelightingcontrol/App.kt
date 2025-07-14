package me.androidbox.cakelightingcontrol

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    candleStates: List<CandleState>,
    onAction: (action: CandleAction) -> Unit,
) {
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xff1A597B)
        ) { paddingValues ->
            CandleScreen(
                candleStates = candleStates,
                onAction= onAction,
                modifier = Modifier.padding(paddingValues))
        }
    }
}