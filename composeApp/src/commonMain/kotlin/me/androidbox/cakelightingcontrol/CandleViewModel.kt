package me.androidbox.cakelightingcontrol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class CandleViewModel : ViewModel() {
    private val _candleState = MutableStateFlow(generateCandles())
    val candleState = _candleState.asStateFlow()

    val allLitCandlesSharedState = MutableSharedFlow<CandleEvent>()

    init {
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

                _candleState.update { listOfCandles ->
                    val newList = listOfCandles.map { candleInList ->
                        if (candleInList.id == action.candleState.id) {
                            candleInList.copy(isLit = !candleInList.isLit)
                        } else {
                            candleInList
                        }
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
}