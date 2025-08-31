package me.androidbox.cakelightingcontrol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class CandleViewModel : ViewModel() {
    private val _candleState = MutableStateFlow(generateCandles())
    val candleState = _candleState.asStateFlow()

    val allLitCandlesSharedState = MutableSharedFlow<CandleEvent>()

    init {
        /** Emit a value when all candles are lit */
        candleState
            .onEach { listOfCandles ->
                val allCandlesLit = listOfCandles.all { candle ->
                    candle.isLit
                }

                println("All Candles Lit-up $allCandlesLit")
                if(allCandlesLit) {
                    allLitCandlesSharedState.emit(CandleEvent.AllCandlesList(allCandlesLit))
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: CandleAction) {
        when(action) {
            is CandleAction.OnClicked -> {
                println("ViewModel: onAction - Clicked candle ID: ${action.candleState.id}")

                candleState.value.map { candle ->
                    if(candle.id == action.candleState.id) {
                        if(candle.isLit) {
                            // We want to turn it off then pause for 6 seconds and turn it on again
                            relightCandle()
                                .onEach { relight ->
                                    if(relight) {
                                        _candleState.update { candleList ->
                                            val newList = candleList.map { candle ->
                                                if(candle.id == action.candleState.id) {
                                                    candle.copy(isLit = true)
                                                }
                                                else {
                                                    candle
                                                }
                                            }
                                            newList
                                        }
                                    }
                                    else {
                                        _candleState.update { candleList ->
                                            val newList = candleList.map { candle ->
                                                if(candle.id == action.candleState.id) {
                                                    candle.copy(isLit = false)
                                                }
                                                else {
                                                    candle
                                                }
                                            }
                                            newList
                                        }
                                    }
                                }.launchIn(viewModelScope)
                        }
                        else {
                            // Its currently off and we want to turn it on
                            _candleState.update { candleList ->
                                val newList = candleList.map { candle ->
                                    if(candle.id == action.candleState.id) {
                                        candle.copy(isLit = true)
                                    }
                                    else {
                                        candle
                                    }
                                }
                                newList
                            }
                        }
                    }
                }
            }

            CandleAction.OnLightAllCandles -> {
                _candleState.update { candleList ->
                    val newList = candleList.map { candle ->
                        candle.copy(isLit = true)
                    }

                    newList
                }
            }
        }
    }

    private fun generateCandles(): List<CandleState> {
        var incrementId = 0

        return generateSequence {
            CandleState(id = incrementId++)
        }
            .take(7)
            .toList()
    }

    private fun relightCandle(): Flow<Boolean> {
        return flow<Boolean> {
            emit(false)
            println("CandleViewModel relight false")
            timer()
            println("CandleViewModel relight true")
            emit(true)
        }
    }

    private suspend fun timer(duration: Duration = 6.seconds) {
        delay(duration.inWholeMilliseconds)
    }
}