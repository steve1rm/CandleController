package me.androidbox.cakelightingcontrol

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cakelightingcontrol.composeapp.generated.resources.Res
import cakelightingcontrol.composeapp.generated.resources.candle
import cakelightingcontrol.composeapp.generated.resources.candle1
import cakelightingcontrol.composeapp.generated.resources.candle2
import cakelightingcontrol.composeapp.generated.resources.candle3
import cakelightingcontrol.composeapp.generated.resources.candle4
import cakelightingcontrol.composeapp.generated.resources.candle5
import cakelightingcontrol.composeapp.generated.resources.candle6
import cakelightingcontrol.composeapp.generated.resources.flameoff
import cakelightingcontrol.composeapp.generated.resources.flameon
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CandleBoard(
    onAction: (action: CandleAction) -> Unit,
    candleStates: List<CandleState>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        FirstCandleRow(
            candleState1 = candleStates[0],
            candleState2 = candleStates[1],
            onAction = onAction
        )

        SecondCandleRow(
            candleState3 = candleStates[2],
            candleState4 = candleStates[3],
            candleState5 = candleStates[4],
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .offset(y = -(100.dp)),
            onAction = onAction
        )

        ThirdCandleRow(
            candleState6 = candleStates[5],
            candleState7 = candleStates[6],
            Modifier
                .offset(y = -(200.dp)),
            onAction = onAction
        )
    }
}

@Composable
fun FirstCandleRow(
    onAction: (action: CandleAction) -> Unit,
    candleState1: CandleState,
    candleState2: CandleState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CandleStick(
            lightCandle = candleState1.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState1))
            }
        )

        CandleStick(
            lightCandle = candleState2.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle1),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState2))
            }
        )
    }
}

@Composable
fun SecondCandleRow(
    candleState3: CandleState,
    candleState4: CandleState,
    candleState5: CandleState,
    modifier: Modifier = Modifier,
    onAction: (CandleAction) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CandleStick(
            lightCandle = candleState3.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle2),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState3))
            }
        )

        CandleStick(
            lightCandle = candleState4.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle3),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState4))
            }
        )

        CandleStick(
            lightCandle = candleState5.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle4),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState5))
            }
        )
    }
}

@Composable
fun ThirdCandleRow(
    candleState6: CandleState,
    candleState7: CandleState,
    modifier: Modifier = Modifier,
    onAction: (CandleAction) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CandleStick(
            lightCandle = candleState6.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle5),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState6))
            }
        )

        CandleStick(
            lightCandle = candleState7.isLit,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle6),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {
                onAction(CandleAction.OnClicked(candleState7))
            }
        )
    }
}

@Preview
@Composable
fun CandleBoardPreview() {
    MaterialTheme {
        CandleBoard(
            candleStates = listOf(CandleState()),
            onAction = {}
        )
    }
}

@Preview
@Composable
fun ThreeCandleRowPreview() {
    MaterialTheme {
        SecondCandleRow(
            candleState3 = CandleState(),
            candleState4 = CandleState(),
            candleState5 = CandleState(),
            onAction = {

            }
        )
    }
}

@Preview
@Composable
fun TwoCandleRowPreview() {
    MaterialTheme {
        FirstCandleRow(
            candleState1 = CandleState(),
            candleState2 = CandleState(),
            onAction = {

            }
        )
    }
}

@Composable
fun CandleStick(
    modifier: Modifier = Modifier,
    lightCandle: Boolean,
    onClicked: () -> Unit,
    candleBody: @Composable () -> Unit) {
    Column(
        modifier = modifier
            .clickable {
                onClicked()
            }
    ) {

        Box(
            modifier = Modifier
                .size(width = 38.dp, height = 70.dp)
                .offset(y = 6.dp),
            contentAlignment = Alignment.Center) {

            if(lightCandle) {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.flameon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            else {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.flameoff),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
        candleBody()
    }
}

@Preview
@Composable
fun CandleStickPreview() {
    MaterialTheme {
        CandleStick(
            lightCandle = false,
            candleBody = {
                Icon(
                    modifier = Modifier.size(height = 180.dp, width = 46.dp),
                    imageVector = vectorResource(Res.drawable.candle),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            onClicked = {

            }
        )
    }
}
