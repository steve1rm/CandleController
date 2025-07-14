package me.androidbox.cakelightingcontrol

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CandleScreen(
    candleStates: List<CandleState>,
    onAction: (action: CandleAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CandleBoard(
            candleStates = candleStates,
            onAction = onAction
        )
    }
}