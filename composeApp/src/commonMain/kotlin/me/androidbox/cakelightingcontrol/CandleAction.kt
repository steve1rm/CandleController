package me.androidbox.cakelightingcontrol

sealed interface CandleAction {
    data class OnClicked(val candleState: CandleState): CandleAction
    data object OnLightAllCandles : CandleAction
}
