package me.androidbox.cakelightingcontrol

sealed interface CandleEvent {
    data class AllCandlesList(val allLit: Boolean) : CandleEvent
}